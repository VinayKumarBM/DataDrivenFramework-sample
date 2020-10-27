package com.automationpractice.listener;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.automationpractice.utility.ElementOperations;
import com.automationpractice.utility.Log;

public class WebDriverListener implements WebDriverEventListener{
	private String value;
	
	@Override
	public void afterAlertAccept(WebDriver driver) {
		Log.info("Accepted Alert");
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		Log.info("Dismissed Alert");
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		value = element.toString();
		Log.info("Entered value into ["+value.substring(value.indexOf(">")+2));
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		Log.info("Screen shot was taken successfully.");		
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		new ElementOperations(driver).highlightElement(element);
		Log.info("Captured Text message: "+text);
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		Log.info("Navigated back to previous page "+driver.getCurrentUrl());	
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		Log.info("Navigated back to forward "+driver.getCurrentUrl());		
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		Log.info("Refreshed the current page");
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("Navigated to: "+url);		
	}

	@Override
	public void afterScript(String text, WebDriver driver) {
		
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		Log.info("Switched to new Window: "+windowName);
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
		Log.info("The searching for webelement using "+ by.toString() + " has started.");
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
		Log.info("Current Window: "+windowName);
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		Log.error("Exception occured: ",throwable);		
	}
}
