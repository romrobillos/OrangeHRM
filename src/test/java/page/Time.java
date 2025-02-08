package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Time extends BasePage{

	@FindBy (xpath = "//a[normalize-space()='Punch In/Out']")
	WebElement time_att_punch;
	
	
	public Time(WebDriver driver) {
		super(driver);
	}

}
