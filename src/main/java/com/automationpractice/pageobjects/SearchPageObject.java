package com.automationpractice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.automationpractice.utility.ConfigReader;
import com.automationpractice.utility.Log;

public class SearchPageObject {
	private WebDriver driver;
	
	public SearchPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 
				Integer.parseInt(ConfigReader.getProperty("webDriverWaitTime"))), this);
	}
	
	@FindBy(xpath = "//a[@class='product_img_link']")
	private List <WebElement> productList;
	
	@FindBy(xpath = "//a[@title='Proceed to checkout']/span")
	private WebElement proceedToCheckout;
	
	private String addToCartButtonXpath = "//a[@title='%s']/../../div/a/span[text()='Add to cart']";
	
	public boolean clickOnProduct(String product) {
		for(int i=0; i<productList.size(); i++) {
			if(productList.get(i).getAttribute("title").equalsIgnoreCase(product)) {
				//productList.get(i).click();
				Actions action = new Actions(driver);
				action.moveToElement(productList.get(i)).perform();
				WebElement element = driver.findElement(By.xpath(String.format(addToCartButtonXpath, product)));
				action.moveToElement(element).click().build().perform();
				Log.info(product+" was found!");
				return true;
			}
		}
		Log.info(product+" was NOT found!!");
		return false;
	}
	
	public void clickProceedToCheckout() {
		proceedToCheckout.click();
	}
}
