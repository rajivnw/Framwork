package pageObjcs;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBaseClass;
import testbase.WSJUtils;

public class SearchCompany extends TestBaseClass {

	public SearchCompany() {
		PageFactory.initElements(super.getDriver(), this);
	}

	@FindAll({@FindBy(xpath = "//*[@class='search-button']"),
			@FindBy(xpath = "//*[@class='searchButtonSwitcher']")})
	private WebElement searchCompanyBox;

	@FindBy(xpath = "//input[contains(@placeholder,'Enter News, Quotes, Companies')]")
	private WebElement enterCmpTextBox;

	@FindAll({@FindBy(xpath = ".//ul[@class='symbols']/li[@class='result-item']"),
			@FindBy(xpath = ".//ul[@class='search-company']/li[@class='resultitem']")})
	private List<WebElement> SearchedCmpList;

	public WebElement getClickOnSearchIcon() {
		return searchCompanyBox;
	}

	public WebElement getEnterCmpTextBox() {
		return enterCmpTextBox;
	}

	public List<WebElement> getSearchedCompany() {
		return SearchedCmpList;
	}

	public void clickToSearchBtn() {
		System.out.println("clicking on search btn");
		getClickOnSearchIcon().click();
	}

	public void enterCompany(String cmp) {
		getEnterCmpTextBox().clear();
		System.out.println("Searching... NYSE Company:- " + cmp);
		getEnterCmpTextBox().sendKeys(cmp);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickToSearchedCompany(String cmp) {
		if (getSearchedCompany().size() > 0) {
			for (WebElement element : getSearchedCompany()) {

				if (element.getText().contains(cmp)) {
					WSJUtils.waitForPageLoad();
					element.click();
					WSJUtils.waitForPageLoad();
					System.out.println("NYSE listed company: " + cmp
							+ " clicked from the search list");
					break;
				}
			}

		} else {
			System.out.println("no company found with the entered name");
		}

	}

}
