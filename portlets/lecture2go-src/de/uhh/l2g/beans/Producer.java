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
 * The Class Producer.
 */
public class Producer extends Student {

	/** The has lectureseries. */
	private boolean hasLectureseries;
	
	/**
	 * Gets the checks for lectureseries.
	 *
	 * @return the checks for lectureseries
	 */
	public boolean getHasLectureseries() {
		return hasLectureseries;
	}

	/**
	 * Sets the checks for lectureseries.
	 *
	 * @param hasLectureseries the new checks for lectureseries
	 */
	public void setHasLectureseries(boolean hasLectureseries) {
		this.hasLectureseries = hasLectureseries;
	}

	/** The host id. */
	private int hostId = 0;

	/**
	 * Gets the host id.
	 *
	 * @return the host id
	 */
	public int getHostId() {
		return hostId;
	}

	/**
	 * Sets the host id.
	 *
	 * @param hostId the new host id
	 */
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	/** The number of productions. */
	private int numberOfProductions = 0;

	/**
	 * Gets the number of productions.
	 *
	 * @return the number of productions
	 */
	public int getNumberOfProductions() {
		return numberOfProductions;
	}

	/**
	 * Sets the number of productions.
	 *
	 * @param numberOfProductions the new number of productions
	 */
	public void setNumberOfProductions(int numberOfProductions) {
		this.numberOfProductions = numberOfProductions;
	}

	/** The home dir. */
	private String homeDir = "new java.lang.String()";

	/**
	 * Gets the home dir.
	 *
	 * @return the home dir
	 */
	public String getHomeDir() {
		return homeDir;
	}

	/**
	 * Sets the home dir.
	 *
	 * @param homeDir the new home dir
	 */
	public void setHomeDir(String homeDir) {
		this.homeDir = homeDir;
	}

	/** The approved. */
	private boolean approved = false;

	/**
	 * Checks if is approved.
	 *
	 * @return true, if is approved
	 */
	public boolean isApproved() {
		return approved;
	}

	/**
	 * Sets the approved.
	 *
	 * @param approved the new approved
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

}
