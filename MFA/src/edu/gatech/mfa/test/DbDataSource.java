package edu.gatech.mfa.test;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.gatech.mfa.core.MFADataSource;
import edu.gatech.mfa.core.MFAUserDetail;

public class DbDataSource implements MFADataSource {

	private DataSource dataSource;
	private JdbcTemplate template;
	private Log log = LogFactory.getLog(getClass());

	public void init() {
		
		template = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean checkUser(String username) {
		log.info("Checking user : " + username);
		Integer numberOfUsers = template.queryForInt(
				"select count(*) from user_credentials where user_name = ? ",
				new Object[] { username });
		log.info("Number of user by username " + username + " is " + numberOfUsers
				);
		if (numberOfUsers == 1)
			return true;
		else
			return false;
	}

	@Override
	public MFAUserDetail getUser(String username) throws Exception {
		Map<String,Object>  record = template.queryForMap("select * from user_credentials where user_name = ?", new Object[]{username});
		MFAUserDetail userDetail = new MFAUserDetail();
		userDetail.setUsername(username);
		userDetail.setSalt(record.get("user_salt").toString());
		userDetail.setCredential(record.get("pass_hash").toString());
		return userDetail;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public String getMobileNumber(String username) throws Exception {
		Map<String,Object>  record = template.queryForMap("select b.* from user_credentials as a,user_details as b where a.user_name = ? and a.user_id = b.id", new Object[]{username});
		String mobile = record.get("phone_no").toString();
		return mobile;
	}

	@Override
	public String getEmailId(String username) throws Exception {
		Map<String,Object>  record = template.queryForMap("select b.* from user_credentials as a,user_details as b where a.user_name = ? and a.user_id = b.id", new Object[]{username});
		String email = record.get("email_id").toString();
		return email;
	}

}
