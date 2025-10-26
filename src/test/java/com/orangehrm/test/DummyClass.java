package com.orangehrm.test;

import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;

public class DummyClass extends BaseClass {
	@Test
	public void dummyTest() {
		String title = driver.getTitle();
		System.out.println("Page Title: " + title);
		assert title != null && !title.isEmpty() : "Title should not be null or empty";
		assert title.contains("OrangeHRM") : "Test failed: Title does not contain 'OrangeHRM'";
		
		System.out.println("Test Passed: Title contains 'OrangeHRM'");
	}
}
