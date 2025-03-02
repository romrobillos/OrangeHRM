package page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SideBar extends BasePage {

	@FindBy(xpath = "//span[normalize-space()='My Info']")
	WebElement myInfo;

	@FindBy(xpath = "//*[@id=\"oxd-toaster_1\"]")
	WebElement toastNotif;

	@FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Dashboard']")
	WebElement dashboard;

	@FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Recruitment']")
	WebElement recruitment;

	public SideBar(WebDriver driver) {
		super(driver);
	}

	public WebElement getMyInfo() {
		return myInfo;
	}

    public WebElement getToastNotif() {
    	return waitForElement(toastNotif);
    }

	public void  clickDashboard() {
		clickWaitElement(dashboard);
		
	}

	public WebElement getRecruitment() {
		return recruitment;
	}
}
