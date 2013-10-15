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

import java.io.UnsupportedEncodingException;

import com.josephoconnell.html.HTMLInputFilter;

/**
 * The Class HtmlManager.
 */
public class HtmlManager {

	/** The html. */
	private String html = "";

	/**
	 * Gets the html.
	 *
	 * @return the html
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * Sets the html.
	 *
	 * @param html the new html
	 */
	public void setHtml(String html) {
		this.html = html;
	}
	
	/**
	 * Prepare html for wyswyg editor.
	 *
	 * @param html the html
	 * @return the string
	 */
	public static String prepareHtmlForWyswygEditor(String html){
		try{
			// Remove tab
			html = html.replace("\t", "");
	        // Remove line break (Unix)
			html = html.replace("\n", "");
	        // Line break / carriage return remove (Windows)
			html = html.replace("\r", "");
			 // introduction of characters replace
			html = html.replace("\'", "\""); 			
		}catch(NullPointerException npe){}
		
		return html;
	}
	
	/**
	 * Clean html tags.
	 *
	 * @param html the html
	 * @return the string
	 */
	public static String cleanHtmlTags(String html){
		// retrieve input from user
		String clean = "";
		try {
			clean = new HTMLInputFilter().filter( html );
		}catch (Exception e){
			clean = "Error!";
		}
		return clean;
	}
	
	/**
	 * Checks if is o88591to ut f8.
	 *
	 * @param text the text
	 * @return the string
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public static String ISO88591toUTF8(String text) throws UnsupportedEncodingException{
		return new String(text.getBytes("ISO-8859-1"),"UTF-8");
	}
}
