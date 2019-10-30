package com.automationpractice.modules;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pageobjects.MyAccountPageObject;

import io.qameta.allure.Step;

public class MyAccountPageModule {

	private MyAccountPageObject myAccountPageObject;
	
	public MyAccountPageModule(WebDriver driver) {
		myAccountPageObject = new MyAccountPageObject(driver);
	}
	
	@Step ("Navigate to My Account screen")
	public String userNavigatedToMyAccount() {
		return myAccountPageObject.validatesUserIsInMyAccountsPage();
	}
	
	@Step ("Log out of My Store application")
	public void logOutOfApplication() {
		myAccountPageObject.logOut();
	}
	
	@Step ("Search for product: {0}")
	public void searchForProduct(String searchKey) {
		myAccountPageObject.enterSearchKey(searchKey);
		myAccountPageObject.submitSearch();
	}
}
