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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.liferay.portal.service.UserLocalServiceUtil;

import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.ProducerLRInfo;
import de.uhh.l2g.util.PaginationResultProducer;


/**
 * The Class ProducerDao.
 */
public class ProducerDao extends JdbcDaoSupport implements IProducerDao {
	
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
	 * @param facilityDao the new facility dao
	 */
	public void setFacilityDao(FacilityDao facilityDao) {
		this.facilityDao = facilityDao;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IProducerDao#create(int, int, int, int, java.lang.String, java.lang.String)
	 */
	/**
	 * Creates the.
	 *
	 * @param userId the user id
	 * @param hostId the host id
	 * @param facilityId the facility id
	 * @param numberOfProductions the number of productions
	 * @param screenName the screen name
	 * @param homeDir the home dir
	 */
	public void create(int userId, int hostId, int facilityId, int numberOfProductions, String screenName, String homeDir) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("INSERT INTO producer(id, idNum, homeDir, userId, hostId, facilityId, numberOfProductions) VALUES(?, ?, ?, ?, ?, ?, ?)", new Object[] { userId, screenName, homeDir, userId, hostId, facilityId, numberOfProductions });
	}

	/**
	 * Update number of productions by user id.
	 *
	 * @param userId the user id
	 */
	public void updateNumberOfProductionsByUserId(int userId) {
		JdbcTemplate template = new JdbcTemplate(this.getDataSource());
		int anzahlProd = template.queryForInt("SELECT COUNT(*) FROM video WHERE producerId="+userId);
		template.update("UPDATE producer SET numberOfProductions = "+anzahlProd+" WHERE userId = ?", new Object[] { userId });
	}

	/**
	 * Checks for lecture series.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 */
	public boolean hasLectureSeries(int userId) {
		JdbcTemplate template = new JdbcTemplate(this.getDataSource());
		return (template.queryForInt("SELECT COUNT(*) FROM producer_lectureseries WHERE producerId="+userId)>0);
	}
	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.IProducerDao#deleteAll()
	 */
	/**
	 * Delete all.
	 */
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see de.uhh.l2g.dao.IProducerDao#deleteById(int)
	 */
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(int id) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		update.update("DELETE FROM producer WHERE id = ?;", new Object[] { id });
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IProducerDao#getAll()
	 */
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@SuppressWarnings("unchecked")
	public List<Producer> getAll() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT id, userId, hostId, facilityId, numberOfProductions, idNum, homeDir FROM producer", new ProducerRowMapper());
	}

	/**
	 * Gets the all sorted by last name.
	 *
	 * @return the all sorted by last name
	 */
	@SuppressWarnings("unchecked")
	public List<ProducerLRInfo> getAllSortedByLastName() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT p.id, p.userId, p.hostId, p.facilityId, p.numberOfProductions, p.idNum, p.homeDir, p.approved, u.emailAddress, u.screenName, c.firstName, c.lastName, u.createDate, u.lastLoginDate FROM producer AS p INNER JOIN User_ AS u ON (p.userId = u.userId) INNER JOIN Contact_ AS c ON (u.contactId = c.contactId) WHERE p.approved=1 ORDER BY c.lastName", new ProducerRowMapper(true));
	}

	/**
	 * Gets the by facility id sorted by last name.
	 *
	 * @param facilityId the facility id
	 * @return the by facility id sorted by last name
	 */
	@SuppressWarnings("unchecked")
	public List<ProducerLRInfo> getByFacilityIdSortedByLastName(int facilityId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT p.id, p.userId, p.hostId, p.facilityId, p.numberOfProductions, p.idNum, p.homeDir, p.approved, u.emailAddress, u.screenName, c.firstName, c.lastName, u.createDate, u.lastLoginDate FROM producer AS p INNER JOIN User_ AS u ON (p.userId = u.userId) INNER JOIN Contact_ AS c ON (u.contactId = c.contactId) WHERE ( p.facilityId=" + facilityId + " AND p.approved=1 ) ORDER BY c.lastName", new ProducerRowMapper(true));
	}

	/**
	 * Gets the with lr info.
	 *
	 * @param approved the approved
	 * @param facultyId the faculty id
	 * @param pageNumber the page number
	 * @param pageSize the page size
	 * @return the with lr info
	 */
	@SuppressWarnings("unchecked")
	public PaginationResultProducer getWithLRInfo(String approved, int facultyId, int pageNumber, int pageSize) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		String queryPagination = "SELECT p.id, p.userId, p.hostId, p.facilityId, p.numberOfProductions, p.idNum, p.homeDir, p.approved, u.emailAddress, u.screenName, c.firstName, c.lastName, u.createDate, u.lastLoginDate FROM producer AS p ";
		queryPagination += "INNER JOIN User_ AS u ON (p.userId = u.userId) ";
		queryPagination += "INNER JOIN Contact_ AS c ON (u.contactId = c.contactId) ";

		List<Object> paramPage = new ArrayList<Object>();

		String queryNumbers = "SELECT COUNT(p.id) FROM producer AS p ";
		List<Object> paramNumbers = new ArrayList<Object>();

		if ("true".equals(approved) || "false".equals(approved) || facultyId > 0) {
			String nextLink = "WHERE ";
			if ("true".equals(approved)) {
				queryNumbers += nextLink + "p.approved = ? ";
				queryPagination += nextLink + "p.approved = ? ";
				nextLink = "AND ";
				paramNumbers.add(new Boolean(true));
				paramPage.add(new Boolean(true));
			} else if ("false".equals(approved)) {
				queryNumbers += nextLink + "p.approved = ? ";
				queryPagination += nextLink + "p.approved = ? ";
				nextLink = "AND ";
				paramNumbers.add(new Boolean(false));
				paramPage.add(new Boolean(false));
			}

			if (facultyId > 0) {
				queryNumbers += nextLink + "p.facilityId = ? ";
				queryPagination += nextLink + "p.facilityId = ? ";
				paramNumbers.add(new Integer(facultyId));
				paramPage.add(new Integer(facultyId));

			}
		}

		queryPagination += "LIMIT ?, ? ";
		paramPage.add(new Integer((pageNumber - 1) * pageSize));
		paramPage.add(new Integer(pageSize));
		int numResults = select.queryForInt(queryNumbers, paramNumbers.toArray());
		List<ProducerLRInfo> r = select.query(queryPagination, paramPage.toArray(), new ProducerRowMapper(true));

		PaginationResultProducer result = new PaginationResultProducer();
		result.setPaginationResult(r);

		int myQuotient = (numResults / pageSize);
		int myRest = numResults % pageSize;

		result.setNumberPages(myRest > 0 ? (myQuotient + 1) : myQuotient);
		return result;

	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IProducerDao#getById(int)
	 */
	/**
	 * Gets the by id.
	 *
	 * @param l the l
	 * @return the by id
	 */
	public List<Producer> getById(long l) {
		return this.getByUserId(l);
	}
	
	/**
	 * Gets the producer lr info by id.
	 *
	 * @param l the l
	 * @return the producer lr info by id
	 */
	@SuppressWarnings("unchecked")
	public List<ProducerLRInfo> getProducerLRInfoById(long l) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		String query = "SELECT p.id, p.userId, p.hostId, p.facilityId, p.numberOfProductions, p.idNum, p.homeDir, p.approved, u.emailAddress, u.screenName, u.firstName, u.lastName, u.createDate, u.lastLoginDate FROM producer AS p ";
		query += "INNER JOIN User_ AS u ON (p.userId = u.userId) WHERE p.userId = ?";

		return select.query(query, new Object[] { l }, new ProducerRowMapper(true));
	}

	/**
	 * Checks if is producer.
	 *
	 * @param l the l
	 * @return true, if is producer
	 */
	public boolean isProducer(long l) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		String query = "SELECT COUNT(*) FROM producer AS p ";
		query += "WHERE p.userId = ? AND approved = 1";
		int res = select.queryForInt(query, new Object[] { l });
		return res == 1;
	}

	/**
	 * Sets the facility.
	 *
	 * @param id the id
	 * @param facilityId the facility id
	 * @return the int
	 */
	public int setFacility(int id, int facilityId) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		return update.update("UPDATE producer SET facilityId = ? WHERE userId = ?", new Object[] { facilityId, id });
	}

	/**
	 * Sets the facility.
	 *
	 * @param id the id
	 * @param facilityId the facility id
	 * @param hostId the host id
	 * @return the int
	 */
	public int setFacility(int id, int facilityId, int hostId) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		return update.update("UPDATE producer SET facilityId = ?, hostId = ? WHERE userId = ?", new Object[] { facilityId, hostId, id });
	}

	/**
	 * Sets the facility.
	 *
	 * @param id the id
	 * @param facilityId the facility id
	 * @param hostId the host id
	 * @param roleId the role id
	 * @param approved the approved
	 * @return the int
	 */
	public int setFacility(long id, int facilityId, int hostId, int roleId, boolean approved) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		try {
			if (approved) {
				if (!UserLocalServiceUtil.hasRoleUser(roleId, id)) {
					UserLocalServiceUtil.addRoleUsers(roleId, new long[] { id });
				}
			} else {
				UserLocalServiceUtil.deleteRoleUser(roleId, id);
			}
			return update.update("UPDATE producer SET approved = ?, facilityId = ?, hostId = ? WHERE userId = ?", new Object[] { approved, facilityId, hostId, id });
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Sets the approved.
	 *
	 * @param id the id
	 * @param roleId the role id
	 * @param approved the approved
	 * @return the int
	 */
	public int setApproved(int id, int roleId, boolean approved) {
		JdbcTemplate update = new JdbcTemplate(this.getDataSource());
		try {
			if (approved) {
				if (!UserLocalServiceUtil.hasRoleUser(roleId, id)) {
					UserLocalServiceUtil.addRoleUsers(roleId, new long[] { id });
				}
			} else {
				UserLocalServiceUtil.deleteRoleUser(roleId, id);
			}
			return update.update("UPDATE producer SET approved = ? WHERE userId = ?", new Object[] { approved, id });
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Gets the by user id.
	 *
	 * @param l the l
	 * @return the by user id
	 */
	@SuppressWarnings("unchecked")
	public List<Producer> getByUserId(long l) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return fillProducerListWithProperties(select.query("SELECT id, userId, hostId, facilityId, numberOfProductions, idNum, homeDir FROM producer WHERE userId=" + l, new ProducerRowMapper()));
	}

	/**
	 * Gets the by user id and is approved.
	 *
	 * @param l the l
	 * @return the by user id and is approved
	 */
	@SuppressWarnings("unchecked")
	public List<Producer> getByUserIdAndIsApproved(long l) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return fillProducerListWithProperties(select.query("SELECT id, userId, hostId, facilityId, numberOfProductions, idNum, homeDir FROM producer WHERE userId=" + l + " AND approved=1", new ProducerRowMapper()));
	}
	
	/**
	 * Gets the producer lr info by id and is approved.
	 *
	 * @param l the l
	 * @return the producer lr info by id and is approved
	 */
	@SuppressWarnings("unchecked")
	public List<ProducerLRInfo> getProducerLRInfoByIdAndIsApproved(long l) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		String query = "SELECT p.id, p.userId, p.hostId, p.facilityId, p.numberOfProductions, p.idNum, p.homeDir, p.approved, u.emailAddress, u.screenName, u.firstName, u.lastName, u.createDate, u.lastLoginDate FROM producer AS p ";
		query += "INNER JOIN User_ AS u ON (p.userId = u.userId) WHERE (p.userId = ? AND p.approved=1)";

		return select.query(query, new Object[] { l }, new ProducerRowMapper(true));
	}
	
	/**
	 * Gets the by facility id.
	 *
	 * @param facilityId the facility id
	 * @return the by facility id
	 */
	@SuppressWarnings("unchecked")
	public List<Producer> getByFacilityId(int facilityId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT producer.id, producer.userId, producer.hostId, producer.facilityId, producer.numberOfProductions, producer.idNum, producer.homeDir FROM producer  WHERE ( facilityId=" + facilityId + " AND approved=1)", new ProducerRowMapper());
	}

	/**
	 * Gets the by host id.
	 *
	 * @param hostId the host id
	 * @return the by host id
	 */
	@SuppressWarnings("unchecked")
	public List<Producer> getByHostId(int hostId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.query("SELECT producer.id, producer.userId, producer.hostId, producer.facilityId, producer.numberOfProductions, producer.idNum, producer.homeDir FROM producer  WHERE ( hostId=" + hostId + " AND approved=1)", new ProducerRowMapper());
	}
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IProducerDao#getFromLectureseriesId(int)
	 */
	/**
	 * Gets the from lectureseries id.
	 *
	 * @param id the id
	 * @return the from lectureseries id
	 */
	@SuppressWarnings("unchecked")
	public List<Producer> getFromLectureseriesId(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		String query = "SELECT p.id, p.userId, p.hostId, p.facilityId, p.numberOfProductions, p.idNum, p.homeDir FROM producer_lectureseries AS pc INNER JOIN producer AS p ON pc.producerId = p.id ";
		query += "WHERE pc.lectureseriesId = ?;";

		return select.query(query, new Object[] { id }, new ProducerRowMapper());
	}

	/**
	 * Fill producer list with properties.
	 *
	 * @param producerList the producer list
	 * @return the list
	 */
	public List<Producer> fillProducerListWithProperties(List<Producer> producerList) {
		ListIterator<Producer> it = producerList.listIterator();
		while (it.hasNext()) {
			Producer p = it.next();
			p.setFacilityList(getFacilityDao().getById(p.getFacilityId()));
			p.setHasLectureseries(hasLectureSeries(p.getId()));
		}
		return producerList;
	}

}
