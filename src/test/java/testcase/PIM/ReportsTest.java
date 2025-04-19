package testcase.PIM;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import page.LoginPage;
import page.SideBar;
import page.PIM.EmployeeList;
import page.PIM.Reports;
import testcase.BaseTest;

public class ReportsTest extends BaseTest {

	@Test(dataProvider = "validCredential", description = "Verify PIM_Reports if Report Name is Filtered from Auto Suggest")
	public void TC0_PIM_Reports_isReportNameFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		Reports r = page.getInstance(Reports.class);
		r.clickReports();

		String expectedReportName = "Employee Job Details";
		r.searchAndSelectReportNameThruAutoSuggest1(expectedReportName);

		r.scrollBy(0, 500);

		boolean isItFiltered = r.isReportNameFiltered(expectedReportName);
		Assert.assertTrue(isItFiltered);
	}

	@Test(dataProvider = "validCredential", description = " Verify PIM EmployeeList Reset Button")
	public void TC0_PIM_EmployeeList_isAtReset(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		Reports r = page.getInstance(Reports.class);
		r.clickReports();

		String garbageValue = "Employee Job Details";
		r.searchAndSelectReportNameThruAutoSuggest1(garbageValue);
		r.clickResetBtn();

		String expectedReportName = "";

		String actualReportName = r.getReportNameValue();

		Assert.assertEquals(actualReportName, expectedReportName);

	}

	@Test(dataProvider = "validCredential", description = " Verify PIM_Reports Employee Reports Toggle Button")
	public void TC0_PIM_EmployeeList_isEmployeeReportToggleWorking(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		Reports r = page.getInstance(Reports.class);
		r.clickReports();

		boolean beforeToggle = r.isReportNameToggleWorking();
		Assert.assertTrue(beforeToggle);

	}

	@Test(dataProvider = "validCredential", description = "Verify PIM_Reports if Selection Criteria added correctly")
	public void TC0_PIM_Reports_Add_SelectionCriteria(String username, String password) throws InterruptedException {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		Reports r = page.getInstance(Reports.class);
		r.clickReports();
		r.clickAddReportBtn();
		r.scrollBy(0, 500);

		// Store report-related properties in a HashMap
		Map<String, Object> reportDetails = new HashMap<>();
		reportDetails.put("Report Name", "Report");
		reportDetails.put("Selection Criteria", Arrays.asList("Employment Status", "Job Title", "Language"));
		reportDetails.put("Include", "Current and Past Employees");
		reportDetails.put("Input Selection Criteria",
				Map.of("Employment Status", "Freelance", "Job Title", "Automaton Tester", "Language", "English"));

	    // Use data
	    r.inputReportName((String) reportDetails.get("Report Name"));
	    r.selectInclude((String) reportDetails.get("Include"));

	    List<String> criteriaList = (List<String>) reportDetails.get("Selection Criteria");
	    Map<String, String> inputCriteriaMap = (Map<String, String>) reportDetails.get("Input Selection Criteria");

	    r.addSelectionCriteria(criteriaList, inputCriteriaMap);
	    
	    boolean isItDisplayed = r.isSelectionCriteriaDisplayed(criteriaList, inputCriteriaMap);
	    Assert.assertTrue(isItDisplayed);
	}

	@Test(dataProvider = "validCredential", description = "Verify PIM_Reports Employee Reports Toggle Button")
	public void TC0_PIM_EmployeeList_toAddReport(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		Reports r = page.getInstance(Reports.class);
		r.clickReports();
		r.clickAddReportBtn();
		r.scrollBy(0, 500);

		// Store report-related properties in a HashMap
		Map<String, String> reportDetails = new HashMap<>();
		reportDetails.put("Report Name", "Report");
		reportDetails.put("Selection Criteria", "Employment Status");
		reportDetails.put("Include", "Current and Past Employees");
		reportDetails.put("Display Field Group", "Personal");
		reportDetails.put("Display Field", "Employee Id");

		// Set report details dynamically
		r.inputReportName(reportDetails.get("Report Name"));
		r.selectInclude(reportDetails.get("Include"));

		r.searchDisplayFieldGroup(reportDetails.get("Display Field Group"));
		r.searchDisplayField(reportDetails.get("Display Field"));
		r.clickDisplayFields_plusIcon();

		r.searchDisplayFieldGroup("Dependents");
		r.clickDisplayFields_plusIcon();

		// Validate if the added fields are correct
		boolean isAddedDisplayFieldsCorrect = r.isAddedDisplayFieldsCorrect(reportDetails.get("Display Field Group"),
				reportDetails.get("Display Field"));

		Assert.assertTrue(isAddedDisplayFieldsCorrect);
	}
}
