package pageTests;

import pageObjcs.Locators;
import pageObjcs.RetrieveTestData;
import pageObjcs.SearchCompany;
import testbase.TestBaseClass;
import testbase.WSJUtils;

public class TestMethod {

	/**
	 * @author rajiv.sharma
	 * @param cmpName
	 *            This method for search a company
	 */

	public void SearchCompany(String cmpName) {

		// SearchCompany searchcmp = new SearchCompany();
		// searchcmp.clickToSearchBtn();
		// searchcmp.enterCompany(cmpName);
		// searchcmp.clickToSearchedCompany(cmpName);
		System.out.println(
				"Thread name in Test Method " + Thread.currentThread().getName());
		Locators.Page(SearchCompany.class).clickToSearchBtn();
		Locators.Page(SearchCompany.class).enterCompany(cmpName);
		Locators.Page(SearchCompany.class).clickToSearchedCompany(cmpName);

	}

	/**
	 * @author rajiv.sharma
	 * @param cmpName
	 *            This method is for retrieve data
	 */

	public void RetrieveCompanyData(String cmpName) {
		// RetrieveTestData retrieveData = new RetrieveTestData();
		// retrieveData.currentPrice(cmpName);
		// retrieveData.lowHighPrices_52Weeks(cmpName);
		// retrieveData.EPSPrice(cmpName);

		Locators.Page(RetrieveTestData.class).currentPrice(cmpName);
		Locators.Page(RetrieveTestData.class).lowHighPrices_52Weeks(cmpName);
		Locators.Page(RetrieveTestData.class).EPSPrice(cmpName);

	}
	/**
	 * @author rajiv.sharma
	 * @param firstCMP
	 * @param secondCMP
	 *            This method for compare values
	 */

	public void compareEPS(String firstCMP, String secondCMP) {

		int cmpr = Float.compare(RetrieveTestData.EPSPriceMap.get(firstCMP),
				RetrieveTestData.EPSPriceMap.get(secondCMP));
		if (cmpr > 0) {
			System.out.println(firstCMP + " EPS is greater than " + secondCMP);
		} else if (cmpr < 0) {
			System.out.println(firstCMP + " EPS is less than " + secondCMP);
		} else {
			System.out.println(firstCMP + " EPS is equal to " + secondCMP);
		}
	}

	public void openWebsite() {
		System.out.println("baseURL" + TestBaseClass.getKey("baseURL"));
		// TestBaseProvider testBase = new TestBaseProvider();
		System.out.println(TestBaseProvider.TestString());

		System.out.println(TestBaseProvider.getDriver().toString());
		TestBaseProvider.getDriver().get(TestBaseClass.getKey("baseURL"));
		TestBaseProvider.getDriver().manage().window().maximize();
		WSJUtils.waitForPageLoad();
	}

}
