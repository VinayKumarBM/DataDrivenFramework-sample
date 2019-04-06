package com.automationpractice.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.automationpractice.utility.ConfigProperties;

public class HomePageObject {
	private static final Logger log = Logger.getLogger(HomePageObject.class);
	
	public HomePageObject(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 
				Integer.parseInt(ConfigProperties.getProperty("webDriverWaitTime"))), this);
	}

	@FindBy(name = "search_query")
	private WebElement searchBox;

	@FindBy(name = "submit_search")
	private WebElement searchButton;

	@FindBy(xpath = "//div[@class='right-block']/h5/a[@class='product-name']")
	List <WebElement> productList;

	@FindBy(xpath = "//span[@class='cat-name']")
	private WebElement subCategory;

	@FindBy(partialLinkText = "Sign in")
	private WebElement signIn_Link;
	
	public void productSearch(String searchKey) {
		searchBox.clear();
		searchBox.sendKeys(searchKey);
		searchButton.click();
	}

	public boolean productMatches(String searchKey) {
		int productCount = productList.size();
		System.out.println("Number of Product Listed: "+productCount);
		String product = null;
		for(int i=0; i<productCount; i++) {
			product = productList.get(i).getText().toLowerCase();
			System.out.println(product);
			if(! product.contains(searchKey)) {
				return false;
			}
		}
		return true;
	}

	public void selectCategory(String category, WebDriver driver) {
		log.info("Selecting Category "+category);
		driver.findElement(By.linkText(category.toUpperCase())).click();
	}

	public void selectSubCategory(String subCategory, WebDriver driver) {
		log.info("Selecting Sub-Category "+subCategory);
		driver.findElement(By.linkText(subCategory.toUpperCase())).click();
	}

	public String validateSubCategory() {
		return subCategory.getText().trim();
	}

	public void clickOnSignInLink() {
		signIn_Link.click();
	}
}
