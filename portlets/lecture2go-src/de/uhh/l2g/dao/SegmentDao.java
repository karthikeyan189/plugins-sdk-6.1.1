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
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.uhh.l2g.beans.Chapter;
import de.uhh.l2g.beans.Mark;
import de.uhh.l2g.beans.Segment;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.util.FFmpegManager;
import de.uhh.l2g.util.L2goPropsUtil;

/**
 * The Class SegmentDao.
 */

public class SegmentDao extends JdbcDaoSupport implements ISegmentDao {

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

	/** The video dao. */
	private VideoDao videoDao;
	
	/**
	 * Gets the video dao.
	 *
	 * @return the video dao
	 */
	public VideoDao getVideoDao() {
		return videoDao;
	}

	/**
	 * Sets the video dao.
	 *
	 * @param videoDao the new video dao
	 */
	public void setVideoDao(VideoDao videoDao) {
		this.videoDao = videoDao;
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.ISegmentDao#deleteAll()
	 */
	/**
	 * Delete all.
	 */
	public void deleteAll() {
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.ISegmentDao#deleteById(int)
	 */
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(int id) {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from segment where id=" + id);
		delete.update("DELETE from segment_user_video where segmentId=" + id);
	}

	/**
	 * Delete by video id.
	 *
	 * @param videoId the video id
	 */
	public void deleteByVideoId(int videoId) {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from segment where videoId=" + videoId);
		delete.update("DELETE from segment_user_video where videoId=" + videoId);
	}


	/**
	 * Creates the.
	 *
	 * @param videoId the video id
	 * @param start the start
	 * @param title the title
	 * @param description the description
	 * @param end the end
	 * @param chapter the chapter
	 * @param userId the user id
	 */
	public void create(int videoId, String start, String title, String description, String end, int chapter, int userId) {
		JdbcTemplate insert = new JdbcTemplate(this.getDataSource());
		insert.update("INSERT INTO segment (videoId, start, title, description, end, chapter, userId) VALUES(?,?,?,?,?,?,?)", new Object[] { videoId, start, title, description, end, chapter, userId });
	}

	/**
	 * Creates the and return generated id.
	 *
	 * @param videoId the video id
	 * @param start the start
	 * @param title the title
	 * @param description the description
	 * @param end the end
	 * @param chapter the chapter
	 * @param userId the user id
	 * @return the int
	 */
	public int createAndReturnGeneratedId(int videoId, String start, String title, String description, String end, int chapter, int userId){
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		PreparedStatementCreator pscInsert = null;
		//initialize parameters
		try {
			String[] parameter = {videoId+"", start, title, description, end, chapter+"", userId+""}; 
			pscInsert = new Segment_INSERT_PreparedStatement(this.getJdbcTemplate(), parameter);
		} catch (SQLException e) {}

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(pscInsert, keyHolder);
		
		Number n = keyHolder.getKey();
		int i = n.intValue();
		return i;
	}
	
	/**
	 * Adds the segment i user i video idd to table_segment_user_video.
	 *
	 * @param segmentId the segment id
	 * @param userId the user id
	 * @param videoId the video id
	 */
	public void addSegmentIUserIVideoIddToTable_segment_user_video(int segmentId, int userId, int videoId){
		JdbcTemplate insert = new JdbcTemplate(this.getDataSource());
		insert.update("INSERT INTO segment_user_video (segmentId, userId, videoId) VALUES(?,?,?)", new Object[] {segmentId, userId, videoId});
	}

	/**
	 * Delete by segment id and user id from table_segment_user_video.
	 *
	 * @param segmentId the segment id
	 * @param userId the user id
	 */
	public void deleteBySegmentIdAndUserIdFromTable_segment_user_video(int segmentId, int userId){
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE FROM segment_user_video WHERE (segmentId = ? AND userId = ?)", new Object[] {segmentId, userId});
	}
	
	/**
	 * Gets the mark by video id.
	 *
	 * @param videoId the video id
	 * @return the mark by video id
	 */
	@SuppressWarnings("unchecked")
	public List<Mark> getMarkByVideoId(int videoId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT id, videoId, start, title, description, end, chapter, userId FROM segment WHERE videoId=" + videoId + " ORDER BY start ASC", new MarkRowMapper());
	}

	/**
	 * Gets the segments by video id.
	 *
	 * @param videoId the video id
	 * @return the segments by video id
	 */
	@SuppressWarnings("unchecked")
	public List<Mark> getSegmentsByVideoId(int videoId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Mark> returnList = select.query("SELECT id, videoId, start, title, description, end, chapter, userId FROM segment WHERE videoId=" + videoId + " ORDER BY start ASC", new MarkRowMapper());
		fillSegmentListWithProperties(returnList);
		return returnList;
	}

	/**
	 * Delete thumbhails from segments.
	 *
	 * @param segmentList the segment list
	 */
	public void deleteThumbhailsFromSegments(List<Mark> segmentList) {
		ListIterator<Mark> it = segmentList.listIterator();

		while (it.hasNext()) {
			Mark objectSegment = it.next();
			List<Video> videoList = getVideoDao().getById(objectSegment.getVideoId());

			Video objectVideo = videoList.iterator().next();
			try{
				int sec = new Integer(objectSegment.getStart().split(":")[0]) * 60 * 60 + new Integer(objectSegment.getStart().split(":")[1]) * 60 + new Integer(objectSegment.getStart().split(":")[2]);
				File thumbNail = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + objectVideo.getId() + "_" + sec + ".jpg");
				if (thumbNail.isFile()) thumbNail.delete();
			}catch (ArrayIndexOutOfBoundsException e) {}
		}
	}
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.ISegmentDao#getById(int)
	 */
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public List<Mark> getById(int id) {
		return this.getMarkById(id);
	}

	/**
	 * Gets the mark by id.
	 *
	 * @param id the id
	 * @return the mark by id
	 */
	@SuppressWarnings("unchecked")
	public List<Mark> getMarkById(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT id, videoId, start, title, description, end, chapter, userId FROM segment WHERE id=" + id, new MarkRowMapper());
	}

	/**
	 * Gets the chapter by video id.
	 *
	 * @param videoId the video id
	 * @return the chapter by video id
	 */
	@SuppressWarnings("unchecked")
	public List<Chapter> getChapterByVideoId(int videoId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT id, videoId, start, title, description, end, chapter, userId FROM segment WHERE ( videoId=" + videoId + " AND chapter=1) ORDER BY start ASC", new ChapterRowMapper());
	}

	/**
	 * Gets the chapter by id.
	 *
	 * @param id the id
	 * @return the chapter by id
	 */
	@SuppressWarnings("unchecked")
	public List<Chapter> getChapterById(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT id, videoId, start, title, description, end, chapter, userId FROM segment WHERE id=" + id, new ChapterRowMapper());
	}

	/**
	 * Update mark.
	 *
	 * @param mark the mark
	 */
	public void updateMark(Mark mark) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE segment SET videoId=?, start=?, title=?, description=?, end=?, chapter=?, userId=? WHERE id=?", new Object[] { mark.getVideoId(), mark.getStart(), mark.getTitle(), "", mark.getEnd(), 1, mark.getUserId(), mark.getId() });
	}

	/**
	 * Update chapter.
	 *
	 * @param chapter the chapter
	 */
	public void updateChapter(Chapter chapter) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE segment SET videoId=?, start=?, title=?, description=?, end=?, chapter=?, userId=? WHERE id=?", new Object[] { chapter.getVideoId(), chapter.getStart(), chapter.getTitle(), "", chapter.getEnd(), 1, chapter.getUserId(), chapter.getId() });
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.ISegmentDao#getAll()
	 */
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<Segment> getAll() {
		return null;
	}

	/**
	 * Fill segment list with properties.
	 *
	 * @param segmentList the segment list
	 */
	public void fillSegmentListWithProperties(List<Mark> segmentList) {
		ListIterator<Mark> it = segmentList.listIterator();
		Integer counter = 0;
		
		while (it.hasNext()) {
			Mark objectSegment = it.next();
			List<Video> videoList = videoDao.getById(objectSegment.getVideoId());

			Video objectVideo = videoList.iterator().next();
			int sec = new Integer(objectSegment.getStart().split(":")[0]) * 60 * 60 + new Integer(objectSegment.getStart().split(":")[1]) * 60 + new Integer(objectSegment.getStart().split(":")[2]);
			objectSegment.setSeconds(sec);

			File thumbNail = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + objectVideo.getId() + "_" + sec + ".jpg");
			String strt = objectSegment.getStart();

			// count chapters
			if (objectSegment.isChapter()) {
				counter++;
				objectSegment.setNumber(counter);
			}

			// generate thumbnail
			if (thumbNail.isFile()) objectSegment.setImage("/" + "images" + "/" + objectVideo.getId() + "_" + sec + ".jpg");
			else {
				((FFmpegManager)getUtilityBeanFactory().getBean("ffmgepManager")).createThumbnail(objectVideo, strt, L2goPropsUtil.get("lecture2go.images.system.path"));
				if (!thumbNail.isFile()) {
					objectSegment.setImage(L2goPropsUtil.get("lecture2go.theme.root.path") + "/" +"images" + "/" + "l2go" + "/" + "noimage.jpg");
				}
			}
		}
	}

	/**
	 * Change permission to segment.
	 *
	 * @param video the video
	 */
	public void changePermissionToSegment(Video video){
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		int set = 0;
		if(!video.isPermittedToSegment())set = 1;
		update.update("UPDATE video SET permittedToSegment=? WHERE id=?", new Object[] { set, video.getId() });
	}
}
