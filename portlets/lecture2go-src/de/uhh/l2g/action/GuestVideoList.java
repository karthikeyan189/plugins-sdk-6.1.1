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

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;

import org.springframework.beans.BeansException;

import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.FacilityDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.GuestModel;
import de.uhh.l2g.util.InstallManager;

/**
 * The Class GuestVideoList.
 */
public final class GuestVideoList extends AbstractGuestCommand{

	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractGuestCommand#execute(javax.portlet.ActionRequest, de.uhh.l2g.model.GuestModel)
	 */
	@Override
	public void execute(ActionRequest request, GuestModel model) {
		//
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractGuestCommand#execute(javax.portlet.PortletRequest, de.uhh.l2g.model.GuestModel)
	 */
	@Override
	public void execute(PortletRequest request, GuestModel model) {
		List<Video> videoList = null;
		List<Video> videoHitList = null;
		List<Video> videoFavList = null;
		
		//system setum
		try {
			((InstallManager)getUtilityBeanFactory().getBean("installManager")).l2goSetup();
		} catch (BeansException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		List<Facility> universitaetList = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByLevel(0);
		List<Facility> facultyList = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByTypeAndLevel("tree1", "1");
		
		try{model.setFacultyId(new Integer(request.getParameter("facultyId")));}catch(NumberFormatException nfe){}
		try{model.setSubFacility1Id(new Integer(request.getParameter("subFacility1Id")));}catch(NumberFormatException nfe){}
		
		try{setVideosPerSite(new Integer(request.getParameter("aktuell")));
		}catch(Exception e){}
		
		//set subeinrichgunen
		setSubfacilities(model);

		//set videos 
		int start=0;
		if ((request).getParameter("videoSeite") == null) start = 0;
		else start = (new Integer((request).getParameter("videoSeite")) - 1) * getVideosPerSite();
		try{videoList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getLatestVideos(start, getVideosPerSite() , model.getFacilityId(),"all");}
		catch(Exception e){}
		
		// New Filter Video Hit List = Most Clicks
		videoHitList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getPopularVideos(start, getVideosPerSite(), model.getFacilityId());
		
		// Fav List -> Zur Zeit noch ZUFALLSLISTE!
		videoFavList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getPopularVideosRnd(model.getFacilityId(), getVideosPerSite());

		model.setVideoList(videoList);
		model.setVideoHitList(videoHitList);
		model.setVideoFavList(videoFavList);
		
		model.setUniversityList(universitaetList);
		model.setFacultyList(facultyList);
		
	}

	/**
	 * Update facility id in table video.
	 */
	@SuppressWarnings("unused")
	private void updateFacilityIdInTableVideo(){
		//get all Videos
		List<Video> videoList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getAll();
		ListIterator<Video> vIt = videoList.listIterator();
		while(vIt.hasNext()){
			Video v = vIt.next();
			Producer p = v.getObjectProducer();
			try{
				((VideoDao)getDaoBeanFactory().getBean("videoDao")).updateFacilityIdInTableById( v.getId(), p.getFacilityId());
			}catch(Exception e){
				int i = 0;
				i++;
			}
		}
	}

}
