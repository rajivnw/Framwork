package testbase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

	// ThreadLocal will keep local copy of driver
	public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();

	public void setBrowser(String myBrowser) throws MalformedURLException {

		RemoteWebDriver driver = null;

		if (myBrowser.equals("chrome")) {
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Google Nexus 5");

			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			// driver = new RemoteWebDriver(new
			// URL("http://localhost:4444/wd/hub"),
			// capabilities);
			driver = new ChromeDriver();

		} else if (myBrowser.equals("firefox")) {
			DesiredCapabilities capability = new DesiredCapabilities().firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
					capability);
		}

		// setting webdriver
		setWebDriver(driver);

		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public WebDriver getDriver() {
		return dr.get();
	}

	public void setWebDriver(RemoteWebDriver driver) {
		dr.set(driver);

	}

	// public void setCap() {
	//
	// String host = "partners.perfectomobile.com";
	//
	// DesiredCapabilities capabilities = new DesiredCapabilities();
	//
	// capabilities.setCapability("user", "is_user1@infostretch.com");
	// capabilities.setCapability("password", "demo123");
	// capabilities.setCapability("platformName", "android");
	// capabilities.setCapability("platformVersion", "6.0.1");
	//
	// // capabilities.setCapability("deviceName", "3219D3B1");
	// capabilities.setCapability("automationName", "appium");
	//
	// capabilities.setCapability(CapabilityType.BROWSER_NAME,
	// BrowserType.CHROME);
	//
	// try {
	// driver = new RemoteWebDriver(
	// new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"),
	// capabilities);
	// } catch (MalformedURLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	// System.out.println("this is done");
	//
	// }

}
