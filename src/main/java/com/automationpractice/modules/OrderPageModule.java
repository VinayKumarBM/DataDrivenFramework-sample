package com.automationpractice.modules;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pageobjects.OrderPageObject;

public class OrderPageModule {
	
	private OrderPageObject orderPageObject;
	
	public OrderPageModule(WebDriver driver) {
		orderPageObject = new OrderPageObject(driver);
	}

	public String confirmOrder() {
		orderPageObject.clickSummaryProceedToCheckout();
		orderPageObject.clickAddressProceedToCheckout();
		orderPageObject.agreeToTerms();
		orderPageObject.clickCarrierProceedToCheckout();
		orderPageObject.clickPayByCheque();
		orderPageObject.clickOnConfirmOrder();
		return orderPageObject.orderConfirmationMsg();
	}
}
