package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	protected static Properties prop;
	protected WebDriver driver;
	
	@BeforeSuite
	public void loadConfig() throws IOException {
		
		// Load configuration file
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
	}
	
	private void launchBrowser() {
		
		// Initialize WebDriver based on the browser specified in config.properties
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			// Initialize ChromeDriver
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			// Initialize FirefoxDriver
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			// Initialize EdgeDriver
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
	}
	
	private void configureBroswer() {
		
		// Implicit wait
		int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		// Maximize browser window
		driver.manage().window().maximize();

		// Navigate to the URL specified in config.properties
		String url = prop.getProperty("url");
		try {
			driver.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to navigate to the URL: " + url);
			System.out.println(e.getMessage());
		}
		
	}
	
	@BeforeMethod
	public void setup() throws IOException {
		System.out.println("Setting up the test environment..."+ this.getClass().getSimpleName());
		loadConfig();
		launchBrowser();
		configureBroswer();
		staticWait(10);
	}
	
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Error occurred while closing the browser...." + e.getMessage());
			}
		}
	}
	
	// Driver getter for WebDriver
	public WebDriver getDriver() {
		return driver;
	}
	
	// Driver setter for WebDriver
	public WebDriver setDriver(WebDriver driver) {
		return this.driver = driver;
	}
	
	// Static wait for pause
	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
	
	public static Properties getProp() {
		return prop;
	}
	
	
}
