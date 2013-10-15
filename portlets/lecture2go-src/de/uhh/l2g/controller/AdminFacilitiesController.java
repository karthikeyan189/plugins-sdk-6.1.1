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

import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.SimpleFormController;

import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.Host;
import de.uhh.l2g.dao.FacilityDao;
import de.uhh.l2g.dao.HostDao;
import de.uhh.l2g.model.AdminFacilitiesModel;


/**
 * The Class AdminFacilitiesController.
 */
public class AdminFacilitiesController extends SimpleFormController{
	
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

	
	/**
	 * Gets the lectureseries root id.
	 *
	 * @return the lectureseries root id
	 */
	public Integer getLectureseriesRootId() {
		return lectureseriesRootId;
	}

	/**
	 * Sets the lectureseries root id.
	 *
	 * @param lectureseriesRootId the lectureseries root id
	 */
	public void setLectureseriesRootId(Integer lectureseriesRootId) {
		this.lectureseriesRootId = lectureseriesRootId;
	}

	/** The lectureseries root id. */
	private Integer lectureseriesRootId;
	
	/** The tree. */
	private String tree;

	/**
	 * Gets the tree.
	 *
	 * @return the tree
	 */
	public String getTree() {
		return tree;
	}

	/**
	 * Sets the tree.
	 *
	 * @param tree the tree
	 */
	public void setTree(String tree) {
		this.tree = tree;
	}

	/*
	 * (non-Javadoc)
	 * @seeorg.springframework.web.portlet.mvc.AbstractFormController#
	 * processFormSubmission(javax.portlet.ActionRequest,
	 * javax.portlet.ActionResponse, java.lang.Object,
	 * org.springframework.validation.BindException)
	 */
	@Override
	protected void processFormSubmission(ActionRequest ar, ActionResponse arg1, Object command, BindException errors) throws Exception {
		String action = ar.getParameter("action");
		String hId = ar.getParameter("hostId");
		int id = new Integer(ar.getParameter("id"));
		AdminFacilitiesModel model = (AdminFacilitiesModel) command;
		
		// object
		Facility ei = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getById(id).iterator().next();

		// edit
		if (action.equals("rename") && !errors.hasErrors()) {
			int parentId = ei.getParentId();
			String newname =ar.getParameter("name");
			String typ = ei.getTyp();
			String www = ei.getWww();
			int level = ei.getLevel();
			int sort = ei.getSort();
			// update
			((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).updateById(parentId, newname, typ, www, level, sort, id);
		}

		// add
		if (action.equals("add") && !errors.hasErrors()) {
			int parentId = ei.getId();
			String name = ar.getParameter("name");
			String typ = ei.getTyp();
			String www = null;
			int level = (ei.getLevel() + 1);
			// sort number
			int sort = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByParentIdAndTyp(parentId,getTree()).size();
			sort++;
			int eId = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).createAndReturnGeneratedId(parentId, name, typ, www, level, sort);
			// update facility_host
			if(((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).addFacilityIdToHost(eId, hId)==1)model.setName("");
		}

		// hoch
		if (action.equals("hoch")) {
			int parentId = ei.getParentId();
			// resort
			int sort = ei.getSort();
			int up = sort - 1;

			// upper facility
			Facility upEi = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByParentIdAndSort(parentId, up).iterator().next();
			// lower facility
			Facility downEi = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByParentIdAndSort(parentId, sort).iterator().next();

			// upper facility down
			((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).updateById(upEi.getParentId(), upEi.getName(), upEi.getTyp(), upEi.getWww(), upEi.getLevel(), sort, upEi.getId());
			// down facility up
			((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).updateById(downEi.getParentId(), downEi.getName(), downEi.getTyp(), downEi.getWww(), downEi.getLevel(), up, downEi.getId());
		}

		// runter
		if (action.equals("runter")) {
			int parentId = ei.getParentId();
			// resort
			int sort = ei.getSort();
			int down = sort + 1;

			// upper facility
			Facility upEi = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByParentIdAndSort(parentId, sort).iterator().next();

			// down facility
			Facility downEi = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByParentIdAndSort(parentId, down).iterator().next();

			// upper facility down
			((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).updateById(upEi.getParentId(), upEi.getName(), upEi.getTyp(), upEi.getWww(), upEi.getLevel(), down, upEi.getId());
			// down facility up
			((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).updateById(downEi.getParentId(), downEi.getName(), downEi.getTyp(), downEi.getWww(), downEi.getLevel(), sort, downEi.getId());
		}

		// delete
		if (action.equals("delete")) {
			((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).deleteById(id);
			List<Facility> facilityList = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByParentIdAndTyp(ei.getParentId(), getTree());
			((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).autoSort(facilityList);
		}
		model.setAction(action);
	}

	/*
	 * (non-Javadoc)
	 * @seeorg.springframework.web.portlet.mvc.AbstractFormController#
	 * renderFormSubmission(javax.portlet.RenderRequest,
	 * javax.portlet.RenderResponse, java.lang.Object,
	 * org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView renderFormSubmission(RenderRequest req, RenderResponse arg1, Object command, BindException errors) throws Exception {
		ModelAndView mv = new ModelAndView();
		AdminFacilitiesModel model = (AdminFacilitiesModel) command;
		Map<?,?> modelMap = errors.getModel();
		
		// get parent-facility for user
		Facility pe = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getById(getLectureseriesRootId()).iterator().next();
		// get unterfacilities from parent facility
		List<Facility> el = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByParentIdAndTyp(pe.getId(), getTree());
		// prepare model properties for front end
		model.setFacilitiesList(el);
		// set facility
		model.setFacility(pe);
		// set facility-length in model
		model.setListFacilitiesSize(el.size());
		//name
		if (model.getAction().equals("rename")) model.setName("");
		// set model
		mv.addAllObjects(modelMap);
		mv.addObject("model", model);
		// set jsp
		mv.setViewName("adminFacilities");
		//host objects
		List<Host> hostList = ((HostDao)getDaoBeanFactory().getBean("hostDao")).getAll();
		model.setHostList(hostList);

		return mv;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.portlet.mvc.AbstractFormController#showForm(javax
	 * .portlet.RenderRequest, javax.portlet.RenderResponse,
	 * org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView showForm(RenderRequest req, RenderResponse arg1, BindException errors) throws Exception {
		ModelAndView mv = new ModelAndView();
		AdminFacilitiesModel model = new AdminFacilitiesModel();
		Map<?,?> modelMap = errors.getModel();
		
		// get parent-facility for user
		Facility pe = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getById(getLectureseriesRootId()).iterator().next();
		// get unterfacilities from parent facility
		List<Facility> el = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByParentIdAndTyp(pe.getId(), getTree());
		// prepare model properties for front end
		model.setFacilitiesList(el);
		// set facility
		model.setFacility(pe);
		// set facility-length in model
		model.setListFacilitiesSize(el.size());
		
		//host objects
		List<Host> hostList = ((HostDao)getDaoBeanFactory().getBean("hostDao")).getAll();
		model.setHostList(hostList);
		
		// set model
		mv.addAllObjects(modelMap);
		mv.addObject("model", model);
		// set jsp
		mv.setViewName("adminFacilities");

		return mv;
	}

}
