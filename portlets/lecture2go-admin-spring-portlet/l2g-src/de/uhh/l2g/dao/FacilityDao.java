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

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.Host;


/**
 * The Class FacilityDao.
 */
public class FacilityDao extends JdbcDaoSupport implements IFacilityDao {

	/** The host dao. */
	private HostDao hostDao;
	
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

	/** The lectureseries dao. */
	private LectureseriesDao lectureseriesDao;
	
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

	/** The return object. */
	private Facility returnObject = null;

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IFacilityDao#deleteAll()
	 */
	/**
	 * Delete all.
	 */
	public void deleteAll() {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from facility");
		delete.update("DELETE from video_facility");
		delete.update("DELETE from veranstaltung_facility");
		delete.update("DELETE from facility_host");
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IFacilityDao#deleteById(int)
	 */
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(int id) {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from facility where id=?", new Object[] { id });
		delete.update("DELETE from video_facility where facilityId=?", new Object[] { id });
		delete.update("DELETE from lectureseries_facility where facilityId=?", new Object[] { id });
		delete.update("DELETE from facility_host where facilityId=?", new Object[] { id });
	}

	/**
	 * Update by id.
	 *
	 * @param parentId the parent id
	 * @param name the name
	 * @param typ the typ
	 * @param www the www
	 * @param level the level
	 * @param sort the sort
	 * @param id the id
	 */
	public void updateById(int parentId, String name, String typ, String www, int level, int sort, int id) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE facility SET parentId=?, name=?, typ=?, www=?, level=?, sort=? WHERE id=?", new Object[] { parentId, name, typ, www, level, sort, id });
	}

	/**
	 * Gets the by parent id and sort.
	 *
	 * @param parentId the parent id
	 * @param sort the sort
	 * @return the by parent id and sort
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getByParentIdAndSort(int parentId, int sort) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el =  select.query("select id, parentId, name, typ, www, level, sort from facility WHERE ( parentId=" + parentId + " AND sort=" + sort + ") ORDER BY sort ASC", new FacilityRowMapper());

		fillFacilityListWithProperties(el);
		return el;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IFacilityDao#getAll()
	 */
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getAll() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el = select.query("select id, parentId, name, typ, www, level, sort from facility", new FacilityRowMapper());
		fillFacilityListWithProperties(el);
		return el;
	}

	/**
	 * Auto sort.
	 *
	 * @param facilityList the facility list
	 */
	public void autoSort(List<Facility> facilityList) {
		ListIterator<Facility> it = facilityList.listIterator();
		int count = 0;
		while (it.hasNext()) {
			count++;
			Facility ei = it.next();
			this.updateById(ei.getParentId(), ei.getName(), ei.getTyp(), ei.getWww(), ei.getLevel(), count, ei.getId());
		}
	}

	/**
	 * Gets the all sorted as tree.
	 *
	 * @return the all sorted as tree
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getAllSortedAsTree() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> returnList;

		String query = "SELECT myNode.id, myNode.parentId, myNode.name, myNode.typ, myNode.www, myNode.level, myNode.sort, ";
		query += "IF(myParent.id>=0, IF(myGrandparent.id>=0, CONCAT(myGrandparent.id, '/', myParent.id, '/', myNode.id),CONCAT('', myParent.id, '/', myNode.id)),CONCAT( myNode.id, '')) AS path ";
		query += "FROM facility AS myNode ";
		query += "LEFT JOIN facility AS myParent ON myNode.parentId = myParent.id ";
		query += "LEFT JOIN facility AS myGrandparent ON myParent.parentId = myGrandparent.id ";
		query += "ORDER BY path ASC";

		returnList = select.query(query, new FacilityRowMapper(true));
		fillFacilityListWithProperties(returnList);
		
		return returnList;
	}

	/**
	 * Gets the by parent id sorted as tree.
	 *
	 * @param parentId the parent id
	 * @return the by parent id sorted as tree
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getByParentIdSortedAsTree(int parentId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> returnList;

		String query = "SELECT myNode.id, myNode.parentId, myNode.name, myNode.typ, myNode.www, myNode.level, myNode.sort, ";
		query += "IF(myParent.id>=0, IF(myGrandparent.id>=0, CONCAT(myGrandparent.id, '/', myParent.id, '/', myNode.id),CONCAT('', myParent.id, '/', myNode.id)),CONCAT( myNode.id, '')) AS path ";
		query += "FROM facility AS myNode ";
		query += "LEFT JOIN facility AS myParent ON myNode.parentId = myParent.id ";
		query += "LEFT JOIN facility AS myGrandparent ON myParent.parentId = myGrandparent.id ";
		query += "WHERE myParent.id='" + parentId + "' ";
		query += "ORDER BY myNode.sort ASC";

		returnList = select.query(query, new FacilityRowMapper(true));
		fillFacilityListWithProperties(returnList);
		
		return returnList;
	}

	/**
	 * Gets the top facilities.
	 *
	 * @return the top facilities
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getTopFacilities() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		String query = "SELECT myNode.id, myNode.parentId, myNode.name, myNode.typ, myNode.www, myNode.level, myNode.sort ";
		query += "FROM facility AS myNode ";
		query += "WHERE myNode.parentId = ? ";
		query += "ORDER BY myNode.id ASC";
		return select.query(query, new Object[] { 1 }, new FacilityRowMapper());
	}

	/**
	 * Gets the by parent id.
	 *
	 * @param parentId the parent id
	 * @return the by parent id
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getByParentId(int parentId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el =  select.query("select id, parentId, name, typ, www, level, sort from facility WHERE parentId=" + parentId + " ORDER BY sort ASC", new FacilityRowMapper());

		fillFacilityListWithProperties(el);
		return el;
	}

	/**
	 * Gets the by parent id and typ.
	 *
	 * @param parentId the parent id
	 * @param tree the tree
	 * @return the by parent id and typ
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getByParentIdAndTyp(int parentId, String tree) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el =  select.query("select id, parentId, name, typ, www, level, sort from facility WHERE parentId="+parentId+" AND typ='"+tree+"' ORDER BY sort ASC", new FacilityRowMapper());

		fillFacilityListWithProperties(el);
		return el;
	}
	
	/**
	 * Gets the by parent id and open access video.
	 *
	 * @param parentId the parent id
	 * @return the by parent id and open access video
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getByParentIdAndOpenAccessVideo(int parentId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el =  select.query("SELECT video_facility.facilityId, parentId, name, typ, www, facility.level, facility.sort FROM video_facility INNER JOIN facility ON video_facility.facilityId = facility.id AND facility.parentId='" + parentId + "' INNER JOIN video ON video_facility.videoId = video.id AND video.openAccess='1' GROUP BY facilityId ORDER BY sort ASC", new FacilityRowMapper());

		fillFacilityListWithProperties(el);
		return el;
	}

	/**
	 * Gets the by video id.
	 *
	 * @param videoId the video id
	 * @return the by video id
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getByVideoId(int videoId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el = select.query("SELECT id, parentId, name, typ, www, level, sort from facility, video_facility WHERE ( video_facility.videoId="+videoId+" AND facility.id=video_facility.facilityId )", new FacilityRowMapper());

		fillFacilityListWithProperties(el);
		return el;
	}
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IFacilityDao#getById(int)
	 */
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getById(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el = select.query("select id, parentId, name, typ, www, level, sort from facility where id=" + id, new FacilityRowMapper());
		
		fillFacilityListWithProperties(el);
		return el;
	}

	/**
	 * Gets the by type.
	 *
	 * @param type the type
	 * @return the by type
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getByType(String type) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el =  select.query("select id, parentId, name, typ, www, level, sort from facility where typ='" + type + "' ORDER BY sort ASC", new FacilityRowMapper());

		fillFacilityListWithProperties(el);
		return el;
	}

	/**
	 * Gets the by type and level.
	 *
	 * @param type the type
	 * @param level the level
	 * @return the by type and level
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getByTypeAndLevel(String type, String level) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el =  select.query("select id, parentId, name, typ, www, level, sort from facility WHERE ( typ='" + type + "' AND level=" + level + ") ORDER BY sort ASC", new FacilityRowMapper());

		fillFacilityListWithProperties(el);
		return el;
	}

	/**
	 * Gets the by level.
	 *
	 * @param level the level
	 * @return the by level
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getByLevel(int level) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el =  select.query("select id, parentId, name, typ, www, level, sort from facility where level='" + level + "' ORDER BY sort ASC", new FacilityRowMapper());

		fillFacilityListWithProperties(el);
		return el;
	}

	/**
	 * Gets the from coordinator.
	 *
	 * @param level the level
	 * @return the from coordinator
	 */
	public List<Facility> getFromCoordinator(int level) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		@SuppressWarnings("unchecked")
		List<Facility> el =  select.query("select id, parentId, name, typ, www, level, sort from facility AS e, coordinator AS k where (level='" + level + "' AND e.id=k.facilityId) ORDER BY sort ASC", new FacilityRowMapper());

		fillFacilityListWithProperties(el);
		return el;
	}
	
	/**
	 * Gets the by level and unassigned.
	 *
	 * @param level the level
	 * @param assigned the assigned
	 * @param exceptId the except id
	 * @return the by level and unassigned
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getByLevelAndUnassigned(int level, List<Facility> assigned, Integer exceptId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		String q="";
		String teilQuery = "(";
		if(assigned.size()>0){
			Iterator<Facility> i = assigned.iterator();
			while(i.hasNext()){
				Facility e = i.next();
					if(i.hasNext()){
						if(e.getId()!=exceptId) teilQuery += "e.id !="+e.getId()+" AND ";
					}else{
						if(e.getId()!=exceptId) teilQuery += "e.id !="+e.getId()+")";
						else teilQuery += "e.id != 0)"; //close with 0, because there is no such id!
					}
			}			
			q = "SELECT id, parentId, name, typ, www, level, sort FROM facility AS e  WHERE (level='"+level+"' AND "+teilQuery+") GROUP BY e.id ORDER BY sort ASC";
		}else{
			q = "SELECT id, parentId, name, typ, www, level, sort FROM facility AS e  WHERE level='"+level+"' GROUP BY e.id ORDER BY sort ASC";
		}
		
		List<Facility> el =  select.query(q, new FacilityRowMapper());
		fillFacilityListWithProperties(el);
		return el;
	}
	
	/**
	 * Creates the.
	 *
	 * @param parentId the parent id
	 * @param name the name
	 * @param typ the typ
	 * @param www the www
	 * @param level the level
	 * @param sort the sort
	 * @return the int
	 */
	public int create(int parentId, String name, String typ, String www, Integer level, Integer sort) {
		JdbcTemplate template = new JdbcTemplate(this.getDataSource());
		template.update("INSERT INTO facility ( parentId, name, typ, www, level, sort ) VALUES(?,?,?,?,?,?)", new Object[] { parentId, name, typ, www, level, sort });
		return template.queryForInt("SELECT MAX(id) FROM facility;");
	}

	/**
	 * Creates the and return generated id.
	 *
	 * @param parentId the parent id
	 * @param name the name
	 * @param typ the typ
	 * @param www the www
	 * @param level the level
	 * @param sort the sort
	 * @return the int
	 */
	public int createAndReturnGeneratedId(int parentId, String name, String typ, String www, Integer level, Integer sort) {
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		PreparedStatementCreator pscInsert = null;
		
		//initialize parameters
		try {
			String[] parameter = {""+parentId, name, typ, www, ""+level, ""+sort}; 
			pscInsert = new Facility_INSERT_PreparedStatement(this.getJdbcTemplate(), parameter);
		} catch (SQLException e) {}
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(pscInsert, keyHolder);
		
		Number n = keyHolder.getKey();
		int i = n.intValue();
		return i;
	}
	
	/**
	 * Adds the facility id to host.
	 *
	 * @param eId the e id
	 * @param hId the h id
	 * @return the int
	 */
	public int addFacilityIdToHost(int eId, String hId) {
		JdbcTemplate template = new JdbcTemplate(this.getDataSource());
		return template.update("INSERT INTO facility_host ( facilityId, hostId ) VALUES( ?, ? )", new Object[] { eId, hId });
	}
	
	/**
	 * Gets the from video id.
	 *
	 * @param videoId the video id
	 * @return the from video id
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getFromVideoId(Integer videoId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el =  select.query("SELECT facility.id, facility.parentId, facility.name, facility.typ, facility.www, facility.level, facility.sort FROM facility, video_facility WHERE (video_facility.videoId=" + videoId + " AND facility.id=video_facility.facilityId);", new FacilityRowMapper());

		fillFacilityListWithProperties(el);
		return el;
	}

	/**
	 * Adds the facility id and video id to table video_facility.
	 *
	 * @param facilityId the facility id
	 * @param videoId the video id
	 */
	public void addFacilityIdAndVideoIdToTableVideo_facility(Integer facilityId, Integer videoId) {
		JdbcTemplate statemant = new JdbcTemplate(this.getDataSource());
		statemant.execute("INSERT INTO video_facility ( videoId, facilityId ) VALUES (" + videoId + "," + facilityId + ")");
	}

	/**
	 * Gets the from lectureseries id.
	 *
	 * @param lectureseriesId the lectureseries id
	 * @return the from lectureseries id
	 */
	@SuppressWarnings("unchecked")
	public List<Facility> getFromLectureseriesId(Integer lectureseriesId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el =  select.query("SELECT facility.id, facility.parentId, facility.name, facility.typ, facility.www, facility.level, facility.sort FROM facility, lectureseries_facility WHERE (lectureseries_facility.lectureseriesId=? AND facility.id=lectureseries_facility.facilityId);", new Object[] { lectureseriesId }, new FacilityRowMapper());

		fillFacilityListWithProperties(el);
		return el;
	}

	/**
	 * Gets the faculty from facility id.
	 *
	 * @param id the id
	 * @return the faculty from facility id
	 */
	@SuppressWarnings("unchecked")
	public Facility getFacultyFromFacilityId(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> result = null;

		result = select.query("select id, parentId, name, typ, www, level, sort FROM facility WHERE id=" + id, new FacilityRowMapper());
		Facility ei = result.iterator().next();
		if (ei.getLevel() == 1) {
			returnObject = result.iterator().next();
		} else {
			if (ei.getLevel() != 0) {
				getFacultyFromFacilityId(ei.getParentId());
			}
		}
		return returnObject;
	}

	/**
	 * Gets the subfacility from facility id.
	 *
	 * @param id the id
	 * @return the subfacility from facility id
	 */
	@SuppressWarnings("unchecked")
	public Facility getSubfacilityFromFacilityId(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> result = null;
		Facility parentEi = getFacultyFromFacilityId(id);

		result = select.query("select id, parentId, name, typ, www, level, sort from facility where id=" + id, new FacilityRowMapper());
		Facility ei = result.iterator().next();
		if (ei.getParentId() == parentEi.getId()) {
			returnObject = result.iterator().next();
		} else {
			if (ei.getParentId() != 0) {
				getFacultyFromFacilityId(ei.getParentId());
			}
		}
		return returnObject;
	}

	/**
	 * Gets the subfacilities from facility id.
	 *
	 * @param id the id
	 * @return the subfacilities from facility id
	 */
	@SuppressWarnings("unchecked")
	private List<Facility> getSubfacilitiesFromFacilityId(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el =  select.query("select id, parentId, name, typ, www, level, sort from facility where parentId=" + id, new FacilityRowMapper());

//		fillFacilityListWithProperties(el);
		return el;
	}

	/**
	 * Count videos from facility.
	 *
	 * @param facilityId the facility id
	 * @return the int
	 */
	public int countVideosFromFacility(Integer facilityId) {

		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		int i = select.queryForInt("SELECT COUNT(*) FROM video, video_facility WHERE ( video.id=video_facility.videoId AND video_facility.facilityId=? );", new Object[] { facilityId });
		
		return i;
	}

	
	/**
	 * Fill facility list with properties.
	 *
	 * @param ei the ei
	 * @return the list
	 */
	public List<Facility> fillFacilityListWithProperties(List<Facility> ei) {
		ListIterator<Facility> it = ei.listIterator();
		while (it.hasNext()) {
			Facility e = it.next();
			
			//set host for streaming
			Host h = null;
			try{ h = getHostDao().getByFacilityId(e.getId()).iterator().next();}catch (NoSuchElementException nsee){}
			
			e.setHost(h);
			
			//check for lectureseries in this "facility"
			if(lectureseriesDao.getByFacilityId(e.getId()).size()==0)e.setHasLectureseries(false);
			else e.setHasLectureseries(true);
			
			//for lectureseries
			if(e.getTyp().equals("tree1")){
				int i = getSubfacilitiesFromFacilityId(e.getId()).size();
				// check for subFacilities in this "facility"
				// update and return list
				if (i == 0) e.setDeletable(true);
				else e.setDeletable(false);				
			}
			
			//for konferenzen
			if(e.getTyp().equals("tree2")){
				int i = countVideosFromFacility(e.getId());
				// check for Videos in this "facility"
				if (i == 0 && !e.getHasLectureseries()) e.setDeletable(true);
				else e.setDeletable(false);				
			}
		}
		return ei;
	}
	
}
