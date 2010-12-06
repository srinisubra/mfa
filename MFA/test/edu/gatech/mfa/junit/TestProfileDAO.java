package edu.gatech.mfa.junit;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.profile.app.db.ProfileDAO;

import junit.framework.TestCase;
 
public class TestProfileDAO extends TestCase {
	private ApplicationContext appContext;
	private ProfileDAO profileDAO;
	
	public TestProfileDAO() throws Exception
	{
		appContext = new ClassPathXmlApplicationContext("db-beans.xml");
		profileDAO = new ProfileDAO();
		profileDAO.setDataSource((DataSource) appContext.getBean("basicDataSource"));
		profileDAO.init();
	}
	
	
	public void testGetProfileData()
	{
		profileDAO.getUserData("nadir");
	}
	
}
