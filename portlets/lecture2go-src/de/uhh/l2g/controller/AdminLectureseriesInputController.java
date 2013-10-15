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
import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.ProducerLRInfo;
import de.uhh.l2g.dao.FacilityDao;
import de.uhh.l2g.dao.LectureseriesDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.model.AdminLectureseriesInputModel;
import de.uhh.l2g.util.Htaccess;

/**
 * The Class AdminLectureseriesInputController.
 */
public class AdminLectureseriesInputController extends SimpleFormController implements InitializingBean {
	

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
		AdminLectureseriesInputModel model = (AdminLectureseriesInputModel) command;

		long count = doMySubmitAction(model);

		response.setRenderParameter("action", "view");
		response.setRenderParameter("lectureseriesId", "" + count);
		response.setRenderParameter("pagesize", "" + model.getPageSize());
		response.setRenderParameter("pagenumber", "" + model.getCurrentPageNumber());
		response.setRenderParameter("facultyid", model.getFilters().get("facultyid"));
		response.setRenderParameter("producerid", model.getFilters().get("producerid"));
		response.setRenderParameter("semester", model.getFilters().get("semester"));
		response.setRenderParameter("approved", model.getFilters().get("approved"));
	}

	/**
	 * Do my submit action.
	 *
	 * @param model the model
	 * @return the long
	 * @throws Exception the exception
	 */
	private long doMySubmitAction(AdminLectureseriesInputModel model) throws Exception {
		long result = 0;
		String[] fKeys = null;
		String[] pKeys = null;

		String lang = "";

		if (model.getFaculties() != null && !model.getFaculties().isEmpty()) {
			fKeys = model.getFaculties().keySet().toArray(new String[0]);
		}

		if (model.getProducers() != null && !model.getProducers().isEmpty()) {
			pKeys = model.getProducers().keySet().toArray(new String[0]);
		}

		if (model.getLanguages() != null && !model.getLanguages().isEmpty()) {
			lang = Arrays.toString(model.getLanguages().keySet().toArray(new String[0]));
			lang = lang.substring(1, lang.length() - 1);
		}

		String shortDesc = !"".equals(model.getShortDescription()) ? model.getShortDescription() : model.getLectureseriesName().substring(0, model.getLectureseriesName().length() > 63 ? 60 : model.getLectureseriesName().length()) + (model.getLectureseriesName().length() > 63 ? "..." : "");

		if (model.getId() > 0) result = ((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).edit(model.getId(), model.getLectureseriesNumber(), model.getEventType(), model.getEventCategory(), model.getLectureseriesName(), shortDesc, model.getLongDescription(), model.getSemesterName(), lang, model.getFacultyName(), model.getInstructorNames(), model.getPassword(), true, fKeys, pKeys);
		else result = ((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).create(model.getLectureseriesNumber(), model.getEventType(), model.getEventCategory(), model.getLectureseriesName(), shortDesc, model.getLongDescription(), model.getSemesterName(), lang, model.getFacultyName(), model.getInstructorNames(), model.getPassword(), true, fKeys, pKeys);
		

		if (!(model.getPassword().equals("") || model.getPassword() == null)) {
			((Htaccess)getUtilityBeanFactory().getBean("htaccess")).writePW(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getAllwithPw());
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#formBackingObject(javax.portlet.PortletRequest)
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

		int id = PortletRequestUtils.getIntParameter(request, "lectureseriesId", 0);
		int pageNumber = PortletRequestUtils.getIntParameter(request, "pagenumber", 1);
		int pageSize = PortletRequestUtils.getIntParameter(request, "pagesize", 10);
		int facultyId = PortletRequestUtils.getIntParameter(request, "facultyid", 0);
		int producerId = PortletRequestUtils.getIntParameter(request, "producerid", 0);
		String semester = PortletRequestUtils.getStringParameter(request, "semester", "");
		String approved = PortletRequestUtils.getStringParameter(request, "approved", "");

		AdminLectureseriesInputModel myModel = new AdminLectureseriesInputModel();

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

				Map<String, String> producers = new HashMap<String, String>();
				for (Producer producer : ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getFromLectureseriesId(id)) {
					ProducerLRInfo plri = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getProducerLRInfoById(producer.getId()).iterator().next();
					producers.put("" + producer.getId(),  plri.getLastName() + ", " + plri.getFirstName());	
				}
				myModel.setProducers(producers);
				
				Map<String, String> lang = new HashMap<String, String>();
				if (lectureseries.getLanguage() != null && !"".equals(lectureseries.getLanguage())) {
					for (String key : lectureseries.getLanguage().trim().split(", ")) {
						lang.put(key, key);
					}
				}
				myModel.setLanguages(lang);
				
			}
		}

		Map<String, String> allFaculties = new LinkedHashMap<String, String>();
		for (Facility faculty : ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getAllSortedAsTree()) {
			allFaculties.put("" + faculty.getId(), _indentFromPath(faculty.getPath(), "/") + faculty.getName());
		}
		myModel.setAllFaculties(allFaculties);

		Map<String, String> allProducers = new LinkedHashMap<String, String>();
		for (ProducerLRInfo p : ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getAllSortedByLastName()) {
			allProducers.put("" + p.getId(), p.getLastName() + ", " + p.getFirstName());
		}
		
		myModel.setAllProducers(allProducers);

		myModel.setAllSemesters(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getAllAvaliableSemesters());
		myModel.setAllEventCategories(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getEventCategories());

		myModel.addFilter("facultyid", "" + facultyId);
		myModel.addFilter("producerid", "" + producerId);
		myModel.addFilter("semester", semester);
		myModel.addFilter("approved", approved);

		// do pagination
		myModel.setPageSize(pageSize);
		myModel.setCurrentPageNumber(pageNumber);

		return myModel;

	}

	/**
	 * _indent from path.
	 *
	 * @param path the path
	 * @param sep the sep
	 * @return the string
	 */
	private String _indentFromPath(String path, String sep) {
		String s = "";
		for (int i = 1; i <= path.split(sep).length - 1; i++) {
			s += "--";
		}
		return s;
	}
	
	// //////////// GETTERS AND SETTERS ////////////////

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
