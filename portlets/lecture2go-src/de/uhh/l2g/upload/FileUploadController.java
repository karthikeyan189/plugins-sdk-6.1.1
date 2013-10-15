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
package de.uhh.l2g.upload;

import java.io.File;
import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.mvc.SimpleFormController;

/**
 * The Class FileUploadController.
 */
public class FileUploadController extends SimpleFormController {

	/** The model. */
	private FileUploadBean model = new FileUploadBean();

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.SimpleFormController#onSubmitAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void onSubmitAction(ActionRequest request, ActionResponse response, Object command, BindException errors) {
		ActionRequest req = request;
		ActionResponse res = response;
		doUpload(req, res, command);
	}

	/**
	 * Do upload.
	 *
	 * @param request the request
	 * @param response the response
	 * @param command the command
	 */
	private void doUpload(ActionRequest request, ActionResponse response, Object command) {
		// upload this file to the user home desitation
		try {

			// pick the multipart file content data
			MultipartFile file = model.getFile();
			// get the original file name
			String originalFileName = file.getOriginalFilename();

			// upload ############
			// if mp4 upload
			String[] p = originalFileName.split("\\.");

			// the upload-file has to end on 'mp4' or 'mp3' or 'm4v' or flv
			int l = p.length;
			l--;

			// UPLOAD this file to the user home desitation
			File destination = new File("/Users/isturm/Desktop/rep/" + file.getOriginalFilename());
			file.transferTo(destination);

		} catch (IllegalStateException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		// set current seite for browsing!
		@SuppressWarnings("unused")
		int currentSeite = 1;
		try {
			currentSeite = new Integer(request.getParameter("videoSeite"));
		} catch (Exception e) {
			// nothing to do
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractFormController#formBackingObject(javax.portlet.PortletRequest)
	 */
	@Override
	protected Object formBackingObject(PortletRequest request) throws Exception {
		return model;
	}

}
