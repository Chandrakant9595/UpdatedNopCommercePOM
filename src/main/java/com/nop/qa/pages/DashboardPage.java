package com.nop.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nop.qa.base.TestBase;

import io.qameta.allure.Step;

public class DashboardPage extends TestBase{

	@FindBy(xpath = "//span[text()='Catalog']")
	WebElement catlogLink;
	
	@FindBy(xpath = "//span[text()='Products']")
	WebElement productsLink;
	
	public DashboardPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	@Step("Allure: Test step for validate Dashboard Page Title")
	public String validateDashboardPageTitle() {
		return driver.getTitle();
	}
	
	@Step("Allure: Test step for click on Products link under the catlog link")
	public ProductListPage clickOnProductsLink() throws IOException {
		catlogLink.click();
		productsLink.click();
		return new ProductListPage();
	}
}