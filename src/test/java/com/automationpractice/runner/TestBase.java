package com.automationpractice.runner;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.automationpractice.utility.CommonFunctions;
import com.automationpractice.utility.ConfigProperties;

public class TestBase {
	public static WebDriver driver;
	public static String testCaseName; 
	
	@BeforeSuite
	public void configuringLog4j() {
		DOMConfigurator.configure("log4j.xml");
	}
	
	@BeforeMethod
	public void browserSetup() {
		driver = CommonFunctions.launchBrowser(driver, ConfigProperties.getProperty("appUrl"));
	}
	
	@AfterMethod
	public void browserTeardown() {
		CommonFunctions.closeBrowser(driver);
	}
}
