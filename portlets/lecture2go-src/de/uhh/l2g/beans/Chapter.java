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
 * The Class Chapter.
 */
public class Chapter extends Segment {

	/**
	 * Instantiates a new chapter.
	 */
	public Chapter() {

	}

	/**
	 * Instantiates a new chapter.
	 *
	 * @param id the id
	 * @param userId the user id
	 * @param start the start
	 * @param end the end
	 * @param videoId the video id
	 * @param title the title
	 * @param seconds the seconds
	 * @param image the image
	 * @param number the number
	 */
	public Chapter(int id, int userId, String start, String end, int videoId, String title, int seconds, String image, Integer number) {
		super(id, userId, start, end, videoId, image);
		this.title = title;
		this.seconds = seconds;
		this.number = number;
	}

	/** The title. */
	private String title = "";

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** The seconds. */
	private int seconds;

	/**
	 * Gets the seconds.
	 *
	 * @return the seconds
	 */
	public int getSeconds() {
		return seconds;
	}

	/**
	 * Sets the seconds.
	 *
	 * @param seconds the new seconds
	 */
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	/** The number. */
	private int number;

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(int number) {
		this.number = number;
	}

}
