package com.automationpractice.runner;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automationpractice.listener.Listener;
import com.automationpractice.scripts.CreateAccountScript;
import com.automationpractice.scripts.LoginScript;
import com.automationpractice.scripts.ProductCheckoutScript;
import com.automationpractice.utility.DriverManager;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners ({Listener.class})
@Epic ("My Store Epic")
@Feature ("To test the functionality of My Store application")
public class TestRunner extends TestBase{

	@Test (groups = { "regression"})
	@Severity (SeverityLevel.NORMAL)
	@Description ("Test to verify the creation of new account")
	@Story ("Create Account")
	public void TC001_CreateNewAccount() {
		String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		CreateAccountScript createAccountScript = new CreateAccountScript(DriverManager.getDriver());
		createAccountScript.createAnAccount(testCaseName);
	}

	@Test (groups = { "regression","smoke"})
	@Severity (SeverityLevel.NORMAL)
	@Description ("Test to verify the purchase the Printed Summary Dress")
	@Story ("Purchase Dress")
	public void TC002_PurchasePrintedSummaryDress() {
		String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		ProductCheckoutScript productCheckoutScript = new ProductCheckoutScript(DriverManager.getDriver());
		productCheckoutScript.checkOutProduct(testCaseName);
	}
	
	@Test (groups = { "regression"}) 
	@Severity (SeverityLevel.NORMAL)
	@Description ("Test to verify the purchase the Printed Chiffon Dress")
	@Story ("Purchase Dress")
	public void TC003_PurchasePrintedChiffonDress() {
		String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		ProductCheckoutScript productCheckoutScript = new ProductCheckoutScript(DriverManager.getDriver());
		productCheckoutScript.checkOutProduct(testCaseName);
	}
	
	@Test
	@Severity (SeverityLevel.NORMAL)
	@Description ("Test to verify the login functionality with invalid credentials")
	@Story ("Login to Application")
	public void TC004_InvalidLogin() {
		String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		LoginScript loginScript = new LoginScript(DriverManager.getDriver());
		loginScript.loginTest(testCaseName);
	}
}
