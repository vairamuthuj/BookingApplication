package com.book.util;

import java.util.regex.*;

public class CommonMethods {

	public static boolean isValidMobileNo(String input)  {  
		Pattern pattern = Pattern.compile("(0/91)?[7-9][0-9]{9}");  
		Matcher match = pattern.matcher(input);  
	  return (match.find() && match.group().equals(input));  
	} 
	
	public static boolean isValidEmailAddress(String input)  {  
		 String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";  
	     Pattern pattern = Pattern.compile(regex);  
	     Matcher matcher = pattern.matcher(input);  
	  return matcher.matches();  
	} 
	
	public static String returnMessages(int status) {
		String response = "FAILURE";
		if(status != 0){
		    	response = "SUCCESS";
		    }
		return response;
	}
}
