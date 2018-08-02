package com.selenium.tests.activity;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.selenium.test.pop.PageObject;

public class ShoppingCartActivity extends PageObject {

	@FindBy(id = "tv_sub_total_price")
	private WebElement subTotalPriceLabel;

	@FindBy(id = "com.habbitzz.app:id/tv_price")
	private List<WebElement> totalPricesLabel;

	public ShoppingCartActivity(WebDriver driver) {
		super(driver);
		webDriverWait.until(ExpectedConditions.visibilityOf(subTotalPriceLabel));
	}

	public void getSub() {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + subTotalPriceLabel.getText());
	}

	public void getTotal() {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "
				+ totalPricesLabel.get(totalPricesLabel.size()-1).getText());
	}

}
