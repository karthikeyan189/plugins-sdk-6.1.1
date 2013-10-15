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

import java.io.File;
import java.util.List;
import java.util.ListIterator;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;

import org.springframework.validation.BindException;

import de.uhh.l2g.beans.Factory;
import de.uhh.l2g.beans.Mark;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.SegmentDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.StudentMetaDataModel;
import de.uhh.l2g.util.HtmlManager;
import de.uhh.l2g.util.L2goPropsUtil;

/**
 * The Class AbstractStudentMetaDataCommand.
 */
public abstract class AbstractStudentMetaDataCommand extends Factory {

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @param model the model
	 * @param errors the errors
	 */
	public abstract void execute(ActionRequest request, StudentMetaDataModel model, BindException errors);
	
	/**
	 * Execute.
	 *
	 * @param request the request
	 * @param model the model
	 * @param errors the errors
	 */
	public abstract void execute(PortletRequest request, StudentMetaDataModel model, BindException errors);

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
	
	/**
	 * Creates the.
	 *
	 * @param psm the psm
	 */
	protected void create(StudentMetaDataModel psm) {
		StudentMetaDataModel model = psm;

		//
		if (model.getDescription().trim().equals("")) model.setDescription(null);

		int chapter = 0;
		if (model.isChapter()) chapter = 1;

		// save
		String description = HtmlManager.cleanHtmlTags(model.getDescription());
		int segmentId = ((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).createAndReturnGeneratedId(model.getVideo().getId(), model.getTimeStart(), model.getTitle(), description, model.getTimeEnd(), chapter, model.getRemoteUserId());
		// and save in table segment_user_video
		((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).addSegmentIUserIVideoIddToTable_segment_user_video(segmentId, model.getRemoteUserId(), model.getVideo().getId());
		// update the videoList please
		model.setSegmentList(((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getSegmentsByVideoId(model.getVideo().getId()));

		// don't forget to reset and set at the end
		model.setTimeStart(model.getTimeEnd());
		model.setTimeEnd("");

		model.setTitle("");
		model.setDescription("");
		
	}
}
