package desktop;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageTests.TestMethod;
import testbase.TestBaseClass;

public class HomePageStepDef {

	@Given("user starts WSJ session")
	public void Starts_WSJ_session() {
		TestBaseClass testBase = new TestBaseClass();
		testBase.openWebsite();
	}

	@Then("user should see WSJ home page with all information")
	public void ValidateWsjHomePage() {

	}

	@Then("user search '(\\w+)' company")
	public void SearchCompanyOnWsj(String cmpName) {
		TestMethod testMethod = new TestMethod();
		testMethod.SearchCompany(cmpName);
	}

	@Then("user retrieve '(\\w+)' company data")
	public void RetrieveCompanyData(String cmpName) {
		TestMethod testMethod = new TestMethod();
		testMethod.RetrieveCompanyData(cmpName);
	}

	@Then("user compare '(\\w+)' and '(\\w+)' compareEPS prices")
	public void CompareEPSPrices(String firstCMP, String secondCMP) {
		TestMethod testMethod = new TestMethod();
		testMethod.compareEPS(firstCMP, secondCMP);
	}

}
