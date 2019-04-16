package com.automationpractice.listener;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.automationpractice.utility.ElementOperations;

public class WebDriverListener implements WebDriverEventListener{
	private Logger log = Logger.getLogger(WebDriverListener.class);
	private String value;
	
	@Override
	public void afterAlertAccept(WebDriver driver) {
		log.info("Accepted Alert");
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		log.info("Dismissed Alert");
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		value = element.toString();
		log.info("Entered value into ["+value.substring(value.indexOf(">")+2));
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		log.info("Screen shot was taken successfully.");		
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		new ElementOperations(driver).highlightElement(element);
		log.info("Captured Text message: "+text);
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		log.info("Navigated back to previous page "+driver.getCurrentUrl());	
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		log.info("Navigated back to forward "+driver.getCurrentUrl());		
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		log.info("Refreshed the current page");
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		log.info("Navigated to: "+url);		
	}

	@Override
	public void afterScript(String text, WebDriver driver) {
		
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		log.info("Switched to new Window: "+windowName);
	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
	
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		log.info("The searching for webelement using "+ by.toString() + " has started.");
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		
	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		
	}

	@Override
	public void beforeScript(String text, WebDriver driver) {		
		
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		log.info("Current Window: "+windowName);
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		log.error("Exception occured: ",throwable);		
	}
}
