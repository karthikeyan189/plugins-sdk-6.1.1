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

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * The Class L2gSystemDao.
 */
public class L2gSystemDao extends JdbcDaoSupport {

	/**
	 * Setup wizard is active.
	 *
	 * @return true, if successful
	 */
	public boolean setupWizardIsActive () {
		JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
		boolean ret = false;
		String sql = "SELECT setupwizard FROM l2gsys WHERE id=1";
		int i = jt.queryForInt(sql);
		if (i==0) ret = true;
		return ret;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion () {
		JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
		String ret = "";
		String sql = "SELECT version FROM l2gsys WHERE id=1";
		Map<?,?> m = jt.queryForMap(sql);
		ret = m.get("version").toString();
		return ret;
	}
	
	/**
	 * Sets the setup wizard active.
	 *
	 * @param i the new setup wizard active
	 */
	public void setSetupWizardActive(int i){
		JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
		jt.update("UPDATE l2gsys SET setupwizard=? WHERE id=1", new Object[] {i});
	}

}
