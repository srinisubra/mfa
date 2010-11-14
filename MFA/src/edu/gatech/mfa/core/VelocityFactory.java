package edu.gatech.mfa.core;

import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;

public class VelocityFactory {

	private VelocityEngine velocityEngine;
	private Properties props;
	
	public void init() throws Exception
	{
		velocityEngine = new VelocityEngine(props);
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}
	
}
