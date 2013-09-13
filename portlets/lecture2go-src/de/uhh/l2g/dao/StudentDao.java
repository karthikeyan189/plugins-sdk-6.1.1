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

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import de.uhh.l2g.beans.Student;


/**
 * The Class StudentDao.
 */
public class StudentDao extends JdbcDaoSupport implements IStudentDao {

	/** The factory. */
	private XmlBeanFactory factory;
	
	/**
	 * Gets the factory.
	 *
	 * @return the factory
	 */
	public XmlBeanFactory getFactory() {
		return factory;
	}

	/**
	 * Sets the factory.
	 *
	 * @param factory the factory
	 */
	public void setFactory(XmlBeanFactory factory) {
		this.factory = factory;
	}

	/**
	 * Gets the by user id.
	 *
	 * @param l the user id
	 * @return the by user id
	 */
	@SuppressWarnings("unchecked")
	public List<Student> getByUserId(long l) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		String query="SELECT User_.userId, Contact_.firstName, Contact_.lastName, User_.screenName, User_.emailAddress, User_.createDate, User_.lastLoginDate FROM User_, Contact_ WHERE ( User_.userId=? AND User_.contactId=Contact_.contactId )";
		return select.query(query, new Object[] { l }, new StudentRowMapper());
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
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IStudentDao#deleteAll()
	 */
	public void deleteAll() {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IStudentDao#deleteById(int)
	 */
	public void deleteById(int id) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IStudentDao#getAll()
	 */
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.dao.IStudentDao#getById(int)
	 */
	public List<Student> getById(long l) {
		return getByUserId(l);
	}

}
