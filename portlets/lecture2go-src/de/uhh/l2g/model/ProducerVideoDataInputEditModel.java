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
package de.uhh.l2g.model;


/**
 * The Class ProducerVideoDataInputEditModel.
 */
public class ProducerVideoDataInputEditModel extends ProducerMetaDataModel {

	/**
	 * The Constructor.
	 */
	public ProducerVideoDataInputEditModel() {

	}

	/** The mp3 file. */
	private String mp3File = "";

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#getMp3File()
	 */
	@Override
	public String getMp3File() {
		return mp3File;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#setMp3File(java.lang.String)
	 */
	@Override
	public void setMp3File(String mp3File) {
		this.mp3File = mp3File;
	}

	/** The pdf file. */
	private String pdfFile = "";

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#getPdfFile()
	 */
	@Override
	public String getPdfFile() {
		return pdfFile;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#setPdfFile(java.lang.String)
	 */
	@Override
	public void setPdfFile(String pdfFile) {
		this.pdfFile = pdfFile;
	}

	/** The file. */
	private String file = "";

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#getM4vFile()
	 */
	@Override
	public String getM4vFile() {
		return file;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#setM4vFile(java.lang.String)
	 */
	@Override
	public void setM4vFile(String file) {
		this.file = file;
	}

	/** The open access. */
	private boolean openAccess;

	/**
	 * Checks if is open access.
	 *
	 * @return true, if checks if is open access
	 */
	public boolean isOpenAccess() {
		return openAccess;
	}

	/**
	 * Sets the open access.
	 *
	 * @param openAccess the open access
	 */
	public void setOpenAccess(boolean openAccess) {
		this.openAccess = openAccess;
	}

	/** The secure url. */
	private String secureUrl = "";

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.StudentModel#getSecureUrl()
	 */
	@Override
	public String getSecureUrl() {
		return secureUrl;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.StudentModel#setSecureUrl(java.lang.String)
	 */
	@Override
	public void setSecureUrl(String secureUrl) {
		this.secureUrl = secureUrl;
	}


	/** The file1. */
	private String file1 = "";

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#getM4aFile()
	 */
	@Override
	public String getM4aFile() {
		return file1;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#setM4aFile(java.lang.String)
	 */
	@Override
	public void setM4aFile(String file) {
		file1 = file;
	}

	/** The delete video request. */
	private boolean deleteVideoRequest;

	/**
	 * Checks if is delete video request.
	 *
	 * @return true, if checks if is delete video request
	 */
	public boolean isDeleteVideoRequest() {
		return deleteVideoRequest;
	}

	/**
	 * Sets the delete video request.
	 *
	 * @param deleteVideoRequest the delete video request
	 */
	public void setDeleteVideoRequest(boolean deleteVideoRequest) {
		this.deleteVideoRequest = deleteVideoRequest;
	}

	/** The first video upload. */
	private boolean firstVideoUpload;

	/**
	 * Checks if is first video upload.
	 *
	 * @return true, if checks if is first video upload
	 */
	public boolean isFirstVideoUpload() {
		return firstVideoUpload;
	}

	/**
	 * Sets the first video upload.
	 *
	 * @param firstVideoUpload the first video upload
	 */
	public void setFirstVideoUpload(boolean firstVideoUpload) {
		this.firstVideoUpload = firstVideoUpload;
	}

}
