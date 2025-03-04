package testcase.Recruitment;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		c.selectVacancy(expectedVacancy);

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
		c.selectHiringManager(expectedManager);

		c.scrollBy(0, 500);

		boolean isItFiltered = c.isHiringManagerFiltered(expectedManager);
		Assert.assertTrue(isItFiltered);

	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment_Candidates if Date is WithinRange")
	public void TC0_Recruitment_Candidates_isDateFiltered(String username, String password)
			throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.clickRecruitment().click();
		Candidates c = new Candidates(driver);
		c.clickCandidatesSubTab().click();

		String expectedDateFrom = "2024-02-02";
		String expectedDateTo = "2024-06-02";

		c.getdateFrom().sendKeys(expectedDateFrom);
		c.getdateTo().sendKeys(expectedDateTo);
		c.clickSearch();

		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 500).perform();

		Thread.sleep(1000);
		boolean isWithinRange = c.isDateOfApplicationWithinRange();
		Assert.assertTrue(isWithinRange, "Some dates are out of the specified range!");

	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment Candidates if Status are filtered")
	public void TC0_Recruitment_Candidates_isStatusFiltered(String username, String password) {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.clickRecruitment().click();
		Candidates c = new Candidates(driver);
		c.clickCandidatesSubTab().click();

		String expectedStatus = "Shortlisted";
		c.selectStatus(expectedStatus);
		c.clickSearch();

		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 500).perform();

		boolean isItFiltered = c.isStatusFiltered(expectedStatus);
		Assert.assertTrue(isItFiltered);

	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment_Candidates Reset Button")
	public void TC0_Recruitment_Candidates_isReset(String username, String password) {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.clickRecruitment().click();
		Candidates c = new Candidates(driver);
		c.clickCandidatesSubTab().click();

		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(c.getCandidateName()));

		String garbageValue = "Senior QA Lead";

		c.selectVacancy(garbageValue);

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
		softAssert.assertEquals(expectedVacancy, actualVacancy);
		softAssert.assertEquals(expectedHiringManager, actualHiringManager);
		softAssert.assertEquals(expectedStatus, actualStatus);
		softAssert.assertEquals(expectedCandidateName, actualCandidateName);
		softAssert.assertEquals(expectedDateFrom, actualDateFrom);
		softAssert.assertEquals(expectedDateTo, actualDateTo);
		softAssert.assertAll();

	}

	@Test(dataProvider = "validCredential", description = " Verify Added Candidate")
	public void TC0_Recruitment_Candidates_addCandidate(String username, String password) {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.clickRecruitment().click();
		Candidates c = new Candidates(driver);
		c.clickCandidatesSubTab().click();
		c.clickAdd();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		String expectedFirstname = "Jon Romeo";
		String expectedMiddlename = "Igoy";
		String expectedLastname = "Robillos";
		String expectedFullname = "Jon Romeo Igoy Robillos";
		String expectedEmail = "rom.robillos@yahoo.com";
		String expectedContactnumber = "0966";
		String expectedVacancy = "Senior QA Lead";

		wait.until(ExpectedConditions.visibilityOf(c.getFirstname())).sendKeys(expectedFirstname);
		wait.until(ExpectedConditions.visibilityOf(c.getMiddlename())).sendKeys(expectedMiddlename);
		wait.until(ExpectedConditions.visibilityOf(c.getLastname())).sendKeys(expectedLastname);
		wait.until(ExpectedConditions.visibilityOf(c.getEmail())).sendKeys(expectedEmail);
		wait.until(ExpectedConditions.visibilityOf(c.getContactnumber())).sendKeys(expectedContactnumber);
		wait.until(ExpectedConditions.elementToBeClickable(c.getAdd_Vacancy()));
		c.selectAdd_Vacancy(expectedVacancy);

		c.clickSave();
		wait.until(ExpectedConditions.visibilityOf(sb.waitToastNotif()));

		Assert.assertTrue(c.isProfileEqualToAddedOrRow1Name(expectedFullname));

	}

	@Test(dataProvider = "validCredential", description = " Verify Add Candidate to Cancel")
	public void TC0_Recruitment_Candidates_addCandidate_ToCancel(String username, String password)
			throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.clickRecruitment().click();
		Candidates c = new Candidates(driver);
		c.clickCandidatesSubTab().click();
		c.clickAdd();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(c.getFirstname()));
		c.clickCancel();

		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates";
		String actualUrl = driver.getCurrentUrl();

		Assert.assertEquals(expectedUrl, actualUrl);
		Assert.assertTrue(c.getCandidateName().isDisplayed());

	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment_Candidates_Table if Checkbox Header Working")
	public void TC0_Recruitment_Candidates_Table_isCheckboxHeaderWorking(String username, String password) {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.clickRecruitment().click();
		Candidates c = new Candidates(driver);
		c.clickCandidatesSubTab().click();

		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 500).perform();

		c.clickHeaderCheckbox();

		boolean isAllChecked = c.verifySelectAllCheckbox();
		Assert.assertTrue(isAllChecked);

	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment_Candidates_Table_Action_ViewProfileBtn for Row1")
	public void TC0_Recruitment_Candidates_Action_isViewProfileBtnWorking(String username, String password) {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.clickRecruitment().click();
		Candidates c = new Candidates(driver);
		c.clickCandidatesSubTab().click();

		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 500).perform();

		String row1Name = c.getRow1Name();
		System.out.println("Row1 Candidate name: " + row1Name);
		c.clickViewProfile();

		boolean isAtCorrectProfile = c.isProfileEqualToAddedOrRow1Name(row1Name);

		Assert.assertTrue(isAtCorrectProfile);
	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment_Candidates_Table_Action_DeleteProfileBtn for Row1")
	public void TC0_Recruitment_Candidates_Action_isDeleteProfileBtnWorking(String username, String password) {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.clickRecruitment().click();
		Candidates c = new Candidates(driver);
		c.clickCandidatesSubTab().click();

		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 500).perform();

		String row1NameAndDate = c.getRow1NameAndDate();
		System.out.println("Candidate to be deleted: " + row1NameAndDate);

		c.clickDeleteProfile();
		c.clickPopupDeleteBtn();

		sb.waitToastNotif();

		boolean isDeleted = c.isRow1CandidateDeleted(row1NameAndDate);

		Assert.assertTrue(isDeleted, "Candidate record was not deleted successfully!");

	}

}
