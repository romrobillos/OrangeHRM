package page;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected final WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
	}

	public String getCurrentPageUrl() {
		return driver.getCurrentUrl();
	}
	
	// Scroll
	public void scrollBy(int x, int y) {
	    Actions actions = new Actions(driver);
	    actions.scrollByAmount(x, y).perform();
	}

	// For Keys
	public void sendKeysWithWait(WebElement element, String text) {
		waitForElement(element);
		element.clear();
		element.sendKeys(text);
	}

	public void jsExecuteScriptWithWait(WebElement element, String text) {
		waitForElement(element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = arguments[1];", element, text);
	}

	// Waits
	public WebElement waitForElement(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void clickWaitElement(WebElement element) {
		waitForElement(element);
		element.click();
	}

	public void waitForCssValueChange(WebElement element, String cssProperty, String expectedValue) {
		wait.until(driver -> element.getCssValue(cssProperty).equals(expectedValue));
	}

	// Page Instance
	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);

		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize page: " + pageClass.getSimpleName(), e);
		}

	}

}
