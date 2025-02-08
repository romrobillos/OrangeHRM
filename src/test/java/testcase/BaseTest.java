package testcase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static FileReader fr;
	WebDriverWait wait;
	
	@BeforeMethod
	public void setUp() throws IOException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		
		FileReader fr = new FileReader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\config.properties");
		prop.load(fr);
		
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
