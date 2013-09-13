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


/**
 * The Class ProducerModel.
 */

public class ProducerModel extends StudentModel {

	/** The number productions. */
	private int numberProductions;

	/** The approved. */
	private boolean approved = false;

	/**
	 * Gets the number productions.
	 *
	 * @return the number productions
	 */
	public int getNumberProductions() {
		return numberProductions;
	}

	/**
	 * Sets the number productions.
	 *
	 * @param numberProductions the number productions
	 */
	public void setNumberProductions(int numberProductions) {
		this.numberProductions = numberProductions;
	}

	/**
	 * Sets the approved.
	 *
	 * @param approved the approved
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	/**
	 * Checks if is approved.
	 *
	 * @return true, if checks if is approved
	 */
	public boolean isApproved() {
		return approved;
	}

	/** The producer id. */
	private int producerId = 0;

	/**
	 * Gets the producer id.
	 *
	 * @return the producer id
	 */
	public int getProducerId() {
		return producerId;
	}

	/**
	 * Sets the producer id.
	 *
	 * @param producerId the producer id
	 */
	public void setProducerId(int producerId) {
		this.producerId = producerId;
	}

	/** The host id. */
	private Integer hostId;

	/**
	 * Gets the host id.
	 *
	 * @return the host id
	 */
	public Integer getHostId() {
		return hostId;
	}

	/**
	 * Sets the host id.
	 *
	 * @param hostId the host id
	 */
	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}

	/** The metadata id. */
	private Integer metadataId;

	/**
	 * Gets the metadata id.
	 *
	 * @return the metadata id
	 */
	public Integer getMetadataId() {
		return metadataId;
	}

	/**
	 * Sets the metadata id.
	 *
	 * @param metadataId the metadata id
	 */
	public void setMetadataId(Integer metadataId) {
		this.metadataId = metadataId;
	}


}
