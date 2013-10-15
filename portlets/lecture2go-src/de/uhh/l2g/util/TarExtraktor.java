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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

/**
 * The Class TarExtraktor.
 */
public class TarExtraktor {

	/**
	 * Untar files.
	 *
	 * @param tarFileName the tar file name
	 * @param dest the dest
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void untarFiles(String tarFileName, File dest) throws IOException {
		// assuming the file you pass in is not a dir
		dest.mkdir();
		// create tar input stream from a .tar.gz file
		TarInputStream tin = new TarInputStream(new FileInputStream(new File(tarFileName)));

		// get the first entry in the archive
		TarEntry tarEntry = tin.getNextEntry();
		while (tarEntry != null) {
			// create a file with the same name as the tarEntry
			File destPath = new File(dest.toString() + File.separatorChar + tarEntry.getName());
			if (tarEntry.isDirectory()) {
				destPath.mkdir();
			} else {
				FileOutputStream fout = new FileOutputStream(destPath);
				tin.copyEntryContents(fout);
				fout.close();
			}
			tarEntry = tin.getNextEntry();
		}
		tin.close();
	}
}
