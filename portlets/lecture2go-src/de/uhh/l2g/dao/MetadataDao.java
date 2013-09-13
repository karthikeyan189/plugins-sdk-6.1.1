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

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.uhh.l2g.beans.Metadata;

/**
 * The Class MetadataDao.
 */
public class MetadataDao extends JdbcDaoSupport implements IMetadataDao {

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IMetadataDao#deleteAll()
	 */
	public void deleteAll() {
		//
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IMetadataDao#deleteById(int)
	 */
	public void deleteById(int id) {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from metadata where id=" + id);
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IMetadataDao#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Metadata> getAll() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT id, urlid, format, type, language, title, subject, coverage, description, creator, publisher, contributor, rightsHolder, rights, provenance, source, relation, audience, instructionalMethod, date FROM metadata ORDER by id ASC", new MetadataRowMapper());
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IMetadataDao#getById(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Metadata> getById(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT id, urlid, format, type, language, title, subject, coverage, description, creator, publisher, contributor, rightsHolder, rights, provenance, source, relation, audience, instructionalMethod, date FROM metadata WHERE id = " + id, new MetadataRowMapper());
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IMetadataDao#create(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void create(String urlid, String format, String type, String language, String title, String subject, String coverage, String description, String creator, String publisher, String contributor, String rightsHolder, String rights, String provenance, String source, String relation, String audience, String instructionalMethod, String date) {
		JdbcTemplate insert = new JdbcTemplate(this.getDataSource());
		insert.update("INSERT INTO metadata (urlid, format, type, language, title, subject, coverage, description, creator, publisher, contributor, rightsHolder, rights, provenance, source, relation, audience, instructionalMethod, date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[] { urlid, format, type, language, title, subject, coverage, description, creator, publisher, contributor, rightsHolder, rights, provenance, source, relation, audience, instructionalMethod, date });
	}

	
	/**
	 * Creates the and return generated id.
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
	 * @return the int
	 */
	public int createAndReturnGeneratedId(final String urlid,final String format,final String type,final String language,final String title,final String subject,final String coverage,final String description,final String creator,final String publisher,final String contributor,final String rightsHolder,final String rights,final String provenance,final String source,final String relation,final String audience,final String instructionalMethod,final String date){
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		PreparedStatementCreator pscInsert = null;
		//initialize parameters
		try {
			String[] parameter = {urlid,format,type,language,title,subject,coverage,description,creator,publisher,contributor,rightsHolder,rights,provenance,source,relation,audience,instructionalMethod,date}; 
			pscInsert = new Metadata_INSERT_PreparedStatement(this.getJdbcTemplate(), parameter);
		} catch (SQLException e) {}

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(pscInsert, keyHolder);
		
		Number n = keyHolder.getKey();
		int i = n.intValue();
		return i;
	}

	/**
	 * Update by id.
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
	 * @param id the id
	 */
	public void updateById(String urlid, String format, String type, String language, String title, String subject, String coverage, String description, String creator, String publisher, String contributor, String rightsHolder, String rights, String provenance, String source, String relation, String audience, String instructionalMethod, String date, int id) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE metadata SET urlid=?, format=?, type=?, language=?, title=?, subject=?, coverage=?, description=?, creator=?, publisher=?, contributor=?, rightsHolder=?, rights=?, provenance=?, source=?, relation=?, audience=?, instructionalMethod=?, date=? WHERE id=?", 
				            new Object[] { urlid,   format,   type,   language,   title,   subject,   coverage,   description,   creator,   publisher,   contributor,   rightsHolder,   rights,   provenance,   source,   relation,   audience,   instructionalMethod,   date,        id });
	}
}
