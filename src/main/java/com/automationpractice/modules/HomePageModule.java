package com.automationpractice.modules;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pageobjects.HomePageObject;

public class HomePageModule {

	private HomePageObject homePageObject;
	
	public HomePageModule(WebDriver driver) {
		homePageObject = new HomePageObject(driver);
	}
	
	public void navigateToLoginPage() {
		homePageObject.clickOnSignInLink();
	}
}
