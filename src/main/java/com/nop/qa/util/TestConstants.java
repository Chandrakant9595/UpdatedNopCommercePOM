package com.nop.qa.util;

import java.io.IOException;

import com.nop.qa.base.TestBase;

public class TestConstants extends TestBase{

	public TestConstants() throws IOException {
		super();
	}

	//login page 
	public static String loginPageTitle = "Your store. Login";
	public static String loginPageURL = "https://admin-demo.nopcommerce.com/login";

	//dashboard page
	public static String dashboardPageTitle = "Dashboard / nopCommerce administration";
	public static String dashboardPageURL = "https://admin-demo.nopcommerce.com/admin/";
	
	//Add new product page
	public static String addNewProductPageTitle = "Add a new product / nopCommerce administration";
	public static String addNewProductPageURL = "https://admin-demo.nopcommerce.com/Admin/Product/Create";
}
