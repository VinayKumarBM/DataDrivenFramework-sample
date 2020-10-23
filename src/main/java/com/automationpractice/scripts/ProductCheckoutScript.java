package com.automationpractice.scripts;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automationpractice.modules.HomePageModule;
import com.automationpractice.modules.LoginPageModule;
import com.automationpractice.modules.MyAccountPageModule;
import com.automationpractice.modules.OrderPageModule;
import com.automationpractice.modules.SearchPageModule;
import com.automationpractice.utility.ConfigReader;
import com.automationpractice.utility.ExcelUtility;
import com.automationpractice.utility.ReportManager;
import com.automationpractice.utility.ResourceUtility;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ProductCheckoutScript {
	private Logger log = Logger.getLogger(ProductCheckoutScript.class.getName());
	private WebDriver driver;
	private HomePageModule homePageModule;
	private LoginPageModule loginPageModule;
	private SearchPageModule searchPageModule;
	private MyAccountPageModule myAccountPageModule;
	private OrderPageModule orderPageModule;
	private String excelSheetPath = ResourceUtility.getDataFolderPath();
	
	public ProductCheckoutScript(WebDriver driver) {
		this.driver = driver;
		homePageModule = new HomePageModule(driver);
		loginPageModule = new LoginPageModule(driver);
		searchPageModule = new SearchPageModule(driver);
		myAccountPageModule	= new MyAccountPageModule(driver);
		orderPageModule = new OrderPageModule(driver);
	}

	public void checkOutProduct(String testCaseName) {
		ExtentTest test = ReportManager.getTest();
		try {
			Map<String, String> testDataMap = new ExcelUtility().getData(testCaseName, excelSheetPath, 
					ConfigReader.getProperty("testDataSheetName"));

			homePageModule.navigateToLoginPage();
			test.log(Status.INFO, "Navigated to login page.");
			loginPageModule.loginToMyStore(testDataMap);
			test.log(Status.INFO, "Logged into the Application.");
			myAccountPageModule.searchForProduct(testDataMap.get(ConfigReader.getProperty("searchKeyColumn")));
			test.log(Status.INFO, "Searching for product: "+ConfigReader.getProperty("searchKeyColumn"));
			boolean status = searchPageModule.selectProduct(testDataMap.get(ConfigReader.getProperty("productColumn")));
			Assert.assertTrue(status, "Product was not found.");
			test.log(Status.INFO, "Added product to cart.");
			String orderConfirmMsg = orderPageModule.confirmOrder();
			Assert.assertEquals(orderConfirmMsg, "Your order on My Store is complete.","Order was not confirmed");
			test.log(Status.INFO, "Order on My Store is complete.");
			myAccountPageModule.logOutOfApplication();
			test.log(Status.INFO, "Logged out of Application");
		} catch(Exception exp) {
			log.error(exp.getMessage(), exp);
			test.error("Exception Occured: "+Thread.currentThread().getStackTrace());
			Assert.fail(testCaseName);
		}
	}
}
