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
package de.uhh.l2g.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.web.portlet.mvc.SimpleFormController;

import de.uhh.l2g.beans.Facility;
import de.uhh.l2g.beans.Host;
import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.ProducerLRInfo;
import de.uhh.l2g.dao.FacilityDao;
import de.uhh.l2g.dao.HostDao;
import de.uhh.l2g.dao.LectureseriesDao;
import de.uhh.l2g.dao.LicenseDao;
import de.uhh.l2g.dao.MetadataDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.ProducerMetaDataInputModel;

/**
 * The Class ProducerMetaDataInputController.
 */
public class ProducerMetaDataInputController extends SimpleFormController{

	/** The utility bean factory. */
	private XmlBeanFactory utilityBeanFactory;
	
	/**
	 * Gets the utility bean factory.
	 *
	 * @return the utility bean factory
	 */
	public XmlBeanFactory getUtilityBeanFactory() {
		return utilityBeanFactory;
	}

	/**
	 * Sets the utility bean factory.
	 *
	 * @param utilityBeanFactory the utility bean factory
	 */
	public void setUtilityBeanFactory(XmlBeanFactory utilityBeanFactory) {
		this.utilityBeanFactory = utilityBeanFactory;
	}

	/** The dao bean factory. */
	private XmlBeanFactory daoBeanFactory;
	
	/**
	 * Gets the dao bean factory.
	 *
	 * @return the dao bean factory
	 */
	public XmlBeanFactory getDaoBeanFactory() {
		return daoBeanFactory;
	}

	/**
	 * Sets the dao bean factory.
	 *
	 * @param beanFactory the dao bean factory
	 */
	public void setDaoBeanFactory(XmlBeanFactory beanFactory) {
		this.daoBeanFactory = beanFactory;
	}
	
	/** The last id. */
	int lastId = 0;

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.SimpleFormController#doSubmitAction(java.lang.Object)
	 */
	@Override
	public void doSubmitAction(Object o) {

		ProducerMetaDataInputModel model = (ProducerMetaDataInputModel) o;
		// Do something with the command object, e.g., save to database
		// save all metadata informations to the database
		Host host = model.getHostList().iterator().next();
		Producer producer = model.getProducerList().iterator().next();

		// -- save to database
		// dublin-core metadata
		int metadataInsertId = ((MetadataDao)getDaoBeanFactory().getBean("metadataDao")).createAndReturnGeneratedId("", model.getFormat(), model.getType(), model.getLanguage(), model.getTitle(), model.getSubject(), model.getCoverage(), model.getDescription(), model.getCreator(), model.getPublisher(), model.getContributor(), model.getRightsHolder(), model.getRights(), model.getProvenance(), model.getSource(), model.getRelation(), model.getAudience(), model.getInstructionalMethod(), model.getDate());
		// lecture2go video data
		// String title, String tags, int lectureseriesId,
		// int ownerId, int producerId, String containerFormat,
		// String url, String resolution, int duration, int hostId, int metadataId
		int videoInsertId = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).createAndReturnGeneratedId(model.getTitle(), model.getTags(), model.getLectureseriesId(), producer.getId(), producer.getId(), model.getContainerFormat(), null, model.getAufloesung(), model.getDuration(), host.getId(), metadataInsertId, producer.getFacilityId(), model.getCitation2go());

		// insert lectureseriesId into the table 'video_facility'
		List<Facility> facilitiesList = ((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).getFromLectureseriesId(model.getLectureseriesId());
		Iterator<Facility> i = facilitiesList.iterator();
		while (i.hasNext()) {
			((FacilityDao)getDaoBeanFactory().getBean("facilityDao")).addFacilityIdAndVideoIdToTableVideo_facility(i.next().getId(), videoInsertId);
		}
		
		//update uploads for producer
		((ProducerDao)getDaoBeanFactory().getBean("producerDao")).updateNumberOfProductionsByUserId(producer.getId());
		
		// set license
		boolean byncsa = false;
		boolean byncnd = false;
		boolean l2go = false;

		try {
			if (model.getLicense().equals("ccbyncsa")) byncsa = true;
		} catch (NullPointerException npe) {
		}
		try {
			if (model.getLicense().equals("ccbyncnd")) byncnd = true;
		} catch (NullPointerException npe) {
		}

		((LicenseDao)getDaoBeanFactory().getBean("licenseDao")).create(videoInsertId, false, false, byncnd, byncsa, false, false, l2go);

		// reset some properties
		model.setLectureseriesId(0);
		model.setFormat("");
		model.setType("");
		model.setLanguage("");
		model.setTitle("");
		model.setSubject("");
		model.setCoverage("");
		model.setLongDescription("");
		model.setDescription("");
		model.setCreator("");
		model.setPublisher("");
		model.setContributor("");
		model.setRightsHolder("");
		model.setRights("");
		model.setProvenance("");
		model.setSource("");
		model.setRelation("");
		model.setAudience("");
		model.setInstructionalMethod("");
		model.setDate("");
		model.setDuration(null);
		model.setSuccess(true);
	}


	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#formBackingObject(javax.portlet.PortletRequest)
	 */
	@Override
	protected Object formBackingObject(PortletRequest request) {
		ProducerMetaDataInputModel model = new ProducerMetaDataInputModel();
		
		String autocompare = request.getParameter("autocompare");
		String lectureseriesId = request.getParameter("lectureseriesId");
		
		// get remote user
		String remoteUserId = request.getRemoteUser();
		int remoteUId = new Integer(remoteUserId);
		Producer producer = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByUserId(remoteUId).iterator().next();
		
		if (!(autocompare == null) && !lectureseriesId.equals("0")) {
			int cId = new Integer(lectureseriesId);
			model.setLectureseriesId(cId);
			List<Lectureseries> lectureseriesList = ((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getById(cId);
			Lectureseries lectureseries = lectureseriesList.iterator().next();
			model.setTitle(lectureseries.getName());
			model.setLanguage(lectureseries.getLanguage());
			model.setLongDescription(lectureseries.getLongDesc());
			model.setCreator(lectureseries.getInstructorsString());
			List<ProducerLRInfo> p = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getProducerLRInfoById(remoteUId);
			String name = p.iterator().next().getFirstName() + " " + p.iterator().next().getLastName();
			model.setRightsHolder(name);
		} else {
			//reset properties
			model.setTitle("");
			model.setLanguage("");
			model.setLongDescription("");
			model.setCreator("");
			model.setPublisher("");
			model.setRightsHolder("");
			model.setSuccess(false);
			model.setLicense(null);
		}
		model.setHostList(((HostDao)getDaoBeanFactory().getBean("hostDao")).getById(producer.getHostId()));
		model.setProducerList(((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByUserId(remoteUId));
		model.setLectureseriesList(((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getLectureseriesForProducer(remoteUId));

		return model;
	}

}
