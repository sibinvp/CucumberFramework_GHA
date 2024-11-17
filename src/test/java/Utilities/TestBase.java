package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public WebDriver driver;
	public WebDriver webDriverManager() throws IOException {
		Properties pi=new Properties();
		FileInputStream fi=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//Global.properties");
	pi.load(fi);
	String url=pi.getProperty("url");
	if(driver==null) {
	
//	/**	if(driver==null) {
//		String browserName=pi.getProperty("browser");
//			if(browserName.equalsIgnoreCase("edge")) {
//		System.setProperty("webdriver.msedge.driver", System.getProperty("user.dir")+"//src//test//resources//msedgedriver.exe");
//		EdgeOptions opt = new EdgeOptions();
//		opt.addArguments("-guest");
//		driver=new EdgeDriver(opt);
//		driver.manage().window().maximize();
//			}
//		else
//		{
//		System.out.println("Please select browser as edge");
//		}
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
//		driver.get(url);
//		System.out.println("browser is: "+browserName);
//		
//			
//			}
//		
//		return driver;
//	}
//	**/
	
	// below code will first check if any global system paramenter send via MVN call from command
	
			String browserName = System.getProperty("browser") != null?System.getProperty("browser"):pi.getProperty("browser");

			if (browserName.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.msedge.driver", "C:\\Selenium\\msedgedriver.exe");

				EdgeOptions opt = new EdgeOptions();
				opt.addArguments("-guest");
				driver = new EdgeDriver(opt);
				driver.manage().window().setSize(new Dimension(1440,900));
			}

			else if (browserName.contains("chrome")) {
				
				WebDriverManager.chromedriver().setup();
				ChromeOptions opt = new ChromeOptions();
				//opt.addArguments("-guest");
				
				System.out.println("chrome browser selected: "+browserName);
				
				if(browserName.contains("headless")) {
					opt.addArguments("headless");
					//opt.addArguments("--headless"); // Run in headless mode
			        opt.addArguments("--disable-gpu"); // Disable GPU hardware acceleration (useful in headless)
			        opt.addArguments("--window-size=1920x1080"); // Set window size (required for headless mode)
			        System.out.println("headless");
				}
				
				driver = new ChromeDriver(opt);
				driver.manage().window().setSize(new Dimension(1440,900));

			}
			driver.manage().window().maximize();
			// Implicit wait initialized
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
			driver.get(url);
					
	}
	
	return driver;
	}
}


