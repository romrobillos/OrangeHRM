package testcase.PIM;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.SideBar;
import page.PIM.Reports;
import testcase.BaseTest;

public class ReportsTest extends BaseTest {

	@Test(dataProvider = "validCredential", description = "Verify PIM_Reports if Report Name is Filtered from Auto Suggest")
	public void TC0_PIM_Reports_isReportNameFiltered(String username, String password) {
		// Login
		page.getInstance(LoginPage.class).toLogin(username, password);
		
		// Navigate to Reports
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		Reports reportPage = page.getInstance(Reports.class);
		reportPage.clickReports();

		// Enter Report name thru Auto suggest
		String expectedReportName = "Employee Job Details";
		reportPage.searchAndSelectReportNameThruAutoSuggest1(expectedReportName);
		reportPage.scrollBy(0, 500);

		// Assertions
		boolean isItFiltered = reportPage.isReportNameFiltered(expectedReportName);
		Assert.assertTrue(isItFiltered);
	}

	@Test(dataProvider = "validCredential", description = " Verify PIM EmployeeList Reset Button")
	public void TC0_PIM_EmployeeList_isAtReset(String username, String password) {
		// Login
		page.getInstance(LoginPage.class).toLogin(username, password);
		
		// Navigate to Reports
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		Reports reportPage = page.getInstance(Reports.class);
		reportPage.clickReports();

		// Enter and reset a report name
		String garbageValue = "Employee Job Details";
		reportPage.searchAndSelectReportNameThruAutoSuggest1(garbageValue);
		reportPage.clickResetBtn();

		// Validate that report name input is cleared
		String expectedReportName = "";
		String actualReportName = reportPage.getReportNameValue();

		// Assertions
		Assert.assertEquals(actualReportName, expectedReportName);

	}

	@Test(dataProvider = "validCredential", description = " Verify PIM_Reports Employee Reports Toggle Button")
	public void TC0_PIM_EmployeeList_isEmployeeReportToggleWorking(String username, String password) {
		// Login
		page.getInstance(LoginPage.class).toLogin(username, password);
		
		// Navigate to Reports
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		Reports reportPage = page.getInstance(Reports.class);
		reportPage.clickReports();

		// Assertions
		boolean beforeToggle = reportPage.isReportNameToggleWorking();
		Assert.assertTrue(beforeToggle);

	}

	@Test(dataProvider = "validCredential", description = "Verify PIM_Reports if Selection Criteria Matched and Displayed")
	public void TC0_PIM_Reports_Add_isSelectionCriteriaMatchedAndDisplayed(String username, String password)
			throws InterruptedException {
		// Login
		page.getInstance(LoginPage.class).toLogin(username, password);

		// Navigate to Reports
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		Reports reportPage = page.getInstance(Reports.class);
		reportPage.clickReports();
		reportPage.clickAddReportBtn();
		reportPage.scrollBy(0, 500);

		// Report data
		Map<String, Object> reportDetails = new HashMap<>();
		reportDetails.put("Report Name", "Report");
		reportDetails.put("Selection Criteria", Arrays.asList("Employment Status", "Job Title", "Language"));
		reportDetails.put("Matched Selection Criteria",
				Map.of("Employment Status", "Freelance", "Job Title", "Automaton Tester", "Language", "English"));
		reportDetails.put("Include", "Current and Past Employees");

		// Input fields
		reportPage.inputReportName((String) reportDetails.get("Report Name"));
		reportPage.selectInclude((String) reportDetails.get("Include"));

		List<String> criteriaList = (List<String>) reportDetails.get("Selection Criteria");
		Map<String, String> criteriaMap = (Map<String, String>) reportDetails.get("Matched Selection Criteria");
		reportPage.addSelectionCriteria(criteriaList, criteriaMap);

		// Assertion
		boolean isItDisplayed = reportPage.isSelectionCriteriaMatchedAndDisplayed(criteriaList, criteriaMap);
		Assert.assertTrue(isItDisplayed);
	}

	@Test(dataProvider = "validCredential", description = "Verify PIM_Reports if Display Fields Matched and Added")
	public void TC0_PIM_EmployeeList_isDisplayFieldMatchedAndDisplayed(String username, String password)
			throws InterruptedException {
		// Login
		page.getInstance(LoginPage.class).toLogin(username, password);

		// Navigate to Reports
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		Reports reportPage = page.getInstance(Reports.class);
		reportPage.clickReports();
		reportPage.clickAddReportBtn();
		reportPage.scrollBy(0, 500);

		// Report data
		Map<String, Object> reportDetails = new HashMap<>();
		reportDetails.put("Report Name", "Report");
		reportDetails.put("Selection Criteria", Arrays.asList("Employment Status", "Job Title"));
		reportDetails.put("Matched Selection Criteria",
				Map.of("Employment Status", "Freelance", "Job Title", "Automaton Tester"));
		reportDetails.put("Include", "Current and Past Employees");
		reportDetails.put("Display Field Group", Arrays.asList("Personal", "Contact Details"));
		reportDetails.put("Matched Display Field",
				Map.of("Personal", "Employee Last Name", "Contact Details", "Address"));

		// Input fields
		reportPage.inputReportName((String) reportDetails.get("Report Name"));
		reportPage.selectInclude((String) reportDetails.get("Include"));

		List<String> criteriaList = (List<String>) reportDetails.get("Selection Criteria");
		Map<String, String> criteriaMap = (Map<String, String>) reportDetails.get("Matched Selection Criteria");
		reportPage.addSelectionCriteria(criteriaList, criteriaMap);

		List<String> fieldList = (List<String>) reportDetails.get("Display Field Group");
		Map<String, String> fieldMap = (Map<String, String>) reportDetails.get("Matched Display Field");
		reportPage.addDisplayFields(fieldList, fieldMap);

		// Assertion
		boolean isAddedDisplayFieldsCorrect = reportPage.isAddedDisplayFieldsCorrect(fieldList, fieldMap);
		Assert.assertTrue(isAddedDisplayFieldsCorrect);
	}

}
