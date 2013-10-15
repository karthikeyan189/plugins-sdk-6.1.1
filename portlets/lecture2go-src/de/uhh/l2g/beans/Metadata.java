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


/**
 * The Class Metadata.
 */
public class Metadata {

	/** The urlid. */
	private String urlid = "";

	/**
	 * Gets the filename id.
	 *
	 * @return the filename id
	 */
	public String getFilenameID() {
		return urlid;
	}

	/**
	 * Sets the url id.
	 *
	 * @param urlid the new url id
	 */
	public void setUrlId(String urlid) {
		this.urlid = urlid;
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
	 * @param format the new format
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
	 * @param type the new type
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
	 * @param language the new language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/** The title. */
	private String title = "";

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
	 * @param subject the new subject
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
	 * @param coverage the new coverage
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
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** The long description. */
	private String longDescription = "";

	/**
	 * Gets the long description.
	 *
	 * @return the long description
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * Sets the long description.
	 *
	 * @param longdescription the new long description
	 */
	public void setLongDescription(String longdescription) {
		this.longDescription = longdescription;
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
	 * @param creator the new creator
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
	 * @param publisher the new publisher
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
	 * @param contributor the new contributor
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
	 * @param rightsHolder the new rights holder
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
	 * @param rights the new rights
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
	 * @param provenance the new provenance
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
	 * @param source the new source
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
	 * @param relation the new relation
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
	 * @param audience the new audience
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
	 * @param instructionalMethod the new instructional method
	 */
	public void setInstructionalMethod(String instructionalMethod) {
		this.instructionalMethod = instructionalMethod;
	}

	/** The date. */
	private String date;

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
	 * @param string the new date
	 */
	public void setDate(String string) {
		this.date = string;
	}

	/** The id. */
	private int id;

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

}
