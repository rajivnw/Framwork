package testbase;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WSJUtils extends TestBaseClass {

	static WebDriverWait wait = null;

	public WSJUtils() {
		System.out.println("WSJUtils const.");
		wait = new WebDriverWait(getDriver(), 30);
	}

	public static void waitForPageLoad() {
		WSJUtils wsj = new WSJUtils();
		wsj.PageLoadWait();
		// wsj.jQueryLoadWait();
	}

	public void PageLoadWait() {
		ExpectedCondition<Boolean> pageLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver)
						.executeScript("return document.readyState").equals("complete");
			}
		};
		wait.until(pageLoad);
	}

	public void jQueryLoadWait() {

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver)
							.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					// no jQuery present
					return true;
				}
			}
		};
		wait.until(jQueryLoad);
	}

	public void waitForAngularLoad(long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);

		ExpectedCondition<Boolean> angularLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return Boolean.valueOf(((JavascriptExecutor) driver)
						.executeScript(
								"return (window.angular !== undefined) && (angular.element(document.body).injector() !== undefined) && (angular.element(document.body).injector().get('$http').pendingRequests.length === 0)")
						.toString());
			}
		};
		wait.until(angularLoad);
	}

	public static void clickUsingJavaScript(WebElement element) {
		JavascriptExecutor executor =
				(JavascriptExecutor) new TestBaseClass().getDriver();
		executor.executeScript("arguments[0].click();", element);
	}

}
