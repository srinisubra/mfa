package com.profile.app;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.profile.app.db.ProfileDAO;

import edu.gatech.mfa.controller.VelocityController;
import edu.gatech.mfa.core.VelocityModelAndView;
import edu.gatech.mfa.extn.SecurityToken;

public class UpdateProfileController extends VelocityController{

	private ProfileDAO profileDAO;
	private String updateProfileTemplateFile;
	private Log log = LogFactory.getLog(getClass());
	
	@Override
	protected VelocityModelAndView processRequest(HttpServletRequest request)
			throws Exception {
		
		Map<String,String> mapOfRequestParameters  = request.getParameterMap();
		SecurityToken token = (SecurityToken) request.getSession().getAttribute("securityToken");
		if(token == null) throw new Exception("Security Exception. Security Token not found in the session");
		String username = token.getUsername();
		profileDAO.save(username, mapOfRequestParameters);
		VelocityModelAndView mav = new VelocityModelAndView();
		mav.setTemplateName(updateProfileTemplateFile);
		return mav;
	}

	public ProfileDAO getProfileDAO() {
		return profileDAO;
	}

	public void setProfileDAO(ProfileDAO profileDAO) {
		this.profileDAO = profileDAO;
	}

	public String getUpdateProfileTemplateFile() {
		return updateProfileTemplateFile;
	}

	public void setUpdateProfileTemplateFile(String updateProfileTemplateFile) {
		this.updateProfileTemplateFile = updateProfileTemplateFile;
	}


}
