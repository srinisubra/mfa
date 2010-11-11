package edu.gatech.mfa.core;

import org.springframework.web.servlet.mvc.Controller;

import edu.gatech.mfa.extn.MFAExtension;

public class MFAConfiguration {

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
	
	public Controller getSuccessController() {
		return successController;
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
