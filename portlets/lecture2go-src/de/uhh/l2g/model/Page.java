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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The Class Page.
 */
public class Page extends Objects {

	/** The portlet request. */
	private PortletRequest portletRequest;

	
	/**
	 * Gets the portlet request.
	 *
	 * @return the portlet request
	 */
	public PortletRequest getPortletRequest() {
		return portletRequest;
	}

	/**
	 * Sets the portlet request.
	 *
	 * @param portletRequest the portlet request
	 */
	public void setPortletRequest(PortletRequest portletRequest) {
		this.portletRequest = portletRequest;
	}

	/** The current seite. */
	private int currentSeite;
	
	/**
	 * Gets the current seite.
	 *
	 * @return the current seite
	 */
	public int getCurrentSeite() {
		return currentSeite;
	}

	/**
	 * Sets the current seite.
	 *
	 * @param currentSeite the current seite
	 */
	public void setCurrentSeite(int currentSeite) {
		this.currentSeite = currentSeite;
	}

	/** The page size. */
	private int pageSize;

	/** The number result pages. */
	private int numberResultPages;

	/** The current page number. */
	private int currentPageNumber;

	/** The page range first. */
	private int pageRangeFirst;

	/** The page range last. */
	private int pageRangeLast;

	/** The has next. */
	private boolean hasNext;

	/** The has prev. */
	private boolean hasPrev;

	/** The filters. */
	private Map<String, String> filters;

	/**
	 * The Constructor.
	 */
	public Page() {
		filters = new HashMap<String, String>();
	}

	/**
	 * Adds the filter.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void addFilter(String key, String value) {
		filters.put(key, value);
	}

	/**
	 * Sets the number result pages.
	 *
	 * @param numberResultPages the number result pages
	 */
	public void setNumberResultPages(int numberResultPages) {
		this.numberResultPages = numberResultPages;
	}

	/**
	 * Gets the number result pages.
	 *
	 * @return the number result pages
	 */
	public int getNumberResultPages() {
		return numberResultPages;
	}

	/**
	 * Sets the current page number.
	 *
	 * @param currentPageNumber the current page number
	 */
	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	/**
	 * Gets the current page number.
	 *
	 * @return the current page number
	 */
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	/**
	 * Sets the page range first.
	 *
	 * @param pageRangeFirst the page range first
	 */
	public void setPageRangeFirst(int pageRangeFirst) {
		this.pageRangeFirst = pageRangeFirst;
	}

	/**
	 * Gets the page range first.
	 *
	 * @return the page range first
	 */
	public int getPageRangeFirst() {
		return pageRangeFirst;
	}

	/**
	 * Sets the page range last.
	 *
	 * @param pageRangeLast the page range last
	 */
	public void setPageRangeLast(int pageRangeLast) {
		this.pageRangeLast = pageRangeLast;
	}

	/**
	 * Gets the page range last.
	 *
	 * @return the page range last
	 */
	public int getPageRangeLast() {
		return pageRangeLast;
	}

	/**
	 * Sets the has next.
	 *
	 * @param hasNext the checks for next
	 */
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	/**
	 * Checks if is has next.
	 *
	 * @return true, if checks if is has next
	 */
	public boolean isHasNext() {
		return hasNext;
	}

	/**
	 * Sets the has prev.
	 *
	 * @param hasPrev the checks for prev
	 */
	public void setHasPrev(boolean hasPrev) {
		this.hasPrev = hasPrev;
	}

	/**
	 * Checks if is has prev.
	 *
	 * @return true, if checks if is has prev
	 */
	public boolean isHasPrev() {
		return hasPrev;
	}

	/**
	 * Sets the page size.
	 *
	 * @param pageSize the page size
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * Gets the page size.
	 *
	 * @return the page size
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Gets the filters.
	 *
	 * @return the filters
	 */
	public Map<String, String> getFilters() {
		return filters;
	}

	/**
	 * Sets the filters.
	 *
	 * @param filters the filters
	 */
	public void setFilters(Map<String, String> filters) {
		this.filters = filters;
	}

	/** The remote user id. */
	private Integer remoteUserId;


	/**
	 * Gets the remote user id.
	 *
	 * @return the remote user id
	 */
	public Integer getRemoteUserId() {
		return remoteUserId;
	}

	/**
	 * Sets the remote user id.
	 *
	 * @param remoteUserId the remote user id
	 */
	public void setRemoteUserId(Integer remoteUserId) {
		this.remoteUserId = remoteUserId;
	}

	/** The url id. */
	private String urlId = "";


	/**
	 * Gets the url id.
	 *
	 * @return the url id
	 */
	public String getUrlId() {
		return urlId;
	}

	/**
	 * Sets the url id.
	 *
	 * @param urlId the url id
	 */
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}

	/** The number of pages. */
	private List<?> numberOfPages;


	/**
	 * Gets the number of pages.
	 *
	 * @return the number of pages
	 */
	public List<?> getNumberOfPages() {
		return numberOfPages;
	}

	/**
	 * Sets the number of pages.
	 *
	 * @param numberOfPages the number of pages
	 */
	public void setNumberOfPages(List<?> numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	/** The action request. */
	private boolean actionRequest;


	/**
	 * Checks if is action request.
	 *
	 * @return true, if checks if is action request
	 */
	public boolean isActionRequest() {
		return actionRequest;
	}

	/**
	 * Sets the action request.
	 *
	 * @param actionRequest the action request
	 */
	public void setActionRequest(boolean actionRequest) {
		this.actionRequest = actionRequest;
	}
}
