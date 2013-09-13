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

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import de.uhh.l2g.model.CoordinatorMetaDataModel;

/**
 * The Class CoordinatorMetaDataValidator.
 */
public class CoordinatorMetaDataValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public boolean supports(Class c) {
		return c.equals(CoordinatorMetaDataModel.class);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object o, Errors errors) {
		CoordinatorMetaDataModel hw = (CoordinatorMetaDataModel) o;
		if (hw.getAction().equals("editMetadata")){
			if (hw.getTitle().trim().equals("")) errors.rejectValue("title", "error.invalid.title", "(!)");
			if (hw.getCreator().trim().equals("")) errors.rejectValue("creator", "error.invalid.creator", "(!)");
			if (hw.getRightsHolder().trim().equals("")) errors.rejectValue("rightsHolder", "error.invalid.rightsHolder", "(!)");
			if (hw.getPublisher().trim().equals("")) errors.rejectValue("publisher", "error.invalid.publisher", "(!)");			
		}
	}

}
