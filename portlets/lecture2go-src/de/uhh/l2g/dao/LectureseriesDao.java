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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.util.PaginationResultLectureseries;


/**
 * The Class LectureseriesDao.
 */
public class LectureseriesDao extends JdbcDaoSupport implements ILectureseriesDao {

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.ILectureseriesDao#deleteAll()
	 */
	/**
	 * Delete all.
	 */
	public void deleteAll() {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.ILectureseriesDao#deleteById(int)
	 */
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(int id) {
		_deleteById(id);
	}

	/**
	 * Clean.
	 */
	@SuppressWarnings("unchecked")
	public void clean() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Lectureseries> cd;
		List<Lectureseries> cdInUse;
		List<Lectureseries> cdInUse2;

		cd = select.query("SELECT lectureseries.number, lectureseries.eventType, lectureseries.eventCategory, lectureseries.name, lectureseries.shortDesc, lectureseries.longDesc, lectureseries.semesterName, lectureseries.language, lectureseries.facultyName, lectureseries.instructorsString, lectureseries.id, lectureseries.password, lectureseries.approved FROM lectureseries ", new LectureseriesRowMapper());

		ListIterator<Lectureseries> it = cd.listIterator();

		while (it.hasNext()) {
			int id = it.next().getId();
			cdInUse = select.query("SELECT lectureseries.number, lectureseries.eventType, lectureseries.eventCategory, lectureseries.name, lectureseries.shortDesc, lectureseries.longDesc, lectureseries.semesterName, lectureseries.language, lectureseries.facultyName, lectureseries.instructorsString, lectureseries.id, lectureseries.password, lectureseries.approved FROM lectureseries, producer_lectureseries WHERE producer_lectureseries.lectureseriesId='" + id + "'", new LectureseriesRowMapper());
			cdInUse2 = select.query("SELECT lectureseries.number, lectureseries.eventType, lectureseries.eventCategory, lectureseries.name, lectureseries.shortDesc, lectureseries.longDesc, lectureseries.semesterName, lectureseries.language, lectureseries.facultyName, lectureseries.instructorsString, lectureseries.id, lectureseries.password, lectureseries.approved FROM lectureseries, lectureseries_facility WHERE lectureseries_facility.lectureseriesId='" + id + "'", new LectureseriesRowMapper());

			if (cdInUse.size() == 0 && cdInUse2.size() == 0) {
//				System.out.println("ID -> " + id + " deleting!");
				deleteById(id);
			} else {
//				System.out.println("ID -> " + id + " in use!");
			}
		}
	}

	/**
	 * _delete by id.
	 *
	 * @param l the l
	 * @return the int
	 */
	private int _deleteById(long l) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		// transaction management needed
		select.update("DELETE FROM lectureseries_facility WHERE lectureseriesId = ?;", new Object[] { new Long(l) });
		select.update("DELETE FROM producer_lectureseries WHERE lectureseriesId = ?;", new Object[] { new Long(l) });
		return select.update("DELETE FROM lectureseries WHERE id = ?;", new Object[] { new Long(l) });
	}

	/**
	 * Contains videos.
	 *
	 * @param lectureseriesId the lectureseries id
	 * @return true, if successful
	 */
	public boolean containsVideos(int lectureseriesId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		int numberVideos = select.queryForInt("SELECT COUNT(v.id) FROM video AS v WHERE v.lectureseriesId = ?", new Object[] { lectureseriesId });
		return numberVideos > 0;
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.ILectureseriesDao#getAll()
	 */
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@SuppressWarnings("unchecked")
	public List<Lectureseries> getAll() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT * FROM lectureseries ORDER BY ID ASC", new LectureseriesRowMapper());
	}

	/**
	 * Gets the allwith pw.
	 *
	 * @return the allwith pw
	 */
	@SuppressWarnings("unchecked")
	public List<Lectureseries> getAllwithPw() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT number, eventType, eventCategory, name, shortDesc, longDesc, semesterName, language, facultyName, instructorsString, id, password, approved FROM lectureseries  WHERE NOT password = ''", new LectureseriesRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * de.uhh.l2g.dao.ILectureseriesDao#getFilteredBySemesterFacultyProducer(java
	 * .lang.String, java.lang.String, int, int, int, int)
	 */
	/**
	 * Gets the filtered by semester faculty producer.
	 *
	 * @param approved the approved
	 * @param semester the semester
	 * @param facultyId the faculty id
	 * @param producerId the producer id
	 * @param pageNumber the page number
	 * @param pageSize the page size
	 * @return the filtered by semester faculty producer
	 */
	public PaginationResultLectureseries getFilteredBySemesterFacultyProducer(String approved, String semester, int facultyId, int producerId, int pageNumber, int pageSize) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());

		List<Lectureseries> r = filterResult(select, approved, semester, facultyId, producerId, pageNumber, pageSize);
		int numResults = filterResultNumbers(select, approved, semester, facultyId, producerId).intValue();
		PaginationResultLectureseries result = new PaginationResultLectureseries();
		result.setPaginationResult(r);

		int myQuotient = (numResults / pageSize);
		int myRest = numResults % pageSize;

		result.setNumberPages(myRest > 0 ? (myQuotient + 1) : myQuotient);
		return result;
	}

	/**
	 * Filter result.
	 *
	 * @param select the select
	 * @param approved the approved
	 * @param semester the semester
	 * @param facultyId the faculty id
	 * @param producerId the producer id
	 * @param pageNumber the page number
	 * @param pageSize the page size
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	private List<Lectureseries> filterResult(JdbcTemplate select, String approved, String semester, int facultyId, int producerId, int pageNumber, int pageSize) {
		List<Object> params = new ArrayList<Object>();

		// build query
		String query = "SELECT c.number, c.eventType, c.eventCategory, c.name, c.shortDesc, c.longDesc, c.semesterName, c.language, c.facultyName, c.instructorsString, c.id, c.password, c.approved FROM lectureseries AS c ";

		if (facultyId > 0) {
			query += "INNER JOIN lectureseries_facility AS ce ON ( c.id = ce.lectureseriesId ) ";
			query += "INNER JOIN facility AS e ON ( ce.facilityId = e.id ) ";
		}

		if (producerId > 0) {
			query += "INNER JOIN producer_lectureseries AS pc ON ( c.id = pc.lectureseriesId ) ";
			query += "INNER JOIN producer AS p ON ( pc.producerId = p.id ) ";
		}

		if ((!"".equals(semester) && semester != null) || ("true".equals(approved) || "false".equals(approved)) || facultyId > 0 || producerId > 0) {
			query += "WHERE ";
			int i = 0;
			if (!"".equals(semester) && semester != null) {
				query += "c.semesterName = ? ";
				params.add(semester);
				i++;
			}

			if ("true".equals(approved) || "false".equals(approved)) {
				query += i > 0 ? "AND " : "";
				query += "c.approved = ? ";
				params.add(new Boolean("true".equals(approved)));
				i++;
			}

			if (facultyId > 0) {
				query += i > 0 ? "AND " : "";
				query += "ce.facilityId IN ";
				query += "(select id from facility AS ein WHERE ein.parentId = ? OR ein.id = ?) ";
				params.add(new Integer(facultyId));
				params.add(new Integer(facultyId));
				i++;
			}

			if (producerId > 0) {
				query += i > 0 ? "AND " : "";
				query += "pc.producerId = ? ";
				params.add(new Integer(producerId));
				i++;
			}
		}
		query += "GROUP BY c.ID ";
		query += "ORDER BY c.name ASC ";
		query += "LIMIT ?, ?";

		params.add(new Integer((pageNumber - 1) * pageSize));
		params.add(new Integer(pageSize));

		return fillLectureseriesListWithProperties(select.query(query, params.toArray(), new LectureseriesRowMapper()));

	}

	/**
	 * Filter result numbers.
	 *
	 * @param select the select
	 * @param approved the approved
	 * @param semester the semester
	 * @param facultyId the faculty id
	 * @param producerId the producer id
	 * @return the integer
	 */
	private Integer filterResultNumbers(JdbcTemplate select, String approved, String semester, int facultyId, int producerId) {
		List<Object> params = new ArrayList<Object>();

		// build query
		String query = "SELECT COUNT(0) FROM lectureseries AS c ";

		if (facultyId > 0) {
			query += "INNER JOIN lectureseries_facility AS ce ON ( c.id = ce.lectureseriesId ) ";
			query += "INNER JOIN facility AS e ON ( ce.facilityId = e.id ) ";
		}

		if (producerId > 0) {
			query += "INNER JOIN producer_lectureseries AS pc ON ( c.id = pc.lectureseriesId ) ";
			query += "INNER JOIN producer AS p ON ( pc.producerId = p.id ) ";
		}

		if ((!"".equals(semester) && semester != null) || ("true".equals(approved) || "false".equals(approved)) || facultyId > 0 || producerId > 0) {
			query += "WHERE ";
			int i = 0;
			if (!"".equals(semester) && semester != null) {
				query += "c.semesterName = ? ";
				params.add(semester);
				i++;
			}

			if ("true".equals(approved) || "false".equals(approved)) {
				query += i > 0 ? "AND " : "";
				query += "c.approved = ? ";
				params.add(new Boolean("true".equals(approved)));
				i++;
			}

			if (facultyId > 0) {
				query += i > 0 ? "AND " : "";
				query += "ce.facilityId IN ";
				query += "(select id from facility AS ein WHERE ein.parentId = ? OR ein.id = ?) ";
				params.add(new Integer(facultyId));
				params.add(new Integer(facultyId));
				i++;
			}

			if (producerId > 0) {
				query += i > 0 ? "AND " : "";
				query += "pc.producerId = ? ";
				params.add(new Integer(producerId));
				i++;
			}
		}

		return select.queryForInt(query, params.toArray());
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.ILectureseriesDao#getEventCategories()
	 */
	/**
	 * Gets the event categories.
	 *
	 * @return the event categories
	 */
	@SuppressWarnings("unchecked")
	public List<String> getEventCategories() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.queryForList("SELECT eventCategory FROM lectureseries WHERE eventCategory!='' GROUP BY eventCategory;", String.class);
	}

	/**
	 * Gets the all avaliable semesters.
	 *
	 * @return the all avaliable semesters
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllAvaliableSemesters() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.queryForList("SELECT semesterName FROM lectureseries WHERE semesterName!='' GROUP BY semesterName;", String.class);
	}

	/**
	 * Gets the by facility id.
	 *
	 * @param eirichtungId the eirichtung id
	 * @return the by facility id
	 */
	@SuppressWarnings("unchecked")
	public List<Lectureseries> getByFacilityId(int eirichtungId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT number, eventType, eventCategory, name, shortDesc, longDesc, semesterName, language, facultyName, instructorsString, id, password, approved FROM lectureseries, lectureseries_facility WHERE ( lectureseries_facility.facilityId=" + eirichtungId + " AND lectureseries.id=lectureseries_facility.lectureseriesId )", new LectureseriesRowMapper());
	}

	/**
	 * Gets the by facility id and openacces videos.
	 *
	 * @param eirichtungId the eirichtung id
	 * @return the by facility id and openacces videos
	 */
	@SuppressWarnings("unchecked")
	public List<Lectureseries> getByFacilityIdAndOpenaccesVideos(int eirichtungId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT lectureseries.number, lectureseries.eventType, lectureseries.eventCategory, lectureseries.name, lectureseries.shortDesc, lectureseries.longDesc, lectureseries.semesterName, lectureseries.language, lectureseries.facultyName, lectureseries.instructorsString, lectureseries.id, lectureseries.password, lectureseries.approved FROM lectureseries RIGHT JOIN video ON video.lectureseriesId=lectureseries.id AND video.openAccess='1' LEFT JOIN lectureseries_facility ON lectureseries.id = lectureseries_facility.lectureseriesId WHERE lectureseries_facility.facilityId='" + eirichtungId + "' GROUP BY video.lectureseriesId", new LectureseriesRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.ILectureseriesDao#getById(int)
	 */
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	@SuppressWarnings("unchecked")
	public List<Lectureseries> getById(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT number, eventType, eventCategory, name, shortDesc, longDesc, semesterName, language, facultyName, instructorsString, id, password, approved FROM lectureseries WHERE id=" + id, new LectureseriesRowMapper());
	}

	/**
	 * Gets the by lectureseries number.
	 *
	 * @param init the init
	 * @return the by lectureseries number
	 */
	@SuppressWarnings("unchecked")
	public List<Lectureseries> getByLectureseriesNumber(int init) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT number, eventType, eventCategory, name, shortDesc, longDesc, semesterName, language, facultyName, instructorsString, id, password, approved FROM lectureseries WHERE number=" + init, new LectureseriesRowMapper());
	}

	/**
	 * Gets the lectureseries for producer.
	 *
	 * @param producerId the producer id
	 * @return the lectureseries for producer
	 */
	@SuppressWarnings("unchecked")
	public List<Lectureseries> getLectureseriesForProducer(Integer producerId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Lectureseries> lectureseries = select.query("SELECT lectureseries.number, lectureseries.eventType, lectureseries.eventCategory, lectureseries.name, lectureseries.shortDesc, lectureseries.longDesc, lectureseries.semesterName, lectureseries.language, lectureseries.facultyName, lectureseries.instructorsString, lectureseries.id, lectureseries.password, lectureseries.approved FROM lectureseries, producer_lectureseries WHERE (producer_lectureseries.producerId=" + producerId + " AND lectureseries.id=producer_lectureseries.lectureseriesId AND approved='1')", new LectureseriesRowMapper());
		return lectureseries;
	}

	/**
	 * Checks for lectureseries producer.
	 *
	 * @param l the l
	 * @param producerId the producer id
	 * @return true, if successful
	 */
	public boolean hasLectureseriesProducer(long l, int producerId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		String query = "SELECT COUNT(pc.id) FROM producer_lectureseries AS pc ";
		query += "WHERE pc.lectureseriesId = ? AND pc.producerId = ?;";

		int result = select.queryForInt(query, new Object[] { l, producerId });
		return result > 0;
	}

	/**
	 * Update by id.
	 *
	 * @param lectureseries the lectureseries
	 */
	public void updateById(Lectureseries lectureseries) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE lectureseries SET number=?, eventType=?, eventCategory=?, name=?, shortDesc=?, longDesc=?, semesterName=?, language=?, facultyName=?, instructorsString=?, password=? WHERE id=?", new Object[] { lectureseries.getNumber(), lectureseries.getEventType(), lectureseries.getEventCategory(), lectureseries.getName(), lectureseries.getShortDesc(), lectureseries.getLongDesc(), lectureseries.getSemesterName(), lectureseries.getLanguage(), lectureseries.getFaculty_name(), lectureseries.getInstructorsString(), lectureseries.getPassword(), lectureseries.getId() });
	}

	/**
	 * Creates the.
	 *
	 * @param number the number
	 * @param eventType the event type
	 * @param eventCategory the event category
	 * @param name the name
	 * @param shortDesc the short desc
	 * @param longDesc the long desc
	 * @param semesterName the semester name
	 * @param language the language
	 * @param facultyName the faculty name
	 * @param instructorsString the instructors string
	 * @param password the password
	 * @param approved the approved
	 * @param facultyKeys the faculty keys
	 * @param producerKeys the producer keys
	 * @return the int
	 */
	public int create(final String number, final String eventType, final String eventCategory, final String name, final String shortDesc, final String longDesc, final String semesterName, final String language, final String facultyName, final String instructorsString, final String password, final boolean approved, final String[] facultyKeys, final String[] producerKeys) {
		JdbcTemplate insert = new JdbcTemplate(this.getDataSource());
		// transaction handling needed
		int maxId = insert.queryForInt("SELECT MAX(id) FROM lectureseries;");

		long count = _createWithId(maxId + 1, number, eventType, eventCategory, name, shortDesc, longDesc, semesterName, language, facultyName, instructorsString, password, approved, facultyKeys, producerKeys);

		logger.debug(count + " lectureseriesata items inserted");
		return count > 0 ? maxId + 1 : 0;
	}

	/**
	 * Creates the with id.
	 *
	 * @param id the id
	 * @param number the number
	 * @param eventType the event type
	 * @param eventCategory the event category
	 * @param name the name
	 * @param shortDesc the short desc
	 * @param longDesc the long desc
	 * @param semesterName the semester name
	 * @param language the language
	 * @param facultyName the faculty name
	 * @param instructorsString the instructors string
	 * @param password the password
	 * @param approved the approved
	 * @param facultyKeys the faculty keys
	 * @param producerKeys the producer keys
	 * @return the long
	 */
	public long createWithId(final int id, final String number, final String eventType, final String eventCategory, final String name, final String shortDesc, final String longDesc, final String semesterName, final String language, final String facultyName, final String instructorsString, final String password, final boolean approved, final String[] facultyKeys, final String[] producerKeys) {
		return _createWithId(id, number, eventType, eventCategory, name, shortDesc, longDesc, semesterName, language, facultyName, instructorsString, password, approved, facultyKeys, producerKeys);

	}

	/**
	 * Sets the approved.
	 *
	 * @param id the id
	 * @param approved the approved
	 * @return the int
	 */
	public int setApproved(int id, boolean approved) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		return update.update("UPDATE lectureseries SET approved = ? WHERE id = ?", new Object[] { approved, id });
	}

	/**
	 * Edits the.
	 *
	 * @param l the l
	 * @param number the number
	 * @param eventType the event type
	 * @param eventCategory the event category
	 * @param name the name
	 * @param shortDesc the short desc
	 * @param longDesc the long desc
	 * @param semesterName the semester name
	 * @param language the language
	 * @param facultyName the faculty name
	 * @param instructorsString the instructors string
	 * @param password the password
	 * @param approved the approved
	 * @param facultyKeys the faculty keys
	 * @param producerKeys the producer keys
	 * @return the long
	 */
	public long edit(final long l, final String number, final String eventType, final String eventCategory, final String name, final String shortDesc, final String longDesc, final String semesterName, final String language, final String facultyName, final String instructorsString, final String password, final boolean approved, final String[] facultyKeys, final String[] producerKeys) {
		if (_deleteById(l) > 0) {
			return _createWithId(l, number, eventType, eventCategory, name, shortDesc, longDesc, semesterName, language, facultyName, instructorsString, password, approved, facultyKeys, producerKeys);
		}

		return 0;
	}

	/**
	 * _create with id.
	 *
	 * @param l the l
	 * @param number the number
	 * @param eventType the event type
	 * @param eventCategory the event category
	 * @param name the name
	 * @param shortDesc the short desc
	 * @param longDesc the long desc
	 * @param semesterName the semester name
	 * @param language the language
	 * @param facultyName the faculty name
	 * @param instructorsString the instructors string
	 * @param password the password
	 * @param approved the approved
	 * @param facultyKeys the faculty keys
	 * @param producerKeys the producer keys
	 * @return the long
	 */
	private long _createWithId(final long l, final String number, final String eventType, final String eventCategory, final String name, final String shortDesc, final String longDesc, final String semesterName, final String language, final String facultyName, final String instructorsString, final String password, final boolean approved, final String[] facultyKeys, final String[] producerKeys) {

		// transaction needed
		JdbcTemplate insert = new JdbcTemplate(this.getDataSource());

		int count = insert.update("INSERT INTO lectureseries (id, number, eventType, eventCategory, name, shortDesc, longDesc, semesterName, language, facultyName, instructorsString, password, approved) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[] { l, number, eventType, eventCategory, name, shortDesc, longDesc, semesterName, language, facultyName, instructorsString, password, approved });

		logger.debug(count + " lectureseriesata items inserted");

		if (facultyKeys != null && facultyKeys.length > 0) {
			final int batchCount = facultyKeys.length;
			int[] a = insert.batchUpdate("INSERT INTO lectureseries_facility (facilityId, lectureseriesId) VALUES(?,?)", new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, Integer.parseInt(facultyKeys[i]));
					ps.setLong(2, l);
				}

				public int getBatchSize() {
					return batchCount;
				}
			});

			logger.debug("result of faculties affected by each query: " + a);

		}

		if (producerKeys != null && producerKeys.length > 0) {
			final int batchCount = producerKeys.length;
			int[] a = insert.batchUpdate("INSERT INTO producer_lectureseries (producerId, lectureseriesId) VALUES(?,?)", new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, Integer.parseInt(producerKeys[i]));
					ps.setLong(2, l);
				}

				public int getBatchSize() {
					return batchCount;
				}
			});

			logger.debug("result of producers affected by each query: " + a);

		}

		return count > 0 ? l : 0;
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	@SuppressWarnings("unchecked")
	public List<Lectureseries> getById(Integer id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Lectureseries> lectureseries = select.query("SELECT number, eventType, eventCategory, name, shortDesc, longDesc, semesterName, language, facultyName, instructorsString, id, password, approved FROM lectureseries WHERE id=" + id, new LectureseriesRowMapper());
		return lectureseries;
	}
	
	/**
	 * Gets the all open access.
	 *
	 * @return the all open access
	 */
	@SuppressWarnings("unchecked")
	public List<Lectureseries> getAllOpenAccess() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		
		return select.query("SELECT number, eventType, eventCategory, name, shortDesc, longDesc, semesterName, language, facultyName, instructorsString, lectureseries.id, password, approved FROM lectureseries, video WHERE (video.openAccess=1 AND video.lectureseriesId=lectureseries.id) GROUP BY lectureseries.id", new LectureseriesRowMapper());
	}	
	
	/**
	 * Fill lectureseries list with properties.
	 *
	 * @param lectureseriesList the lectureseries list
	 * @return the list
	 */
	public List<Lectureseries> fillLectureseriesListWithProperties(List<Lectureseries> lectureseriesList) {
		ListIterator<Lectureseries> it = lectureseriesList.listIterator();
		while (it.hasNext()) {
			Lectureseries object = it.next();
			if(!containsVideos(object.getId()))object.setDeleteable(true);
		}
		return lectureseriesList;
	}
}
