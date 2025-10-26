package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;

public class LoginPage {
	
	private ActionDriver actionDriver;
	
	// define locators using By class
	private By usernameField = By.xpath("//input[@name='username']");
	private By passwordField = By.cssSelector("input[type='password']");
	private By loginButton = By.xpath("//button[text()=' Login ']");
	private By errorMessage = By.xpath("//p[text()='Invalid credentials']");
	
	// constructor to initialize ActionDriver
	public LoginPage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	}
	
	
	// method to perform login action
	public void login(String username, String password) {
		actionDriver.enterText(usernameField, username);
		actionDriver.enterText(passwordField, password);
		actionDriver.click(loginButton);
	}
	
	// method to check invalid login
	public boolean isErrorMessageDisplayed() {
		return actionDriver.isDisplayed(errorMessage);
	}
	
	// method to get error message text
	public String getErrorMessageText() {
		return actionDriver.getText(errorMessage);
	}
	
	// verify if error message is correct or not
	public void verifyErrorMessage(String expectedMessage) {
		actionDriver.compareText(errorMessage, expectedMessage);
	}
	

}
