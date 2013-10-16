/***************************************************************************
 * The Lecture2Go software is based on the liferay portal 6.1.1 
 * <http://www.liferay.com>
 * 
 * Lecture2Go <http://lecture2go.uni-hamburg.de> is an open source 
 * platform for media management and distribution. Our goal is to 
 * support the free access to knowledge because this is a component 
 * of each democratic society. The open source software is aimed at 
 * academic institutions and has to strengthen the blended learning.
 * 
 * All Lecture2Go plugins are continuously being developed and improved.
 * For more details please visit <http://lecture2go-open-source.rrz.uni-hamburg.de>
 * 
 * @Autor Lecture2Go Team
 * @Version 1.0
 * @Contact lecture2go-open-source@uni-hamburg.de
 *  
 * Copyright (c) 2013 University of Hamburg / Computer and Data Center (RRZ)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 ***************************************************************************/
package de.uhh.l2g.controller;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.bind.PortletRequestUtils;
import org.springframework.web.portlet.mvc.SimpleFormController;

import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.Host;
import de.uhh.l2g.beans.ProducerLRInfo;
import de.uhh.l2g.dao.FacilityDao;
import de.uhh.l2g.dao.HostDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.model.AdminProducerInputModel;
import de.uhh.l2g.util.L2goPropsUtil;


/**
 * The Class AdminProducerInputController.
 */
public class AdminProducerInputController extends SimpleFormController implements InitializingBean {
	
	/** The dao bean factory. */
	private XmlBeanFactory daoBeanFactory;
	
	/**
	 * Gets the dao bean factory.
	 *
	 * @return the dao bean factory
	 */
	public XmlBeanFactory getDaoBeanFactory() {
		return daoBeanFactory;
	}

	/**
	 * Sets the dao bean factory.
	 *
	 * @param beanFactory the new dao bean factory
	 */
	public void setDaoBeanFactory(XmlBeanFactory beanFactory) {
		this.daoBeanFactory = beanFactory;
	}
	
	/** The l2go producer role id. */
	private int l2goProducerRoleId;

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.SimpleFormController#onSubmitAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	/**
	 * On submit action.
	 *
	 * @param request the request
	 * @param response the response
	 * @param command the command
	 * @param errors the errors
	 * @throws Exception the exception
	 */
	@Override
	protected void onSubmitAction(ActionRequest request, ActionResponse response, Object command, BindException errors) throws Exception {
		AdminProducerInputModel model = (AdminProducerInputModel) command;

		String msg = "";
		ProducerLRInfo producer = null;

		if (model.getId() > 0) {
			int eid = model.getFacilityId();
			List<ProducerLRInfo> producers = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getProducerLRInfoById(model.getId());
			if (!producers.isEmpty()) {
				producer = producers.iterator().next();
				try {
					int hostId = 0;

					List<Host> hosts = ((HostDao)getDaoBeanFactory().getBean("hostDao")).getByFacilityId(eid);
					if (!hosts.isEmpty()) {
						Host host = hosts.iterator().next();
						hostId = host.getId();
					}

					if (((ProducerDao)getDaoBeanFactory().getBean("producerDao")).setFacility(model.getId(), eid, hostId, getL2goProducerRoleId(), true) > 0) {
						msg = "Producer ist freigegeben.";
						if (!hosts.isEmpty()) {
							Host host = hosts.iterator().next();
							File folder = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/");
							if (!folder.exists()) {
								if (folder.mkdir()) {
									Runtime runtime = Runtime.getRuntime();
									String[] cmdArray = { L2goPropsUtil.get("lecture2go.shell.bin"), "-c", "chown nobody " + folder.getAbsolutePath() };
									runtime.exec(cmdArray);
									logger.info("owner nobody set");
									String[] cmdArray1 = { L2goPropsUtil.get("lecture2go.shell.bin"), "-c", "chown nobody:nobody " + folder.getAbsolutePath() };
									runtime.exec(cmdArray1);
									logger.info("group nobody set");
									String[] cmdArray2 = { L2goPropsUtil.get("lecture2go.shell.bin"), "-c", "chmod 701 " + folder.getAbsolutePath() };
									runtime.exec(cmdArray2);
									logger.info("permissions 701 set");

									logger.debug("created directory for producer: " + producer.getIdNum());
								}
							}
						}

					} else {
						msg = "Ihre Aktion war nicht erfolgreich.";
						response.setRenderParameter("colorMsg", "#ff0000");
					}
				} catch (Exception e) {
					msg = "Ein Fehler ist aufgetreten. Ihre Aktion war nicht erfolgreich. Versuchen Sie es nochmal zu einem anderen Zeitpunkt.";
					response.setRenderParameter("colorMsg", "#ff0000");
				}
			}
		}
		response.setRenderParameter("action", "viewproducers");
		response.setRenderParameter("producerid", "" + model.getId());
		response.setRenderParameter("pagesize", "" + model.getPageSize());
		response.setRenderParameter("pagenumber", "" + model.getCurrentPageNumber());
		response.setRenderParameter("facultyid", model.getFilters().get("facultyid"));
		response.setRenderParameter("approved", model.getFilters().get("approved"));
		response.setRenderParameter("messageToShow", msg);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.portlet.mvc.AbstractFormController#formBackingObject
	 * (javax.portlet.PortletRequest)
	 */
	/**
	 * Form backing object.
	 *
	 * @param request the request
	 * @return the object
	 * @throws Exception the exception
	 */
	@Override
	protected Object formBackingObject(PortletRequest request) throws Exception {

		int id = PortletRequestUtils.getIntParameter(request, "producerid", 0);
		int facultyid = PortletRequestUtils.getIntParameter(request, "faculty", 0);
		int pageNumber = PortletRequestUtils.getIntParameter(request, "pagenumber", 1);
		int pageSize = PortletRequestUtils.getIntParameter(request, "pagesize", 10);
		int facultyFilter = PortletRequestUtils.getIntParameter(request, "facultyid", 0);
		String approved = PortletRequestUtils.getStringParameter(request, "approved", "");

		AdminProducerInputModel myModel = new AdminProducerInputModel();

		myModel.setId(id);
		myModel.setCurrentPageNumber(pageNumber);
		myModel.setPageSize(pageSize);
		myModel.setFacilityId(facultyid);

		Map<String, String> allFaculties = new LinkedHashMap<String, String>();
		for (Facility faculty : ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getTopFacilities()) {
			allFaculties.put("" + faculty.getId(), faculty.getName());
		}
		myModel.setAllFaculties(allFaculties);

		myModel.addFilter("facultyid", "" + facultyFilter);
		myModel.addFilter("producerid", "" + id);
		myModel.addFilter("approved", approved);

		return myModel;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	/**
	 * After properties set.
	 *
	 * @throws Exception the exception
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(daoBeanFactory, "DaoBeanFactory not null required");
	}

	/**
	 * Sets the l2go producer role id.
	 *
	 * @param l2goProducerRoleId the new l2go producer role id
	 */
	public void setL2goProducerRoleId(int l2goProducerRoleId) {
		this.l2goProducerRoleId = l2goProducerRoleId;
	}

	/**
	 * Gets the l2go producer role id.
	 *
	 * @return the l2go producer role id
	 */
	public int getL2goProducerRoleId() {
		return l2goProducerRoleId;
	}

}
