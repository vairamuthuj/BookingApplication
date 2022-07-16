package com.book.util;

import java.util.Properties;


public class PropertyUtil {

	private static Properties properties;
	
	private PropertyUtil() {
		
	}
	
	
	public static String getProperty(String key) {
		
		if(properties == null) {
			properties = new Properties();
			try {
				properties.load(PropertyUtil.class.getClassLoader().getResourceAsStream("query.properties"));
			}catch(Exception e) {
				e.getMessage();
			}
		}
		
		String value = properties.getProperty(key);
				
		return value;
	}
	
	
}
