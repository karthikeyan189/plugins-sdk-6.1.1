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

import java.util.NoSuchElementException;

import org.springframework.web.portlet.bind.PortletRequestUtils;

import de.uhh.l2g.beans.Factory;
import de.uhh.l2g.beans.License;
import de.uhh.l2g.dao.FacilityDao;
import de.uhh.l2g.dao.LectureseriesDao;
import de.uhh.l2g.dao.LicenseDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.GuestModel;


/**
 * The Class AbstractGuestCommand.
 */
public abstract class AbstractGuestCommand extends Factory {

	/** The line break. */
	private Integer lineBreak;
	
	/**
	 * Gets the line break.
	 *
	 * @return the line break
	 */
	public Integer getLineBreak() {
		return lineBreak;
	}

	/**
	 * Sets the line break.
	 *
	 * @param lineBreak the line break
	 */
	public void setLineBreak(Integer lineBreak) {
		this.lineBreak = lineBreak;
	}

	/** The videos per site. */
	private Integer videosPerSite;

	/**
	 * Gets the videos per site.
	 *
	 * @return the videos per site
	 */
	public Integer getVideosPerSite() {
		return videosPerSite;
	}

	/**
	 * Sets the videos per site.
	 *
	 * @param videosPerSite the videos per site
	 */
	public void setVideosPerSite(Integer videosPerSite) {
		this.videosPerSite = videosPerSite;
	} 

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @param model the model
	 */
	public abstract void execute(ActionRequest request, GuestModel model);
	
	/**
	 * Execute.
	 *
	 * @param request the request
	 * @param model the model
	 */
	public abstract void execute(PortletRequest request, GuestModel model);

	/**
	 * Sets the licenze.
	 *
	 * @param model the licenze
	 */
	public void setLicenze(GuestModel model){
		try {
			License license = ((LicenseDao)getDaoBeanFactory().getBean("licenseDao")).getByVideoId(model.getVideo().getId()).iterator().next();
			model.setLicense(license.getType().trim());

			if (license.getType().contains("ccbyncnd")) model.setLicenseByNcNd("ccbyncnd");
			else model.setLicenseByNcNd("");

			if (license.getType().contains("ccbyncsa")) model.setLicenseByNcSa("ccbyncsa");
			else model.setLicenseByNcSa("");

			if (license.getType().contains("uhhl2go")) model.setLicenseUhhL2go("uhhl2go");
			else model.setLicenseUhhL2go("");

		} catch (NoSuchElementException nsee) {
			model.setLicense("");
		}		
	}
	
	/**
	 * Sets the subfacilities.
	 *
	 * @param model the subfacilities
	 */
	public void setSubfacilities(GuestModel model){
		if(model.getTree().equals("tree1"))_setLectureseriesSubfacilities(model);
	}
	
	/**
	 * _set lectureseries subfacilities.
	 *
	 * @param model the model
	 */
	private void _setLectureseriesSubfacilities(GuestModel model){
		//set fakuly and subfacility1 list
		if (model.getFacultyId()!= 0) {
			model.setSubFacility1List(((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByParentIdAndOpenAccessVideo(model.getFacultyId()));
			model.setFacilityId(model.getFacultyId());
		} else {
			model.setSubFacility1Id(null);
			model.setLectureseriesNumber("0");
		}
		
		//set subfacility1 and subfacility2list
		try{
			if (model.getSubFacility1Id() != 0) {
				model.setSubFacility2List(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getByFacilityIdAndOpenaccesVideos(model.getSubFacility1Id()));
				model.setFacilityId(model.getSubFacility1Id());
			}else{
				model.setSubFacility1Id(null);
			}
		}catch(Exception e){
			model.setSubFacility1Id(0);
			model.setLectureseriesNumber("0");
		}	
		
		//selected video from list
		try {
			if(model.getAction().equals("showDetailsForSelectedVideo")){
				model.setSubFacility1List(((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getByParentIdAndOpenAccessVideo(model.getFacultyId()));
				model.setSubFacility2List(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getByFacilityIdAndOpenaccesVideos(model.getSubFacility1Id()));
			}
		} catch (NullPointerException npe) {}
	
		if (model.getSubFacility1List() == null || model.getSubFacility1List().size() == 0) model.setSubFacility1ListVisible(false);
			else model.setSubFacility1ListVisible(true);
		if (model.getSubFacility2List() == null || model.getSubFacility2List().size() == 0) model.setSubFacility2ListVisible(false);
			else model.setSubFacility2ListVisible(true);
	}

	/**
	 * Sets the pagination.
	 *
	 * @param model the model
	 * @param request the request
	 */
	public void setPagination(GuestModel model, Object request){
		int numberOpenAccessLectureseries = 0;
		if(model.getTree().equals("tree1")) numberOpenAccessLectureseries = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getNumberOpenAccessLectureseries(model.getFacilityId(),"tree1");
		int pageSize = PortletRequestUtils.getIntParameter((PortletRequest) request, "pagesize", 10);
		int pageNumber = PortletRequestUtils.getIntParameter((PortletRequest) request, "pagenumber", 1);
		
		int numberSites = numberOpenAccessLectureseries / getVideosPerSite();
		int rest = numberOpenAccessLectureseries % getVideosPerSite();

		if (rest > 0 && getVideosPerSite() < numberOpenAccessLectureseries) numberSites++;
		if (getVideosPerSite() > numberOpenAccessLectureseries) numberSites = 1;

		// do pagination
		model.setPageSize(pageSize);
		model.setNumberResultPages(numberSites);
		model.setCurrentPageNumber(pageNumber);

		int rangeFirst = (((pageNumber - 1) / 3)) * 3 + 1;
		int rangeLast = Math.min((((pageNumber - 1) / 3)) * 3 + 3, model.getNumberResultPages());
		model.setPageRangeFirst(rangeFirst);
		model.setPageRangeLast(rangeLast);
		model.setHasPrev(rangeFirst > 1);
		model.setHasNext(rangeLast < model.getNumberResultPages());

		// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
	}
}
