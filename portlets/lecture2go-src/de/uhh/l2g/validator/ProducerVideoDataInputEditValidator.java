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

import java.util.Calendar;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import de.uhh.l2g.model.ProducerVideoDataInputEditModel;

/**
 * The Class ProducerVideoDataInputEditValidator.
 */
public class ProducerVideoDataInputEditValidator implements Validator {
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public boolean supports(Class c) {
		return c.equals(ProducerVideoDataInputEditModel.class);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object o, Errors errors) {
		ProducerVideoDataInputEditModel hw = (ProducerVideoDataInputEditModel) o;

		// validate data only, if delete request is false
		if (!hw.isDeleteVideoRequest()) {
			// get the original file name
			String originalFilename = "";
			try {
				originalFilename = hw.getContactFile().getOriginalFilename();
			} catch (NullPointerException e) {
				//
			}
			// if no video loaded
			if (hw.isFirstVideoUpload()) {
				// validate data
				if (originalFilename == "") {
					errors.rejectValue("contactFile", "error.invalid.contactFile", "1");
				} else {
					// the user has chosen a video file
					// then validate the name-parameter
					// the file-name has to hold the following parameter
					// separated by _ (underline)
					// example: 41-50.181_mustermann_2009-03-22_12-33 <->
					// int-int.int_String_int-int-int_int-int.String
					// has to be a mp4 or mp3 or tar file for the firs upload

					String[] parameter = originalFilename.split("\\_");

					// the parameter array length has to be 4
					if (parameter.length != 4) {
						String fExt = originalFilename.split("\\.")[parameter.length].toLowerCase();
						if ((!fExt.equalsIgnoreCase("mp4") && !fExt.equalsIgnoreCase("tar") && !fExt.equalsIgnoreCase("mp3")))
							errors.rejectValue("contactFile", "error.invalid.contactFile", "3");
						//forward to controller to rename this file correctly like 00.000_name_2001-03-03_13-45.mp4
					} else {
						// the upload-file has to end on 'mp4' or 'mp3' or 'm4v'
						// or flv
						String parameter4 = parameter[3];
						//to lower case
						String dateiEndung = parameter4.split("\\.")[1].toLowerCase();
						
						if ((dateiEndung.equalsIgnoreCase("mp4") || dateiEndung.equalsIgnoreCase("tar") || dateiEndung.equalsIgnoreCase("mp3")) && parameter4.split("\\.").length == 2) {
							checkDateAndTime(parameter, errors, hw);
							// and proceed
						} else {
							// format rejected
							errors.rejectValue("contactFile", "error.invalid.contactFile", "3");
						}
					}
				}
				// upload m4v, pdf, m4a, pdf, mp3
			} else {
				if (hw.isActionRequest()) {
					// proceed! action
				} else {
					// the user has chosen a file
					// then validate the name-parameter
					// the file-name has to hold the following parameter
					// separated by _ (underline)
					// example: 41-50.181_mustermann_2009-03-22_12-33 <->
					// int-int.int_String_int-int-int_int-int.String
					String[] parameter = originalFilename.split("\\.");

					// the upload-file has to end on 'mp4' or 'mp3' or 'm4v' or
					// flv
					int l = parameter.length;
					l--;
					String dateiEndung = parameter[l];
//					int t = 0;
//					t++;
					if ((dateiEndung.equalsIgnoreCase("mp4") || dateiEndung.equalsIgnoreCase("m4v") || dateiEndung.equalsIgnoreCase("pdf") || dateiEndung.equalsIgnoreCase("mp3") || dateiEndung.equalsIgnoreCase("m4a") || dateiEndung.equalsIgnoreCase("xml"))) {
						// proceed
					} else {
						 errors.rejectValue("contactFile", "error.invalid.contactFile", "4");
					}
				}
			}
		}
	}

	/**
	 * Check date and time.
	 *
	 * @param parameter the parameter
	 * @param errors the errors
	 * @param hw the hw
	 */
	protected void checkDateAndTime(String[] parameter, Errors errors, ProducerVideoDataInputEditModel hw) {
		Calendar cal = Calendar.getInstance();

		int year = 0;
		int month = 0;
		int date = 0;
		int hours = 0;
		int minutes = 0;

		// get the original file name
		@SuppressWarnings("unused")
		String originalFilename = "";
		try {
			originalFilename = hw.getContactFile().getOriginalFilename();
		} catch (NullPointerException e) {
			//
		}

		// check parameter 3 - this is the date
		String parameter4 = parameter[3];
		String l2gDate = parameter[2];
		// and parameter 4 - this is the time
		String l2gTime = parameter4.split("\\.")[0];

		try {
			year = Integer.parseInt(l2gDate.split("\\-")[0]);
			month = Integer.parseInt(l2gDate.split("\\-")[1]);
			date = Integer.parseInt(l2gDate.split("\\-")[2]);
			hours = Integer.parseInt(l2gTime.split("\\-")[0]);
			minutes = Integer.parseInt(l2gTime.split("\\-")[1]);
		} catch (NumberFormatException ne) {
			errors.rejectValue("contactFile", "error.invalid.contactFile", "5");
		}

		// validate all time parameter
		// date
		if (1 > date || date > 31) errors.rejectValue("contactFile", "error.invalid.contactFile", "6");
		// month
		if (1 > month || month > 12) errors.rejectValue("contactFile", "error.invalid.contactFile", "7");
		// year
		if (1970 > year || year > (cal.get(Calendar.YEAR))) errors.rejectValue("contactFile", "error.invalid.contactFile", "8");
		// hours
		if (0 > hours || hours > 23) errors.rejectValue("contactFile", "error.invalid.contactFile", "9");
		// minutes
		if (0 > minutes || minutes > 59) errors.rejectValue("contactFile", "error.invalid.contactFile", "10");

	}
}
