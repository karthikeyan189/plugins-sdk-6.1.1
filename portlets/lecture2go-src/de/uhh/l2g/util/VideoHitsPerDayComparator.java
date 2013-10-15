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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import de.uhh.l2g.beans.Video;

/**
 * The Class VideoHitsPerDayComparator.
 */
public class VideoHitsPerDayComparator implements Comparator<Video> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	/**
	 * Compare.
	 *
	 * @param v1 the v1
	 * @param v2 the v2
	 * @return the int
	 */
	public int compare(Video v1, Video v2) {
		
		long clicksperday1 = 0;
		long clicksperday2 = 0;
		
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTimeZone( TimeZone.getTimeZone("CET") );
		long msnow = calendar.getTimeInMillis();

		Date d1 = new Date();
		Date d2 = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		try {
			d1 = df.parse(v1.getGenerationDate());
			d2 = df.parse(v2.getGenerationDate());
			
			
			
			long ms1   = d1.getTime();
			long ms2   = d2.getTime();
			
			//Hits pro Tag
			long days1 = ((msnow - ms1) / (1000*60*60*24)); 
			long days2 = ((msnow - ms2) / (1000*60*60*24)); 
			
			clicksperday1 = (v1.getHits()/days1);
			clicksperday2 = (v2.getHits()/days2);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int ret = 0;
		if(clicksperday1 > clicksperday2) ret = 1;
		if(clicksperday1 < clicksperday2) ret = -1;

		return ret;
	}

}
