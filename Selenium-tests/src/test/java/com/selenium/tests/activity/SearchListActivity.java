package com.selenium.tests.activity;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.test.pop.PageObject;

import io.appium.java_client.android.AndroidDriver;

public class SearchListActivity extends PageObject {

	@FindBy(id = "tv_header")
	private WebElement items;

	@FindBy(xpath = "//android.widget.ImageView[not(@resource-id)]")
	private WebElement refresh;

	@FindBy(id = "view_sort")
	private WebElement sortButton;

	@FindBy(id = "tv_price")
	private List<WebElement> priceList;

	public SearchListActivity(WebDriver driver) {
		super(driver);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public SearchListActivity scrollDown() {
		try {
			((AndroidDriver<WebElement>) driver)
					.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"\"))");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new SearchListActivity(driver);
	}

	public SortActivity clickOnSortButton() {
		sortButton.click();
		return new SortActivity(driver);
	}

	public List<BigInteger> getPrices() {
		return priceList.stream().map(x -> new BigInteger(x.getText().split("\\$")[1].replaceAll(",", "")))
				.collect(Collectors.toList());
	}

	public ProductActivity selectProducy(String string) {
		priceList.get(0).click();
		return new ProductActivity(driver);
	}

}
