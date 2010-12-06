package com.profile.app;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.profile.app.db.ProfileDAO;

import edu.gatech.mfa.controller.VelocityController;
import edu.gatech.mfa.core.VelocityModelAndView;
import edu.gatech.mfa.extn.SecurityToken;

public class DisplayProfileController extends VelocityController{

	private ProfileDAO profileDAO;
	private Log log = LogFactory.getLog(getClass());
	public ProfileDAO getProfileDAO() {
		return profileDAO;
	}

	public void setProfileDAO(ProfileDAO profileDAO) {
		this.profileDAO = profileDAO;
	}

	public String getDisplayProfileTemplateFile() {
		return displayProfileTemplateFile;
	}

	public void setDisplayProfileTemplateFile(String displayProfileTemplateFile) {
		this.displayProfileTemplateFile = displayProfileTemplateFile;
	}

	private String displayProfileTemplateFile;
	
	@Override
	protected VelocityModelAndView processRequest(HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		SecurityToken token = (SecurityToken) request.getSession().getAttribute("securityToken");
		if(token == null) throw new Exception("Security Exception. Security Token not found in the session");
		String username = token.getUsername();
		log.info("Fetching record for user [" + username + "]" );
		Map<String,String> records = profileDAO.getUserData(username);
		VelocityModelAndView mav = new VelocityModelAndView();
		mav.addAllObjects(records);
		mav.setTemplateName(displayProfileTemplateFile);
		return mav;
	}

}
