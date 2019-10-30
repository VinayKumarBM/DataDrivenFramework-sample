package com.automationpractice.modules;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pageobjects.OrderPageObject;

import io.qameta.allure.Step;

public class OrderPageModule {
	
	private OrderPageObject orderPageObject;
	
	public OrderPageModule(WebDriver driver) {
		orderPageObject = new OrderPageObject(driver);
	}

	@Step ("Placing the order")
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
