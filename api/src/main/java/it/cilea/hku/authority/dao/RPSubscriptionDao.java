/**
 * The contents of this file are subject to the license and copyright
 *  detailed in the LICENSE and NOTICE files at the root of the source
 *  tree and available online at
 *  
 *  https://github.com/CILEA/dspace-cris/wiki/License
 */
package it.cilea.hku.authority.dao;

import it.cilea.hku.authority.model.RPSubscription;
import it.cilea.hku.authority.model.ResearcherPage;
import it.cilea.osd.common.dao.PaginableObjectDao;

import java.util.List;

/**
 * This interface define the methods available to retrieve RPSubscription
 * 
 * @author cilea
 * 
 */
public interface RPSubscriptionDao extends PaginableObjectDao<RPSubscription, Integer> {
	public long countByRp(ResearcherPage rp);
	public List<ResearcherPage> findRPByEpersonID(int epersonID);
	public RPSubscription uniqueByEpersonIDandRp(int epersonID, ResearcherPage rp);
    public void deleteByEpersonID(int id);
}