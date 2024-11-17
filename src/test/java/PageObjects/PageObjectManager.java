package PageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	public WebDriver driver;
	public HomePage homePage;
	public OfferPage offerPage;
	public CartPage cartPage;
	
	public PageObjectManager(WebDriver driver) {
		this.driver=driver;
	}

	public HomePage getHomePage() {
		
		homePage=new HomePage(driver);
		return homePage;
	}
	
public OfferPage getOfferPage() {
		
		offerPage=new OfferPage(driver);
		return offerPage;
	}

public CartPage getCartPage() {
	
	cartPage=new CartPage(driver);
	return cartPage;
}
	

}
