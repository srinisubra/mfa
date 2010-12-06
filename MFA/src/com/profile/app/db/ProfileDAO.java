package com.profile.app.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProfileDAO {

	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private DataSource dataSource;
	private JdbcTemplate template;
	private Log log = LogFactory.getLog(getClass());
	
	public void init() throws Exception
	{
		template = new JdbcTemplate(dataSource);
	}
	
	
	public Map<String,String> getUserData(String username)
	{
		Map<String,Object> record = template.queryForMap("select * from user_details where user_name=?",username);
		log.info("User data for [" + username + "] \n" + record);
		return getMapOfStringFromMapOfObjects(record);
	}
	
	private Map<String,String> getMapOfStringFromMapOfObjects(Map<String,Object> mapOfObjects)
	{
		Map<String,String> mapOfString = new HashMap<String, String>();
		for(String key : mapOfObjects.keySet())
		{
			String str = mapOfObjects.get(key) == null ? "" : mapOfObjects.get(key).toString();
			
			if(key.equals("phone_no"))
			{
				mapOfString.put("phone_no_1", str.substring(0,3));
				mapOfString.put("phone_no_2", str.substring(3,6));
				mapOfString.put("phone_no_3", str.substring(6,10));
			}
			else if(key.equals("join_date"))
			{
				mapOfString.put("join_date_1", str.substring(0,4));
				mapOfString.put("join_date_2", str.substring(5,7));
				mapOfString.put("join_date_3", str.substring(8,10));
			}
			else if(key.equals("user_ssn"))
			{
				mapOfString.put("user_ssn_1", str.substring(0,3));
				mapOfString.put("user_ssn_2", str.substring(3,5));
				mapOfString.put("user_ssn_3", str.substring(5,9));
			}
			else			
			mapOfString.put(key, str);
		}
		return mapOfString;
	}
	
	
	public int save(String username,Map mapOfRequestParameters) throws Exception
	{
		StringBuilder stringBuilder = new StringBuilder();
		List listOfArguments = new ArrayList();
		stringBuilder.append("update user_details set ");
		
		for(Object key : mapOfRequestParameters.keySet())
		{
			stringBuilder.append(key + "=?,");
			log.info(mapOfRequestParameters.get(key));
			listOfArguments.add(((String[])mapOfRequestParameters.get(key))[0]);
		}
		
		stringBuilder.replace(stringBuilder.length()-1, stringBuilder.length(), "");
		stringBuilder.append(" where user_name=? ");
		listOfArguments.add(username);
		log.info("Executing Query:\n" + stringBuilder.toString());
		int status = -1 ; 
		try{
			
			Object[] array =  listOfArguments.toArray();
			for(Object a : array)
			{
				log.info( "val[" + a.toString() + "]");
			}
		template.update(stringBuilder.toString(),array );
		}catch(Exception e)
		{
			log.error(e);
			throw e;
		}
		return status;
	}
	
	public static void main(String[] args) throws Exception {
		ProfileDAO dao = new ProfileDAO();
		Map m = new HashMap();
		m.put("a", "1");
		m.put("b", "2");
		m.put("c", "3");
		dao.save("nadir",m);
		
	}
}
