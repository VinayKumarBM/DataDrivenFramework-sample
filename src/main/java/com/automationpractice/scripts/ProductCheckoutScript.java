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
import com.automationpractice.utility.CommonFunctions;
import com.automationpractice.utility.ConfigProperties;
import com.automationpractice.utility.ExcelUtils;
import com.automationpractice.utility.ReportManager;
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
			String excelSheetPath = CommonFunctions.basePath+ConfigProperties.getProperty("testDataPath")
			+ConfigProperties.getProperty("excelSheetName");
			Map<String, String> testDataMap = ExcelUtils.getData(testCaseName, excelSheetPath, 
					ConfigProperties.getProperty("testDataSheetName"));

			homePageModule.navigateToLoginPage();
			test.log(Status.INFO, "Navigated to login page.");
			loginPageModule.loginToMyStore(testDataMap);
			test.log(Status.INFO, "Logged into the Application.");
			myAccountPageModule.searchForProduct(testDataMap.get(ConfigProperties.getProperty("searchKeyColumn")));
			test.log(Status.INFO, "Searching for product: "+ConfigProperties.getProperty("searchKeyColumn"));
			boolean status = searchPageModule.selectProduct(testDataMap.get(ConfigProperties.getProperty("productColumn")));
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
