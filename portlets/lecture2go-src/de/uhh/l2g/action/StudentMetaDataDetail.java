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
package de.uhh.l2g.action;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;

import org.springframework.validation.BindException;

import de.uhh.l2g.beans.Mark;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.SegmentDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.StudentMetaDataModel;
import de.uhh.l2g.util.L2goPropsUtil;
import de.uhh.l2g.util.ProzessManager;

/**
 * The Class StudentMetaDataDetail.
 */
public final class StudentMetaDataDetail extends AbstractStudentMetaDataCommand{

	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractStudentMetaDataCommand#execute(javax.portlet.ActionRequest, de.uhh.l2g.model.StudentMetaDataModel, org.springframework.validation.BindException)
	 */
	@Override
	public void execute(ActionRequest request, StudentMetaDataModel model, BindException errors) {
		//Segment-List
		List<Mark> segmentList;
		
		//userId
		Integer remoteUserId = new Integer (request.getRemoteUser());
		model.setRemoteUserId(remoteUserId);
		
		//domain url
		String domainURL = L2goPropsUtil.get("lecture2go.web.home");
		model.setDomainURL(domainURL);
		
		// video
		Video video = (((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(new Integer(request.getParameter("videoId")))).iterator().next();
		model.setVideo(video);

		model.setProducer(video.getObjectProducer());
		model.setHost(video.getObjectHost());

		//downloads
		((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).insertAdditionalDownloads(model);

		//actions
		if(!errors.hasErrors()){
			//delete action 
			if (model.getAction().equals("delete")) {
				int sId = new Integer(request.getParameter("segmentId"));
				
				segmentList = ((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getMarkById(sId);
				//delete all
				deleteThumbhailsFromSegments(segmentList);
				((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).deleteBySegmentIdAndUserIdFromTable_segment_user_video(sId, model.getRemoteUserId());
				((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).deleteById(sId);
			}
			//create action
			if (model.getAction().equals("create"))create(model);
			
			// reset actions
			if (!errors.hasErrors())model.setAction(null);			
		}
		// actions
		// refresh model
		// update the videoList please
		segmentList = ((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getSegmentsByVideoId(model.getVideoId());

		if (segmentList.size() == 0) model.setSegmentList(null);
		else model.setSegmentList(segmentList);
		
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractStudentMetaDataCommand#execute(javax.portlet.PortletRequest, de.uhh.l2g.model.StudentMetaDataModel, org.springframework.validation.BindException)
	 */
	@Override
	public void execute(PortletRequest request, StudentMetaDataModel model, BindException errors) {
		//userId
		Integer remoteUserId = new Integer (request.getRemoteUser());
		model.setRemoteUserId(remoteUserId);
		
		//domain url
		String domainURL = L2goPropsUtil.get("lecture2go.web.home");
		model.setDomainURL(domainURL);
		
		// video
		Video video = (((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(new Integer(request.getParameter("videoId")))).iterator().next();
		model.setVideo(video);

		model.setProducer(video.getObjectProducer());
		model.setHost(video.getObjectHost());

		//downloads
		((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).insertAdditionalDownloads(model);

		//mark list
		List<Mark> markList = ((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getSegmentsByVideoId(model.getVideo().getId());
		if (markList.size() > 0) model.setSegmentList(markList);
		else model.setSegmentList(null);
		
		//lectureseries
		model.setLectureseries(video.getObjectLectureseries());
	}
}
