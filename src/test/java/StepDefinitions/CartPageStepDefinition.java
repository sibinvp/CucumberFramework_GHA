package StepDefinitions;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;

import PageObjects.CartPage;
import Utilities.TestContextSetup;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartPageStepDefinition {

	CartPage cartPage;
	TestContextSetup testContextSetup;
	
	public CartPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup=testContextSetup;
		this.cartPage =testContextSetup.pageObjectManager.getCartPage();
	}
	@Then("^User searched for (.+) and quantity (.+) in cart page$")
	public void user_searched_for_prodname_and_quantity_in_cart_page(String prodname, String quantity) {
		
	    testContextSetup.cartProductName=cartPage.getCartProdName();
	    Assert.assertEquals(testContextSetup.cartProductName[0].toUpperCase(),prodname.toUpperCase());
	    Assert.assertEquals(testContextSetup.cartProductName[1],quantity);
	    System.out.println(testContextSetup.cartProductName[0]+" : is the cart product name with quantity: "+testContextSetup.cartProductName[1]);
	}
	@Then("user validate apply button and place the order")
	public void user_validate_apply_button_and_place_the_order() {
	    Assert.assertTrue(cartPage.checkPromoCode());
	    cartPage.placeOrder();
	}
}
