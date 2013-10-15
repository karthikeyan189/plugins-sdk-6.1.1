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
package de.uhh.l2g.upload;

import java.text.NumberFormat;
import java.util.LinkedHashMap;

import org.apache.commons.fileupload.ProgressListener;


/**
 * The listener interface for receiving fileUploadProgress events.
 * The class that is interested in processing a fileUploadProgress
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addFileUploadProgressListener<code> method. When
 * the fileUploadProgress event occurs, that object's appropriate
 * method is invoked.
 *
 * @see FileUploadProgressEvent
 */
public class FileUploadProgressListener implements ProgressListener {
	
	/** The Constant DEFAULT_MAX_UPLOADS. */
	private static final int DEFAULT_MAX_UPLOADS = 1000;

	/** The current uploads. */
	private static LinkedHashMap currentUploads = new LinkedHashMap(DEFAULT_MAX_UPLOADS);
	
	/** The bytes transferred. */
	private long bytesTransferred = 0;

	/** The file size. */
	private long fileSize = -100;

	/** The ten kb read. */
	private long tenKBRead = -1;
	
	/** The id. */
	private int id;
	
	/** The content length. */
	private long contentLength ;

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

	/**
	 * Gets the content length.
	 *
	 * @return the content length
	 */
	public long getContentLength() {
		return contentLength;
	}

	/**
	 * Sets the content length.
	 *
	 * @param contentLength the new content length
	 */
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	/**
	 * Instantiates a new file upload progress listener.
	 *
	 * @param id the id
	 * @param contentLength the content length
	 */
	public FileUploadProgressListener(Integer id, long contentLength) {
		this.id = id;
		this.contentLength = contentLength;
		if(currentUploads.size()==DEFAULT_MAX_UPLOADS)clear();
	}

	/**
	 * Instantiates a new file upload progress listener.
	 */
	public FileUploadProgressListener() {
	}
	
	/**
	 * Gets the file upload status.
	 *
	 * @return the file upload status
	 */
	private String getFileUploadStatus() {
		// per looks like 0% - 100%, remove % before submission
		String per = NumberFormat.getPercentInstance().format((double) bytesTransferred / (double) fileSize);
		String ret = per.substring(0, per.length() - 1); 
		return ret;
	}

	/**
	 * Gets the file upload status.
	 *
	 * @param id the id
	 * @return the file upload status
	 */
	public String getFileUploadStatus(int id) {
			return (String) currentUploads.get(id);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.ProgressListener#update(long, long, int)
	 */
	/**
	 * Update.
	 *
	 * @param bytesRead the bytes read
	 * @param contentLength the content length
	 * @param items the items
	 */
	public void update(long bytesRead, long contentLength, int items) {
		// update bytesTransferred and fileSize (if required) every 10 KB is
		// read
		long tenKB = bytesRead / 10240;
		if (tenKBRead == tenKB) return;
		tenKBRead = tenKB;

		bytesTransferred = bytesRead;
		if (fileSize != contentLength) fileSize = contentLength;
		
		currentUploads.put(id, getFileUploadStatus());
		if(new Integer(getFileUploadStatus())==100) remove(id);
	}
	
	/**
	 * Removes the.
	 *
	 * @param id the id
	 */
	public void remove(int id){
		currentUploads.remove(id);		
	}
	
	/**
	 * Clear.
	 */
	protected void clear(){
		currentUploads.clear();
	}

}
