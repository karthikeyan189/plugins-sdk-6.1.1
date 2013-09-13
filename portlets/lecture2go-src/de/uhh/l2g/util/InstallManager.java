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

import java.io.IOException;

import de.uhh.l2g.beans.Factory;
import de.uhh.l2g.dao.HostDao;
import de.uhh.l2g.dao.L2gSystemDao;
import de.uhh.l2g.dao.ProducerDao;

/**
 * The Class InstallManager.
 */
public class InstallManager extends Factory {

	//set up l2go system
	/**
	 * L2go setup.
	 *
	 * @throws IOException the IO exception
	 */
	public void l2goSetup() throws IOException{
		L2gSystemDao l2gSysDao = (L2gSystemDao)getDaoBeanFactory().getBean("l2gSysDao");
		HostDao hostDao = (HostDao)getDaoBeanFactory().getBean("hostDao");
		ProducerDao producerDao = (ProducerDao)getDaoBeanFactory().getBean("producerDao");
		
		//check for first setup	and setup media repository	
		if(!(l2gSysDao).setupWizardIsActive()){
			//create media repository with all repository folder
			RepositoryManager.createRepository(hostDao, producerDao);
			//deactivate setup wizard
			l2gSysDao.setSetupWizardActive(0);
		}
	}
}
