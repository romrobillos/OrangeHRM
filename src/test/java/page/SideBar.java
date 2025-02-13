package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SideBar extends BasePage{

	@FindBy(xpath = "//span[normalize-space()='My Info']")
	WebElement myInfo;
	
	
	public SideBar(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getMyInfo() {
		return myInfo;
	}

}
