package api.testng;

import cucumber.runtime.CucumberException;
import cucumber.runtime.model.CucumberFeature;

public class CucumberExceptionWrapper implements CucumberFeatureWrapper {
	private CucumberException exception;

	public CucumberExceptionWrapper(CucumberException e) {
		this.exception = e;
	}

	public CucumberFeature getCucumberFeature() {
		throw this.exception;
	}

}
