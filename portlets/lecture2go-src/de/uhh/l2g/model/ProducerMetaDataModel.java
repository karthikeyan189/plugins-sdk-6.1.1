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

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * The Class ProducerMetaDataModel.
 */
public class ProducerMetaDataModel extends ProducerModel {

	/** The citation2go. */
	private int citation2go=1;
	
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
	 * @param citation2go the citation2go
	 */
	public void setCitation2go(int citation2go) {
		this.citation2go = citation2go;
	}

	/** The license cc. */
	private String licenseCC;

	/**
	 * Gets the license cc.
	 *
	 * @return the license cc
	 */
	public String getLicenseCC() {
		return licenseCC;
	}

	/**
	 * Sets the license cc.
	 *
	 * @param licenseCC the license cc
	 */
	public void setLicenseCC(String licenseCC) {
		this.licenseCC = licenseCC;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#getLicense()
	 */
	@Override
	public String getLicense() {
		return license;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#setLicense(java.lang.String)
	 */
	@Override
	public void setLicense(String license) {
		this.license = license;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#getLicenseUhhL2go()
	 */
	@Override
	public String getLicenseUhhL2go() {
		return licenseUhhL2go;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#setLicenseUhhL2go(java.lang.String)
	 */
	@Override
	public void setLicenseUhhL2go(String licenseUhhL2go) {
		this.licenseUhhL2go = licenseUhhL2go;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#getLicenseByNcSa()
	 */
	@Override
	public String getLicenseByNcSa() {
		return licenseByNcSa;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#setLicenseByNcSa(java.lang.String)
	 */
	@Override
	public void setLicenseByNcSa(String licenseByNcSa) {
		this.licenseByNcSa = licenseByNcSa;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#getLicenseByNcNd()
	 */
	@Override
	public String getLicenseByNcNd() {
		return licenseByNcNd;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.GuestModel#setLicenseByNcNd(java.lang.String)
	 */
	@Override
	public void setLicenseByNcNd(String licenseByNcNd) {
		this.licenseByNcNd = licenseByNcNd;
	}

	/** The license. */
	private String license;

	/** The license uhh l2go. */
	private String licenseUhhL2go;

	/** The license by nc sa. */
	private String licenseByNcSa;

	/** The license by nc nd. */
	private String licenseByNcNd;

	/** The title. */
	private String title;

	/** The tags. */
	private String tags;

	/** The resolution. */
	private String resolution = "";

	/** The duration. */
	private String duration = "";

	/** The container format. */
	private String containerFormat = "";

	/**
	 * The Constructor.
	 */
	public ProducerMetaDataModel() {

	}

	/**
	 * Sets the title.
	 *
	 * @param title the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the tags.
	 *
	 * @param tags the tags
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

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
	 * @param containerFormat the container format
	 */
	public void setContainerFormat(String containerFormat) {
		this.containerFormat = containerFormat;
	}

	/**
	 * Gets the aufloesung.
	 *
	 * @return the aufloesung
	 */
	public String getAufloesung() {
		return resolution;
	}

	/**
	 * Sets the aufloesung.
	 *
	 * @param resolution the aufloesung
	 */
	public void setAufloesung(String resolution) {
		this.resolution = resolution;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.StudentModel#getDuration()
	 */
	public String getDuration() {
		return duration;
	}

	/** The contact file. */
	private CommonsMultipartFile contactFile;

	/**
	 * Gets the contact file.
	 *
	 * @return the contact file
	 */
	public CommonsMultipartFile getContactFile() {
		return contactFile;
	}

	/** The format. */
	private String format = "";

	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Sets the format.
	 *
	 * @param format the format
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/** The type. */
	private String type = "";

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/** The language. */
	private String language = "";

	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the language.
	 *
	 * @param language the language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/** The subject. */
	private String subject = "";

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject.
	 *
	 * @param subject the subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/** The coverage. */
	private String coverage = "";

	/**
	 * Gets the coverage.
	 *
	 * @return the coverage
	 */
	public String getCoverage() {
		return coverage;
	}

	/**
	 * Sets the coverage.
	 *
	 * @param coverage the coverage
	 */
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	/** The description. */
	private String description = "";

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** The longdescription. */
	private String longdescription = "";

	/**
	 * Gets the long description.
	 *
	 * @return the long description
	 */
	public String getLongDescription() {
		return longdescription;
	}

	/**
	 * Sets the long description.
	 *
	 * @param longdescription the long description
	 */
	public void setLongDescription(String longdescription) {
		this.longdescription = longdescription;
	}

	/** The creator. */
	private String creator = "";

	/**
	 * Gets the creator.
	 *
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * Sets the creator.
	 *
	 * @param creator the creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/** The publisher. */
	private String publisher = "";

	/**
	 * Gets the publisher.
	 *
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * Sets the publisher.
	 *
	 * @param publisher the publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/** The contributor. */
	private String contributor = "";

	/**
	 * Gets the contributor.
	 *
	 * @return the contributor
	 */
	public String getContributor() {
		return contributor;
	}

	/**
	 * Sets the contributor.
	 *
	 * @param contributor the contributor
	 */
	public void setContributor(String contributor) {
		this.contributor = contributor;
	}

	/** The rights holder. */
	private String rightsHolder = "";

	/**
	 * Gets the rights holder.
	 *
	 * @return the rights holder
	 */
	public String getRightsHolder() {
		return rightsHolder;
	}

	/**
	 * Sets the rights holder.
	 *
	 * @param rightsHolder the rights holder
	 */
	public void setRightsHolder(String rightsHolder) {
		this.rightsHolder = rightsHolder;
	}

	/** The rights. */
	private String rights = "";

	/**
	 * Gets the rights.
	 *
	 * @return the rights
	 */
	public String getRights() {
		return rights;
	}

	/**
	 * Sets the rights.
	 *
	 * @param rights the rights
	 */
	public void setRights(String rights) {
		this.rights = rights;
	}

	/** The provenance. */
	private String provenance = "";

	/**
	 * Gets the provenance.
	 *
	 * @return the provenance
	 */
	public String getProvenance() {
		return provenance;
	}

	/**
	 * Sets the provenance.
	 *
	 * @param provenance the provenance
	 */
	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	/** The source. */
	private String source = "";

	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Sets the source.
	 *
	 * @param source the source
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/** The relation. */
	private String relation = "";

	/**
	 * Gets the relation.
	 *
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * Sets the relation.
	 *
	 * @param relation the relation
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}

	/** The audience. */
	private String audience = "";

	/**
	 * Gets the audience.
	 *
	 * @return the audience
	 */
	public String getAudience() {
		return audience;
	}

	/**
	 * Sets the audience.
	 *
	 * @param audience the audience
	 */
	public void setAudience(String audience) {
		this.audience = audience;
	}

	/** The instructional method. */
	private String instructionalMethod = "";

	/**
	 * Gets the instructional method.
	 *
	 * @return the instructional method
	 */
	public String getInstructionalMethod() {
		return instructionalMethod;
	}

	/**
	 * Sets the instructional method.
	 *
	 * @param instructionalMethod the instructional method
	 */
	public void setInstructionalMethod(String instructionalMethod) {
		this.instructionalMethod = instructionalMethod;
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
	 * @param ownerId the eigentuemer id
	 */
	public void setEigentuemerId(int ownerId) {
		this.ownerId = ownerId;
	}

	/** The text id. */
	private int textId = 0;

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
	 * @param textId the text id
	 */
	public void setTextId(int textId) {
		this.textId = textId;
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
	 * @param generationDate the generation date
	 */
	public void setGenerationDate(String generationDate) {
		this.generationDate = generationDate;
	}

	/** The openaccess. */
	private boolean openaccess = false;

	/**
	 * Checks if is openaccess.
	 *
	 * @return true, if checks if is openaccess
	 */
	public boolean isOpenaccess() {
		return openaccess;
	}

	/**
	 * Sets the openaccess.
	 *
	 * @param openaccess the openaccess
	 */
	public void setOpenaccess(boolean openaccess) {
		this.openaccess = openaccess;
	}

	/** The file size. */
	private long fileSize;

	/**
	 * Gets the file size.
	 *
	 * @return the file size
	 */
	public long getFileSize() {
		return fileSize;
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
	 * @param date the date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/** The url. */
	private String url = "";

	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/** The multipart file. */
	private String multipartFile = "";

	/**
	 * Gets the multipart file.
	 *
	 * @return the multipart file
	 */
	public String getMultipartFile() {
		return multipartFile;
	}

	/**
	 * Sets the multipart file.
	 *
	 * @param multipartFile the multipart file
	 */
	public void setMultipartFile(String multipartFile) {
		this.multipartFile = multipartFile;
	}

	/**
	 * Sets the file size.
	 *
	 * @param fileSize the file size
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/** The has metadata. */
	private boolean hasMetadata;

	/**
	 * Checks if is has metadata.
	 *
	 * @return true, if checks if is has metadata
	 */
	public boolean isHasMetadata() {
		return hasMetadata;
	}

	/**
	 * Sets the has metadata.
	 *
	 * @param hasMetadata the checks for metadata
	 */
	public void setHasMetadata(boolean hasMetadata) {
		this.hasMetadata = hasMetadata;
	}

	/* (non-Javadoc)
	 * @see de.uhh.l2g.model.StudentModel#setDuration(java.lang.String)
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Sets the contact file.
	 *
	 * @param contactFile the contact file
	 */
	public void setContactFile(CommonsMultipartFile contactFile) {
		this.contactFile = contactFile;
	}
	
}
