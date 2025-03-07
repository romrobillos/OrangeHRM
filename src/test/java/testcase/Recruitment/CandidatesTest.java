package testcase.Recruitment;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import page.LoginPage;
import page.SideBar;
import page.Recruitment.Candidates;
import testcase.BaseTest;

public class CandidatesTest extends BaseTest {

	@Test(dataProvider = "validCredential", description = "Verify Recruitment Candidates if Vacancy is Filtered")
	public void TC0_Recruitment_Candidates_isVacancyFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Candidates c = page.getInstance(Candidates.class);
		c.clickCandidatesSubTab();

		String expectedVacancy = "Payroll Administrator";
		c.searchVacancy(expectedVacancy);

		c.scrollBy(0, 500);

		boolean isItFiltered = c.isVacancyFiltered(expectedVacancy);
		Assert.assertTrue(isItFiltered);

	}

	@Test(dataProvider = "validCredential", description = "Verify Recruitment Candidates if Candidate Name is Filtered")
	public void TC0_Recruitment_Candidates_isCandidateFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Candidates c = page.getInstance(Candidates.class);
		c.clickCandidatesSubTab();

		String expectedCandidateName = "Gautham Raj R";
		c.searchCandidateByNameThruAutoSuggest(expectedCandidateName);

		c.scrollBy(0, 500);

		boolean isItFiltered = c.isCandidateNameFiltered(expectedCandidateName);
		Assert.assertTrue(isItFiltered);

	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment_Candidates if Manual search for Candidates name is working")
	public void candidateNameManualSearch(String username, String password) throws InterruptedException {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Candidates c = page.getInstance(Candidates.class);
		c.clickCandidatesSubTab();

		String[] testNames = { "John", "Doe", "John Doe" };

		SoftAssert softAssert = new SoftAssert();
		for (String testName : testNames) {
			c.searchCandidateByNameThruManual(testName);

			boolean result = c.isManualSearchGotFilteredOrInvalid(testName);

			softAssert.assertFalse(result,
					"FAILED: Invalid search message displayed OR search results did not match the expected name filter for: "
							+ testName);
		}

		softAssert.assertAll();
	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment Candidates if Hiring Manager are filtered")
	public void TC0_Recruitment_Candidates_isHiringManagerFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Candidates c = page.getInstance(Candidates.class);
		c.clickCandidatesSubTab();

		String expectedManager = "manda";
		c.searchHiringManager(expectedManager);

		c.scrollBy(0, 500);

		boolean isItFiltered = c.isHiringManagerFiltered(expectedManager);
		Assert.assertTrue(isItFiltered);

	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment_Candidates if Date is WithinRange")
	public void TC0_Recruitment_Candidates_isDateFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Candidates c = page.getInstance(Candidates.class);
		c.clickCandidatesSubTab();

		String expectedDateFrom = "2024-02-02";
		String expectedDateTo = "2024-06-02";

		c.searchDate(expectedDateFrom, expectedDateTo);

		c.scrollBy(0, 500);

		boolean isWithinRange = c.isDateOfApplicationWithinRange();
		Assert.assertTrue(isWithinRange, "Some dates are out of the specified range!");

	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment Candidates if Status are filtered")
	public void TC0_Recruitment_Candidates_isStatusFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Candidates c = page.getInstance(Candidates.class);
		c.clickCandidatesSubTab();

		String expectedStatus = "Shortlisted";

		c.searchStatus(expectedStatus);

		c.scrollBy(0, 500);

		boolean isItFiltered = c.isStatusFiltered(expectedStatus);
		Assert.assertTrue(isItFiltered);

	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment_Candidates Reset Button")
	public void TC0_Recruitment_Candidates_isReset(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Candidates c = page.getInstance(Candidates.class);
		c.clickCandidatesSubTab();

		String garbageValue = "Senior QA Lead";

		c.searchVacancy(garbageValue);

		c.clickReset();

		String expectedVacancy = "-- Select --";
		String expectedHiringManager = "-- Select --";
		String expectedStatus = "-- Select --";
		String expectedCandidateName = "";
		String expectedDateFrom = "";
		String expectedDateTo = "";

		String actualVacancy = c.getVacancyTxt();
		String actualHiringManager = c.getHiringManagerTxt();
		String actualStatus = c.getStatusTxt();
		String actualCandidateName = c.getCandidateName().getAttribute("value");
		String actualDateFrom = c.getdateFrom().getAttribute("value");
		String actualDateTo = c.getdateTo().getAttribute("value");

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualVacancy, expectedVacancy);
		softAssert.assertEquals(actualHiringManager, expectedHiringManager);
		softAssert.assertEquals(actualStatus, expectedStatus);
		softAssert.assertEquals(actualCandidateName, expectedCandidateName);
		softAssert.assertEquals(actualDateFrom, expectedDateFrom);
		softAssert.assertEquals(actualDateTo, expectedDateTo);
		softAssert.assertAll();

	}

	@Test(dataProvider = "validCredential", description = " Verify Added Candidate")
	public void TC0_Recruitment_Candidates_addCandidate(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Candidates c = page.getInstance(Candidates.class);
		c.clickCandidatesSubTab();

		String expectedFirstname = "Jon Romeo";
		String expectedMiddlename = "Igoy";
		String expectedLastname = "Robillos";
		String expectedFullname = "Jon Romeo Igoy Robillos";
		String expectedEmail = "rom.robillos@yahoo.com";
		String expectedContactnumber = "0966";
		String expectedVacancy = "Senior QA Lead";

		c.addCandidate(expectedFirstname, expectedMiddlename, expectedLastname, expectedEmail, expectedContactnumber,
				expectedVacancy);

		sb.waitToastNotif();

		Assert.assertTrue(c.isProfileEqualToAddedOrRow1Name(expectedFullname));

	}

	@Test(dataProvider = "validCredential", description = " Verify Add Candidate to Cancel")
	public void TC0_Recruitment_Candidates_addCandidate_ToCancel(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Candidates c = page.getInstance(Candidates.class);
		c.clickCandidatesSubTab();

		c.cancelAddCandidate();

		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates";
		String actualUrl = c.getCurrentPageUrl();

		Assert.assertEquals(expectedUrl, actualUrl);
		Assert.assertTrue(c.getCandidateName().isDisplayed());

	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment_Candidates_Table if Checkbox Header Working")
	public void TC0_Recruitment_Candidates_Table_isCheckboxHeaderWorking(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Candidates c = page.getInstance(Candidates.class);
		c.clickCandidatesSubTab();

		c.scrollBy(0, 500);

		c.clickHeaderCheckbox();

		boolean isAllChecked = c.verifySelectAllCheckbox();
		Assert.assertTrue(isAllChecked);

	}

	@Test(dataProvider = "validCredential", description = "Verify Recruitment Candidates Table Action - View Profile Button for First Row")
	public void TC0_Recruitment_Candidates_Action_isViewProfileBtnWorking(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Candidates c = page.getInstance(Candidates.class);
		c.clickCandidatesSubTab();

		c.scrollBy(0, 500);

		String row1Name = c.getRow1Name();
		System.out.println("Row1 Candidate name: " + row1Name);
		c.viewProfileRow1();

		boolean isAtCorrectProfile = c.isProfileEqualToAddedOrRow1Name(row1Name);

		Assert.assertTrue(isAtCorrectProfile);
	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment Candidates Table Action - Delete Profile Button for First Row")
	public void TC0_Recruitment_Candidates_Action_isDeleteProfileBtnWorking(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Candidates c = page.getInstance(Candidates.class);
		c.clickCandidatesSubTab();

		c.scrollBy(0, 500);

		String row1NameAndDate = c.getRow1NameAndDate();
		System.out.println("Candidate to be deleted: " + row1NameAndDate);

		c.deleteProfileRow1();

		sb.waitToastNotif();

		boolean isDeleted = c.isRow1CandidateDeleted(row1NameAndDate);

		Assert.assertTrue(isDeleted, "Candidate record was not deleted successfully!");

	}

}
