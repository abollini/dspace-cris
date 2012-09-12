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
package it.cilea.hku.authority.webui.controller;

import it.cilea.hku.authority.model.dynamicfield.RPPropertiesDefinition;
import it.cilea.hku.authority.service.ApplicationService;
import it.cilea.hku.authority.util.ImportExportUtils;
import it.cilea.hku.authority.webui.dto.ImportDTO;
import it.cilea.osd.common.controller.BaseFormController;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dspace.app.webui.util.UIUtil;
import org.dspace.authorize.AuthorizeManager;
import org.dspace.core.ConfigurationManager;
import org.dspace.core.Context;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * This SpringMVC controller is responsible to handle the import by webui
 * 
 * @author cilea
 * 
 */
public class ImportFormController extends BaseFormController {

	private ApplicationService applicationService;

	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		Context dspaceContext = UIUtil.obtainContext(request);

		ImportDTO object = (ImportDTO) command;
		MultipartFile fileDTO = object.getFile();

		// read folder from configuration and make dir
		String path = ConfigurationManager
				.getProperty("researcherpage.file.import.path");
		File dir = new File(path);
		dir.mkdir();
		try {
			if (object.getModeXSD() != null) {
				response.setContentType("application/xml;charset=UTF-8");
				response.addHeader("Content-Disposition",
						"attachment; filename=rp.xsd");
				String nameXSD = "xsd-download-webuirequest.xsd";
				File filexsd = new File(dir, nameXSD);
				filexsd.createNewFile();	
				ImportExportUtils.generateXSD(response.getWriter(), dir, applicationService
                        .findAllContainables(RPPropertiesDefinition.class), filexsd, null);
				response.getWriter().flush();
				response.getWriter().close();
				return null;
			} else {
				if (fileDTO != null && !fileDTO.getOriginalFilename().isEmpty()) {
					Boolean defaultStatus = ConfigurationManager
							.getBooleanProperty("researcherpage.file.import.rpdefaultstatus");
					if (AuthorizeManager.isAdmin(dspaceContext)) {
						dspaceContext.turnOffAuthorisationSystem();
					}
					ImportExportUtils.importResearchersXML(fileDTO.getInputStream(), dir,
							applicationService, dspaceContext, defaultStatus);
					saveMessage(
							request,
							getText("action.import.with.success",
									request.getLocale()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			saveMessage(
					request,
					getText("action.import.with.noSuccess", e.getMessage(),
							request.getLocale()));
		}

		return new ModelAndView(getSuccessView());

	}
}
