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
package de.uhh.l2g.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import de.uhh.l2g.beans.Video;

/**
 * The Class VideoDateOfLectureComparator.
 */
public class VideoDateOfLectureComparator implements Comparator<Video> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Video v1, Video v2) {
		Date d1 = new Date();
		Date d2 = new Date();
		String[] split1 = v1.getFilenamePreffix().split("_");
		String[] split2 = v2.getFilenamePreffix().split("_");
		
		//System.out.println("STRING 2 :" + split1[2] + "_" + split1[3]);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		try {
			d1 = df.parse(split1[2] + "_" + split1[3]);
			d2 = df.parse(split2[2] + "_" + split2[3]);
		} catch (ParseException e) {
			
		}

		int ret = 0;
		if (d1.before(d2)) ret = 1;
		if (d1.after(d2)) ret = -1;

		return ret;

		}
	
}

