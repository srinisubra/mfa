package edu.gatech.mfa.core;

import org.springframework.web.servlet.mvc.Controller;

import edu.gatech.mfa.extn.MFAExtension;

public class MFAConfiguration {

	private static int DEFAULT_INACTIVE_SESSION_EXPRATION_TIME_SECONDS = 60 * 5;
	
	public MFAExtension getExtension() {
		return extension;
	}
	public void setExtension(MFAExtension extension) {
		this.extension = extension;
	}
	private Controller successController;
	private Controller failureController;
	private MFADataSource dataSource;
	private MFAExtension extension;
	private int inactiveSessionExpirationTime = DEFAULT_INACTIVE_SESSION_EXPRATION_TIME_SECONDS;
	
	public Controller getSuccessController() {
		return successController;
	}
	public int getInactiveSessionExpirationTime() {
		return inactiveSessionExpirationTime;
	}
	public void setInactiveSessionExpirationTime(
			int inactiveSessionExpirationTime) {
		this.inactiveSessionExpirationTime = inactiveSessionExpirationTime;
	}
	public void setSuccessController(Controller successController) {
		this.successController = successController;
	}
	public Controller getFailureController() {
		return failureController;
	}
	public void setFailureController(Controller failureController) {
		this.failureController = failureController;
	}
	public MFADataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(MFADataSource dataSource) {
		this.dataSource = dataSource;
	}
}
