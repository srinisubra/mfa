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
		return getMapOfStringFromMapOfObjects(record);
	}
	
	private Map<String,String> getMapOfStringFromMapOfObjects(Map<String,Object> mapOfObjects)
	{
		Map<String,String> mapOfString = new HashMap<String, String>();
		for(String key : mapOfObjects.keySet())
		{
			mapOfString.put(key, mapOfObjects.get(key).toString());
		}
		return mapOfString;
	}
	
	
	public int save(String username,Map<String, String> mapOfRequestParameters)
	{
		StringBuilder stringBuilder = new StringBuilder();
		List<Object> listOfArguments = new ArrayList<Object>();
		stringBuilder.append("update user_details set ");
		
		for(String key : mapOfRequestParameters.keySet())
		{
			stringBuilder.append(key + "=?,");
			listOfArguments.add(mapOfRequestParameters.get(key));
		}
		
		stringBuilder.replace(stringBuilder.length()-1, stringBuilder.length(), "");
		stringBuilder.append(" where user_name=? ");
		listOfArguments.add(username);
		log.info("Executing Query:\n" + stringBuilder.toString());
		int status = template.update(stringBuilder.toString(), listOfArguments.toArray());
		return status;
	}
	
	public static void main(String[] args) {
		ProfileDAO dao = new ProfileDAO();
		Map m = new HashMap();
		m.put("a", "1");
		m.put("b", "2");
		m.put("c", "3");
		dao.save("nadir",m);
		
	}
}
