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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.uhh.l2g.beans.Host;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.beans.Video;

/**
 * The Class FFmpegManager.
 */
public class FFmpegManager {

	/**
	 * Instantiates a new f fmpeg manager.
	 *
	 * @param filename the filename
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public FFmpegManager(String filename) throws IOException {
		ProcessBuilder pb = new ProcessBuilder(ffmgegLibPath, "-i", filename);
		Process p = pb.start();
		InputStream errorStream = p.getErrorStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(errorStream));

		String[] out = new String[40];// array buffer
		String s = "";
		int i = 0;
		while ((s = in.readLine()) != null) {
			out[i] = s;
			i++;
		}
		videoMetadata = out;
		for (int j = 0; j < out.length; j++) {
			String line = out[j];

			// duration
			try {
				videoDuration = line.split("Duration:")[1].split(",")[0];
			} catch (Exception e) {
			}

			// bitrate
			try {
				videoBitRate = line.split("Duration:")[1].split("bitrate:")[1].trim();
			} catch (Exception e) {
			}

			// resolution
			try {
				videoResolution = line.split("Video:")[1].split(",")[2].trim();
			} catch (Exception e) {
			}

			// codec
			try {
				videoCodec = line.split("Video:")[1].split(",")[0].trim();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Instantiates a new f fmpeg manager.
	 */
	public FFmpegManager() {

	}

	/**
	 * Creates the thumbnail.
	 *
	 * @param video the video
	 * @param time the time
	 * @param thumbnailLocation the thumbnail location
	 * @return true, if successful
	 */
	public boolean createThumbnail(Video video, String time, String thumbnailLocation) {
		boolean ret = false;

		Producer producer = video.getObjectProducer();
		Host host = video.getObjectHost();

		Runtime runCmd = Runtime.getRuntime();
		String command = "";

		int sec = new Integer(time.split(":")[0]) * 60 * 60 + new Integer(time.split(":")[1]) * 60 + new Integer(time.split(":")[2]);

		if (video.isOpenaccess()) command = ffmgegLibPath +" -ss " + sec + " -i " + L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/" + video.getFilename() + " -f image2 -vframes 1 -filter:v scale='130:-1' " + thumbnailLocation + "/" + video.getId() + "_" + sec + ".jpg";
		else command = ffmgegLibPath + " -ss " + sec + " -i " + L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/" + video.getSecureFilename() + " -f image2 -vframes 1 -filter:v scale='130:-1' " + thumbnailLocation + "/" + video.getId() + "_" + sec + ".jpg";
		try {
			runCmd.exec(command);
			ret = true;
		} catch (IOException e) {
			ret = false;
		}

		return ret;
	}

	/**
	 * Creates the thumbnail.
	 *
	 * @param fileLocation the file location
	 * @param thumbnailLocation the thumbnail location
	 * @return true, if successful
	 */
	public boolean createThumbnail(String fileLocation, String thumbnailLocation) {
		Runtime runCmd = Runtime.getRuntime();
		String thumbPreffLoc = thumbnailLocation.split(".jpg")[0];
		String command = ffmgegLibPath + " -ss 13 -i " + fileLocation + " -f image2 -vframes 1 " + thumbnailLocation;
		String command1 = ffmgegLibPath + " -ss 13 -i " + fileLocation + " -f image2 -vframes 1 -filter:v scale='130:-1' " + thumbPreffLoc+"_s.jpg";
		String command2 = ffmgegLibPath + " -ss 13 -i " + fileLocation + " -f image2 -vframes 1 -filter:v scale='300:-1' " + thumbPreffLoc+"_m.jpg";

		boolean ret = true;
		
		try {
			runCmd.exec(command);
			File f = new File(thumbnailLocation);
			if(!f.isFile())ret = false;
		} catch (IOException e) { ret = false;}

		try {
			runCmd.exec(command1);
			File f = new File(thumbPreffLoc+"_s.jpg");
			if(!f.isFile())ret = false;
		} catch (IOException e) { ret = false;}
		
		try {
			runCmd.exec(command2);
			File f = new File(thumbPreffLoc+"_m.jpg");
			if(!f.isFile())ret = false;
		} catch (IOException e) { ret = false;}
		
		//because of memory limit should close the ffmpeg processes
		if(ret)try{runCmd.exec("/usr/bin/killall -9 ffmpeg");} catch (IOException e) {e.printStackTrace();}
				
		return ret;
	}
	
	/**
	 * Thumbnails exists.
	 *
	 * @param video the video
	 * @return true, if successful
	 */
	public boolean thumbnailsExists(Video video){
		boolean ret = true;
		
		String preffix = "";
		if(video.isOpenaccess())preffix=video.getPreffix();
		else preffix=video.getSPreffix();
		
		File fJpg = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + preffix + ".jpg");
		File fJpgm = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + preffix + "_m.jpg");
		File fJpgs = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + preffix + "_s.jpg");
		
		if(!fJpg.isFile() || !fJpgs.isFile() || !fJpgm.isFile()) ret = false;
		
		return ret;
	}

	/** The video duration. */
	private String videoDuration = "";

	/**
	 * Gets the video duration.
	 *
	 * @return the video duration
	 */
	public String getVideoDuration() {
		return videoDuration;
	}

	/** The video resolution. */
	private String videoResolution = "";

	/**
	 * Gets the video resolution.
	 *
	 * @return the video resolution
	 */
	public String getVideoResolution() {
		return videoResolution;
	}

	/**
	 * Sets the video resolution.
	 *
	 * @param videoResolution the new video resolution
	 */
	public void setVideoResolution(String videoResolution) {
		this.videoResolution = videoResolution;
	}

	/** The video bit rate. */
	private String videoBitRate = "";

	/**
	 * Gets the video bit rate.
	 *
	 * @return the video bit rate
	 */
	public String getVideoBitRate() {
		return videoBitRate;
	}

	/**
	 * Sets the video bit rate.
	 *
	 * @param videoBitRate the new video bit rate
	 */
	public void setVideoBitRate(String videoBitRate) {
		this.videoBitRate = videoBitRate;
	}

	/** The video metadata. */
	private String[] videoMetadata;

	/**
	 * Gets the video metadata.
	 *
	 * @return the video metadata
	 */
	public String[] getVideoMetadata() {
		return videoMetadata;
	}

	/**
	 * Sets the video metadata.
	 *
	 * @param videoMetadata the new video metadata
	 */
	public void setVideoMetadata(String[] videoMetadata) {
		this.videoMetadata = videoMetadata;
	}

	/**
	 * Sets the video duration.
	 *
	 * @param videoDuration the new video duration
	 */
	public void setVideoDuration(String videoDuration) {
		this.videoDuration = videoDuration;
	}

	/** The video codec. */
	private String videoCodec = "";

	/**
	 * Gets the video codec.
	 *
	 * @return the video codec
	 */
	public String getVideoCodec() {
		return videoCodec;
	}

	/**
	 * Sets the video codec.
	 *
	 * @param videoCodec the new video codec
	 */
	public void setVideoCodec(String videoCodec) {
		this.videoCodec = videoCodec;
	}
	
	/** The ffmgeg lib path. */
	private String ffmgegLibPath = L2goPropsUtil.get("lecture2go.ffmpeg.bin");

	/**
	 * Gets the ffmgeg lib path.
	 *
	 * @return the ffmgeg lib path
	 */
	public String getFfmgegLibPath() {
		return ffmgegLibPath;
	}

	/**
	 * Sets the ffmgeg lib path.
	 *
	 * @param ffmgegLibPath the new ffmgeg lib path
	 */
	public void setFfmgegLibPath(String ffmgegLibPath) {
		this.ffmgegLibPath = ffmgegLibPath;
	}

}
