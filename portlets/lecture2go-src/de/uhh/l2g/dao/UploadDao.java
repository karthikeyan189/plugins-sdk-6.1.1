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

import de.uhh.l2g.beans.Upload;


/**
 * The Class UploadDao.
 */

public class UploadDao extends JdbcDaoSupport implements IUploadDao {

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.IUploadDao#create(int, long)
	 */
	/**
	 * Creates the.
	 *
	 * @param userId the user id
	 * @param contentLength the content length
	 */
	public void create(int userId, long contentLength) {
		JdbcTemplate insert = new JdbcTemplate(this.getDataSource());
		insert.update("INSERT INTO upload (userId, contentLength) VALUES(?,?)", new Object[] { userId, contentLength });
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.IUploadDao#deleteAll()
	 */
	/**
	 * Delete all.
	 */
	public void deleteAll() {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from upload");
	}

	/**
	 * Delete by content length.
	 *
	 * @param contentLength the content length
	 */
	public void deleteByContentLength(long contentLength) {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from upload where contentLength=" + contentLength);
	}

	/**
	 * Delete by user id.
	 *
	 * @param userId the user id
	 */
	public void deleteByUserId(long userId) {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from upload where userId=" + userId);
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.IUploadDao#deleteById(int)
	 */
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(int id) {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from upload where id=" + id);
	}

	/**
	 * Update by id.
	 *
	 * @param id the id
	 * @param userId the user id
	 * @param contentLength the content length
	 * @param timestamp the timestamp
	 * @param status the status
	 * @param videoId the video id
	 */
	public void updateById(int id, int userId, long contentLength, String timestamp, int status, int videoId) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE upload SET userId=?, contentLength=?, timestamp=?, status=?, videoId=? WHERE id=?", new Object[] { userId, contentLength, timestamp, status, videoId, id });
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.IUploadDao#getAll()
	 */
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@SuppressWarnings("unchecked")
	public List<Upload> getAll() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("select id, userId, contentLength, timestamp, status, videoId from upload", new UploadRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.IUploadDao#getById(int)
	 */
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	@SuppressWarnings("unchecked")
	public List<Upload> getById(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("select id, userId, contentLength, timestamp, status, videoId from upload where id=" + id, new UploadRowMapper());
	}

	/**
	 * Gets the by user id desc.
	 *
	 * @param id the id
	 * @return the by user id desc
	 */
	@SuppressWarnings("unchecked")
	public List<Upload> getByUserIdDesc(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("select id, userId, contentLength, timestamp, status, videoId from upload where userId=" + id + " order by timestamp desc", new UploadRowMapper());
	}

	/**
	 * Gets the by content length desc.
	 *
	 * @param contentLength the content length
	 * @return the by content length desc
	 */
	@SuppressWarnings("unchecked")
	public List<Upload> getByContentLengthDesc(long contentLength) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("select id, userId, contentLength, timestamp, status, videoId from upload where contentLength=" + contentLength + " order by timestamp desc", new UploadRowMapper());
	}

}
