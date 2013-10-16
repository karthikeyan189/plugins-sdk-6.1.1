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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import de.uhh.l2g.beans.Lectureseries;
import de.uhh.l2g.beans.Video;

/**
 * The Class Htaccess.
 */
public class Htaccess {

	/** The Constant fileExtentions. */
	final static String[] fileExtentions = { ".pdf", ".mp3", ".m4v", ".mp4", ".m4a", ".tar" };
  
	/** The file ht users. */
	private String fileHtUsers = L2goPropsUtil.get("lecture2go.security.folder") + "/" + ".htusers"; 

	/**
	 * Gets the file ht users.
	 *
	 * @return the file ht users
	 */
	public String getFileHtUsers() {
		return fileHtUsers;
	}

	/**
	 * Sets the file ht users.
	 *
	 * @param fileHtUsers the new file ht users
	 */
	public void setFileHtUsers(String fileHtUsers) {
		this.fileHtUsers = fileHtUsers;
	}

	/**
	 * Instantiates a new htaccess.
	 */
	public Htaccess() {

	}

	/**
	 * Write pw.
	 *
	 * @param data the data
	 */
	public void writePW(List<Lectureseries> data) {

		BufferedWriter bufferedWriter = null;

		try {
			// Construct the BufferedWriter object
			bufferedWriter = new BufferedWriter(new FileWriter(fileHtUsers));

			// Start writing to the output stream
			for (Lectureseries lectureseries : data) {
				int id = lectureseries.getId();
				String password = lectureseries.getPassword();
				password = UnixCrypt.crypt(password);
				// write
				bufferedWriter.write(id + ":" + password);
				bufferedWriter.newLine();
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// Close the BufferedWriter
			try {
				if (bufferedWriter != null) {
					bufferedWriter.flush();
					bufferedWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Make htaccess.
	 *
	 * @param url the url
	 * @param lockedVideos the locked videos
	 */
	public void makeHtaccess(String url, List<Video> lockedVideos) {

		// Datei erstellen fuerr den ersten Run und wieder loeschen
		File file = new File(url + ".htaccess");
		file.delete();
		new File(url + "htaccess.txt").delete();

		// Und neu erstellen...
		file = new File(url + ".htaccess");

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			{
			}

			bw.write("# .htaccess-Datei zum Schutz der Files");
			bw.newLine();
			bw.write("AuthType Basic");
			bw.newLine();
			bw.write("AuthName 'Service-Bereich'");
			bw.newLine();
			// bw.write("AuthUserFile "+model.getRepositoryPfad().concat(
			// "/security/.htusers"));
			bw.write("AuthUserFile " + fileHtUsers);

			bw.newLine();
			bw.write("");

			for (Video video : lockedVideos) {
				if (video != null) {
					String videoUrl = "";
					String videoUrlS = "";
					int lectureseriesId = video.getLectureseriesId();

					if (video.getFilename() != null) {
						videoUrl = video.getFilename().substring(0, video.getFilename().lastIndexOf(46));
					}

					if (video.getSecureFilename() != null) {
						videoUrlS = video.getSecureFilename().substring(0, video.getSecureFilename().lastIndexOf(46));
					}

					if (videoUrl.length() > 10) {
						for (String extention : fileExtentions) {
							bw.write("<Files " + videoUrl + extention + ">");
							bw.newLine();
							bw.write("Require user " + lectureseriesId);
							bw.newLine();
							bw.write("</Files>");
							bw.newLine();

							bw.write("<Files " + videoUrlS + extention + ">");
							bw.newLine();
							bw.write("Require user " + lectureseriesId);
							bw.newLine();
							bw.write("</Files>");
							bw.newLine();
						}
					}
				}

			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
