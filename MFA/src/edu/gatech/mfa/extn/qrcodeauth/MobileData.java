package edu.gatech.mfa.extn.qrcodeauth;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class MobileData implements MobileDataExtractor {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void init()
	{
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public String getDeviceID(String userName) {
		
		Map<String,Object> record = jdbcTemplate.queryForMap("select b.* from user_credentials as a,user_details as b where a.user_name = ? and a.user_id = b.id", new Object[]{userName});
		String deviceID = record.get("device_id").toString();
		return deviceID;
	}

	@Override
	public String getDeviceOS(String userName) {
		Map<String,Object> record = jdbcTemplate.queryForMap("select b.* from user_credentials as a,user_details as b where a.user_name = ? and a.user_id = b.id", new Object[]{userName});
		String deviceOS = record.get("device_id").toString();
		return deviceOS;
	}

	@Override
	public String getSIMID(String userName) {
		Map<String,Object> record = jdbcTemplate.queryForMap("select b.* from user_credentials as a,user_details as b where a.user_name = ? and a.user_id = b.id", new Object[]{userName});
		String deviceOS = record.get("device_type").toString();
		return deviceOS;
	}

	@Override
	public String getDeviceType(String userName) {
		Map<String,Object> record = jdbcTemplate.queryForMap("select b.* from user_credentials as a,user_details as b where a.user_name = ? and a.user_id = b.id", new Object[]{userName});
		String deviceType = record.get("device_type").toString();
		return deviceType;
	}

}
