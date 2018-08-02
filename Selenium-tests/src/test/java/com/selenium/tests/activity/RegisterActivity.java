package com.selenium.tests.activity;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.test.pop.PageObject;

public class RegisterActivity extends PageObject {

	@FindBy(id = "et_title")
	private WebElement titleSelect;

	@FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"com.habbitzz.app:id/layout_parent\"]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView")
	private List<WebElement> headerLabel;

	public RegisterActivity(WebDriver driver) {
		super(driver);
	}

	public TitlePopUpActivity clickOnTitleSelect() {
		titleSelect.click();
		return new TitlePopUpActivity(driver);
	}

	public String getTitleText() {
		return titleSelect.getText();
	}

	public String getFirstHeader() {
		return headerLabel.get(0).getText();
	}

}
