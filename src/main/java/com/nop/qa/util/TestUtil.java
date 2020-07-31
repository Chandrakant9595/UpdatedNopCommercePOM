package com.nop.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nop.qa.base.TestBase;

public class TestUtil extends TestBase {

	public TestUtil() throws IOException {
		super();
	}

	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 30;

	public static String parentWindow;
	public static String childWindow;

	public static Actions actions;
	public static Select select;
	public static Alert alert;

	public static String TESTDATA_SHEET_PATH = "D:\\Projects\\NopCommercePOMUpdatedNew\\src\\main\\java\\com\\nop\\qa\\testdata\\TestData.xlsx";

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;

	// data driven function
	public static Object[][] getTestData(String sheetName) throws InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}

	// screen shot function
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	// page scroll down function
	public static void scrollPage() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}

	// get current date function
	public static String getCurrectDate() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		return timeStamp;
	}

	// replace string function
	public static String replaceString(String rString) {
		return rString.replaceAll("[^a-zA-Z0-9]", "");
	}

	// trim string function
	public static String trimString(String tString) {
		return tString.trim();
	}

	// Switch to child window
	public static void switchToChildWindow() {
		parentWindow = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		java.util.Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			childWindow = itr.next();
			if (!parentWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
			}
		}
	}

	// switch to parent window
	public static void switchToParentWindow() {
		driver.switchTo().window(parentWindow);
	}

	// wait for web element
	public static void waitForWebElementPresent(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// To Switch into a Frame using Name.
	public void switchToFrame(String frameName) {
		try {
			driver.switchTo().frame(frameName);
			System.out.println("Navigated to Frame with Name ::: " + frameName);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to Locate Frame with Name ::: " + frameName + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to Navigate to Frame with Name ::: " + frameName + e.getStackTrace());
		}
	}

	// To Select a value from Drop Down by using SelectByVisibleText Method.
	public static void selectValueFromDropDownByText(WebElement element, String value) {
		select = new Select(element);
		select.selectByVisibleText(value);
	}

	// To Select a value from Drop Down by using SelectByIndex Method.
	public static void selectValueFromDropDownByIndex(WebElement element, int value) {
		select = new Select(element);
		select.selectByIndex(value);
	}

	// To Select a value from Drop Down by using SelectByValue Method.
	public static void selectValueFromDropDownByValue(WebElement element, String value) {
		select = new Select(element);
		select.selectByValue(value);
	}

	// To Print all the Values and Select a Required Value from Drop Down.
	public static void selectDropDownValue(String xpathValue, String value) {
		List<WebElement> monthList = driver.findElements(By.xpath(xpathValue));
		System.out.println(monthList.size());

		for (int i = 0; i < monthList.size(); i++) {
			System.out.println(monthList.get(i).getText());
			if (monthList.get(i).getText().equals(value)) {
				monthList.get(i).click();
				break;
			}
		}
	}

	// To Accept Alert Pop-Up.
	public static void acceptAlertPopup() throws InterruptedException {
		try {
			alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			Thread.sleep(2000);
			alert.accept();
			System.out.println("Alert Accepted Successfully");
		} catch (Exception e) {
			System.out.println("Something Went Wrong ==>> Please Check ::: " + e.getMessage());
		}
	}

	// To Dismiss Alert Pop-Up.
	public static void dismissAlertPopup() throws InterruptedException {
		try {
			alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			Thread.sleep(2000);
			alert.dismiss();
			System.out.println("Alert Dismissed Successfully");
		} catch (Exception e) {
			System.out.println("Something Went Wrong ==>> Please Check ::: " + e.getMessage());
		}
	}

	// To Click on Element using Actions Class.
	public void clickOnElementUsingActions(WebElement element) {
		actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}

	// Mouse Hover
	public static void moveToElement(WebDriver driver, WebElement element) {
		actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	// To Perform Drag and Drop action using Actions Class - 1.
	public static void dragAndDrop_1(WebDriver driver, WebElement sourceElement, WebElement destinationElement) {
		actions = new Actions(driver);
		actions.dragAndDrop(sourceElement, destinationElement).pause(Duration.ofSeconds(2)).release().build().perform();
	}

	// To Perform Drag and Drop action using Actions Class - 2.
	public static void dragAndDrop_2(WebDriver driver, WebElement sourceElement, WebElement destinationElement) {
		actions = new Actions(driver);
		actions.clickAndHold(sourceElement).pause(Duration.ofSeconds(2)).moveToElement(destinationElement)
				.pause(Duration.ofSeconds(2)).release().build().perform();
	}

	// To Perform Right Click action using Actions Class.
	public static void rightClick(WebDriver driver, WebElement element) {
		actions = new Actions(driver);
		actions.contextClick(element).build().perform();
	}

	// To perform Double Click action using Actions Class.
	public static void doubleClick(WebDriver driver, WebElement element) {
		actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
	}

	// To Click on any WebElement by using JavaScript Executor.
	public static void clickElementByJavaScript(WebElement element, WebDriver driver) {
		js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", element);
	}

	// To Refresh Browser by using JavaScript Executor.
	public static void refreshBrowserByJavaScript(WebDriver driver) {
		js = ((JavascriptExecutor) driver);
		js.executeScript("history.go(0)");
	}

	// To Get Title of the Page by using JavaScript Executor.
	public static void getPageTitleByJavaScript(WebDriver driver) {
		js = ((JavascriptExecutor) driver);
		String pageTitle = js.executeScript("return document.title;").toString();
		System.out.println("The Title of the Page is ::: " + pageTitle);
	}

	// To Get the Page Inner Text by using JavaScript Executor.
	public static void getPageInnerTextByJavaScript(WebDriver driver) {
		js = ((JavascriptExecutor) driver);
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		System.out.println("The Text of the Page is ::: " + pageText);
	}
}
