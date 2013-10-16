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

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import de.uhh.l2g.beans.License;


/**
 * The Class LicenseDao.
 */

public class LicenseDao extends JdbcDaoSupport implements ILicenseDao {

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.ILicenseDao#deleteAll()
	 */
	/**
	 * Delete all.
	 */
	public void deleteAll() {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE * from license");
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.ILicenseDao#deleteById(int)
	 */
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(int id) {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from license WHERE id=?", new Object[] { id });
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.ILicenseDao#getAll()
	 */
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@SuppressWarnings("unchecked")
	public List<License> getAll() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT * FROM license ORDER BY ID ASC", new LicenseRowMapper());
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.ILicenseDao#getById(int)
	 */
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	@SuppressWarnings("unchecked")
	public List<License> getById(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT id, videoId, ccby, ccbybc, ccbyncnd, ccbyncsa, ccbysa, ccbync, l2go FROM license WHERE id=?", new Object[] { id }, new LicenseRowMapper());
	}

	/**
	 * Gets the by video id.
	 *
	 * @param videoId the video id
	 * @return the by video id
	 */
	@SuppressWarnings("unchecked")
	public List<License> getByVideoId(int videoId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT id, videoId, ccby, ccbybc, ccbyncnd, ccbyncsa, ccbysa, ccbync, l2go FROM license WHERE videoId=?", new Object[] { videoId }, new LicenseRowMapper());
	}

	/**
	 * Update by id.
	 *
	 * @param videoId the video id
	 * @param ccby the ccby
	 * @param ccbybc the ccbybc
	 * @param ccbyncnd the ccbyncnd
	 * @param ccbyncsa the ccbyncsa
	 * @param ccbysa the ccbysa
	 * @param ccbync the ccbync
	 * @param l2go the l2go
	 * @param id the id
	 */
	public void updateById(int videoId, boolean ccby, boolean ccbybc, boolean ccbyncnd, boolean ccbyncsa, boolean ccbysa, boolean ccbync, boolean l2go, int id) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE license SET videoId=?, ccby=?, ccbybc=?, ccbyncnd=?, ccbyncsa=?, ccbysa=?, ccbync=?, l2go=? WHERE id=?", new Object[] { videoId, ccby, ccbybc, ccbyncnd, ccbyncsa, ccbysa, ccbync, l2go, id });
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.ILicenseDao#create(int, boolean, boolean, boolean,
	 * boolean, boolean, boolean, boolean)
	 */
	/**
	 * Creates the.
	 *
	 * @param videoId the video id
	 * @param ccby the ccby
	 * @param ccbybc the ccbybc
	 * @param ccbyncnd the ccbyncnd
	 * @param ccbyncsa the ccbyncsa
	 * @param ccbysa the ccbysa
	 * @param ccbync the ccbync
	 * @param l2go the l2go
	 */
	public void create(int videoId, boolean ccby, boolean ccbybc, boolean ccbyncnd, boolean ccbyncsa, boolean ccbysa, boolean ccbync, boolean l2go) {
		JdbcTemplate insert = new JdbcTemplate(this.getDataSource());
		insert.update("INSERT INTO license (videoId, ccby, ccbybc, ccbyncnd, ccbyncsa, ccbysa, ccbync, l2go) VALUES(?,?,?,?,?,?,?,?)", new Object[] { videoId, ccby, ccbybc, ccbyncnd, ccbyncsa, ccbysa, ccbync, l2go });
	}

	/**
	 * Delete by id video id.
	 *
	 * @param videoId the video id
	 */
	public void deleteByIdVideoId(int videoId) {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from license WHERE videoId=?", new Object[] { videoId });
	}
}
