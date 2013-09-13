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

import java.util.Stack;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

/**
 * The Class StaxToXPathProperties.
 */
public class StaxToXPathProperties {

	/**
	 * The Constructor.
	 */
	public StaxToXPathProperties() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Xml2array.
	 *
	 * @param args the args
	 * @return the string[][]
	 * @throws XMLStreamException the XML stream exception
	 */
	public String[][] xml2array(String[] args) throws XMLStreamException {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLInputFactory inputFactory1 = XMLInputFactory.newInstance();
		XMLEventReader evRd = inputFactory.createXMLEventReader(new StreamSource(args[0]));
		XMLEventReader evRd1 = inputFactory1.createXMLEventReader(new StreamSource(args[0]));
		Stack<String> stck = new Stack<String>();
		Stack<String> stck1 = new Stack<String>();

		int z = 0;
		// read out the number of array elements
		while (evRd1.hasNext()) {
			XMLEvent ev1 = evRd1.nextEvent();
			if (ev1.isStartElement()) {
				stck1.push(ev1.asStartElement().getName().getLocalPart());
			}
			if (ev1.isCharacters()) {
				String s1 = ev1.asCharacters().getData();
				if (s1.trim().length() > 0) {
					String[] arr = stck1.toString().split(",");
					if (arr[2].trim().equals("Timecode]")) z++;
				}
			}
			if (ev1.isEndElement()) stck1.pop();
		}

		String[][] xmlArr = new String[z][2];

		int z1 = 0;
		int z2 = 0;

		while (evRd.hasNext()) {
			XMLEvent ev = evRd.nextEvent();
			if (ev.isStartElement()) {
				stck.push(ev.asStartElement().getName().getLocalPart());
			}
			if (ev.isCharacters()) {
				String s = ev.asCharacters().getData();
				if (s.trim().length() > 0) {
					String[] arr = stck.toString().split(",");
					if (arr[2].trim().equals("Title]")) {
						xmlArr[z1][0] = s;
						z2++;
					}
					if (arr[2].trim().equals("Timecode]")) {
						xmlArr[z1][1] = s;
						z2++;
					}
					if (z2 == 2) {
						z1++;
						z2 = 0;
					}
				}
			}
			if (ev.isEndElement()) stck.pop();
		}
		return xmlArr;
	}

}
