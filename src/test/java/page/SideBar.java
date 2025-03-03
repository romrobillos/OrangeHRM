package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

	public void clickMyInfo() {
		clickWaitElement(myInfo);
	}

	public WebElement waitToastNotif() {
		return waitForElement(toastNotif);
	}

	public void clickDashboard() {
		clickWaitElement(dashboard);

	}

	public void clickRecruitment() {
		clickWaitElement(recruitment);
	}
}
