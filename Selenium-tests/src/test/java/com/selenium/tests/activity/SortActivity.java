package com.selenium.tests.activity;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.exceptions.SortNotFoundException;
import com.selenium.test.pop.PageObject;

public class SortActivity extends PageObject {

	@FindBy(className = "android.widget.RadioButton")
	private List<WebElement> sortList;

	public SortActivity(WebDriver driver) {
		super(driver);
	}

	public SearchListActivity selectSortType(String sortType) throws SortNotFoundException {
		return sortList.stream().filter(element -> element.getText().equals(sortType)).findFirst().map(sort -> {
			sort.click();
			return new SearchListActivity(driver);
		}).orElseThrow(() -> new SortNotFoundException("not found"));
	}

}
