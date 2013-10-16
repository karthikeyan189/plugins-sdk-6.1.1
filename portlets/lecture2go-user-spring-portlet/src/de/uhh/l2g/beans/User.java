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
package de.uhh.l2g.beans;

import java.util.List;

/**
 * The Class User.
 */
public class User extends Coordinator{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The object coordinator. */
	private Coordinator objectCoordinator;
	
	/** The object producer. */
	private Producer objectProducer;
	
	/** The object student. */
	private Student objectStudent;
	
	/**
	 * Gets the object coordinator.
	 *
	 * @return the object coordinator
	 */
	public Coordinator getObjectCoordinator() {
		return objectCoordinator;
	}

	/**
	 * Sets the object coordinator.
	 *
	 * @param objectCoordinator the new object coordinator
	 */
	public void setObjectCoordinator(Coordinator objectCoordinator) {
		this.objectCoordinator = objectCoordinator;
	}

	/**
	 * Gets the object producer.
	 *
	 * @return the object producer
	 */
	public Producer getObjectProducer() {
		return objectProducer;
	}

	/**
	 * Sets the object producer.
	 *
	 * @param objectProducer the new object producer
	 */
	public void setObjectProducer(Producer objectProducer) {
		this.objectProducer = objectProducer;
	}

	/**
	 * Gets the object student.
	 *
	 * @return the object student
	 */
	public Student getObjectStudent() {
		return objectStudent;
	}

	/**
	 * Sets the object student.
	 *
	 * @param objectStudent the new object student
	 */
	public void setObjectStudent(Student objectStudent) {
		this.objectStudent = objectStudent;
	}

	/** The first name. */
	private String firstName = "";

	/* (non-Javadoc)
	 * @see de.uhh.l2g.beans.Student#getFirstName()
	 */
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.beans.Student#setFirstName(java.lang.String)
	 */
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/** The middle name. */
	private String middleName = "";

	/**
	 * Gets the middle name.
	 *
	 * @return the middle name
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets the mittel name.
	 *
	 * @param middleName the new mittel name
	 */
	public void setMittelName(String middleName) {
		this.middleName = middleName;
	}

	/** The family name. */
	private String familyName = "";

	/**
	 * Gets the family name.
	 *
	 * @return the family name
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * Sets the nachname.
	 *
	 * @param familyName the new nachname
	 */
	public void setNachname(String familyName) {
		this.familyName = familyName;
	}
	
	/** The user roles. */
	private List<String> userRoles;
	
    /**
     * Gets the user roles.
     *
     * @return the user roles
     */
    public List<String> getUserRoles() {
		return userRoles;
	}

	/**
	 * Sets the user roles.
	 *
	 * @param userRoles the new user roles
	 */
	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}

	/** The is student. */
	private boolean isStudent = false;
    
    /** The is coordinator. */
    private boolean isCoordinator = false;
    
    /** The is producer. */
    private boolean isProducer = false;
    
    /** The is l2goadmin. */
    private boolean isL2goadmin = false;

	/**
	 * Gets the checks if is l2goadmin.
	 *
	 * @return the checks if is l2goadmin
	 */
	public boolean getIsL2goadmin() {
		return isL2goadmin;
	}

	/**
	 * Sets the l2goadmin.
	 *
	 * @param isL2goadmin the new l2goadmin
	 */
	public void setL2goadmin(boolean isL2goadmin) {
		this.isL2goadmin = isL2goadmin;
	}

	/**
	 * Gets the checks if is student.
	 *
	 * @return the checks if is student
	 */
	public boolean getIsStudent() {
		return isStudent;
	}

	/**
	 * Sets the student.
	 *
	 * @param isStudent the new student
	 */
	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	/**
	 * Gets the checks if is coordinator.
	 *
	 * @return the checks if is coordinator
	 */
	public boolean getIsCoordinator() {
		return isCoordinator;
	}

	/**
	 * Sets the coordinator.
	 *
	 * @param isCoordinator the new coordinator
	 */
	public void setCoordinator(boolean isCoordinator) {
		this.isCoordinator = isCoordinator;
	}

	/**
	 * Gets the checks if is producer.
	 *
	 * @return the checks if is producer
	 */
	public boolean getIsProducer() {
		return isProducer;
	}

	/**
	 * Sets the producer.
	 *
	 * @param isProducer the new producer
	 */
	public void setProducer(boolean isProducer) {
		this.isProducer = isProducer;
	}
    
}
