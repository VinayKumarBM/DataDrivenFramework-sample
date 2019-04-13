package com.automationpractice.scripts;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automationpractice.modules.HomePageModule;
import com.automationpractice.modules.LoginPageModule;
import com.automationpractice.modules.MyAccountPageModule;
import com.automationpractice.utility.ConfigReader;
import com.automationpractice.utility.ExcelUtility;
import com.automationpractice.utility.ReportManager;
import com.automationpractice.utility.ResourceUtility;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class LoginScript {
	private Logger log = Logger.getLogger(LoginScript.class.getName());
	private WebDriver driver;
	private HomePageModule homePageModule;
	private LoginPageModule loginPageModule;
	private MyAccountPageModule myAccountPageModule;
	private String excelSheetPath = ResourceUtility.getDataFolderPath();

	public LoginScript(WebDriver driver) {
		this.driver = driver;
		homePageModule = new HomePageModule(driver);
		loginPageModule = new LoginPageModule(driver);
		myAccountPageModule	= new MyAccountPageModule(driver);
	}

	public void loginTest(String testCaseName) {
		ExtentTest test = ReportManager.getTest();
		try {
			Map<String, String> testDataMap = ExcelUtility.getData(testCaseName, excelSheetPath, 
					ConfigReader.getProperty("testDataSheetName"));

			homePageModule.navigateToLoginPage();
			test.log(Status.INFO, "Navigated to login page.");
			loginPageModule.loginToMyStore(testDataMap);
			String pageHeading = myAccountPageModule.userNavigatedToMyAccount();
			log.info("User is in "+pageHeading+" page");
			Assert.assertEquals(pageHeading, "MY ACCOUNT", "Page Heading did not match ");
			test.log(Status.INFO, "Logged into the Application.");
		} catch(Exception exp) {
			log.error(exp.getMessage(), exp);
			test.error("Exception Occured: "+Thread.currentThread().getStackTrace());
			Assert.fail(testCaseName);
		}
	}
}
