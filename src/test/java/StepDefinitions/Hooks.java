package StepDefinitions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Utilities.TestContextSetup;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Hooks {
	
TestContextSetup testContextSetup;
public Hooks(TestContextSetup testContextSetup) {
this.testContextSetup=testContextSetup;
}
	
   @After
	public void closeBrowser() throws IOException, InterruptedException {
		Thread.sleep(1000);
		testContextSetup.testBase.webDriverManager().quit();
	}
   
   @AfterStep
   public void addScreenShot(Scenario scenario) throws IOException {
	   
	   if (scenario.isFailed()) {
		WebDriver driver=testContextSetup.testBase.webDriverManager();   
	  TakesScreenshot ts= (TakesScreenshot)driver;
	  File source=ts.getScreenshotAs(OutputType.FILE);
	  byte[] fileByte= FileUtils.readFileToByteArray(source);
	  scenario.attach(fileByte, "image/png", "failed Step");
   }
   }
}
