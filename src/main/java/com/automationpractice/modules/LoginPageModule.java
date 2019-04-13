package com.automationpractice.modules;

import java.util.Map;
import java.util.Random;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pageobjects.LoginPageObject;
import com.automationpractice.utility.ConfigReader;

public class LoginPageModule {

	private LoginPageObject loginPageObject;
	
	public LoginPageModule(WebDriver driver) {
		loginPageObject = new LoginPageObject(driver);
	}
	
	public String enterEmailAddressToCreateAccount() {
		Random random = new Random();
		int randNumb = random.nextInt(999);
		String email = String.format("test%s@gmail.com", randNumb);
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
