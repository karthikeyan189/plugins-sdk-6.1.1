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

import java.util.List;

import de.uhh.l2g.beans.Facility;

/**
 * The Class CoordinatorFacilitiesModel.
 */
public class CoordinatorFacilitiesModel extends CoordinatorModel {

	/** The facilities list. */
	private List<Facility> facilitiesList;

	/**
	 * Gets the facilities list.
	 *
	 * @return the facilities list
	 */
	public List<Facility> getFacilitiesList() {
		return facilitiesList;
	}

	/**
	 * Sets the facilities list.
	 *
	 * @param facilitiesList the facilities list
	 */
	public void setFacilitiesList(List<Facility> facilitiesList) {
		this.facilitiesList = facilitiesList;
	}

	/**
	 * Gets the facility.
	 *
	 * @return the facility
	 */
	public Facility getFacility() {
		return facility;
	}

	/**
	 * Sets the facility.
	 *
	 * @param facility the facility
	 */
	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	/** The facility. */
	private Facility facility;

	/** The list facilities size. */
	private int listFacilitiesSize = 0;

	/**
	 * Gets the list facilities size.
	 *
	 * @return the list facilities size
	 */
	public int getListFacilitiesSize() {
		return listFacilitiesSize;
	}

	/**
	 * Sets the list facilities size.
	 *
	 * @param listFacilitiesSize the list facilities size
	 */
	public void setListFacilitiesSize(int listFacilitiesSize) {
		this.listFacilitiesSize = listFacilitiesSize;
	}

}
