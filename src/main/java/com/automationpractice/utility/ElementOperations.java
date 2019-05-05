package com.automationpractice.utility;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementOperations {
	private Logger log = Logger.getLogger(ElementOperations.class.getName());
	private Long waitTime = Long.parseLong(ConfigReader.getProperty("webDriverWaitTime"));
	private WebDriver driver = null;
	
	public ElementOperations(WebDriver driver) {
		this.driver = driver;
	}

	public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='4px solid yellow'", element);
		pause(1);
		js.executeScript("arguments[0].style.border=''", element);
	}
	
	public void clickOnWebElement(WebElement element){
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",element);
	}	

	public void selectByVisibleText(WebElement element, String visibleText){
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}	

	public void selectByIndex(WebElement element, int index){
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		log.info("Selected Value : "+ value);
		return value;
	}
	
	public List<String> getAllSelectedValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getAllSelectedOptions();
		return getListValue(elementList);
	}

	public List<String> getAllDropDownValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		return getListValue(elementList);
	}

	private List<String> getListValue(List<WebElement> elementList) {
		List<String> valueList = new LinkedList<String>();
		
		for (WebElement element : elementList) {
			Log.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}
	
	public void waitForvisibilityOfElement(WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToBeClickable(WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForWindowsToOpen(int numberOfWindow){
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindow));
	}

	public boolean isElementPresent(By byElement) {
		try{
			driver.findElement(byElement);
			log.info("Element is present");
			return true;
		}catch(Exception exp){
			log.info("Element is NOT present");
			return false;
		}
	}

	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
		log.info("Switched to new frame");
	}
	
	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		log.info("Switched to new frame");
	}
	
	public void switchToParentFrame() {
		driver.switchTo().parentFrame();
		log.info("Switched to Parent Frame");
	}
	
	public void switchToParentWindow() {
		driver.switchTo().defaultContent();
		log.info("Switched to Parent Window");
	}
	
	public void switchToNewWindow() {
		String mainWindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		Log.info("Number of Windows: "+windowHandles.size());
		for (String handle: windowHandles) {
			if (!mainWindow.equals(handle)) {
				driver.switchTo().window(handle);
				driver.manage().window().maximize();
				log.info("Switched to New Window with Title: "+driver.getTitle());
			}
		}
	}
	
	public void switchToParentWithChildClose() {
		String mainWindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		Log.info("Number of Windows: "+windowHandles.size());
		int i=1;
		for (String handle: windowHandles) {
			if (!mainWindow.equals(handle)) {
				driver.switchTo().window(handle);
				driver.close();
				log.info("Closed Child Window: "+i);
				i++;
			}
		}
		driver.switchTo().window(mainWindow);
	}

	public void pause(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void scrollToElement(WebElement element){		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public void scrollToBottom(){		
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollToTop(){		
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}
	
	public void zoomInByPercentage(String percentage){		
		((JavascriptExecutor)driver).executeScript("document.body.style.zoom='"+percentage+"%'");
	}
	
	public void waitForAlert(){
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertMessageAndAccept(){
		String message = null;
		try {
			waitForAlert();
			Alert alert = driver.switchTo().alert();		
			message = alert.getText();
			log.info("Alert message: "+message);
			alert.accept();
		}catch (Exception e) {
			log.info("Alert was not present");
		}
		return message;
	}

	public String getAlertMessageAndDismiss(){
		String message = null;
		try {
			waitForAlert();
			Alert alert = driver.switchTo().alert();		
			message = alert.getText();
			log.info("Alert message: "+message);
			alert.dismiss();
		}catch (Exception e) {
			log.info("Alert was not present");
		}
		return message;
	}
	
	public boolean isAlertPresent() {
		try{
			driver.switchTo().alert();
			return true;
		}catch (NoAlertPresentException exp){
			return false;
		}
	}
	
	public boolean isVisible(WebElement element) {
		try{
			waitForvisibilityOfElement(element);
			return element.isDisplayed();
		}catch (NoSuchElementException exp){
			return false;
		}
	}
	
	public void selectCheckbox(boolean clickOnCheckbox, WebElement element) {
		if(clickOnCheckbox && !element.isSelected()) {
			element.click();
		}
		else if(!clickOnCheckbox && element.isSelected()) {
			element.click();
		}
	}
	
	public void doubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().build().perform();
	}
}
