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
package de.uhh.l2g.upload;

import java.util.List;

import javax.portlet.ActionRequest;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.portlet.PortletFileUpload;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.portlet.multipart.CommonsPortletMultipartResolver;

import de.uhh.l2g.dao.UploadDao;


/**
 * The Class MyCommonsPortletMultipartResolver.
 */
public class MyCommonsPortletMultipartResolver extends CommonsPortletMultipartResolver {

	/** The dao bean factory. */
	private XmlBeanFactory daoBeanFactory;
	
	/**
	 * Gets the dao bean factory.
	 *
	 * @return the dao bean factory
	 */
	public XmlBeanFactory getDaoBeanFactory() {
		return daoBeanFactory;
	}

	/**
	 * Sets the dao bean factory.
	 *
	 * @param beanFactory the new dao bean factory
	 */
	public void setDaoBeanFactory(XmlBeanFactory beanFactory) {
		this.daoBeanFactory = beanFactory;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.multipart.CommonsPortletMultipartResolver#parseRequest(javax.portlet.ActionRequest)
	 */
	/**
	 * Parses the request.
	 *
	 * @param request the request
	 * @return the multipart parsing result
	 * @throws MultipartException the multipart exception
	 */
	@Override
	protected MultipartParsingResult parseRequest(ActionRequest request) throws MultipartException {

		Integer userId = new Integer(request.getRemoteUser());
		Integer videoId = new Integer(request.getParameter("videoId"));
		long contentLength = request.getContentLength();
		((UploadDao) getDaoBeanFactory().getBean("uploadDao")).create(userId, contentLength);

		String encoding = determineEncoding(request);
		FileUpload fileUpload = this.prepareFileUpload(encoding);

		try {
			PortletFileUpload pfu = ((PortletFileUpload) fileUpload);
			FileUploadProgressListener fupl = new FileUploadProgressListener(videoId, contentLength);
			pfu.setProgressListener(fupl);
			List<?> fileItems = pfu.parseRequest(request);
			
			return parseFileItems(fileItems, encoding);
		} catch (FileUploadBase.SizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
		} catch (FileUploadException ex) {
			throw new MultipartException("Could not parse multipart portlet request", ex);
		}

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.multipart.commons.CommonsFileUploadSupport#prepareFileUpload(java.lang.String)
	 */
	/**
	 * Prepare file upload.
	 *
	 * @param encoding the encoding
	 * @return the file upload
	 */
	@Override
	protected FileUpload prepareFileUpload(String encoding) {
		FileUpload fileUpload = getFileUpload();
		FileUpload actualFileUpload = fileUpload;
		// Use new temporary FileUpload instance if the request specifies
		// its own encoding that does not match the default encoding.
		if (encoding != null && !encoding.equals(fileUpload.getHeaderEncoding())) {
			actualFileUpload = newFileUpload(getFileItemFactory());
			actualFileUpload.setSizeMax(fileUpload.getSizeMax());
			actualFileUpload.setHeaderEncoding(encoding);
		}

		return actualFileUpload;
	}

}
