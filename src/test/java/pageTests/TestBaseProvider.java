package pageTests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.SessionId;

import testbase.TestBaseClass;

public class TestBaseProvider extends ThreadLocal<TestBaseClass> {

	private static final TestBaseProvider INSTANCE = new TestBaseProvider();

	public static WebDriver getDriver() {

		return INSTANCE.get().getDriver();

	}

	public SessionId getSessionID() {

		return INSTANCE.get().sessionID();

	}

	public static SessionId TestString() throws MalformedURLException {
		TestBaseClass tbc = new TestBaseClass();
		tbc.setBrowser("rajiv");
		INSTANCE.set(tbc);

		return INSTANCE.get().sessionID();

	}

}
