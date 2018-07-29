package com.selenium.tests.activity;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.selenium.exceptions.ProductNotFoundException;
import com.selenium.test.pop.ActivityObject;
import com.selenium.test.pop.PageObject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SearchActivity extends PageObject {

	@FindBy(id = "search_src_text")
	private WebElement searchInput;

	@FindBy(id = "rv_search_suggestion")
	private WebElement searchResault;

	@FindBy(id = "tv_search_suggestion")
	private List<WebElement> searchResaultList;

	public SearchActivity(WebDriver driver) {
		super(driver);
	}

	public SearchActivity setTextIntoSearchInput(String text) {
		searchInput.sendKeys(text);
		return new SearchActivity(driver);
	}

	public SearchListActivity clickOnSearchingText(String text) throws ProductNotFoundException {
		webDriverWait.until(ExpectedConditions.visibilityOf(searchResault));
		return searchResaultList.stream().filter(element -> element.getText().equals(text)).findFirst().map(resault -> {
			resault.click();
			return new SearchListActivity(driver);
		}).orElseThrow(() -> new ProductNotFoundException(text));
	}

}
