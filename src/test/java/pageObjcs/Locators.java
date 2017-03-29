package pageObjcs;

import pageTests.TestBaseProvider;
public class Locators {

	private Locators() {

	}
	//
	// public static volatile ThreadLocal<HashMap<SessionId, Object>> map =
	// new ThreadLocal<HashMap<SessionId, Object>>() {
	// @Override
	// protected HashMap<SessionId, Object> initialValue() {
	// return new HashMap<SessionId, Object>();
	// }
	//
	// };

	static TestBaseProvider baseprovider = new TestBaseProvider();

	@SuppressWarnings("unchecked")
	public static <T> T Page(Class<T> myClass) {
		try {
			T clazz;
			clazz = myClass.newInstance();
			return clazz;

			// synchronized (Locators.class) {
			//
			// if (!map.get().containsKey(baseprovider.getSessionID())) {
			// clazz = myClass.newInstance();
			// SessionId session =
			// ((RemoteWebDriver) baseprovider.getDriver()).getSessionId();
			//
			// map.get().put(session, clazz);
			// return clazz;
			// } else {
			// clazz = (T) map.get().get(baseprovider.getSessionID());
			// return clazz;
			// }
			// }
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
