package com.nop.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nop.qa.base.TestBase;

import io.qameta.allure.Step;

public class ProductListPage extends TestBase{

	@FindBy(xpath = "//div[@class='pull-right']/a/i")
	WebElement addNewProductButton;
	
	public ProductListPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	@Step("Allure: Test step for click on Add New Product Button under the Product list page")
	public ProductAddPage clickOnAddNewProductButton() throws IOException {
		addNewProductButton.click();
		return new ProductAddPage();
	}
}