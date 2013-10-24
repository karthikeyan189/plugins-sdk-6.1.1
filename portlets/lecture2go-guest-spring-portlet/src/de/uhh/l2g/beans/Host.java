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


/**
 * The Class Host.
 */
public class Host {

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
	 * @param facilitiesList the new facilities list
	 */
	public void setFacilitiesList(List<Facility> facilitiesList) {
		this.facilitiesList = facilitiesList;
	}

	/** The deletable. */
	private boolean deletable;
	
	/**
	 * Checks if is deletable.
	 *
	 * @return true, if is deletable
	 */
	public boolean isDeletable() {
		return deletable;
	}

	/**
	 * Sets the deletable.
	 *
	 * @param deletable the new deletable
	 */
	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
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

	/** The protokoll. */
	private String protokoll = "new java.lang.String()";

	/**
	 * Gets the protokoll.
	 *
	 * @return the protokoll
	 */
	public String getProtokoll() {
		return protokoll;
	}

	/**
	 * Sets the protokoll.
	 *
	 * @param protokoll the new protokoll
	 */
	public void setProtokoll(String protokoll) {
		this.protokoll = protokoll;
	}

	/** The streamer. */
	private String streamer = "new java.lang.String()";

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
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/** The server root. */
	private String serverRoot = "new java.lang.String()";

	/**
	 * Gets the server root.
	 *
	 * @return the server root
	 */
	public String getServerRoot() {
		return serverRoot;
	}

	/**
	 * Sets the server root.
	 *
	 * @param serverRoot the new server root
	 */
	public void setServerRoot(String serverRoot) {
		this.serverRoot = serverRoot;
	}

	/** The name. */
	private String name = "new java.lang.String()";

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

	/** The port. */
	private int port = 0;

	/** The id. */
	private int id = 0;

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

}
