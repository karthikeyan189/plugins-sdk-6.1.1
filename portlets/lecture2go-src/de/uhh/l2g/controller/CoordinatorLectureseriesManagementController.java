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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.PortletRequestUtils;
import org.springframework.web.portlet.mvc.AbstractController;

import de.uhh.l2g.beans.Coordinator;
import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.ProducerLRInfo;
import de.uhh.l2g.dao.CoordinatorDao;
import de.uhh.l2g.dao.FacilityDao;
import de.uhh.l2g.dao.LectureseriesDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.model.AdminLectureseriesManagementModel;
import de.uhh.l2g.model.AdminLectureseriesModel;
import de.uhh.l2g.util.PaginationResultLectureseries;


/**
 * The Class CoordinatorLectureseriesManagementController.
 */
public class CoordinatorLectureseriesManagementController extends AbstractController {

	/** The list view. */
	private String listView;

	/** The detail view. */
	private String detailView;

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

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractController#handleRenderRequestInternal(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	@Override
	protected ModelAndView handleRenderRequestInternal(RenderRequest request, RenderResponse response) throws Exception {

		// handle parameters
		int lectureseriesId = PortletRequestUtils.getIntParameter(request, "lectureseriesId", 0);
		int pageNumber = PortletRequestUtils.getIntParameter(request, "pagenumber", 1);
		int pageSize = PortletRequestUtils.getIntParameter(request, "pagesize", 10);
		int facultyId = PortletRequestUtils.getIntParameter(request, "facultyid", 0);
		int producerId = PortletRequestUtils.getIntParameter(request, "producerid", 0);
		String semester = PortletRequestUtils.getStringParameter(request, "semester", "");
		String approved = PortletRequestUtils.getStringParameter(request, "approved", "approvedfalse");
		int userId = new Integer(request.getRemoteUser());
		Coordinator koord = ((CoordinatorDao)getDaoBeanFactory().getBean("coordinatorDao")).getById(userId).iterator().next();
		if (facultyId == 0) facultyId = koord.getFacilityId();

		Map<String, Serializable> model = new HashMap<String, Serializable>();
		String finalView = "";

		if (lectureseriesId == 0) {

			// showing list view
			AdminLectureseriesManagementModel myModel = new AdminLectureseriesManagementModel();

			Map<String, String> allFaculties = new LinkedHashMap<String, String>();
			List<Facility> einListAll = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByParentIdSortedAsTree(koord.getFacilityId());

			for (Facility faculty : einListAll) {
				String id = "" + faculty.getId();
				String name = _indentFromPath(faculty.getPath(), "/") + faculty.getName();
				allFaculties.put(id, name);
			}
			myModel.setAllFaculties(allFaculties);

			Map<String, String> allProducers = new LinkedHashMap<String, String>();
			for (ProducerLRInfo p : ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByFacilityIdSortedByLastName(koord.getFacilityId())) {
				allProducers.put("" + p.getId(), p.getLastName() + ", " + p.getFirstName());
			}
			myModel.setAllProducers(allProducers);

			myModel.setAllSemesters(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getAllAvaliableSemesters());

			myModel.addFilter("facultyid", "" + facultyId);
			myModel.addFilter("producerid", "" + producerId);
			myModel.addFilter("semester", semester);
			myModel.addFilter("approved", approved);

			if ("approvedtrue".equals(approved)) {
				approved = "true";
			}
			if ("approvedfalse".equals(approved)) {
				approved = "false";
			}

			// get paginated results and store them in model
			PaginationResultLectureseries lectureseriesList = ((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getFilteredBySemesterFacultyProducer(approved, semester, facultyId, producerId, pageNumber, pageSize);
			myModel.setLectureseriesList(lectureseriesList.getPaginationResult());

			// do pagination
			myModel.setPageSize(pageSize);
			myModel.setNumberResultPages(lectureseriesList.getNumberPages());
			myModel.setCurrentPageNumber(pageNumber);

			int rangeFirst = (((pageNumber - 1) / 5)) * 5 + 1;
			int rangeLast = Math.min((((pageNumber - 1) / 5)) * 5 + 5, myModel.getNumberResultPages());
			myModel.setPageRangeFirst(rangeFirst);
			myModel.setPageRangeLast(rangeLast);
			myModel.setHasPrev(rangeFirst > 1);
			myModel.setHasNext(rangeLast < myModel.getNumberResultPages());

			model.put("model", myModel);
			finalView = listView;
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
				myModel.setApproved(lectureseries.isApproved());
				Map<String, String> faculties = new HashMap<String, String>();
				for (Facility faculty : ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getFromLectureseriesId(lectureseriesId)) {
					faculties.put("" + faculty.getId(), faculty.getName());
				}
				myModel.setFaculties(faculties);

				Map<String, String> producers = new HashMap<String, String>();
				for (Producer producer : ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getFromLectureseriesId(lectureseriesId)) {
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

				myModel.addFilter("facultyid", "" + facultyId);
				myModel.addFilter("producerid", "" + producerId);
				myModel.addFilter("semester", semester);
				myModel.addFilter("approved", approved);

				// do pagination
				myModel.setPageSize(pageSize);
				myModel.setCurrentPageNumber(pageNumber);

				model.put("model", myModel);
				finalView = detailView;

			} else {
				finalView = listView;
			}
		}

		return new ModelAndView(finalView, model);
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
