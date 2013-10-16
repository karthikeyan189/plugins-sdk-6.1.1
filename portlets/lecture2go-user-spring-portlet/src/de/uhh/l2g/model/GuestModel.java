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

import com.liferay.portal.model.User;

import de.uhh.l2g.beans.Coordinator;
import de.uhh.l2g.util.L2goPropsUtil;



/**
 * The Class GuestModel.
 */
public class GuestModel extends Page {

	/** The liferay user. */
	private User liferayUser;
	

	/**
	 * Gets the liferay user.
	 *
	 * @return the liferay user
	 */
	public User getLiferayUser() {
		return liferayUser;
	}

	/**
	 * Sets the liferay user.
	 *
	 * @param liferayUser the new liferay user
	 */
	public void setLiferayUser(User liferayUser) {
		this.liferayUser = liferayUser;
	}

	/** The citation2go view. */
	private int citation2goView=1;
	

	/**
	 * Gets the citation2go view.
	 *
	 * @return the citation2go view
	 */
	public int getCitation2goView() {
		return citation2goView;
	}

	/**
	 * Sets the citation2go view.
	 *
	 * @param citation2goView the new citation2go view
	 */
	public void setCitation2goView(int citation2goView) {
		this.citation2goView = citation2goView;
	}

	/**
	 * Gets the clip start time.
	 *
	 * @return the clip start time
	 */
	public Integer getClipStartTime() {
		return clipStartTime;
	}

	/**
	 * Sets the clip start time.
	 *
	 * @param clipStartTime the new clip start time
	 */
	public void setClipStartTime(Integer clipStartTime) {
		this.clipStartTime = clipStartTime;
	}

	/**
	 * Gets the clip end time.
	 *
	 * @return the clip end time
	 */
	public Integer getClipEndTime() {
		return clipEndTime;
	}

	/**
	 * Sets the clip end time.
	 *
	 * @param clipEndTime the new clip end time
	 */
	public void setClipEndTime(Integer clipEndTime) {
		this.clipEndTime = clipEndTime;
	}

	/** The clip start time. */
	private Integer clipStartTime = null;
	
	/** The clip end time. */
	private Integer clipEndTime = null;
	
	/** The web root. */
	private String webRoot = L2goPropsUtil.get("lecture2go.web.root");
	
	/**
	 * Gets the web root.
	 *
	 * @return the web root
	 */
	public String getWebRoot() {
		return webRoot;
	}

	/**
	 * Sets the web root.
	 *
	 * @param webRoot the new web root
	 */
	public void setWebRoot(String webRoot) {
		this.webRoot = webRoot;
	}

	/** The faculty id. */
	private int facultyId = 0;
	
	/**
	 * Gets the faculty id.
	 *
	 * @return the faculty id
	 */
	public int getFacultyId() {
		return facultyId;
	}

	/**
	 * Sets the faculty id.
	 *
	 * @param facultyId the new faculty id
	 */
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	/** The tree. */
	private String tree;
	
	/**
	 * Gets the tree.
	 *
	 * @return the tree
	 */
	public String getTree() {
		return tree;
	}

	/**
	 * Sets the tree.
	 *
	 * @param tree the new tree
	 */
	public void setTree(String tree) {
		this.tree = tree;
	}

	
	/** The license. */
	private String license;

	/**
	 * Gets the license.
	 *
	 * @return the license
	 */
	
	public String getLicense() {
		return license;
	}

	/**
	 * Sets the license.
	 *
	 * @param license the new license
	 */
	public void setLicense(String license) {
		this.license = license;
	}

	/**
	 * Gets the license uhh l2go.
	 *
	 * @return the license uhh l2go
	 */
	public String getLicenseUhhL2go() {
		return licenseUhhL2go;
	}

	/**
	 * Sets the license uhh l2go.
	 *
	 * @param licenseUhhL2go the new license uhh l2go
	 */
	public void setLicenseUhhL2go(String licenseUhhL2go) {
		this.licenseUhhL2go = licenseUhhL2go;
	}

	/**
	 * Gets the license by nc sa.
	 *
	 * @return the license by nc sa
	 */
	public String getLicenseByNcSa() {
		return licenseByNcSa;
	}

	/**
	 * Sets the license by nc sa.
	 *
	 * @param licenseByNcSa the new license by nc sa
	 */
	public void setLicenseByNcSa(String licenseByNcSa) {
		this.licenseByNcSa = licenseByNcSa;
	}

	/**
	 * Gets the license by nc nd.
	 *
	 * @return the license by nc nd
	 */
	public String getLicenseByNcNd() {
		return licenseByNcNd;
	}

	/**
	 * Sets the license by nc nd.
	 *
	 * @param licenseByNcNd the new license by nc nd
	 */
	public void setLicenseByNcNd(String licenseByNcNd) {
		this.licenseByNcNd = licenseByNcNd;
	}

	/** The license uhh l2go. */
	private String licenseUhhL2go;

	/** The license by nc sa. */
	private String licenseByNcSa;

	/** The license by nc nd. */
	private String licenseByNcNd;

	/** The user agent. */
	private String userAgent;

	/**
	 * Gets the user agent.
	 *
	 * @return the user agent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * Sets the user agent.
	 *
	 * @param userAgent the new user agent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/** The mp3 download. */
	private String mp3Download;

	/**
	 * Gets the mp3 download.
	 *
	 * @return the mp3 download
	 */
	public String getMp3Download() {
		return mp3Download;
	}

	/**
	 * Sets the mp3 download.
	 *
	 * @param mp3Download the new mp3 download
	 */
	public void setMp3Download(String mp3Download) {
		this.mp3Download = mp3Download;
	}

	/**
	 * Gets the video download.
	 *
	 * @return the video download
	 */
	public String getVideoDownload() {
		return videoDownload;
	}

	/**
	 * Sets the video download.
	 *
	 * @param videoDownload the new video download
	 */
	public void setVideoDownload(String videoDownload) {
		this.videoDownload = videoDownload;
	}

	/**
	 * Gets the audio download.
	 *
	 * @return the audio download
	 */
	public String getAudioDownload() {
		return audioDownload;
	}

	/**
	 * Sets the audio download.
	 *
	 * @param audioDownload the new audio download
	 */
	public void setAudioDownload(String audioDownload) {
		this.audioDownload = audioDownload;
	}

	/**
	 * Gets the pdf download.
	 *
	 * @return the pdf download
	 */
	public String getPdfDownload() {
		return pdfDownload;
	}

	/**
	 * Sets the pdf download.
	 *
	 * @param pdfDownload the new pdf download
	 */
	public void setPdfDownload(String pdfDownload) {
		this.pdfDownload = pdfDownload;
	}

	/**
	 * Gets the m4v download.
	 *
	 * @return the m4v download
	 */
	public String getM4vDownload() {
		return m4vDownload;
	}

	/**
	 * Sets the m4v download.
	 *
	 * @param download the new m4v download
	 */
	public void setM4vDownload(String download) {
		m4vDownload = download;
	}

	/**
	 * Gets the m4a download.
	 *
	 * @return the m4a download
	 */
	public String getM4aDownload() {
		return m4aDownload;
	}

	/**
	 * Sets the m4a download.
	 *
	 * @param download the new m4a download
	 */
	public void setM4aDownload(String download) {
		m4aDownload = download;
	}

	/** The video stream. */
	private String videoStream;
	
	/**
	 * Gets the video stream.
	 *
	 * @return the video stream
	 */
	public String getVideoStream() {
		return videoStream;
	}

	/**
	 * Sets the video stream.
	 *
	 * @param videoStream the new video stream
	 */
	public void setVideoStream(String videoStream) {
		this.videoStream = videoStream;
	}

	/** The video download. */
	private String videoDownload;

	/** The audio download. */
	private String audioDownload;

	/** The pdf download. */
	private String pdfDownload;

	/** The m4v download. */
	private String m4vDownload;

	/** The m4a download. */
	private String m4aDownload;

	/** The mp4 download. */
	private String mp4Download;

	/**
	 * Gets the mp4 download.
	 *
	 * @return the mp4 download
	 */
	public String getMp4Download() {
		return mp4Download;
	}

	/**
	 * Sets the mp4 download.
	 *
	 * @param mp4Download the new mp4 download
	 */
	public void setMp4Download(String mp4Download) {
		this.mp4Download = mp4Download;
	}

	/** The mobile device. */
	private boolean mobileDevice;

	/**
	 * Checks if is mobile device.
	 *
	 * @return true, if is mobile device
	 */
	public boolean isMobileDevice() {
		return mobileDevice;
	}

	/**
	 * Sets the mobile device.
	 *
	 * @param mobileDevice the new mobile device
	 */
	public void setMobileDevice(boolean mobileDevice) {
		this.mobileDevice = mobileDevice;
	}

	/** The download allawed. */
	private boolean downloadAllawed = false;

	/**
	 * Checks if is download allawed.
	 *
	 * @return true, if is download allawed
	 */
	public boolean isDownloadAllawed() {
		return downloadAllawed;
	}

	/**
	 * Sets the download allawed.
	 *
	 * @param downloadAllawed the new download allawed
	 */
	public void setDownloadAllawed(boolean downloadAllawed) {
		this.downloadAllawed = downloadAllawed;
	}

	/** The video id. */
	private int videoId = 0;

	/**
	 * Gets the video id.
	 *
	 * @return the video id
	 */
	public int getVideoId() {
		return videoId;
	}

	/**
	 * Sets the video id.
	 *
	 * @param videoId the new video id
	 */
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	/** The success. */
	private boolean success = false;

	/**
	 * Checks if is success.
	 *
	 * @return true, if is success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets the success.
	 *
	 * @param success the new success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}


	/** The mp3 file. */
	private String mp3File = "";

	/** The mp4 file. */
	private String mp4File = "";

	/**
	 * Gets the mp4 file.
	 *
	 * @return the mp4 file
	 */
	public String getMp4File() {
		return mp4File;
	}

	/**
	 * Sets the mp4 file.
	 *
	 * @param mp4File the new mp4 file
	 */
	public void setMp4File(String mp4File) {
		this.mp4File = mp4File;
	}

	/**
	 * Gets the mp3 file.
	 *
	 * @return the mp3 file
	 */
	public String getMp3File() {
		return mp3File;
	}

	/**
	 * Sets the mp3 file.
	 *
	 * @param mp3File the new mp3 file
	 */
	public void setMp3File(String mp3File) {
		this.mp3File = mp3File;
	}

	/** The pdf file. */
	private String pdfFile = "";

	/**
	 * Gets the pdf file.
	 *
	 * @return the pdf file
	 */
	public String getPdfFile() {
		return pdfFile;
	}

	/**
	 * Sets the pdf file.
	 *
	 * @param pdfFile the new pdf file
	 */
	public void setPdfFile(String pdfFile) {
		this.pdfFile = pdfFile;
	}

	/** The m4v file. */
	private String m4vFile;

	/**
	 * Gets the m4v file.
	 *
	 * @return the m4v file
	 */
	public String getM4vFile() {
		return m4vFile;
	}

	/**
	 * Sets the m4v file.
	 *
	 * @param m4vFile the new m4v file
	 */
	public void setM4vFile(String m4vFile) {
		this.m4vFile = m4vFile;
	}

	/** The url. */
	private String url = "";

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getURL() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setURL(String url) {
		this.url = url;
	}

	/** The video seiten. */
	private int videoSeiten;

	/**
	 * Gets the video seiten.
	 *
	 * @return the video seiten
	 */
	public int getVideoSeiten() {
		return videoSeiten;
	}

	/**
	 * Sets the video seiten.
	 *
	 * @param videoSeiten the new video seiten
	 */
	public void setVideoSeiten(int videoSeiten) {
		this.videoSeiten = videoSeiten;
	}

	/** The commsy embed. */
	private boolean commsyEmbed = false;

	/**
	 * Checks if is commsy embed.
	 *
	 * @return true, if is commsy embed
	 */
	public boolean isCommsyEmbed() {
		return commsyEmbed;
	}

	/**
	 * Sets the commsy embed.
	 *
	 * @param commsyEmbed the new commsy embed
	 */
	public void setCommsyEmbed(boolean commsyEmbed) {
		this.commsyEmbed = commsyEmbed;
	}

	/** The file. */
	private String file = "";

	/**
	 * Gets the m4a file.
	 *
	 * @return the m4a file
	 */
	public String getM4aFile() {
		return file;
	}

	/**
	 * Sets the m4a file.
	 *
	 * @param file the new m4a file
	 */
	public void setM4aFile(String file) {
		this.file = file;
	}

	/** The domain url. */
	private String domainURL = "";

	/**
	 * Gets the domain url.
	 *
	 * @return the domain url
	 */
	public String getDomainURL() {
		return domainURL;
	}

	/**
	 * Sets the domain url.
	 *
	 * @param domainURL the new domain url
	 */
	public void setDomainURL(String domainURL) {
		this.domainURL = domainURL;
	}

	/** The download server url. */
	private String downloadServerURL = "";

	/**
	 * Gets the download server url.
	 *
	 * @return the download server url
	 */
	public String getDownloadServerURL() {
		return downloadServerURL;
	}

	/**
	 * Sets the download server url.
	 *
	 * @param downloadServerURL the new download server url
	 */
	public void setDownloadServerURL(String downloadServerURL) {
		this.downloadServerURL = downloadServerURL;
	}

	/** The action. */
	private String action = "";

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Checks if is user is student.
	 *
	 * @return true, if is user is student
	 */
	public boolean isUserIsStudent() {
		return userIsStudent;
	}

	/**
	 * Sets the user is student.
	 *
	 * @param userIsStudent the new user is student
	 */
	public void setUserIsStudent(boolean userIsStudent) {
		this.userIsStudent = userIsStudent;
	}

	/** The user is student. */
	private boolean userIsStudent;

	/** The lectureseries id. */
	private int lectureseriesId;

	/**
	 * Gets the lectureseries id.
	 *
	 * @return the lectureseries id
	 */
	public int getLectureseriesId() {
		return lectureseriesId;
	}

	/**
	 * Sets the lectureseries id.
	 *
	 * @param lectureseriesId the new lectureseries id
	 */
	public void setLectureseriesId(int lectureseriesId) {
		this.lectureseriesId = lectureseriesId;
	}

	/** The facility id. */
	private int facilityId=0;

	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the new facility id
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	/** The number. */
	private String number = "";

	/**
	 * Gets the lectureseries number.
	 *
	 * @return the lectureseries number
	 */
	public String getLectureseriesNumber() {
		return number;
	}

	/**
	 * Sets the lectureseries number.
	 *
	 * @param number the new lectureseries number
	 */
	public void setLectureseriesNumber(String number) {
		this.number = number;
	}

	/** The path. */
	private String path = "";

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/** The coordinator. */
	private Coordinator coordinator;


	/**
	 * Gets the coordinator.
	 *
	 * @return the coordinator
	 */
	public Coordinator getCoordinator() {
		return coordinator;
	}

	/**
	 * Sets the coordinator.
	 *
	 * @param coordinator the new coordinator
	 */
	public void setCoordinator(Coordinator coordinator) {
		this.coordinator = coordinator;
	}
	
	/** The email to. */
	private String emailTo;
	
	/**
	 * Gets the email to.
	 *
	 * @return the email to
	 */
	public String getEmailTo() {
		return emailTo;
	}

	/**
	 * Sets the email to.
	 *
	 * @param emailTo the new email to
	 */
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	
}
