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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import de.uhh.l2g.beans.Factory;
import de.uhh.l2g.beans.Host;
import de.uhh.l2g.beans.Producer;
import de.uhh.l2g.dao.HostDao;
import de.uhh.l2g.dao.ProducerDao;

/**
 * The Class RepositoryManager.
 */
public class RepositoryManager extends Factory{
	
	//get runtime
	/** The run cmd. */
	static Runtime runCmd = Runtime.getRuntime();
	
	/**
	 * Creates the forlder.
	 *
	 * @param path the path
	 * @throws IOException the IO exception
	 */
	public static void createForlder(String path) throws IOException{
		File folder = new File(path);
		if(folder.mkdirs()){
			String[] cmdArray = {L2goPropsUtil.get("lecture2go.shell.bin"), "-cr", "chown nobody " + folder.getAbsolutePath() };
			runCmd.exec(cmdArray);
			String[] cmdArray1 = { L2goPropsUtil.get("lecture2go.shell.bin"), "-cr", "chown nobody:nobody " + folder.getAbsolutePath() };
			runCmd.exec(cmdArray1);
			String[] cmdArray2 = { L2goPropsUtil.get("lecture2go.shell.bin"), "-cr", "chmod 701 " + folder.getAbsolutePath() };
			runCmd.exec(cmdArray2);
		}
	}

	/**
	 * Creates the repository.
	 *
	 * @param hostDao the host dao
	 * @param producerDao the producer dao
	 * @throws IOException the IO exception
	 */
	public static void createRepository(HostDao hostDao, ProducerDao producerDao) throws IOException{
		File mediaRep = new File(L2goPropsUtil.get("lecture2go.media.repository"));
		if(!mediaRep.isDirectory()){
			createForlder(L2goPropsUtil.get("lecture2go.media.repository"));
			createForlder(L2goPropsUtil.get("lecture2go.images.system.path"));
			createForlder(L2goPropsUtil.get("lecture2go.media.repository")+"/abo");
			createForlder(L2goPropsUtil.get("lecture2go.images.system.path"));
			createForlder(L2goPropsUtil.get("lecture2go.security.folder"));
			createVHosts(hostDao, producerDao);
			symlinkToImagesHome();
			symlinkToAboHome();
		}
	}
	
	/**
	 * Repository exists.
	 *
	 * @return true, if repository exists
	 */
	public static boolean repositoryExists(){
		boolean exists = false;

		File mediaRep = new File(L2goPropsUtil.get("lecture2go.media.repository"));
		
		File aboRep = new File(L2goPropsUtil.get("lecture2go.media.repository")+"/abo");
		File imagesRep = new File(L2goPropsUtil.get("lecture2go.images.system.path"));
		File securityRep = new File(L2goPropsUtil.get("lecture2go.security.folder"));
		
		if(mediaRep.isDirectory() && aboRep.isDirectory() && imagesRep.isDirectory() && securityRep.isDirectory())exists = true;
		
		return exists;
	}

	/**
	 * Symlink to abo home.
	 */
	public static void symlinkToAboHome(){
		File aboFolder = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + "abo");
		if (aboFolder.exists()) {
			String cmd = "ln -s " + aboFolder.getAbsolutePath() + " " + System.getProperty("catalina.base") + "/" + "webapps" + "/" + "abo";
			try { runCmd.exec(cmd); } catch (IOException e) {}
		}		
	}

	/**
	 * Symlink to images home.
	 */
	public static void symlinkToImagesHome(){
		File imgFolder = new File(L2goPropsUtil.get("lecture2go.images.system.path"));
		if (imgFolder.exists()) {
			String cmd = "ln -s " + imgFolder.getAbsolutePath() + " " + System.getProperty("catalina.base") + "/" + "webapps" + "/" + "images";
			try { runCmd.exec(cmd); } catch (IOException e) {}
		}		
	}
	
	/**
	 * Symlink to user home.
	 *
	 * @param host the host
	 * @param producer the producer
	 */
	public static void symlinkToUserHome(Host host, Producer producer){
		File folder = new File(L2goPropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/");
		File httpFolder = new File(L2goPropsUtil.get("lecture2go.httpstreaming.video.repository") + "/" + producer.getFacilityId() + "l2g" + producer.getHomeDir());
		if (!httpFolder.exists()) {
			String cmd = "ln -s " + folder.getAbsolutePath() + " " + httpFolder.getAbsolutePath();
			try { runCmd.exec(cmd); } catch (IOException e) {}
		}		
	}
	
	/**
	 * Creates the v hosts.
	 *
	 * @param hostDao the host dao
	 * @param producerDao the producer dao
	 * @throws IOException the IO exception
	 */
	public static void createVHosts(HostDao hostDao, ProducerDao producerDao) throws IOException{
		ArrayList<Host> hosts = (ArrayList<Host>) hostDao.getAll();
		ListIterator<Host> i = hosts.listIterator();
		//create httpsreaming.video.repository
		createForlder(L2goPropsUtil.get("lecture2go.httpstreaming.video.repository"));
		
		//then vhost directories
		while(i.hasNext()){
			Host h = i.next();
			List<Producer> pL = producerDao.getByHostId(h.getId());
			//create host
			createForlder(L2goPropsUtil.get("lecture2go.media.repository")+"/"+h.getServerRoot());
			//and user directories 
			ListIterator<Producer> iP = pL.listIterator();
			while(iP.hasNext()){
				Producer p = iP.next();
				createForlder(L2goPropsUtil.get("lecture2go.media.repository")+"/"+h.getServerRoot()+"/"+p.getHomeDir());
				//create symbolic link to required directory
				symlinkToUserHome(h, p);
			}
		}
	}
}
