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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.AbstractFormController;

import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.GuestSearchModel;

/**
 * The Class GuestSearchController.
 */
public class GuestSearchController extends AbstractFormController {

	/** The video search limit. */
	private int videoSearchLimit = 100;
	
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
	
	/*
	 * (non-Javadoc)
	 * @seeorg.springframework.web.portlet.mvc.AbstractFormController#
	 * processFormSubmission(javax.portlet.ActionRequest,
	 * javax.portlet.ActionResponse, java.lang.Object,
	 * org.springframework.validation.BindException)
	 */
	@Override
	protected void processFormSubmission(ActionRequest request, ActionResponse response, Object command, BindException errors) throws Exception {
		GuestSearchModel model = (GuestSearchModel)command;
		
		List<Video> videoList = new ArrayList<Video>();
		List<Video> videoList2 = new ArrayList<Video>();
		
		model.setSearchResultsLimit(videoSearchLimit);
		model.setVideoList(videoList);
		model.setMoreVideoSearchResults(videoList2);

		String search = "";
		try {search = request.getParameter("search");} catch (Exception e) {}

		if (search.trim().length() > 1) {
			if (search != null) {
				videoList = ((VideoDao) getDaoBeanFactory().getBean("videoDao")).getVideoResultSearchListBySearchWord(search, model.getSearchResultsLimit());
				// weitere treffer
				videoList2 = ((VideoDao) getDaoBeanFactory().getBean("videoDao")).getVideoResultSearchListBySplittedSearchWord(search, model.getSearchResultsLimit());
			} 

			model.setVideoList(videoList);
			model.setMoreVideoSearchResults(videoList2);

			model.setNumberOfSearchResults(videoList.size());
			model.setNumberOfSearchResults2(videoList2.size());

		} else {
			try {
				videoList = new ArrayList<Video>();
				videoList2 = new ArrayList<Video>();
			} catch (NullPointerException e) {}

			model.setNumberOfSearchResults(0);
			model.setNumberOfSearchResults2(0);
			model.setVideoList(null);
			model.setMoreVideoSearchResults(null);
		}

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#renderFormSubmission(javax.portlet.RenderRequest, javax.portlet.RenderResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView renderFormSubmission(RenderRequest request, RenderResponse response, Object command, BindException errors) throws Exception {
		ModelAndView mv = new ModelAndView();
		GuestSearchModel model = (GuestSearchModel) command;
		// check user agent
		HttpServletRequest hsr = com.liferay.portal.util.PortalUtil.getHttpServletRequest(request);
		String userAgent = hsr.getHeader("User-Agent");
		model.setUserAgent(userAgent);

		// check mobile device
		if (userAgent.contains("Mobile") || userAgent.contains("Android") || userAgent.contains("Skyfire")) model.setMobileDevice(true);
		else model.setMobileDevice(false);

		mv.addObject("model", model);
		mv.setView("guestSearchVideoList");
		
		return mv;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#showForm(javax.portlet.RenderRequest, javax.portlet.RenderResponse, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView showForm(RenderRequest request, RenderResponse response, BindException errors) throws Exception {
		ModelAndView mv = new ModelAndView();
		GuestSearchModel model = new GuestSearchModel();
		
		// check user agent
		HttpServletRequest hsr = com.liferay.portal.util.PortalUtil.getHttpServletRequest(request);
		String userAgent = hsr.getHeader("User-Agent");
		model.setUserAgent(userAgent);

		// check mobile device
		if (userAgent.contains("Mobile") || userAgent.contains("Android") || userAgent.contains("Skyfire")) model.setMobileDevice(true);
		else model.setMobileDevice(false);

		model.setSearchResultsLimit(videoSearchLimit);
		model.setNumberOfSearchResults(0);
		model.setNumberOfSearchResults2(0);
		model.setVideoList(null);
		model.setMoreVideoSearchResults(null);
		
		mv.setView("guestSearchVideoList");

		return mv;
	}

}
