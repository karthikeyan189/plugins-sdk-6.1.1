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
import org.springframework.web.portlet.bind.PortletRequestUtils;

import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.LectureseriesDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.ProducerMetaDataModel;
import de.uhh.l2g.util.ProzessManager;

/**
 * The Class ProducerMetaDataList.
 */
public class ProducerMetaDataList extends AbstractProducerMetaDataController{

	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractProducerMetaDataController#execute(javax.portlet.ActionRequest, de.uhh.l2g.model.ProducerMetaDataModel, org.springframework.validation.BindException)
	 */
	@Override
	public void execute(ActionRequest request, ProducerMetaDataModel model, BindException errors) {
		//initialization for required model-objects
		initModel(request, model);
		
		// only owner can read write execute
		//video musst belong to producer	
		if(model.getProducer().getId() == model.getVideo().getProducerId()){
			// complete action
			if (model.getAction().equals("aktivateOpenaccess")) ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).activateOpenaccess( model, model.getVideo(), model.getHost(), model.getProducer());
			//
			if (model.getAction().equals("deaktivateOpenaccess")) ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).deactivateOpenaccess(model.getVideo(),  model, model.getHost(), model.getProducer());
			//
			if (model.getAction().equals("aktivateDownload")) ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).activateDownload( model, model.getVideo(), model.getHost(), model.getProducer());
			//
			if (model.getAction().equals("deaktivateDownload")) ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).deactivateDownload( model, model.getVideo(), model.getHost(), model.getProducer());
			//
			if (model.getAction().equals("loeschen")) ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).delete(model, model.getVideo(), model.getHost(), model.getProducer(), request);
		}
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.action.AbstractProducerMetaDataController#execute(javax.portlet.RenderRequest, de.uhh.l2g.model.ProducerMetaDataModel, org.springframework.validation.BindException)
	 */
	@Override
	public void execute(RenderRequest request, ProducerMetaDataModel model, BindException errors) {

		initModel(request, model);

		//get remote user
		int remoteUserId = new Integer(request.getRemoteUser());

		//set producer 
		model.setProducer(((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getById(remoteUserId).iterator().next());
		
		//variables for paging
		int offset = 10;
		int currentSeite = model.getCurrentSeite();
		int start = (currentSeite - 1) * offset;
		
		int pageSize = PortletRequestUtils.getIntParameter(request, "pagesize", offset);
		int anzahlAllerVideos = 0;
		
		if(model.getLectureseriesId()!=0)anzahlAllerVideos = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).countByProducerIdAndLectureseriesId(remoteUserId, model.getLectureseriesId());
		else anzahlAllerVideos = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).countByProducerId(remoteUserId);

		//--paging start
		// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
		int numberOfPages = anzahlAllerVideos / offset;
		int rest = anzahlAllerVideos % offset;

		if (rest > 0 && offset < anzahlAllerVideos) numberOfPages++;
		if (offset > anzahlAllerVideos) numberOfPages = 1;

		// do pagination
		model.setPageSize(pageSize);
		model.setNumberResultPages(numberOfPages);
		model.setCurrentPageNumber(currentSeite);

		int rangeFirst = (((currentSeite - 1) / 3)) * 3 + 1;
		int rangeLast = Math.min((((currentSeite - 1) / 3)) * 3 + 3, model.getNumberResultPages());
		model.setPageRangeFirst(rangeFirst);
		model.setPageRangeLast(rangeLast);
		model.setHasPrev(rangeFirst > 1);
		model.setHasNext(rangeLast < model.getNumberResultPages());
		// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
		//-- paging end

		List<Video> videoListByProducerIdAndCounter;
		if (model.getLectureseriesId()!=0)videoListByProducerIdAndCounter = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getByProducerIdAndCounterAndlectureseriesId(remoteUserId, start, offset, model.getLectureseriesId());
		else videoListByProducerIdAndCounter = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getByProducerIdAndCounter(remoteUserId, start, offset);

		//get full info for video list
		model.setVideoList(videoListByProducerIdAndCounter);

		//set lectureseries list for user
		List<Lectureseries> lectureseriesList = ((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getLectureseriesForProducer(remoteUserId);
		model.setLectureseriesList(lectureseriesList);
	}

}
