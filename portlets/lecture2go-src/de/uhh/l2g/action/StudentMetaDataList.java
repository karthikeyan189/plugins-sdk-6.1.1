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

import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.StudentMetaDataModel;

/**
 * The Class StudentMetaDataList.
 */
public final class StudentMetaDataList extends AbstractStudentMetaDataCommand{

	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractStudentMetaDataCommand#execute(javax.portlet.ActionRequest, de.uhh.l2g.model.StudentMetaDataModel, org.springframework.validation.BindException)
	 */
	@Override
	public void execute(ActionRequest request, StudentMetaDataModel model, BindException errors) {
		//
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractStudentMetaDataCommand#execute(javax.portlet.PortletRequest, de.uhh.l2g.model.StudentMetaDataModel, org.springframework.validation.BindException)
	 */
	@Override
	public void execute(PortletRequest request, StudentMetaDataModel model, BindException errors) {
		// get remote user
		int remoteUserId = new Integer(request.getRemoteUser());
		
		List<Video> videoListForSegmentsByUserId = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getVideoListForSegmentsByUserId(remoteUserId);
		if(videoListForSegmentsByUserId.size()==0)videoListForSegmentsByUserId=null;
		
		//get full info for video list
		model.setVideoList(videoListForSegmentsByUserId);
	}
}
