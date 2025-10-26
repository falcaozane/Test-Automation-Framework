package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;

public class HomePage {
	
	private ActionDriver actionDriver;
	
	// define locators using By class
	private By dashBoardTab = By.xpath("//span[text()='Dashboard']");
	private By userIDButton = By.xpath("//p[@class='oxd-userdropdown-name']");
	private By logoutButton = By.xpath("//a[text()='Logout']");
	private By orangeHRMLogo = By.xpath("//div[@class='oxd-brand-banner']//img");
	
	// constructor to initialize ActionDriver by passing WebDriver instance
	public HomePage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	}
	
	// method to verify if Dashboard tab is displayed
	public boolean isDashboardTabDisplayed() {
		return actionDriver.isDisplayed(dashBoardTab);
	}
	
	// method to verify if OrangeHRM logo is displayed
	public boolean isOrangeHRMLogoDisplayed() {
		return actionDriver.isDisplayed(orangeHRMLogo);
	}
	
	// method to perform logout action
	public void logout() {
		actionDriver.click(userIDButton);
		actionDriver.click(logoutButton);
	}

}
