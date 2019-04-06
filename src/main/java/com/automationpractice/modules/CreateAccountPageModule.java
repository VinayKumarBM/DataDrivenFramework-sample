package com.automationpractice.modules;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pageobjects.CreateAccountPageObject;
import com.automationpractice.utility.ConfigProperties;

public class CreateAccountPageModule {
	private CreateAccountPageObject createAccountPageObject; 
	
	public CreateAccountPageModule(WebDriver driver) {
		createAccountPageObject = new CreateAccountPageObject(driver);
	}

	public void createAccount(Map <String, String> testDataMap) {
		createAccountPageObject.enterFirstName(testDataMap.get(ConfigProperties.getProperty("firstNameColumn")));
		createAccountPageObject.enterLastName(testDataMap.get(ConfigProperties.getProperty("lastNameColumn")));
		createAccountPageObject.enterPassword(testDataMap.get(ConfigProperties.getProperty("passwordColumn")));
		createAccountPageObject.enterAddress(testDataMap.get(ConfigProperties.getProperty("addressColumn")));
		createAccountPageObject.enterCity(testDataMap.get(ConfigProperties.getProperty("cityColumn")));
		createAccountPageObject.selectCountry(testDataMap.get(ConfigProperties.getProperty("countryColumn")));
		createAccountPageObject.selectState(testDataMap.get(ConfigProperties.getProperty("stateColumn")));
		createAccountPageObject.enterZipCode(testDataMap.get(ConfigProperties.getProperty("zipCodeColumn")));
		createAccountPageObject.enterMobilePhone(testDataMap.get(ConfigProperties.getProperty("mobilePhoneColumn")));
		createAccountPageObject.enterAliasAddress(testDataMap.get(ConfigProperties.getProperty("aliasAddressColumn")));
		createAccountPageObject.clickOnRegisterButton();
	}
}
