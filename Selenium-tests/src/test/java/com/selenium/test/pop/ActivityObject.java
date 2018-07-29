package com.selenium.test.pop;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

import java.util.Arrays;

public class ActivityObject {

	protected AndroidDriver<WebElement> driver;
	protected WebDriverWait webDriverWait;
	protected FluentWait fluentWait;

	public ActivityObject(AndroidDriver<WebElement> driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		webDriverWait = new WebDriverWait(driver, 60);
		fluentWait = new FluentWait(driver);
		fluentWait.ignoreAll(Arrays.asList(NoSuchElementException.class, WebDriverException.class));
	}

}
