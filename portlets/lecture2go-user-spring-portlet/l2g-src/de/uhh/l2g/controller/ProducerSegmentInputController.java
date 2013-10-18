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

import java.io.File;
import java.util.List;
import java.util.ListIterator;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.web.portlet.mvc.SimpleFormController;

import de.uhh.l2g.beans.Mark;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.HostDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.dao.SegmentDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.ProducerSegmentInputModel;
import de.uhh.l2g.util.HtmlManager;
import de.uhh.l2g.util.L2goPropsUtil;
import de.uhh.l2g.util.ProzessManager;

/**
 * The Class ProducerSegmentInputController.
 */
public class ProducerSegmentInputController extends SimpleFormController{


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
	 * @param utilityBeanFactory the new utility bean factory
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
	 * @param beanFactory the new dao bean factory
	 */
	public void setDaoBeanFactory(XmlBeanFactory beanFactory) {
		this.daoBeanFactory = beanFactory;
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.portlet.mvc.SimpleFormController#doSubmitAction
	 * (java.lang.Object)
	 */
	/**
	 * Do submit action.
	 *
	 * @param o the o
	 */
	@Override
	public void doSubmitAction(Object o) {
		ProducerSegmentInputModel model = (ProducerSegmentInputModel) o;
			//delete action 
			if (model.getAction().equals("delete")) {
				int sId = new Integer(model.getPortletRequest().getParameter("segmentId"));
				
				List<Mark> segmentList;
				segmentList = ((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getMarkById(sId);

				deleteThumbhailsFromSegments(segmentList);
				((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).deleteById(sId);

				// refresh model
				// update the videoList please
				segmentList = ((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getSegmentsByVideoId(model.getVideoId());

				if (segmentList.size() == 0) model.setSegmentList(null);
				else model.setSegmentList(segmentList);
			}
			
			//create action
			if (model.getAction().equals("create")) {
				create(model);
				// reload list
				List<Mark> segmentList = ((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getSegmentsByVideoId(model.getVideoId());
				model.setSegmentList(segmentList);
			}
			
			//change permission to segment 
			if (model.getAction().equals("permittToSegment")){
				((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).changePermissionToSegment(model.getVideo());
				//update video from DB
				Video v = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(model.getVideo().getId()).iterator().next();
				model.setVideo(v);
			}
			
		// reset actions
		model.setAction(null);
	}
	
	/**
	 * Creates the.
	 *
	 * @param psm the psm
	 */
	private void create(ProducerSegmentInputModel psm) {
		ProducerSegmentInputModel model = psm;

		//
		if (model.getDescription().trim().equals("")) model.setDescription(null);

		int chapter = 0;
		if (model.isChapter()) chapter = 1;

		//save
		String description = HtmlManager.cleanHtmlTags(model.getDescription());
		((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).create(model.getVideo().getId(), model.getTimeStart(), model.getTitle(), description, model.getTimeEnd(), chapter, model.getRemoteUserId());

		// update the videoList please
		model.setSegmentList(((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getSegmentsByVideoId(model.getVideo().getId()));

		// don't forget to reset and set at the end
		model.setTimeStart(model.getTimeEnd());
		model.setTimeEnd("");

		model.setTitle("");
		model.setDescription("");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.portlet.mvc.AbstractFormController#formBackingObject
	 * (javax.portlet.PortletRequest)
	 */
	/**
	 * Form backing object.
	 *
	 * @param request the request
	 * @return the object
	 */
	@Override
	protected Object formBackingObject(PortletRequest request) {
		ProducerSegmentInputModel model = new ProducerSegmentInputModel();
				
		//save request !
		model.setPortletRequest(request);

		// check user agent
		HttpServletRequest hsr = com.liferay.portal.util.PortalUtil.getHttpServletRequest(request);
		String userAgent = hsr.getHeader("User-Agent");
		model.setUserAgent(userAgent);
		
		//default for chapter
		int chapter = 0;
		
		try{
			chapter = new Integer(com.liferay.portal.util.PortalUtil.getHttpServletRequest(request).getParameter("chapter"));
			if(chapter==1) model.setChapter(true);
			else model.setChapter(false);
		}catch (Exception e) {
			//not set yet
			model.setChapter(true);
		}
				
		//userId
		Integer remoteUserId = new Integer (request.getRemoteUser());
		model.setRemoteUserId(remoteUserId);
		
		//active lectureseriesId
		Integer lectureseriesId=0;
		try{
			lectureseriesId=new Integer(request.getParameter("lectureseriesId"));
		}catch(NumberFormatException nfe){
			lectureseriesId=model.getLectureseriesId();
		}
		model.setLectureseriesId(lectureseriesId);

		// video
		Video video = (((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(new Integer(request.getParameter("videoId")))).iterator().next();
		model.setVideo(video);

		model.setProducer((((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByUserId(remoteUserId)).iterator().next());
		model.setHost((((HostDao)getDaoBeanFactory().getBean("hostDao")).getById(model.getProducer().getHostId())).iterator().next());

		//downloads
		((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).insertAdditionalDownloads(model);

		//current site
		model.setCurrentSeite(new Integer(request.getParameter("currentSeite")));
		
		//mark list
		List<Mark> markList = ((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getSegmentsByVideoId(model.getVideo().getId());
		if (markList.size() > 0) model.setSegmentList(markList);
		else model.setSegmentList(null);

		//lectureseries
		model.setLectureseries(video.getObjectLectureseries());

		return model;
	}

	/**
	 * Delete thumbhails from segments.
	 *
	 * @param segmentList the segment list
	 */
	protected void deleteThumbhailsFromSegments(List<Mark> segmentList) {
		ListIterator<Mark> it = segmentList.listIterator();
		while (it.hasNext()) {
			Mark objectSegment = it.next();
			List<Video> videoList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(objectSegment.getVideoId());
			Video objectVideo = videoList.iterator().next();
			int sec = new Integer(objectSegment.getStart().split(":")[0]) * 60 * 60 + new Integer(objectSegment.getStart().split(":")[1]) * 60 + new Integer(objectSegment.getStart().split(":")[2]);
			File thumbNail = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + objectVideo.getId() + "_" + sec + ".jpg");
			if (thumbNail.isFile()) thumbNail.delete();
		}
	}
	
	
}
