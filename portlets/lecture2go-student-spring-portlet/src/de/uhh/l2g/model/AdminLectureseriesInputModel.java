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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.uhh.l2g.beans.Coordinator;


/**
 * The Class AdminLectureseriesInputModel.
 */
@SuppressWarnings("serial")
public class AdminLectureseriesInputModel extends AdminLectureseriesModel implements Serializable {

	/** The all producers. */
	private Map<String, String> allProducers; // list of all producers

	/** The all faculties. */
	private Map<String, String> allFaculties; 

	/** The all languages. */
	private Map<String, String> allLanguages; 

	/** The event type list. */
	private List<String> eventTypeList; 

	/** The all event categories. */
	private List<String> allEventCategories; // eventCategory

	/** The all semesters. */
	private List<String> allSemesters; // eventCategory

	/** The facility name. */
	private String facilityName = "";

	/** The role coordinator. */
	private boolean roleCoordinator = false;

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

	/**
	 * Checks if is coordinator.
	 *
	 * @return true, if is coordinator
	 */
	public boolean isCoordinator() {
		return roleCoordinator;
	}

	/**
	 * Sets the coordinator.
	 *
	 * @param roleCoordinator the new coordinator
	 */
	public void setCoordinator(boolean roleCoordinator) {
		this.roleCoordinator = roleCoordinator;
	}

	/**
	 * Gets the facility name.
	 *
	 * @return the facility name
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * Sets the facility name.
	 *
	 * @param facilityName the new facility name
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * Instantiates a new admin lectureseries input model.
	 */
	public AdminLectureseriesInputModel() {
		super();
		// initialize fields that are not required
		this.setId (0);

		this.setAllSemesters(new ArrayList<String>());
		this.setAllFaculties(new LinkedHashMap<String, String>());
		this.setAllProducers(new LinkedHashMap<String, String>());
		this.setAllLanguages(new HashMap<String, String>());
		this.setFaculties(new HashMap<String, String>());
		this.setProducers(new HashMap<String, String>());
		this.setLanguages(new HashMap<String, String>());
		this.setAllEventCategories(new ArrayList<String>());

		this.setEventCategory("");
		this.setInstructorNames("");
		this.setSemesterName("");
		this.setShortDescription("");
		this.setLongDescription("");
		this.setFacultyName("");
	}

	// //////////////////////////////////////////////////////
	// ///////////// GETTERS AND SETTERS ///////////////////


	/**
	 * Gets the event type list.
	 *
	 * @return the event type list
	 */
	public List<String> getEventTypeList() {
		return eventTypeList;
	}

	/**
	 * Sets the event type list.
	 *
	 * @param eventTypeList the new event type list
	 */
	public void setEventTypeList(List<String> eventTypeList) {
		this.eventTypeList = eventTypeList;
	}

	/**
	 * Sets the all faculties.
	 *
	 * @param allFaculties the all faculties
	 */
	public void setAllFaculties(Map<String, String> allFaculties) {
		this.allFaculties = allFaculties;
	}

	/**
	 * Gets the all faculties.
	 *
	 * @return the all faculties
	 */
	public Map<String, String> getAllFaculties() {
		return allFaculties;
	}

	/**
	 * Sets the all producers.
	 *
	 * @param allProducers the all producers
	 */
	public void setAllProducers(Map<String, String> allProducers) {
		this.allProducers = allProducers;
	}

	/**
	 * Gets the all producers.
	 *
	 * @return the all producers
	 */
	public Map<String, String> getAllProducers() {
		return allProducers;
	}

	/**
	 * Sets the all event categories.
	 *
	 * @param allEventCategories the new all event categories
	 */
	public void setAllEventCategories(List<String> allEventCategories) {
		this.allEventCategories = allEventCategories;
	}

	/**
	 * Gets the all event categories.
	 *
	 * @return the all event categories
	 */
	public List<String> getAllEventCategories() {
		return allEventCategories;
	}

	/**
	 * Sets the all languages.
	 *
	 * @param allLanguages the all languages
	 */
	public void setAllLanguages(Map<String, String> allLanguages) {
		this.allLanguages = allLanguages;
	}

	/**
	 * Gets the all languages.
	 *
	 * @return the all languages
	 */
	public Map<String, String> getAllLanguages() {
		return allLanguages;
	}

	/**
	 * Sets the all semesters.
	 *
	 * @param allSemesters the new all semesters
	 */
	public void setAllSemesters(List<String> allSemesters) {
		this.allSemesters = allSemesters;
	}

	/**
	 * Gets the all semesters.
	 *
	 * @return the all semesters
	 */
	public List<String> getAllSemesters() {
		return allSemesters;
	}
}
