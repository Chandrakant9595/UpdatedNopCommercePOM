package com.nop.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nop.qa.base.TestBase;
import com.nop.qa.pages.DashboardPage;
import com.nop.qa.pages.LoginPage;
import com.nop.qa.util.TestConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends TestBase{

	LoginPage loginPage;
	DashboardPage dashboardPage;
	
	public LoginPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	@Severity(SeverityLevel.CRITICAL)
	@Description("Allure: Test pre-condition")
	public void setUp() throws IOException {
		initalization();
		loginPage = new LoginPage();
	}
	
	@AfterMethod
	@Severity(SeverityLevel.CRITICAL)
	@Description("Allure: Test post-condition")
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1, description = "Test for validate the login page title")
	@Severity(SeverityLevel.NORMAL)
	@Description("Allure: Test for Validate the Login Page Title")
	public void varifyLoginPageTitle() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, TestConstants.loginPageTitle);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Allure: Test for Validate the Login Page URL")
	@Test(priority = 2, description = "Test for validate the login page url")
	public void verifyLoginPageURL() {
		String url = loginPage.validateLoginPageURL();
		Assert.assertEquals(url, TestConstants.loginPageURL);
	}
	
	@Test(priority = 3, description = "Test for validate the login page login functionality")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Allure: Test for Validate the Login Page Login functionality")
	public void verifyLoginPageLoginFunctionality() throws IOException {
		dashboardPage = loginPage.validateLoginFunctionality(prop.getProperty("username"), prop.getProperty("password"));
		String title = dashboardPage.validateDashboardPageTitle();
		Assert.assertEquals(title, TestConstants.dashboardPageTitle);
	}
}