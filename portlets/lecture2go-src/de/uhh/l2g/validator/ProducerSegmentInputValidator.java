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
package de.uhh.l2g.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import de.uhh.l2g.model.ProducerSegmentInputModel;

/**
 * The Class ProducerSegmentInputValidator.
 */
public class ProducerSegmentInputValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	/**
	 * Supports.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	@SuppressWarnings("rawtypes")
	public boolean supports(Class c) {
		return c.equals(ProducerSegmentInputModel.class);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	/**
	 * Validate.
	 *
	 * @param o the o
	 * @param errors the errors
	 */
	public void validate(Object o, Errors errors) {
		ProducerSegmentInputModel hw = (ProducerSegmentInputModel) o;
		try {
			String fw = hw.getPortletRequest().getParameter("forward");

			if (hw.getAction().equals("create") && fw.equals("false")) {
				if (!hw.isChapter())if (hw.getDescription().equals("")) errors.rejectValue("description", "error.invalid.description", "(!)");
				if (hw.getTimeStart().equals("")) errors.rejectValue("timeStart", "error.invalid.timeStart", "(!)");
				if (hw.getTimeEnd().equals("")) errors.rejectValue("timeEnd", "error.invalid.timeEnd", "(!)");

				if (!hw.getTimeStart().equals("") && !hw.getTimeEnd().equals("")) {
					try {
						
						int starttime = new Integer(hw.getTimeStart().split(":")[0])*60 * 60 + new Integer(hw.getTimeStart().split(":")[1])*60 + new Integer(hw.getTimeStart().split(":")[2]);
						int endtime = new Integer(hw.getTimeEnd().split(":")[0])*60 * 60 + new Integer(hw.getTimeEnd().split(":")[1])*60 + new Integer(hw.getTimeEnd().split(":")[2]);

						if (starttime > endtime) {
							if (hw.getTimeStart().equals("")) errors.rejectValue("timeStart", "error.invalid.timeStart", "(!)");
							if (hw.getTimeEnd().equals("")) errors.rejectValue("timeEnd", "error.invalid.timeEnd", "(!)");
						}

					} catch (NumberFormatException nfe) {
						if (hw.getTimeStart().equals("")) errors.rejectValue("timeStart", "error.invalid.timeStart", "(!)");
						if (hw.getTimeEnd().equals("")) errors.rejectValue("timeEnd", "error.invalid.timeEnd", "(!)");
					}
				}
				if (hw.getTitle() == "") errors.rejectValue("title", "error.invalid.title", "(!)");
			}
		} catch (NullPointerException ne) {
			//
		}
	}

}
