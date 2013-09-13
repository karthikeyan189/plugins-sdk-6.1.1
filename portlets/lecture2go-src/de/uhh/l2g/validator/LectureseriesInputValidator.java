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
package de.uhh.l2g.validator;

import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import de.uhh.l2g.model.AdminLectureseriesInputModel;

/**
 * The Class LectureseriesInputValidator.
 */
public class LectureseriesInputValidator implements Validator {

	/** The number message. */
	private static String numberMessage = "(!)";

	/** The required error message. */
	private static String requiredErrorMessage = "(!)";

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public boolean supports(Class c) {
		return c.equals(AdminLectureseriesInputModel.class);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object o, Errors errors) {
		AdminLectureseriesInputModel model = (AdminLectureseriesInputModel) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lectureseriesNumber", "errors.required", numberMessage);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lectureseriesName", "errors.required", requiredErrorMessage);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eventType", "errors.required", requiredErrorMessage);

		if (model.getFaculties().isEmpty()) {
			if (model.getFacilityName().equals("")) errors.rejectValue("faculties", "errors.required", requiredErrorMessage);
		}

		String shortDescription = model.getShortDescription();
		if (shortDescription != null && shortDescription.length() > 1000) errors.rejectValue("shortDescription", "errors.shortDescription.length", "Der Text soll kuerzer als 1000 Zeichen sein!");

		String instructors = model.getInstructorNames();
		if (instructors != null && instructors.length() > 1000) errors.rejectValue("instructorNames", "error.instructorNames.length", "Der Text soll kuerzer als 1000 Zeichen sein!");

		String password = model.getPassword();
		if (password != null && password.length() > 50) errors.rejectValue("password", "errors.password.length", "Der Text soll kuerzer als 50 Zeichen sein!");

		Map<String, String> lang = model.getLanguages();
		if (lang.size()==0) errors.rejectValue("languages", "errors.required", requiredErrorMessage);
	}
}
