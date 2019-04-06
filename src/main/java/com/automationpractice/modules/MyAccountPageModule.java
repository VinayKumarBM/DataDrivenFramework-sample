package com.automationpractice.modules;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pageobjects.MyAccountPageObject;

public class MyAccountPageModule {

	private MyAccountPageObject myAccountPageObject;
	
	public MyAccountPageModule(WebDriver driver) {
		myAccountPageObject = new MyAccountPageObject(driver);
	}
	
	public String userNavigatedToMyAccount() {
		return myAccountPageObject.validatesUserIsInMyAccountsPage();
	}
	
	public void logOutOfApplication() {
		myAccountPageObject.logOut();
	}
	
	public void searchForProduct(String searchKey) {
		myAccountPageObject.enterSearchKey(searchKey);
		myAccountPageObject.submitSearch();
	}
}
