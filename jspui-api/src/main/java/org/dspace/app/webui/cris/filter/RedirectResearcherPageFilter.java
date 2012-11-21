/**
 * The contents of this file are subject to the license and copyright
 *  detailed in the LICENSE and NOTICE files at the root of the source
 *  tree and available online at
 *  
 *  https://github.com/CILEA/dspace-cris/wiki/License
 */
package org.dspace.app.webui.cris.filter;


import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dspace.app.cris.model.ResearcherPage;
import org.dspace.app.cris.service.ApplicationService;
import org.dspace.core.ConfigurationManager;
import org.dspace.utils.DSpace;


/**
 * Filter to redirect the user to the RP pages when browsing on HKUAuthority
 * values. If the ResearcherPage is not active the user WILL NOT redirect and
 * the standard DSpace browse page will be shown module
 * 
 * @author cilea
 * 
 */
public class RedirectResearcherPageFilter implements Filter
{
    /** log4j category */
    private static Logger log = Logger
            .getLogger(RedirectResearcherPageFilter.class);

    private ApplicationService applicationService;

    public void init(FilterConfig config)
    {
        DSpace dspace = new DSpace();
        applicationService = dspace.getServiceManager()
                .getServiceByName("applicationService",
                        ApplicationService.class);
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws ServletException, IOException
    {
        // We need HTTP request objects
        HttpServletRequest hrequest = (HttpServletRequest) request;
        HttpServletResponse hresponse = (HttpServletResponse) response;

        String browseType = hrequest.getParameter("type");
        String authority = hrequest.getParameter("authority");
        
        if (browseType != null
                && browseType.equals(ConfigurationManager
                        .getProperty("researcherpage.browseindex"))
                && authority != null)
        {
            try
            {
                Integer rid = Integer.parseInt(authority.substring(2));
                ResearcherPage rp = applicationService.get(ResearcherPage.class,
                        rid);
                if (rp.getStatus() != null && rp.getStatus().booleanValue() == true)
                {
                    StringBuffer param = new StringBuffer();
                    Map<String,String[]> parameterMap = request.getParameterMap();
                    if (parameterMap != null)
                    {
                        for (String key : parameterMap.keySet())
                        {
                            param.append("&").append(key).append("=").append(
                                    parameterMap.get(key)[0]);
                        }
                    }
                    hresponse.sendRedirect(hrequest.getContextPath()
                            + "/cris/"
                            + authority
                            + (param.length() > 0 ? "?" + param.substring(1)
                                    : ""));
                    return;
                }
            }
            catch (Exception e)
            {
                log.error("Error retrieving research page URL for redirect, continue with default behaviour", e);
            }
        }
        chain.doFilter(hrequest, hresponse);
    }

    public void destroy()
    {
        // Nothing
    }
}