package com.automationpractice.runner;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.automationpractice.listener.WebDriverListener;
import com.automationpractice.utility.ConfigReader;
import com.automationpractice.utility.GlobalVariable;
import com.automationpractice.utility.Log;
import com.automationpractice.utility.ReportManager;
import com.automationpractice.utility.ScreenshotUtility;
import com.aventstack.extentreports.MediaEntityBuilder;

public class TestBase {
	private Logger log = Logger.getLogger(TestBase.class.getName());
	protected WebDriver driver;
	protected String testCaseName;
	private EventFiringWebDriver eventHandler;
	private WebDriverListener listener;
	
	@BeforeSuite
	public void configuringLog4j() {
		DOMConfigurator.configure("log4j.xml");
	}
	
	@BeforeMethod
	public void browserSetup() {
		driver = launchBrowser(driver, ConfigReader.getProperty("appUrl"));
	}
	
	@AfterMethod
	public void browserTeardown(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			String imageFilePath = ScreenshotUtility.takeFullScreenShot(driver, testCaseName+"_Failed");
			try {
				ReportManager.getTest().error("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(imageFilePath).build());
			} catch (IOException e) {
				e.printStackTrace();
				Log.error("Error occured while attaching screenshot: "+e.getMessage());
			}
		}
		eventHandler.unregister(listener);
		closeBrowser(driver);
	}
	
	public WebDriver launchBrowser(WebDriver driver, String url){
		log.info("Launching Browser.");
		String chromePath = GlobalVariable.basePath + ConfigReader.getProperty("chromeDriverPath");
		System.setProperty("webdriver.chrome.driver", chromePath);
		ChromeOptions option = new ChromeOptions();
		option.addArguments("disable-infobars");
		driver = new ChromeDriver(option);
		eventHandler = new EventFiringWebDriver(driver);
		listener = new WebDriverListener();
		eventHandler.register(listener);
		driver = eventHandler;
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigReader.getProperty("implicitlyWaitTime")),TimeUnit.SECONDS);
		return driver;
	}

	public void closeBrowser(WebDriver driver){
		log.info("Closing Browser.");
		driver.quit();
	}
}
