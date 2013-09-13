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

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.util.HtmlManager;


/**
 * The Class LectureseriesResultSetExtractor.
 */
public class LectureseriesResultSetExtractor implements ResultSetExtractor {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
	public Object extractData(ResultSet rs) throws SQLException {
		Lectureseries lectureseries = new Lectureseries();

		lectureseries.setNumber(rs.getString(1));
		lectureseries.setEventType(rs.getString(2));
		lectureseries.setEventCategory(rs.getString(3));

		String l = rs.getString(4).toString();
		String m = new String(l);
		int length = m.length();

		lectureseries.setName(rs.getString(4));

		if (length > 40) lectureseries.setLectureseriesShortName(m.substring(0, 40) + "...");
		else lectureseries.setLectureseriesShortName(rs.getString(4));

		lectureseries.setShortDesc(rs.getString(5));
		lectureseries.setLongDesc(HtmlManager.prepareHtmlForWyswygEditor(rs.getString(6)));
		lectureseries.setSemesterName(rs.getString(7));
		lectureseries.setLanguage(rs.getString(8));
		lectureseries.setFacultyName(rs.getString(9));
		lectureseries.setInstructorsString(rs.getString(10));
		lectureseries.setId(rs.getInt(11));
		lectureseries.setPassword(rs.getString(12));
		lectureseries.setApproved(rs.getBoolean(13));

		return lectureseries;
	}

}
