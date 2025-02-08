package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dashboard extends BasePage {

	@FindBy (xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Dashboard']")
	WebElement dashboard;
	
	@FindBy (xpath = "//i[@class='oxd-icon bi-stopwatch']")
	WebElement db_timeAtWork_stopwatch;

	
	// Quick Launch
	@FindBy (xpath = "//button[@title='Assign Leave']//*[name()='svg']//*[name()='g' and contains(@fill,'currentCol')]//*[name()='path' and contains(@class,'cls-1')]")
	WebElement db_quickLaunch_assignLeave;
	
	@FindBy (xpath = "//button[@title='Leave List']//*[name()='svg']//*[name()='g' and contains(@fill,'currentCol')]//*[name()='g'][1]")
	WebElement db_quickLaunch_leavelist;
	
	@FindBy (xpath = "//button[@title='Timesheets']//*[name()='svg']//*[name()='g' and contains(@fill,'currentCol')]//*[name()='g'][1]")
	WebElement db_quickLaunch_timesheets;
	
	@FindBy (xpath = "//button[@title='Apply Leave']//*[name()='svg']//*[name()='g' and contains(@fill,'currentCol')]//*[name()='path' and contains(@class,'cls-1')]")
	WebElement db_quickLaunch_applyLeave;
	
	@FindBy (xpath = "//button[@title='My Leave']//*[name()='svg']//*[name()='g' and contains(@fill,'currentCol')]//*[name()='path' and contains(@class,'cls-1')]")
	WebElement db_quickLaunch_myLeave;
	
	@FindBy (xpath = "//button[@title='My Timesheet']//*[name()='svg']//*[name()='g' and contains(@fill,'currentCol')]//*[name()='path' and contains(@class,'cls-1')]")
	WebElement db_quickLaunch_myTimesheet;
	
	
	public Dashboard(WebDriver driver) {
		super(driver); 
	}
	
	public WebElement getDashboard() {
		return dashboard;
	}
	
	public WebElement getDb_timeAtWork_stopwatch() {
		return db_timeAtWork_stopwatch;
	}
	
	// Quick Launch 
	public WebElement getDb_quickLaunch_assignLeave() {
		return db_quickLaunch_assignLeave;
	}
	
	public WebElement getDb_quickLaunch_leavelist() {
		return db_quickLaunch_leavelist;
	}
	
	public WebElement getDb_quickLaunch_timesheets() {
		return db_quickLaunch_timesheets;
	}
	
	public WebElement getDb_quickLaunch_applyLeave() {
		return db_quickLaunch_applyLeave;
	}
	
	public WebElement getDb_quickLaunch_myLeave() {
		return db_quickLaunch_myLeave;
	}

	public WebElement getDb_quickLaunch_myTimesheet() {
		return db_quickLaunch_myTimesheet;
	}

}
