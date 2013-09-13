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

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import de.uhh.l2g.util.L2goPropsUtil;

/**
 * The Class DatabaseManager.
 */
public class DatabaseManager extends DriverManagerDataSource {
	
	/** The url. */
	String url="";
	
	/** The username. */
	String username="";
	
	/** The password. */
	String password="";
	
	/** The driver. */
	String driver="";
	
	/**
	 * The Constructor.
	 */
	public DatabaseManager(){
		url = L2goPropsUtil.get("jdbc.default.url");
		username = L2goPropsUtil.get("jdbc.default.username");
		password = L2goPropsUtil.get("jdbc.default.password");
		driver = L2goPropsUtil.get("jdbc.default.driverClassName");
	
		super.setDriverClassName(this.driver);
		super.setUrl(this.url);
		super.setUsername(this.username);
		super.setPassword(this.password);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.datasource.AbstractDriverBasedDataSource#setUrl(java.lang.String)
	 */
	@Override
	public void setUrl(String url) {
		this.url = url;
		super.setUrl(this.url);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.datasource.AbstractDriverBasedDataSource#setUsername(java.lang.String)
	 */
	@Override
	public void setUsername(String username) {
		this.username = username;
		super.setUsername(this.username);
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.datasource.AbstractDriverBasedDataSource#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		this.password = password;
		super.setPassword(this.password);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.datasource.DriverManagerDataSource#setDriverClassName(java.lang.String)
	 */
	@Override
	public void setDriverClassName(String driver){
		this.driver = driver;
		super.setDriverClassName(this.driver);
	}
	
}
