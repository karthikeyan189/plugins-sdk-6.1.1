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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.fileupload.portlet.PortletFileUpload;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.mvc.SimpleFormController;

import de.uhh.l2g.beans.Host;
import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.beans.Mark;
import de.uhh.l2g.beans.Metadata;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.Upload;
import de.uhh.l2g.beans.Video;
import de.uhh.l2g.dao.HostDao;
import de.uhh.l2g.dao.LectureseriesDao;
import de.uhh.l2g.dao.MetadataDao;
import de.uhh.l2g.dao.ProducerDao;
import de.uhh.l2g.dao.SegmentDao;
import de.uhh.l2g.dao.UploadDao;
import de.uhh.l2g.dao.VideoDao;
import de.uhh.l2g.model.ProducerVideoDataInputEditModel;
import de.uhh.l2g.upload.MyCommonsPortletMultipartResolver;
import de.uhh.l2g.util.FFmpegManager;
import de.uhh.l2g.util.Htaccess;
import de.uhh.l2g.util.L2goPropsUtil;
import de.uhh.l2g.util.ProzessManager;
import de.uhh.l2g.util.Security;
import de.uhh.l2g.util.StaxToXPathProperties;
import de.uhh.l2g.util.TarExtraktor;

/**
 * The Class ProducerVideoDataInputEditController.
 */
public class ProducerVideoDataInputEditController extends SimpleFormController {

	/** The my commons portlet multipart resolver. */
	private MyCommonsPortletMultipartResolver myCommonsPortletMultipartResolver;
	
	/**
	 * Gets the my commons portlet multipart resolver.
	 *
	 * @return the my commons portlet multipart resolver
	 */
	public MyCommonsPortletMultipartResolver getMyCommonsPortletMultipartResolver() {
		return myCommonsPortletMultipartResolver;
	}

	/**
	 * Sets the my commons portlet multipart resolver.
	 *
	 * @param myCommonsPortletMultipartResolver the my commons portlet multipart resolver
	 */
	public void setMyCommonsPortletMultipartResolver(MyCommonsPortletMultipartResolver myCommonsPortletMultipartResolver) {
		this.myCommonsPortletMultipartResolver = myCommonsPortletMultipartResolver;
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
	
	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.SimpleFormController#onSubmitAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void onSubmitAction(ActionRequest request, ActionResponse response, Object command, BindException errors) {
		ProducerVideoDataInputEditModel model = (ProducerVideoDataInputEditModel) command;

		// upload -- enctype="multipart/form-data"
		
		if (PortletFileUpload.isMultipartContent(request)) doUpload(request, response, model);
		// delete requested files
		String action = request.getParameter("action");
		Integer vidId = new Integer(request.getParameter("videoId"));
		// update video-object for model

		// here get video object
		List<Video> videoList = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(vidId);
		Video video = videoList.iterator().next();
		// -- video object end

		try {
			// delete video request
			if (action.equals("deleteVideo")) {
				deleteMp3(model);
				model.setMp3File(null);
				deleteM4v(model);
				model.setM4vFile(null);
				deletePdf(model);
				model.setPdfFile(null);
				deleteM4a(model);
				model.setM4aFile(null);
				deleteTar(model);
				// has to be at the and
				deleteVideo(model);
			}
			// delete mp3 request
			if (action.equals("deleteMp3")) {
				deleteMp3(model);
				model.setMp3File(null);
			}
			// delete m4v request
			if (action.equals("deleteM4v")) {
				deleteM4v(model);
				model.setM4vFile(null);
			}
			// delete m4a request
			if (action.equals("deleteM4a")) {
				deleteM4a(model);
				model.setM4aFile(null);
			}
			// delete pdf request
			if (action.equals("deletePdf")) {
				deletePdf(model);
				model.setPdfFile(null);
			}
			// upload request
			if (action.equals("upload")) {
				// check for mp3 or mp4 file
				// here update mp3-file in model
				try{ if (video.getMp3File().isFile()) model.setMp3File(video.getMp3File().toString()); }catch(NullPointerException npe){}

				// update model
				model.setVideo(video);
				model.setURL(null);
			}

		} catch (Exception e) {
		}
	}

	/**
	 * Update ffmpeg metadata.
	 *
	 * @param video the video
	 * @param model the model
	 * @throws IOException the IO exception
	 */
	private void updateFfmpegMetadata(Video video, ProducerVideoDataInputEditModel model) throws IOException {
		Host host = model.getHost();
		Producer producer = model.getProducer();
		// FFMPEG
		if (video.getFileSize().longValue() == 0) {
			try {
				String videopfad = "";
				if (video.isOpenaccess()) videopfad = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producer.getHomeDir() + "/" + video.getFilename();
				else videopfad = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producer.getHomeDir() + "/" + video.getSecureFilename();

				FFmpegManager ffmpegp = new FFmpegManager(videopfad);
				video.setDuration(ffmpegp.getVideoDuration());
				video.setResolution(ffmpegp.getVideoResolution());
				video.setBitrate(ffmpegp.getVideoBitRate());
				video.setFileSize(new File(videopfad).length());
				((VideoDao)getDaoBeanFactory().getBean("videoDao")).updateById(
						video.getTitle(), 
						video.getTags(), 
						video.getLectureseriesId(), 
						video.getEigentuemerId(), 
						video.getProducerId(), 
						video.getContainerFormat(), 
						video.getFilename(), 
						video.getResolution(), 
						video.getDuration(), 
						video.getHostId(), 
						video.getTextId(), 
						video.getFileSize(), 
						video.getGenerationDate(), 
						video.isOpenaccess(), 
						video.isDownloadAllawed(), 
						video.getMetadataId(), 
						video.getSecureFilename(), 
						video.getHits(), 
						video.isPermittedToSegment(), 
						video.getFacilityId(), 
						video.getCitation2go(),
						video.getId()
						);
			} catch (NullPointerException npe) {
			}
		}
	}

	/**
	 * Gets the upload date.
	 *
	 * @return the upload date
	 */
	private final Date getUploadDate(){
		//actual time zone from geografical location
	    Calendar c = new GregorianCalendar(Locale.getDefault());
	    return c.getTime();
	}

	  
	/**
	 * Do upload.
	 *
	 * @param request the request
	 * @param response the response
	 * @param model the model
	 * @throws NullPointerException the null pointer exception
	 */
	private void doUpload(ActionRequest request, ActionResponse response, ProducerVideoDataInputEditModel model) throws NullPointerException{
		File destination = null;
		String fileDestPhad = "";

		// upload this file to the user home desitation
		try {
			Producer producer = model.getProducer();
			Video video = model.getVideo();
			Host host = model.getHost();

			// pick the multipart file content data
			MultipartFile file = model.getContactFile();
			// get the producers home directory
			String producerHomeDir = producer.getHomeDir();

			// Current Path for the current User
			String tmpath = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/";

			// get the original file name
			String originalFileName = file.getOriginalFilename();
			originalFileName = replaceBadCharacters(originalFileName);

			// upload ############ if mp4 upload or tar
			// the upload-file has to end on 'mp4' or 'mp3' or 'm4v' or 'm4a' or
			// pdf
			String[] p = originalFileName.split("\\.");
			int l = p.length; l--;
			String fileFormat = p[l].toLowerCase();

			/**rename start**/
			String[] parameter = originalFileName.split("\\_");
			// the parameter array length has to be 4, 
			// if not -> this is not l2go upload
			// so, have to rename

		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		    String newDate = format.format(getUploadDate()).toString();		
		    
			if (parameter.length != 4)originalFileName = model.getLectureseries().getNumber()+"_"+model.getMetadata().getCreator()+"_"+newDate+"."+fileFormat;
			originalFileName = replaceBadCharacters(originalFileName);
			//now reset the parameter array from above
			parameter = originalFileName.split("\\_");
			/**rename end**/
			
			if ((fileFormat.equalsIgnoreCase("mp4") || fileFormat.equalsIgnoreCase("tar") || fileFormat.equalsIgnoreCase("mp3")) && (video.getFilename() == null)) {
				// build the filename without file format
				String[] ofn = originalFileName.split("\\_");
				String filename = ofn[0] + "_" + ofn[1] + "_" + ofn[2] + "_" + ofn[3].split("\\.")[0];
				// extract time and date from the originalFileName
				// the upload-file has to end on 'mp4' or 'mp3' or 'm4v' or flv
				String parameter4 = parameter[3];
				// check parameter 3 - this is the date
				String l2gDate = parameter[2];
				// and parameter 4 - this is the time
				String l2gTime = parameter4.split("\\.")[0];
				String generationDate = l2gDate + "_" + l2gTime;
				// does filename exists in the DB?
				if (((VideoDao)getDaoBeanFactory().getBean("videoDao")).filenameExists(filename) && !fileFormat.equalsIgnoreCase("tar")) // check
				// only for mp4 and mp3
				filename = ofn[0] + "_" + ofn[1] + "-" + video.getId() + "_" + ofn[2] + "_" + ofn[3].split("\\.")[0];

				// update video-database, only if mp4 or tar or mp3 - this is the first upload
				if (fileFormat.equalsIgnoreCase("mp4") || fileFormat.equalsIgnoreCase("tar") || fileFormat.equalsIgnoreCase("mp3")) {
					// update the data row for this file-> set the new original
					// filename in the ´url´ field from the database table
					if (fileFormat.equalsIgnoreCase("mp3")) video.setFilename(filename + ".mp3");
					else video.setFilename(filename + ".mp4");
					// and save the extracted date and time in the database
					video.setGenerationDate(generationDate);

					String secfilename = Security.createSecureFileName();
					if (fileFormat.equalsIgnoreCase("mp4")) secfilename = secfilename + ".mp4";
					if (fileFormat.equalsIgnoreCase("mp3")) secfilename = secfilename + ".mp3";
					if (fileFormat.equalsIgnoreCase("tar")) secfilename = secfilename + ".mp4";

					((VideoDao)getDaoBeanFactory().getBean("videoDao")).updateById(
						video.getTitle(), 
						video.getTags(), 
						video.getLectureseriesId(),
						video.getEigentuemerId(),
						video.getProducerId(), 
						video.getContainerFormat(),
						video.getFilename(),
						video.getResolution(),
						video.getDuration(),
						producer.getHostId(),
						0,// textId
						video.getFileSize(),// filesize
						video.getGenerationDate(),
						false,// openAccess
						false,// downloadLink
						video.getMetadataId(),// metadataId
						secfilename, 
						video.getHits(), 
						video.isPermittedToSegment(),
						video.getFacilityId(),
						video.getCitation2go(),
						video.getId()
					);
					((VideoDao)getDaoBeanFactory().getBean("videoDao")).setUploadDate(getUploadDate(), video.getId());

					Video v = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(video.getId()).iterator().next();
					// is mp4 or tar and not openaccess
					if (!v.isOpenaccess()) {
						destination = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producerHomeDir + "/" + v.getSecureFilename());
						fileDestPhad = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producerHomeDir + "/" + v.getSPreffix();

						model.setOpenAccess(false);
						model.setSecureUrl(v.getSecureUrl());
					} else {
						destination = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producerHomeDir + "/" + v.getFilename());
						fileDestPhad = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producerHomeDir + "/" + v.getSPreffix();

						model.setOpenAccess(true);
						model.setSecureUrl(null);
					}
				}
			} else {
				// update model
				// if mp4, mp3, m4v, pdf file upload
				// get the video name -> 11.123_sturm_2009-09-11_10-00
				Integer vidId = new Integer(request.getParameter("videoId"));
				Video v = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(vidId).iterator().next();

				if (v.isOpenaccess()) fileDestPhad = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producerHomeDir + "/" + v.getPreffix();
				else fileDestPhad = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producerHomeDir + "/" + v.getSPreffix();

				// if mp4
				if (fileFormat.equalsIgnoreCase("mp4")) {
					((VideoDao)getDaoBeanFactory().getBean("videoDao")).setUploadDate(getUploadDate(), video.getId());
					destination = new File(fileDestPhad + ".mp4");
				}

				// if mp3
				if (fileFormat.equalsIgnoreCase("mp3")) {
					destination = new File(fileDestPhad + ".mp3");
					model.setMp3File(fileDestPhad + ".mp3");
				}
				// if m4v
				if (fileFormat.equalsIgnoreCase("m4v")) {
					destination = new File(fileDestPhad + ".m4v");
					model.setM4vFile(fileDestPhad + ".m4v");
				}
				// if pdf
				if (fileFormat.equalsIgnoreCase("pdf")) {
					destination = new File(fileDestPhad + ".pdf");
					model.setPdfFile(fileDestPhad + ".pdf");
				}
				// if m4a
				if (fileFormat.equalsIgnoreCase("m4a")) {
					destination = new File(fileDestPhad + ".m4a");
					model.setM4aFile(fileDestPhad + ".m4a");
				}
				// if xml
				if (fileFormat.equalsIgnoreCase("xml")) destination = new File(fileDestPhad + ".xml");
			}
			//before mp4-upload is completed, delete all thumbnails from file
			if(fileFormat.equalsIgnoreCase("mp4"))((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).deleteThumbnails(video);
			
			// UPLOAD this file to the user home destination
			file.transferTo(destination);
			
			// change upload status after update prozess
			Upload upload = ((UploadDao) getDaoBeanFactory().getBean("uploadDao")).getByUserIdDesc(producer.getId()).iterator().next();
			((UploadDao) getDaoBeanFactory().getBean("uploadDao")).updateById(upload.getId(), upload.getUserId(), upload.getContentLength(), upload.getTimestamp(), 1, video.getId());

			// get updated video from DB
			Video uploadetVideo = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(video.getId()).iterator().next();

			// update RSS
			try {
				((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).RSS(uploadetVideo,"mp4", model);
				((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).RSS(uploadetVideo,"mp3", model);
				((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).RSS(uploadetVideo,"m4v", model);
				((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).RSS(uploadetVideo,"m4a", model);
			} catch (Exception e) {}
			
			// TAR -- unpack the tar file in the home directory and rename all files
			if (fileFormat.equalsIgnoreCase("tar")) {
				// unpack all files and rename
				try {
					String videopre = uploadetVideo.getPreffix();
					String videoSpre = uploadetVideo.getSPreffix();
					String tarFileName = tmpath + uploadetVideo.getSecureFilename();
					File dest = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir());

					// extract all files from archive
					TarExtraktor.untarFiles(tarFileName, dest);

					// get extracted files
					File mp4 = new File(tmpath + videopre + ".mp4");
					File mp3 = new File(tmpath + videopre + ".mp3");
					File m4v = new File(tmpath + videopre + ".m4v");
					File m4a = new File(tmpath + videopre + ".m4a");

					// rename them
					File smp4 = new File(tmpath + videoSpre + ".mp4");
					File smp3 = new File(tmpath + videoSpre + ".mp3");
					File sm4v = new File(tmpath + videoSpre + ".m4v");
					File sm4a = new File(tmpath + videoSpre + ".m4a");

					// do
					mp4.renameTo(smp4);
					mp3.renameTo(smp3);
					m4v.renameTo(sm4v);
					m4a.renameTo(sm4a);

					// delete the tar file
					File tar = new File(tmpath + videoSpre + ".tar");
					tar.delete();

					// update model
					model.setM4aFile(tmpath + videoSpre + ".m4a");
					model.setMp3File(tmpath + videoSpre + ".mp3");
					model.setM4vFile(tmpath + videoSpre + ".m4v");
				} catch (IOException e) {}
			}

			// write the htaccess
			String url = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producer.getHomeDir() + "/";
			String remoteUserId = request.getRemoteUser();
			int remoteUId = new Integer(remoteUserId);

			((Htaccess) getUtilityBeanFactory().getBean("htaccess")).makeHtaccess(url, ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getLockedByProducerId(remoteUId));

			// and update metadata if not xml
			if (!fileFormat.equalsIgnoreCase("xml")) updateFfmpegMetadata(uploadetVideo, model);

			// and check for xml-file
			try {
				String[] arg = new String[1];
				// destination
				if (video.isOpenaccess()) arg[0] = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/" + video.getFilenamePreffix() + ".xml";
				else arg[0] = L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/" + video.getSecureFilename().split("\\.")[0] + ".xml";

				// if XML file exists, import it to database
				File xml = new File(arg[0]);
				if (xml.isFile()) {
					if (this.importXmlToDatabase(arg, video)) {
						xml.delete();
					}
				}
			} catch (NullPointerException npe) {
			}
			model.setContactFile(null);

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// set current site for browsing!
		int currentSeite = 1;
		try {currentSeite = new Integer(request.getParameter("videoSeite"));} catch (Exception e) {}

		model.setCurrentSeite(currentSeite);
	}

	/**
	 * Replace bad characters.
	 *
	 * @param originalFileName the original file name
	 * @return the string
	 */
	private String replaceBadCharacters(String originalFileName) {
		originalFileName = originalFileName.replace(" ", "");
		originalFileName = originalFileName.replace("ä", "ae");
		originalFileName = originalFileName.replace("ö", "oe");
		originalFileName = originalFileName.replace("ü", "ue");
		originalFileName = originalFileName.replace("ß", "ss");
		originalFileName = originalFileName.replace("Ä", "Ae");
		originalFileName = originalFileName.replace("Ö", "Oe");
		originalFileName = originalFileName.replace("Ü", "Ue");

		return originalFileName;
	}

	/**
	 * Import xml to database.
	 *
	 * @param arg the arg
	 * @param video the video
	 * @return true, if import xml to database
	 */
	private boolean importXmlToDatabase(String[] arg, Video video) {
		boolean ret = false;
		try {
			StaxToXPathProperties s2xp = new StaxToXPathProperties();
			String[][] arr = s2xp.xml2array(arg);
			// import array attributes to database

			for (int i = 0; i <= (arr.length - 1); i++) {
				String timeEnd = "";

				if (i < (arr.length - 1)) timeEnd = arr[i + 1][1];
				if (i == (arr.length - 1)) {
					if (video.getDuration().trim().equalsIgnoreCase("")) timeEnd = "00:00:00";
					else timeEnd = video.getDuration();
				}

				String beginn = arr[i][1].split("\\.")[0];
				String end = timeEnd.split("\\.")[0];
				((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).create(video.getId(), beginn, arr[i][0], null, end, 1, video.getObjectProducer().getId());
			}
			ret = true;
		} catch (XMLStreamException e) {
			ret = false;
		}
		return ret;
	}

	/**
	 * Delete video.
	 *
	 * @param model the model
	 */
	private void deleteVideo(ProducerVideoDataInputEditModel model) {
		Video video = model.getVideo();
		String preffix = "";
		if (video.isOpenaccess()) preffix = video.getPreffix();
		else preffix = video.getSPreffix();

		// delete this video from the filesystem
		File originalFile = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + model.getHost().getName() + "/" + model.getProducer().getHomeDir() + "/" + preffix + ".mp4");

		// delete image
		File jpg = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + preffix + ".jpg");
		jpg.delete();

		// delete segments
		List<Mark> segmentList = ((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).getSegmentsByVideoId(video.getId());
		((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).deleteThumbhailsFromSegments(segmentList);

		// delete all segment data from table
		((SegmentDao)getDaoBeanFactory().getBean("segmentDao")).deleteByVideoId(video.getId());

		originalFile.delete();

		// delete the symbolic link
		File symLink = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + video.getFilename());
		symLink.delete();

		// update the data row
		video.setFilename(null);
		video.setGenerationDate(null);
		video.setOpenaccess(false);
		video.setDownloadAllawed(false);

		((VideoDao)getDaoBeanFactory().getBean("videoDao")).updateById(
				video.getTitle(), 
				video.getTags(), 
				video.getLectureseriesId(),
				video.getEigentuemerId(), 
				video.getProducerId(), 
				video.getContainerFormat(),
				video.getFilename(), 
				null, 
				null, 
				video.getHostId(),
				0,// textId
				new Long("0"),
				null, 
				false,// openAccess
				false,// downloadLink
				video.getMetadataId(),// metadataId
				null, 
				video.getHits(), 
				video.isPermittedToSegment(), 
				video.getFacilityId(), 
				video.getCitation2go(),
				video.getId()
		);

		// update RSS
		try {
			((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).RSS(video,"mp4", model);
			((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).RSS(video,"mp3", model);
			((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).RSS(video,"m4v", model);
			((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).RSS(video,"m4a", model);
		} catch (Exception e) {}
	}

	/**
	 * Delete mp3.
	 *
	 * @param model the model
	 */
	private void deleteMp3(ProducerVideoDataInputEditModel model) {
		// get the producers home directory
		String producerHomeDir = model.getProducer().getHomeDir();
		Host host = model.getHost();
		Video video = model.getVideo();
		String preffix = "";
		if (video.isOpenaccess()) preffix = video.getPreffix();
		else preffix = video.getSPreffix();
		// delete this video from the filesystem
		File originalFile = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producerHomeDir + "/" + preffix + ".mp3");
		originalFile.delete();
		// delete symbolic link
		File symLinkMp3 = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + video.getPreffix() + ".mp3");
		symLinkMp3.delete();
		//update RSS
		((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).RSS(video,"mp3", model);
	}

	/**
	 * Delete m4a.
	 *
	 * @param model the model
	 */
	private void deleteM4a(ProducerVideoDataInputEditModel model) {
		// get the producers home directory
		String producerHomeDir = model.getProducer().getHomeDir();
		Host host = model.getHost();
		Video video = model.getVideo();
		String preffix = "";
		if (video.isOpenaccess()) preffix = video.getPreffix();
		else preffix = video.getSPreffix();
		// delete this video from the filesystem
		File originalFile = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producerHomeDir + "/" + preffix + ".m4a");
		originalFile.delete();
		// delete symbolic link
		File symLinkM4a = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + video.getPreffix() + ".m4a");
		symLinkM4a.delete();
		//update RSS
		((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).RSS(video,"m4a", model);
	}

	/**
	 * Delete m4v.
	 *
	 * @param model the model
	 */
	private void deleteM4v(ProducerVideoDataInputEditModel model) {
		// get the producers home directory
		String producerHomeDir = model.getProducer().getHomeDir();
		Host host = model.getHost();
		Video video = model.getVideo();
		String preffix = "";
		if (video.isOpenaccess()) preffix = video.getPreffix();
		else preffix = video.getSPreffix();
		// delete this video from the filesystem
		File originalFile = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producerHomeDir + "/" + preffix + ".m4v");
		originalFile.delete();
		// delete symbolic link
		File symLinkM4v = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + video.getPreffix() + ".m4v");
		symLinkM4v.delete();
		//update RSS
		((ProzessManager)getUtilityBeanFactory().getBean("prozessManager")).RSS(video,"m4v", model);
	}

	/**
	 * Delete pdf.
	 *
	 * @param model the model
	 */
	private void deletePdf(ProducerVideoDataInputEditModel model) {
		// get the producers home directory
		String producerHomeDir = model.getProducer().getHomeDir();
		Host host = model.getHost();
		Video video = model.getVideo();
		String preffix = "";
		if (video.isOpenaccess()) preffix = video.getPreffix();
		else preffix = video.getSPreffix();
		// delete this video from the filesystem
		File originalFile = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producerHomeDir + "/" + preffix + ".pdf");
		originalFile.delete();
	}

	/**
	 * Delete tar.
	 *
	 * @param model the model
	 */
	private void deleteTar(ProducerVideoDataInputEditModel model) {
		// get the producers home directory
		String producerHomeDir = model.getProducer().getHomeDir();
		Host host = model.getHost();
		Video video = model.getVideo();
		String preffix = "";
		if (video.isOpenaccess()) preffix = video.getPreffix();
		else preffix = video.getSPreffix();
		// delete this video from the filesystem
		File originalFile = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getName() + "/" + producerHomeDir + "/" + preffix + ".tar");
		originalFile.delete();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.portlet.mvc.AbstractFormController#formBackingObject
	 * (javax.portlet.PortletRequest)
	 */
	@Override
	protected Object formBackingObject(PortletRequest request) {
		ProducerVideoDataInputEditModel model = new ProducerVideoDataInputEditModel();
		String vid = request.getParameter("videoId");

		try {
			int vidId = new Integer(vid);
			Video video = ((VideoDao)getDaoBeanFactory().getBean("videoDao")).getById(vidId).iterator().next();
			Host host = ((HostDao)getDaoBeanFactory().getBean("hostDao")).getById(video.getHostId()).iterator().next();
			model.setHost(host);
			String remoteUserId = request.getRemoteUser();
			Integer ruid = new Integer(remoteUserId);
			// check user
			if (video.getProducerId() != ruid) {
				video.setId(-1);
			}
			Producer producer = ((ProducerDao)getDaoBeanFactory().getBean("producerDao")).getByUserId(ruid).iterator().next();
			model.setProducer(producer);
			
			Metadata metadata = ((MetadataDao)getDaoBeanFactory().getBean("metadataDao")).getById(video.getMetadataId()).iterator().next();
			model.setMetadata(metadata);
			
			Lectureseries lectureseries = ((LectureseriesDao)getDaoBeanFactory().getBean("lectureseriesDao")).getById(video.getLectureseriesId()).iterator().next();
			model.setLectureseries(lectureseries);
			
			if (video.getFilename() == null) {
				model.setM4vFile(null);
				model.setMp3File(null);
				model.setPdfFile(null);
				model.setM4aFile(null);
			} else {
				String preffix = "";
				
				if (video.isOpenaccess()) {
					preffix = video.getPreffix();
					model.setOpenaccess(true);
					model.setSecureUrl(null);
				} else {
					preffix = video.getSPreffix();
					model.setOpenaccess(false);
					model.setSecureUrl(video.getSecureUrl());
				}
				
				// check, if existent on the hard disc
				String mp3File = preffix + ".mp3";
				String pdfFile = preffix + ".pdf";
				String m4vFile = preffix + ".m4v";
				String m4aFile = preffix + ".m4a";

				File fMp3 = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/" + mp3File);
				File fPdf = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/" + pdfFile);
				File fM4v = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/" + m4vFile);
				File fM4a = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/" + m4aFile);

				if (fMp3.isFile()) model.setMp3File(mp3File);
				else model.setMp3File(null);
				if (fPdf.isFile()) model.setPdfFile(pdfFile);
				else model.setPdfFile(null);
				if (fM4v.isFile()) model.setM4vFile(m4vFile);
				else model.setM4vFile(null);
				if (fM4a.isFile()) model.setM4aFile(m4aFile);
				else model.setM4aFile(null);
				if (video.isDownloadAllawed()) model.setDownloadAllawed(true);
				else model.setDownloadAllawed(false);
			}

			model.setDomainURL(L2goPropsUtil.get("lecture2go.web.home"));
			model.setVideo(video);
			model.setLectureseriesId(video.getLectureseriesId());
			model.setCommsyEmbed(true);
			model.setVideoId(video.getId());

		} catch (Exception e) {
			model.setVideoId(-1);
		}
		// set current seite for browsing!
		int currentSeite = 1;
		try {
			currentSeite = new Integer(request.getParameter("videoSeite"));
		} catch (Exception e) {
			// nothing to do
		}
		model.setCurrentSeite(currentSeite);
		model.setURL(null);
		return model;
	}

}
