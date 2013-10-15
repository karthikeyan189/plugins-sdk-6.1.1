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

import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.mvc.SimpleFormController;

import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.dao.LectureseriesDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.ProducerLectureseriesModel;
import de.uhh.l2g.util.Htaccess;

/**
 * The Class ProducerLectureseriesController.
 */
public class ProducerLectureseriesController extends SimpleFormController {

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
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.portlet.mvc.SimpleFormController#onSubmitAction
	 * (javax.portlet.ActionRequest, javax.portlet.ActionResponse,
	 * java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void onSubmitAction(ActionRequest request, ActionResponse response, Object comm, BindException errors) {
		ProducerLectureseriesModel model = (ProducerLectureseriesModel) comm;
		// set password
		int cdId = new Integer(request.getParameter("lectureseriesId"));
		Lectureseries cd = ((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getById(cdId).iterator().next();
		String password = request.getParameter("password");
		String trimedPWD = password.trim();
		if (trimedPWD.equals("")) cd.setPassword(null);
		else cd.setPassword(password);
		((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).updateById(cd);
		// update list
		updateViewList(model);
		// update /srv/www/security/.htaccess
		// get all lectureseries data with pwd
		((Htaccess)getUtilityBeanFactory().getBean("htaccess")).writePW(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getAllwithPw());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.portlet.mvc.AbstractFormController#formBackingObject
	 * (javax.portlet.PortletRequest)
	 */
	@Override
	protected Object formBackingObject(PortletRequest request) throws Exception {
		ProducerLectureseriesModel model = new ProducerLectureseriesModel();
		//remote user
		model.setRemoteUserId(new Integer(request.getRemoteUser()));
		
		// get remote user id and producer
		updateViewList(model);
		
		if (model.getLectureseriesList().size() == 0) model.setVideoListEmpty(true);
		else model.setVideoListEmpty(false);
		
		return model;
	}

	/**
	 * Update view list.
	 *
	 * @param model the model
	 */
	private void updateViewList(ProducerLectureseriesModel model) {
		List<Lectureseries> lectureseriesList = ((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getLectureseriesForProducer(model.getRemoteUserId());
		//number of videos
		Iterator<Lectureseries> it = lectureseriesList.listIterator();
		while (it.hasNext()) {
			Lectureseries cd = it.next();
			cd.setNumberOfVideos(((VideoDao)getDaoBeanFactory().getBean("videoDao")).getNumberVideosByLectureseriesIdAndProducer(cd.getId(), model.getRemoteUserId()));
		}
		model.setLectureseriesList(lectureseriesList);
	}

}
