package com.automationpractice.modules;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pageobjects.SearchPageObject;

import io.qameta.allure.Step;

public class SearchPageModule {
	
	private SearchPageObject searchPageObject;
	
	public SearchPageModule(WebDriver driver) {
		searchPageObject = new SearchPageObject(driver);
	}

	@Step ("Select a product: {0}")
	public boolean selectProduct(String product) {
		boolean found = searchPageObject.clickOnProduct(product);
		searchPageObject.clickProceedToCheckout();
		return found;
	}
}
