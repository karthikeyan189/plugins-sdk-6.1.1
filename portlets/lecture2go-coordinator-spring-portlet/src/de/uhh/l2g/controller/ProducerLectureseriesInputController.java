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

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.bind.PortletRequestUtils;
import org.springframework.web.portlet.mvc.SimpleFormController;

import de.uhh.l2g.beans.Coordinator;
import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.ProducerLRInfo;
import de.uhh.l2g.dao.CoordinatorDao;
import de.uhh.l2g.dao.FacilityDao;
import de.uhh.l2g.dao.LectureseriesDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.model.AdminLectureseriesInputModel;
import de.uhh.l2g.util.Htaccess;
import de.uhh.l2g.util.HtmlManager;
import de.uhh.l2g.util.L2goPropsUtil;
import de.uhh.l2g.util.EmailManager;
;


/**
 * The Class ProducerLectureseriesInputController.
 */

public class ProducerLectureseriesInputController extends SimpleFormController implements InitializingBean {

	/** The utility bean factory. */
	private XmlBeanFactory utilityBeanFactory;
	
	/**
	 * Gets the utility bean factory.
	 *
	 * @return the utility bean factory
	 */
	public XmlBeanFactory getUtilityBeanFactory() {
		return utilityBeanFactory;
	}

	/**
	 * Sets the utility bean factory.
	 *
	 * @param utilityBeanFactory the new utility bean factory
	 */
	public void setUtilityBeanFactory(XmlBeanFactory utilityBeanFactory) {
		this.utilityBeanFactory = utilityBeanFactory;
	}
	
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
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.portlet.mvc.SimpleFormController#onSubmitAction
	 * (javax.portlet.ActionRequest, javax.portlet.ActionResponse,
	 * java.lang.Object, org.springframework.validation.BindException)
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
		AdminLectureseriesInputModel model = (AdminLectureseriesInputModel) command;
		ResourceBundle res = ResourceBundle.getBundle("de.uhh.l2g.resources.Resource", request.getLocale());

		boolean koordExists = false;
		Coordinator koord;
		
		// set current user as producer

		String userId = request.getRemoteUser();
		int uId = new Integer(userId);

		Producer prod = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByUserId(uId).iterator().next();
		// Coordinator for this Producer
		try{
			koord = ((CoordinatorDao)getDaoBeanFactory().getBean("coordinatorDao")).getByFacilityId(prod.getFacilityId()).iterator().next();
			koordExists = true;
		}catch(NoSuchElementException nsee){
			koord = null;
			koordExists = false;
		}
		
		long count = 0;
		if (model.getId() > 0) {
			if (((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).hasLectureseriesProducer(model.getId(), Integer.parseInt(userId))) {
				count = doMySubmitAction(model, userId);
			} else {}
		} else {
			count = doMySubmitAction(model, userId);
		}

		if (count > 0) {

			// Subject
			String SUBJECT = res.getString("new-request-for-approval");
			String BODY = res.getString("you-have-a-new-request-for-approval") + " \n" + res.getString("lecture") + ": " + model.getLectureseriesNumber() + ": " + model.getLectureseriesName();
			
			//if coordinator exists
			if(koordExists){
				String KOORDEMAILADDRESS = koord.getEmailAddress();				
				String BODY2 = res.getString("coordinator") + " " + koord.getFirstName() + " " + koord.getLastName() + " " + res.getString("got-a-new-request-for-approval") + "  \n" + res.getString("lecture") + ": " + model.getLectureseriesNumber() + ": " + model.getLectureseriesName();
				// Send mail to Coordinator
				((EmailManager)getUtilityBeanFactory().getBean("emailManager")).sendEmail(L2goPropsUtil.get("lecture2go.response.email.address"), KOORDEMAILADDRESS, HtmlManager.ISO88591toUTF8(SUBJECT), HtmlManager.ISO88591toUTF8(BODY));
				// Send mail to L2Go
				((EmailManager)getUtilityBeanFactory().getBean("emailManager")).sendEmail(L2goPropsUtil.get("lecture2go.response.email.address"), L2goPropsUtil.get("lecture2go.response.email.address"), HtmlManager.ISO88591toUTF8(SUBJECT), HtmlManager.ISO88591toUTF8(BODY2));
			}

			// Send mail to Producer
			ProducerLRInfo producerLRInfo = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getProducerLRInfoById(prod.getId()).iterator().next();
			String PRODEMAILADDRESS = producerLRInfo.getEmailAddress();
			String BODY3 = res.getString("your-request-was-sent") +"  \n" + res.getString("lecture") +" :"+  model.getLectureseriesNumber() + ": " + model.getLectureseriesName();
			((EmailManager)getUtilityBeanFactory().getBean("emailManager")).sendEmail(L2goPropsUtil.get("lecture2go.response.email.address"), PRODEMAILADDRESS, HtmlManager.ISO88591toUTF8(SUBJECT), HtmlManager.ISO88591toUTF8(BODY3));

		} else {}
		response.setRenderParameter("action", "view");
		response.setRenderParameter("lectureseriesId", "" + count);
	}

	/**
	 * Do my submit action.
	 *
	 * @param model the model
	 * @param producerId the producer id
	 * @return the long
	 * @throws Exception the exception
	 */
	private long doMySubmitAction(AdminLectureseriesInputModel model, String producerId) throws Exception {

		long result = 0;
		int pid = new Integer(producerId);

		Producer p = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByUserId(pid).iterator().next();
		// Coordinator for this Producer
		Coordinator k = ((CoordinatorDao)getDaoBeanFactory().getBean("coordinatorDao")).getByFacilityId(p.getFacilityId()).iterator().next();
		String coordinatorId = k.getId() + "";

		String[] fKeys = null;
		String[] pKeys;

		if (!producerId.equals(coordinatorId)) pKeys = new String[] { producerId, coordinatorId };
		else pKeys = new String[] { producerId };

		Map<String, String> faculties = new HashMap<String, String>();
		String lang = "";

		// facility selected from list
		if (model.getFaculties() != null && !model.getFaculties().isEmpty()) {
			// fKeys = model.getFaculties().keySet().toArray(new String[0]);
			faculties = model.getFaculties();
		}

		// user input for facility
		if (model.getFacilityName() != null && !model.getFacilityName().trim().equals("")) {
			// add new faculty to database
			// int pid = new Integer(producerId);
			// Producer p =
			// this.getProducerDao().getByUserId(pid).iterator().next();
			// producer facility
			int eId = p.getFacilityId();
			Facility ein = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getById(eId).iterator().next();
			// get tree-type from facility
			String treetyp = ein.getTyp();
			// safe the new one "facility"
			int id = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).create(eId, model.getFacilityName(), treetyp, null, 2, null);
			// add to fKeys array using maps
			Facility lastEin = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getById(id).iterator().next();
			// values for map
			String key = lastEin.getId() + "";
			String value = lastEin.getName();
			// add to faculties hash map
			faculties.put(key, value);
		}

		// update the fKeys - list for model
		fKeys = faculties.keySet().toArray(new String[0]);

		if (model.getLanguages() != null && !model.getLanguages().isEmpty()) {
			lang = Arrays.toString(model.getLanguages().keySet().toArray(new String[0]));
			lang = lang.substring(1, lang.length() - 1);
		}
		if (model.getId() > 0) { // editing existing lectureseries
			result = ((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).edit(model.getId(), model.getLectureseriesNumber(), model.getEventType(), model.getEventCategory(), model.getLectureseriesName(), model.getShortDescription(), model.getLongDescription(), model.getSemesterName(), lang, model.getFacultyName(), model.getInstructorNames(), model.getPassword(), false, fKeys, pKeys);
		} else {
			result = ((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).create(model.getLectureseriesNumber(), model.getEventType(), model.getEventCategory(), model.getLectureseriesName(), model.getShortDescription(), model.getLongDescription(), model.getSemesterName(), lang, model.getFacultyName(), model.getInstructorNames(), model.getPassword(), false, fKeys, pKeys);
		}

		if (!(model.getPassword().equals("") || model.getPassword() == null)) {
			try{
				((Htaccess)getUtilityBeanFactory().getBean("htaccess")).writePW(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getAllwithPw());
			}catch(NullPointerException e){}
		}

		return result;
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

		AdminLectureseriesInputModel myModel = new AdminLectureseriesInputModel();
		String userId = request.getRemoteUser();

		int id = PortletRequestUtils.getIntParameter(request, "lectureseriesId", 0);

		if (id > 0) {
			List<Lectureseries> data = ((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getById(id);
			if (data != null && !data.isEmpty()) {
				Lectureseries lectureseries = data.get(0);

				myModel.setId(id);
				myModel.setLectureseriesNumber(lectureseries.getNumber());
				myModel.setLectureseriesName(lectureseries.getName());
				myModel.setEventType(lectureseries.getEventType());
				myModel.setEventCategory(lectureseries.getEventCategory());
				myModel.setShortDescription(lectureseries.getShortDesc());
				myModel.setLongDescription(lectureseries.getLongDesc());
				myModel.setSemesterName(lectureseries.getSemesterName());
				myModel.setInstructorNames(lectureseries.getInstructorsString());
				myModel.setLectureseriesLanguage(lectureseries.getLanguage());
				myModel.setFacultyName(lectureseries.getFaculty_name());
				myModel.setPassword(lectureseries.getPassword());

				Map<String, String> faculties = new HashMap<String, String>();
				for (Facility faculty : ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getFromLectureseriesId(id)) {
					faculties.put("" + faculty.getId(), faculty.getName());
				}
				myModel.setFaculties(faculties);

				Map<String, String> lang = new HashMap<String, String>();
				if (lectureseries.getLanguage() != null && !"".equals(lectureseries.getLanguage())) {
					for (String key : lectureseries.getLanguage().trim().split(", ")) {
						lang.put(key, key);
					}
				}
				myModel.setLanguages(lang);
			}
		}

		int uid = Integer.parseInt(userId);

		Map<String, String> allFaculties = new LinkedHashMap<String, String>();
		List<ProducerLRInfo> prodList = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getProducerLRInfoById(uid);
		if (prodList != null && !prodList.isEmpty()) {
			ProducerLRInfo prod = prodList.iterator().next();
			for (Facility faculty : ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByParentId(prod.getFacilityId())) {
				allFaculties.put("" + faculty.getId(), faculty.getName());
			}
			myModel.setAllFaculties(allFaculties);
		}

		// check for coordinator access
		List<Coordinator> koo = ((CoordinatorDao)getDaoBeanFactory().getBean("coordinatorDao")).getById(uid);

		if (koo.size() != 0) myModel.setCoordinator(true);
		else myModel.setCoordinator(false);

		myModel.setAllSemesters(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getAllAvaliableSemesters());
		myModel.setAllEventCategories(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getEventCategories());

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

}
