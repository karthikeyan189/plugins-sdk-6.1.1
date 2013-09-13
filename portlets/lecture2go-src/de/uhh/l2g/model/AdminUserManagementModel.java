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
package de.uhh.l2g.model;

import java.util.List;
import java.util.Map;

import de.uhh.l2g.beans.Coordinator;
import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.User;

/**
 * The Class AdminUserManagementModel.
 */
public class AdminUserManagementModel extends AdminModel {
	
	/** The coordinator. */
	private Coordinator coordinator;
	
	/**
	 * Gets the coordinator.
	 *
	 * @return the coordinator
	 */
	public Coordinator getCoordinator() {
		return coordinator;
	}

	/**
	 * Sets the coordinator.
	 *
	 * @param coordinator the coordinator
	 */
	public void setCoordinator(Coordinator coordinator) {
		this.coordinator = coordinator;
	}

	/**
	 * Gets the user list.
	 *
	 * @return the user list
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * Sets the user list.
	 *
	 * @param userList the user list
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/** The user list. */
	private List<User>  userList;
	
	/** The facilities list. */
	private List<Facility> facilitiesList;

	/**
	 * Gets the facilities list.
	 *
	 * @return the facilities list
	 */
	public List<Facility> getFacilitiesList() {
		return facilitiesList;
	}

	/**
	 * Sets the facilities list.
	 *
	 * @param facilitiesList the facilities list
	 */
	public void setFacilitiesList(List<Facility> facilitiesList) {
		this.facilitiesList = facilitiesList;
	}
	
	/** The role. */
	private String role;

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	/** The role id. */
	private int roleId;

	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * Sets the role id.
	 *
	 * @param roleId the role id
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/** The l2go roles. */
	private Map<String, String> l2goRoles;

	/**
	 * Gets the l2go roles.
	 *
	 * @return the l2go roles
	 */
	public Map<String, String> getL2goRoles() {
		return l2goRoles;
	}

	/**
	 * Sets the l2go roles.
	 *
	 * @param l2goRoles the l2go roles
	 */
	public void setL2goRoles(Map<String, String> l2goRoles) {
		this.l2goRoles = l2goRoles;
	}
	
}
