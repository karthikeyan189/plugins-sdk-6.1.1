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

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.SimpleFormController;

import de.uhh.l2g.action.StudentMetaDataDetail;
import de.uhh.l2g.action.StudentMetaDataList;
import de.uhh.l2g.model.StudentMetaDataModel;

/**
 * The Class StudentMetaDataController.
 */
public class StudentMetaDataController extends SimpleFormController{
	
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
	 * @see org.springframework.web.portlet.mvc.SimpleFormController#processFormSubmission(javax.portlet.ActionRequest, javax.portlet.ActionResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void processFormSubmission(ActionRequest request, ActionResponse response, Object command, BindException errors) {
		StudentMetaDataModel model = (StudentMetaDataModel) command;

		// check user agent
		HttpServletRequest hsr = com.liferay.portal.util.PortalUtil.getHttpServletRequest(request);
		String userAgent = hsr.getHeader("User-Agent");
		model.setUserAgent(userAgent);
		
		//action
		String action = request.getParameter("action");
		model.setAction(action);
		
		int videoId=0;
		try{
			videoId=new Integer(request.getParameter("videoId"));
			model.setVideoId(videoId);
		}catch(NumberFormatException nfe){}
		
		if (model.getVideoId()!=0)
			((StudentMetaDataDetail)getCommandBeanFactory().getBean("studentMetaDataDetail")).execute(request, model,errors);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.SimpleFormController#renderFormSubmission(javax.portlet.RenderRequest, javax.portlet.RenderResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView renderFormSubmission(RenderRequest request, RenderResponse response, Object command, BindException errors) {
		ModelAndView mv = new ModelAndView();
		StudentMetaDataModel model = (StudentMetaDataModel) command;
		Map<?,?> modelMap = errors.getModel();
	
		mv.setView("studentMetaDataDetails");
	
		mv.addAllObjects(modelMap);
		mv.addObject("model", model);
		
		return mv;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.SimpleFormController#showForm(javax.portlet.RenderRequest, javax.portlet.RenderResponse, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView showForm(RenderRequest request, RenderResponse response, BindException errors) {
		ModelAndView mv = new ModelAndView();
		StudentMetaDataModel model = new StudentMetaDataModel();
		Map<?,?> modelMap = errors.getModel();

		// check user agent
		HttpServletRequest hsr = com.liferay.portal.util.PortalUtil.getHttpServletRequest(request);
		String userAgent = hsr.getHeader("User-Agent");
		model.setUserAgent(userAgent);
		
		int videoId=0;
		try{
			videoId=new Integer(request.getParameter("videoId"));
			model.setVideoId(videoId);
		}catch(NumberFormatException nfe){}
		
		// prepare detail view
		if(model.getVideoId()!=0){
			((StudentMetaDataDetail)getCommandBeanFactory().getBean("studentMetaDataDetail")).execute(request, model, errors);
			mv.setView("studentMetaDataDetails");
		} else {
			((StudentMetaDataList) getCommandBeanFactory().getBean("studentMetaDataList")).execute(request, model, errors);
			mv.setView("studentMetaDataList");
		}
		
		mv.addAllObjects(modelMap);
		mv.addObject("model", model);
		
		return mv;
	}
}
