package com.automationpractice.modules;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pageobjects.CreateAccountPageObject;
import com.automationpractice.utility.ConfigReader;

import io.qameta.allure.Step;

public class CreateAccountPageModule {
	private CreateAccountPageObject createAccountPageObject; 
	
	public CreateAccountPageModule(WebDriver driver) {
		createAccountPageObject = new CreateAccountPageObject(driver);
	}
	
	@Step ("Entering the Account details")
	public void createAccount(Map <String, String> testDataMap) {
		createAccountPageObject.enterFirstName(testDataMap.get(ConfigReader.getProperty("firstNameColumn")));
		createAccountPageObject.enterLastName(testDataMap.get(ConfigReader.getProperty("lastNameColumn")));
		createAccountPageObject.enterPassword(testDataMap.get(ConfigReader.getProperty("passwordColumn")));
		createAccountPageObject.enterAddress(testDataMap.get(ConfigReader.getProperty("addressColumn")));
		createAccountPageObject.enterCity(testDataMap.get(ConfigReader.getProperty("cityColumn")));
		createAccountPageObject.selectCountry(testDataMap.get(ConfigReader.getProperty("countryColumn")));
		createAccountPageObject.selectState(testDataMap.get(ConfigReader.getProperty("stateColumn")));
		createAccountPageObject.enterZipCode(testDataMap.get(ConfigReader.getProperty("zipCodeColumn")));
		createAccountPageObject.enterMobilePhone(testDataMap.get(ConfigReader.getProperty("mobilePhoneColumn")));
		createAccountPageObject.enterAliasAddress(testDataMap.get(ConfigReader.getProperty("aliasAddressColumn")));
		createAccountPageObject.clickOnRegisterButton();
	}
}
