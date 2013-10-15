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

import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.FacilityDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.GuestModel;

/**
 * The Class GuestLectureseriesList.
 */
public final class GuestLectureseriesList extends AbstractGuestCommand{

	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractGuestCommand#execute(javax.portlet.ActionRequest, de.uhh.l2g.model.GuestModel)
	 */
	@Override
	public void execute(ActionRequest request, GuestModel model) {
		try{model.setFacultyId(new Integer(request.getParameter("facultyId")));}catch(NumberFormatException nfe){}
		try{model.setSubFacility1Id(new Integer(request.getParameter("subFacility1Id")));}catch(NumberFormatException nfe){}

		setSubfacilities(model);

		//set pagination
		setPagination(model, request);
		
		//set videos 
		List<Video> videoList = null;
		int start=0;
		if (((PortletRequest) request).getParameter("videoSeite") == null) start = 0;
		else start = (new Integer(((PortletRequest) request).getParameter("videoSeite")) - 1) * getVideosPerSite();
		
		videoList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getLatestVideos(start, getVideosPerSite(), model.getFacilityId(),"tree1");
		model.setVideoList(videoList);
		
		model.setFacultyList(((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByTypeAndLevel("tree1", "1"));
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractGuestCommand#execute(javax.portlet.PortletRequest, de.uhh.l2g.model.GuestModel)
	 */
	@Override
	public void execute(PortletRequest request, GuestModel model) {
	
		List<Video> videoList = null;
		
		try{model.setFacultyId(new Integer(request.getParameter("facultyId")));}catch(NumberFormatException nfe){}
		try{model.setSubFacility1Id(new Integer(request.getParameter("subFacility1Id")));}catch(NumberFormatException nfe){}
		
		//set subeinrichgunen
		setSubfacilities(model);

		//set pagination
		setPagination(model, request);
		
		//set videos 
		int start=0;
		if ((request).getParameter("videoSeite") == null) start = 0;
		else start = (new Integer((request).getParameter("videoSeite")) - 1) * getVideosPerSite();
		videoList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getLatestVideos(start, getVideosPerSite(), model.getFacilityId(),"tree1");

		// get paginated results and store them in model
		model.setVideoList(videoList);

		model.setUniversityList(((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByLevel(0));
		model.setFacultyList(((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByTypeAndLevel("tree1", "1"));
	}

}
