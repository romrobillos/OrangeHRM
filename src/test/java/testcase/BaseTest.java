package testcase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public String username, password, errorMessage;
	
	@BeforeMethod
	public void setUp() throws IOException {
	 
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("https://opensource-demo.orangehrmlive.com/");
			driver.manage().window().maximize();
  
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
 

	@AfterMethod
	public void tearDown() {
		driver.quit();
		System.out.println("Closed succesful");
	}
	
	@DataProvider(name = "validCredential")
	public Object[][] ProblemAndError() {
		return new Object[][] { { "Admin", "admin123" } };
	}
}
