package edu.gatech.mfa.test;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import edu.gatech.mfa.core.MFADataSource;
import edu.gatech.mfa.core.MFAUserDetail;

public class DbDataSource implements MFADataSource {

	private DataSource dataSource;
	private JdbcTemplate template;

	public void init() {
		template = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean checkUser(String username) {
		Integer numberOfUsers = template.queryForInt(
				"select count(*) from <table> where <name> = ? ",
				new Object[] { username }, String.class);
		if (numberOfUsers == 1)
			return true;
		else
			return false;
	}

	@Override
	public MFAUserDetail getUser(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMobileNumber(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmailId(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
