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
package de.uhh.l2g.beans;

import java.io.File;
import java.util.List;


/**
 * The Class Video.
 */
public class Video {
	
	/** The stream android url. */
	private String streamAndroidUrl;
	
	/**
	 * Gets the stream android url.
	 *
	 * @return the stream android url
	 */
	public String getStreamAndroidUrl() {
		return streamAndroidUrl;
	}

	/**
	 * Sets the stream android url.
	 *
	 * @param streamAndroidUrl the new stream android url
	 */
	public void setStreamAndroidUrl(String streamAndroidUrl) {
		this.streamAndroidUrl = streamAndroidUrl;
	}

	/** The stream ios url. */
	private String streamIosUrl;
	
	/**
	 * Gets the stream ios url.
	 *
	 * @return the stream ios url
	 */
	public String getStreamIosUrl() {
		return streamIosUrl;
	}

	/**
	 * Sets the stream ios url.
	 *
	 * @param streamIosUrl the new stream ios url
	 */
	public void setStreamIosUrl(String streamIosUrl) {
		this.streamIosUrl = streamIosUrl;
	}

	/** The citation2go. */
	private int citation2go;
	
	/**
	 * Gets the citation2go.
	 *
	 * @return the citation2go
	 */
	public int getCitation2go() {
		return citation2go;
	}

	/**
	 * Sets the citation2go.
	 *
	 * @param citation2go the new citation2go
	 */
	public void setCitation2go(int citation2go) {
		this.citation2go = citation2go;
	}

	/** The stream url. */
	private String streamUrl;
	
	/**
	 * Gets the stream url.
	 *
	 * @return the stream url
	 */
	public String getStreamUrl() {
		return streamUrl;
	}

	/**
	 * Sets the stream url.
	 *
	 * @param streamUrl the new stream url
	 */
	public void setStreamUrl(String streamUrl) {
		this.streamUrl = streamUrl;
	}

	/** The facility id. */
	private int facilityId;
	
	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the new facility id
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	/** The stream type. */
	private String streamType="wowza";

	/**
	 * Gets the stream type.
	 *
	 * @return the stream type
	 */
	public String getStreamType() {
		return streamType;
	}

	/**
	 * Sets the stream type.
	 *
	 * @param streamType the new stream type
	 */
	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}

	/** The secure filename. */
	private String secureFilename;
	
	/**
	 * Gets the secure filename.
	 *
	 * @return the secure filename
	 */
	public String getSecureFilename() {
		return secureFilename;
	}

	/**
	 * Sets the secure filename.
	 *
	 * @param secureFilename the new secure filename
	 */
	public void setSecureFilename(String secureFilename) {
		this.secureFilename = secureFilename;
	}

	/** The url. */
	private String url;
	
	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/** The upload date. */
	private String uploadDate;
	
	/**
	 * Gets the upload date.
	 *
	 * @return the upload date
	 */
	public String getUploadDate() {
		return uploadDate;
	}

	/**
	 * Sets the upload date.
	 *
	 * @param uploadDate the new upload date
	 */
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	/** The upload type. */
	private String uploadType = "";

	/**
	 * Gets the upload type.
	 *
	 * @return the upload type
	 */
	public String getUploadType() {
		return uploadType;
	}

	// video or audio
	/**
	 * Sets the upload type.
	 *
	 * @param uploadType the new upload type
	 */
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	/** The short name. */
	private String shortName = "";

	/**
	 * Gets the short name.
	 *
	 * @return the short name
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * Sets the short name.
	 *
	 * @param shortName the new short name
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/** The id. */
	private int id = 0;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** The title. */
	private String title = "new java.lang.String()";

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** The tags. */
	private String tags = "new java.lang.String()";

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * Sets the tags.
	 *
	 * @param tags the new tags
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/** The container format. */
	private String containerFormat = "new java.lang.String()";

	/**
	 * Gets the container format.
	 *
	 * @return the container format
	 */
	public String getContainerFormat() {
		return containerFormat;
	}

	/**
	 * Sets the container format.
	 *
	 * @param containerFormat the new container format
	 */
	public void setContainerFormat(String containerFormat) {
		this.containerFormat = containerFormat;
	}

	/** The filename. */
	private String filename = "new java.lang.String()";

	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename.
	 *
	 * @param filename the new filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/** The resolution. */
	private String resolution = "new java.lang.String()";

	/**
	 * Gets the resolution.
	 *
	 * @return the resolution
	 */
	public String getResolution() {
		return resolution;
	}

	/**
	 * Sets the resolution.
	 *
	 * @param resolution the new resolution
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	/** The duration. */
	private String duration = "";

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/** The duration min. */
	private String durationMin = "";

	/**
	 * Gets the duration min.
	 *
	 * @return the duration min
	 */
	public String getDurationMin() {
		return durationMin;
	}

	/** The codec. */
	private String codec = "new java.lang.String()";

	/**
	 * Gets the codec.
	 *
	 * @return the codec
	 */
	public String getCodec() {
		return codec;
	}

	/**
	 * Sets the codec.
	 *
	 * @param codec the new codec
	 */
	public void setCodec(String codec) {
		this.codec = codec;
	}

	/** The lectureseries id. */
	private int lectureseriesId = 0;

	/**
	 * Gets the lectureseries id.
	 *
	 * @return the lectureseries id
	 */
	public int getLectureseriesId() {
		return lectureseriesId;
	}

	/**
	 * Sets the lectureseries id.
	 *
	 * @param lectureseriesId the new lectureseries id
	 */
	public void setLectureseriesId(int lectureseriesId) {
		this.lectureseriesId = lectureseriesId;
	}

	/** The owner id. */
	private int ownerId = 0;

	/**
	 * Gets the eigentuemer id.
	 *
	 * @return the eigentuemer id
	 */
	public int getEigentuemerId() {
		return ownerId;
	}

	/**
	 * Sets the eigentuemer id.
	 *
	 * @param ownerId the new eigentuemer id
	 */
	public void setEigentuemerId(int ownerId) {
		this.ownerId = ownerId;
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
	 * @param producerId the new producer id
	 */
	public void setProducerId(int producerId) {
		this.producerId = producerId;
	}

	/** The streamer. */
	private String streamer = "";

	/**
	 * Gets the streamer.
	 *
	 * @return the streamer
	 */
	public String getStreamer() {
		return streamer;
	}

	/**
	 * Sets the streamer.
	 *
	 * @param string the new streamer
	 */
	public void setStreamer(String string) {
		this.streamer = string;
	}

	/** The generation date. */
	private String generationDate = "";

	/**
	 * Gets the generation date.
	 *
	 * @return the generation date
	 */
	public String getGenerationDate() {
		return generationDate;
	}

	/**
	 * Sets the generation date.
	 *
	 * @param generationDate the new generation date
	 */
	public void setGenerationDate(String generationDate) {
		this.generationDate = generationDate;
	}

	/** The host id. */
	private int hostId;

	/**
	 * Gets the host id.
	 *
	 * @return the host id
	 */
	public int getHostId() {
		return hostId;
	}

	/**
	 * Sets the host id.
	 *
	 * @param hostId the new host id
	 */
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	/** The metadata id. */
	private int metadataId;

	/**
	 * Gets the metadata id.
	 *
	 * @return the metadata id
	 */
	public int getMetadataId() {
		return metadataId;
	}

	/**
	 * Sets the metadata id.
	 *
	 * @param metadataId the new metadata id
	 */
	public void setMetadataId(int metadataId) {
		this.metadataId = metadataId;
	}

	/** The text id. */
	private int textId;

	/**
	 * Gets the text id.
	 *
	 * @return the text id
	 */
	public int getTextId() {
		return textId;
	}

	/**
	 * Sets the text id.
	 *
	 * @param textId the new text id
	 */
	public void setTextId(int textId) {
		this.textId = textId;
	}

	/** The file size. */
	private Long fileSize;

	/**
	 * Gets the file size.
	 *
	 * @return the file size
	 */
	public Long getFileSize() {
		return fileSize;
	}

	/** The openaccess. */
	private boolean openaccess = false;

	/**
	 * Checks if is openaccess.
	 *
	 * @return true, if is openaccess
	 */
	public boolean isOpenaccess() {
		return openaccess;
	}

	/**
	 * Sets the openaccess.
	 *
	 * @param openaccess the new openaccess
	 */
	public void setOpenaccess(boolean openaccess) {
		this.openaccess = openaccess;
	}

	/** The download allawed. */
	private boolean downloadAllawed = false;

	/**
	 * Checks if is download allawed.
	 *
	 * @return true, if is download allawed
	 */
	public boolean isDownloadAllawed() {
		return downloadAllawed;
	}

	/**
	 * Sets the download allawed.
	 *
	 * @param downloadAllawed the new download allawed
	 */
	public void setDownloadAllawed(boolean downloadAllawed) {
		this.downloadAllawed = downloadAllawed;
	}

	/** The counter. */
	private int counter = 0;

	/**
	 * Gets the counter.
	 *
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Sets the counter.
	 *
	 * @param counter the new counter
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/** The home dir. */
	private String homeDir = "";

	/**
	 * Gets the home dir.
	 *
	 * @return the home dir
	 */
	public String getHomeDir() {
		return homeDir;
	}

	/**
	 * Sets the home dir.
	 *
	 * @param homeDir the new home dir
	 */
	public void setHomeDir(String homeDir) {
		this.homeDir = homeDir;
	}

	/** The host. */
	private String host = "";

	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Sets the host.
	 *
	 * @param host the new host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/** The protokoll. */
	private String protokoll = "";

	/**
	 * Gets the protokoll.
	 *
	 * @return the protokoll
	 */
	public String getProtokoll() {
		return protokoll;
	}

	/**
	 * Sets the protokoll.
	 *
	 * @param protokoll the new protokoll
	 */
	public void setProtokoll(String protokoll) {
		this.protokoll = protokoll;
	}

	/** The port. */
	private String port = "";

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/** The object host. */
	private Host objectHost;

	/**
	 * Gets the object host.
	 *
	 * @return the object host
	 */
	public Host getObjectHost() {
		return objectHost;
	}

	/**
	 * Sets the object host.
	 *
	 * @param objectHost the new object host
	 */
	public void setObjectHost(Host objectHost) {
		this.objectHost = objectHost;
	}

	/** The object producer. */
	private Producer objectProducer;

	/**
	 * Gets the object producer.
	 *
	 * @return the object producer
	 */
	public Producer getObjectProducer() {
		return objectProducer;
	}

	/**
	 * Sets the object producer.
	 *
	 * @param objectProducer the new object producer
	 */
	public void setObjectProducer(Producer objectProducer) {
		this.objectProducer = objectProducer;
	}

	/** The short title. */
	private String shortTitle = "";

	/**
	 * Gets the short title.
	 *
	 * @return the short title
	 */
	public String getShortTitle() {
		return shortTitle;
	}

	/**
	 * Sets the short title.
	 *
	 * @param shortTitle the new short title
	 */
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	/** The object metadata. */
	private Metadata objectMetadata;

	/**
	 * Gets the object metadata.
	 *
	 * @return the object metadata
	 */
	public Metadata getObjectMetadata() {
		return objectMetadata;
	}

	/**
	 * Sets the object metadata.
	 *
	 * @param objectMetadata the new object metadata
	 */
	public void setObjectMetadata(Metadata objectMetadata) {
		this.objectMetadata = objectMetadata;
	}

	/** The object lectureseries. */
	private Lectureseries objectLectureseries;

	/**
	 * Gets the object lectureseries.
	 *
	 * @return the object lectureseries
	 */
	public Lectureseries getObjectLectureseries() {
		return objectLectureseries;
	}

	/**
	 * Sets the object lectureseries.
	 *
	 * @param objectLectureseries the new object lectureseries
	 */
	public void setObjectLectureseries(Lectureseries objectLectureseries) {
		this.objectLectureseries = objectLectureseries;
	}

	/** The image. */
	private String image = "";

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/** The simple date. */
	private String simpleDate = "";
	
	/**
	 * Gets the simple date.
	 *
	 * @return the simple date
	 */
	public String getSimpleDate() {
		return simpleDate;
	}

	/**
	 * Sets the simple date.
	 *
	 * @param simpleDate the new simple date
	 */
	public void setSimpleDate(String simpleDate) {
		this.simpleDate = simpleDate;
	}

	/** The date. */
	private String date = "";

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets the filename preffix.
	 *
	 * @return the filename preffix
	 */
	public String getFilenamePreffix() {
		// extract time and date from the originalFileName
		String[] parameter = this.getFilename().split("\\_");
		// the upload-file has to end on 'mp4' or 'mp3' or 'm4v' or flv
		String parameter4 = parameter[3];
		// check parameter 3 - this is the date
		String l2gDate = parameter[2];
		// and parameter 4 - this is the time
		String l2gTime = parameter4.split("\\.")[0];
		String generationDate = l2gDate + "_" + l2gTime;
		String preffix = parameter[0] + "_" + parameter[1] + "_" + generationDate;
		return preffix;
	}

	/**
	 * Gets the filename format.
	 *
	 * @return the filename format
	 */
	public String getFilenameFormat() {
		// extract time and date from the originalFileName
		String[] parameter = this.getFilename().split("\\_");
		// the upload-file has to end on 'mp4' or 'mp3' or 'm4v' or flv
		String parameter4 = parameter[3];
		// format
		String format = parameter4.split("\\.")[1];
		return format;
	}

	/** The has mp4 file. */
	private boolean hasMp4File;

	/**
	 * Checks if is checks for mp4 file.
	 *
	 * @return true, if is checks for mp4 file
	 */
	public boolean isHasMp4File() {
		return hasMp4File;
	}

	/**
	 * Sets the checks for mp4 file.
	 *
	 * @param hasMp4File the new checks for mp4 file
	 */
	public void setHasMp4File(boolean hasMp4File) {
		this.hasMp4File = hasMp4File;
	}

	/** The mp3 file. */
	private File mp3File;

	/**
	 * Gets the mp3 file.
	 *
	 * @return the mp3 file
	 */
	public File getMp3File() {
		return mp3File;
	}

	/**
	 * Sets the mp3 file.
	 *
	 * @param mp3File the new mp3 file
	 */
	public void setMp3File(File mp3File) {
		this.mp3File = mp3File;
	}

	/** The file. */
	private File file;

	/**
	 * Gets the m4v file.
	 *
	 * @return the m4v file
	 */
	public File getM4vFile() {
		return file;
	}

	/**
	 * Sets the m4v file.
	 *
	 * @param file the new m4v file
	 */
	public void setM4vFile(File file) {
		this.file = file;
	}

	/** The pdf file. */
	private File pdfFile;

	/**
	 * Gets the pdf file.
	 *
	 * @return the pdf file
	 */
	public File getPdfFile() {
		return pdfFile;
	}

	/**
	 * Sets the pdf file.
	 *
	 * @param pdfFile the new pdf file
	 */
	public void setPdfFile(File pdfFile) {
		this.pdfFile = pdfFile;
	}

	/** The mp4 file. */
	private File mp4File;

	/**
	 * Gets the mp4 file.
	 *
	 * @return the mp4 file
	 */
	public File getMp4File() {
		return mp4File;
	}

	/**
	 * Sets the mp4 file.
	 *
	 * @param mp4File the new mp4 file
	 */
	public void setMp4File(File mp4File) {
		this.mp4File = mp4File;
	}

	/** The secure url. */
	private String secureUrl = "null";

	/**
	 * Gets the secure url.
	 *
	 * @return the secure url
	 */
	public String getSecureUrl() {
		return secureUrl;
	}

	/**
	 * Sets the secure url.
	 *
	 * @param secureUrl the new secure url
	 */
	public void setSecureUrl(String secureUrl) {
		this.secureUrl = secureUrl;
	}

	/**
	 * Sets the duration.
	 *
	 * @param i the new duration
	 */
	public void setDuration(String i) {
		this.duration = i;
	}

	/**
	 * Sets the duration min.
	 *
	 * @param i the new duration min
	 */
	public void setDurationMin(String i) {
		this.durationMin = i;
	}

	/** The bitrate. */
	private String bitrate = "";

	/**
	 * Gets the bitrate.
	 *
	 * @return the bitrate
	 */
	public String getBitrate() {
		return bitrate;
	}

	/**
	 * Sets the bitrate.
	 *
	 * @param bitrate the new bitrate
	 */
	public void setBitrate(String bitrate) {
		this.bitrate = bitrate;
	}

	/**
	 * Sets the file size.
	 *
	 * @param fileSize the new file size
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	/** The segment list. */
	private List<?> segmentList;

	/**
	 * Gets the segment list.
	 *
	 * @return the segment list
	 */
	public List<?> getSegmentList() {
		return segmentList;
	}

	/**
	 * Sets the segment list.
	 *
	 * @param segmentList the new segment list
	 */
	public void setSegmentList(List<?> segmentList) {
		this.segmentList = segmentList;
	}

	/** The file size mb. */
	private long fileSizeMB;

	/**
	 * Gets the file size mb.
	 *
	 * @return the file size mb
	 */
	public long getFileSizeMB() {
		return fileSizeMB;
	}

	/**
	 * Sets the file size mb.
	 *
	 * @param fileSizeMB the new file size mb
	 */
	public void setFileSizeMB(long fileSizeMB) {
		this.fileSizeMB = fileSizeMB;
	}

	/** The hits. */
	private int hits;

	/**
	 * Gets the hits.
	 *
	 * @return the hits
	 */
	public int getHits() {
		return hits;
	}

	/**
	 * Sets the hits.
	 *
	 * @param hits the new hits
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}

	/** The m4a file. */
	private File m4aFile;

	/**
	 * Gets the m4a file.
	 *
	 * @return the m4a file
	 */
	public File getM4aFile() {
		return m4aFile;
	}

	/**
	 * Sets the m4a file.
	 *
	 * @param file the new m4a file
	 */
	public void setM4aFile(File file) {
		m4aFile = file;
	}

	/**
	 * Checks if is permitted to segment.
	 *
	 * @return true, if is permitted to segment
	 */
	public boolean isPermittedToSegment() {
		return permittedToSegment;
	}

	/**
	 * Sets the permitted to segment.
	 *
	 * @param permittedToSegment the new permitted to segment
	 */
	public void setPermittedToSegment(boolean permittedToSegment) {
		this.permittedToSegment = permittedToSegment;
	}

	/** The permitted to segment. */
	private boolean permittedToSegment;
	
	/**
	 * Gets the s preffix.
	 *
	 * @return the s preffix
	 */
	public String getSPreffix() {
		String preffix="";
		try{
			// extract time and date from the originalFileName
			String[] parameter = this.getSecureFilename().split("\\.");
			preffix = parameter[0];
		}catch(NullPointerException npe){}
		return preffix;
	}

	/**
	 * Gets the preffix.
	 *
	 * @return the preffix
	 */
	public String getPreffix() {
		String preffix="";
		try{
			// extract time and date from the originalFileName
			String[] parameter = this.getFilename().split("\\_");
			// the upload-file has to end on 'mp4' or 'mp3' or 'm4v' or flv
			String parameter4 = parameter[3];
			// check parameter 3 - this is the date
			String l2gDate = parameter[2];
			// and parameter 4 - this is the time
			String l2gTime = parameter4.split("\\.")[0];
			String generationDate = l2gDate + "_" + l2gTime;
			preffix = parameter[0] + "_" + parameter[1] + "_" + generationDate;
						
		}catch(ArrayIndexOutOfBoundsException aiobe){
			preffix="";
		}
		return preffix;
	}
}
