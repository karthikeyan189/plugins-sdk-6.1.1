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

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.PortletRequestUtils;
import org.springframework.web.portlet.mvc.AbstractController;

import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.dao.FacilityDao;
import de.uhh.l2g.dao.LectureseriesDao;
import de.uhh.l2g.model.AdminLectureseriesModel;

/**
 * The Class ProducerLectureseriesManagementController.
 */
public class ProducerLectureseriesManagementController extends AbstractController {

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractController#handleRenderRequestInternal(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	@Override
	protected ModelAndView handleRenderRequestInternal(RenderRequest request, RenderResponse response) throws Exception {

		// handle parameters
		int lectureseriesId = PortletRequestUtils.getIntParameter(request, "lectureseriesId", 0);
		String messageToShow = PortletRequestUtils.getStringParameter(request, "messageToShow", "");
		String colorMsg = PortletRequestUtils.getStringParameter(request, "colorMsg", "");

		Map<String, Serializable> model = new HashMap<String, Serializable>();
		String finalView = "";

		if (lectureseriesId == 0) { // showing list view
			model.put("msg", messageToShow);
			finalView = "producerLectureseriesManagement";
		} else { // showing detail view
			AdminLectureseriesModel myModel = new AdminLectureseriesModel();

			List<Lectureseries> data = ((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getById(lectureseriesId);
			if (data != null && !data.isEmpty()) {
				Lectureseries lectureseries = data.get(0);
				myModel.setId(lectureseriesId);
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
				for (Facility faculty : ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getFromLectureseriesId(lectureseriesId)) {
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

				model.put("model", myModel);
				finalView = "lectureseriesShow";
			} else {
				finalView = "producerLectureseriesManagement";
				messageToShow = "Keine Veranstaltung mit dieser id!";
				colorMsg = "#ff0000";
			}
			model.put("msg", messageToShow);
			model.put("colorMsg", colorMsg);

		}

		return new ModelAndView(finalView, model);
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
	 * @param beanFactory the dao bean factory
	 */
	public void setDaoBeanFactory(XmlBeanFactory beanFactory) {
		this.daoBeanFactory = beanFactory;
	}	
}
