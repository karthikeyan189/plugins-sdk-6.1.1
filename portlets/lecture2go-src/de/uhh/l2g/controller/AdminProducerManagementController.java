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
 * For more details please visit <http://lecture2go-demo.rrz.uni-hamburg.de>
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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.PortletRequestUtils;
import org.springframework.web.portlet.mvc.AbstractController;

import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.ProducerLRInfo;
import de.uhh.l2g.dao.FacilityDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.model.ProducerManagementModel;
import de.uhh.l2g.model.ProducerModel;
import de.uhh.l2g.util.PaginationResultProducer;

/**
 * The Class AdminProducerManagementController.
 */
public class AdminProducerManagementController extends AbstractController {

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
	 * @param beanFactory the dao bean factory
	 */
	public void setDaoBeanFactory(XmlBeanFactory beanFactory) {
		this.daoBeanFactory = beanFactory;
	}

	/** The list view. */
	private String listView;

	/** The detail view. */
	private String detailView;

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractController#handleRenderRequestInternal(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	@Override
	protected ModelAndView handleRenderRequestInternal(RenderRequest request, RenderResponse response) throws Exception {

		// handle parameters
		int producerid = PortletRequestUtils.getIntParameter(request, "producerid", 0);
		int pageNumber = PortletRequestUtils.getIntParameter(request, "pagenumber", 1);
		int pageSize = PortletRequestUtils.getIntParameter(request, "pagesize", 10);
		String approved = PortletRequestUtils.getStringParameter(request, "approved", "approvedfalse");
		int facultyid = PortletRequestUtils.getIntParameter(request, "facultyid", 0);
		String messageToShow = PortletRequestUtils.getStringParameter(request, "messageToShow", "");
		String colorMsg = PortletRequestUtils.getStringParameter(request, "colorMsg", "");

		Map<String, Object> model = new HashMap<String, Object>();
		String finalView = "";

		if (producerid == 0) {
			
			// showing list view
			ProducerManagementModel myModel = new ProducerManagementModel();

			Map<String, String> allFaculties = new LinkedHashMap<String, String>();
			for (Facility faculty : ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getTopFacilities()) {
				allFaculties.put("" + faculty.getId(), faculty.getName());
			}
			myModel.setAllFaculties(allFaculties);

			myModel.addFilter("facultyid", "" + facultyid);
			myModel.addFilter("approved", approved);

			if ("approvedtrue".equals(approved)) {
				approved = "true";
			}
			if ("approvedfalse".equals(approved)) {
				approved = "false";
			}

			PaginationResultProducer result = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getWithLRInfo(approved, facultyid, pageNumber, pageSize);
			// get paginated results and store them in model
			myModel.setProducerLRList(result.getPaginationResult());

			// do pagination
			myModel.setPageSize(pageSize);
			myModel.setNumberResultPages(result.getNumberPages());
			myModel.setCurrentPageNumber(pageNumber);

			int rangeLast = Math.min((((pageNumber - 1) / 5)) * 5 + 5, myModel.getNumberResultPages());
			int rangeFirst = (((pageNumber - 1) / 5)) * 5 + 1;

			myModel.setPageRangeFirst(rangeFirst);
			myModel.setPageRangeLast(rangeLast);
			myModel.setHasPrev(rangeFirst > 1);
			myModel.setHasNext(rangeLast < myModel.getNumberResultPages());

			model.put("model", myModel);
			finalView = listView;
		} else { // showing detail view

			ProducerModel myModel = new ProducerModel();

			List<ProducerLRInfo> data = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getProducerLRInfoById(producerid);
			if (data != null && !data.isEmpty()) {
				ProducerLRInfo prod = data.iterator().next();

				myModel.setId(prod.getUserId());
				myModel.setScreenName(prod.getScreenName());
				myModel.setIdNum(prod.getIdNum());
				myModel.setEmailAddress(prod.getEmailAddress());
				myModel.setFirstName(prod.getFirstName());
				myModel.setLastName(prod.getLastName());
				myModel.setNumberProductions(prod.getNumberOfProductions());
				myModel.setCreateDate(prod.getCreateDate());
				myModel.setLastLoginDate(prod.getLastLoginDate());
				myModel.setApproved(prod.isApproved());

				myModel.addFilter("facultyid", "" + facultyid);
				myModel.addFilter("approved", approved);

				// do pagination
				myModel.setPageSize(pageSize);
				myModel.setCurrentPageNumber(pageNumber);

				List<Facility> faculties = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getById(prod.getFacilityId());
				if (faculties != null && !faculties.isEmpty()) {
					Facility faculty = faculties.iterator().next();
					myModel.setFaculty(faculty);
				}
				model.put("model", myModel);
				finalView = detailView;

			} else {
				finalView = listView;
				messageToShow = "Keine Veranstaltung mit dieser id!";
				colorMsg = "#ff0000";
			}
		}
		model.put("msg", messageToShow);
		model.put("colorMsg", colorMsg);
		return new ModelAndView(finalView, model);

	}

	/**
	 * Gets the list view.
	 *
	 * @return the list view
	 */
	public String getListView() {
		return listView;
	}

	/**
	 * Sets the list view.
	 *
	 * @param listView the list view
	 */
	public void setListView(String listView) {
		this.listView = listView;
	}

	/**
	 * Sets the detail view.
	 *
	 * @param detailView the detail view
	 */
	public void setDetailView(String detailView) {
		this.detailView = detailView;
	}

	/**
	 * Gets the detail view.
	 *
	 * @return the detail view
	 */
	public String getDetailView() {
		return detailView;
	}

}
