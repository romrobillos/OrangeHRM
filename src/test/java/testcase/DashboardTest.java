package testcase;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.Dashboard;
import page.LoginPage;

public class DashboardTest extends BaseTest {

	@Test(dataProvider = "validCredential", description = "Verify timeAtWork_stopwatch")
	public void TC008_timeAtWork_stopwatch(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		Dashboard db = new Dashboard(driver);
		db.getDashboard().click();
		db.getDb_timeAtWork_stopwatch().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String actualTimeUrl = driver.getCurrentUrl();
		String expectedTimeUrl = prop.getProperty("time");

		Assert.assertEquals(expectedTimeUrl, actualTimeUrl);
	}

	@Test(dataProvider = "validCredential", description = "Verify QuickLaunch_AssignLeave")
	public void TC009_quickLaunch_assignLeave(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		Dashboard db = new Dashboard(driver);
		db.getDashboard().click();
		db.getDb_quickLaunch_assignLeave().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String actualAssignLeaveUrl = driver.getCurrentUrl();
		String expectedAssignLeaveUrl = prop.getProperty("assignLeave");

		Assert.assertEquals(actualAssignLeaveUrl, expectedAssignLeaveUrl);
	}

	@Test(dataProvider = "validCredential", description = "Verify QuickLaunch_LeaveList")
	public void TC010_quickLaunch_leaveList(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		Dashboard db = new Dashboard(driver);
		db.getDashboard().click();
		db.getDb_quickLaunch_leavelist().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String actualLeavelistUrl = driver.getCurrentUrl();
		String expectedLeavelistUrl = prop.getProperty("leaveList");

		Assert.assertEquals(actualLeavelistUrl, expectedLeavelistUrl);
	}

	@Test(dataProvider = "validCredential", description = "Verify QuickLaunch_Timesheets")
	public void TC011_quickLaunch_timesheets(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		Dashboard db = new Dashboard(driver);
		db.getDashboard().click();
		db.getDb_quickLaunch_timesheets().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String actualTimesheetsUrl = driver.getCurrentUrl();
		String expectedTimesheetsUrl = prop.getProperty("timesheets");
	 
		Assert.assertEquals(actualTimesheetsUrl, expectedTimesheetsUrl);
	}

	@Test(dataProvider = "validCredential", description = "Verify QuickLaunch_ApplyLeave")
	public void TC012_quickLaunch_applyLeave(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		Dashboard db = new Dashboard(driver);
		db.getDashboard().click();
		db.getDb_quickLaunch_applyLeave().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String actualApplyLeaveUrl = driver.getCurrentUrl();
		String expectedApplyLeaveUrl = prop.getProperty("applyLeave");

		Assert.assertEquals(actualApplyLeaveUrl, expectedApplyLeaveUrl);
	}

	@Test(dataProvider = "validCredential", description = "Verify QuickLaunch_myLeave")
	public void TC013_quickLaunch_myLeave(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		Dashboard db = new Dashboard(driver);
		db.getDashboard().click();
		db.getDb_quickLaunch_myLeave().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String actualmyLeaveUrl = driver.getCurrentUrl();
		String expectedmyLeaveUrl = prop.getProperty("myLeave");

		Assert.assertEquals(actualmyLeaveUrl, expectedmyLeaveUrl);
	}

	@Test(dataProvider = "validCredential", description = "Verify QuickLaunch_myTimesheet")
	public void TC014_quickLaunch_myTimesheet(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		Dashboard db = new Dashboard(driver);
		db.getDashboard().click();
		db.getDb_quickLaunch_myTimesheet().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String actualmyTimesheetUrl = driver.getCurrentUrl();
		String expectedmyTimesheetUrl = prop.getProperty("myTimesheet");

		Assert.assertEquals(actualmyTimesheetUrl, expectedmyTimesheetUrl);
	}
}
