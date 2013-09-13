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
package de.uhh.l2g.beans;


/**
 * The Class Facility.
 */
public class Facility {

	/** The host. */
	private Host host;
	
	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public Host getHost() {
		return host;
	}

	/**
	 * Sets the host.
	 *
	 * @param host the host
	 */
	public void setHost(Host host) {
		this.host = host;
	}

	/** The deletable. */
	private boolean deletable;

	/**
	 * Checks if is deletable.
	 *
	 * @return true, if checks if is deletable
	 */
	public boolean isDeletable() {
		return deletable;
	}

	/**
	 * Sets the deletable.
	 *
	 * @param deletable the deletable
	 */
	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

	/** The sort. */
	private int sort;

	/**
	 * Gets the sort.
	 *
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * Sets the sort.
	 *
	 * @param i the sort
	 */
	public void setSort(int i) {
		this.sort = i;
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
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** The level. */
	private int level;

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/** The www. */
	private String www;

	/**
	 * Gets the www.
	 *
	 * @return the www
	 */
	public String getWww() {
		return www;
	}

	/**
	 * Sets the www.
	 *
	 * @param www the www
	 */
	public void setWww(String www) {
		this.www = www;
	}

	/** The typ. */
	private String typ = "new java.lang.String()";

	/**
	 * Gets the typ.
	 *
	 * @return the typ
	 */
	public String getTyp() {
		return typ;
	}

	/**
	 * Sets the typ.
	 *
	 * @param typ the typ
	 */
	public void setTyp(String typ) {
		this.typ = typ;
	}

	/** The id. */
	private Integer id = 0;

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
	 * @param id the id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** The parent id. */
	private int parentId;

	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * Sets the parent id.
	 *
	 * @param parentId the parent id
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/** The path. */
	private String path;

	/**
	 * Sets the path.
	 *
	 * @param path the path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Gets the has videos.
	 *
	 * @return the checks for videos
	 */
	public boolean getHasVideos() {
		return hasVideos;
	}

	/**
	 * Sets the has videos.
	 *
	 * @param hasVideos the checks for videos
	 */
	public void setHasVideos(boolean hasVideos) {
		this.hasVideos = hasVideos;
	}

	/**
	 * Gets the has lectureseries.
	 *
	 * @return the checks for lectureseries
	 */
	public boolean getHasLectureseries() {
		return hasLectureseries;
	}

	/**
	 * Sets the has lectureseries.
	 *
	 * @param hasLectureseries the checks for lectureseries
	 */
	public void setHasLectureseries(boolean hasLectureseries) {
		this.hasLectureseries = hasLectureseries;
	}

	/** The has videos. */
	private boolean hasVideos = false;

	/** The has lectureseries. */
	private boolean hasLectureseries = false;


}
