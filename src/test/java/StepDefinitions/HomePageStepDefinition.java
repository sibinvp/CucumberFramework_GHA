package StepDefinitions;

import java.io.IOException;

import org.testng.Assert;


import PageObjects.HomePage;
import Utilities.TestContextSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;


public class HomePageStepDefinition {

	TestContextSetup testContextSetup;
	HomePage homePage;
	

	public HomePageStepDefinition(TestContextSetup testContextSetup) throws IOException {

		this.testContextSetup = testContextSetup;
		this.homePage = testContextSetup.pageObjectManager.getHomePage();
		
	}

	private HashMap<String, String> dataSet;
	
	/**
	@Before	
	public void loadCSVData() throws IOException {
		String filePath = System.getProperty("user.dir") + "//src//test//java//Data//TestData.xlsx";
		csvData = testContextSetup.testBase.convertExcelToListOfHashMaps(filePath);
		// csvData = testContextSetup.testBase.convertCSVToList(filePath);
	}
	**/
	
    
	@Given("^User is on GreenCart home page for (.+)$")
	public void user_is_on_green_cart_home_page(String tcID) throws IOException {
		String filePath = System.getProperty("user.dir") + "//src//test//java//Data//TestData.xlsx";
		testContextSetup.testBase.convertExcelToListOfHashMaps(filePath, tcID);
		dataSet=testContextSetup.testBase.dataSet;
		Assert.assertEquals(homePage.getHomePageTitle(), "GreenKart - veg and fruits kart");
	}

	@When("^user searched with shortname (.+) and extracted actual name of product$")
	public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortname)
			throws InterruptedException {

		// homePage=testContextSetup.pageObjectManager.getHomePage();
		homePage.searchItem(shortname);

		Thread.sleep(1000);

		testContextSetup.homeProductName = homePage.getProductName();
		System.out.println(testContextSetup.homeProductName + " is the home page product name");

	}
	
	
	@When("user searched with shortname and extracted actual name of product")
	public void user_searched_with_shortname()
			throws InterruptedException {
			String shortname=dataSet.get("ShortName");
		// homePage=testContextSetup.pageObjectManager.getHomePage();
		homePage.searchItem(shortname);

		Thread.sleep(1000);

		testContextSetup.homeProductName = homePage.getProductName();
		System.out.println(testContextSetup.homeProductName + " is the home page product name");

	}
	

	@When("^user searched with (.+) and click add to cart for (.+) quantity$")
	public void user_searched_with__prodName_and_click_add_to_cart(String prodName, String quantity)
			throws InterruptedException {
		homePage.searchItem(prodName);
		Thread.sleep(1000);
		String itemName = homePage.getProductName();

		if (itemName.equalsIgnoreCase(prodName)) {
			homePage.selectCount(Integer.parseInt(quantity));
			homePage.addToCart(itemName);
		} else {
			System.out.println("Item mismatch: Test Failed");
		}

	}

	@When("user searched with prodName and click add to cart for required quantity from data sheet")
	public void user_searched_with_prod_name_and_click_add_to_cart_for_required_quantity_from_data_sheet()
			throws InterruptedException, IOException {

		String prodName = dataSet.get("prodName");
		String quantity = dataSet.get("quantity");

		// String quantity = row.get("quantity");

		homePage.searchItem(prodName);
		Thread.sleep(1000);
		String itemName = homePage.getProductName();

		if (itemName.equalsIgnoreCase(prodName)) {
			homePage.selectCount(Integer.parseInt(quantity));
			homePage.addToCart(itemName);
		} else {
			System.out.println("Item mismatch: Test Failed");
		}

	}

}
