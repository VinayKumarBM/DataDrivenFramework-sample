package com.automationpractice.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CommonFunctions {
	private static Logger log = Logger.getLogger(CommonFunctions.class.getName());
	
	public static WebDriver launchBrowser(WebDriver driver, String url){
		log.info("Launching Browser.");
		String chromePath = GlobalVariable.basePath + ConfigProperties.getProperty("chromeDriverPath");
		System.setProperty("webdriver.chrome.driver", chromePath);
		ChromeOptions option = new ChromeOptions();
		option.addArguments("disable-infobars");
		driver = new ChromeDriver(option);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("implicitlyWaitTime")),TimeUnit.SECONDS);
		return driver;
	}

	public static void closeBrowser(WebDriver driver){
		log.info("Closing Browser.");
		driver.quit();
	}
	
	public static String getStringDate(String dateFormat) {
		SimpleDateFormat dtf = new SimpleDateFormat(dateFormat);  
	    Date localDate = new Date();  
		return dtf.format(localDate).toString();
	}
	
	public static boolean createDirectory(String folderPath) {
		Path path = Paths.get(folderPath);
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                log.info("Directory was created.");
                return true;
            } catch (IOException exp) {
                log.error(exp.getMessage(), exp);
                return false;
            }
        }
        return true;
	}
}
