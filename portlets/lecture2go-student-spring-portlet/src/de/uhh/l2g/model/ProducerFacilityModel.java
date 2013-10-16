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
import java.util.Map;

import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.ProducerLRInfo;

/**
 * The Class ProducerFacilityModel.
 */
public class ProducerFacilityModel extends UserStatusModel {

	/** The producer. */
	private ProducerLRInfo producer;

	/** The faculty. */
	private Facility faculty; // list of faculties of this
	// producer

	/** The all faculties as list. */
	private List<Facility> allFacultiesAsList; // list of all faculties

	/**
	 * Gets the all faculties as list.
	 *
	 * @return the all faculties as list
	 */
	public List<Facility> getAllFacultiesAsList() {
		return allFacultiesAsList;
	}

	/**
	 * Sets the all faculties.
	 *
	 * @param allFaculties the new all faculties
	 */
	public void setAllFaculties(List<Facility> allFaculties) {
		this.allFacultiesAsList = allFaculties;
	}

	/** The all faculties as map. */
	private Map<String, String> allFacultiesAsMap; // list of all faculties

	/**
	 * Gets the all faculties as map.
	 *
	 * @return the all faculties as map
	 */
	public Map<String, String> getAllFacultiesAsMap() {
		return allFacultiesAsMap;
	}

	/**
	 * Sets the all faculties.
	 *
	 * @param allFaculties the all faculties
	 */
	public void setAllFaculties(Map<String, String> allFaculties) {
		this.allFacultiesAsMap = allFaculties;
	}

	/**
	 * Instantiates a new producer facility model.
	 */
	public ProducerFacilityModel() {

	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.Objects#setFaculty(de.uhh.l2g.beans.Facility)
	 */
	/**
	 * Sets the faculty.
	 *
	 * @param faculty the new faculty
	 */
	@Override
	public void setFaculty(Facility faculty) {
		this.faculty = faculty;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.Objects#getFaculty()
	 */
	/**
	 * Gets the faculty.
	 *
	 * @return the faculty
	 */
	@Override
	public Facility getFaculty() {
		return faculty;
	}

	/**
	 * Sets the producer.
	 *
	 * @param producer the new producer
	 */
	public void setProducer(ProducerLRInfo producer) {
		this.producer = producer;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.Objects#getProducer()
	 */
	/**
	 * Gets the producer.
	 *
	 * @return the producer
	 */
	public ProducerLRInfo getProducer() {
		return producer;
	}
}
