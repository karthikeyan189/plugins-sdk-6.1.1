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

import de.uhh.l2g.beans.Lectureseries;

/**
 * The Class StudentMetaDataModel.
 */
public class StudentMetaDataModel extends ProducerMetaDataModel {

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.Page#getRemoteUserId()
	 */
	@Override
	public Integer getRemoteUserId() {
		return remoteUserId;
	}
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.Page#setRemoteUserId(java.lang.Integer)
	 */
	@Override
	public void setRemoteUserId(Integer remoteUserId) {
		this.remoteUserId = remoteUserId;
	}
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#getAction()
	 */
	@Override
	public String getAction() {
		return action;
	}
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#setAction(java.lang.String)
	 */
	@Override
	public void setAction(String action) {
		this.action = action;
	}
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.Objects#getLectureseriesList()
	 */
	@Override
	public List<Lectureseries> getLectureseriesList() {
		return lectureseriesList;
	}
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.Objects#setLectureseriesList(java.util.List)
	 */
	@Override
	public void setLectureseriesList(List<Lectureseries> lectureseriesList) {
		this.lectureseriesList = lectureseriesList;
	}
	
	/** The lectureseries list. */
	private List<Lectureseries> lectureseriesList;
	
	/** The remote user id. */
	private Integer remoteUserId;
	
	/** The action. */
	private String action;	
	
}
