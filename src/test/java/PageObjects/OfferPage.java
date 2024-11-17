package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OfferPage {
	public WebDriver driver;

	public OfferPage(WebDriver driver) {
		this.driver = driver;
	}

	By search = By.cssSelector("input[type='search']");
	By offerTable = By.cssSelector("tr td:nth-child(1)");


	public void searchItem(String itemName) {
		driver.findElement(search).sendKeys(itemName);
	}

	public String getOfferPageProdName(String shortName) throws InterruptedException {
		Thread.sleep(2000);
		String offerProductName = driver.findElement(offerTable).getText();
		System.out.println(offerProductName+" is the offer page product name");
		return offerProductName;
	}
}
