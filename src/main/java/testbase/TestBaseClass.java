package testbase;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class TestBaseClass implements ITestListener, IExecutionListener {

	File file = new File("src/test/resources/prop");
	protected Map<String, String> driverCap = null;
	protected RemoteWebDriver driver = null;
	protected SessionId SessionId = null;
	public TestBaseClass() {
		System.out.println("in TestBase Class constructor");
	}

	public void onStart1() {

	}

	public void onStart(ITestContext context) {

		System.out.println("in on start");
		this.loadProperties(file);

		// driverCap = context.getCurrentXmlTest().getAllParameters();
		// Map<String, String> testParma =
		// context.getCurrentXmlTest().getTestParameters();
		// Map<String, String> localParm =
		// context.getCurrentXmlTest().getLocalParameters();
		// String globalParm =
		// context.getCurrentXmlTest().getParameter("feature");
		//
		// System.out.println("driverCap" + driverCap);
		// System.out.println("testParma" + testParma);
		// System.out.println("localParm" + localParm);
		// System.out.println("globalParm" + globalParm);

		try {
			// driverFactory
			// .setBrowser(context.getCurrentXmlTest().getParameter("browserName"));
			setBrowser("chrome");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public WebDriver getDriver() {
		// DriverFactory driverFactory = new DriverFactory();
		// SessionId session = ((RemoteWebDriver)
		// driverFactory.getDriver()).getSessionId();
		// System.out.println("session id " + session);
		// return driverFactory.getDriver();
		// return dr.get();
		return driver;

	}

	public void loadProperties(final File folder) {
		LoadTestData testData = new LoadTestData();
		testData.loadProperties(folder);
	}
	public static String getKey(String key) {
		LoadTestData testData = new LoadTestData();
		return testData.getKey(key);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		DriverFactory driverFac = new DriverFactory();
		if (ITestResult.FAILURE == result.getStatus()) {
			try {

				TakesScreenshot ts = (TakesScreenshot) driverFac.getDriver();

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

	public static boolean isActive(WebDriver driver) {
		System.out.println("driver " + driver);
		if (((RemoteWebDriver) driver).getSessionId() == null) {
			return false;
		} else {
			return true;
		}
	}

	public SessionId sessionID() {
		System.out.println("driver getSessionID" + getDriver());
		return ((RemoteWebDriver) getDriver()).getSessionId();
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
		System.out.println("onFinish method");
		System.out.println("Driver going to close");
		getDriver().close();
		// Locators.map.clear();

	}

	public void onFinish1() {
		System.out.println("onFinish method");
		System.out.println("Driver going to close");
		getDriver().close();
		// Locators.map.clear();

	}

	public void onExecutionStart() {
		System.out.println("onExecutionStart method");

	}

	public void onExecutionFinish() {
		System.out.println("Generating the Masterthought Report");
		GenerateReport.GenerateMasterthoughtReport();
		System.out.println("TestNG has finished, the execution");
	}

	// --------------------------------------------------------------------------------------

	public void setBrowser(String myBrowser) throws MalformedURLException {

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
			SessionId = ((RemoteWebDriver) getDriver()).getSessionId();

		} else if (myBrowser.equals("firefox")) {
			DesiredCapabilities capability = new DesiredCapabilities().firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
					capability);
		}

		// setting webdriver
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

}
