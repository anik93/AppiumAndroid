package com.selenium.test.pop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import com.selenium.exceptions.ProductNotFoundException;
import com.selenium.exceptions.SortNotFoundException;
import com.selenium.test.page.ArticlePage;
import com.selenium.test.page.HomePage;
import com.selenium.tests.activity.AccountActivity;
import com.selenium.tests.activity.MainActivity;
import com.selenium.tests.activity.ProductActivity;
import com.selenium.tests.activity.RegisterActivity;
import com.selenium.tests.activity.SearchActivity;
import com.selenium.tests.activity.SearchListActivity;
import com.selenium.tests.activity.ShoppingCartActivity;
import com.selenium.tests.activity.SortActivity;
import com.selenium.tests.activity.TitlePopUpActivity;

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
			AccountActivity accountActivity = mainActivity.clickOnAccountInMenu();
			RegisterActivity registerActivity = accountActivity.clickOnRegisterButton();
			TitlePopUpActivity titlePopUpActivity = registerActivity.clickOnTitleSelect();
			registerActivity = titlePopUpActivity.selectTitle("Miss");
			System.out.println(registerActivity.getTitleText());
			System.out.println(registerActivity.getFirstHeader());
			// SearchActivity searchActivity = mainActivity.clickSearchButton();
			// searchActivity = searchActivity.setTextIntoSearchInput("tea");
			// SearchListActivity searchListActivity = null;
			// try {
			// searchListActivity = searchActivity.clickOnSearchingText("tea");
			// } catch (ProductNotFoundException e) {
			// System.out.println("product not found " + e.getName());
			// }
			// ProductActivity productActivity = searchListActivity.selectProducy("HK$218");
			// productActivity = productActivity.addToCart();
			// ShoppingCartActivity shoppingCartActivity =
			// productActivity.clickToGoToCartButton();
			// shoppingCartActivity.getSub();
			// shoppingCartActivity.getTotal();

			// SortActivity sortActivity = searchListActivity.clickOnSortButton();
			// try {
			// searchListActivity = sortActivity.selectSortType("Price: Low to High");
			// } catch (SortNotFoundException e) {
			// System.out.println("sort not found " + e.getName());
			// }
			// searchListActivity = searchListActivity.scrollDown();
			// searchListActivity.getPrices().forEach(x -> System.out.println(x));
			// assertThat(searchListActivity.getPrices()).isSorted();
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
