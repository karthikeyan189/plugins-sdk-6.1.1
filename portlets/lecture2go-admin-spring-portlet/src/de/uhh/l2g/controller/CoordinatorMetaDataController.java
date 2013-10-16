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
package de.uhh.l2g.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.AbstractFormController;

import de.uhh.l2g.beans.Coordinator;
import de.uhh.l2g.beans.Host;
import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.beans.Metadata;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.ProducerLRInfo;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.CoordinatorDao;
import de.uhh.l2g.dao.HostDao;
import de.uhh.l2g.dao.LectureseriesDao;
import de.uhh.l2g.dao.MetadataDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.CoordinatorMetaDataModel;
import de.uhh.l2g.util.ProzessManager;
import de.uhh.l2g.util.VideoGenerationDateComparator;


/**
 * The Class CoordinatorMetaDataController.
 */
public class CoordinatorMetaDataController extends AbstractFormController{

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
	 * @param beanFactory the new dao bean factory
	 */
	public void setDaoBeanFactory(XmlBeanFactory beanFactory) {
		this.daoBeanFactory = beanFactory;
	}
	
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
	 * @param utilityBeanFactory the new utility bean factory
	 */
	public void setUtilityBeanFactory(XmlBeanFactory utilityBeanFactory) {
		this.utilityBeanFactory = utilityBeanFactory;
	}

	/*
	 * (non-Javadoc)
	 * @seeorg.springframework.web.portlet.mvc.AbstractFormController#
	 * processFormSubmission(javax.portlet.ActionRequest,
	 * javax.portlet.ActionResponse, java.lang.Object,
	 * org.springframework.validation.BindException)
	 */
	/**
	 * Process form submission.
	 *
	 * @param request the request
	 * @param response the response
	 * @param command the command
	 * @param errors the errors
	 * @throws Exception the exception
	 */
	@Override
	protected void processFormSubmission(ActionRequest request, ActionResponse response, Object command, BindException errors) throws Exception {
		CoordinatorMetaDataModel model = (CoordinatorMetaDataModel) command;
		
		int videoId = new Integer(request.getParameter("videoId"));
		List<Video> vidList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(videoId);
		Video video = vidList.iterator().next();
		Host host = video.getObjectHost();
		Producer producer = video.getObjectProducer();
		Metadata metadata = ((MetadataDao)getDaoBeanFactory().getBean("metadataDao")).getById(video.getMetadataId()).iterator().next();
		String action = request.getParameter("action");

		Integer producerId = new Integer(request.getParameter("producerId"));
		Integer lectureseriesId = new Integer(request.getParameter("lectureseriesId"));

		model.setProducerId(producerId);
		model.setLectureseriesId(lectureseriesId);

		// complete action
		if (action.equals("aktivateOpenaccess")) ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).activateOpenaccess( model, video, host, producer);
		//
		if (action.equals("deaktivateOpenaccess")) ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).deactivateOpenaccess(video,  model, host, producer);
		//
		if (action.equals("aktivateDownload")) ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).activateDownload( model, video, host, producer);
		//
		if (action.equals("deaktivateDownload")) ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).deactivateDownload( model, video, host, producer);
		//
		if (action.equals("editMetadata")) ((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).editMetadata(model, video, metadata);
	}

	/*
	 * (non-Javadoc)
	 * @seeorg.springframework.web.portlet.mvc.AbstractFormController#
	 * renderFormSubmission(javax.portlet.RenderRequest,
	 * javax.portlet.RenderResponse, java.lang.Object,
	 * org.springframework.validation.BindException)
	 */
	/**
	 * Render form submission.
	 *
	 * @param request the request
	 * @param response the response
	 * @param command the command
	 * @param errors the errors
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@Override
	protected ModelAndView renderFormSubmission(RenderRequest request, RenderResponse response, Object command, BindException errors) throws Exception {
		ModelAndView mv = new ModelAndView();
		CoordinatorMetaDataModel model = (CoordinatorMetaDataModel) command;
		Map<?,?> modelMap = errors.getModel();
		
		// refresh model from command object
		doCoordinatorMetaDataListRender(request, model);

		if (model.getVideoList().size() == 0) model.setVideoList(null);

		if (model.getAction().equals("editMetadata")) mv.setView("coordinatorMetaDataDetails");
		else mv.setView("coordinatorMetaDataList");

		mv.addAllObjects(modelMap);
		mv.addObject("model", model);
		return mv;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.portlet.mvc.AbstractFormController#showForm(javax
	 * .portlet.RenderRequest, javax.portlet.RenderResponse,
	 * org.springframework.validation.BindException)
	 */
	/**
	 * Show form.
	 *
	 * @param request the request
	 * @param response the response
	 * @param errors the errors
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@Override
	protected ModelAndView showForm(RenderRequest request, RenderResponse response, BindException errors) throws Exception {
		ModelAndView mv = new ModelAndView();
		CoordinatorMetaDataModel model = new CoordinatorMetaDataModel();
		Map<?,?> modelMap = errors.getModel();
		
		int videoId = 0;
		try {
			videoId = new Integer(request.getParameter("videoId"));
		} catch (NumberFormatException nfe) {
			//
		}

		if (videoId != 0) {
			doCoordinatorMetaDataDetailsRender(request, model);
			mv.setView("coordinatorMetaDataDetails");
		} else {
			model.setLectureseriesId(0);
			doCoordinatorMetaDataListRender(request, model);
			mv.setView("coordinatorMetaDataList");
		}

		model.setVideoList(model.getVideoList());
		
		try {
			int cid = new Integer(request.getParameter("lectureseriesId"));
			model.setLectureseriesId(cid);
		} catch (NumberFormatException nfe) {
			model.setLectureseriesId(0);
		}

		mv.addAllObjects(modelMap);
		mv.addObject("model", model);
		return mv;
	}

	/**
	 * Do coordinator meta data details render.
	 *
	 * @param request the request
	 * @param model the model
	 */
	private void doCoordinatorMetaDataDetailsRender(RenderRequest request, CoordinatorMetaDataModel model) {
		// here proceeds the main logic
		try {
			int videoId = new Integer(request.getParameter("videoId"));
			int currentSeite = new Integer(request.getParameter("currentSeite"));
			
			List<Video> videoList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(videoId);
			Video video = videoList.iterator().next();
			int prodId = video.getObjectProducer().getId();
			model.setProducerId(prodId);
			
			model.setVideoList(((VideoDao)getDaoBeanFactory().getBean("videoDao")).getByProducerId(prodId));
			model.setHostList(((HostDao)getDaoBeanFactory().getBean("hostDao")).getAll());
			List<Producer> producerList = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByUserId(prodId);
			model.setProducerList(producerList);

			int metadataId = video.getMetadataId();
			List<Metadata> metaDataList = ((MetadataDao)getDaoBeanFactory().getBean("metadataDao")).getById(metadataId);
			Metadata metadata = metaDataList.iterator().next();

			// l2go metadata properties
			model.setVideoId(videoId);
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
			model.setProducerId(prodId);
			model.setTitle(video.getTitle());
			model.setLectureseriesId(video.getLectureseriesId());
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
			model.setCurrentSeite(currentSeite);
			model.setSuccess(false);
			
			//
			model.setVideo(video);
		} catch (Exception e) {
			model.setVideoId(-1);
			model.setSuccess(false);
		}
		try {
			model.setLectureseriesId(new Integer(request.getParameter("lectureseriesId")));
		} catch (NumberFormatException nfe) {}
		
		model.setPortletRequest(request);
	}

	/**
	 * Do coordinator meta data list render.
	 *
	 * @param request the request
	 * @param model the model
	 * @throws Exception the exception
	 */
	private void doCoordinatorMetaDataListRender(RenderRequest request, CoordinatorMetaDataModel model) throws Exception {

		int lectureseriesId = 0;

		try {
			lectureseriesId = new Integer(request.getParameter("lectureseriesId"));
		} catch (NumberFormatException npe) {
			if (model.getLectureseriesId() != 0) lectureseriesId = model.getLectureseriesId();
			else lectureseriesId = 0;
		}

		// current user
		String remoteUserId = request.getRemoteUser();
		int remoteUId = new Integer(remoteUserId);

		// producer has been chosen
		String producerId = request.getParameter("producerId");

		int prodId = 0;
		try {
			prodId = new Integer(producerId);
		} catch (NumberFormatException nfe) {
			if (model.getProducerId() != 0) {
				// new produzten chosen
				if (prodId != model.getProducerId()) {
					prodId = model.getProducerId();
				}
			} else {
				prodId = 0;
			}
		}

		List<Coordinator> coordinatorList = ((CoordinatorDao)getDaoBeanFactory().getBean("coordinatorDao")).getById(remoteUId);
		Coordinator koo = coordinatorList.iterator().next();
		List<ProducerLRInfo> producerLRList = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByFacilityIdSortedByLastName(koo.getFacilityId());

		int o = 11;
		int f = 0;
		int s = 1;

		try {
			s = new Integer(request.getParameter("currentSeite"));
			f = (s - 1) * o;
		} catch (Exception e) {
			if (model.getCurrentSeite() != 0) {
				s = model.getCurrentSeite();
				f = (s - 1) * o;
			} else {
				f = 0;
			}
		}


		int anzahlAllerVideos = 0;
		
		
		if (prodId != 0) {
			// get all videos for producerId
			if (lectureseriesId == 0) anzahlAllerVideos = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).countByProducerId(prodId);
			else anzahlAllerVideos = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).countByProducerIdAndLectureseriesId(prodId, lectureseriesId);
		} 
		
		// gets lectureseries for the chosen producerId
		List<Lectureseries> lectureseriesList = ((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getLectureseriesForProducer(prodId);
		List<Video> videoListByProducerIdAndCounter = new ArrayList<Video>();
		// make list from videos
		if (prodId != 0) {// producerId chosen
			if (lectureseriesId == 0) {
				// get all videos for producer and counter, if lectureseriesId not
				// chosen
				videoListByProducerIdAndCounter = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getByProducerIdAndCounter(prodId, f, o);
				if (videoListByProducerIdAndCounter.size() == 0) model.setVideoSeiten(0);
			} else {
				// get videos for the chosen lectureseriesId and current counter
				videoListByProducerIdAndCounter = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getByProducerIdAndCounterAndlectureseriesId(prodId, f, o, lectureseriesId);
				if (videoListByProducerIdAndCounter.size() == 0) model.setVideoSeiten(0);
			}
		} 


		try {
			Comparator<Video> comparator = new VideoGenerationDateComparator();
			java.util.Collections.sort(videoListByProducerIdAndCounter, comparator);
		} catch (NullPointerException npe) {
			//
		}

		ListIterator<Video> it = videoListByProducerIdAndCounter.listIterator();
		int counter = 0;
		while (it.hasNext()) {
			counter++;
			if (counter == 4) counter = 1;
			it.next().setCounter(counter);
		}

		int numberOfPages = anzahlAllerVideos / o;
		
		List<Integer> listAnzahlVideoSeiten = new ArrayList<Integer>();
		for (int i = 0; i <= numberOfPages; i++) {
			listAnzahlVideoSeiten.add(i + 1);
		}

		if (listAnzahlVideoSeiten.size() == 0) model.setVideoSeiten(0);
		else {
			model.setNumberOfPages(listAnzahlVideoSeiten);
			model.setVideoSeiten(listAnzahlVideoSeiten.size());
		}

		// set all ObjectDaos to the ObjectModel
		model.setVideoList(videoListByProducerIdAndCounter);
		model.setCurrentSeite(s);

		try {
			Video video = videoListByProducerIdAndCounter.iterator().next();
			model.setVideoId(video.getId());
			model.setOpenaccess(video.isOpenaccess());
			model.setDownloadAllawed(video.isDownloadAllawed());
		} catch (Exception e) {
			model.setVideoList(null);
		}

		if (!videoListByProducerIdAndCounter.isEmpty()) model.setHasMetadata(true);
		model.setHostList(((HostDao)getDaoBeanFactory().getBean("hostDao")).getAll());
		model.setProducerId(prodId);
		model.setProducerLRList(producerLRList);
		model.setPortletRequest(request);
		model.setLectureseriesList(lectureseriesList);
		model.setLectureseriesId(lectureseriesId);
	}

}
