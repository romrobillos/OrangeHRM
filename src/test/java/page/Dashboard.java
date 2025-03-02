package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dashboard extends BasePage {

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div[1]/div[2]/button")
	WebElement db_timeAtWork_stopwatch;

	// Quick Launch
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[1]/button")
	WebElement db_quickLaunch_assignLeave;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[2]/button")
	WebElement db_quickLaunch_leavelist;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[3]/button")
	WebElement db_quickLaunch_timesheets;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[4]/button")
	WebElement db_quickLaunch_applyLeave;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[5]/button")
	WebElement db_quickLaunch_myLeave;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[6]/button")
	WebElement db_quickLaunch_myTimesheet;

	@FindBy(xpath = "//i[@class='oxd-icon bi-gear-fill orangehrm-leave-card-icon']")
	WebElement db_employeesOnLeaveToday_Settings;

	@FindBy(xpath = "//div[@role='document']")
	WebElement db_employeesOnLeaveToday_Settings_Popup;

	@FindBy(xpath = "//span[contains(@class, 'oxd-switch-input')]")
	WebElement db_employeesOnLeaveToday_Settings_Popup_Toggle;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement db_employeesOnLeaveToday_Settings_Popup_Save;

	@FindBy(xpath = "//button[normalize-space()='Cancel']")
	WebElement db_employeesOnLeaveToday_Settings_Popup_Cancel;

	public Dashboard(WebDriver driver) {
		super(driver);
	}

	public void clickDb_timeAtWork_stopwatch() {
		clickWaitElement(db_timeAtWork_stopwatch);
	}

	// Quick Launch
	public void clickDb_quickLaunch_assignLeave() {
		clickWaitElement(db_quickLaunch_assignLeave);
	}

	public void clickDb_quickLaunch_leavelist() {
		clickWaitElement(db_quickLaunch_leavelist);
	}

	public void clickDb_quickLaunch_timesheets() {
		clickWaitElement(db_quickLaunch_timesheets);
	}

	public void clickDb_quickLaunch_applyLeave() {
		clickWaitElement(db_quickLaunch_applyLeave);
	}

	public void clickDb_quickLaunch_myLeave() {
		clickWaitElement(db_quickLaunch_myLeave);
	}

	public void clickDb_quickLaunch_myTimesheet() {
		clickWaitElement(db_quickLaunch_myTimesheet);
	}

	public void clickDb_employeesOnLeaveToday_Settings() {
		clickWaitElement(db_employeesOnLeaveToday_Settings);
	}

	public WebElement getDb_employeesOnLeaveToday_Settings_Popup() {
		return waitForElement(db_employeesOnLeaveToday_Settings_Popup);
	}

	public WebElement getDb_employeesOnLeaveToday_Settings_Popup_Toggle() {
		return waitForElement(db_employeesOnLeaveToday_Settings_Popup_Toggle);
	}
	
	public void clickDb_employeesOnLeaveToday_Settings_Popup_Toggle() {
		 db_employeesOnLeaveToday_Settings_Popup_Toggle.click(); //.click to prevent double waiting. clickWaitElement() (X)
	}

	public void clickDb_employeesOnLeaveToday_Settings_Popup_Save() {
		clickWaitElement(db_employeesOnLeaveToday_Settings_Popup_Save);
	}

	public void clickDb_employeesOnLeaveToday_Settings_Popup_Cancel() {
		clickWaitElement(db_employeesOnLeaveToday_Settings_Popup_Cancel);
	}
	

}
