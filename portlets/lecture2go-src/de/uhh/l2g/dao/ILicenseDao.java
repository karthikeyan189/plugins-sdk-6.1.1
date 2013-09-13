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
package de.uhh.l2g.dao;

import java.util.List;

import de.uhh.l2g.beans.License;

/**
 * The Interface ILicenseDao.
 */
public interface ILicenseDao {

	/**
	 * Creates the.
	 *
	 * @param videoId the video id
	 * @param ccby the ccby
	 * @param ccbybc the ccbybc
	 * @param ccbyncnd the ccbyncnd
	 * @param ccbyncca the ccbyncca
	 * @param ccbysa the ccbysa
	 * @param ccbync the ccbync
	 * @param l2go the l2go
	 */
	void create(int videoId, boolean ccby, boolean ccbybc, boolean ccbyncnd, boolean ccbyncca, boolean ccbysa, boolean ccbync, boolean l2go);

	/**
	 * Delete all.
	 */
	void deleteAll();

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	void deleteById(int id);

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	List<License> getById(int id);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<License> getAll();
}
