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

import de.uhh.l2g.beans.ProducerLRInfo;

/**
 * The Class ProducerLRInfoResultSetExtractor.
 */
public class ProducerLRInfoResultSetExtractor implements ResultSetExtractor {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
	public Object extractData(ResultSet rs) throws SQLException {
		ProducerLRInfo producer = new ProducerLRInfo();
		producer.setId(rs.getInt(1));
		producer.setUserId(rs.getInt(2));
		producer.setHostId(rs.getInt(3));
		producer.setFacilityId(rs.getInt(4));
		producer.setNumberOfProductions(rs.getInt(5));
		producer.setIdNum(rs.getString(6));
		producer.setHomeDir(rs.getString(7));
		producer.setApproved(rs.getBoolean(8));
		producer.setEmailAddress(rs.getString(9));
		producer.setScreenName(rs.getString(10));
		producer.setFirstName(rs.getString(11));
		producer.setLastName(rs.getString(12));
		producer.setCreateDate(rs.getDate(13));
		producer.setLastLoginDate(rs.getDate(14));
		return producer;
	}

}
