package edu.gatech.mfa.extn.otpemail;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import edu.gatech.mfa.core.VelocityFactory;

public class EmailSender {

	public String getEmailTemplateName() {
		return emailTemplateName;
	}

	public void setEmailTemplateName(String emailTemplateName) {
		this.emailTemplateName = emailTemplateName;
	}

	public VelocityFactory getVelocityFactory() {
		return velocityFactory;
	}

	public void setVelocityFactory(VelocityFactory velocityFactory) {
		this.velocityFactory = velocityFactory;
	}

	private MailSender mailSender;
	private SimpleMailMessage templateMessage;
	private VelocityEngine velocityEngine;
	private String emailTemplateName;
	private Template emailTemplate;
	private VelocityFactory velocityFactory;
	
	private Log log = LogFactory.getLog(getClass());

	public void init() throws ResourceNotFoundException, ParseErrorException,
			Exception {
		velocityEngine = velocityFactory.getVelocityEngine();
		emailTemplate = velocityEngine.getTemplate(emailTemplateName);
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public SimpleMailMessage getTemplateMessage() {
		return templateMessage;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	public String constructMessage(String username, String otp)
			throws ResourceNotFoundException, ParseErrorException,
			MethodInvocationException, IOException {
		StringWriter sw = new StringWriter();
		VelocityContext context = new VelocityContext();
		context.put("username", username);
		context.put("otp", otp);
		emailTemplate.merge(context, sw);
		return sw.toString();

	}

	public void sendMail(String username, String emailId, String otp)
			throws Exception {
		String message = constructMessage(username, otp);
		SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
		msg.setTo(emailId);
		msg.setText(message);
		mailSender.send(msg);

	}
}
