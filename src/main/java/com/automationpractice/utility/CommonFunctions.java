package com.automationpractice.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CommonFunctions {
	private static Logger log = Logger.getLogger(CommonFunctions.class.getName());
	public static final String basePath = System.getProperty("user.dir");
	
	public static WebDriver launchBrowser(WebDriver driver, String url){
		log.info("Launching Browser.");
		String chromePath = basePath + ConfigProperties.getProperty("chromeDriverPath");
		System.setProperty("webdriver.chrome.driver", chromePath);
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("implicitlyWaitTime")),TimeUnit.SECONDS);
		return driver;
	}

	public static void closeBrowser(WebDriver driver){
		log.info("Closing Browser.");
		driver.quit();
	}

	public static String takeScreenShot(WebDriver driver,String testCaseName){
		// Take screenshot and store as a file format
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenShotFilePath = basePath+ConfigProperties.getProperty("screenShotFolderPath")
								+testCaseName+"_"+getStringDate("ddMMyyyy_HHmmss")+ConfigProperties.getProperty("fileFormat");
		try{
			FileUtils.copyFile(src, new File(screenShotFilePath));
		}
		catch(Exception exp){
			log.error("Exception occured in takeScreenShot: "+exp.getMessage());
		}
		return screenShotFilePath;
	}
	
	public static String takeFullScreenShot(WebDriver driver,String testCaseName){
		// Take full screenshot and store as a file format
		String screenShotFilePath = basePath+ConfigProperties.getProperty("screenShotFolderPath")+testCaseName
				+"_"+getStringDate("ddMMyyyy_HHmmss")+ConfigProperties.getProperty("fileFormat");
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		try {
			ImageIO.write(screenshot.getImage(),"PNG",new File(screenShotFilePath));
		} catch (IOException exp) {
			log.error("Exception occured in takeScreenShot: "+exp.getMessage());
		}
		return screenShotFilePath;
	}
	
	public static String getStringDate(String dateFormat) {
		SimpleDateFormat dtf = new SimpleDateFormat(dateFormat);  
	    Date localDate = new Date();  
		return dtf.format(localDate).toString();
	}
}
