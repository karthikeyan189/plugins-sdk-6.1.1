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

import java.util.List;
import java.util.ListIterator;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.liferay.portal.service.UserLocalServiceUtil;

import de.uhh.l2g.beans.Coordinator;


/**
 * The Class CoordinatorDao.
 */
public class CoordinatorDao extends JdbcDaoSupport implements ICoordinatorDao {
	
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
	 * Sets the approved.
	 *
	 * @param userId the user id
	 * @param roleId the role id
	 * @param approved the approved
	 */
	public void setApproved(int userId, int roleId, boolean approved) {
		@SuppressWarnings("unused")
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		try {
			if (approved) {
				if (!UserLocalServiceUtil.hasRoleUser(roleId, userId)) {
					UserLocalServiceUtil.addRoleUsers(roleId, new long[] { userId });
				}
			} else {
				UserLocalServiceUtil.deleteRoleUser(roleId, userId);
			}
		} catch (Exception e) {
			//
		}
	}
	
	/**
	 * Update by id.
	 *
	 * @param userId the user id
	 * @param facilityId the facility id
	 * @return the int
	 */
	public int updateById(int userId, int facilityId) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		return update.update("UPDATE coordinator SET facilityId=? WHERE userId=?", new Object[] { facilityId, userId });
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.ICoordinatorDao#create(int, int, int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public void create(int userId, int facilityId) {
		JdbcTemplate insert = new JdbcTemplate(this.getDataSource());
		insert.update("INSERT INTO coordinator (userId, facilityId) VALUES(?,?)", new Object[] { userId, facilityId });
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.ICoordinatorDao#deleteAll()
	 */
	public void deleteAll() {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.ICoordinatorDao#deleteById(int)
	 */
	public void deleteById(int userId) {
		JdbcTemplate delete = new JdbcTemplate(this.getDataSource());
		delete.update("DELETE FROM coordinator WHERE userId=?", new Object[] { userId });
	}

	/**
	 * Gets the by facility id.
	 *
	 * @param facilityId the facility id
	 * @return the by facility id
	 */
	@SuppressWarnings("unchecked")
	public List<Coordinator> getByFacilityId(int facilityId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Coordinator> kL = select.query("SELECT k.userId, k.facilityId, u.emailAddress, u.firstName, u.lastName FROM coordinator k, User_ u WHERE (k.facilityId="+facilityId+" AND u.userId=k.userId)", new CoordinatorRowMapper());
		return fillCoordinatorListWithProperties(kL);
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.ICoordinatorDao#getById(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Coordinator> getById(long l) {
		long cId = l+1;
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<Coordinator> kL = select.query("SELECT k.userId, k.facilityId, u.emailAddress, u.firstName, u.lastName FROM coordinator k, User_ u WHERE (k.userId='" + l + "' AND u.userId=k.userId AND u.contactId='"+cId+"')", new CoordinatorRowMapper());
		return fillCoordinatorListWithProperties(kL);
	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.ICoordinatorDao#getAll()
	 */
	public List<Coordinator> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Fill coordinator list with properties.
	 *
	 * @param coordinatorList the coordinator list
	 * @return the list< coordinator>
	 */
	public List<Coordinator> fillCoordinatorListWithProperties(List<Coordinator> coordinatorList) {
		ListIterator<Coordinator> it = coordinatorList.listIterator();
		while (it.hasNext()) {
			Coordinator k = it.next();
			k.setFacilityList(getFacilityDao().getById(k.getFacilityId()));
		}
		return coordinatorList;
	}
}
