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

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.AbstractFormController;

import de.uhh.l2g.action.GuestVideoList;
import de.uhh.l2g.model.GuestModel;

/**
 * The Class GuestVideoController.
 */
public class GuestVideoController extends AbstractFormController {

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
	 * @param commandBeanFactory the new command bean factory
	 */
	public void setCommandBeanFactory(XmlBeanFactory commandBeanFactory) {
		this.commandBeanFactory = commandBeanFactory;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#processFormSubmission(javax.portlet.ActionRequest, javax.portlet.ActionResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	/**
	 * Process form submission.
	 *
	 * @param request the request
	 * @param response the response
	 * @param command the command
	 * @param errors the errors
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void processFormSubmission(ActionRequest request, ActionResponse response, Object command, BindException errors) throws IOException {
		GuestModel model = (GuestModel) command;
		HttpServletRequest hsr = com.liferay.portal.util.PortalUtil.getHttpServletRequest(request);
		String userAgent = hsr.getHeader("User-Agent");
		model.setUserAgent(userAgent);

		//set tree
		model.setTree("tree1");
		
		((GuestVideoList) getCommandBeanFactory().getBean("guestVideoList")).execute(request,  model);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#renderFormSubmission(javax.portlet.RenderRequest, javax.portlet.RenderResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	/**
	 * Render form submission.
	 *
	 * @param request the request
	 * @param response the response
	 * @param command the command
	 * @param errors the errors
	 * @return the model and view
	 */
	@Override
	protected ModelAndView renderFormSubmission(RenderRequest request, RenderResponse response, Object command, BindException errors){
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
		if (model.getFacultyId() == 0){
			((GuestVideoList) getCommandBeanFactory().getBean("guestVideoList")).execute(request,  model);
		}

		mv.addObject("model", model);
		if (model.getLectureseriesId() != 0) mv.setView("guestLectureseriesShowDetails");
		else mv.setView("guestVideoList");

		return mv;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#showForm(javax.portlet.RenderRequest, javax.portlet.RenderResponse, org.springframework.validation.BindException)
	 */
	/**
	 * Show form.
	 *
	 * @param request the request
	 * @param response the response
	 * @param errors the errors
	 * @return the model and view
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected ModelAndView showForm(RenderRequest request, RenderResponse response, BindException errors) throws IOException{
		ModelAndView mv = new ModelAndView();
		GuestModel model = new GuestModel();
		
		// check user agent
		HttpServletRequest hsr = com.liferay.portal.util.PortalUtil.getHttpServletRequest(request);
		String userAgent = hsr.getHeader("User-Agent");
		model.setUserAgent(userAgent);

		// check mobile device
		if (userAgent.contains("Mobile") || userAgent.contains("Android") || userAgent.contains("Skyfire")) model.setMobileDevice(true);
		else model.setMobileDevice(false);

		//set tree
		model.setTree("tree1");
		
		// -- model and view --
		//lectureseries or video selected
		if (model.getLectureseries() != null || model.getVideo()!=null) {
			if (model.getVideo().isOpenaccess()){//take care for open access
				mv.setView("guestLectureseriesShowDetails");
			}else{
				mv.setView("error");
			}
		}else{
			((GuestVideoList) getCommandBeanFactory().getBean("guestVideoList")).execute(request,  model);
			mv.setView("guestVideoList");
		}
		// -- model and view --
		
		mv.addObject("model", model);
		return mv;
	}

}
