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
package it.cilea.hku.authority.webui.dto;

import it.cilea.hku.authority.model.OrganizationUnit;
import it.cilea.osd.jdyna.dto.AnagraficaObjectAreaDTO;

import java.util.List;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OUAnagraficaObjectDTO extends CrisAnagraficaObjectDTO
{

    /**
     * The log4j category
     */
    protected final Log log = LogFactory.getLog(getClass());


    public OUAnagraficaObjectDTO(OrganizationUnit ou)
    {
        super(ou);
        this.setTimeStampCreated(ou.getTimeStampInfo().getCreationTime());
        this.setTimeStampModified(ou.getTimeStampInfo()
                .getLastModificationTime());
    }

 

    /**
     * Decorate list for dynamic binding with spring mvc
     * 
     * @param list
     * @return lazy list temporary
     */
    private List getLazyList(List<String> list)
    {
        log.debug("Decorate list for dynamic binding with spring mvc");
        List lazyList = LazyList.decorate(list,
                FactoryUtils.instantiateFactory(String.class));

        return lazyList;
    }

}
