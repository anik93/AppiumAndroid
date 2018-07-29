package com.selenium.test.pop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import com.selenium.exceptions.ProductNotFoundException;
import com.selenium.exceptions.SortNotFoundException;
import com.selenium.test.page.ArticlePage;
import com.selenium.test.page.HomePage;
import com.selenium.tests.activity.MainActivity;
import com.selenium.tests.activity.SearchActivity;
import com.selenium.tests.activity.SearchListActivity;
import com.selenium.tests.activity.SortActivity;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import com.anik.selenium.model.enums.WebDriverType;

import cucumber.api.Scenario;
import cucumber.api.java8.En;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class FunctionalTest implements En {

	private AndroidDriver<WebElement> driver;
	private Scenario scenario;
	// private String url = System.getProperty("url");
	private WebDriverType driverType;
	private HomePage homePage;
	private ArticlePage articlePage;
	private AppiumDriverLocalService service;

	public FunctionalTest() {
		String[] adnotation = { "@scenarioTest" };
		Before(adnotation, (Scenario scenario) -> {
			this.scenario = scenario;
		});

		After(adnotation, () -> {
			if (this.scenario.isFailed())
				this.scenario.embed(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES), "image/png");

			if (this.driver.getClass().equals(OperaDriver.class)) {
				this.driver.close();
				this.driver.quit();
			} else if (this.driver.getClass().equals(AndroidDriver.class)) {
				this.driver.quit();
				this.service.stop();
			} else if (!this.driver.getClass().equals(InternetExplorerDriver.class))
				this.driver.close();
			else
				this.driver.quit();
		});

		Given("^I navigate to \"([^\"]*)\" using \"([^\"]*)\"$", (String path, WebDriverType webDriverType) -> {
			initWebDriver(webDriverType, path);
			// homePage = new HomePage(driver);
		});

		Given("^I navigate to article \"([^\"]*)\" using \"([^\"]*)\"$", (String path, WebDriverType webDriverType) -> {
			initWebDriver(webDriverType, path);
			// articlePage = new ArticlePage(driver);
		});

		Given("^I open app using \"([^\"]*)\"$", (WebDriverType webDriverType) -> {
			initWebDriver(webDriverType, "https://github.com/");
			// articlePage = new ArticlePage(driver);
			MainActivity mainActivity = new MainActivity(driver);
			SearchActivity searchActivity = mainActivity.clickSearchButton();
			searchActivity = searchActivity.setTextIntoSearchInput("samsung");
			SearchListActivity searchListActivity = null;
			try {
				searchListActivity = searchActivity.clickOnSearchingText("samsung");
			} catch (ProductNotFoundException e) {
				System.out.println("product not found " + e.getName());
			}
			SortActivity sortActivity = searchListActivity.clickOnSortButton();
			try {
				searchListActivity = sortActivity.selectSortType("Price: Low to High");
			} catch (SortNotFoundException e) {
				System.out.println("sort not found " + e.getName());
			}
			searchListActivity = searchListActivity.scrollDown();
			searchListActivity.getPrices().forEach(x -> System.out.println(x));
			assertThat(searchListActivity.getPrices()).isSorted();
		});
	}

	private void initWebDriver(WebDriverType webDriverType, String path) {
		/*
		 * if (!webDriverType.equals(WebDriverType.IE) &&
		 * !webDriverType.equals(WebDriverType.ANDROID)) { this.driver =
		 * webDriverType.create(); this.driver.manage().timeouts().implicitlyWait(1,
		 * TimeUnit.SECONDS); this.driver.manage().deleteAllCookies();
		 * this.driver.get(path); } else
		 */if (webDriverType.equals(WebDriverType.ANDROID)) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
			this.driver = webDriverType.create();
			// this.driver.get(path);
		} else {
			this.driver = webDriverType.create();
			this.driver.manage().deleteAllCookies();
			this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public HomePage getHomePage() {
		return homePage;
	}

	public void setHomePage(HomePage homePage) {
		this.homePage = homePage;
	}

	public ArticlePage getArticlePage() {
		return articlePage;
	}

	public void setArticlePage(ArticlePage articlePage) {
		this.articlePage = articlePage;
	}
}
