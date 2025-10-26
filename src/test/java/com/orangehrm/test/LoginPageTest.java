package com.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

public class LoginPageTest extends BaseClass {
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setUpPages() {
		// Initialize LoginPage and HomePage objects
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}
	
	@Test
	public void verifyValidLoginTest() {
		// Perform login action
		loginPage.login("Admin", "admin123");
		
		Assert.assertTrue(homePage.isDashboardTabDisplayed(), "Dashboard tab is not displayed after login");
		homePage.logout();
		staticWait(3);
	}
}
