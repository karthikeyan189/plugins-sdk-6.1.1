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

import java.util.Map;

import com.liferay.faces.bridge.container.liferay.LiferayPortletURL;

/**
 * The Class SearchFriendlyURLMapper.
 */
public class SearchFriendlyURLMapper extends BaseFriendlyURLMapper {

	/** The Constant _MAPPING. */
	private static final String _MAPPING = "s";

	/** The Constant _PORTLET_ID. */
	private static final String _PORTLET_ID = "searchVideoDataShow_WAR_lecture2goproducerspringportlet";

	/* (non-Javadoc)
	 * @see com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper#getPortletId()
	 */
	@Override
	public String getPortletId() {
		return _PORTLET_ID;
	}

	/* (non-Javadoc)
	 * @see com.liferay.portal.kernel.portlet.FriendlyURLMapper#buildPath(com.liferay.portal.kernel.portlet.LiferayPortletURL)
	 */
	public String buildPath(LiferayPortletURL portletURL) {
		String friendlyURLPath = null;
		String videoId = portletURL.getParameter("videoId");

		// videoId
		if (videoId != null && !videoId.equals("")) {
			friendlyURLPath = "/s/" + videoId;
		}

		if (Validator.isNotNull(friendlyURLPath)) {
			portletURL.addParameterIncludedInPath("p_p_id");
			portletURL.addParameterIncludedInPath("p_p_lifecycle");
			portletURL.addParameterIncludedInPath("p_p_state");
			portletURL.addParameterIncludedInPath("p_p_mode");
			portletURL.addParameterIncludedInPath("p_p_col_id");
			portletURL.addParameterIncludedInPath("p_p_col_count");
			portletURL.addParameterIncludedInPath("videoId");
			portletURL.addParameterIncludedInPath("method");

			portletURL.addParameterIncludedInPath("facultyId");
			portletURL.addParameterIncludedInPath("subFacility1Id");
			portletURL.addParameterIncludedInPath("subFacility2Id");
			portletURL.addParameterIncludedInPath("lectureseriesId");
		}

		return friendlyURLPath;
	}

	/* (non-Javadoc)
	 * @see com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper#getMapping()
	 */
	public String getMapping() {
		return _MAPPING;
	}

	/* (non-Javadoc)
	 * @see com.liferay.portal.kernel.portlet.FriendlyURLMapper#populateParams(java.lang.String, java.util.Map, java.util.Map)
	 */
	public void populateParams(String friendlyURLPath, Map<String, String[]> params,  Map<String, Object> arg2) {
		String[] s = friendlyURLPath.split("/");

		addParam(params, "p_p_id", _PORTLET_ID);
		addParam(params, "p_p_lifecycle", "0");
		addParam(params, "p_p_state", "normal");
		addParam(params, "p_p_mode", PortletMode.EDIT);
		addParam(params, "p_p_col_id", "column-1");
		addParam(params, "p_p_col_count", "1");
		addParam(params, "videoId", s[2]);
		addParam(params, "method", "get");
	}

}
