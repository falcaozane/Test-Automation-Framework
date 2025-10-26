package com.orangehrm.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;

public class ActionDriver {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	// constructor to initialize WebDriver and WebDriverWait
	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicitWait"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
	}
	
	// method to click on an element
	public void click(By by) {
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to click on element: " + e.getMessage());
		}
	}
	
	// method to enter text in a text field
	public void enterText(By by, String value) {
		try {
			waitForElementToBeVisible(by);
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to enter text in element: " + e.getMessage());
		}
	}
	
	// method to wait for an element to be clickable
	private void waitForElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element is not clickable: " + e.getMessage());
		}
	}
	
	// method to get text from an element
	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to get the text from element: " + e.getMessage());
			return "";
		}
	}
	
	// method to compare text from an element with expected text
	public void compareText(By by, String expectedText) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if(actualText.equals(expectedText)) {
				System.out.println("Text matches: " + actualText);
			} else {
				System.out.println("Text does not match. Expected: " + expectedText + ", but found: " + actualText);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to compare text from element: " + e.getMessage());
		}
	}
	
	// method to check if an element is displayed
	public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by);
			boolean isDisplayed = driver.findElement(by).isDisplayed();
			return isDisplayed;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to determine if element is displayed: " + e.getMessage());
			return false;
		}
	}
	
	// method to scroll to an element
	public void scrollToElement(By by) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			waitForElementToBeVisible(by);
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to scroll to element: " + e.getMessage());
		}
	}
	
	// method to wait for page load completion
	public void waitForPageLoad(int timeoutInSeconds) {
		try {
			wait.withTimeout(Duration.ofSeconds(timeoutInSeconds)).until(
				WebDriver -> ((JavascriptExecutor) WebDriver).executeScript("return document.readyState").equals("complete"));
			System.out.println("Page loaded completely.");
		} catch (Exception e) {
			System.out.println("Page did not load within the specified timeout: "+ timeoutInSeconds + e.getMessage());
		}
	}
	
	// method to wait for an element to be visible
	private void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element is not visible: " + e.getMessage());
		}
	}
	
}
