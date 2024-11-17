package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	By search = By.cssSelector("input[type='search']");
	By productName = By.cssSelector("h4.product-name");
	By topDeals = By.linkText("Top Deals");
	By addTocart = By.cssSelector(".product-action button:first-of-type");
	By cart= By.cssSelector(".cart-icon");
	By cartPreView= By.cssSelector("div[class=\"cart-preview active\"] div[class=\"action-block\"]  button");
	By increment= By.cssSelector(".increment");

	public void selectTopDealsPage() {
		driver.findElement(topDeals).click();
	}

	public void searchItem(String itemName) {
		driver.findElement(search).sendKeys(itemName);
	}

	public String getProductName() {
		String[] prod = driver.findElement(productName).getText().split("-");
		String prodName = prod[0].trim();
		return prodName;
	}
	public void selectCount(int quantity) {
		int i=quantity-1;
		while (i>0) {
			driver.findElement(increment).click();
			i--;
		}
	}
	public void addToCart(String prodName) throws InterruptedException {

			driver.findElement(addTocart).click();
			driver.findElement(cart).click();
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(cartPreView)).click().build().perform();
			Thread.sleep(1000);
		

	}
	
	public String getHomePageTitle() {
		
		return driver.getTitle();
	}

}
