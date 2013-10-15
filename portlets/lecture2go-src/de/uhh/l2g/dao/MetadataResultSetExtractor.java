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

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import de.uhh.l2g.beans.Metadata;
import de.uhh.l2g.util.HtmlManager;

/**
 * The Class MetadataResultSetExtractor.
 */
public class MetadataResultSetExtractor implements ResultSetExtractor {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
	/**
	 * Extract data.
	 *
	 * @param rs the rs
	 * @return the object
	 * @throws SQLException the sQL exception
	 */
	public Object extractData(ResultSet rs) throws SQLException {
		Metadata metadata = new Metadata();
		metadata.setId(rs.getInt(1));
		metadata.setUrlId(rs.getString(2));
		metadata.setFormat(rs.getString(3));
		metadata.setType(rs.getString(4));
		metadata.setLanguage(rs.getString(5));
		metadata.setTitle(rs.getString(6));
		metadata.setSubject(rs.getString(7));
		metadata.setCoverage(rs.getString(8));
		metadata.setDescription(HtmlManager.prepareHtmlForWyswygEditor(rs.getString(9)));
		metadata.setCreator(rs.getString(10));
		metadata.setPublisher(rs.getString(11));
		metadata.setContributor(rs.getString(12));
		metadata.setRightsHolder(rs.getString(13));
		metadata.setRights(rs.getString(14));
		metadata.setProvenance(rs.getString(15));
		metadata.setSource(rs.getString(16));
		metadata.setRelation(rs.getString(17));
		metadata.setAudience(rs.getString(18));
		metadata.setInstructionalMethod(rs.getString(19));
		metadata.setDate(rs.getString(20));
		return metadata;
	}

}
