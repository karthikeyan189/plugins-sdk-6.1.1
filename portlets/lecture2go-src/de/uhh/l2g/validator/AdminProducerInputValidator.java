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
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import de.uhh.l2g.model.AdminProducerInputModel;


/**
 * The Class AdminProducerInputValidator.
 */
public class AdminProducerInputValidator implements Validator {

	/** The required error message. */
	private static String requiredErrorMessage = "(!)";

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
		return c.equals(AdminProducerInputModel.class);
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
		AdminProducerInputModel model = (AdminProducerInputModel) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "errors.required", requiredErrorMessage);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "facilityId", "errors.required", requiredErrorMessage);

		Integer facilityId = model.getFacilityId();
		if (facilityId == null) errors.rejectValue("facilityId", "errors.facilityId.format", "Ungueltiges Format");
	}

}
