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

import java.util.NoSuchElementException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.AbstractFormController;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

import de.uhh.l2g.action.GuestLectureseriesDetail;
import de.uhh.l2g.action.GuestLectureseriesList;
import de.uhh.l2g.dao.LectureseriesDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.GuestModel;

/**
 * The Class GuestLectureseriesController.
 */
public class GuestLectureseriesController extends AbstractFormController {

	/** The command bean factory. */
	private XmlBeanFactory commandBeanFactory;
	
	/**
	 * Gets the command bean factory.
	 *
	 * @return the command bean factory
	 */
	public XmlBeanFactory getCommandBeanFactory() {
		return commandBeanFactory;
	}
	
	/**
	 * Sets the command bean factory.
	 *
	 * @param commandBeanFactory the command bean factory
	 */
	public void setCommandBeanFactory(XmlBeanFactory commandBeanFactory) {
		this.commandBeanFactory = commandBeanFactory;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#processFormSubmission(javax.portlet.ActionRequest, javax.portlet.ActionResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void processFormSubmission(ActionRequest request, ActionResponse response, Object command, BindException errors) {
		GuestModel model = (GuestModel) command;
		//initialize tree
		model.setTree("tree1");
		
		//liferay user
		User user = null;
		try {
			user = (User) PortalUtil.getUser(request);
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		//set user for model
		model.setLiferayUser(user);
		
		// check user agent
		HttpServletRequest hsr = com.liferay.portal.util.PortalUtil.getHttpServletRequest(request);
		String userAgent = hsr.getHeader("User-Agent");
		model.setUserAgent(userAgent);

		// prepare list view
		((GuestLectureseriesList) getCommandBeanFactory().getBean("guestLectureseriesList")).execute(request,  model);

		model.setFacultyId(new Integer(request.getParameter("facultyId")));

		try {
			Integer lectureseriesId = new Integer(request.getParameter("lectureseriesId"));
			model.setLectureseriesId(lectureseriesId);
			// prepare detail view
			((GuestLectureseriesDetail) getCommandBeanFactory().getBean("guestLectureseriesDetail")).execute(request,  model);
		} catch (NumberFormatException nfe) {
			model.setLectureseriesId(0);
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#renderFormSubmission(javax.portlet.RenderRequest, javax.portlet.RenderResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView renderFormSubmission(RenderRequest request, RenderResponse response, Object command, BindException errors) {
		ModelAndView mv = new ModelAndView();
		GuestModel model = (GuestModel) command;
		
		// check user agent
		HttpServletRequest hsr = com.liferay.portal.util.PortalUtil.getHttpServletRequest(request);
		String userAgent = hsr.getHeader("User-Agent");
		model.setUserAgent(userAgent);

		// check mobile device
		if (userAgent.contains("Mobile") || userAgent.contains("Android") || userAgent.contains("Skyfire")) model.setMobileDevice(true);
		else model.setMobileDevice(false);

		// if faculty id different than 0, do not execute doRenderModel
		if (model.getFacultyId() == 0) {
			// prepare list view
			((GuestLectureseriesList) getCommandBeanFactory().getBean("guestLectureseriesList")).execute(request,  model);
		}

		mv.addObject("model", model);
		if (model.getLectureseriesId() != 0) mv.setView("guestLectureseriesShowDetails");
		else mv.setView("guestLectureseriesList");

		return mv;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#showForm(javax.portlet.RenderRequest, javax.portlet.RenderResponse, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView showForm(RenderRequest request, RenderResponse response, BindException errors) {
		ModelAndView mv = new ModelAndView();
		GuestModel model = new GuestModel();
		//initialize tree
		model.setTree("tree1");
		
		//liferay user
		User user = null;
		try {
			user = PortalUtil.getUser(request);
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		//set user for model
		model.setLiferayUser(user);
		
		// check user agent
		HttpServletRequest hsr = com.liferay.portal.util.PortalUtil.getHttpServletRequest(request);
		String userAgent = hsr.getHeader("User-Agent");
		model.setUserAgent(userAgent);

		// check mobile device
		if (userAgent.contains("Mobile") || userAgent.contains("Android") || userAgent.contains("Skyfire")) model.setMobileDevice(true);
		else model.setMobileDevice(false);

		// -- Video Object --
		try {
			model.setVideo(((VideoDao) ((GuestLectureseriesDetail) getCommandBeanFactory().getBean("guestLectureseriesDetail")).getDaoBeanFactory().getBean("videoDao")).getById(new Integer(request.getParameter("videoId"))).iterator().next());
		} catch (NoSuchElementException nse) {
			model.setVideo(null);
		} catch (NumberFormatException nfe) {
			model.setVideo(null);
		}
		// -- Video Object --

		// -- lectureseries --
		try {
			model.setLectureseries(((LectureseriesDao) ((GuestLectureseriesDetail) getCommandBeanFactory().getBean("guestLectureseriesDetail")).getDaoBeanFactory().getBean("lectureseriesDao")).getById(new Integer(request.getParameter("lectureseriesId"))).iterator().next());
		} catch (NumberFormatException nfe) {
			model.setLectureseries(null);
		}
		// -- lectureseries --

		// -- model and view --
		// lectureseries or video selected
		if (model.getLectureseries() != null || model.getVideo() != null) {
			if (model.getVideo().isOpenaccess()) {// take care for open access
				// prepare detail view
				((GuestLectureseriesDetail) getCommandBeanFactory().getBean("guestLectureseriesDetail")).execute(request,  model);
				mv.setView("guestLectureseriesShowDetails");
			} else {
				mv.setView("error");
			}
		} else {
			// prepare list view
			((GuestLectureseriesList) getCommandBeanFactory().getBean("guestLectureseriesList")).execute(request,  model);
			mv.setView("guestLectureseriesList");
		}
		// -- model and view --

		mv.addObject("model", model);
		return mv;
	}
}
