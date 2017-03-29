package pageObjcs;

import java.util.HashMap;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import testbase.TestBaseClass;

public class Locators {

	private Locators() {

	}

	public static volatile ThreadLocal<HashMap<SessionId, Object>> map =
			new ThreadLocal<HashMap<SessionId, Object>>() {
				@Override
				protected HashMap<SessionId, Object> initialValue() {
					return new HashMap<SessionId, Object>();
				}

			};

	@SuppressWarnings("unchecked")
	public static <T> T Page(Class<T> myClass) {
		try {
			T clazz;

			synchronized (Locators.class) {
				if (!map.get().containsKey(TestBaseClass.sessionID())) {
					clazz = myClass.newInstance();
					SessionId session =
							((RemoteWebDriver) TestBaseClass.getDriver()).getSessionId();

					map.get().put(session, clazz);
					return clazz;
				} else {
					clazz = (T) map.get().get(TestBaseClass.sessionID());
					return clazz;
				}
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
