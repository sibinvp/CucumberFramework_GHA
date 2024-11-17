package StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import PageObjects.HomePage;
import PageObjects.OfferPage;
import Utilities.TestContextSetup;
import io.cucumber.java.en.Then;

public class OfferPageStepDefinition {

	TestContextSetup testContextSetup;

	public OfferPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
	}

	@Then("^User searched for (.+) shortname in offer page$")
	public void user_searched_for_shortname_in_offer_page(String shortName) throws InterruptedException {

		HomePage homePage = testContextSetup.pageObjectManager.getHomePage();
		homePage.selectTopDealsPage();
		testContextSetup.genericUtilities.switchToChildWindow();
		OfferPage offerPage = testContextSetup.pageObjectManager.getOfferPage();
		offerPage.searchItem(shortName);
		testContextSetup.offerProductName = offerPage.getOfferPageProdName(shortName);

	}

	@Then("Validate product name in offer page matches with home page.")
	public void validate_product_name_in_offer_page_matches_with_home_page() throws InterruptedException, IOException {
		Thread.sleep(1000);
		Assert.assertEquals(testContextSetup.offerProductName, testContextSetup.homeProductName);
		System.out.println("Test pass");
		
	}
}
