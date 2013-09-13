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
 * The Class Mark.
 */
public class Mark extends Chapter {

	/**
	 * The Constructor.
	 */
	public Mark() {
		//
	}

	/**
	 * The Constructor.
	 *
	 * @param id the id
	 * @param userId the user id
	 * @param start the start
	 * @param end the end
	 * @param videoId the video id
	 * @param title the title
	 * @param seconds the seconds
	 * @param image the image
	 * @param description the description
	 * @param number the number
	 */
	public Mark(int id, int userId, String start, String end, int videoId, String title, int seconds, String image, String description, Integer number) {
		super(id, userId, start, end, videoId, title, seconds, image, number);
		this.description = description;
	}

	/** The description. */
	private String description = "";

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** The chapter. */
	private boolean chapter;

	/**
	 * Checks if is chapter.
	 *
	 * @return true, if checks if is chapter
	 */
	public boolean isChapter() {
		return chapter;
	}

	/**
	 * Sets the chapter.
	 *
	 * @param chapter the chapter
	 */
	public void setChapter(boolean chapter) {
		this.chapter = chapter;
	}

}
