package com.automationpractice.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import com.automationpractice.utility.ConfigProperties;

public class CreateAccountPageObject {
	private static final Logger log = Logger.getLogger(CreateAccountPageObject.class);
	
	public CreateAccountPageObject(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 
				Integer.parseInt(ConfigProperties.getProperty("webDriverWaitTime"))), this);
	}

	@FindBy(id = "id_gender1")
	private WebElement title;
	
	@FindBy(id = "customer_firstname")
	private WebElement firstName;
	
	@FindBy(id = "customer_lastname")
	private WebElement lastName;
	
	@FindBy(id = "passwd")
	private WebElement password;
	
	@FindBy(id = "address1")
	private WebElement address;
	
	@FindBy(id = "city")
	private WebElement city;
	
	@FindBy(id = "id_state")
	private WebElement state;
	
	@FindBy(id = "postcode")
	private WebElement zipCode;
	
	@FindBy(id = "id_country")
	private WebElement country;
	
	@FindBy(id = "phone_mobile")
	private WebElement mobilePhone;
	
	@FindBy(id = "alias")
	private WebElement aliasAddress;
	
	@FindBy(id = "submitAccount")
	private WebElement register_button;
	
	public void enterFirstName(String firstName) {
		this.firstName.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		this.lastName.sendKeys(lastName);
	}
	
	public void enterPassword(String password) {
		this.password.sendKeys(password);
	}
	
	public void enterAddress(String address) {
		this.address.clear();
		this.address.sendKeys(address);
	}
	
	public void selectCountry(String country) {
		new Select(this.country).selectByVisibleText(country);
	}
	
	public void selectState(String state) {
		new Select(this.state).selectByVisibleText(state);
	}
	
	public void enterZipCode(String zipCode) {
		this.zipCode.sendKeys(zipCode);
	}
	
	public void enterCity(String city) {
		this.city.sendKeys(city);
	}
	
	public void enterMobilePhone(String mobilePhone) {
		this.mobilePhone.sendKeys(mobilePhone);
	}
	
	public void enterAliasAddress(String aliasAddress) {
		this.aliasAddress.clear();
		this.aliasAddress.sendKeys(aliasAddress);
	}
	
	public void clickOnRegisterButton() {
		this.register_button.click();
	}
}
