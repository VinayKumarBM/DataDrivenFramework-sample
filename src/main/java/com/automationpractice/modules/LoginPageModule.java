package com.automationpractice.modules;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pageobjects.LoginPageObject;
import com.automationpractice.utility.ConfigReader;
import com.automationpractice.utility.DateUtility;

public class LoginPageModule {

	private LoginPageObject loginPageObject;
	
	public LoginPageModule(WebDriver driver) {
		loginPageObject = new LoginPageObject(driver);
	}
	
	public String enterEmailAddressToCreateAccount() {
		String email = String.format("test%s@gmail.com", DateUtility.getStringDate("DDhhmmss"));
		loginPageObject.enterEmailToCreateAccount(email);
		loginPageObject.clickOnCreateAccountButton();
		return email;
	}
	
	public void loginToMyStore(Map<String, String> testDataMap) {
		loginPageObject.enterEmailToSignin(testDataMap.get(ConfigReader.getProperty("emailIDColumn")));
		loginPageObject.enterPassword(testDataMap.get(ConfigReader.getProperty("passwordColumn")));
		loginPageObject.clickOnLoginButton();
	}

}
