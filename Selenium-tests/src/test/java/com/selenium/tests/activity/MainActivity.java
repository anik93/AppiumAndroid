package com.selenium.tests.activity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.test.pop.ActivityObject;
import com.selenium.test.pop.PageObject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MainActivity extends PageObject {

	@FindBy(id = "iv_toolbar_search_icon")
	private WebElement searchButton;

	@FindBy(id = "search_src_text")
	private WebElement searchInput;

	public MainActivity(WebDriver driver) {
		super(driver);
	}

	public SearchActivity clickSearchButton() {
		searchButton.click();
		return new SearchActivity(driver);
	}
}
