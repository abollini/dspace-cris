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
package it.cilea.surplus.oa.servlet;

import it.cilea.surplus.oa.json.JSONRequest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dspace.app.webui.servlet.DSpaceServlet;
import org.dspace.authorize.AuthorizeException;
import org.dspace.core.Context;
import org.dspace.core.LogManager;
import org.dspace.core.PluginManager;

public class JSONServlet extends DSpaceServlet {
	private static Logger log = Logger.getLogger(JSONServlet.class);

	@Override
	protected void doDSPost(Context context, HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException,
	        SQLException, AuthorizeException
	{
	    doDSGet(context, request, response);
	}
	
	@Override
	protected void doDSGet(Context context, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, AuthorizeException {
		String pluginName = request.getPathInfo();

		if (pluginName == null) {
			pluginName = "";
		}

		if (pluginName.startsWith("/")) {
			pluginName = pluginName.substring(1);
			pluginName = pluginName.split("/")[0];
		}

		JSONRequest jsonReq = (JSONRequest) PluginManager.getNamedPlugin(
				JSONRequest.class, pluginName);

		try {
//			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			jsonReq.doJSONRequest(context, request, response);
		} catch (Exception e) {
			log.error(LogManager.getHeader(context, "jsonrequest", pluginName),
					e);
		}
	}
}
