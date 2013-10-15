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

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;

/**
 * The Class MailManager.
 */
public class EmailManager extends MailEngine {
	
	/**
	 * Send mail.
	 *
	 * @param from the from
	 * @param to comma separated email address list
	 * @param subject the subject
	 * @param body the body
	 * @return true, if send mail
	 */
	public boolean sendEmail(String from, String to, String subject, String body){
		boolean ret = false;
		InternetAddress fadss = null;
		InternetAddress[] tadss = null;
		InternetAddress[] cadss = null;
		//try to send 
		try {
			try {
				fadss =  new InternetAddress(from);
				tadss = InternetAddress.parse(to);
				cadss = InternetAddress.parse(from);
			} catch (AddressException e) {
				e.printStackTrace();
			}
			send(fadss, tadss, cadss, subject, body);
			ret = true;
		} catch (MailEngineException e) {
			e.printStackTrace();
		}
		//email successfully sent
		return ret;
	}
}
