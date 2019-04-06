package com.automationpractice.modules;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pageobjects.SearchPageObject;

public class SearchPageModule {
	
	private SearchPageObject searchPageObject;
	
	public SearchPageModule(WebDriver driver) {
		searchPageObject = new SearchPageObject(driver);
	}

	public boolean selectProduct(String product) {
		boolean found = searchPageObject.clickOnProduct(product);
		searchPageObject.clickProceedToCheckout();
		return found;
	}
}
