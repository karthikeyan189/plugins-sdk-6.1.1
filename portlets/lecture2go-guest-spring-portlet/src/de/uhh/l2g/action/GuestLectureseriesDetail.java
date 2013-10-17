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
import javax.portlet.PortletRequest;

import de.uhh.l2g.beans.Coordinator;
import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.Mark;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.CoordinatorDao;
import de.uhh.l2g.dao.FacilityDao;
import de.uhh.l2g.dao.HostDao;
import de.uhh.l2g.dao.LectureseriesDao;
import de.uhh.l2g.dao.MetadataDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.dao.SegmentDao;
import de.uhh.l2g.dao.UserDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.GuestModel;
import de.uhh.l2g.util.L2goPropsUtil;
import de.uhh.l2g.util.ProzessManager;

/**
 * The Class GuestLectureseriesDetail.
 */
public final class GuestLectureseriesDetail extends AbstractGuestCommand{

	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractGuestCommand#execute(javax.portlet.ActionRequest, de.uhh.l2g.model.GuestModel)
	 */
	/**
	 * Execute.
	 *
	 * @param request the request
	 * @param model the model
	 */
	@Override
	public void execute(ActionRequest request, GuestModel model) {
		//get lectureseries-id and lectureseries data
		Integer lectureseriesId;
		try {
			// set user status
			try{
				int remoteUserId = new Integer (request.getRemoteUser());
				model.setUserIsStudent(((UserDao)getDaoBeanFactory().getBean("userDao")).getById(remoteUserId).iterator().next().getIsStudent());
			}catch(NullPointerException npe){} catch(NumberFormatException nfe){}
			
			lectureseriesId = new Integer(request.getParameter("lectureseriesId"));
			model.setLectureseries(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getById(lectureseriesId).iterator().next());

			if (lectureseriesId != 0) {
				List<Video> relatedVideosList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getOpenAccessVideosByLectureseriesNumber(lectureseriesId, "DESC");
				model.setRelatedVideosList(relatedVideosList);
				
				model.setDownloadServerURL(L2goPropsUtil.get("lecture2go.downloadserver.web.root"));

				Video video = relatedVideosList.iterator().next();
				model.setVideo(video);
				
				model.setHost(((HostDao)getDaoBeanFactory().getBean("hostDao")).getById(video.getHostId()).iterator().next());
				
				model.setFacultyId(new Integer(request.getParameter("facultyId")));
				model.setSubFacility1Id(new Integer(request.getParameter("subFacility1Id")));
				
				setSubfacilities(model);
				
				model.setFacultyList(((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByTypeAndLevel("tree1", "1"));
				model.setProducer(((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByUserId(video.getProducerId()).iterator().next());
				
				String webhome = L2goPropsUtil.get("lecture2go.web.home");
				if (webhome.contains("localhost")) webhome+="/web/vod";
				
				model.setURL(webhome + "/l2go/-/v/" + video.getId());
				model.setMetadata(((MetadataDao)getDaoBeanFactory().getBean("metadataDao")).getById(video.getMetadataId()).iterator().next());
				
				// video segments
				List<Mark> segmentList = ((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getSegmentsByVideoId(video.getId());
				if (segmentList.size() == 0) model.setSegmentList(null);
				else model.setSegmentList(segmentList);
				
				model.setSuccess(true);
				((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).insertAdditionalDownloads(model);
				// count hits
				((VideoDao)getDaoBeanFactory().getBean("videoDao")).incrementHitsForVideo(video);

				// set license
				setLicenze(model);
				
				//responsible contact persons
				pepareContactEmailReceptors(video, model);
			}
		} catch (NullPointerException e) {
			model.setVideoId(-1);
			model.setSuccess(false);
		} 
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractGuestCommand#execute(javax.portlet.PortletRequest, de.uhh.l2g.model.GuestModel)
	 */
	/**
	 * Execute.
	 *
	 * @param request the request
	 * @param model the model
	 */
	@Override
	public void execute(PortletRequest request, GuestModel model) {
		List<Video> videoList = null;
		try {
			// set user status
			try{
				int remoteUserId = new Integer (request.getRemoteUser());
				model.setUserIsStudent( ((UserDao)getDaoBeanFactory().getBean("userDao")).getById(remoteUserId).iterator().next().getIsStudent());
			}catch(NullPointerException npe){} catch(NumberFormatException nfe){}
			
			//action
			model.setAction(request.getParameter("action"));
			
			//prepare video object and set
			int videoId = new Integer(request.getParameter("videoId"));
			videoList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(videoId);
			Video video = videoList.iterator().next();
			model.setVideo(video);
			
			//prepare facility and set
			Facility ei = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getFromVideoId(videoId).iterator().next();
			
			// pick the facultyId and the subfacilityId by videoId
			model.setSubFacility1Id(((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getSubfacilityFromFacilityId(ei.getId()).getId());
			model.setFacultyId(((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getFacultyFromFacilityId(ei.getId()).getId());
			
			setSubfacilities(model);
			
			//lectureseries
			model.setLectureseries(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getById(video.getLectureseriesId()).iterator().next());

			//faculty list
			model.setFacultyList(((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByTypeAndLevel("tree1", "1"));
			
			// here "relatedVideosList"
			model.setRelatedVideosList(((VideoDao)getDaoBeanFactory().getBean("videoDao")).getOpenAccessVideosByLectureseriesNumber(model.getLectureseries().getId(), "DESC"));
			
			//metadata
			model.setMetadata(((MetadataDao)getDaoBeanFactory().getBean("metadataDao")).getById(video.getMetadataId()).iterator().next());
			model.setProducer(((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByUserId(video.getProducerId()).iterator().next());

			String webhome = L2goPropsUtil.get("lecture2go.web.home");
			if (webhome.contains("localhost")) webhome+="/web/vod";

			model.setURL(webhome + "/l2go/-/v/" + video.getId());

			//host
			model.setHost(((HostDao)getDaoBeanFactory().getBean("hostDao")).getById(video.getHostId()).iterator().next());
			
			//video segments
			List<Mark> segmentList = ((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getSegmentsByVideoId(video.getId());
			if (segmentList.size() == 0) model.setSegmentList(null);
			else model.setSegmentList(segmentList);
			
			//prepare zitat2go and set
			try{model.setClipStartTime(new Integer(request.getParameter("clipStartTime")));}catch(NumberFormatException nfe){}
			try{model.setClipEndTime(new Integer(request.getParameter("clipEndTime")));}catch(NumberFormatException nfe){}
			if(video.getCitation2go()==0 && (model.getClipStartTime()!=null || model.getClipEndTime()!=null) )model.setCitation2goView(0);
			
			//downloads
			((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).insertAdditionalDownloads(model);
			
			// set license
			setLicenze(model);
			
			model.setDomainURL(L2goPropsUtil.get("lecture2go.web.root"));
			model.setSuccess(true);

			//count hits
			((VideoDao)getDaoBeanFactory().getBean("videoDao")).incrementHitsForVideo(video);
			
			model.setDownloadServerURL(L2goPropsUtil.get("lecture2go.downloadserver.web.root"));
			
			//responsible contact persons
			pepareContactEmailReceptors(video, model);
			
		} catch (NullPointerException e) {
			model.setVideoId(-1);
			model.setSuccess(false);
		}
	}
	
	/**
	 * Pepare contact email receptors.
	 *
	 * @param video the video
	 * @param model the model
	 */
	protected void pepareContactEmailReceptors(Video video, GuestModel model){
		//get Coordinator and Producer if they exist
		Coordinator c = null;
		Producer p = null;
		try{c = ((CoordinatorDao)getDaoBeanFactory().getBean("coordinatorDao")).getByFacilityId(video.getFacilityId()).iterator().next();}catch(Exception e){}
		try{p = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getProducerLRInfoByIdAndIsApproved(video.getProducerId()).iterator().next();;}catch(Exception e){}
		model.setCoordinator(c);
		model.setProducer(p);
		String emailTo="";
		//there is a coordinator
		if(c!=null){
			emailTo=c.getEmailAddress();//then email only to coordinator
		}else{
			//there is only producer
			if(p!=null)emailTo=p.getEmailAddress();	//email only to producer		
			//neither active producer nor coordinator
			if(p==null)emailTo=L2goPropsUtil.get("lecture2go.response.email.address");//email to l2go
		}
		model.setEmailTo(emailTo);
	}
	
}
