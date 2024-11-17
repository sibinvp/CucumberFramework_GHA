package Utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import PageObjects.PageObjectManager;

public class TestContextSetup {

	public WebDriver driver;
	public String homeProductName;
	public String offerProductName;
	public String[] cartProductName;
	public PageObjectManager pageObjectManager;
	public TestBase testBase;
	public GenericUtilities genericUtilities;
	
	public TestContextSetup() throws IOException {
		testBase=new TestBase();
		pageObjectManager=new PageObjectManager(testBase.webDriverManager());
		genericUtilities=new GenericUtilities(testBase.webDriverManager());
	}
}
