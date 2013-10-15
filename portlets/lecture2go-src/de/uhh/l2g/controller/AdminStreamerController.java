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

import de.uhh.l2g.beans.Host;
import de.uhh.l2g.dao.HostDao;
import de.uhh.l2g.model.AdminStreamerModel;
import de.uhh.l2g.util.L2goPropsUtil;
import de.uhh.l2g.util.RepositoryManager;


/**
 * The Class AdminStreamerController.
 */
public class AdminStreamerController extends SimpleFormController{

	/** The port. */
	private final int PORT = 80;
	
	/** The protokoll. */
	private final String PROTOKOLL = "http";
	
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
	 * @see org.springframework.web.portlet.mvc.SimpleFormController#processFormSubmission(javax.portlet.ActionRequest, javax.portlet.ActionResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void processFormSubmission(ActionRequest request, ActionResponse arg1, Object command, BindException errors) throws Exception {
		//model
		AdminStreamerModel model = (AdminStreamerModel) command;
		//action
		String action = request.getParameter("action");

		//prepare parameter
		String name = "";
		String serverRoot = "";
		
		//action execute
		//add new data
		if(action.equals("add")){
			//and save data to database
			int id = ((HostDao)getDaoBeanFactory().getBean("hostDao")).createAndReturnGeneratedId( PROTOKOLL, model.getStreamer(), PORT, serverRoot, name );
			//prepare parameter for update
			name = prepareStreamerName(id);
			serverRoot = name;
			//update
			((HostDao)getDaoBeanFactory().getBean("hostDao")).updateById( PROTOKOLL,  model.getStreamer(),  PORT,  serverRoot,  name, id);
			//finally create the directory
			String folder = L2goPropsUtil.get("lecture2go.media.repository")+"/"+serverRoot;
			RepositoryManager.createForlder(folder);
		}
		//edit
		if(action.equals("edit")){
			//prepare parameter for update
			name = prepareStreamerName(model.getHostId());
			serverRoot = name;			
			//edit streamer in database
			((HostDao)getDaoBeanFactory().getBean("hostDao")).updateById(PROTOKOLL, model.getStreamer(), PORT, serverRoot, name, model.getHostId());
		}
		//delete
		if(action.equals("delete")){
			//delete by id if not used 
			((HostDao)getDaoBeanFactory().getBean("hostDao")).deleteById(model.getHostId());	
			//do not delete the file directory on server for security and data persistence reasons	
		}
	}

	/**
	 * Prepare streamer name.
	 *
	 * @param id the id
	 * @return the string
	 */
	protected String prepareStreamerName(int id){
		String name="";
		if(id<10)name = "vh_00"+id;
		if(id>10 && id<100)name = "vh_0"+id;
		if(id>100)name = "vh_"+id;
		return name;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.SimpleFormController#renderFormSubmission(javax.portlet.RenderRequest, javax.portlet.RenderResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView renderFormSubmission(RenderRequest req, RenderResponse arg1, Object command, BindException errors) throws Exception {
		ModelAndView mv = new ModelAndView();
		AdminStreamerModel model = (AdminStreamerModel) command;
		Map<?,?> modelMap = errors.getModel();
	
		//reset model
		model.setStreamer("");
		
		//Host List
		List<Host> hostList = ((HostDao)getDaoBeanFactory().getBean("hostDao")).getAll();
		model.setHostList(hostList);
		
		mv.addAllObjects(modelMap);
		mv.addObject("model", model);

		mv.setViewName("adminStreamer");

		return mv;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.SimpleFormController#showForm(javax.portlet.RenderRequest, javax.portlet.RenderResponse, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView showForm(RenderRequest req, RenderResponse arg1, BindException errors) throws Exception {
		ModelAndView mv = new ModelAndView();
		AdminStreamerModel model = new AdminStreamerModel();
		Map<?,?> modelMap = errors.getModel();
		
		//Host List
		List<Host> hostList = ((HostDao)getDaoBeanFactory().getBean("hostDao")).getAll();
		model.setHostList(hostList);
		
		mv.addAllObjects(modelMap);
		mv.addObject("model", model);

		mv.setViewName("adminStreamer");

		return mv;
	}

}
