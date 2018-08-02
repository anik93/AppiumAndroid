package com.selenium.tests.activity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.selenium.test.pop.PageObject;

public class ProductActivity extends PageObject {

	@FindBy(id = "tv_price")
	private WebElement priceLabel;

	@FindBy(id = "tv_product_name")
	private WebElement productNameLabel;

	@FindBy(id = "btn_add_to_bag")
	private WebElement addToCartButton;

	@FindBy(id = "btn_add_to_bag")
	private WebElement goToCartButton;

	public ProductActivity(WebDriver driver) {
		super(driver);
		webDriverWait.until(ExpectedConditions.visibilityOf(addToCartButton));
	}

	public ProductActivity addToCart() {
		addToCartButton.click();
		return new ProductActivity(driver);
	}

	public ShoppingCartActivity clickToGoToCartButton() {
		goToCartButton.click();
		return new ShoppingCartActivity(driver);
	}

}
