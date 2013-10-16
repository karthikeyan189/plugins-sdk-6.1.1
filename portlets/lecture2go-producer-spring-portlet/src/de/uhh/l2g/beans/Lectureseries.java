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


/**
 * The Class Lectureseries.
 */
public class Lectureseries {

	/** The deleteable. */
	private boolean deleteable=false;
	
	/**
	 * Gets the deleteable.
	 *
	 * @return the deleteable
	 */
	public boolean getDeleteable() {
		return deleteable;
	}

	/**
	 * Sets the deleteable.
	 *
	 * @param deleteable the new deleteable
	 */
	public void setDeleteable(boolean deleteable) {
		this.deleteable = deleteable;
	}

	/** The number. */
	private String number = "";

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/** The event type. */
	private String eventType = "";

	/**
	 * Gets the event type.
	 *
	 * @return the event type
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * Sets the event type.
	 *
	 * @param eventType the new event type
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/** The name. */
	private String name = "";

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** The long desc. */
	private String longDesc = "";

	/**
	 * Gets the long desc.
	 *
	 * @return the long desc
	 */
	public String getLongDesc() {
		return longDesc;
	}

	/**
	 * Sets the long desc.
	 *
	 * @param longDesc the new long desc
	 */
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	/** The short desc. */
	private String shortDesc = "";

	/**
	 * Gets the short desc.
	 *
	 * @return the short desc
	 */
	public String getShortDesc() {
		return shortDesc;
	}

	/**
	 * Sets the short desc.
	 *
	 * @param shortDesc the new short desc
	 */
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	/** The semester name. */
	private String semesterName = "";

	/**
	 * Gets the semester name.
	 *
	 * @return the semester name
	 */
	public String getSemesterName() {
		return semesterName;
	}

	/**
	 * Sets the semester name.
	 *
	 * @param semesterName the new semester name
	 */
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	/** The language. */
	private String language = "";

	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the language.
	 *
	 * @param language the new language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/** The faculty name. */
	private String facultyName = "";

	/**
	 * Gets the faculty_name.
	 *
	 * @return the faculty_name
	 */
	public String getFaculty_name() {
		return facultyName;
	}

	/**
	 * Sets the faculty name.
	 *
	 * @param facultyName the new faculty name
	 */
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	/** The instructors string. */
	private String instructorsString = "";

	/**
	 * Gets the instructors string.
	 *
	 * @return the instructors string
	 */
	public String getInstructorsString() {
		return instructorsString;
	}

	/**
	 * Sets the instructors string.
	 *
	 * @param instructorsString the new instructors string
	 */
	public void setInstructorsString(String instructorsString) {
		this.instructorsString = instructorsString;
	}

	/** The facility id. */
	private int facilityId;

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

	/** The id. */
	private int id;

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

	/** The event category. */
	private String eventCategory = "";

	/**
	 * Gets the event category.
	 *
	 * @return the event category
	 */
	public String getEventCategory() {
		return eventCategory;
	}

	/**
	 * Sets the event category.
	 *
	 * @param eventCategory the new event category
	 */
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}

	/** The lectureseries short name. */
	private String lectureseriesShortName = "";

	/**
	 * Gets the lectureseries short name.
	 *
	 * @return the lectureseries short name
	 */
	public String getLectureseriesShortName() {
		return lectureseriesShortName;
	}

	/**
	 * Sets the lectureseries short name.
	 *
	 * @param lectureseriesShortName the new lectureseries short name
	 */
	public void setLectureseriesShortName(String lectureseriesShortName) {
		this.lectureseriesShortName = lectureseriesShortName;
	}

	/** The number of videos. */
	private int numberOfVideos;

	/**
	 * Gets the number of videos.
	 *
	 * @return the number of videos
	 */
	public int getNumberOfVideos() {
		return numberOfVideos;
	}

	/**
	 * Sets the number of videos.
	 *
	 * @param numberOfVideos the new number of videos
	 */
	public void setNumberOfVideos(int numberOfVideos) {
		this.numberOfVideos = numberOfVideos;
	}

	/** The password. */
	private String password = "";

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** The approved. */
	private boolean approved = false;

	/**
	 * Sets the approved.
	 *
	 * @param approved the new approved
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	/**
	 * Checks if is approved.
	 *
	 * @return true, if is approved
	 */
	public boolean isApproved() {
		return approved;
	}

}
