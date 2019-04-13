package com.automationpractice.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
	
	public static String getStringDate(String dateFormat) {
		SimpleDateFormat dtf = new SimpleDateFormat(dateFormat);  
	    Date localDate = new Date();  
		return dtf.format(localDate).toString();
	}
}
