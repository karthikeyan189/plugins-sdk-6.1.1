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
package de.uhh.l2g.model;

import java.util.Date;
import java.util.List;

import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.Host;
import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.beans.Mark;
import de.uhh.l2g.beans.Metadata;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.Video;

/**
 * The Class Objects.
 */
public class Objects {
	
	/** The create date. */
	private Date createDate;

	/** The last login date. */
	private Date lastLoginDate;

	/**
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the creates the date.
	 *
	 * @param createDate the new creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the last login date.
	 *
	 * @return the last login date
	 */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * Sets the last login date.
	 *
	 * @param lastLoginDate the new last login date
	 */
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * Gets the faculty.
	 *
	 * @return the faculty
	 */
	public Facility getFaculty() {
		return faculty;
	}

	/**
	 * Sets the faculty.
	 *
	 * @param faculty the new faculty
	 */
	public void setFaculty(Facility faculty) {
		this.faculty = faculty;
	}

	/** The faculty. */
	private Facility faculty;
	
	/**
	 * Gets the lectureseries list.
	 *
	 * @return the lectureseries list
	 */
	public List<Lectureseries> getLectureseriesList() {
		return lectureseriesList;
	}

	/** The lectureseries list. */
	private List<Lectureseries> lectureseriesList;


	/** The host list. */
	private List<Host> hostList;

	/**
	 * Gets the host list.
	 *
	 * @return the host list
	 */
	public List<Host> getHostList() {
		return hostList;
	}

	/**
	 * Sets the host list.
	 *
	 * @param hostList the new host list
	 */
	public void setHostList(List<Host> hostList) {
		this.hostList = hostList;
	}
	
	/**
	 * Sets the lectureseries list.
	 *
	 * @param lectureseriesList the new lectureseries list
	 */
	public void setLectureseriesList(List<Lectureseries> lectureseriesList) {
		this.lectureseriesList = lectureseriesList;
	}
	
	/** The video hit list. */
	private List<Video> videoHitList;
	
	/**
	 * Sets the video hit list.
	 *
	 * @param videoHitList the new video hit list
	 */
	public void setVideoHitList(List<Video> videoHitList) {
		this.videoHitList = videoHitList;
	}
	
	/**
	 * Gets the video hit list.
	 *
	 * @return the video hit list
	 */
	public List<Video> getVideoHitList() {
		return this.videoHitList;
	}
	
	/** The video new list. */
	private List<Video> videoNewList;
	
	/**
	 * Sets the video new list.
	 *
	 * @param VideoNewList the new video new list
	 */
	public void setVideoNewList(List<Video> VideoNewList) {
		this.videoNewList = VideoNewList;
	}
	
	/**
	 * Gets the video new list.
	 *
	 * @return the video new list
	 */
	public List<Video> getVideoNewList() {
		return this.videoNewList;
	}
	
	/** The video fav list. */
	private List<Video> videoFavList;
	
	/**
	 * Sets the video fav list.
	 *
	 * @param videoFavList the new video fav list
	 */
	public void setVideoFavList(List<Video> videoFavList) {
		this.videoFavList = videoFavList;
	}
	
	/**
	 * Gets the video fav list.
	 *
	 * @return the video fav list
	 */
	public List<Video> getVideoFavList() {
		return this.videoFavList;
	}
	
	/** The video list. */
	private List<Video> videoList;
	

	/** The more videos hit list. */
	private List<?> moreVideosHitList;

	/**
	 * Gets the more videos hit list.
	 *
	 * @return the more videos hit list
	 */
	public List<?> getMoreVideosHitList() {
		return moreVideosHitList;
	}

	/**
	 * Sets the more videos hit list.
	 *
	 * @param moreVideosHitList the new more videos hit list
	 */
	public void setMoreVideosHitList(List<?> moreVideosHitList) {
		this.moreVideosHitList = moreVideosHitList;
	}

	/** The related videos list. */
	private List<Video> relatedVideosList;

	/**
	 * Gets the video list.
	 *
	 * @return the video list
	 */
	public List<Video> getVideoList() {
		return videoList;
	}

	/**
	 * Sets the video list.
	 *
	 * @param videoList the new video list
	 */
	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}

	/**
	 * Gets the related videos list.
	 *
	 * @return the related videos list
	 */
	public List<Video> getRelatedVideosList() {
		return relatedVideosList;
	}

	/**
	 * Sets the related videos list.
	 *
	 * @param relatedVideosList the new related videos list
	 */
	public void setRelatedVideosList(List<Video> relatedVideosList) {
		this.relatedVideosList = relatedVideosList;
	}
	

	/** The university list. */
	private List<Facility> universityList;

	/**
	 * Gets the university list.
	 *
	 * @return the university list
	 */
	public List<Facility> getUniversityList() {
		return universityList;
	}

	/**
	 * Sets the university list.
	 *
	 * @param universityList the new university list
	 */
	public void setUniversityList(List<Facility> universityList) {
		this.universityList = universityList;
	}
	
	/** The video. */
	private Video video;

	/**
	 * Gets the video.
	 *
	 * @return the video
	 */
	public Video getVideo() {
		return video;
	}

	/**
	 * Sets the video.
	 *
	 * @param video the new video
	 */
	public void setVideo(Video video) {
		this.video = video;
	}
	
	/** The lectureseries. */
	private Lectureseries lectureseries;



	/** The faculty list. */
	private List<Facility> facultyList;

	/** The host. */
	private Host host;
	
	/** The mark list. */
	private List<Mark> markList;
	
	/** The metadata. */
	private Metadata metadata;
	
	/** The producer. */
	private Producer producer;	
	
	/** The producer list. */
	private List<Producer> producerList;
	
	/** The segment list. */
	private List<Mark> segmentList;

	/**
	 * Gets the sub facility1 id.
	 *
	 * @return the sub facility1 id
	 */
	public Integer getSubFacility1Id() {
		return subFacility1Id;
	}

	/**
	 * Sets the sub facility1 id.
	 *
	 * @param subFacility1Id the new sub facility1 id
	 */
	public void setSubFacility1Id(Integer subFacility1Id) {
		this.subFacility1Id = subFacility1Id;
	}

	/**
	 * Gets the sub facility2 id.
	 *
	 * @return the sub facility2 id
	 */
	public Integer getSubFacility2Id() {
		return subFacility2Id;
	}

	/**
	 * Sets the sub facility2 id.
	 *
	 * @param subFacility2Id the new sub facility2 id
	 */
	public void setSubFacility2Id(Integer subFacility2Id) {
		this.subFacility2Id = subFacility2Id;
	}

	/**
	 * Checks if is sub facility2 list visible.
	 *
	 * @return true, if is sub facility2 list visible
	 */
	public boolean isSubFacility2ListVisible() {
		return subFacility2ListVisible;
	}

	/**
	 * Sets the sub facility2 list visible.
	 *
	 * @param subFacility2ListVisible the new sub facility2 list visible
	 */
	public void setSubFacility2ListVisible(boolean subFacility2ListVisible) {
		this.subFacility2ListVisible = subFacility2ListVisible;
	}

	/**
	 * Checks if is sub facility1 list visible.
	 *
	 * @return true, if is sub facility1 list visible
	 */
	public boolean isSubFacility1ListVisible() {
		return subFacility1ListVisible;
	}

	/**
	 * Sets the sub facility1 list visible.
	 *
	 * @param subFacility1ListVisible the new sub facility1 list visible
	 */
	public void setSubFacility1ListVisible(boolean subFacility1ListVisible) {
		this.subFacility1ListVisible = subFacility1ListVisible;
	}

	/** The sub facility1 id. */
	private Integer subFacility1Id;
	
	/** The sub facility2 id. */
	private Integer subFacility2Id;
	
	/** The sub facility2 list visible. */
	private boolean subFacility2ListVisible;
	
	/** The sub facility1 list visible. */
	private boolean subFacility1ListVisible;

	/**
	 * Gets the faculty list.
	 *
	 * @return the faculty list
	 */
	public List<Facility> getFacultyList() {
		return facultyList;
	}

	/**
	 * Sets the faculty list.
	 *
	 * @param facultyList the new faculty list
	 */
	public void setFacultyList(List<Facility> facultyList) {
		this.facultyList = facultyList;
	}

	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public Host getHost() {
		return host;
	}

	/**
	 * Sets the host.
	 *
	 * @param host the new host
	 */
	public void setHost(Host host) {
		this.host = host;
	}

	/**
	 * Gets the mark list.
	 *
	 * @return the mark list
	 */
	public List<Mark> getMarkList() {
		return markList;
	}

	/**
	 * Sets the mark list.
	 *
	 * @param markList the new mark list
	 */
	public void setMarkList(List<Mark> markList) {
		this.markList = markList;
	}

	/**
	 * Gets the metadata.
	 *
	 * @return the metadata
	 */
	public Metadata getMetadata() {
		return metadata;
	}

	/**
	 * Sets the metadata.
	 *
	 * @param metadata the new metadata
	 */
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	/**
	 * Gets the producer.
	 *
	 * @return the producer
	 */
	public Producer getProducer() {
		return producer;
	}

	/**
	 * Sets the producer.
	 *
	 * @param producer the new producer
	 */
	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	/**
	 * Gets the producer list.
	 *
	 * @return the producer list
	 */
	public List<Producer> getProducerList() {
		return producerList;
	}

	/**
	 * Sets the producer list.
	 *
	 * @param producerList the new producer list
	 */
	public void setProducerList(List<Producer> producerList) {
		this.producerList = producerList;
	}

	/**
	 * Gets the segment list.
	 *
	 * @return the segment list
	 */
	public List<Mark> getSegmentList() {
		return segmentList;
	}

	/**
	 * Sets the segment list.
	 *
	 * @param segmentList the new segment list
	 */
	public void setSegmentList(List<Mark> segmentList) {
		this.segmentList = segmentList;
	}

	/**
	 * Gets the lectureseries.
	 *
	 * @return the lectureseries
	 */
	public Lectureseries getLectureseries() {
		return lectureseries;
	}
	
	/**
	 * Sets the lectureseries.
	 *
	 * @param lectureseries the new lectureseries
	 */
	public void setLectureseries(Lectureseries lectureseries) {
		this.lectureseries = lectureseries;
	}

	/** The sub facility1 list. */
	private List<Facility> subFacility1List;

	/**
	 * Gets the sub facility1 list.
	 *
	 * @return the sub facility1 list
	 */
	public List<Facility> getSubFacility1List() {
		return subFacility1List;
	}

	/**
	 * Sets the sub facility1 list.
	 *
	 * @param subFacility1List the new sub facility1 list
	 */
	public void setSubFacility1List(List<Facility> subFacility1List) {
		this.subFacility1List = subFacility1List;
	}

	/** The sub facility2 list. */
	private List<Lectureseries> subFacility2List;

	/**
	 * Gets the sub facility2 list.
	 *
	 * @return the sub facility2 list
	 */
	public List<Lectureseries> getSubFacility2List() {
		return subFacility2List;
	}

	/**
	 * Sets the sub facility2 list.
	 *
	 * @param list the new sub facility2 list
	 */
	public void setSubFacility2List(List<Lectureseries> list) {
		this.subFacility2List = list;
	}
	
	
	
	
	
}
