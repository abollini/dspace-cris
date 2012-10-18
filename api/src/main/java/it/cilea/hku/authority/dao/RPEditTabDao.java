/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 * 
 * http://www.dspace.org/license/
 * 
 * The document has moved 
 * <a href="https://svn.duraspace.org/dspace/licenses/LICENSE_HEADER">here</a>
 */
package it.cilea.hku.authority.dao;

import it.cilea.hku.authority.model.dynamicfield.BoxResearcherPage;
import it.cilea.hku.authority.model.dynamicfield.EditTabResearcherPage;
import it.cilea.hku.authority.model.dynamicfield.TabResearcherPage;
import it.cilea.osd.jdyna.dao.EditTabDao;



public interface RPEditTabDao extends EditTabDao<BoxResearcherPage,TabResearcherPage,EditTabResearcherPage> {
	
}
