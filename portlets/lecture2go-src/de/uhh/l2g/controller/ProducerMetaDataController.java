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

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.SimpleFormController;

import de.uhh.l2g.action.ProducerMetaDataDetails;
import de.uhh.l2g.action.ProducerMetaDataList;
import de.uhh.l2g.model.ProducerVideoDataInputEditModel;


/**
 * The Class ProducerMetaDataController.
 */
public class ProducerMetaDataController extends SimpleFormController{

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.SimpleFormController#processFormSubmission(javax.portlet.ActionRequest, javax.portlet.ActionResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void processFormSubmission(ActionRequest request, ActionResponse response, Object command, BindException errors) {
		ProducerVideoDataInputEditModel model = (ProducerVideoDataInputEditModel) command;

		//action
		String action = request.getParameter("action");
		//set action to model
		model.setAction(action);
		
		if(model.getAction().equals("editMetadata")){
			((ProducerMetaDataDetails)getCommandBeanFactory().getBean("producerMetaDataDetails")).execute(request, model,errors);
		}else{
			((ProducerMetaDataList)getCommandBeanFactory().getBean("producerMetaDataList")).execute(request, model,errors);;
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.SimpleFormController#renderFormSubmission(javax.portlet.RenderRequest, javax.portlet.RenderResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView renderFormSubmission(RenderRequest request, RenderResponse response, Object command, BindException errors) {
		ModelAndView mv = new ModelAndView();
		ProducerVideoDataInputEditModel model = (ProducerVideoDataInputEditModel) command;
		Map<?,?> modelMap = errors.getModel();
		
		((ProducerMetaDataList)getCommandBeanFactory().getBean("producerMetaDataList")).execute(request, model,errors);
		mv.setView("producerMetaDataList");
				
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
		ProducerVideoDataInputEditModel model = new ProducerVideoDataInputEditModel();
		Map<?,?> modelMap = errors.getModel();
		
		//get Action
		String action = request.getParameter("action");
		if (action==null || action.equals(""))action = "showList";
		model.setAction(action);//store to model
		
		//edit metadata render url
		if (model.getAction().equals("editMetadata")){
			((ProducerMetaDataDetails)getCommandBeanFactory().getBean("producerMetaDataDetails")).execute(request, model,errors);
			mv.setView("producerMetaDataDetails");
		} 
			
		//back to list render url
		if ( action.equals("showList") || action.equals("lectureseriesSelect") || action.equals("browseMetadata")){
			try {
				((ProducerMetaDataList)getCommandBeanFactory().getBean("producerMetaDataList")).execute(request, model,errors);
			} catch (Exception e) {}
			mv.setView("producerMetaDataList");			
		}

		mv.addAllObjects(modelMap);
		mv.addObject("model", model);
		return mv;
	}

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

}
