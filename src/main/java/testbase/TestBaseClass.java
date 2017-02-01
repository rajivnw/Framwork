package testbase;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class TestBaseClass implements ITestListener, IExecutionListener {

	File file = new File("src/test/resources/prop");
	private Map<String, String> driverCap = null;

	public TestBaseClass() {
		System.out.println("in TestBase Class constructor");
	}

	public void onStart(ITestContext context) {

		System.out.println("in on start");
		DriverFactory driverFactory = new DriverFactory();
		this.loadProperties(file);

		driverCap = context.getCurrentXmlTest().getAllParameters();
		Map<String, String> testParma = context.getCurrentXmlTest().getTestParameters();
		Map<String, String> localParm = context.getCurrentXmlTest().getLocalParameters();
		String globalParm = context.getCurrentXmlTest().getParameter("feature");

		System.out.println("driverCap" + driverCap);
		System.out.println("testParma" + testParma);
		System.out.println("localParm" + localParm);
		System.out.println("globalParm" + globalParm);

		try {
			driverFactory
					.setBrowser(context.getCurrentXmlTest().getParameter("browserName"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public WebDriver getDriver() {
		DriverFactory driverFactory = new DriverFactory();
		return driverFactory.getDriver();
	}

	public void openWebsite() {
		System.out.println("baseURL" + this.getKey("baseURL"));

		getDriver().get(this.getKey("baseURL"));
		getDriver().manage().window().maximize();
		WSJUtils.waitForPageLoad();
	}

	public void loadProperties(final File folder) {
		LoadTestData testData = new LoadTestData();
		testData.loadProperties(folder);
	}
	public String getKey(String key) {
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

	}

	public void onExecutionStart() {
		System.out.println("onExecutionStart method");

	}

	public void onExecutionFinish() {
		System.out.println("Generating the Masterthought Report");
		GenerateReport.GenerateMasterthoughtReport();
		System.out.println("TestNG has finished, the execution");
	}

}
