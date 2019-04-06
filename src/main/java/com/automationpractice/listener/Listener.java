package com.automationpractice.listener;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automationpractice.runner.TestBase;
import com.automationpractice.utility.CommonFunctions;
import com.automationpractice.utility.ConfigProperties;
import com.automationpractice.utility.Log;
import com.automationpractice.utility.ReportManager;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Listener implements ITestListener{

	@Override
	public void onFinish(ITestContext result) {
		ReportManager.endTest();
	}

	@Override
	public void onStart(ITestContext result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testCaseName = result.getName();
		String status = testCaseName+ConfigProperties.getProperty("testCaseFail");	
		String imageFilePath = CommonFunctions.takeFullScreenShot(TestBase.driver, testCaseName+"_Failed");
		try {
			ReportManager.getTest().error("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(imageFilePath, status).build());
		} catch (IOException e) {
			e.printStackTrace();
			Log.error("Error occured while attaching screenshot: "+e.getMessage());
		}
		Log.error(status);
		Log.endTestCase(testCaseName);	
		ReportManager.getTest().log(Status.FAIL, status);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testCaseName = result.getName();
		String status = testCaseName+ConfigProperties.getProperty("testCaseSkipped");
		Log.info(status);
		ReportManager.getTest().log(Status.SKIP, status);
	}

	@Override
	public void onTestStart(ITestResult result) {
		ReportManager.startTest(result.getName());
		Log.startTestCase(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testCaseName = result.getName();
		String status = testCaseName+ConfigProperties.getProperty("testCasePass");
		Log.info(status);
		Log.endTestCase(testCaseName);
		ReportManager.getTest().log(Status.PASS, status);
	}
}
