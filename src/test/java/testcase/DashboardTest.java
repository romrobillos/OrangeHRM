package testcase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.Dashboard;
import page.LoginPage;
import page.SideBar;

public class DashboardTest extends BaseTest {

	@Test(dataProvider = "validCredential", description = "Verify timeAtWork_stopwatch")
	public void TC008_timeAtWork_stopwatch(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		page.getInstance(SideBar.class).clickDashboard();
		Dashboard db = page.getInstance(Dashboard.class);
		db.clickDb_timeAtWork_stopwatch();
		String actualTimeUrl = db.getCurrentPageUrl();
		String expectedTimeUrl = prop.getProperty("time");
		Assert.assertEquals(expectedTimeUrl, actualTimeUrl);
	}

	@Test(dataProvider = "validCredential", description = "Verify QuickLaunch_AssignLeave")
	public void TC009_quickLaunch_assignLeave(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		page.getInstance(SideBar.class).clickDashboard();
		Dashboard db = page.getInstance(Dashboard.class);
		db.clickDb_quickLaunch_assignLeave();
		String actualAssignLeaveUrl = db.getCurrentPageUrl();
		String expectedAssignLeaveUrl = prop.getProperty("assignLeave");

		Assert.assertEquals(actualAssignLeaveUrl, expectedAssignLeaveUrl);
	}

	@Test(dataProvider = "validCredential", description = "Verify QuickLaunch_LeaveList")
	public void TC010_quickLaunch_leaveList(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		page.getInstance(SideBar.class).clickDashboard();
		Dashboard db = page.getInstance(Dashboard.class);
		db.clickDb_quickLaunch_leavelist();
		String actualLeavelistUrl = db.getCurrentPageUrl();
		String expectedLeavelistUrl = prop.getProperty("leaveList");

		Assert.assertEquals(actualLeavelistUrl, expectedLeavelistUrl);
	}

	@Test(dataProvider = "validCredential", description = "Verify QuickLaunch_Timesheets")
	public void TC011_quickLaunch_timesheets(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		page.getInstance(SideBar.class).clickDashboard();
		Dashboard db = page.getInstance(Dashboard.class);
		db.clickDb_quickLaunch_timesheets();
		String actualTimesheetsUrl = driver.getCurrentUrl();
		String expectedTimesheetsUrl = prop.getProperty("timesheets");

		Assert.assertEquals(actualTimesheetsUrl, expectedTimesheetsUrl);
	}

	@Test(dataProvider = "validCredential", description = "Verify QuickLaunch_ApplyLeave")
	public void TC012_quickLaunch_applyLeave(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		page.getInstance(SideBar.class).clickDashboard();
		Dashboard db = page.getInstance(Dashboard.class);
		db.clickDb_quickLaunch_applyLeave();
		String actualApplyLeaveUrl = db.getCurrentPageUrl();
		String expectedApplyLeaveUrl = prop.getProperty("applyLeave");

		Assert.assertEquals(actualApplyLeaveUrl, expectedApplyLeaveUrl);
	}

	@Test(dataProvider = "validCredential", description = "Verify QuickLaunch_myLeave")
	public void TC013_quickLaunch_myLeave(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		page.getInstance(SideBar.class).clickDashboard();
		Dashboard db = page.getInstance(Dashboard.class);
		db.clickDb_quickLaunch_myLeave();
		String actualmyLeaveUrl = driver.getCurrentUrl();
		String expectedmyLeaveUrl = prop.getProperty("myLeave");

		Assert.assertEquals(actualmyLeaveUrl, expectedmyLeaveUrl);
	}

	@Test(dataProvider = "validCredential", description = "Verify QuickLaunch_myTimesheet")
	public void TC014_quickLaunch_myTimesheet(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		page.getInstance(SideBar.class).clickDashboard();
		Dashboard db = page.getInstance(Dashboard.class);
		db.clickDb_quickLaunch_myTimesheet();
		String actualmyTimesheetUrl = driver.getCurrentUrl();
		String expectedmyTimesheetUrl = prop.getProperty("myTimesheet");

		Assert.assertEquals(actualmyTimesheetUrl, expectedmyTimesheetUrl);
	}

	@Test(dataProvider = "validCredential", description = "Verify employeesOnLeaveToday_Settings")
	public void TC015_employeesOnLeaveToday_Settings(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		page.getInstance(SideBar.class).clickDashboard();
		Dashboard db = page.getInstance(Dashboard.class);
		db.clickDb_employeesOnLeaveToday_Settings();

		Assert.assertTrue(db.getDb_employeesOnLeaveToday_Settings_Popup().isDisplayed());
	}

	@Test(dataProvider = "validCredential", description = "Verify employeesOnLeaveToday_Settings_Toggle")
	public void TC016_employeesOnLeaveToday_Settings_Toggle(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		page.getInstance(SideBar.class).clickDashboard();
		Dashboard db = page.getInstance(Dashboard.class);
		db.clickDb_employeesOnLeaveToday_Settings();
		
		WebElement toggle = db.getDb_employeesOnLeaveToday_Settings_Popup_Toggle();

		boolean beforeToggle = toggle.getAttribute("class").contains("oxd-switch-input--active");

		db.clickDb_employeesOnLeaveToday_Settings_Popup_Toggle();

		db.waitForCssValueChange(toggle, "class", beforeToggle ? "" : "oxd-switch-input--active");

		boolean afterToggle = toggle.getAttribute("class").contains("oxd-switch-input--active");

		Assert.assertNotEquals(beforeToggle, afterToggle);
	}

	@Test(dataProvider = "validCredential", description = "Verify employeesOnLeaveToday_Settings_Save Button")
	public void TC017_employeesOnLeaveToday_Settings_Save(String username, String password)
			throws InterruptedException {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickDashboard();
		Dashboard db = page.getInstance(Dashboard.class);
		db.clickDb_employeesOnLeaveToday_Settings();
		db.clickDb_employeesOnLeaveToday_Settings_Popup_Save();
		WebElement toastNotif = sb.waitToastNotif();
		System.out.println(toastNotif.getText());
		Assert.assertTrue(toastNotif.isDisplayed());
	}

	@Test(dataProvider = "validCredential", description = "Verify employeesOnLeaveToday_Settings_Cancel Button")
	public void TC018_employeesOnLeaveToday_Settings_Cancel(String username, String password)
			throws InterruptedException {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickDashboard();
		Dashboard db = page.getInstance(Dashboard.class);
		db.clickDb_employeesOnLeaveToday_Settings();

		db.clickDb_employeesOnLeaveToday_Settings_Popup_Cancel();
		boolean isPopupClosed = wait
				.until(ExpectedConditions.invisibilityOf(db.getDb_employeesOnLeaveToday_Settings_Popup()));

		Assert.assertTrue(isPopupClosed);
	}

}
