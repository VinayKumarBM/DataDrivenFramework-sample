package com.automationpractice.modules;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pageobjects.HomePageObject;

import io.qameta.allure.Step;

public class HomePageModule {

	private HomePageObject homePageObject;
	
	public HomePageModule(WebDriver driver) {
		homePageObject = new HomePageObject(driver);
	}
	
	@Step ("Navigate to Login Page")
	public void navigateToLoginPage() {
		homePageObject.clickOnSignInLink();
	}
}
