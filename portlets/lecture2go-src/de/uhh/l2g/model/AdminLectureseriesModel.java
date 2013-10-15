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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * The Class AdminLectureseriesModel.
 */
@SuppressWarnings("serial")
public class AdminLectureseriesModel extends AdminModel implements Serializable {

	/** The lectureseries name. */
	private String lectureseriesName; // lectureseriesName

	/** The lectureseries number. */
	private String lectureseriesNumber; // lectureseriesName
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#getLectureseriesNumber()
	 */
	/**
	 * Gets the lectureseries number.
	 *
	 * @return the lectureseries number
	 */
	public String getLectureseriesNumber() {
		return lectureseriesNumber;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#setLectureseriesNumber(java.lang.String)
	 */
	/**
	 * Sets the lectureseries number.
	 *
	 * @param lectureseriesNumber the new lectureseries number
	 */
	public void setLectureseriesNumber(String lectureseriesNumber) {
		this.lectureseriesNumber = lectureseriesNumber;
	}

	/** The event type. */
	private String eventType; // eventType

	/** The event category. */
	private String eventCategory; // eventCategory

	/** The short description. */
	private String shortDescription; // shortDesc

	/** The long description. */
	private String longDescription; // longDesc

	/** The semester name. */
	private String semesterName; // semesterName

	/** The language. */
	private String language; // language

	/** The faculty name. */
	private String facultyName; // facultyName

	/** The instructor names. */
	private String instructorNames; // instructor_string

	/** The producers. */
	private Map<String, String> producers; // list of producers

	/** The faculties. */
	private Map<String, String> faculties; // list of faculties

	/** The languages. */
	private Map<String, String> languages; // list of languages

	/** The password. */
	private String password; // password

	/** The approved. */
	private boolean approved = false;

	/** The success. */
	private boolean success = false;

	/**
	 * Instantiates a new admin lectureseries model.
	 */
	public AdminLectureseriesModel() {
		super();
		// initialize fields that are not required
		this.eventCategory = "";
		this.faculties = new HashMap<String, String>();
		this.producers = new HashMap<String, String>();
		this.instructorNames = "";
		this.semesterName = "";
		this.shortDescription = "";
		this.longDescription = "";
		this.facultyName = "";
	}

	// //////////////////////////////////////////////////////
	// ///////////// GETTERS AND SETTERS ///////////////////

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#isSuccess()
	 */
	/**
	 * Checks if is success.
	 *
	 * @return true, if is success
	 */
	@Override
	public boolean isSuccess() {
		return success;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#setSuccess(boolean)
	 */
	/**
	 * Sets the success.
	 *
	 * @param success the new success
	 */
	@Override
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Gets the lectureseries name.
	 *
	 * @return the lectureseries name
	 */
	public String getLectureseriesName() {
		return lectureseriesName;
	}

	/**
	 * Sets the lectureseries name.
	 *
	 * @param lectureseriesName the new lectureseries name
	 */
	public void setLectureseriesName(String lectureseriesName) {
		this.lectureseriesName = lectureseriesName;
	}

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

	/**
	 * Gets the short description.
	 *
	 * @return the short description
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * Sets the short description.
	 *
	 * @param shortDescription the new short description
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.ProducerMetaDataModel#getLongDescription()
	 */
	/**
	 * Gets the long description.
	 *
	 * @return the long description
	 */
	@Override
	public String getLongDescription() {
		return longDescription;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.ProducerMetaDataModel#setLongDescription(java.lang.String)
	 */
	/**
	 * Sets the long description.
	 *
	 * @param longDescription the new long description
	 */
	@Override
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

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

	/**
	 * Gets the lectureseries language.
	 *
	 * @return the lectureseries language
	 */
	public String getLectureseriesLanguage() {
		return language;
	}

	/**
	 * Sets the lectureseries language.
	 *
	 * @param language the new lectureseries language
	 */
	public void setLectureseriesLanguage(String language) {
		this.language = language;
	}

	/**
	 * Gets the faculty name.
	 *
	 * @return the faculty name
	 */
	public String getFacultyName() {
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

	/**
	 * Gets the instructor names.
	 *
	 * @return the instructor names
	 */
	public String getInstructorNames() {
		return instructorNames;
	}

	/**
	 * Sets the instructor names.
	 *
	 * @param instructorNames the new instructor names
	 */
	public void setInstructorNames(String instructorNames) {
		this.instructorNames = instructorNames;
	}

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

	/**
	 * Sets the producers.
	 *
	 * @param producers the producers
	 */
	public void setProducers(Map<String, String> producers) {
		this.producers = producers;
	}

	/**
	 * Gets the producers.
	 *
	 * @return the producers
	 */
	public Map<String, String> getProducers() {
		return producers;
	}

	/**
	 * Sets the faculties.
	 *
	 * @param faculties the faculties
	 */
	public void setFaculties(Map<String, String> faculties) {
		this.faculties = faculties;
	}

	/**
	 * Gets the faculties.
	 *
	 * @return the faculties
	 */
	public Map<String, String> getFaculties() {
		return faculties;
	}

	
	/**
	 * Sets the languages.
	 *
	 * @param languages the languages
	 */
	public void setLanguages(Map<String, String> languages) {
		this.languages = languages;
	}

	/**
	 * Gets the languages.
	 *
	 * @return the languages
	 */
	public Map<String, String> getLanguages() {
		return languages;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.ProducerModel#isApproved()
	 */
	/**
	 * Checks if is approved.
	 *
	 * @return true, if is approved
	 */
	@Override
	public boolean isApproved() {
		return approved;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.ProducerModel#setApproved(boolean)
	 */
	/**
	 * Sets the approved.
	 *
	 * @param approved the new approved
	 */
	@Override
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}
