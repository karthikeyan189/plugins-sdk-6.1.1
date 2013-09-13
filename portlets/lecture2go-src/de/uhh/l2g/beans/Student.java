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
package de.uhh.l2g.beans;

import java.sql.Date;
import java.util.List;


/**
 * The Class Student.
 */
public class Student extends Guest {

	private long userId;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	/** The id num. */
	private String idNum = "new java.lang.String()";
	
	/**
	 * Gets the id num.
	 *
	 * @return the id num
	 */
	public String getIdNum() {
		return idNum;
	}

	/**
	 * Sets the id num.
	 *
	 * @param idNum the id num
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	/** The id. */
	private int id = 0;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/** The facility id. */
	private int facilityId = 0;

	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the facility id
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	
	/**
	 * Gets the create date.
	 *
	 * @return the creates the date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the create date.
	 *
	 * @param date the creates the date
	 */
	public void setCreateDate(Date date) {
		this.createDate = date;
	}

	/**
	 * Gets the last login date.
	 *
	 * @return the last login date
	 */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * Sets the last login date.
	 *
	 * @param date the last login date
	 */
	public void setLastLoginDate(Date date) {
		this.lastLoginDate = date;
	}

	/** The facility list. */
	private List<Facility> facilityList;
	
	/**
	 * Gets the facility list.
	 *
	 * @return the facility list
	 */
	public List<Facility> getFacilityList() {
		return facilityList;
	}

	/**
	 * Sets the facility list.
	 *
	 * @param facilityList the facility list
	 */
	public void setFacilityList(List<Facility> facilityList) {
		this.facilityList = facilityList;
	}

	/** The create date. */
	private Date createDate;
	
	/** The last login date. */
	private Date lastLoginDate;
	
	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The screen name. */
	private String screenName;

	/** The email address. */
	private String emailAddress;

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress the email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the screen name.
	 *
	 * @param screenName the screen name
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * Gets the screen name.
	 *
	 * @return the screen name
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
}
