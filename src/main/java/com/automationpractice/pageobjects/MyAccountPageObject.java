package com.automationpractice.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.automationpractice.utility.ConfigProperties;

public class MyAccountPageObject {
	private static final Logger log = Logger.getLogger(MyAccountPageObject.class.getName());
	
	public MyAccountPageObject(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 
				Integer.parseInt(ConfigProperties.getProperty("webDriverWaitTime"))), this);
	}
	
	@FindBy(css = ".page-heading")
	private WebElement pageHeading;
	
	@FindBy(linkText = "Sign out")
	private WebElement signOut;
	
	@FindBy(name = "search_query")
	private WebElement searchQuery;
	
	@FindBy(name = "submit_search")
	private WebElement submitSearch;

	public String validatesUserIsInMyAccountsPage() {
		return pageHeading.getText().trim();
	}
	
	public void logOut() {
		signOut.click();
	}

	public void enterSearchKey(String searchKey) {
		searchQuery.clear();
		searchQuery.sendKeys(searchKey);
	}
	
	public void submitSearch() {
		submitSearch.click();
	}
}
