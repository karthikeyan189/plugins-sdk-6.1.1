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
package de.uhh.l2g.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;

import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.liferay.portal.service.UserLocalServiceUtil;

import de.uhh.l2g.beans.Coordinator;
import de.uhh.l2g.beans.Host;
import de.uhh.l2g.beans.License;
import de.uhh.l2g.beans.Mark;
import de.uhh.l2g.beans.Metadata;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.CoordinatorDao;
import de.uhh.l2g.dao.LicenseDao;
import de.uhh.l2g.dao.MetadataDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.dao.SegmentDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.GuestModel;
import de.uhh.l2g.model.ProducerMetaDataModel;

/**
 * The Class ProzessManager.
 */
public class ProzessManager {

	/** The htaccess. */
	private Htaccess htaccess;
	
	/** The rss manager. */
	private RSSManager rssManager;
	
	/**
	 * Gets the htaccess.
	 *
	 * @return the htaccess
	 */
	public Htaccess getHtaccess() {
		return htaccess;
	}

	/**
	 * Sets the htaccess.
	 *
	 * @param htaccess the new htaccess
	 */
	public void setHtaccess(Htaccess htaccess) {
		this.htaccess = htaccess;
	}

	/**
	 * Gets the rss manager.
	 *
	 * @return the rss manager
	 */
	public RSSManager getRssManager() {
		return rssManager;
	}

	/**
	 * Sets the rss manager.
	 *
	 * @param rssManager the new rss manager
	 */
	public void setRssManager(RSSManager rssManager) {
		this.rssManager = rssManager;
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
	 * @param beanFactory the new dao bean factory
	 */
	public void setDaoBeanFactory(XmlBeanFactory beanFactory) {
		this.daoBeanFactory = beanFactory;
	}
	
	/**
	 * Sets the user role id.
	 *
	 * @param userId the user id
	 * @param roleId the role id
	 * @param approved the approved
	 */
	public void setUserRoleId(int userId, int roleId, boolean approved) {
		try {
			if (approved) {
				if (!UserLocalServiceUtil.hasRoleUser(roleId, userId)) {
					UserLocalServiceUtil.addRoleUsers(roleId, new long[] { userId });
				}
			} else {
				UserLocalServiceUtil.deleteRoleUser(roleId, userId);
			}
		} catch (Exception e) {
			//
		}
	}
	
	/**
	 * Creates the coordinator.
	 *
	 * @param userId the user id
	 * @param facilityId the facility id
	 * @param coordinatorId the coordinator id
	 */
	public void createCoordinator(int userId, int facilityId, int coordinatorId){
		//prepare object
		List<Coordinator> coordinatorList = ((CoordinatorDao)getDaoBeanFactory().getBean("coordinatorDao")).getById(userId);
		//if coordinator don't exists in table coordinator, create a new one
		if(coordinatorList.size()==0){
			((CoordinatorDao)getDaoBeanFactory().getBean("coordinatorDao")).create(userId, facilityId);
			((CoordinatorDao)getDaoBeanFactory().getBean("coordinatorDao")).setApproved(userId, coordinatorId, true);
		}else{
			((CoordinatorDao)getDaoBeanFactory().getBean("coordinatorDao")).updateById(userId, facilityId);
		}		
	}
	
	/**
	 * Delete coordinator.
	 *
	 * @param userId the user id
	 * @param coordinatorRolleId the coordinator rolle id
	 */
	public void deleteCoordinator(int userId, int coordinatorRolleId){
		((CoordinatorDao)getDaoBeanFactory().getBean("coordinatorDao")).deleteById(userId);
		((CoordinatorDao)getDaoBeanFactory().getBean("coordinatorDao")).setApproved(userId, coordinatorRolleId, false);
	}
	
	/**
	 * Deactivate download.
	 *
	 * @param model the model
	 * @param video the video
	 * @param host the host
	 * @param producer the producer
	 */
	public void deactivateDownload(ProducerMetaDataModel model, Video video, Host host, Producer producer){
		((VideoDao)getDaoBeanFactory().getBean("videoDao")).deactivateDownload(video.getId());
		// set RSS
		try {
			RSS(video, "mp4", model);
			RSS(video, "mp3", model);
			RSS(video, "m4v", model);
			RSS(video, "m4a", model);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String url = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producer.getHomeDir() + "/";
		getHtaccess().makeHtaccess(url, ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getLockedByProducerId(producer.getId()));		
	}
	
	/**
	 * Activate download.
	 *
	 * @param model the model
	 * @param video the video
	 * @param host the host
	 * @param producer the producer
	 */
	public void activateDownload(ProducerMetaDataModel model, Video video, Host host, Producer producer){
		((VideoDao)getDaoBeanFactory().getBean("videoDao")).activateDownload(video.getId());
		// set RSS
		try {
			RSS(video, "mp4", model);
			RSS(video, "mp3", model);
			RSS(video, "m4v", model);
			RSS(video, "m4a", model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producer.getHomeDir() + "/";
		getHtaccess().makeHtaccess(url, ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getLockedByProducerId(producer.getId()));
	}

	/**
	 * Activate openaccess.
	 *
	 * @param model the model
	 * @param video the video
	 * @param host the host
	 * @param producer the producer
	 */
	public void activateOpenaccess(ProducerMetaDataModel model, Video video, Host host, Producer producer){
		// first rename the file from the filesystem first
		String path = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir();

		String videoPreffix = video.getPreffix();
		String videoSPreffix = video.getSPreffix();

		try {
			// then update the filesystem
			// here rename
			File fJpg = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + videoSPreffix + ".jpg");
			File fJpgm = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + videoSPreffix + "_m.jpg");
			File fJpgs = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + videoSPreffix + "_s.jpg");
			File fPdf = new File(path + "/" + videoSPreffix + ".pdf");
			File fMp3 = new File(path + "/" + videoSPreffix + ".mp3");
			File fM4v = new File(path + "/" + videoSPreffix + ".m4v");
			File fMp4 = new File(path + "/" + videoSPreffix + ".mp4");
			File fM4a = new File(path + "/" + videoSPreffix + ".m4a");
			File fTar = new File(path + "/" + videoSPreffix + ".tar");

			timeout();// wait for a while!

			// if mp4 or mp3 exists
			if (fMp4.isFile() || fMp3.isFile()) {
				fJpg.renameTo(new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + videoPreffix + ".jpg"));
				fJpgm.renameTo(new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + videoPreffix + "_m.jpg"));
				fJpgs.renameTo(new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + videoPreffix + "_s.jpg"));
				fPdf.renameTo(new File(path + "/" + videoPreffix + ".pdf"));
				fMp3.renameTo(new File(path + "/" + videoPreffix + ".mp3"));
				fM4v.renameTo(new File(path + "/" + videoPreffix + ".m4v"));
				fMp4.renameTo(new File(path + "/" + videoPreffix + ".mp4"));
				fM4a.renameTo(new File(path + "/" + videoPreffix + ".m4a"));
				fTar.renameTo(new File(path + "/" + videoPreffix + ".tar"));
				// then update the video in the database
				((VideoDao)getDaoBeanFactory().getBean("videoDao")).activateOpenaccess(video.getId());
			}
		} catch (Exception e) {}

		// set RSS
		try {
			RSS(video, "mp4", model);
			RSS(video, "mp3", model);
			RSS(video, "m4v", model);
			RSS(video, "m4a", model);
		} catch (Exception e) {}

		// rss reload
		RSS(video, "mp4", model);
		RSS(video, "mp3", model);
		RSS(video, "m4v", model);
		RSS(video, "m4a", model);

		String url = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producer.getHomeDir() + "/";
		getHtaccess().makeHtaccess(url, ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getLockedByProducerId(producer.getId()));
		
	}
	
	/**
	 * Deactivate openaccess.
	 *
	 * @param video the video
	 * @param model the model
	 * @param host the host
	 * @param producer the producer
	 */
	public void deactivateOpenaccess(Video video, ProducerMetaDataModel model, Host host, Producer producer) {
		// then update the filesystem
		String path = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir();
		String videoPreffix = video.getPreffix();

		// here rename
		File fJpg = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + videoPreffix + ".jpg");
		File fJpgm = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + videoPreffix + "_m.jpg");
		File fJpgs = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + videoPreffix + "_s.jpg");
		File fPdf = new File(path + "/" + videoPreffix + ".pdf");
		File fMp3 = new File(path + "/" + videoPreffix + ".mp3");
		File fM4v = new File(path + "/" + videoPreffix + ".m4v");
		File fMp4 = new File(path + "/" + videoPreffix + ".mp4");
		File fM4a = new File(path + "/" + videoPreffix + ".m4a");
		File fTar = new File(path + "/" + videoPreffix + ".tar");

		// wait for a wile!
		timeout();

		if (fMp4.isFile() || fMp3.isFile()) {
			// first update the video in the database and create secure name
			((VideoDao)getDaoBeanFactory().getBean("videoDao")).deactivateOpenaccess(video.getId());
			Video v = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(video.getId()).iterator().next();

			String vidSPreffix = v.getSPreffix();

			// then rename all system files
			fJpg.renameTo(new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + vidSPreffix + ".jpg"));
			fJpgm.renameTo(new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + vidSPreffix + "_m.jpg"));
			fJpgs.renameTo(new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + vidSPreffix + "_s.jpg"));
			fPdf.renameTo(new File(path + "/" + vidSPreffix + ".pdf"));
			fMp3.renameTo(new File(path + "/" + vidSPreffix + ".mp3"));
			fM4v.renameTo(new File(path + "/" + vidSPreffix + ".m4v"));
			fMp4.renameTo(new File(path + "/" + vidSPreffix + ".mp4"));
			fM4a.renameTo(new File(path + "/" + vidSPreffix + ".m4a"));
			fTar.renameTo(new File(path + "/" + vidSPreffix + ".tar"));
		}

		// delete all symbolic links
		File symLinkMp4 = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + video.getPreffix() + ".mp4");
		File symLinkM4v = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + video.getPreffix() + ".m4v");
		File symLinkM4a = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + video.getPreffix() + ".m4a");
		File symLinkMp3 = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + video.getPreffix() + ".mp3");

		symLinkMp4.delete();
		symLinkM4v.delete();
		symLinkM4a.delete();
		symLinkMp3.delete();
	
		// set RSS
		try {
			RSS(video, "mp4", model);
			RSS(video, "mp3", model);
			RSS(video, "m4v", model);
			RSS(video, "m4a", model);
		} catch (Exception e) {}

		//delete video from videohitlist
		((VideoDao)getDaoBeanFactory().getBean("videoDao")).deleteVideoFromVideoHitListById(video.getId());
		
		String url = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producer.getHomeDir() + "/";
		getHtaccess().makeHtaccess(url, ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getLockedByProducerId(producer.getId()));
	}
	
	/**
	 * Delete thumbnails.
	 *
	 * @param video the video
	 */
	public void deleteThumbnails(Video video){
		try{
			String videoPreffix = "";
			if (video.isOpenaccess()) videoPreffix = video.getPreffix();
			else videoPreffix = video.getSPreffix();
			
			File jpgFile = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + videoPreffix + ".jpg");
			File jpgmFile = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + videoPreffix + "_m.jpg");
			File jpgsFile = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + videoPreffix + "_s.jpg");
			
			jpgFile.delete();	
			jpgmFile.delete();	
			jpgsFile.delete();	
		}catch(NullPointerException npe){}
	}
	
	/**
	 * Delete.
	 *
	 * @param model the model
	 * @param video the video
	 * @param host the host
	 * @param producer the producer
	 * @param request the request
	 */
	public void delete(ProducerMetaDataModel model, Video video, Host host, Producer producer, ActionRequest request){
		model.setAction("loeschen");
		int metadataId = video.getMetadataId();

		// delete all segment images from repository location first!
		List<Mark> segmentList = ((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getSegmentsByVideoId(video.getId());
		((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).deleteThumbhailsFromSegments(segmentList);

		// delete all segment data from table
		((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).deleteByVideoId(video.getId());

		// delete license
		((LicenseDao)getDaoBeanFactory().getBean("licenseDao")).deleteByIdVideoId(video.getId());

		// delete all data contents
		((MetadataDao)getDaoBeanFactory().getBean("metadataDao")).deleteById(metadataId);
		((VideoDao)getDaoBeanFactory().getBean("videoDao")).deleteById(video.getId());

		//delete video from videohitlist
		((VideoDao)getDaoBeanFactory().getBean("videoDao")).deleteVideoFromVideoHitListById(video.getId());
		
		String videoPreffix = "";
		if (video.isOpenaccess()) videoPreffix = video.getPreffix();
		else videoPreffix = video.getSPreffix();
		
		try {
			// delete all video contents
			if (video.getFilename() != null) {

				File mp3File = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producer.getHomeDir() + "/" + videoPreffix + ".mp3");
				File m4aFile = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producer.getHomeDir() + "/" + videoPreffix + ".m4a");
				File mp4vFile = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producer.getHomeDir() + "/" + videoPreffix + ".m4v");
				File pdfFile = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producer.getHomeDir() + "/" + videoPreffix + ".pdf");
				File mp4File = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producer.getHomeDir() + "/" + videoPreffix + ".mp4");
				File tarFile = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producer.getHomeDir() + "/" + videoPreffix + ".tar");

				mp3File.delete();
				m4aFile.delete();
				mp4vFile.delete();
				pdfFile.delete();
				mp4File.delete();
				tarFile.delete();
				deleteThumbnails(video);
			}

			// delete all symbolic links
			File symLinkMp4 = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + videoPreffix + ".mp4");
			File symLinkM4v = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + videoPreffix + ".m4v");
			File symLinkM4a = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + videoPreffix + ".m4a");
			File symLinkMp3 = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + videoPreffix + ".mp3");
			File symLinkJpg = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + videoPreffix + ".jpg");

			symLinkMp4.delete();
			symLinkM4v.delete();
			symLinkM4a.delete();
			symLinkMp3.delete();
			symLinkJpg.delete();
			
		} catch (NullPointerException npe) {
		}
		// set RSS
		try {
			RSS(video, "mp4", model);
			RSS(video, "mp3", model);
			RSS(video, "m4v", model);
			RSS(video, "m4a", model);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		//update uploads for producer
		((ProducerDao)getDaoBeanFactory().getBean("producerDao")).updateNumberOfProductionsByUserId(producer.getId());
		
	}
	
	/**
	 * Rss.
	 *
	 * @param video the video
	 * @param type the type
	 * @param model the model
	 */
	public void RSS(Video video, String type, ProducerMetaDataModel model) {
		// RSS generate for this lecture
		try {
			List<Video> videoList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getOpenAccessVideosByLectureseriesNumber(video.getLectureseriesId(), "DESC");
			String feedName = "";

			if (type.equals("mp4")) feedName = "" + video.getLectureseriesId() + ".mp4.xml";
			if (type.equals("mp3")) feedName = "" + video.getLectureseriesId() + ".mp3.xml";
			if (type.equals("m4a")) feedName = "" + video.getLectureseriesId() + ".m4a.xml";
			if (type.equals("m4v")) feedName = "" + video.getLectureseriesId() + ".m4v.xml";

			getRssManager().setRssFilename(feedName);
			getRssManager().setTitle(video.getObjectLectureseries().getName());

			if (type.equals("mp4")) getRssManager().createRssFile(videoList, "mp4");
			if (type.equals("mp3")) getRssManager().createRssFile(videoList, "mp3");
			if (type.equals("m4a")) getRssManager().createRssFile(videoList, "m4a");
			if (type.equals("m4v")) getRssManager().createRssFile(videoList, "m4v");
		} catch (Exception e) {
			try {
				if (type.equals("mp4")) getRssManager().createRssFile(null, "mp4");
				if (type.equals("mp3")) getRssManager().createRssFile(null, "mp3");
				if (type.equals("m4a")) getRssManager().createRssFile(null, "m4a");
				if (type.equals("m4v")) getRssManager().createRssFile(null, "m4v");
			} catch (IOException ie) {}
		}
		// RSS end
	}

	/**
	 * Timeout.
	 */
	private void timeout() {
		double a = 0;
		for (int i = 0; i <= 10000000; i++) {
			a++;
			a = Math.exp(a);
		}
	}

	/**
	 * Edits the metadata.
	 *
	 * @param model the model
	 * @param video the video
	 * @param metadata the metadata
	 */
	public void editMetadata(ProducerMetaDataModel model, Video video, Metadata metadata){
		((MetadataDao)getDaoBeanFactory().getBean("metadataDao")).updateById(metadata.getFilenameID(), metadata.getFormat(), metadata.getType(), metadata.getLanguage(), model.getTitle(), metadata.getSubject(), metadata.getCoverage(), HtmlManager.prepareHtmlForWyswygEditor(model.getDescription()), model.getCreator(), model.getPublisher(), model.getContributor(), model.getRightsHolder(), metadata.getRights(), metadata.getProvenance(), metadata.getSource(), metadata.getRelation(), metadata.getAudience(), metadata.getInstructionalMethod(), metadata.getDate(), metadata.getId());
		((VideoDao)getDaoBeanFactory().getBean("videoDao")).updateById(model.getTitle(), model.getTags(), video.getLectureseriesId(), video.getEigentuemerId(), video.getProducerId(), video.getContainerFormat(), video.getFilename(), video.getResolution(), video.getDuration(), video.getHostId(), video.getTextId(), video.getFileSize(), video.getGenerationDate(), video.isOpenaccess(), video.isDownloadAllawed(), video.getMetadataId(), video.getSecureFilename(), video.getHits(), video.isPermittedToSegment(), video.getFacilityId(), model.getCitation2go(), video.getId());

		// update license
		License l = ((LicenseDao)getDaoBeanFactory().getBean("licenseDao")).getByVideoId(video.getId()).iterator().next();
		boolean byncnd = false;
		boolean byncsa = false;
		boolean uhhl2go = false;

		try {
			if (model.getLicense().equals("ccbyncnd")) byncnd = true;
		} catch (NullPointerException ne) {
			byncnd = false;
		}

		try {
			if (model.getLicense().equals("ccbyncsa")) byncsa = true;
		} catch (NullPointerException ne) {
			byncsa = false;
		}

		((LicenseDao)getDaoBeanFactory().getBean("licenseDao")).updateById(video.getId(), false, false, byncnd, byncsa, false, false, uhhl2go, l.getId());
		
		// update license
		if (byncnd || byncsa || uhhl2go) {
			if (byncnd) model.setLicense("ccbyncnd");
			if (byncsa) model.setLicense("ccbyncsa");
		} else {
			model.setLicense("");
		}
		//update description
		Metadata m = ((MetadataDao)getDaoBeanFactory().getBean("metadataDao")).getById(metadata.getId()).iterator().next();
		model.setDescription(m.getDescription());
	}
	

	/**
	 * Prepare links for download.
	 *
	 * @param model the model
	 */
	private void prepareLinksForDownload(GuestModel model) {
		// video downloads
		String mp3Download = "";
		String pdfDownload = "";
		String m4aDownload = "";
		String m4vDownload = "";
		String mp4Download = "";
		String videoDownload = "";
		String urlPreffix = "";
		
		if (!model.getVideo().isOpenaccess()) urlPreffix = model.getVideo().getSPreffix();
		else urlPreffix = model.getVideo().getPreffix();
		
		//prepare progressive download for devices
		//web-phad for file
		String phadOpen = L2goPropsUtil.get("lecture2go.downloadserver.web.root") + "/" + "abo";
		String phadClosed = L2goPropsUtil.get("lecture2go.downloadserver.web.root") + "/" + model.getHost().getName() + "/" + model.getProducer().getHomeDir();
		//filesystem phad for file
		//check for audio-upload
		if (model.getVideo().getUploadType().equals("audio")){
			if (model.getVideo().isOpenaccess())videoDownload = phadOpen + "/" + urlPreffix + ".mp3";
			else videoDownload = phadClosed + "/" + urlPreffix + ".mp3";
		}else{//video-upload
			// if mobile
			if (model.getUserAgent().contains("iPod") || model.getUserAgent().contains("iPhone") || model.getUserAgent().contains("mobile") || model.getUserAgent().contains("Android") || model.getUserAgent().contains("Skyfire")) {
				File f4vFile = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + model.getHost().getName() + "/" + model.getProducer().getHomeDir() + "/" + urlPreffix + ".m4v");
				if (model.getVideo().isOpenaccess()){
					// if m4v exists for the video
					if (f4vFile.isFile()) videoDownload = phadOpen + "/" + urlPreffix + ".m4v";
					else videoDownload = phadOpen + "/" + urlPreffix + ".mp4";
				}else{
					// if m4v exists for the video
					if (f4vFile.isFile()) videoDownload = phadClosed + "/" + urlPreffix + ".m4v";
					else videoDownload = phadClosed + "/" + urlPreffix + ".mp4";			
				}
			}else{// not mobile
				if (model.getVideo().isOpenaccess())videoDownload = phadOpen + "/" + urlPreffix + ".mp4";
				else videoDownload = phadClosed + "/" + urlPreffix + ".mp4";
			}				
		}	
		
		mp4Download = phadOpen + "/" + urlPreffix + ".mp4";
		mp3Download = phadOpen + "/" + urlPreffix + ".mp3";
		pdfDownload = phadOpen + "/" + urlPreffix + ".pdf";
		m4aDownload = phadOpen + "/" + urlPreffix + ".m4a";
		m4vDownload = phadOpen + "/" + urlPreffix + ".m4v";

		model.setVideoDownload(videoDownload);																									
		model.setMp4Download(mp4Download);
		model.setMp3Download(mp3Download);
		model.setPdfDownload(pdfDownload);
		model.setM4aDownload(m4aDownload);
		model.setM4vDownload(m4vDownload);
	}

	/**
	 * Insert additional downloads.
	 *
	 * @param model the model
	 */
	public void insertAdditionalDownloads(GuestModel model) {
		prepareLinksForDownload(model);
		if(model.getVideo().isOpenaccess()){
			try {
				if (model.getVideo().isDownloadAllawed()) model.setDownloadAllawed(true);
				else model.setDownloadAllawed(false);

				String mp4File = model.getVideo().getFilename().split("\\_")[0] + "_" + model.getVideo().getFilename().split("\\_")[1] + "_" + model.getVideo().getFilename().split("\\_")[2] + "_" + model.getVideo().getFilename().split("\\_")[3].split("\\.")[0] + ".mp4";
				File fMp4 = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + model.getHost().getServerRoot() + "/" + model.getProducer().getHomeDir() + "/" + mp4File);

				String mp3File = model.getVideo().getFilename().split("\\_")[0] + "_" + model.getVideo().getFilename().split("\\_")[1] + "_" + model.getVideo().getFilename().split("\\_")[2] + "_" + model.getVideo().getFilename().split("\\_")[3].split("\\.")[0] + ".mp3";
				File fMp3 = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + model.getHost().getServerRoot() + "/" + model.getProducer().getHomeDir() + "/" + mp3File);

				String pdfFile = model.getVideo().getFilename().split("\\_")[0] + "_" + model.getVideo().getFilename().split("\\_")[1] + "_" + model.getVideo().getFilename().split("\\_")[2] + "_" + model.getVideo().getFilename().split("\\_")[3].split("\\.")[0] + ".pdf";
				File fPdf = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + model.getHost().getServerRoot() + "/" + model.getProducer().getHomeDir() + "/" + pdfFile);

				String m4vFile = model.getVideo().getFilename().split("\\_")[0] + "_" + model.getVideo().getFilename().split("\\_")[1] + "_" + model.getVideo().getFilename().split("\\_")[2] + "_" + model.getVideo().getFilename().split("\\_")[3].split("\\.")[0] + ".m4v";
				File fM4v = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + model.getHost().getServerRoot() + "/" + model.getProducer().getHomeDir() + "/" + m4vFile);

				String m4aFile = model.getVideo().getFilename().split("\\_")[0] + "_" + model.getVideo().getFilename().split("\\_")[1] + "_" + model.getVideo().getFilename().split("\\_")[2] + "_" + model.getVideo().getFilename().split("\\_")[3].split("\\.")[0] + ".m4a";
				File fM4a = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + model.getHost().getServerRoot() + "/" + model.getProducer().getHomeDir() + "/" + m4aFile);

				if (fMp4.isFile()) model.setMp4File(mp4File);
				else model.setMp4File(null);

				if (fMp3.isFile()) model.setMp3File(mp3File);
				else model.setMp3File(null);

				if (fPdf.isFile()) model.setPdfFile(pdfFile);
				else model.setPdfFile(null);

				if (fM4v.isFile()) model.setM4vFile(m4vFile);
				else model.setM4vFile(null);

				if (fM4a.isFile()) model.setM4aFile(m4aFile);
				else model.setM4aFile(null);
			} catch (ArrayIndexOutOfBoundsException aobe) {}			
		}else{
			try {
				if (model.getVideo().isDownloadAllawed()) model.setDownloadAllawed(true);
				else model.setDownloadAllawed(false);

				String mp4File = model.getVideo().getSecureFilename().split("\\.")[0] + ".mp4";
				File fMp4 = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" +model.getHost().getServerRoot() + "/" + model.getProducer().getHomeDir() + "/" + mp4File);

				String mp3File = model.getVideo().getSecureFilename().split("\\.")[0] + ".mp3";
				File fMp3 = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" +model.getHost().getServerRoot() + "/" + model.getProducer().getHomeDir() + "/" + mp3File);

				String pdfFile = model.getVideo().getSecureFilename().split("\\.")[0] + ".pdf";
				File fPdf = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" +model.getHost().getServerRoot() + "/" + model.getProducer().getHomeDir() + "/" + pdfFile);

				String m4vFile = model.getVideo().getSecureFilename().split("\\.")[0] + ".m4v";
				File fM4v = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" +model.getHost().getServerRoot() + "/" + model.getProducer().getHomeDir() + "/" + m4vFile);

				String m4aFile = model.getVideo().getSecureFilename().split("\\.")[0] + ".m4a";
				File fM4a = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" +model.getHost().getServerRoot() + "/" + model.getProducer().getHomeDir() + "/" + m4aFile);

				if (fMp4.isFile()) model.setMp4File(mp4File);
				else model.setMp4File(null);

				if (fMp3.isFile()) model.setMp3File(mp3File);
				else model.setMp3File(null);

				if (fPdf.isFile()) model.setPdfFile(pdfFile);
				else model.setPdfFile(null);

				if (fM4v.isFile()) model.setM4vFile(m4vFile);
				else model.setM4vFile(null);

				if (fM4a.isFile()) model.setM4aFile(m4aFile);
				else model.setM4aFile(null);
			} catch (Exception e) {}			
		}
	}
	
	/**
	 * Adds the new media directory for producer.
	 *
	 * @param host the host
	 * @param producer the producer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void addNewMediaDirectoryForProducer(Host host, Producer producer) throws IOException{
		File folder = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/");
		if (!folder.exists()) {
			if (folder.mkdir()) {
				Runtime runtime = Runtime.getRuntime();
				String[] cmdArray = {L2goPropsUtil.get("lecture2go.shell.bin"), "-c", "chown nobody " + folder.getAbsolutePath() };
				runtime.exec(cmdArray);
				String[] cmdArray1 = { L2goPropsUtil.get("lecture2go.shell.bin"), "-c", "chown nobody:nobody " + folder.getAbsolutePath() };
				runtime.exec(cmdArray1);
				String[] cmdArray2 = { L2goPropsUtil.get("lecture2go.shell.bin"), "-c", "chmod 701 " + folder.getAbsolutePath() };
				runtime.exec(cmdArray2);

				File prodFolder = new File(L2goPropsUtil.get("lecture2go.httpstreaming.video.repository") + "/" + producer.getFacilityId() + "l2g" + producer.getHomeDir());
				if (!prodFolder.exists()) {
					String cmd = "ln -s " + folder.getAbsolutePath() + " " + prodFolder.getAbsolutePath();
					runtime.exec(cmd);
				}
			}
		}	
	}
}
