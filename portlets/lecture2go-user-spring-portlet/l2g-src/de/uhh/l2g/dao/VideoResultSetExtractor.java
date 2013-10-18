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
package de.uhh.l2g.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import de.uhh.l2g.beans.Video;


/**
 * The Class VideoResultSetExtractor.
 */
public class VideoResultSetExtractor implements ResultSetExtractor {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
	/**
	 * Extract data.
	 *
	 * @param rs the rs
	 * @return the object
	 * @throws SQLException the sQL exception
	 */
	public Object extractData(ResultSet rs) throws SQLException {
		Video video = new Video();
		video.setId(rs.getInt(1));
		video.setTitle(rs.getString(2));
		video.setTags(rs.getString(3));
		video.setLectureseriesId(rs.getInt(4));
		video.setEigentuemerId(rs.getInt(5));
		video.setProducerId(rs.getInt(6));
		video.setContainerFormat(rs.getString(7));
		video.setFilename(rs.getString(8));
		video.setResolution(rs.getString(9));

		try {
			String str = rs.getString(10).trim();
			String[] strArr = str.split(":");

			String s = "";
			String mi = "";

			// st
			if ((strArr[0].charAt(0) + "").equals("0")) s = strArr[0].charAt(1) + "";
			else s = strArr[0];
			// min
			if ((strArr[1].charAt(0) + "").equals("0")) mi = strArr[1].charAt(1) + "";
			else mi = strArr[1];

			Integer st = new Integer(s);
			Integer min = new Integer(mi);
			Integer durationMin = (st * 60 + min);
			video.setDurationMin(durationMin + "");
		} catch (NullPointerException npe) {
			//
		} catch (StringIndexOutOfBoundsException nobe) {
			//
		}

		String duration = "";
		try {
			duration = rs.getString(10).trim();
			if (duration.equals("") || duration == null || duration.equals("0")) duration = "";
		} catch (NullPointerException npe) {
			duration = "";
		}

		video.setDuration(duration);
		video.setHostId(rs.getInt(11));
		video.setTextId(rs.getInt(12));
		video.setFileSize(rs.getLong(13));
		video.setGenerationDate(rs.getString(14));
		video.setOpenaccess(rs.getBoolean(15));
		video.setDownloadAllawed(rs.getBoolean(16));
		video.setMetadataId(rs.getInt(17));
		video.setSecureFilename(rs.getString(18));
		video.setFileSizeMB((rs.getLong(13) / 1024) / 1000);

		String l = rs.getString(2).toString();
		String m = new String(l);
		int length = m.length();

		if (length > 50) video.setShortTitle(m.substring(0, 50) + "...");
		else video.setShortTitle(rs.getString(2));

		video.setHits(rs.getInt(19));
		
		try{
			String dt = rs.getString(20);
			String d = dt.split(" ")[0];
			String t = dt.split(" ")[1];
			String uploadDate = d.split("-")[2]+"."+d.split("-")[1]+"."+d.split("-")[0]+" - "+t.split(":")[0]+":"+t.split(":")[1];
			video.setUploadDate(uploadDate);
		}catch(NullPointerException npe){
			//
		}
		
		// set video type
		try {
			String file = video.getFilename();
			String[] arr = file.split("\\.");
			int s = arr.length;

			if (video.getFilename().split("\\.")[s - 1].toLowerCase().equals("mp3")) video.setUploadType("audio");
			if (video.getFilename().split("\\.")[s - 1].toLowerCase().equals("mp4")) video.setUploadType("video");
		} catch (NullPointerException npe) {
			//
		}

		// set permission to segment 
		video.setPermittedToSegment(rs.getBoolean(21));
		video.setFacilityId(rs.getInt(22));
		video.setCitation2go(rs.getInt(23));
		
		return video;
	}
}
