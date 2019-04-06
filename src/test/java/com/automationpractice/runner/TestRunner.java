package com.automationpractice.runner;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automationpractice.scripts.CreateAccountScript;
import com.automationpractice.scripts.LoginScript;
import com.automationpractice.scripts.ProductCheckoutScript;

@Listeners (com.automationpractice.listener.Listener.class)
public class TestRunner extends TestBase{

	@Test
	public void TC001_CreateNewAccount() {
		testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		CreateAccountScript createAccountScript = new CreateAccountScript(driver);
		createAccountScript.createAnAccount(testCaseName);
	}

	@Test
	public void TC002_PurchasePrintedSummaryDress() {
		testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		ProductCheckoutScript productCheckoutScript = new ProductCheckoutScript(driver);
		productCheckoutScript.checkOutProduct(testCaseName);
	}
	
	@Test
	public void TC003_PurchasePrintedChiffonDress() {
		testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		ProductCheckoutScript productCheckoutScript = new ProductCheckoutScript(driver);
		productCheckoutScript.checkOutProduct(testCaseName);
	}
	
	@Test
	public void TC004_InvalidLogin() {
		testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		LoginScript loginScript = new LoginScript(driver);
		loginScript.loginTest(testCaseName);
	}
}
