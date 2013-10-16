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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 * The Class Metadata_INSERT_PreparedStatement.
 */
public class Metadata_INSERT_PreparedStatement implements PreparedStatementCreator {
	
	/** The insert sql. */
	private String INSERT_SQL = "INSERT INTO metadata (urlid, format, type, language, title, subject, coverage, description, creator, publisher, contributor, rightsHolder, rights, provenance, source, relation, audience, instructionalMethod, date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;
	
	/** The connection. */
	private Connection connection;
	
	/** The parameter. */
	private String[] parameter;
	
	/**
	 * Instantiates a new metadata_ inser t_ prepared statement.
	 *
	 * @param jdbcTemplate the jdbc template
	 * @param parameter the parameter
	 * @throws SQLException the sQL exception
	 */
	public Metadata_INSERT_PreparedStatement(JdbcTemplate jdbcTemplate, String[] parameter) throws SQLException{
		this.jdbcTemplate = jdbcTemplate;
		this.connection = this.jdbcTemplate.getDataSource().getConnection();
		this.parameter = parameter;
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.PreparedStatementCreator#createPreparedStatement(java.sql.Connection)
	 */
	/**
	 * Creates the prepared statement.
	 *
	 * @param connection the connection
	 * @return the prepared statement
	 * @throws SQLException the sQL exception
	 */
	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		PreparedStatement ps = this.connection.prepareStatement(INSERT_SQL, new String[] {"id"});
		
		ps.setString(1,  parameter[0]);
		ps.setString(2,  parameter[1]);
		ps.setString(3,  parameter[2]);
		ps.setString(4,  parameter[3]);
		ps.setString(5,  parameter[4]);
		ps.setString(6,  parameter[5]);
		ps.setString(7,  parameter[6]);
		ps.setString(8,  parameter[7]);
		ps.setString(9,  parameter[8]);
		ps.setString(10,  parameter[9]);
		ps.setString(11,  parameter[10]);
		ps.setString(12,  parameter[11]);
		ps.setString(13,  parameter[12]);
		ps.setString(14,  parameter[13]);
		ps.setString(15,  parameter[14]);
		ps.setString(16,  parameter[15]);
		ps.setString(17,  parameter[16]);
		ps.setString(18,  parameter[17]);
		ps.setString(19,  parameter[18]);
		
        return ps;
	}

}
