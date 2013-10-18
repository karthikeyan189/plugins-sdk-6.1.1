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
package de.uhh.l2g.dao;

import java.util.List;

import de.uhh.l2g.beans.Metadata;

/**
 * The Interface IMetadataDao.
 */
public interface IMetadataDao {

	/**
	 * Creates the.
	 *
	 * @param urlid the urlid
	 * @param format the format
	 * @param type the type
	 * @param language the language
	 * @param title the title
	 * @param subject the subject
	 * @param coverage the coverage
	 * @param description the description
	 * @param creator the creator
	 * @param publisher the publisher
	 * @param contributor the contributor
	 * @param rightsHolder the rights holder
	 * @param rights the rights
	 * @param provenance the provenance
	 * @param source the source
	 * @param relation the relation
	 * @param audience the audience
	 * @param instructionalMethod the instructional method
	 * @param date the date
	 */
	void create(String urlid, String format, String type, String language, String title, String subject, String coverage, String description, String creator, String publisher, String contributor, String rightsHolder, String rights, String provenance, String source, String relation, String audience, String instructionalMethod, String date);

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
	List<Metadata> getById(int id);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<Metadata> getAll();
}
