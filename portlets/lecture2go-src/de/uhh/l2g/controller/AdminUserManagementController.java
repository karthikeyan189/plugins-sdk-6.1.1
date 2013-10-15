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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.PortletRequestUtils;
import org.springframework.web.portlet.mvc.AbstractFormController;

import de.uhh.l2g.beans.User;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.dao.UserDao;
import de.uhh.l2g.model.AdminUserManagementModel;
import de.uhh.l2g.util.PaginationResultUser;
import de.uhh.l2g.util.ProzessManager;

/**
 * The Class AdminUserManagementController.
 */
public class AdminUserManagementController extends AbstractFormController implements InitializingBean{

	/** The l2go roles. */
	private Properties l2goRoles;

	/**
	 * Gets the l2go roles.
	 *
	 * @return the l2go roles
	 */
	public Properties getL2goRoles() {
		return l2goRoles;
	}

	/**
	 * Sets the l2go roles.
	 *
	 * @param l2goRoles the l2go roles
	 */
	public void setL2goRoles(Properties l2goRoles) {
		this.l2goRoles = l2goRoles;
	}

	/**
	 * Gets the l2go administrator role id.
	 *
	 * @return the l2go administrator role id
	 */
	public int getL2goAdministratorRoleId() {
		return l2goAdministratorRoleId;
	}

	/**
	 * Sets the l2go administrator role id.
	 *
	 * @param l2goAdministratorRoleId the l2go administrator role id
	 */
	public void setL2goAdministratorRoleId(int l2goAdministratorRoleId) {
		this.l2goAdministratorRoleId = l2goAdministratorRoleId;
	}

	/**
	 * Gets the l2go student role id.
	 *
	 * @return the l2go student role id
	 */
	public int getL2goStudentRoleId() {
		return l2goStudentRoleId;
	}

	/**
	 * Sets the l2go student role id.
	 *
	 * @param l2goStudentRoleId the l2go student role id
	 */
	public void setL2goStudentRoleId(int l2goStudentRoleId) {
		this.l2goStudentRoleId = l2goStudentRoleId;
	}

	/** The l2go administrator role id. */
	private int l2goAdministratorRoleId;
	
	/** The l2go student role id. */
	private int l2goStudentRoleId;
	
	/** The l2go producer role id. */
	private int l2goProducerRoleId;
	
	/**
	 * Gets the l2go producer role id.
	 *
	 * @return the l2go producer role id
	 */
	public int getL2goProducerRoleId() {
		return l2goProducerRoleId;
	}

	/**
	 * Sets the l2go producer role id.
	 *
	 * @param l2goProducerRoleId the l2go producer role id
	 */
	public void setL2goProducerRoleId(int l2goProducerRoleId) {
		this.l2goProducerRoleId = l2goProducerRoleId;
	}

	/** The l2go coordinator role id. */
	private int l2goCoordinatorRoleId;

	/**
	 * Gets the l2go coordinator role id.
	 *
	 * @return the l2go coordinator role id
	 */
	public int getL2goCoordinatorRoleId() {
		return l2goCoordinatorRoleId;
	}

	/**
	 * Sets the l2go coordinator role id.
	 *
	 * @param l2goCoordinatorRoleId the l2go coordinator role id
	 */
	public void setL2goCoordinatorRoleId(int l2goCoordinatorRoleId) {
		this.l2goCoordinatorRoleId = l2goCoordinatorRoleId;
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
	 * @param utilityBeanFactory the utility bean factory
	 */
	public void setUtilityBeanFactory(XmlBeanFactory utilityBeanFactory) {
		this.utilityBeanFactory = utilityBeanFactory;
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
	
	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#showForm(javax.portlet.RenderRequest, javax.portlet.RenderResponse, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView showForm(RenderRequest request, RenderResponse response, BindException errors) throws Exception {
		ModelAndView mv = new ModelAndView();
		AdminUserManagementModel model = new AdminUserManagementModel();
		Map<?,?> modelMap = errors.getModel();
		
		//parameter
		int pageSize = PortletRequestUtils.getIntParameter((PortletRequest) request, "pagesize", 10);
		int start = (PortletRequestUtils.getIntParameter((PortletRequest) request, "pagenumber", 1)-1)*pageSize;
		Integer roleId = PortletRequestUtils.getIntParameter(request, "roleId", 0);
		
		//set users list
		List<User> userList = null;
		if(roleId==0)userList =	((UserDao)getDaoBeanFactory().getBean("userDao")).getPaginatetList(start, pageSize);
		else userList =	((UserDao)getDaoBeanFactory().getBean("userDao")).getPaginatetListByRoleId(start, pageSize, roleId);
		model.setUserList(userList);
		
		//set l2go roles
		setRoles(model);
		model.setRoleId(roleId);
		
		//Pagination 
		((PaginationResultUser)getUtilityBeanFactory().getBean("paginationResultUser")).setPagination(model, request);
		
		mv.addAllObjects(modelMap);
		mv.addObject("model", model);
		mv.setView("adminUserList");
		return mv;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#renderFormSubmission(javax.portlet.RenderRequest, javax.portlet.RenderResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView renderFormSubmission(RenderRequest request, RenderResponse response, Object command, BindException errors) throws Exception {
		ModelAndView mv = new ModelAndView();
		AdminUserManagementModel model = (AdminUserManagementModel)command;
		Map<?,?> modelMap = errors.getModel();
		
		if(model.getAction().equals("viewallusers"))mv.setView("adminUserList");
		if(model.getAction().equals("approvalproducer"))mv.setView("adminProducerDetails");
		if(model.getAction().equals("approvalcoordinator"))mv.setView("adminCoordinatorDetails");
		
		mv.addAllObjects(modelMap);
		mv.addObject("model", model);
		return mv;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#processFormSubmission(javax.portlet.ActionRequest, javax.portlet.ActionResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void processFormSubmission(ActionRequest request, ActionResponse response, Object command, BindException errors) throws Exception {
		String action = request.getParameter("action");
		int userId = PortletRequestUtils.getIntParameter(request, "userId", 0);
		
		boolean makeProducer = false;
		boolean makeCoordinator = false;
		boolean makeAdmin = false;
		boolean makeStudent = false;
		
		//make producer
		try{ if(request.getParameter("isProducer").equals("true"))makeProducer = true; }catch(NullPointerException npe){}
		//make coordinator
		try{ if(request.getParameter("isCoordinator").equals("true"))makeCoordinator = true; }catch(NullPointerException npe){}
		//make admin
		try{ if(request.getParameter("isL2goadmin").equals("false"))makeAdmin = true; }catch(NullPointerException npe){}
		//make student
		try{ if(request.getParameter("isStudent").equals("false"))makeStudent = true; }catch(NullPointerException npe){}
			
		AdminUserManagementModel model = (AdminUserManagementModel)command;
		model.setAction(action);
		
		if(model.getAction().equals("viewallusers")){
			
			//sub actions and parameters
			Integer k = PortletRequestUtils.getIntParameter(request, "coordinator", 0);
			Integer p = PortletRequestUtils.getIntParameter(request, "producer", 0);				
			Integer s = PortletRequestUtils.getIntParameter(request, "student", 0);
			Integer a = PortletRequestUtils.getIntParameter(request, "admin", 0);
			Integer roleId = PortletRequestUtils.getIntParameter(request, "roleId", 0);
			
			//do actions
			
			//for producer
			if(p==1)((ProducerDao)getDaoBeanFactory().getBean("producerDao")).setApproved(userId, getL2goProducerRoleId(), makeProducer);
			//for coordinator
			if(k==1)if(!makeCoordinator)((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).deleteCoordinator(userId, getL2goCoordinatorRoleId());
			//for administrator
			if(a==1){
				if(makeAdmin) ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).setUserRoleId(userId, getL2goAdministratorRoleId(), true);
				else ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).setUserRoleId(userId, getL2goAdministratorRoleId(), false);
			}
			//for student
			if(s==1){
				if(makeStudent) ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).setUserRoleId(userId, getL2goStudentRoleId(), true);
				else ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).setUserRoleId(userId, getL2goStudentRoleId(), false);				
			}
			
			//set filter
			int pageSize = PortletRequestUtils.getIntParameter((PortletRequest) request, "pagesize", 10);
			int start = (PortletRequestUtils.getIntParameter((PortletRequest) request, "pagenumber", 1)-1)*pageSize;
			
			//set users list
			List<User> userList = null;
			if(roleId==0)userList =	((UserDao)getDaoBeanFactory().getBean("userDao")).getPaginatetList(start, pageSize);
			else userList =	((UserDao)getDaoBeanFactory().getBean("userDao")).getPaginatetListByRoleId(start, pageSize, roleId);
			model.setUserList(userList);


			//set l2go roles
			setRoles(model);
			model.setRoleId(roleId);
			
			//set pagination 
			((PaginationResultUser)getUtilityBeanFactory().getBean("paginationResultUser")).setPagination(model, request);
					
		}
	}
	
	/**
	 * Sets the roles.
	 *
	 * @param model the roles
	 */
	public void setRoles(AdminUserManagementModel model){
		Map<String, String> lecture2goRoles = new HashMap<String, String>();
		for (Object roleKey : l2goRoles.keySet()) {
			lecture2goRoles.put(("" + roleKey), l2goRoles.getProperty("" + roleKey));
		}
		model.setL2goRoles(lecture2goRoles);
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(daoBeanFactory, "daoBeanFactory not null required");
		Assert.notNull(l2goRoles, "l2goRoles not null required");
	}
}
