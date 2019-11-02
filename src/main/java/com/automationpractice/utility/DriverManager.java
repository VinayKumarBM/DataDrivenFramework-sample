package com.automationpractice.utility;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	 private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
	 
	    public synchronized static void setDriver (WebDriver driver) {
	    	tDriver.set(driver);
	    }
	 
	    public synchronized static WebDriver getDriver () {
	    	WebDriver driver = tDriver.get();
	        if (driver == null) {
	            throw new IllegalStateException("Driver should have not been null!!");
	        }
	        return driver;
	    }

}
