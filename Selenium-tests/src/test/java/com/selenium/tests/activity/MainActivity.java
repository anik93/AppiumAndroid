package com.selenium.tests.activity;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.test.pop.PageObject;

public class MainActivity extends PageObject {

	@FindBy(id = "iv_toolbar_search_icon")
	private WebElement searchButton;

	@FindBy(id = "search_src_text")
	private WebElement searchInput;

	@FindBy(id = "icon")
	private List<WebElement> downMenuList;

	public MainActivity(WebDriver driver) {
		super(driver);
	}

	public SearchActivity clickSearchButton() {
		searchButton.click();
		return new SearchActivity(driver);
	}

	public AccountActivity clickOnAccountInMenu() {
		this.downMenuList.get(downMenuList.size() - 1).click();
		return new AccountActivity(driver);
	}
}
