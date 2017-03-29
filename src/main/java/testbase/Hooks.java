package testbase;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	@Before
	public void before() {
		System.out.println("in before hook");
	}

	@After
	public void after() {
		System.out.println("in after hook");
	}

}
