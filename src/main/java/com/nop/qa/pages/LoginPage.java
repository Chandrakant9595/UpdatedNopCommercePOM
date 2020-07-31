package com.nop.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nop.qa.base.TestBase;

import io.qameta.allure.Step;

public class LoginPage extends TestBase {

	@FindBy(css = "input[id='Email']")
	WebElement emailTxt;

	@FindBy(css = "input[id='Password']")
	WebElement passwordlTxt;

	@FindBy(css = "input[type='submit']")
	WebElement loginButton;

	public LoginPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	@Step("Allure: Test step for validate Login Page Title")
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	@Step("Allure: Test step for validate Login Page URL")
	public String validateLoginPageURL() {
		return driver.getCurrentUrl();
	}

	@Step("Allure: Test step for validate Login Page Login functionality")
	public DashboardPage validateLoginFunctionality(String un, String pas) throws IOException {
		emailTxt.clear();
		emailTxt.sendKeys(un);
		passwordlTxt.clear();
		passwordlTxt.sendKeys(pas);
		loginButton.click();
		return new DashboardPage();
	}
}
