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

import de.uhh.l2g.dao.VideoDao;

/**
 * The Class DatabaseThread.
 */
public final class DatabaseThread extends Thread {

	/** The should stop. */
	private boolean shouldStop = false;
	
	/**
	 * Should stop.
	 *
	 * @return true, if should stop
	 */
	public boolean shouldStop() {
		return shouldStop;
	}

	/**
	 * Sets the should stop.
	 *
	 * @param shouldStop the should stop
	 */
	public void setShouldStop(boolean shouldStop) {
		this.shouldStop = shouldStop;
	}

	/** The Constant INSTANCE. */
	private static final DatabaseThread INSTANCE = new DatabaseThread();

	/** The time. */
	private static int time = 10800000; //TODO

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public static int getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the time
	 */
	public static void setTime(int time) {
		DatabaseThread.time = time;
	}

	/** The video dao. */
	private static VideoDao videoDao;

	/**
	 * Gets the video dao.
	 *
	 * @return the video dao
	 */
	public static VideoDao getVideoDao() {
		return videoDao;
	}

	/**
	 * Sets the video dao.
	 *
	 * @param videoDao the video dao
	 */
	public static void setVideoDao(VideoDao videoDao) {
		DatabaseThread.videoDao = videoDao;
	}

	/**
	 * Gets the instance.
	 *
	 * @return the instance
	 */
	public static DatabaseThread getInstance() {
		return INSTANCE;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while(true){
			if(!shouldStop){
				System.out.println("Startet Update!");
				videoDao.createPopularVideoList();
				System.out.println("Update fertig!");
				try { Thread.sleep(time); } catch (InterruptedException e) {}
			}else{
				try { Thread.sleep(1000); } catch (InterruptedException e) {}				
			}
		}
	}

	/**
	 * Start thread.
	 */
	public void startThread(){
		//start thread
		try{
			this.start();
			synchronized (this) {notify();}			
		}catch(IllegalThreadStateException ite){
			//or continue executing
		}
	}
}
