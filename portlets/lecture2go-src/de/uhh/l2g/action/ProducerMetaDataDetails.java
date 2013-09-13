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
package de.uhh.l2g.action;

import java.util.List;

import org.springframework.validation.BindException;

import de.uhh.l2g.beans.License;
import de.uhh.l2g.beans.Metadata;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.LicenseDao;
import de.uhh.l2g.dao.MetadataDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.ProducerMetaDataModel;
import de.uhh.l2g.util.ProzessManager;

/**
 * The Class ProducerMetaDataDetails.
 */
public class ProducerMetaDataDetails extends AbstractProducerMetaDataController{

	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractProducerMetaDataController#execute(javax.portlet.ActionRequest, de.uhh.l2g.model.ProducerMetaDataModel, org.springframework.validation.BindException)
	 */
	@Override
	public void execute(ActionRequest request, ProducerMetaDataModel model, BindException errors) {
		//initialization for required model-objects
		initModel(request, model);
		// only owner can read write execute
		//video musst belong to producer	
		if(model.getProducer().getId() == model.getVideo().getProducerId() && !errors.hasErrors()){
			if (model.getAction().equals("editMetadata")) ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).editMetadata(model, model.getVideo(), model.getMetadata());
		}	
	}
	
	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractProducerMetaDataController#execute(javax.portlet.RenderRequest, de.uhh.l2g.model.ProducerMetaDataModel, org.springframework.validation.BindException)
	 */
	@Override
	public void execute(RenderRequest request, ProducerMetaDataModel model, BindException errors) {
		//initialization for required model-objects
		initModel(request, model);
		
		try {
			int videoId = new Integer(request.getParameter("videoId"));
			//get video object
			List<Video> videoList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(videoId);
			Video video = videoList.iterator().next();
			
			//set video and lectureseries-data object to model
			model.setVideo(video);
			
			//get metadata object
			int metadataId = video.getMetadataId();
			List<Metadata> metaDataList = ((MetadataDao)getDaoBeanFactory().getBean("metadataDao")).getById(metadataId);
			Metadata metadata = metaDataList.iterator().next();
			License license = ((LicenseDao)getDaoBeanFactory().getBean("licenseDao")).getByVideoId(video.getId()).iterator().next();
			
			// l2go metadata properties
			model.setVideoId(video.getId());
			model.setUrlId(metadata.getFilenameID());
			model.setFormat(metadata.getFormat());
			model.setType(metadata.getType());
			model.setLanguage(metadata.getLanguage());
			model.setTitle(metadata.getTitle());
			model.setSubject(metadata.getSubject());
			model.setCoverage(metadata.getCoverage());
			model.setDescription(metadata.getDescription());
			model.setCreator(metadata.getCreator());
			model.setPublisher(metadata.getPublisher());
			model.setContributor(metadata.getContributor());
			model.setRightsHolder(metadata.getRightsHolder());
			model.setRights(metadata.getRights());
			model.setProvenance(metadata.getProvenance());
			model.setSource(metadata.getSource());
			model.setRelation(metadata.getRelation());
			model.setAudience(metadata.getAudience());
			model.setInstructionalMethod(metadata.getInstructionalMethod());
			model.setDate(metadata.getDate());
			
			// l2go video properties
			model.setTags(video.getTags());
			model.setAufloesung(video.getResolution());
			model.setContainerFormat(video.getContainerFormat());
			model.setDuration(video.getDuration());
			model.setProducerId(video.getProducerId());
			model.setTitle(video.getTitle());
			model.setEigentuemerId(video.getEigentuemerId());
			model.setUrl(video.getFilename());
			model.setHostId(video.getHostId());
			model.setTextId(video.getTextId());
			model.setFileSize(video.getFileSize());
			model.setGenerationDate(video.getGenerationDate());
			model.setOpenaccess(video.isOpenaccess());
			model.setDownloadAllawed(video.isDownloadAllawed());
			model.setMetadataId(video.getMetadataId());
			model.setVideoId(video.getId());
			model.setSuccess(false);
			model.setCitation2go(video.getCitation2go());
			
			// l2go video license
			if (license.getType().contains("ccbyncnd") || license.getType().contains("ccbyncsa")) {
				if (license.getType().contains("ccbyncnd")) model.setLicense("ccbyncnd");
				if (license.getType().contains("ccbyncsa")) model.setLicense("ccbyncsa");
			} else model.setLicenseCC("");

		} catch (Exception e) {
			model.setVideoId(-1);
			model.setSuccess(false);
		}
	}

}
