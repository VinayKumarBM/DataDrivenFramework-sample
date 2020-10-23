package com.automationpractice.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.automationpractice.listener.WebDriverListener;

public class DriverManager {

	private static DriverManager driverManager;
	private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
	private static ThreadLocal<EventFiringWebDriver> tEventFiringWebDriver = new ThreadLocal<>();
	private static ThreadLocal<WebDriverListener> tWebDriverListener = new ThreadLocal<>();

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
	
	public synchronized void setWebDriverListener (WebDriverListener listener) {
		tWebDriverListener.set(listener);
	}

	public synchronized WebDriverListener getWebDriverListener () {
		WebDriverListener listener = tWebDriverListener.get();
		if (listener == null) {
			throw new IllegalStateException("WebDriverListener should have not been null!!");
		}
		return listener;
	}
	
	public synchronized void setEventFiringWebDriver (EventFiringWebDriver eventFiringWebDriver) {
		tEventFiringWebDriver.set(eventFiringWebDriver);
	}

	public synchronized EventFiringWebDriver getEventFiringWebDriver () {
		EventFiringWebDriver eventFiringWebDriver = tEventFiringWebDriver.get();
		if (eventFiringWebDriver == null) {
			throw new IllegalStateException("EventFiringWebDriver should have not been null!!");
		}
		return eventFiringWebDriver;
	}

}
