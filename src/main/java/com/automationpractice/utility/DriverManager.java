package com.automationpractice.utility;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	private static DriverManager driverManager;
	private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();

	private DriverManager() {
		
	}
	
	public static DriverManager getInstance(){
		if(driverManager == null) {
			synchronized (DriverManager.class) {
				driverManager = new DriverManager();
			}
		}
		return driverManager;
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
