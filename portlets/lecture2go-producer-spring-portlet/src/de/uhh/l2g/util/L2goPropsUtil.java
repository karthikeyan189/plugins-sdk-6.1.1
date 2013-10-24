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

import com.liferay.portal.kernel.util.PortalClassInvoker;

/**
 * The Class L2goPropsUtil.
 */
public class L2goPropsUtil {

	/**
	 * Gets the.
	 *
	 * @param key the key
	 * @return the string
	 */
	public static String get(String key){
		Object returnObj = null;
		try {
			returnObj = PortalClassInvoker.invoke(_CLASS, _METHOD_GET, key, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (returnObj != null) {
			return (String)returnObj;
		}
		else {
			return null;
		}
	}

	/**
	 * Gets the array.
	 *
	 * @param key the key
	 * @return the array
	 */
	public static String[] getArray(String key) {
		Object returnObj = null;
		try {
			returnObj = PortalClassInvoker.invoke(_CLASS, _METHOD_GET_ARRAY, key, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (returnObj != null) {
			return (String[])returnObj;
		}
		else {
			return null;
		}
	}

	/** The Constant _CLASS. */
	private static final String _CLASS = "com.liferay.portal.util.PropsUtil";

	/** The Constant _METHOD_GET. */
	private static final String _METHOD_GET = "get";

	/** The Constant _METHOD_GET_ARRAY. */
	private static final String _METHOD_GET_ARRAY = "getArray";
}
