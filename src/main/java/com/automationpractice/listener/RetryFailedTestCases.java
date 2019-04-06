package com.automationpractice.listener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestCases implements IRetryAnalyzer {
	private Logger log = Logger.getLogger(RetryFailedTestCases.class.getName());
	private int retryCount = 0;
	// If there are any failed test cases, then it runs 2 times
	private int maxRetryCount = 2;
	
	@Override
	public boolean retry(ITestResult arg0) {
		if(retryCount < maxRetryCount) {
			log.info("Retrying the failed test cases.");
			retryCount++;
			return true;
		}
		return false;
	}

}
