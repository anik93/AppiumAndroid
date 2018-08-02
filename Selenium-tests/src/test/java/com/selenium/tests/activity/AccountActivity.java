package com.selenium.tests.activity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.test.pop.PageObject;

public class AccountActivity extends PageObject {

	@FindBy(id = "btn_register")
	private WebElement registerButton;

	public AccountActivity(WebDriver driver) {
		super(driver);
	}

	public RegisterActivity clickOnRegisterButton() {
		registerButton.click();
		return new RegisterActivity(driver);
	}

}
