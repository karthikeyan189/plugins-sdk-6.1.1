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

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.uhh.l2g.beans.Chapter;
import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.Host;
import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.beans.Mark;
import de.uhh.l2g.beans.Metadata;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.beans.VideoResultSearch;
import de.uhh.l2g.util.FFmpegManager;
import de.uhh.l2g.util.L2goPropsUtil;
import de.uhh.l2g.util.Security;
import de.uhh.l2g.util.VideoGenerationDateComparator;

/**
 * The Class VideoDao.
 */
public class VideoDao extends JdbcDaoSupport implements IVideoDao {
	
	/** The utility bean factory. */
	private XmlBeanFactory utilityBeanFactory;

	/**
	 * Gets the utility bean factory.
	 *
	 * @return the utility bean factory
	 */
	public XmlBeanFactory getUtilityBeanFactory() {
		return utilityBeanFactory;
	}

	/**
	 * Sets the utility bean factory.
	 *
	 * @param utilityBeanFactory the new utility bean factory
	 */
	public void setUtilityBeanFactory(XmlBeanFactory utilityBeanFactory) {
		this.utilityBeanFactory = utilityBeanFactory;
	}

	/** The return list. */
	public List<Video> returnList = new ArrayList<Video>();

	/** The return list2. */
	private List<Video> returnList2 = new ArrayList<Video>();

	/**
	 * Gets the host dao.
	 *
	 * @return the host dao
	 */
	public HostDao getHostDao() {
		return hostDao;
	}

	/**
	 * Sets the host dao.
	 *
	 * @param hostDao the new host dao
	 */
	public void setHostDao(HostDao hostDao) {
		this.hostDao = hostDao;
	}

	/**
	 * Gets the producer dao.
	 *
	 * @return the producer dao
	 */
	public ProducerDao getProducerDao() {
		return producerDao;
	}

	/**
	 * Sets the producer dao.
	 *
	 * @param producerDao the new producer dao
	 */
	public void setProducerDao(ProducerDao producerDao) {
		this.producerDao = producerDao;
	}

	/**
	 * Gets the lectureseries dao.
	 *
	 * @return the lectureseries dao
	 */
	public LectureseriesDao getLectureseriesDao() {
		return lectureseriesDao;
	}

	/**
	 * Sets the lectureseries dao.
	 *
	 * @param lectureseriesDao the new lectureseries dao
	 */
	public void setLectureseriesDao(LectureseriesDao lectureseriesDao) {
		this.lectureseriesDao = lectureseriesDao;
	}

	/**
	 * Gets the metadata dao.
	 *
	 * @return the metadata dao
	 */
	public MetadataDao getMetadataDao() {
		return metadataDao;
	}

	/**
	 * Sets the metadata dao.
	 *
	 * @param metadataDao the new metadata dao
	 */
	public void setMetadataDao(MetadataDao metadataDao) {
		this.metadataDao = metadataDao;
	}

	/**
	 * Gets the segment dao.
	 *
	 * @return the segment dao
	 */
	public SegmentDao getSegmentDao() {
		return segmentDao;
	}

	/**
	 * Sets the segment dao.
	 *
	 * @param segmentDao the new segment dao
	 */
	public void setSegmentDao(SegmentDao segmentDao) {
		this.segmentDao = segmentDao;
	}

	/**
	 * Gets the facility dao.
	 *
	 * @return the facility dao
	 */
	public FacilityDao getFacilityDao() {
		return facilityDao;
	}

	/**
	 * Sets the facility dao.
	 *
	 * @param facilityDao the new facility dao
	 */
	public void setFacilityDao(FacilityDao facilityDao) {
		this.facilityDao = facilityDao;
	}

	/** The facility dao. */
	private FacilityDao facilityDao;
	
	/** The host dao. */
	private HostDao hostDao;

	/** The producer dao. */
	private ProducerDao producerDao;

	/** The lectureseries dao. */
	private LectureseriesDao lectureseriesDao;

	/** The metadata dao. */
	private MetadataDao metadataDao;

	/** The segment dao. */
	private SegmentDao segmentDao;

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IVideoDao#deleteAll()
	 */
	/**
	 * Delete all.
	 */
	public void deleteAll() {

		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from video");
		delete.update("DELETE from video_facility");
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IVideoDao#deleteById(int)
	 */
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(int id) {

		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from video where id=?", new Object[] { id });
		delete.update("DELETE from video_facility WHERE videoId=?", new Object[] { id });
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IVideoDao#getAll()
	 */
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getAll(){
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> videoList = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video ORDER BY ID ASC", new VideoRowMapper());
		try {this.fillVideoListWithProperties(videoList);} catch (IOException e) {}
		return videoList;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IVideoDao#getById(int)
	 */
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getById(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List <Video> returnList = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE id=?", new Object[] { id }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(returnList);} catch (IOException e) {}
		
		return returnList;
	}

	/**
	 * Gets the by id for download.
	 *
	 * @param id the id
	 * @return the by id for download
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByIdForDownload(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List <Video> returnList = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE id=?", new Object[] { id }, new VideoRowMapper());
		return returnList;
	}	
	
	/**
	 * Gets the by s url.
	 *
	 * @param surl the surl
	 * @return the by s url
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getBySUrl(String surl) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE surl=?", new Object[] { surl }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Gets the by producer id.
	 *
	 * @param remoteUserId the remote user id
	 * @return the by producer id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByProducerId(int remoteUserId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		String request="SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE producerId=? ORDER BY id DESC";
		List<Video> result = select.query(request, new Object[] { remoteUserId }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		return result;
	}

	/**
	 * Count by producer id.
	 *
	 * @param remoteUserId the remote user id
	 * @return the int
	 */
	public int countByProducerId(int remoteUserId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		int result = select.queryForInt("SELECT COUNT(*) FROM video WHERE producerId=? ORDER BY id DESC", new Object[] { remoteUserId });
		return result;
	}
	
	/**
	 * Gets the video list for segments by user id.
	 *
	 * @param remoteUserId the remote user id
	 * @return the video list for segments by user id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getVideoListForSegmentsByUserId(int remoteUserId){
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		String request="SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video, segment_user_video WHERE (video.id = segment_user_video.videoId AND segment_user_video.userId = ?) GROUP BY id DESC";
		List<Video> result = select.query(request, new Object[] { remoteUserId }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		return result;		
	}
	
	/**
	 * Gets the by producer id and lectureseries id.
	 *
	 * @param remoteUserId the remote user id
	 * @param lectureseriesId the lectureseries id
	 * @return the by producer id and lectureseries id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByProducerIdAndLectureseriesId(int remoteUserId, int lectureseriesId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE ( producerId=? AND lectureseriesId=?) ORDER BY id DESC", new Object[] { remoteUserId, lectureseriesId }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(returnList);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Count by producer id and lectureseries id.
	 *
	 * @param remoteUserId the remote user id
	 * @param lectureseriesId the lectureseries id
	 * @return the int
	 */
	public int countByProducerIdAndLectureseriesId(int remoteUserId, int lectureseriesId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		int result = select.queryForInt("SELECT COUNT(*) FROM video WHERE ( producerId=? AND lectureseriesId=?) ORDER BY id DESC", new Object[] { remoteUserId, lectureseriesId });
		return result;
	}
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IVideoDao#create(java.lang.String, java.lang.String, int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, int, int)
	 */
	/**
	 * Creates the.
	 *
	 * @param title the title
	 * @param tags the tags
	 * @param lectureseriesId the lectureseries id
	 * @param ownerId the owner id
	 * @param producerId the producer id
	 * @param containerFormat the container format
	 * @param filename the filename
	 * @param resolution the resolution
	 * @param duration the duration
	 * @param hostId the host id
	 * @param metadataId the metadata id
	 * @param facilityId the facility id
	 * @param citation2go the citation2go
	 */
	public void create(String title, String tags, int lectureseriesId, int ownerId, int producerId, String containerFormat, String filename, String resolution, String duration, int hostId, int metadataId, int facilityId, int citation2go) {

		JdbcTemplate insert = new JdbcTemplate(this.getDataSource());
		insert.update("INSERT INTO video (title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, metadataId, facilityId, citation2go) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[] { title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, metadataId, facilityId, citation2go });
	}

	/**
	 * Creates the and return generated id.
	 *
	 * @param title the title
	 * @param tags the tags
	 * @param lectureseriesId the lectureseries id
	 * @param ownerId the owner id
	 * @param producerId the producer id
	 * @param containerFormat the container format
	 * @param filename the filename
	 * @param resolution the resolution
	 * @param duration the duration
	 * @param hostId the host id
	 * @param metadataId the metadata id
	 * @param facilityId the facility id
	 * @param citation2go the citation2go
	 * @return the int
	 */
	public int createAndReturnGeneratedId(String title, String tags, int lectureseriesId, int ownerId, int producerId, String containerFormat, String filename, String resolution, String duration, int hostId, int metadataId, int facilityId, int citation2go){
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		PreparedStatementCreator pscInsert = null;
		//initialize parameters
		try {
			String[] parameter = {title, tags, lectureseriesId+"", ownerId+"", producerId+"", containerFormat, filename, resolution, duration, hostId+"", metadataId+"", facilityId+"", citation2go+""}; 
			pscInsert = new Video_INSERT_PreparedStatement(this.getJdbcTemplate(), parameter);
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
	 * @param title the title
	 * @param tags the tags
	 * @param lectureseriesId the lectureseries id
	 * @param ownerId the owner id
	 * @param producerId the producer id
	 * @param containerFormat the container format
	 * @param filename the filename
	 * @param resolution the resolution
	 * @param duration the duration
	 * @param hostId the host id
	 * @param textId the text id
	 * @param filesize the filesize
	 * @param generationDate the generation date
	 * @param openAccess the open access
	 * @param downloadAllawed the download allawed
	 * @param metadataId the metadata id
	 * @param surl the surl
	 * @param hits the hits
	 * @param permittedToSegment the permitted to segment
	 * @param facilityId the facility id
	 * @param citation2go the citation2go
	 * @param id the id
	 */
	public void updateById(String title, String tags, int lectureseriesId, int ownerId, int producerId, String containerFormat, String filename, String resolution, String duration, int hostId, int textId, Long filesize, String generationDate, boolean openAccess, boolean downloadAllawed, int metadataId, String surl, int hits, boolean permittedToSegment, int facilityId, int citation2go, int id) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE video SET title=?, tags=?, lectureseriesId=?, ownerId=?, producerId=?, containerFormat=?, filename=?, resolution=?, duration=?, hostId=?, textId=?, filesize=?, generationDate=?, openAccess=?, downloadLink=?, metadataId=?, surl=?, hits=?, permittedToSegment=?, facilityId=?, citation2go=? WHERE id=?", new Object[] { title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadAllawed, metadataId, surl, hits, permittedToSegment, facilityId, citation2go, id });
	}

	/**
	 * Gets the by producer and video filename.
	 *
	 * @param producer the producer
	 * @param videoFileName the video file name
	 * @return the by producer and video filename
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByProducerAndVideoFilename(Producer producer, String videoFileName) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE ( filename=? AND producerId=? )", new Object[] { videoFileName, producer.getId() }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Activate openaccess.
	 *
	 * @param id the id
	 */
	public void activateOpenaccess(int id) {

		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE video SET openAccess=true, surl=null WHERE id=?", new Object[] { id });
	}

	/**
	 * Deactivate openaccess.
	 *
	 * @param id the id
	 */
	public void deactivateOpenaccess(int id) {

		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		Video v = this.getById(id).iterator().next();
		String secureFileName = Security.createSecureFileName() + "." + v.getFilenameFormat();
		update.update("UPDATE video SET openAccess=false, surl='" + secureFileName + "' WHERE id=?", new Object[] { id });
	}

	/**
	 * Activate download.
	 *
	 * @param id the id
	 */
	public void activateDownload(int id) {

		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE video SET downloadLink=true WHERE id=?", new Object[] { id });
	}

	/**
	 * Deactivate download.
	 *
	 * @param id the id
	 */
	public void deactivateDownload(int id) {

		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE video SET downloadLink=false WHERE id=?", new Object[] { id });
	}

	/**
	 * Gets the by filename.
	 *
	 * @param filename the filename
	 * @return the by filename
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByFilename(String filename) {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video  WHERE filename LIKE '%" + filename + "%'", new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Gets the by open access.
	 *
	 * @return the by open access
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByOpenAccess() {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE openAccess=1 ORDER BY generationDate DESC", new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Gets the by open access and duration not null.
	 *
	 * @return the by open access and duration not null
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByOpenAccessAndDurationNotNull() {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE (openAccess=1 AND duration!='') ORDER BY generationDate DESC", new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Gets the by open access and lectureseries id.
	 *
	 * @param lectureseriesId the lectureseries id
	 * @param order the order
	 * @return the by open access and lectureseries id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByOpenAccessAndLectureseriesId(Integer lectureseriesId, String order) {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE (openAccess=1 AND lectureseriesId=?) ORDER BY generationDate "+order, new Object[] { lectureseriesId }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Gets the ids by open access and lectureseries id.
	 *
	 * @param lectureseriesId the lectureseries id
	 * @param order the order
	 * @return the ids by open access and lectureseries id
	 */
	public List<?> getIdsByOpenAccessAndLectureseriesId(Integer lectureseriesId, String order) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.queryForList("SELECT id FROM video WHERE (openAccess=1 AND lectureseriesId=?) ORDER BY generationDate "+order, new Object[] { lectureseriesId });
	}
	
	/**
	 * Gets the by open access and counter.
	 *
	 * @param start the start
	 * @param ende the ende
	 * @return the by open access and counter
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByOpenAccessAndCounter(Integer start, Integer ende) {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE openAccess=1 ORDER BY generationDate DESC LIMIT ?, ?;", new Object[] { start, ende }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Gets the by open access and counter and facility id.
	 *
	 * @param start the start
	 * @param ende the ende
	 * @param facilityId the facility id
	 * @return the by open access and counter and facility id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByOpenAccessAndCounterAndFacilityId(Integer start, Integer ende, Integer facilityId) {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT * FROM video, video_facility WHERE (video.openAccess=1 AND video.id=video_facility.videoId AND video_facility.facilityId=?) ORDER BY id DESC LIMIT ?,?;", new Object[] { facilityId, start, ende }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Gets the by open access and counter and facility id and parent id.
	 *
	 * @param start the start
	 * @param ende the ende
	 * @param facilityId the facility id
	 * @param parentId the parent id
	 * @return the by open access and counter and facility id and parent id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByOpenAccessAndCounterAndFacilityIdAndParentId(Integer start, Integer ende, Integer facilityId, Integer parentId) {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT * FROM video, video_facility WHERE ( video.openAccess=1 AND (video.id=video_facility.videoId AND video_facility.facilityId=?) OR (video.id=video_facility.videoId AND video_facility.facilityId=?) ) ORDER BY id DESC LIMIT ?,?;", new Object[] { facilityId, parentId, start, ende }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Gets the by open access and facility id.
	 *
	 * @param facilityId the facility id
	 * @return the by open access and facility id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByOpenAccessAndFacilityId(Integer facilityId) {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT * FROM video, video_facility WHERE ( video.openAccess=1 AND video.id=video_facility.videoId AND video_facility.facilityId=? ) ORDER BY id DESC;", new Object[] { facilityId }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Gets the by facility id.
	 *
	 * @param facilityId the facility id
	 * @return the by facility id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByFacilityId(Integer facilityId) {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT * FROM video, video_facility WHERE video.id=video_facility.videoId AND video_facility.facilityId=? ORDER BY id DESC;", new Object[] { facilityId }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);}catch(NullPointerException npe){} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Count videos from facility.
	 *
	 * @param facilityId the facility id
	 * @return the int
	 */
	public int countVideosFromFacility(Integer facilityId) {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		int i = select.queryForInt("SELECT COUNT(*) FROM video, video_facility WHERE video.id=video_facility.videoId AND video_facility.facilityId=?;", new Object[] { facilityId });
		
		return i;
	}
	
	/**
	 * Gets the by open facility id.
	 *
	 * @param facilityId the facility id
	 * @return the by open facility id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByOpenFacilityId(Integer facilityId) {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT * FROM video, video_facility WHERE ( video.id=video_facility.videoId AND video_facility.facilityId=? ) ORDER BY id DESC;", new Object[] { facilityId }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Gets the number open access lectureseries.
	 *
	 * @param facilityId the facility id
	 * @param tree the tree
	 * @return the number open access lectureseries
	 */
	public Integer getNumberOpenAccessLectureseries(Integer facilityId, String tree) {
		List<?> retNum;

		//use jdbc template
		JdbcTemplate jdbst = new JdbcTemplate(this.getDataSource());
		//prepare query
		String sqlquery="";
		//prepare facilities list 
		//get all sub-facilitye from input-id
		List<Facility> einList = null;
		String partEinQuery="";

		//if facilityId choosen
		if (facilityId!=0){
			einList = facilityDao.getByParentId(facilityId);
			//add parentId-facility himself to list
			Facility ein = facilityDao.getById(facilityId).iterator().next();
			einList.add(ein);	
			//now get all videos for each facility from list
			//prepare string for sql-query
			Iterator<Facility> iE = einList.iterator();
			partEinQuery="(";
			while (iE.hasNext()) {
				Facility e = iE.next();
				partEinQuery += "video_facility.facilityId="+e.getId()+" || ";
			}
			// and update part query
			partEinQuery +="video_facility.facilityId="+facilityId+")";
			sqlquery = "SELECT COUNT(*) FROM video, video_facility, facility WHERE ( video.openAccess=1 AND video.id=video_facility.videoId AND "+partEinQuery+" AND video_facility.facilityId=facility.id AND facility.typ='"+tree+"' ) GROUP BY lectureseriesId ORDER BY video.generationDate DESC ";
		}else{
			sqlquery= "SELECT COUNT(*) FROM video, video_facility, facility WHERE ( video.openAccess=1 AND video.id=video_facility.videoId AND video_facility.facilityId=facility.id AND  facility.typ='"+tree+"') GROUP BY lectureseriesId ORDER BY video.generationDate DESC ";
		}
		
		//execute
		retNum = jdbst.queryForList(sqlquery);
		
		return retNum.size();
	}

	/**
	 * Gets the all lectureseries whith openaccess videos.
	 *
	 * @param tree the tree
	 * @return the all lectureseries whith openaccess videos
	 */
	@SuppressWarnings("unchecked")
	private final List<Lectureseries> getAllLectureseriesWhithOpenaccessVideos(String tree){
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		String sql="";
		if(tree.equals("all")) sql="SELECT number, eventType, eventCategory, lectureseries.name, shortDesc, longDesc, semesterName, language, facultyName, instructorsString, lectureseries.id, password, approved FROM lectureseries, video, video_facility, facility WHERE ( lectureseries.id=video.lectureseriesId AND video.openAccess=1 AND video.id=video_facility.videoId AND video_facility.facilityId=facility.id) GROUP BY lectureseriesId ";
		else sql = "SELECT number, eventType, eventCategory, lectureseries.name, shortDesc, longDesc, semesterName, language, facultyName, instructorsString, lectureseries.id, password, approved FROM lectureseries, video, video_facility, facility WHERE ( lectureseries.id=video.lectureseriesId AND video.openAccess=1 AND video.id=video_facility.videoId AND video_facility.facilityId=facility.id AND facility.typ='"+tree+"') GROUP BY lectureseriesId ";
		return select.query(sql, new LectureseriesRowMapper());
	}
	
	
	/**
	 * Gets the open access videos.
	 *
	 * @param start the start
	 * @param ende the ende
	 * @param facilityId the facility id
	 * @param tree the tree
	 * @return the open access videos
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getOpenAccessVideos(Integer start, Integer ende, Integer facilityId, String tree) {
		//use jdbc template
		JdbcTemplate jdbst = new JdbcTemplate(this.getDataSource());
		//prepare result list
		List<Video> returnList = null;

		//Prepare part query for lectureseries
		//get all lectureseries open access videos
		List<Lectureseries> lectureseriesList = getAllLectureseriesWhithOpenaccessVideos(tree);
		Iterator<Lectureseries> iC = lectureseriesList.iterator();
		
		//for every lectureseriesId get latest video - tree 1
		String partVideoIdQuery="";
		partVideoIdQuery+="(";
		while (iC.hasNext()) {
			Lectureseries c = iC.next();
			if(iC.hasNext())partVideoIdQuery += "video.id="+getLatestOpenAccessVideoIdFromLectureseriesId(c.getId())+" OR ";
			else partVideoIdQuery += "video.id="+getLatestOpenAccessVideoIdFromLectureseriesId(c.getId());
		}
		partVideoIdQuery+=")";
		
		//prepare facilities list 
		//get all sub-facilitye from input-id
		List<Facility> einList = null;
		String partEinQuery="";
		//prepare query
		String sqlquery="";
		//if facilityId choosen
		if (facilityId!=0){
			einList = facilityDao.getByParentId(facilityId);
			//add parentId-facility himself to list
			Facility ein = facilityDao.getById(facilityId).iterator().next();
			einList.add(ein);	
			//now get all videos for each facility from list
			//prepare string for sql-query
			Iterator<Facility> iE = einList.iterator();
			partEinQuery="(";
			while (iE.hasNext()) {
				Facility e = iE.next();
				partEinQuery += "video_facility.facilityId="+e.getId()+" OR ";
			}
			// and update part query
			partEinQuery +="video_facility.facilityId="+facilityId+")";
			
			//check tree
			if(tree.equals("all"))sqlquery = "SELECT video.id, video.title, video.tags, video.lectureseriesId, video.ownerId, video.producerId, video.containerFormat, video.filename, video.resolution, video.duration, video.hostId, video.textId, video.filesize, video.generationDate, video.openAccess, video.downloadLink, video.metadataId, video.surl, video.hits, video.uploadDate, video.permittedToSegment, video.facilityId, video.citation2go FROM video, video_facility, facility WHERE ( "+partVideoIdQuery+" AND  video.id=video_facility.videoId AND "+partEinQuery+" AND video_facility.facilityId=facility.id ) GROUP BY lectureseriesId ORDER BY video.generationDate DESC ";
			else sqlquery = "SELECT video.id, video.title, video.tags, video.lectureseriesId, video.ownerId, video.producerId, video.containerFormat, video.filename, video.resolution, video.duration, video.hostId, video.textId, video.filesize, video.generationDate, video.openAccess, video.downloadLink, video.metadataId, video.surl, video.hits, video.uploadDate, video.permittedToSegment, video.facilityId, video.citation2go FROM video, video_facility, facility WHERE ( "+partVideoIdQuery+" AND  video.id=video_facility.videoId AND "+partEinQuery+" AND video_facility.facilityId=facility.id AND facility.typ='"+tree+"' ) GROUP BY lectureseriesId ORDER BY video.generationDate DESC ";
		}else{
			//check tree
			if(tree.equals("all"))sqlquery = "SELECT video.id, video.title, video.tags, video.lectureseriesId, video.ownerId, video.producerId, video.containerFormat, video.filename, video.resolution, video.duration, video.hostId, video.textId, video.filesize, video.generationDate, video.openAccess, video.downloadLink, video.metadataId, video.surl, video.hits, video.uploadDate, video.permittedToSegment, video.facilityId, video.citation2go FROM video, video_facility, facility WHERE ( "+partVideoIdQuery+" AND  video.id=video_facility.videoId AND video_facility.facilityId=facility.id ) GROUP BY lectureseriesId ORDER BY video.generationDate DESC ";
			else sqlquery = "SELECT video.id, video.title, video.tags, video.lectureseriesId, video.ownerId, video.producerId, video.containerFormat, video.filename, video.resolution, video.duration, video.hostId, video.textId, video.filesize, video.generationDate, video.openAccess, video.downloadLink, video.metadataId, video.surl, video.hits, video.uploadDate, video.permittedToSegment, video.facilityId, video.citation2go FROM video, video_facility, facility WHERE ( "+partVideoIdQuery+" AND  video.id=video_facility.videoId AND video_facility.facilityId=facility.id AND facility.typ='"+tree+"' ) GROUP BY lectureseriesId ORDER BY video.generationDate DESC ";
		}
		//and prepare with output - limit
		sqlquery+="LIMIT "+start+" , "+ende+";";
		
		try {
			returnList = fillVideoListWithProperties(jdbst.query(sqlquery, new VideoRowMapper()));
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return returnList;
	}

	
	/**
	 * Gets the latest videos.
	 *
	 * @param start the start
	 * @param ende the ende
	 * @param facilityId the facility id
	 * @param tree the tree
	 * @return the latest videos
	 */
	public List<Video> getLatestVideos(int start, int ende, int facilityId, String tree) {
		List<Video> returnList = new ArrayList<Video>();
		try{
			Iterator<Video> i = getOpenAccessVideos(start, ende, facilityId, tree).iterator();
			while (i.hasNext()) {
				Video o = i.next();
				Video vid = new Video();
				//if conference, get latest lecture
				if(tree.equals("tree1"))vid = getLatestLactureFromlectureseriesId(o.getLectureseriesId());
				//if lecture, get latest lecture
				if(tree.equals("all"))vid = getLatestLactureFromlectureseriesId(o.getLectureseriesId());
				
				if (vid.getId() != 0)returnList.add(vid);
			}
			Comparator<Video> comparator = new VideoGenerationDateComparator();
			java.util.Collections.sort(returnList, comparator);
		}catch(NullPointerException npe){}
		return returnList;
	}
	

	/**
	 * Gets the open access videos by facility id.
	 *
	 * @param facilityId the facility id
	 * @return the open access videos by facility id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getOpenAccessVideosByFacilityId(Integer facilityId) {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());

		List<Video> result = null;

		// if facilityId==0, select all
		if (facilityId == 0) {
			result = select.query("SELECT video.id, video.title, video.tags, video.lectureseriesId, video.ownerId, video.producerId, video.containerFormat, video.filename, video.resolution, video.duration, video.hostId, video.textId, video.filesize, video.generationDate, video.openAccess, video.downloadLink, video.metadataId, video.surl, video.hits, video.uploadDate, video.permittedToSegment, video.facilityId, video.citation2go FROM video, video_facility, facility WHERE ( video.openAccess=1 AND video.id=video_facility.videoId AND video_facility.facilityId=facility.id AND facility.typ!='tree2' ) GROUP BY lectureseriesId ORDER BY video.generationDate DESC;", new VideoRowMapper());
		} else {
			result = select.query("SELECT video.id, video.title, video.tags, video.lectureseriesId, video.ownerId, video.producerId, video.containerFormat, video.filename, video.resolution, video.duration, video.hostId, video.textId, video.filesize, video.generationDate, video.openAccess, video.downloadLink, video.metadataId, video.surl, video.hits, video.uploadDate, video.permittedToSegment, video.facilityId, video.citation2go FROM video, video_facility, facility WHERE ( video.openAccess=1 AND video.id=video_facility.videoId AND video_facility.facilityId=? AND video_facility.facilityId=facility.id AND facility.typ!='tree2' ) GROUP BY lectureseriesId ORDER BY video.generationDate DESC;", new Object[] { facilityId }, new VideoRowMapper());
		}
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		return result;
	}

	/**
	 * Gets the by producer id and counter.
	 *
	 * @param remoteUserId the remote user id
	 * @param start the start
	 * @param ende the ende
	 * @return the by producer id and counter
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByProducerIdAndCounter(Integer remoteUserId, Integer start, Integer ende) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE producerId=? ORDER BY id DESC LIMIT ?,?;", new Object[] { remoteUserId, start, ende }, new VideoRowMapper());
		try {fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Gets the by producer id and counter andlectureseries id.
	 *
	 * @param remoteUserId the remote user id
	 * @param start the start
	 * @param ende the ende
	 * @param lectureseriesId the lectureseries id
	 * @return the by producer id and counter andlectureseries id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getByProducerIdAndCounterAndlectureseriesId(Integer remoteUserId, Integer start, Integer ende, Integer lectureseriesId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE ( producerId=? AND lectureseriesId=?) ORDER BY id DESC LIMIT ?, ?;", new Object[] { remoteUserId, lectureseriesId, start, ende }, new VideoRowMapper());
		try {fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}



	/**
	 * Gets the video result search list by splitted search word.
	 *
	 * @param word the word
	 * @param limit the limit
	 * @return the video result search list by splitted search word
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getVideoResultSearchListBySplittedSearchWord(String word, int limit) {

		List<Video> returnList = new ArrayList<Video>();
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		Iterator<VideoResultSearch> i;

		// split the search string and add all results to the return list
		String[] splitSearchWord = word.split(" ");

		// clean the list first
		returnList2.removeAll(returnList2);
		int count = 0;

		// search for in particular words
		for (int z = 0; z < splitSearchWord.length; z++) {
			List<VideoResultSearch> result = null;
			// find it!
			String splittedword = splitSearchWord[z];
			result = select.query("SELECT  video.id, video.title,  metadata.creator, lectureseries.name, video.filename, video.generationDate, facility.typ FROM video, metadata, lectureseries, facility WHERE ( ( video.openAccess=1 AND video.metadataId = metadata.id AND video.lectureseriesId = lectureseries.id AND video.facilityId=facility.id ) AND (( lectureseries.name LIKE '%" + splittedword + "%' ) OR ( lectureseries.number LIKE '%" + splittedword + "%' ) OR ( lectureseries.instructorsString LIKE '%" + splittedword + "%' ) OR ( video.title LIKE '%" + splittedword + "%' ) OR ( video.tags LIKE '%" + splittedword + "%' ) OR ( metadata.creator LIKE '%" + splittedword + "%' ) OR ( metadata.description LIKE '%" + splittedword + "%' ) ));", new VideoResultSearchRowMapper());

			i = result.iterator();
			// if results more than $limit, don't save them to the result list

			while (i.hasNext()) {
				VideoResultSearch vid = i.next();
				if (count <= limit) {
					if (vid.getId() != 0 && !videoPresentInReturnList2(vid) && !videoPresentInReturnList(vid)) {
						returnList2.add(vid);
						count++;
					}
				}
			}

		}
		returnList = returnList2;
		
		return returnList;
	}

	/**
	 * Gets the video result search list by search word.
	 *
	 * @param word the word
	 * @param limit the limit
	 * @return the video result search list by search word
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getVideoResultSearchListBySearchWord(String word, int limit) {

		returnList = new ArrayList<Video>();
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());

		Iterator<VideoResultSearch> i;
		List<VideoResultSearch> result = null;

		// find it!
		result = select.query("SELECT  video.id, video.title,  metadata.creator, lectureseries.name, video.filename, video.generationDate, facility.typ FROM video, metadata, lectureseries, facility WHERE ( ( video.openAccess=1 AND video.metadataId = metadata.id AND video.lectureseriesId = lectureseries.id AND video.facilityId=facility.id ) AND (( lectureseries.name LIKE '%" + word + "%' ) OR ( lectureseries.number LIKE '%" + word + "%' ) OR ( lectureseries.instructorsString LIKE '%" + word + "%' ) OR ( video.title LIKE '%" + word + "%' ) OR ( video.tags LIKE '%" + word + "%' ) OR ( metadata.creator LIKE '%" + word + "%' ) OR ( metadata.description LIKE '%" + word + "%' ) ));", new VideoResultSearchRowMapper());

		i = result.iterator();
		while (i.hasNext()) {
			Video vid = i.next();
			if (vid.getId() != 0 && !videoPresentInReturnList(vid)) returnList.add(vid);
		}
		
		return returnList;
	}

	/**
	 * Video present in return list.
	 *
	 * @param video the video
	 * @return true, if successful
	 */
	private boolean videoPresentInReturnList(Video video) {

		boolean returN = false;
		Iterator<Video> i = returnList.iterator();
		while (i.hasNext()) {
			Video vid = i.next();
			if (vid.getId() == video.getId()) returN = true;
		}
		return returN;
	}

	/**
	 * Video present in return list2.
	 *
	 * @param video the video
	 * @return true, if successful
	 */
	private boolean videoPresentInReturnList2(Video video) {

		boolean returN = false;
		Iterator<Video> i = returnList2.iterator();
		while (i.hasNext()) {
			Video vid = i.next();
			if (vid.getId() == video.getId()) returN = true;
		}
		return returN;
	}

	/**
	 * Gets the open access videos by lectureseries number.
	 *
	 * @param lectureseriesId the lectureseries id
	 * @param sort the sort
	 * @return the open access videos by lectureseries number
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getOpenAccessVideosByLectureseriesNumber(Integer lectureseriesId, String sort) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = null;
		result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE ( lectureseriesId=? AND openAccess=1 ) ORDER BY generationDate " + sort + ";", new Object[] { lectureseriesId }, new VideoRowMapper());
		try {fillVideoListWithProperties(result);} catch (IOException e) {e.printStackTrace();}
		return result;
	}

	/**
	 * Increment hits for video.
	 *
	 * @param video the video
	 */
	public void incrementHitsForVideo(Video video) {
		int hits = video.getHits() + 1;
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE video SET hits=? WHERE id=?", new Object[] { hits, video.getId() });
	}

	/**
	 * Gets the closed access videos by lectureseries id.
	 *
	 * @param lectureseriesId the lectureseries id
	 * @param sort the sort
	 * @return the closed access videos by lectureseries id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getClosedAccessVideosByLectureseriesId(Integer lectureseriesId, String sort) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = null;
		result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE ( lectureseriesId=? AND openAccess=0 AND surl>'') ORDER BY generationDate " + sort + ";", new Object[] { lectureseriesId }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		return result;
	}

	/**
	 * Gets the first lacture fromlectureseries id.
	 *
	 * @param lectureseriesId the lectureseries id
	 * @return the first lacture fromlectureseries id
	 */
	public Video getFirstLactureFromlectureseriesId(Integer lectureseriesId) {
		List<?> l = this.getIdsByOpenAccessAndLectureseriesId(lectureseriesId,"ASC");
		ListOrderedMap lid = (ListOrderedMap) l.listIterator().next();
		Integer id = (Integer) lid.get("id");
		Video vid = this.getById(id).iterator().next();
		return vid;
	}
	
	/**
	 * Gets the latest lacture fromlectureseries id.
	 *
	 * @param lectureseriesId the lectureseries id
	 * @return the latest lacture fromlectureseries id
	 */
	public Video getLatestLactureFromlectureseriesId(Integer lectureseriesId) {
		List<?> l = this.getIdsByOpenAccessAndLectureseriesId(lectureseriesId,"DESC");
		ListOrderedMap lid = (ListOrderedMap) l.listIterator().next();
		Integer id = (Integer) lid.get("id");
		Video vid = this.getById(id).iterator().next();
		return vid;
	}
	
	/**
	 * Gets the latest open access video id from lectureseries id.
	 *
	 * @param id the id
	 * @return the latest open access video id from lectureseries id
	 */
	public Integer getLatestOpenAccessVideoIdFromLectureseriesId(Integer id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		String sql ="SELECT video.id FROM video WHERE ( lectureseriesId='"+id+"' AND video.openaccess= '1' AND generationDate=(SELECT max(generationDate) FROM video WHERE lectureseriesId='"+id+"' AND openaccess = '1' ) ) GROUP BY generationDate ";
		return select.queryForInt(sql);
	}
	
	/**
	 * Gets the video list by lectureseries id and producer.
	 *
	 * @param lectureseriesId the lectureseries id
	 * @param producerId the producer id
	 * @return the video list by lectureseries id and producer
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getVideoListByLectureseriesIdAndProducer(int lectureseriesId, int producerId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE ( lectureseriesId = ? AND producerId =?)", new Object[] { lectureseriesId, producerId }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		return result;
	}

	/**
	 * Gets the number videos by lectureseries id and producer.
	 *
	 * @param lectureseriesId the lectureseries id
	 * @param producerId the producer id
	 * @return the number videos by lectureseries id and producer
	 */
	public int getNumberVideosByLectureseriesIdAndProducer(int lectureseriesId, int producerId) {
		JdbcTemplate jdbst = new JdbcTemplate(this.getDataSource());
		List<?> retNum = jdbst.queryForList("SELECT id FROM video WHERE ( lectureseriesId = ? AND producerId =?)", new Object[] { lectureseriesId, producerId });
		return retNum.size();
	}
	
	/**
	 * Gets the locked by producer id.
	 *
	 * @param remoteUserId the remote user id
	 * @return the locked by producer id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getLockedByProducerId(int remoteUserId) {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE ( producerId=? AND downloadLink=0 ) ORDER BY id DESC", new Object[] { remoteUserId }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Gets the locked by lectureseries id.
	 *
	 * @param lectureseriesId the lectureseries id
	 * @return the locked by lectureseries id
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getLockedByLectureseriesId(int lectureseriesId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video WHERE lectureseriesId=? AND downloadLink=0 ORDER BY id DESC", new Object[] { lectureseriesId }, new VideoRowMapper());
		try {this.fillVideoListWithProperties(result);} catch (IOException e) {}
		
		return result;
	}

	/**
	 * Filename exists.
	 *
	 * @param filename the filename
	 * @return true, if successful
	 */
	@SuppressWarnings("unchecked")
	public boolean filenameExists(String filename) {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Video> result = select.query("SELECT id, title, tags, lectureseriesId, ownerId, producerId, containerFormat, filename, resolution, duration, hostId, textId, filesize, generationDate, openAccess, downloadLink, metadataId, surl, hits, uploadDate, permittedToSegment, facilityId, citation2go FROM video  WHERE filename LIKE '%" + filename + "%'", new VideoRowMapper());
		boolean returN = false;
		if (result.size() > 0) returN = true;

		return returN;
	}

	/**
	 * Sets the upload date.
	 *
	 * @param d the d
	 * @param vId the v id
	 */
	public void setUploadDate(Date d, int vId) {

		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE video SET uploadDate=? WHERE id=?", new Object[] { d, vId });
	}

	/**
	 * Gets the facility idfor video.
	 *
	 * @param videoId the video id
	 * @return the facility idfor video
	 */
	public int getFacilityIdforVideo(int videoId){
		int facilityId;
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		int parentId = select.queryForInt("SELECT facilityId FROM video_facility WHERE videoId = ?", new Object[] { videoId });
		facilityId = select.queryForInt("SELECT parentId FROM facility WHERE id = ?",  new Object[] { parentId });
		return facilityId;
	}
	
	/**
	 * Fill video list with properties.
	 *
	 * @param videoList the video list
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<Video> fillVideoListWithProperties(List<Video> videoList) throws IOException {
		ListIterator<Video> it = videoList.listIterator();
		//TODO: Eigentlich brauchen wir den Counter nicht mehr!!
		int counter = 0;
		while (it.hasNext()) {
			counter++;
			if (counter == 4) counter = 1;
			Video objectVideo = it.next();
			objectVideo.setCounter(counter);
			Host objectHost = hostDao.getById(objectVideo.getHostId()).iterator().next();
			Producer objectProducer = producerDao.getByUserId(objectVideo.getProducerId()).iterator().next();
			try{
				Lectureseries lectureseries = lectureseriesDao.getById(objectVideo.getLectureseriesId()).iterator().next();
				objectVideo.setObjectLectureseries(lectureseries);
			}catch(NoSuchElementException nse){}
			Metadata metadata = metadataDao.getById(objectVideo.getMetadataId()).iterator().next();
			
			// prepare video short name
			String video_shortname = objectVideo.getShortTitle();
			if (video_shortname.length() > 45) video_shortname = video_shortname.substring(0, 45) + "...";
			objectVideo.setShortTitle(video_shortname);
			objectVideo.setShortName(metadata.getCreator().split(" ")[metadata.getCreator().split(" ").length - 1]);
			
			objectVideo.setObjectHost(objectHost);
			objectVideo.setObjectProducer(objectProducer);
			objectVideo.setObjectMetadata(metadata);
			
			List<Mark> segmentList = segmentDao.getMarkByVideoId(objectVideo.getId());
			//segments
			if(segmentList.size()!=0)objectVideo.setSegmentList(segmentList);
			else objectVideo.setSegmentList(null);
			
			try{
				// images
				String image ="";
				String imageSmall ="";
				String imageMedium ="";
				String videoPfad="";
				
				if (objectVideo.isOpenaccess()){
					videoPfad = L2goPropsUtil.get("lecture2go.media.repository") + "/" + objectHost.getServerRoot() + "/" + objectProducer.getHomeDir() + "/" + objectVideo.getFilename();
					image = objectVideo.getPreffix() + ".jpg";
					imageSmall = objectVideo.getPreffix() + "_s.jpg";
					imageMedium = objectVideo.getPreffix() + "_m.jpg";
				}else{
					videoPfad = L2goPropsUtil.get("lecture2go.media.repository") + "/" + objectHost.getServerRoot() + "/" + objectProducer.getHomeDir() + "/" +  objectVideo.getSecureFilename();
					image = objectVideo.getSPreffix()+ ".jpg";
					imageSmall = objectVideo.getSPreffix() + "_s.jpg";
					imageMedium = objectVideo.getSPreffix() + "_m.jpg";
				}
				
				//thumbnails
				// set thumbnail
				//if audio file
				if (objectVideo.getUploadType().equals("audio")){
					objectVideo.setImage(L2goPropsUtil.get("lecture2go.web.root") + L2goPropsUtil.get("lecture2go.theme.root.path") + "/images/l2go/audio_only_big.png");
					objectVideo.setImageSmall(L2goPropsUtil.get("lecture2go.web.root") + L2goPropsUtil.get("lecture2go.theme.root.path") + "/images/l2go/audio_only_small.png");
					objectVideo.setImageMedium(L2goPropsUtil.get("lecture2go.web.root") + L2goPropsUtil.get("lecture2go.theme.root.path") + "/images/l2go/audio_only_medium.png");
				}
				
				//is video
				if (objectVideo.getUploadType().equals("video")){
					File videoFile = new File(videoPfad);
					if (videoFile.isFile()){
						if(!((FFmpegManager) getUtilityBeanFactory().getBean("ffmgepManager")).thumbnailsExists(objectVideo)){
							// create thumbnail
							String thumbnailLocation = L2goPropsUtil.get("lecture2go.images.system.path") + "/" + image;
							((FFmpegManager) getUtilityBeanFactory().getBean("ffmgepManager")).createThumbnail(videoPfad, thumbnailLocation);							
						}
						objectVideo.setImage(L2goPropsUtil.get("lecture2go.web.root") + "/images/" + image);
						objectVideo.setImageSmall(L2goPropsUtil.get("lecture2go.web.root") + "/images/" + imageSmall);
						objectVideo.setImageMedium(L2goPropsUtil.get("lecture2go.web.root") + "/images/" + imageMedium);						
					}else{
						String img = L2goPropsUtil.get("lecture2go.web.root") + L2goPropsUtil.get("lecture2go.theme.root.path")  + "/images/l2go/noimage.jpg";
						objectVideo.setImage(img);
						objectVideo.setImageSmall(img);
						objectVideo.setImageMedium(img);						
					}
				}
				
				// date
				// extract time and date from the originalFileName
				String[] parameter = objectVideo.getGenerationDate().split("\\_");
				// check parameter 3 - this is the date
				String l2gDate = parameter[0];
				String l2gTime = parameter[1];
				objectVideo.setDate(l2gDate.split("\\-")[2] + "." + l2gDate.split("\\-")[1] + "." + l2gDate.split("\\-")[0] + " - " + l2gTime.split("\\-")[0] + ":" + l2gTime.split("\\-")[1]);
				objectVideo.setSimpleDate(l2gDate.split("\\-")[2] + "." + l2gDate.split("\\-")[1] + "." + l2gDate.split("\\-")[0] );

				// set preffix and filename
				String preffix = "";
				String filename = "";
				if (objectVideo.isOpenaccess()){
					preffix = objectVideo.getPreffix();
					filename = objectVideo.getFilename();
				}else{
					preffix = objectVideo.getSPreffix();
					filename = objectVideo.getSecureFilename();
				}
				
				String homedirPath = "";
				homedirPath = L2goPropsUtil.get("lecture2go.media.repository") + "/" + objectHost.getServerRoot() + "/" + objectProducer.getHomeDir() + "/" +preffix;
					
 				//additional files
				try {
					File mp4File = new File(homedirPath + ".mp4");
					File mp3File = new File(homedirPath + ".mp3");
					File m4vFile = new File(homedirPath + ".m4v");
					File pdfFile = new File(homedirPath + ".pdf");
					File m4aFile = new File(homedirPath + ".m4a");
					//
					if (mp4File.isFile()) objectVideo.setMp4File(mp4File);
					if (mp3File.isFile()) objectVideo.setMp3File(mp3File);
					if (m4vFile.isFile()) objectVideo.setM4vFile(m4vFile);
					if (pdfFile.isFile()) objectVideo.setPdfFile(pdfFile);
					if (m4aFile.isFile()) objectVideo.setM4aFile(m4aFile);
				} catch (Exception e) {
					//
				}
			
				//URL
				String webhome = L2goPropsUtil.get("lecture2go.web.home");	
				try{
					if (webhome.contains("localhost"))webhome+="/web/vod";
					if ((facilityDao.getByVideoId(objectVideo.getId()).iterator().next()).getTyp().equals("tree1"))objectVideo.setUrl(webhome+"/l2go/-/v/"+objectVideo.getId());
				}catch(NoSuchElementException nseex){}

				//SURL
				if(!objectVideo.isOpenaccess())objectVideo.setSecureUrl(webhome+"/lecture/-/sv/"+objectVideo.getSPreffix());
				
				//Streaming-URL
				String streamUrl="";
				String streamIosUrl="";
				String streamAndroidUrl="";
				
				//base url
				String url="";
				
				//wowza stream 
				if(objectVideo.getStreamType().equals("wowza")){//TODO stream type into context.xml
					if (objectVideo.getUploadType().equals("audio")) url=objectHost.getProtokoll()+"://"+objectHost.getStreamer()+"/vod/_definst_/mp3:"+objectVideo.getFacilityId()+"l2g"+objectProducer.getHomeDir()+"/"+filename;
					else url=objectHost.getProtokoll()+"://"+objectHost.getStreamer()+"/vod/_definst_/mp4:"+objectVideo.getFacilityId()+"l2g"+objectProducer.getHomeDir()+"/"+filename;
					
					streamUrl=url+"/manifest.f4m"; //normal
					streamIosUrl=url+"/playlist.m3u8"; //iOS	
					streamAndroidUrl=url+"/playlist.m3u8"; //android
				}
				objectVideo.setStreamUrl(streamUrl); //normal
				objectVideo.setStreamIosUrl(streamIosUrl); //normal
				objectVideo.setStreamAndroidUrl(streamAndroidUrl); //normal
				
				//has chapters
				if(segmentDao.getChapterByVideoId(objectVideo.getId()).size()>0)objectVideo.setHasChapters(true);
							
				//has comments
				if(segmentDao.getCommentsByVideoId(objectVideo.getId()).size()>0)objectVideo.setHasComments(true);
				
			}catch(Exception e){}
		}
		return videoList;
	}
	
	/**
	 * Gets the popular videos rnd.
	 *
	 * @param facilityId the facility id
	 * @param limit the limit
	 * @return the popular videos rnd
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getPopularVideosRnd(Integer facilityId, Integer limit) {
		
		List<Video> liste = new ArrayList<Video>();
		List<Video> returnList = new ArrayList<Video>();
		String sqlquery="";
		JdbcTemplate jdbst = new JdbcTemplate(this.getDataSource());
		// ohne Facility
		if (facilityId == 0) {
			sqlquery= "SELECT v.id, v.title, v.tags, v.lectureseriesId, " +
			"v.ownerId, v.producerId, v.containerFormat, v.filename, v.resolution, " +
			"v.duration, v.hostId, v.textId, v.filesize, v.generationDate, v.openAccess, " +
			"v.downloadLink, v.metadataId, v.surl, v.hits, v.uploadDate, v.permittedToSegment, v.facilityId, v.citation2go " +
			"FROM videohitlist vh, video v WHERE v.id = vh.id GROUP BY v.id";
			liste = jdbst.query(sqlquery, new Object[] { }, new VideoRowMapper());
			
		} else {
			List<Facility> einList = null;
			String partEinQuery="";	
				einList = facilityDao.getByParentId(facilityId);
				Facility ein= facilityDao.getById(facilityId).iterator().next();
				einList.add(ein);	
				Iterator<Facility> iE = einList.iterator();
				partEinQuery="(";
				while (iE.hasNext()) {
					Facility e = iE.next();
					partEinQuery += "ve.facilityId="+e.getId()+" || ";
				}
				partEinQuery +="ve.facilityId="+facilityId+")";
				
			sqlquery= "SELECT v.id, v.title, v.tags, v.lectureseriesId, " +
			"v.ownerId, v.producerId, v.containerFormat, v.filename, v.resolution, " +
			"v.duration, v.hostId, v.textId, v.filesize, v.generationDate, v.openAccess, " +
			"v.downloadLink, v.metadataId, v.surl, v.hits, v.uploadDate, v.permittedToSegment, v.facilityId, v.citation2go " +
			"FROM videohitlist vh, video v, video_facility ve, facility e " +
			"WHERE v.id=ve.videoId " +
			"AND " + partEinQuery + " " +
			"AND v.id = vh.id GROUP BY v.id";
			liste = jdbst.query(sqlquery, new Object[] { }, new VideoRowMapper());
		}
		
		//Die Liste Randomisieren
		Collections.shuffle(liste);
		
		// Die ersten i-Rauspicken (je nachdem wie viele Angezeigt werden sollen), Pruefung auf Listengroesse wichtig falls Liste < Anzeiggroesse
		for(int i = 0 ; i<limit && i<liste.size(); i++)
		{
			if(liste.get(i)!=null)
			returnList.add(liste.get(i));
		}
			
		try {fillVideoListWithProperties(returnList);} catch (IOException e) {e.printStackTrace();}
		
		return returnList;
	}
	
	/**
	 * Gets the popular videos.
	 *
	 * @param start the start
	 * @param ende the ende
	 * @param facilityId the facility id
	 * @return the popular videos
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getPopularVideos(Integer start, Integer ende, Integer facilityId) {
		
//		System.out.println("facilityID: " + facilityId );
		List<Video> returnList = new ArrayList<Video>();
		String sqlquery="";
		JdbcTemplate jdbst = new JdbcTemplate(this.getDataSource());
		// ohne Facility
		if (facilityId == 0) {
			sqlquery= "SELECT v.id, v.title, v.tags, v.lectureseriesId, " +
			"v.ownerId, v.producerId, v.containerFormat, v.filename, v.resolution, " +
			"v.duration, v.hostId, v.textId, v.filesize, v.generationDate, v.openAccess, " +
			"v.downloadLink, v.metadataId, v.surl, v.hits, v.uploadDate, v.permittedToSegment, v.facilityId, v.citation2go " +
			"FROM videohitlist vh, video v WHERE v.id = vh.id ORDER BY hitsperday DESC LIMIT ?, ?";
			returnList = jdbst.query(sqlquery, new Object[] { start, ende }, new VideoRowMapper());
			
		} else {
			
			List<Facility> einList = null;
			String partEinQuery="";

				einList = facilityDao.getByParentId(facilityId);
				Facility ein = facilityDao.getById(facilityId).iterator().next();
				einList.add(ein);	
				Iterator<Facility> iE = einList.iterator();
				partEinQuery="(";
				while (iE.hasNext()) {
					Facility e = iE.next();
					partEinQuery += "ve.facilityId="+e.getId()+" || ";
				}
				partEinQuery +="ve.facilityId="+facilityId+")";
				
			sqlquery= "SELECT v.id, v.title, v.tags, v.lectureseriesId, " +
			"v.ownerId, v.producerId, v.containerFormat, v.filename, v.resolution, " +
			"v.duration, v.hostId, v.textId, v.filesize, v.generationDate, v.openAccess, " +
			"v.downloadLink, v.metadataId, v.surl, v.hits, v.uploadDate, v.permittedToSegment, v.facilityId, v.citation2go " +
			"FROM videohitlist vh, video v, video_facility ve, facility e " +
			"WHERE v.id=ve.videoId " +
			"AND " + partEinQuery + " " +
			"AND v.id = vh.id " +
			"GROUP BY lectureseriesId " +
			"ORDER BY hitsperday DESC " +
			"LIMIT ?, ?";
			returnList = jdbst.query(sqlquery, new Object[] { start, ende }, new VideoRowMapper());
		}
		
		try {fillVideoListWithProperties(returnList);} catch (IOException e) {e.printStackTrace();}
		
		return returnList;
	}
	
	/**
	 * Creates the popular video list.
	 */
	@SuppressWarnings("unchecked")
	public void createPopularVideoList() {
		List<Video> returnList = new ArrayList<Video>();
		JdbcTemplate jdbst = new JdbcTemplate(this.getDataSource());
		String sqlquery= "SELECT v.id, v.title, v.tags, v.lectureseriesId, " +
				"v.ownerId, v.producerId, v.containerFormat, v.filename, v.resolution, " +
				"v.duration, v.hostId, v.textId, v.filesize, v.generationDate, v.openAccess, " +
				"v.downloadLink, v.metadataId, v.surl, v.hits, v.uploadDate, v.permittedToSegment, v.facilityId, v.citation2go " +
				"FROM video v WHERE v.openAccess=1 AND v.hits > 20 ORDER BY hits DESC ";
		returnList = jdbst.query(sqlquery, new VideoRowMapper());

//		System.out.println("Size: " + returnList.size());
		
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE FROM videohitlist");
		
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTimeZone( TimeZone.getTimeZone("CET") );
		long msnow = calendar.getTimeInMillis();

		Date d1 = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm");

		for(Video v : returnList)
		{
			
			try {
				d1 = df.parse(v.getGenerationDate()); 
				long ms1   = d1.getTime();
				int hits = v.getHits();
				long timeinms = msnow - ms1;

				// Durschnittswerte berechnen

				//Berechne alter des Videos in...
				long days = timeinms / (1000*60*60*24); //...Tagen
				long week = timeinms / (1000*60*60*24*7); //...Wochen
				long month = timeinms / 2628000000l; //....Monaten
				long year = timeinms / (2628000000l*12l); //....Jahren
				
				//Berechne die Hits pro...
				int clicksperday = calcHitsPro(days, hits);
				int clicksperweek = calcHitsPro(week, hits);
				int clickspermonth = calcHitsPro(month, hits);
				int clicksperyear = calcHitsPro(year, hits);

				createOneHit(v.getId(), clicksperday,clicksperweek,clickspermonth,clicksperyear);
				}
					catch (ParseException e) {System.out.println("Simple Date Parsen Error!!");}
		}
		
	}
	
	//Durchschnittswerte Berechnung. 
	/**
	 * Calc hits pro.
	 *
	 * @param einheit the einheit
	 * @param hits the hits
	 * @return the int
	 */
	private int calcHitsPro(long einheit, int hits)
	{
		if(einheit>=1)return (int) (hits/einheit); //Hits pro Einheit (tag, woche, monat, jahr...)
		else return hits; //else: Das Video ist noch kein volles Jahr vollen Monat etc alt.
	}
	
	/**
	 * Delete video from video hit list by id.
	 *
	 * @param id the id
	 */
	public void deleteVideoFromVideoHitListById(int id) {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE FROM videohitlist WHERE id="+id);
	}
	
	/**
	 * Creates the one hit.
	 *
	 * @param id the id
	 * @param hitsperday the hitsperday
	 * @param hitsperweek the hitsperweek
	 * @param hitspermonth the hitspermonth
	 * @param hitsperyear the hitsperyear
	 */
	public void createOneHit(int id, int hitsperday, int hitsperweek, int hitspermonth, int hitsperyear) {
		JdbcTemplate insert = new JdbcTemplate(this.getDataSource());
		insert.update("INSERT INTO videohitlist (id, hitsperday, hitsperweek, hitspermonth, hitsperyear ) VALUES(?,?,?,?,?)", new Object[] { id, hitsperday, hitsperweek, hitspermonth, hitsperyear });
	}

	/**
	 * Update facility id in table by id.
	 *
	 * @param id the id
	 * @param facilityId the facility id
	 * @return the int
	 */
	public int updateFacilityIdInTableById(int id, int facilityId){
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		int ret = update.update("UPDATE video SET facilityId=? WHERE id=?", new Object[] { facilityId, id });
		System.out.println("update einrichutngId = "+facilityId+" for video id = "+id+"");
		return ret;
	}

}
