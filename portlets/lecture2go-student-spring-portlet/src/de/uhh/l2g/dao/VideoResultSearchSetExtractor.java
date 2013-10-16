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

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.jdbc.core.ResultSetExtractor;

import de.uhh.l2g.beans.VideoResultSearch;
import de.uhh.l2g.util.L2goPropsUtil;



/**
 * The Class VideoResultSearchSetExtractor.
 */
public class VideoResultSearchSetExtractor implements ResultSetExtractor {
 
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
		VideoResultSearch videoResultSearch = new VideoResultSearch();
		
		videoResultSearch.setId(rs.getInt(1));
		videoResultSearch.setTitle(rs.getString(2));
		videoResultSearch.setLecturerName(rs.getString(3));
		videoResultSearch.setLectureserieName(rs.getString(4));
		videoResultSearch.setFilename(rs.getString(5));
		
		// date
		// extract time and date from the originalFileName
		String[] parameter = rs.getString(6).split("\\_");
		// check parameter 3 - this is the date
		String l2gDate = parameter[0];
		String l2gTime = parameter[1];
		videoResultSearch.setDate(l2gDate.split("\\-")[2] + "." + l2gDate.split("\\-")[1] + "." + l2gDate.split("\\-")[0] + " - " + l2gTime.split("\\-")[0] + ":" + l2gTime.split("\\-")[1]);
		videoResultSearch.setSimpleDate(l2gDate.split("\\-")[2] + "." + l2gDate.split("\\-")[1] + "." + l2gDate.split("\\-")[0] );

		//tree1 or tree2
		String facilityType = rs.getString(7);
				
		//URL
		String webhome = L2goPropsUtil.get("lecture2go.web.home");
		if (webhome.contains("localhost")) webhome+="/web/vod";
		try{
			if (facilityType.equals("tree1")){
				videoResultSearch.setUrl(webhome+"/l2go/-/v/"+videoResultSearch.getId());
			}
		}catch(NoSuchElementException nseex){}

		// image
		String image ="";
		image = videoResultSearch.getPreffix() + ".jpg";
		File im = new File(L2goPropsUtil.get("lecture2go.images.system.path") + "/" + image);

		// set thumbnail
		if (im.isFile()) videoResultSearch.setImage(L2goPropsUtil.get("lecture2go.web.root") + "/images/" + image);
		else {
			//thumb for audio or no-image
			String f = videoResultSearch.getFilename();
			int z = f.split("\\.").length;z--;//count down because of the array
			String suffix = videoResultSearch.getFilename().toLowerCase().split("\\.")[z];
			
			if (suffix.equals("mp3")) {
				videoResultSearch.setImage(L2goPropsUtil.get("lecture2go.web.root") + L2goPropsUtil.get("lecture2go.theme.root.path") + "/images/l2go/audio_only_big.png");
			}
			else videoResultSearch.setImage(L2goPropsUtil.get("lecture2go.web.root") + L2goPropsUtil.get("lecture2go.theme.root.path")  + "/images/l2go/noimage.jpg");
		}
		
		return videoResultSearch;
	}
}
