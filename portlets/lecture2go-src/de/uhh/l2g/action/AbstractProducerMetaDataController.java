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

import de.uhh.l2g.beans.Factory;
import de.uhh.l2g.beans.Host;
import de.uhh.l2g.beans.Metadata;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.HostDao;
import de.uhh.l2g.dao.MetadataDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.ProducerMetaDataModel;


/**
 * The Class AbstractProducerMetaDataController.
 */
public abstract class AbstractProducerMetaDataController extends Factory{

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @param model the model
	 * @param errors the errors
	 */
	public abstract void execute(ActionRequest request, ProducerMetaDataModel model, BindException errors);
	
	/**
	 * Execute.
	 *
	 * @param request the request
	 * @param model the model
	 * @param errors the errors
	 */
	public abstract void execute(RenderRequest request, ProducerMetaDataModel model, BindException errors);

	/**
	 * Inits the model.
	 *
	 * @param request the request
	 * @param model the model
	 */
	protected void initModel(RenderRequest request, ProducerMetaDataModel model){
		
		Integer remoteUserId = new Integer(request.getRemoteUser());
		model.setRemoteUserId(remoteUserId);

		//current page number
		String currentSeite = request.getParameter("currentSeite");
		//set to model
		if (currentSeite!=null && currentSeite.trim().length()>0) model.setCurrentSeite(new Integer(currentSeite));
		else{
			//if not in model, set to 1
			if(model.getCurrentSeite()==0)model.setCurrentSeite(1);
		}
		
		//get lectureseries-data id
		Integer lectureseriesId=0;
		try{
			lectureseriesId=new Integer(request.getParameter("lectureseriesId"));
		}catch(NumberFormatException nfe){
			lectureseriesId=model.getLectureseriesId();
		}
		model.setLectureseriesId(lectureseriesId);
		
		Producer producer = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByUserId(model.getRemoteUserId()).iterator().next();
		model.setProducer(producer);
		
		Host host = ((HostDao)getDaoBeanFactory().getBean("hostDao")).getById(model.getProducer().getHostId()).iterator().next();
		model.setHost(host);
		
	}
	
	/**
	 * Inits the model.
	 *
	 * @param request the request
	 * @param model the model
	 */
	protected void initModel(ActionRequest request, ProducerMetaDataModel model){
		
		Integer remoteUserId = new Integer(request.getRemoteUser());
		model.setRemoteUserId(remoteUserId);

		//current page number
		String currentSeite = request.getParameter("currentSeite");
		//set to model
		if (currentSeite!=null && currentSeite.trim().length()>0) model.setCurrentSeite(new Integer(currentSeite));
		else model.setCurrentSeite(1);
		
		//get lectureseries-data id
		Integer lectureseriesId=0;
		try{
			lectureseriesId=new Integer(request.getParameter("lectureseriesId"));
		}catch(NumberFormatException nfe){
			lectureseriesId=0;
		}
		model.setLectureseriesId(lectureseriesId);
		
		Producer producer = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByUserId(model.getRemoteUserId()).iterator().next();
		model.setProducer(producer);
		
		Host host = ((HostDao)getDaoBeanFactory().getBean("hostDao")).getById(model.getProducer().getHostId()).iterator().next();
		model.setHost(host);
		
		Integer videoId = new Integer(request.getParameter("videoId"));
		List<Video> videoList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(videoId);
		Video video = videoList.iterator().next();
		model.setVideo(video);
		
		//pick meta data	
		Metadata metadata = ((MetadataDao)getDaoBeanFactory().getBean("metadataDao")).getById(video.getMetadataId()).iterator().next();
		model.setMetadata(metadata);
		
	}

}
