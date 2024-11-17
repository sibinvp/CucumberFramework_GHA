package Utilities;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class GenericUtilities {
	public WebDriver driver;
	public GenericUtilities(WebDriver driver) {
		this.driver=driver;
	}

	
	public void switchToChildWindow() {
		 Set<String> s1=driver.getWindowHandles();
		   Iterator<String> i1=s1.iterator();
		   String parentWindow=i1.next();
		   String childWindow=i1.next();
		   driver.switchTo().window(childWindow);

}
}