package com.automationpractice.utility;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementOperations {
	private static Logger log = Logger.getLogger(ElementOperations.class.getName());
	private static Long waitTime = Long.parseLong(ConfigProperties.getProperty("webDriverWaitTime"));

	public static void clickOnWebElement(WebElement element, WebDriver driver){
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",element);
	}	

	public static void selectByVisibleText(WebElement element, String visibleText){
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}	

	public static void waitForElementToBeClickable(WebDriver driver, WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static boolean isElementPresent(WebDriver driver,By byElement) {
		try{
			driver.findElement(byElement);
			log.info("Element is present");
			return true;
		}catch(Exception exp){
			log.info("Element is NOT present");
			return false;
		}
	}

	public static void switchToNewWindow(WebDriver driver, String mainWindow) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		Set<String> windowHandles = driver.getWindowHandles();
		Log.info("Number of Windows: "+windowHandles.size());
		for (String handle: windowHandles) {
			if (!mainWindow.equals(handle)) {
				driver.switchTo().window(handle);
				driver.manage().window().maximize();
				log.info("Window Title: "+driver.getTitle());
			}
		}
	}

	public static void pause(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void scrollToElement(WebElement element,WebDriver driver){		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public static void scrollToBottom(WebDriver driver){		
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void waitForAlert(WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static String getAlertMessageAndAccept(WebDriver driver){
		String message = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();		
			message = alert.getText();
			log.info("Alert message: "+message);
			alert.accept();
		}catch (Exception e) {
			log.info("Alert was not present");
		}
		return message;
	}

	public static boolean isAlertPresent(WebDriver driver) {
		try{
			driver.switchTo().alert();
			return true;
		}catch (NoAlertPresentException exp){
			return false;
		}
	}
}
