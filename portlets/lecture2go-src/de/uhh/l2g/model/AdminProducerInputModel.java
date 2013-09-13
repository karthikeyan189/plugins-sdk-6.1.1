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
package de.uhh.l2g.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Class AdminProducerInputModel.
 */
public class AdminProducerInputModel extends AdminModel {

	/** The id. */
	private long id;

	/** The all faculties. */
	private Map<String, String> allFaculties; // list of all faculties

	/**
	 * The Constructor.
	 */
	public AdminProducerInputModel() {
		this.setAllFaculties(new LinkedHashMap<String, String>());
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getId() {
		return id;
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

}
