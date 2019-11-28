package com.automationpractice.utility;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	private static final DriverManager DRIVER_MANAGER = new DriverManager();
	private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();

	private DriverManager() {
		
	}
	
	public static DriverManager getInstance(){
		return DRIVER_MANAGER;
	}
	
	public synchronized void setDriver (WebDriver driver) {
		tDriver.set(driver);
	}

	public synchronized WebDriver getDriver () {
		WebDriver driver = tDriver.get();
		if (driver == null) {
			throw new IllegalStateException("Driver should have not been null!!");
		}
		return driver;
	}

}
