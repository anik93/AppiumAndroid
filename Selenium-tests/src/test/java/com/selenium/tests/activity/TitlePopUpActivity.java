package com.selenium.tests.activity;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.test.pop.PageObject;

public class TitlePopUpActivity extends PageObject {

	@FindBy(className = "android.widget.RadioButton")
	private List<WebElement> titleRadioGroup;

	public TitlePopUpActivity(WebDriver driver) {
		super(driver);
	}

	public RegisterActivity selectTitle(String text) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		titleRadioGroup.forEach(x -> System.out.println(
				"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + x.getAttribute("text")));
		titleRadioGroup.forEach(x -> System.out.println(
				"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + x.getText()));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		titleRadioGroup.stream().filter(x -> x.getText().equals(text)).findFirst().ifPresent(x -> x.click());
		return new RegisterActivity(driver);
	}

}
