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

import de.uhh.l2g.beans.Chapter;


/**
 * The Class ChapterResultSetExtractor.
 */
public class ChapterResultSetExtractor implements ResultSetExtractor {

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
		Chapter chapter = new Chapter();

		chapter.setId(rs.getInt(1));
		chapter.setVideoId(rs.getInt(2));
		chapter.setStart(rs.getString(3));
		chapter.setTitle(rs.getString(4));
		chapter.setEnd(rs.getString(6));
		chapter.setUserId(rs.getInt(8));
		// seconds
		try {
			int sec = new Integer(rs.getString(3).split(":")[0]) * 60 * 60 + new Integer(rs.getString(3).split(":")[1]) * 60 + new Integer(rs.getString(3).split(":")[2]);
			chapter.setSeconds(sec);
		} catch (Exception npe) {
		}

		return chapter;
	}

}
