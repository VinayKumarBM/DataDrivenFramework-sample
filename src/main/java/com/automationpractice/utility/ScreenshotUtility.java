package com.automationpractice.utility;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenshotUtility {
	private static Logger log = Logger.getLogger(ScreenshotUtility.class.getName());
	
	public static String takeScreenShot(WebDriver driver,String testCaseName){
		// Take screenshot and store as a file format
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenShotFilePath = ResourceUtility.getScreenShotFolderPath()+testCaseName+"_"+DateUtility.getStringDate("ddMMyyyy_HHmmss")
										+ConfigReader.getProperty("fileFormat");
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
		String screenShotFilePath = ResourceUtility.getScreenShotFolderPath()+testCaseName+"_"+DateUtility.getStringDate("ddMMyyyy_HHmmss")
										+ConfigReader.getProperty("fileFormat");
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		try {
			ImageIO.write(screenshot.getImage(),"PNG",new File(screenShotFilePath));
		} catch (IOException exp) {
			log.error("Exception occured in takeScreenShot: "+exp.getMessage());
		}
		return screenShotFilePath;
	}
}
