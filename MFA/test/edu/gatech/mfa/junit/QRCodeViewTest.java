package edu.gatech.mfa.junit;

import java.io.FileWriter;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class QRCodeViewTest extends TestCase {

	VelocityEngine veloEngine;
	Properties p;
	
	protected void setUp() throws Exception {
		p = new Properties();
		p.setProperty("resource.loader", "class");
		p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		veloEngine = new VelocityEngine(p);
		super.setUp();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testQRCode() throws ResourceNotFoundException, ParseErrorException, Exception{
		
		Template template = veloEngine.getTemplate("qrcode.vm");
		VelocityContext context = new VelocityContext();
		context.put("salt", "2eqweqeqwe");
		context.put("requestId", "asdasq2e123");
		context.put("otpID", "1313123123qe");
		
		FileWriter fw = new FileWriter("test.html");
		template.merge(context, fw);
		fw.close();
	}

}
