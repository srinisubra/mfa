package edu.gatech.mfa.junit;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.gatech.mfa.core.MFAUserDetail;
import edu.gatech.mfa.test.DbDataSource;
import junit.framework.TestCase;

public class TestDB extends TestCase{

	private DbDataSource dbSource;
	private ApplicationContext appContext;
	
	public TestDB()
	{
		appContext = new ClassPathXmlApplicationContext("db-beans.xml");
	}

	@Override
	protected void setUp() throws Exception {
		
		dbSource = (DbDataSource) appContext.getBean("dbDataSource");
		dbSource.init();
	}


	public void testCheckUser() {
		String username = "nadir" ;
		boolean status = dbSource.checkUser(username);
		assertTrue(status);
		
	}

	
	public void testGetUser() throws Exception {
		String username = "nadir" ;
		MFAUserDetail details = dbSource.getUser(username);
		assertNotNull(details);
		System.out.println(details);
	}

	
	public void testgetMobileNumber() throws Exception {
		String username = "nadir" ;
		String mobile = dbSource.getMobileNumber(username);
		assertNotNull(mobile);
		System.out.println(mobile);
	}


	public void testgetEmailId() throws Exception {
		String username = "nadir" ;
		String email = dbSource.getEmailId(username);
		assertNotNull(email);
		System.out.println(email);
	}

	public void testAll() throws Exception{ 
		System.out.println(dbSource.getAll("nadir"));
	}
}
