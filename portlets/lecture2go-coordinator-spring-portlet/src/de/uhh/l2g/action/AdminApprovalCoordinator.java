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
package de.uhh.l2g.action;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.bind.PortletRequestUtils;
import org.springframework.web.portlet.mvc.SimpleFormController;

import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.IFactory;
import de.uhh.l2g.beans.User;
import de.uhh.l2g.dao.FacilityDao;
import de.uhh.l2g.dao.UserDao;
import de.uhh.l2g.model.AdminUserManagementModel;
import de.uhh.l2g.util.ProzessManager;

/**
 * The Class AdminApprovalCoordinator.
 */
public class AdminApprovalCoordinator extends SimpleFormController implements InitializingBean, IFactory{

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
	 * @param l2goCoordinatorRoleId the new l2go coordinator role id
	 */
	public void setL2goCoordinatorRoleId(int l2goCoordinatorRoleId) {
		this.l2goCoordinatorRoleId = l2goCoordinatorRoleId;
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
	protected void onSubmitAction(ActionRequest request, ActionResponse response, Object command, BindException errors) throws Exception {
		AdminUserManagementModel model = (AdminUserManagementModel)command;
		int facilityId = PortletRequestUtils.getIntParameter(request, "facilityId");
		int userId = PortletRequestUtils.getIntParameter(request, "userId");
		
		//create coordinator
		((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).createCoordinator(userId, facilityId, getL2goCoordinatorRoleId());

		//renew facilities list
		//get assignet facilities from table coordinator
		List <Facility> ke = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getFromCoordinator(1);
		model.setFacilitiesList(((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByLevelAndUnassigned(1, ke, facilityId));
		
		//renew user object
		User user = ((UserDao)getDaoBeanFactory().getBean("userDao")).getById(userId).iterator().next();
		model.setUser(user);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#formBackingObject(javax.portlet.PortletRequest)
	 */
	/**
	 * Form backing object.
	 *
	 * @param request the request
	 * @return the object
	 * @throws Exception the exception
	 */
	protected Object formBackingObject(PortletRequest request) throws Exception {
		AdminUserManagementModel model = new AdminUserManagementModel();
		
		//userId
		int userId = new Integer(request.getParameter("userId"));

		//parameter
		int roleId = PortletRequestUtils.getIntParameter(request, "roleId", 0);
		int pagesize = PortletRequestUtils.getIntParameter(request, "pagesize", 10);
		int pagenumber = PortletRequestUtils.getIntParameter(request, "pagenumber", 1);
		model.setRoleId(roleId);
		model.setCurrentPageNumber(pagenumber);
		model.setPageSize(pagesize);
		
		//User
		User user = ((UserDao)getDaoBeanFactory().getBean("userDao")).getById(userId).iterator().next();
		model.setUser(user);	
		
		//set facilities list
		//get assignet facilities from table coordinator
		List <Facility> ke = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getFromCoordinator(1);
		
		int exceptId = 0;
		try{
			exceptId = model.getUser().getObjectCoordinator().getFacilityId();
		}catch(NullPointerException npe){}
		
		List <Facility> uke = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByLevelAndUnassigned(1, ke, exceptId);
		model.setFacilitiesList(uke);
		return model;		
	}
	
	
	/** The utility bean factory. */
	private XmlBeanFactory utilityBeanFactory;
	
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

	/* (non-Javadoc)
	 * @see de.uhh.l2g.beans.IFactory#setDaoBeanFactory(org.springframework.beans.factory.xml.XmlBeanFactory)
	 */
	/**
	 * Sets the dao bean factory.
	 *
	 * @param beanFactory the new dao bean factory
	 */
	public void setDaoBeanFactory(XmlBeanFactory beanFactory) {
		this.daoBeanFactory = beanFactory;
	}
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.beans.IFactory#getUtilityBeanFactory()
	 */
	/**
	 * Gets the utility bean factory.
	 *
	 * @return the utility bean factory
	 */
	public XmlBeanFactory getUtilityBeanFactory() {
		return utilityBeanFactory;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.beans.IFactory#setUtilityBeanFactory(org.springframework.beans.factory.xml.XmlBeanFactory)
	 */
	/**
	 * Sets the utility bean factory.
	 *
	 * @param utilityBeanFactory the new utility bean factory
	 */
	public void setUtilityBeanFactory(XmlBeanFactory utilityBeanFactory) {
		this.utilityBeanFactory = utilityBeanFactory;
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
		Assert.notNull(daoBeanFactory, "DaoBeanFactory not null required");
	}
}
