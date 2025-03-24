package testcase.PIM;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import page.LoginPage;
import page.SideBar;
import page.PIM.EmployeeList;
import page.Recruitment.Candidates;
import testcase.BaseTest;

public class EmployeeListTest extends BaseTest {
	@Test(dataProvider = "validCredential", description = "Verify PIM EmployeeList if EmployeeName is Filtered from Auto Suggest")
	public void TC0_PIM_EmployeeList_isEmployeeNameFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		EmployeeList e = page.getInstance(EmployeeList.class);
		e.clickEmployeeList();

		String expectedEmployeeName = "Amelia Brown";
		e.searchAndSelectEmployeeNameThruAutoSuggest1(expectedEmployeeName);

		e.scrollBy(0, 500);

		boolean isItFiltered = e.isEmployeeNameFiltered(expectedEmployeeName);
		Assert.assertTrue(isItFiltered);

	}

	@Test(dataProvider = "validCredential", description = " Verify PIM EmployeeList if Manual search for Employee name is working")
	public void employeeNameManualSearch(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		EmployeeList e = page.getInstance(EmployeeList.class);
		e.clickEmployeeList();

		String expectedEmployeeName = "Amelia Brown";
		e.manualSearchEmployeeName(expectedEmployeeName);

		e.scrollBy(0, 500);

		boolean isItFiltered = e.isEmployeeNameFiltered(expectedEmployeeName);
		Assert.assertTrue(isItFiltered);

	}

	@Test(dataProvider = "validCredential", description = " Verify PIM EmployeeList if EmployeeIF got filtered ")
	public void TC0_PIM_Employeelist_isEmployeeIDFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		EmployeeList e = page.getInstance(EmployeeList.class);
		e.clickEmployeeList();

		String expectedEmployeeID = "0295";
		e.searchEmployeeID(expectedEmployeeID);

		e.scrollBy(0, 500);

		boolean isItFiltered = e.isEmployeeIDFiltered(expectedEmployeeID);
		Assert.assertTrue(isItFiltered);
	}
	
	@Test(dataProvider = "validCredential", description = " Verify PIM EmployeeList if Employee Status got filtered ")
	public void TC0_Recruitment_Candidates_isStatusFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		EmployeeList e = page.getInstance(EmployeeList.class);
		e.clickEmployeeList();

		String expectedEmployeeID = "Full-Time Contract";
		e.searchStatus(expectedEmployeeID);

		e.scrollBy(0, 500);

		boolean isItFiltered = e.isEmployeeIDFiltered(expectedEmployeeID);
		Assert.assertTrue(isItFiltered);
		
	}
	
	@Test(dataProvider = "validCredential", description = "Verify PIM EmployeeList if SupervisorName is Filtered from Auto Suggest")
	public void TC0_PIM_EmployeeList_isSupervisorNameFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		EmployeeList e = page.getInstance(EmployeeList.class);
		e.clickEmployeeList();

		String expectedSupervisorName = " ";
		e.manualSearchSupervisorName(expectedSupervisorName);

		e.scrollBy(0, 500);

		boolean isItFiltered = e.isSupervisorNameFiltered(expectedSupervisorName);
		Assert.assertTrue(isItFiltered);

	}
	
	@Test(dataProvider = "validCredential", description = " Verify PIM EmployeeList if Job title got filtered ")
	public void TC0_PIM_EmployeeList_isJobTitleFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		EmployeeList e = page.getInstance(EmployeeList.class);
		e.clickEmployeeList();

		String expectedJobtitle = "HR Manager";
		e.searchJobtitle(expectedJobtitle);

		e.scrollBy(0, 500);

		boolean isItFiltered = e.isJobtitleFiltered(expectedJobtitle);
		Assert.assertTrue(isItFiltered);
		
	}
	
	@Test(dataProvider = "validCredential", description = " Verify PIM EmployeeList if Sub Unit got filtered ")
	public void TC0_PIM_EmployeeList_isSubUnitFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		EmployeeList e = page.getInstance(EmployeeList.class);
		e.clickEmployeeList();

		String expectedSubUnit = "Administration";
		e.searchSubUnit(expectedSubUnit);

		e.scrollBy(0, 500);

		boolean isItFiltered = e.isSubUnitFiltered(expectedSubUnit);
		Assert.assertTrue(isItFiltered);
		
	}
	
	@Test(dataProvider = "validCredential", description = " Verify PIM EmployeeList Reset Button")
	public void TC0_PIM_EmployeeList_isAtReset(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		EmployeeList e = page.getInstance(EmployeeList.class);
		e.clickEmployeeList();

		String garbageValue = "QA Lead";

		e.searchJobtitle(garbageValue);
		e.clickResetBtn();

		String expectedEmployeeName = "";
		String expectedEmployeeID = "";
		String expectedStatus = "-- Select --";
		String expectedSupervisorName = "";
		String expectedJobtitle = "-- Select --";
		String expectedSubUnit = "-- Select --";

		String actualEmployeeName =  e.getEmployeeName().getAttribute("value");
		String actualEmployeeID  = e.getEmployeeID().getAttribute("value");
		String actualSupervisorName = e.getSupervisorName().getAttribute("value");
		String actualStatus  = e.getStatusTxt();
		String actualJobtitle = e.getJobtitleTxt();
		String actualSubUnit = e.getSubUnitTxt();

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualEmployeeName, expectedEmployeeName);
		softAssert.assertEquals(actualEmployeeID, expectedEmployeeID);
		softAssert.assertEquals(actualStatus, expectedStatus);
		softAssert.assertEquals(actualSupervisorName, expectedSupervisorName);
		softAssert.assertEquals(actualJobtitle, expectedJobtitle);
		softAssert.assertEquals(actualSubUnit, expectedSubUnit);
		softAssert.assertAll();

	}
	
	@Test(dataProvider = "validCredential", description = " Verify PIM EmployeeList is at Add Employee ")
	public void TC0_PIM_EmployeeList_isAtAddEmployee(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		EmployeeList e = page.getInstance(EmployeeList.class);
		e.clickEmployeeList();
		
		boolean isAt = e.isAtAddEmployee();
		Assert.assertTrue(isAt);
		
	}
}
