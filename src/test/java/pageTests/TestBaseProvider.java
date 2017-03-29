package pageTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import testbase.DriverFactory;
import testbase.TestBaseClass;

public class TestBaseProvider extends ThreadLocal<TestBaseClass> {

	private static final TestBaseProvider INSTANCE = new TestBaseProvider();

	public WebDriver getDriver() {
		DriverFactory driverFactory = new DriverFactory();
		SessionId session = ((RemoteWebDriver) driverFactory.getDriver()).getSessionId();
		System.out.println("session id " + session);
		return INSTANCE.get().getDriver();

	}

}
