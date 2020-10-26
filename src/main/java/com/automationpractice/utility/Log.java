package com.automationpractice.utility;

import org.apache.log4j.Logger;

public class Log {

	//Initialize Log4j logs
	private static Logger log = Logger.getLogger(Log.class.getName());

	// This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
	public static void startTestCase(String testCaseName){
		log.info("*****************************************************************************************");
		log.info("");
		log.info("\t\t\t--{  "+testCaseName.toUpperCase()+" - STARTS  }--");
		log.info("");
		log.info("*****************************************************************************************");
	}

	//This is to print log for the ending of the test case
	public static void endTestCase(String testCaseName, String status){
		log.info("*****************************************************************************************");
		log.info("");
		log.info("\t\t\t--{  "+testCaseName.toUpperCase()+" - "+status+"  }--");
		log.info("");
		log.info("*****************************************************************************************");
	}
	
	public static void error(String errorMsg) {
		log.error(errorMsg);
	}

	public static void info(String message) {
		log.info(message);
	}
}