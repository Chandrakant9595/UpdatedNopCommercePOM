package com.nop.qa.pages;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import com.nop.qa.base.TestBase;

import io.qameta.allure.Step;

public class ProductAddPage extends TestBase {

	public ProductAddPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	@Step("Allure: Test step for validate Login Page Title")
	public String validateAddProductPageTitle() {
		return driver.getTitle();
	}

	@Step("Allure: Test step for validate Login Page URL")
	public String validateAddProductPageURL() {
		return driver.getCurrentUrl();
	}

}