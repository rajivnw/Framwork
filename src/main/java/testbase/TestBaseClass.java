package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import io.appium.java_client.android.AndroidDriver;

public class TestBaseClass implements ITestListener {

	File file = new File("src/test/resources/prop");
	Properties properties = new Properties();
	private static AndroidDriver driver;
	private static Map<String, String> driverCap = null;

	public TestBaseClass() {

	}

	public void onStart(ITestContext context) {

		this.loadProperties(file);
		driverCap = context.getCurrentXmlTest().getAllParameters();
		System.out.println(driverCap);

		if (driverCap.containsKey("browserName") && driver == null) {
			setDriver(context.getCurrentXmlTest().getParameter("browserName"));
		}

		if (driverCap.containsKey("cloudName") && driver == null) {
			setCap();
		}
		driver.manage().deleteAllCookies();
		this.openWebsite();
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public void setDriver(String browserName) {
		System.out.println("Browser Name......." + browserName);

		if (browserName.toLowerCase().contains("chrome")) {
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Google Nexus 5");

			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver(capabilities);

		}
	}

	public void setCap() {

		String host = "partners.perfectomobile.com";

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("user", "is_user1@infostretch.com");
		capabilities.setCapability("password", "demo123");
		capabilities.setCapability("platformName", "android");
		capabilities.setCapability("platformVersion", "6.0.1");

		// capabilities.setCapability("deviceName", "3219D3B1");
		capabilities.setCapability("automationName", "appium");

		capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);

		try {
			driver = new AndroidDriver(
					new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"),
					capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("this is done");

	}

	public void openWebsite() {
		getDriver().get(getKey("baseURL"));
		getDriver().manage().window().maximize();
		WSJUtils.waitForPageLoad();
	}

	public void loadProperties(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				loadProperties(fileEntry);
			} else {
				System.out.println(fileEntry.getName());
				try {
					File file = new File(
							"src/test/resources/prop/" + fileEntry.getName() + "");
					FileInputStream fileInput = new FileInputStream(file);

					properties.load(fileInput);
					fileInput.close();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {
			try {

				TakesScreenshot ts = driver;

				File source = ts.getScreenshotAs(OutputType.FILE);

				FileUtils.copyFile(source,
						new File("./Screenshots/" + result.getName() + ".png"));

				System.out.println("Screenshot taken : Failure ");
				getDriver().close();
			} catch (Exception e) {

				System.out.println("Exception while taking screenshot " + e.getMessage());
				getDriver().close();
			}

		} else {
			getDriver().close();
			// log.info(result.getName() + " Function is passed");

		}

	}

	public String getKey(String key) {

		Enumeration enuKeys = properties.keys();
		while (enuKeys.hasMoreElements()) {
			String keyValue = (String) enuKeys.nextElement();
			if (keyValue.equalsIgnoreCase(key)) {
				return properties.getProperty(keyValue);
			}
			// String value = properties.getProperty(key);
			// System.out.println(key + ": " + value);
		}
		return null;
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {

	}

	@AfterSuite
	public void closeDriver() {
		System.out.println("Driver going to close");
		driver.quit();
	}

}
