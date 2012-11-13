/**
 * The contents of this file are subject to the license and copyright
 *  detailed in the LICENSE and NOTICE files at the root of the source
 *  tree and available online at
 *  
 *  https://github.com/CILEA/dspace-cris/wiki/License
 */
package it.cilea.hku.authority.dao;

import it.cilea.hku.authority.model.dynamicfield.BoxProject;
import it.cilea.hku.authority.model.dynamicfield.EditTabProject;
import it.cilea.hku.authority.model.dynamicfield.TabProject;
import it.cilea.osd.jdyna.dao.EditTabDao;



public interface ProjectEditTabDao extends EditTabDao<BoxProject,TabProject,EditTabProject> {

	
}