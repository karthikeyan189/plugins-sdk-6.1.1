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
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.UserLocalServiceUtil;

import de.uhh.l2g.beans.User;


/**
 * The Class UserDao.
 */

public class UserDao extends JdbcDaoSupport {
	
	/** The student dao. */
	private StudentDao studentDao;
	
	/** The producer dao. */
	private ProducerDao producerDao;
	
	/** The coordinator dao. */
	private CoordinatorDao coordinatorDao;
	
	/**
	 * Gets the student dao.
	 *
	 * @return the student dao
	 */
	public StudentDao getStudentDao() {
		return studentDao;
	}

	/**
	 * Sets the student dao.
	 *
	 * @param studentDao the new student dao
	 */
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
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
	 * Gets the coordinator dao.
	 *
	 * @return the coordinator dao
	 */
	public CoordinatorDao getCoordinatorDao() {
		return coordinatorDao;
	}

	/**
	 * Sets the coordinator dao.
	 *
	 * @param coordinatorDao the new coordinator dao
	 */
	public void setCoordinatorDao(CoordinatorDao coordinatorDao) {
		this.coordinatorDao = coordinatorDao;
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	@SuppressWarnings("unchecked")
	public List<User> getById(int id) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<User> ul = select.query("SELECT C.firstName,  C.middleName,  C.lastName, U.screenName, U.createDate, U.lastLoginDate, U.userId FROM lportal.Contact_ C, lportal.User_ U WHERE ( U.userID=" + id + " AND C.contactId = U.contactId ) GROUP BY U.userId", new UserRowMapper());
		fillUserListWithProperties(ul);
		return ul;
	}
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<User> ul = select.query("SELECT C.firstName,  C.middleName,  C.lastName, U.screenName, U.createDate, U.lastLoginDate, U.userId FROM lportal.Contact_ C, lportal.User_ U WHERE C.contactId = U.contactId  GROUP BY U.userId", new UserRowMapper());
		fillUserListWithProperties(ul);
		return ul;
	}

	/**
	 * Gets the paginatet list by role id.
	 *
	 * @param start the start
	 * @param ende the ende
	 * @param roleId the role id
	 * @return the paginatet list by role id
	 */
	@SuppressWarnings("unchecked")
	public List<User> getPaginatetListByRoleId(int start, int ende, int roleId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<User> ul = select.query("SELECT C.firstName,  C.middleName,  C.lastName, U.screenName, U.createDate, U.lastLoginDate, U.userId FROM lportal.Contact_ C, lportal.User_ U, lportal.Users_Roles UR WHERE ( C.contactId = U.contactId AND (U.userId=UR.userId AND UR.roleId="+roleId+"))  GROUP BY U.userId LIMIT "+start+" , "+ende+";", new UserRowMapper());
		fillUserListWithProperties(ul);
		return ul;
	}
	
	/**
	 * Gets the paginatet list.
	 *
	 * @param start the start
	 * @param ende the ende
	 * @return the paginatet list
	 */
	@SuppressWarnings("unchecked")
	public List<User> getPaginatetList(int start, int ende) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		List<User> ul = select.query("SELECT C.firstName,  C.middleName,  C.lastName, U.screenName, U.createDate, U.lastLoginDate, U.userId, UR.roleId, UR.userId FROM lportal.Contact_ C, lportal.User_ U LEFT OUTER JOIN lportal.Users_Roles UR ON U.userId = UR.userId WHERE C.contactId = U.contactId AND NOT U.defaultUser=1 GROUP BY U.userId LIMIT "+start+" , "+ende+";", new UserRowMapper());
		fillUserListWithProperties(ul);
		return ul;
	}

	/**
	 * Gets the count user by role id.
	 *
	 * @param roleId the role id
	 * @return the count user by role id
	 */
	public int getCountUserByRoleId(int roleId) {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.queryForInt("SELECT COUNT(*) FROM lportal.User_ U, lportal.Users_Roles UR WHERE ( U.userId=UR.userId AND UR.roleId='"+roleId+"') GROUP BY UR.roleId");
	}
	
	/**
	 * Gets the count all user.
	 *
	 * @return the count all user
	 */
	public int getCountAllUser() {
		JdbcTemplate select = new JdbcTemplate(this.getDataSource());
		return select.queryForInt("SELECT COUNT(*) FROM lportal.User_");
	}
	
	/**
	 * Fill user list with properties.
	 *
	 * @param userList the user list
	 * @return the list
	 */
	public List<User> fillUserListWithProperties(List<User> userList) {
		ListIterator<User> it = userList.listIterator();
		while (it.hasNext()) {	
			User u = it.next();
			ArrayList<String> userRoles = new ArrayList<String>();
			//Users Roles
			List<Role> lr;
			try {
				lr = UserLocalServiceUtil.getUserById(u.getId()).getRoles();
				ListIterator<Role> ir = lr.listIterator();
				while(ir.hasNext()){
					String role = ir.next().getName();
					//User Roles
					if(!role.equals("User")){
						if(role.equals("Student"))u.setStudent(true);
						if(role.equals("Coordinator") && getCoordinatorDao().getById(u.getUserId()).size()>0)u.setCoordinator(true);
						if(role.equals("Producer") && getProducerDao().isProducer(u.getUserId()))u.setProducer(true);
						if(role.equals("L2Go Admin"))u.setL2goadmin(true);
						userRoles.add(role);
					}				
				}
			} catch (SystemException e1) {
				e1.printStackTrace();
			} catch (PortalException e1) {
				e1.printStackTrace();
			}

			u.setUserRoles(userRoles);
			
			//coordinator role
			if (u.getIsCoordinator())u.setObjectCoordinator(getCoordinatorDao().getById(u.getUserId()).iterator().next());
			//producer role
			if (u.getIsProducer())u.setObjectProducer(getProducerDao().getById(u.getUserId()).iterator().next());
			//student role
			if (u.getIsStudent())u.setObjectStudent(getStudentDao().getById(u.getUserId()).iterator().next());
	
			//set user parameter
			try {
				u.setNachname(UserLocalServiceUtil.getUserById(u.getId()).getLastName());
				u.setFirstName(UserLocalServiceUtil.getUserById(u.getId()).getFirstName());
				u.setScreenName(UserLocalServiceUtil.getUserById(u.getId()).getScreenName());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return userList;
	}
	
}
