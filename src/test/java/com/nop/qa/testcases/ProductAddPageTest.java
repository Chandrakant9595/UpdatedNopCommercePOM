package com.nop.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nop.qa.base.TestBase;
import com.nop.qa.pages.DashboardPage;
import com.nop.qa.pages.LoginPage;
import com.nop.qa.pages.ProductAddPage;
import com.nop.qa.pages.ProductListPage;
import com.nop.qa.util.TestConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ProductAddPageTest extends TestBase {

	LoginPage loginPage;
	DashboardPage dashboardPage;
	ProductListPage productListPage;
	ProductAddPage productAddPage;

	public ProductAddPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	@Severity(SeverityLevel.CRITICAL)
	@Description("Allure: Test pre-condition")
	public void setUp() throws IOException {
		initalization();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		productListPage = new ProductListPage();
		productAddPage = new ProductAddPage();

		dashboardPage = loginPage.validateLoginFunctionality(prop.getProperty("username"),prop.getProperty("password"));
		productListPage = dashboardPage.clickOnProductsLink();
		productAddPage = productListPage.clickOnAddNewProductButton();
	}

	@AfterMethod
	@Severity(SeverityLevel.CRITICAL)
	@Description("Allure: Test post-condition")
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, description = "Test for validate the Add New Product page Title")
	@Severity(SeverityLevel.NORMAL)
	@Description("Allure: Test for Validate the Add New Product Page Title")
	public void verifyAddProductPageTitle() throws IOException {
		String title = productAddPage.validateAddProductPageTitle();
		Assert.assertEquals(title, TestConstants.addNewProductPageTitle);
	}
	
	@Test(priority = 2, description = "Test for validate the Add New Product page URL")
	@Severity(SeverityLevel.NORMAL)
	@Description("Allure: Test for Validate the Add New Product Page URL")
	public void verifyAddProductPageURL() {
		String url = productAddPage.validateAddProductPageURL();
		Assert.assertEquals(url, TestConstants.addNewProductPageURL);
	}
}