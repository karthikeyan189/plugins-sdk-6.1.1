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

import java.util.List;
import java.util.NoSuchElementException;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.web.portlet.mvc.SimpleFormController;

import de.uhh.l2g.beans.Host;
import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.beans.Mark;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.User;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.HostDao;
import de.uhh.l2g.dao.LectureseriesDao;
import de.uhh.l2g.dao.MetadataDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.dao.SegmentDao;
import de.uhh.l2g.dao.UserDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.StudentModel;
import de.uhh.l2g.util.L2goPropsUtil;
import de.uhh.l2g.util.ProzessManager;

/**
 * The Class StudentController.
 */
public class StudentController extends SimpleFormController{
		
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

	 /**
 	 * Checks if is authenticated.
 	 *
 	 * @param vlpwd the vlpwd
 	 * @param lectureseries the lectureseries
 	 * @return true, if is authenticated
 	 */
 	private boolean isAuthenticated(String vlpwd, Lectureseries lectureseries) {
		boolean rt = false;
		if (lectureseries.getPassword().equals(vlpwd.trim())) rt = true;
		else rt = false;

		return rt;
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
		StudentModel model = new StudentModel();

		String userId = request.getRemoteUser();
		int uId = 0;
		try{
			uId = new Integer(userId);
		}catch (NumberFormatException nfe){}
		
		String vi = request.getParameter("videoId");
		String vlpwd = request.getParameter("vlpwd");
		
		// check user agent
		HttpServletRequest hsr = com.liferay.portal.util.PortalUtil.getHttpServletRequest(request);
		String userAgent = hsr.getHeader("User-Agent");
		model.setUserAgent(userAgent);

		// check mobile device
		if (userAgent.contains("Mobile") || userAgent.contains("Android") || userAgent.contains("Skyfire")) model.setMobileDevice(true);
		else model.setMobileDevice(false);

		// security
		model.setStudentAuthenticated(false);

		// check for video OR audio
		String surl = "";
		int id = 0;

		try {// video
			surl = vi.split("\\.")[0] + ".mp4";
			id = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getBySUrl(surl).iterator().next().getId();
		} catch (NoSuchElementException e) {// audio
			surl = vi.split("\\.")[0] + ".mp3";
			id = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getBySUrl(surl).iterator().next().getId();
		}
		
		//set video
		Video video = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(new Integer(id)).iterator().next();
		model.setVideo(video);
		
		//set metadata
		model.setMetadata(((MetadataDao)getDaoBeanFactory().getBean("metadataDao")).getById(video.getMetadataId()).iterator().next());
		
		//set host
		Host host = ((HostDao)getDaoBeanFactory().getBean("hostDao")).getById(video.getHostId()).iterator().next();
		model.setHost(host);
		
		//set user
		try{
			User u = ((UserDao)getDaoBeanFactory().getBean("userDao")).getById(uId).iterator().next();
			model.setUserIsStudent(u.getIsStudent());
		}catch(Exception e){
			
		}
	
		//set producer
		Producer producer = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByUserId(video.getProducerId()).iterator().next();
		model.setProducer(producer);
		
		//verwandte videos
		model.setRelatedVideosList(((VideoDao)getDaoBeanFactory().getBean("videoDao")).getClosedAccessVideosByLectureseriesId(video.getLectureseriesId(), "DESC"));
		
		//set lectureseries
		model.setLectureseries(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getById(video.getLectureseriesId()).iterator().next());

		// video segments
		List<Mark> segmentList = ((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getSegmentsByVideoId(video.getId());
		
		if (segmentList.size() == 0) model.setSegmentList(null);
		else model.setSegmentList(segmentList);

		//prepare downloads
		((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).insertAdditionalDownloads(model);
		
		model.setDownloadServerURL(L2goPropsUtil.get("lecture2go.downloadserver.web.root"));
		
		if (vlpwd != null) {
			if (isAuthenticated(vlpwd, model.getLectureseries())) {
				model.setStudentAuthenticated(true);
				// count hits
				((VideoDao)getDaoBeanFactory().getBean("videoDao")).incrementHitsForVideo(video);
			} else {
				model.setStudentAuthenticated(false);
			}
			model.setPwdCheck(true);
		} else {
			model.setPwdCheck(false);
		}
		
		if (model.getLectureseries().getPassword() == null)((VideoDao)getDaoBeanFactory().getBean("videoDao")).incrementHitsForVideo(video);

		model.setSuccess(true);
		
		return model;
	}	
}
