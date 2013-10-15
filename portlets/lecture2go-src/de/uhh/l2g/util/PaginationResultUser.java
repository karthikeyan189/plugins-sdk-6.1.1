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
package de.uhh.l2g.util;

import java.util.List;

import javax.portlet.PortletRequest;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.web.portlet.bind.PortletRequestUtils;

import de.uhh.l2g.beans.User;
import de.uhh.l2g.dao.UserDao;
import de.uhh.l2g.model.GuestModel;

/**
 * The Class PaginationResultUser.
 */
public class PaginationResultUser extends PaginationResult {

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

	/** The result. */
	private List<User> result;

	/* (non-Javadoc)
	 * @see de.uhh.l2g.util.PaginationResult#getPaginationResult()
	 */
	@Override
	public List<User> getPaginationResult() {
		return result;
	}

	/**
	 * Sets the pagination result.
	 *
	 * @param r the pagination result
	 */
	public void setPaginationResult(List<User> r) {
		result = r;
	}

	/**
	 * Sets the pagination.
	 *
	 * @param model the model
	 * @param request the request
	 */
	public void setPagination(GuestModel model, Object request){
		int numberAllItems = 0;
	
		int pageSize = PortletRequestUtils.getIntParameter((PortletRequest) request, "pagesize", 10);
		int pageNumber = PortletRequestUtils.getIntParameter((PortletRequest) request, "pagenumber", 1);
		int roleId = PortletRequestUtils.getIntParameter((PortletRequest) request, "roleId", 0);
		
		//get items
		if(roleId==0) numberAllItems = ((UserDao)getDaoBeanFactory().getBean("userDao")).getCountAllUser();
		else numberAllItems = ((UserDao)getDaoBeanFactory().getBean("userDao")).getCountUserByRoleId(roleId);
			
		int numberSites = numberAllItems / pageSize;
		int rest = numberAllItems % pageSize;

		if (rest > 0 && pageSize < numberAllItems) numberSites++;
		if (pageSize > numberAllItems) numberSites = 1;

		// do pagination
		model.setPageSize(pageSize);
		model.setNumberResultPages(numberSites);
		model.setCurrentPageNumber(pageNumber);

		int rangeFirst = (((pageNumber - 1) / 3)) * 3 + 1;
		int rangeLast = Math.min((((pageNumber - 1) / 3)) * 3 + 3, model.getNumberResultPages());
		model.setPageRangeFirst(rangeFirst);
		model.setPageRangeLast(rangeLast);
		model.setHasPrev(rangeFirst > 1);
		model.setHasNext(rangeLast < model.getNumberResultPages());

		// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
		// -- -- -- -- -- -- -- -- -- -- -- -- -- -- --		
	}
	
}
