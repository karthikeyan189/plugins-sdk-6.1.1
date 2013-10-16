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

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.web.portlet.bind.PortletRequestUtils;
import org.springframework.web.portlet.mvc.AbstractController;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import de.uhh.l2g.dao.LectureseriesDao;


/**
 * The Class CoordinatorApprovalLectureseriesController.
 */
public class CoordinatorApprovalLectureseriesController extends AbstractController {
	
	/** The logger. */
	private static Log logger = LogFactoryUtil.getLog(CoordinatorApprovalLectureseriesController.class);

	/** The success view. */
	private String successView;

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
	 * @param beanFactory the new dao bean factory
	 */
	public void setDaoBeanFactory(XmlBeanFactory beanFactory) {
		this.daoBeanFactory = beanFactory;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractController#handleActionRequestInternal(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	/**
	 * Handle action request internal.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws Exception the exception
	 */
	@Override
	protected void handleActionRequestInternal(ActionRequest request, ActionResponse response) throws Exception {
		int id = PortletRequestUtils.getIntParameter(request, "lectureseriesId", 0);
		int pageNumber = PortletRequestUtils.getIntParameter(request, "pagenumber", 1);
		int pageSize = PortletRequestUtils.getIntParameter(request, "pagesize", 10);
		int facultyId = PortletRequestUtils.getIntParameter(request, "facultyid", 0);
		int producerId = PortletRequestUtils.getIntParameter(request, "producerid", 0);
		String semester = PortletRequestUtils.getStringParameter(request, "semester", "");
		String approved = PortletRequestUtils.getStringParameter(request, "approved", "");
		boolean reject = PortletRequestUtils.getBooleanParameter(request, "reject", false);

		if (id > 0) {
			String msg = "";
			if (((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).setApproved(id, !reject) > 0) {
				msg = "Die Veranstaltung wurde erfolgreich " + (reject
						? "auf den Status nicht freigegeben gesetzt" : "freigegeben") + " .";
				logger.debug("lectureseries with id " + id + " is " + (reject ? "rejected"
						: "approved") + " by admin.");
			} else {
				msg = "Ihre Aktion war nicht erfolgreich.";
				response.setRenderParameter("colorMsg", "#ff0000");
			}
			response.setRenderParameter("lectureseriesId", "" + id);
			response.setRenderParameter("pagesize", "" + pageSize);
			response.setRenderParameter("pagenumber", "" + pageNumber);
			response.setRenderParameter("facultyid", "" + facultyId);
			response.setRenderParameter("producerid", "" + producerId);
			response.setRenderParameter("semester", semester);
			response.setRenderParameter("approved", approved);
			response.setRenderParameter("messageToShow", msg);
		}
		response.setRenderParameter("action", "view");
	}

	/**
	 * Gets the success view.
	 *
	 * @return the success view
	 */
	public String getSuccessView() {
		return successView;
	}

	/**
	 * Sets the success view.
	 *
	 * @param successView the new success view
	 */
	public void setSuccessView(String successView) {
		this.successView = successView;
	}

}
