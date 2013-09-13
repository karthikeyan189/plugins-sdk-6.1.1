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

import java.util.List;

/**
 * The Class GuestSearchModel.
 */
public class GuestSearchModel extends GuestModel {

	/** The number of search results. */
	private int numberOfSearchResults;

	/**
	 * Gets the number of search results.
	 *
	 * @return the number of search results
	 */
	public int getNumberOfSearchResults() {
		return numberOfSearchResults;
	}

	/**
	 * Sets the number of search results.
	 *
	 * @param numberOfSearchResults the number of search results
	 */
	public void setNumberOfSearchResults(int numberOfSearchResults) {
		this.numberOfSearchResults = numberOfSearchResults;
	}

	/** The number of search results2. */
	private int numberOfSearchResults2;

	/**
	 * Gets the number of search results2.
	 *
	 * @return the number of search results2
	 */
	public int getNumberOfSearchResults2() {
		return numberOfSearchResults2;
	}

	/**
	 * Sets the number of search results2.
	 *
	 * @param numberOfSearchResults2 the number of search results2
	 */
	public void setNumberOfSearchResults2(int numberOfSearchResults2) {
		this.numberOfSearchResults2 = numberOfSearchResults2;
	}

	/** The more video search results. */
	private List<?> moreVideoSearchResults;

	/**
	 * Gets the more video search results.
	 *
	 * @return the more video search results
	 */
	public List<?> getMoreVideoSearchResults() {
		return moreVideoSearchResults;
	}

	/**
	 * Sets the more video search results.
	 *
	 * @param moreVideoSearchResults the more video search results
	 */
	public void setMoreVideoSearchResults(List<?> moreVideoSearchResults) {
		this.moreVideoSearchResults = moreVideoSearchResults;
	}

	/** The search results limit. */
	private int searchResultsLimit;

	/**
	 * Gets the search results limit.
	 *
	 * @return the search results limit
	 */
	public int getSearchResultsLimit() {
		return searchResultsLimit;
	}

	/**
	 * Sets the search results limit.
	 *
	 * @param searchResultsLimit the search results limit
	 */
	public void setSearchResultsLimit(int searchResultsLimit) {
		this.searchResultsLimit = searchResultsLimit;
	}
}
