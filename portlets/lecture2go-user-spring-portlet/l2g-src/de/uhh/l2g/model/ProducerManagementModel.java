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
package de.uhh.l2g.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.uhh.l2g.beans.ProducerLRInfo;

/**
 * The Class ProducerManagementModel.
 */
@SuppressWarnings("serial")
public class ProducerManagementModel extends ProducerModel implements Serializable {

	/** The all faculties. */
	private Map<String, String> allFaculties; // list of all faculties

	/** The producer lr list. */
	private List<ProducerLRInfo> producerLRList;

	/**
	 * Instantiates a new producer management model.
	 */
	public ProducerManagementModel() {
		this.setAllFaculties(new LinkedHashMap<String, String>());
	}

	/**
	 * Gets the producer lr list.
	 *
	 * @return the producer lr list
	 */
	public List<ProducerLRInfo> getProducerLRList() {
		return producerLRList;
	}

	/**
	 * Gets the all faculties.
	 *
	 * @return the all faculties
	 */
	public Map<String, String> getAllFaculties() {
		return allFaculties;
	}

	/**
	 * Sets the all faculties.
	 *
	 * @param allFaculties the all faculties
	 */
	public void setAllFaculties(Map<String, String> allFaculties) {
		this.allFaculties = allFaculties;
	}

	/**
	 * Sets the producer lr list.
	 *
	 * @param producerLRList the new producer lr list
	 */
	public void setProducerLRList(List<ProducerLRInfo> producerLRList) {
		this.producerLRList = producerLRList;
	}

}
