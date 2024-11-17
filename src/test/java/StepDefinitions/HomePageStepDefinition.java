package StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import PageObjects.HomePage;
import Utilities.TestContextSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class HomePageStepDefinition {

TestContextSetup testContextSetup;
HomePage homePage;
public HomePageStepDefinition(TestContextSetup testContextSetup) {
	
	this.testContextSetup=testContextSetup;
	this.homePage=testContextSetup.pageObjectManager.getHomePage();
	
}

	
	@Given("User is on GreenCart home page")
	public void user_is_on_green_cart_home_page() throws IOException {
		Assert.assertEquals(homePage.getHomePageTitle(),"GreenKart - veg and fruits kart");
	}
	
	@When("^user searched with shortname (.+) and extracted actual name of product$")
	public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortname) throws InterruptedException {
		
		//homePage=testContextSetup.pageObjectManager.getHomePage();
		homePage.searchItem(shortname);
		
	Thread.sleep(1000);  
	   
	   testContextSetup.homeProductName=homePage.getProductName();
	   System.out.println(testContextSetup.homeProductName+" is the home page product name");
	   
	}
	
	@When("^user searched with (.+) and click add to cart for (.+) quantity$")
	public void user_searched_with__prodName_and_click_add_to_cart(String prodName, String quantity ) throws InterruptedException {
		homePage.searchItem(prodName);
		Thread.sleep(1000);
		String itemName=homePage.getProductName();
	
	if (itemName.equalsIgnoreCase(prodName)){
		homePage.selectCount(Integer.parseInt(quantity));
		homePage.addToCart(itemName);
	}
	else {
		System.out.println("Item mismatch: Test Failed");
	}
		
	}
	
}
