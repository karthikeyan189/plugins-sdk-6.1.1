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

import java.util.List;

import de.uhh.l2g.beans.Mark;

/**
 * The Class StudentModel.
 */
public class StudentModel extends GuestModel {
	
	/**
	 * Gets the time hhb.
	 *
	 * @return the time hhb
	 */
	public String getTimeHHB() {
		return timeHHB;
	}

	/**
	 * Sets the time hhb.
	 *
	 * @param timeHHB the new time hhb
	 */
	public void setTimeHHB(String timeHHB) {
		this.timeHHB = timeHHB;
	}

	/**
	 * Gets the time mmb.
	 *
	 * @return the time mmb
	 */
	public String getTimeMMB() {
		return timeMMB;
	}

	/**
	 * Sets the time mmb.
	 *
	 * @param timeMMB the new time mmb
	 */
	public void setTimeMMB(String timeMMB) {
		this.timeMMB = timeMMB;
	}

	/**
	 * Gets the time ssb.
	 *
	 * @return the time ssb
	 */
	public String getTimeSSB() {
		return timeSSB;
	}

	/**
	 * Sets the time ssb.
	 *
	 * @param timeSSB the new time ssb
	 */
	public void setTimeSSB(String timeSSB) {
		this.timeSSB = timeSSB;
	}

	/**
	 * Gets the time mme.
	 *
	 * @return the time mme
	 */
	public String getTimeMME() {
		return timeMME;
	}

	/**
	 * Sets the time mme.
	 *
	 * @param timeMME the new time mme
	 */
	public void setTimeMME(String timeMME) {
		this.timeMME = timeMME;
	}

	/**
	 * Gets the time hhe.
	 *
	 * @return the time hhe
	 */
	public String getTimeHHE() {
		return timeHHE;
	}

	/**
	 * Sets the time hhe.
	 *
	 * @param timeHHE the new time hhe
	 */
	public void setTimeHHE(String timeHHE) {
		this.timeHHE = timeHHE;
	}

	/**
	 * Gets the time sse.
	 *
	 * @return the time sse
	 */
	public String getTimeSSE() {
		return timeSSE;
	}

	/**
	 * Sets the time sse.
	 *
	 * @param timeSSE the new time sse
	 */
	public void setTimeSSE(String timeSSE) {
		this.timeSSE = timeSSE;
	}

	/**
	 * Gets the time hh.
	 *
	 * @return the time hh
	 */
	public String getTimeHH() {
		return timeHH;
	}

	/**
	 * Sets the time hh.
	 *
	 * @param timeHH the new time hh
	 */
	public void setTimeHH(String timeHH) {
		this.timeHH = timeHH;
	}

	/**
	 * Gets the time mm.
	 *
	 * @return the time mm
	 */
	public String getTimeMM() {
		return timeMM;
	}

	/**
	 * Sets the time mm.
	 *
	 * @param timeMM the new time mm
	 */
	public void setTimeMM(String timeMM) {
		this.timeMM = timeMM;
	}

	/**
	 * Gets the time ss.
	 *
	 * @return the time ss
	 */
	public String getTimeSS() {
		return timeSS;
	}

	/**
	 * Sets the time ss.
	 *
	 * @param timeSS the new time ss
	 */
	public void setTimeSS(String timeSS) {
		this.timeSS = timeSS;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Checks if is chapter.
	 *
	 * @return true, if is chapter
	 */
	public boolean isChapter() {
		return chapter;
	}

	/**
	 * Sets the chapter.
	 *
	 * @param chapter the new chapter
	 */
	public void setChapter(boolean chapter) {
		this.chapter = chapter;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.Objects#getSegmentList()
	 */
	/**
	 * Gets the segment list.
	 *
	 * @return the segment list
	 */
	@Override
	public List<Mark> getSegmentList() {
		return segmentList;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.Objects#setSegmentList(java.util.List)
	 */
	/**
	 * Sets the segment list.
	 *
	 * @param segmentList the new segment list
	 */
	@Override
	public void setSegmentList(List<Mark> segmentList) {
		this.segmentList = segmentList;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#getDomainURL()
	 */
	/**
	 * Gets the domain url.
	 *
	 * @return the domain url
	 */
	@Override
	public String getDomainURL() {
		return domainURL;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#setDomainURL(java.lang.String)
	 */
	/**
	 * Sets the domain url.
	 *
	 * @param domainURL the new domain url
	 */
	@Override
	public void setDomainURL(String domainURL) {
		this.domainURL = domainURL;
	}

	/**
	 * Gets the current segment timepoint.
	 *
	 * @return the current segment timepoint
	 */
	public String getCurrentSegmentTimepoint() {
		return currentSegmentTimepoint;
	}

	/**
	 * Sets the current segment timepoint.
	 *
	 * @param currentSegmentTimepoint the new current segment timepoint
	 */
	public void setCurrentSegmentTimepoint(String currentSegmentTimepoint) {
		this.currentSegmentTimepoint = currentSegmentTimepoint;
	}

	/** The time hhb. */
	private String timeHHB = "";

	/** The time mmb. */
	private String timeMMB = "";

	/** The time ssb. */
	private String timeSSB = "";

	/** The time mme. */
	private String timeMME = "";

	/** The time hhe. */
	private String timeHHE = "";

	/** The time sse. */
	private String timeSSE = "";

	/** The time hh. */
	private String timeHH = "";

	/** The time mm. */
	private String timeMM = "";

	/** The time ss. */
	private String timeSS = "";

	/** The time. */
	private String time = "";

	/** The duration. */
	private String duration = "";

	/** The chapter. */
	private boolean chapter;

	/** The segment list. */
	private List<Mark> segmentList;

	/** The domain url. */
	private String domainURL = "";

	/** The current segment timepoint. */
	private String currentSegmentTimepoint = "";
	
	/** The id. */
	private long id;

	/** The id num. */
	private String idNum;

	/** The screen name. */
	private String screenName;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The email address. */
	private String emailAddress;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param l the new id
	 */
	public void setId(long l) {
		this.id = l;
	}

	/**
	 * Gets the id num.
	 *
	 * @return the id num
	 */
	public String getIdNum() {
		return idNum;
	}

	/**
	 * Sets the id num.
	 *
	 * @param idNum the new id num
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	/**
	 * Gets the screen name.
	 *
	 * @return the screen name
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * Sets the screen name.
	 *
	 * @param screenName the new screen name
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/** The student authenticated. */
	private boolean studentAuthenticated;

	/**
	 * Checks if is student authenticated.
	 *
	 * @return true, if is student authenticated
	 */
	public boolean isStudentAuthenticated() {
		return studentAuthenticated;
	}

	/** The secure url. */
	private String secureUrl = "";

	/**
	 * Gets the secure url.
	 *
	 * @return the secure url
	 */
	public String getSecureUrl() {
		return secureUrl;
	}

	/**
	 * Sets the secure url.
	 *
	 * @param secureUrl the new secure url
	 */
	public void setSecureUrl(String secureUrl) {
		this.secureUrl = secureUrl;
	}

	/** The pwd check. */
	private boolean pwdCheck;

	/**
	 * Checks if is pwd check.
	 *
	 * @return true, if is pwd check
	 */
	public boolean isPwdCheck() {
		return pwdCheck;
	}

	/**
	 * Sets the student authenticated.
	 *
	 * @param studentAuthenticated the new student authenticated
	 */
	public void setStudentAuthenticated(boolean studentAuthenticated) {
		this.studentAuthenticated = studentAuthenticated;
	}

	/**
	 * Sets the pwd check.
	 *
	 * @param pwdCheck the new pwd check
	 */
	public void setPwdCheck(boolean pwdCheck) {
		this.pwdCheck = pwdCheck;
	}

	/** The time start. */
	private String timeStart = "";

	/**
	 * Gets the time start.
	 *
	 * @return the time start
	 */
	public String getTimeStart() {
		return timeStart;
	}

	/**
	 * Sets the time start.
	 *
	 * @param timeStart the new time start
	 */
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	/** The time end. */
	private String timeEnd = "";

	/**
	 * Gets the time end.
	 *
	 * @return the time end
	 */
	public String getTimeEnd() {
		return timeEnd;
	}

	/**
	 * Sets the time end.
	 *
	 * @param timeEnd the new time end
	 */
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

}
