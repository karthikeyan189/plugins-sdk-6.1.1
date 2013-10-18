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

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.mvc.SimpleFormController;

import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.AdminThreadControlModel;
import de.uhh.l2g.util.DatabaseThread;

/**
 * The Class AdminThreadControlController.
 */
public class AdminThreadControlController extends SimpleFormController implements InitializingBean {

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
	 * @param daoBeanFactory the new dao bean factory
	 */
	public void setDaoBeanFactory(XmlBeanFactory daoBeanFactory) {
		this.daoBeanFactory = daoBeanFactory;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	/**
	 * After properties set.
	 *
	 * @throws Exception the exception
	 */
	public void afterPropertiesSet() throws Exception {

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
		AdminThreadControlModel model = (AdminThreadControlModel) command;
		String state = request.getParameter("state");
		
		if (state.equals("0")) {
			DatabaseThread.getInstance().setShouldStop(true);
		} 
		
		if (state.equals("1")) { 
			//start it
			try{DatabaseThread.getInstance().start();}catch (IllegalThreadStateException ite){}
			DatabaseThread.getInstance().setShouldStop(false);
		}
		
		//set parameters for all objects
		setParameters(model);		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#formBackingObject(javax.portlet.PortletRequest)
	 */
	/**
	 * Form backing object.
	 *
	 * @param request the request
	 * @return the object
	 */
	@Override
	protected Object formBackingObject(PortletRequest request) {
		AdminThreadControlModel model = new AdminThreadControlModel();
		//if thread not alive, set buttons to defaults
		if(!DatabaseThread.getInstance().isAlive())DatabaseThread.getInstance().setShouldStop(true);
		//set parameters for all objects
		setParameters(model);
		
		return model;
	}

	/**
	 * Sets the parameters.
	 *
	 * @param model the new parameters
	 */
	@SuppressWarnings("static-access")
	private final void setParameters(AdminThreadControlModel model){
		DatabaseThread.getInstance();
		//get resurses to thread
		DatabaseThread.setVideoDao(((VideoDao) getDaoBeanFactory().getBean("videoDao")));	
		//interrupt
		model.setInterrupted(DatabaseThread.getInstance().shouldStop());
		//set thread interval time, convert from ms to hours
		model.setTime((DatabaseThread.getInstance().getTime() / 3600000) +"");
	}
}
