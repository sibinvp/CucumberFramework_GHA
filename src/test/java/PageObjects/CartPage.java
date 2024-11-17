package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
	
	public WebDriver driver;
	public CartPage(WebDriver driver) {
		this.driver=driver;
	}
	
	By tableItem= By.cssSelector("tbody td:nth-child(2)");
	By tableQuantity= By.cssSelector("tbody td .quantity");
	By promoBtn=By.cssSelector(".promoBtn");
	By placeOrderBtn= By.xpath("//button[contains(text(),'Place Order')]");
	
	public String[] getCartProdName() {
		
		String[] cartProdName= driver.findElement(tableItem).getText().split("-");
		
		String cartName=cartProdName[0].trim();
		String cartQuantity= driver.findElement(tableQuantity).getText();
		String[]cartDetails = new String[2];
		cartDetails[0]=cartName;
		cartDetails[1]=cartQuantity;
		
		return cartDetails;
	}

	public boolean checkPromoCode() {
		return driver.findElement(promoBtn).isDisplayed();
		
	}
	
	public void placeOrder() {
		
		driver.findElement(placeOrderBtn).click();
	}
}
