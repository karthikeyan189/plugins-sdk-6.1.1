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
package de.uhh.l2g.beans;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * The Class ContactFile.
 */
public class ContactFile {

	/** The video list. */
	private List<Video> videoList;

	/** The title. */
	private String title;

	/** The url. */
	private String url = "";

	/** The resolution. */
	private String resolution = "";

	/** The duration. */
	private int duration = 0;

	/** The streamer. */
	private String streamer = "";

	/** The container format. */
	private String containerFormat = "";

	/** The contact file. */
	private MultipartFile contactFile;

	/** The id. */
	private int id = 0;

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
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
	 * Gets the video list.
	 *
	 * @return the video list
	 */
	public List<Video> getVideoList() {
		return this.videoList;
	}

	/**
	 * Gets the container format.
	 *
	 * @return the container format
	 */
	public String getContainerFormat() {
		return containerFormat;
	}

	/**
	 * Sets the container format.
	 *
	 * @param containerFormat the new container format
	 */
	public void setContainerFormat(String containerFormat) {
		this.containerFormat = containerFormat;
	}

	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the aufloesung.
	 *
	 * @return the aufloesung
	 */
	public String getAufloesung() {
		return resolution;
	}

	/**
	 * Sets the aufloesung.
	 *
	 * @param resolution the new aufloesung
	 */
	public void setAufloesung(String resolution) {
		this.resolution = resolution;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Gets the streamer.
	 *
	 * @return the streamer
	 */
	public String getStreamer() {
		return streamer;
	}

	/**
	 * Sets the streamer.
	 *
	 * @param streamer the new streamer
	 */
	public void setStreamer(String streamer) {
		this.streamer = streamer;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the contact file.
	 *
	 * @return the contact file
	 */
	public MultipartFile getContactFile() {
		return contactFile;
	}

	/**
	 * Sets the contact file.
	 *
	 * @param contactFile the new contact file
	 */
	public void setContactFile(MultipartFile contactFile) {
		this.contactFile = contactFile;
	}
}
