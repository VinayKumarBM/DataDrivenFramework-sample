package com.automationpractice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.automationpractice.utility.ConfigReader;
import com.automationpractice.utility.Log;

public class OrderPageObject {
	
	public OrderPageObject(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 
				Integer.parseInt(ConfigReader.getProperty("webDriverWaitTime"))), this);
	}
	
	@FindBy(xpath = "//div[@id='center_column']//a[@title='Proceed to checkout']/span")
	private WebElement proceedToCheckoutSummary;
	
	@FindBy(name = "processAddress")
	private WebElement proceedToCheckoutAddress;
	
	@FindBy(id = "cgv")
	private WebElement agreeTerms;
	
	@FindBy(name = "processCarrier")
	private WebElement proceedToCheckoutCarrier;
	
	@FindBy(xpath = "//a[@class='cheque']")
	private WebElement payByCheck;
	
	@FindBy(xpath = "//span[text()='I confirm my order']")
	private WebElement confirmOrder;
	
	@FindBy(xpath = "//p[@class='alert alert-success']")
	private WebElement orderConfirmMsg;
	
	public void clickSummaryProceedToCheckout() {
		Log.info("Clicking on Summary Proceed to checkout");
		proceedToCheckoutSummary.click();
	}
	
	public void clickAddressProceedToCheckout() {
		Log.info("Clicking on Address Proceed to checkout");
		proceedToCheckoutAddress.click();
	}
	
	public void agreeToTerms() {
		Log.info("Agreeing to Terms of Service");
		agreeTerms.click();
	}
	
	public void clickCarrierProceedToCheckout() {
		Log.info("Clicking on Carrier Proceed to checkout");
		proceedToCheckoutCarrier.click();
	}
	
	public void clickPayByCheque() {
		Log.info("Clicking on Pay By Cheque");
		payByCheck.click();
	}
	
	public void clickOnConfirmOrder() {
		Log.info("Clicking on I confirm Order");
		confirmOrder.click();
	}
	
	public String orderConfirmationMsg() {
		return orderConfirmMsg.getText();
	}
}
