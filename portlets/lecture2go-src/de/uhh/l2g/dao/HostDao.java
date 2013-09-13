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
import java.util.ListIterator;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.Host;


/**
 * The Class HostDao.
 */

public class HostDao extends JdbcDaoSupport implements IHostDao {

	/** The facility dao. */
	private FacilityDao facilityDao;
	
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
	 * @param facilityDao the facility dao
	 */
	public void setFacilityDao(FacilityDao facilityDao) {
		this.facilityDao = facilityDao;
	}

	/**
	 * Creates the and return generated id.
	 *
	 * @param protokoll the protokoll
	 * @param streamer the streamer
	 * @param port the port
	 * @param serverRoot the server root
	 * @param name the name
	 * @return the int
	 */
	public int createAndReturnGeneratedId(String protokoll, String streamer, int port, String serverRoot, String name){
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		PreparedStatementCreator pscInsert = null;
		//initialize parameters
		try {
			String[] parameter = { protokoll, streamer, port+"", serverRoot, name }; 
			pscInsert = new Host_INSERT_PreparedStatement(this.getJdbcTemplate(), parameter);
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
	 * @param protokoll the protokoll
	 * @param streamer the streamer
	 * @param port the port
	 * @param serverRoot the server root
	 * @param name the name
	 * @param id the id
	 */
	public void updateById(String protokoll, String streamer, int port, String serverRoot, String name, int id) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("UPDATE host SET protokoll=?, streamer=?, port=?, name=?, serverRoot=? WHERE id=?", new Object[] { protokoll, streamer, port, name, serverRoot, id });
	}
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IHostDao#create(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void create(String name, int port, String protokoll, String serverRoot, String streamer) {
		JdbcTemplate insert = new JdbcTemplate(this.getDataSource());
		insert.update("INSERT INTO host (protokoll, streamer, port, serverRoot, name) VALUES(?,?,?,?,?)", new Object[] { protokoll, streamer, port, serverRoot, name });
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IHostDao#deleteAll()
	 */
	public void deleteAll() {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from host");
		delete.update("DELETE from facility_host");
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IHostDao#deleteById(int)
	 */
	public void deleteById(int id) {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE from host where id=" + id);
		delete.update("DELETE from facility_host where hostId=" + id);
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IHostDao#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Host> getAll() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return fillVideoListWithProperties(select.query("select id, protokoll, streamer, port, serverRoot, name from host", new HostRowMapper()));
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IHostDao#getById(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Host> getById(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return fillVideoListWithProperties(select.query("select id, protokoll, streamer, port, serverRoot, name from host WHERE host.id="+id, new HostRowMapper()));
	}

	/**
	 * Gets the by facility id.
	 *
	 * @param facilityId the facility id
	 * @return the by facility id
	 */
	@SuppressWarnings("unchecked")
	public List<Host> getByFacilityId(int facilityId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return fillVideoListWithProperties(select.query("SELECT h.id, h.protokoll, h.streamer, h.port, h.serverRoot, h.name FROM facility_host AS eh INNER JOIN host AS h ON ( eh.hostId = h.id ) WHERE eh.facilityId=?", new Object[] { new Integer(facilityId) }, new HostRowMapper()));
	}
	
	/**
	 * Checks if is deletable.
	 *
	 * @param id the id
	 * @return true, if checks if is deletable
	 */
	public boolean isDeletable(int id){
		boolean ret = false;
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		
		int result = select.queryForInt("SELECT COUNT(*) FROM facility_host WHERE hostId="+id);
		if (result == 0 )ret = true;
		
		return ret;
	}

	/**
	 * Gets the facilities by host id.
	 *
	 * @param hostId the host id
	 * @return the facilities by host id
	 */
	public List<Facility> getFacilitiesByHostId(int hostId){
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Facility> el = select.query("SELECT id, parentId, name, typ, www, level, sort FROM facility e, facility_host eh  WHERE (eh.hostId=" + hostId+" AND eh.facilityId = e.id)", new FacilityRowMapper());
		return el;
	}
	
	/**
	 * Fill video list with properties.
	 *
	 * @param hostList the host list
	 * @return the list< host>
	 */
	public List<Host> fillVideoListWithProperties(List<Host> hostList){
		ListIterator<Host> it = hostList.listIterator();

		while (it.hasNext()) {
			Host h = it.next();
			if(isDeletable(h.getId()))h.setDeletable(true);
			//set Facilities
			List<Facility> facilitiesList = getFacilitiesByHostId(h.getId());
			h.setFacilitiesList(facilitiesList);			
		}
		
		return hostList;
	}
}
