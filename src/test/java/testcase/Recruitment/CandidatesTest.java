package testcase.Recruitment;

import java.time.Duration;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.SideBar;
import page.Recruitment.Candidates;
import testcase.BaseTest;

public class CandidatesTest extends BaseTest {

	@Test(dataProvider = "validCredential", description = " Verify Recruitment Candidates if Vacancy are filtered")
	public void TC0_Recruitment_Candidates_isVacancyFiltered(String username, String password) {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getRecruitment().click();
		Candidates c = new Candidates(driver);
		c.getCandidates().click();

		String expectedVacancy = "Payroll Administrator";
		c.selectVacancy(expectedVacancy);
		c.clickSearch();

		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 500).perform();

		boolean isItFiltered = c.isVacancyFiltered(expectedVacancy);
		Assert.assertTrue(isItFiltered);

	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment Candidates if CandidatesName are filtered")
	public void TC0_Recruitment_Candidates_isCandidateFiltered(String username, String password) {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getRecruitment().click();
		Candidates c = new Candidates(driver);
		c.getCandidates().click();

		String expectedCandidateName = "Gautham Raj R";
		c.getCandidateName().sendKeys(expectedCandidateName.split(" ")[0]);
		c.selectNameFromSuggestion1(expectedCandidateName);
		c.clickSearch();

		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 500).perform();

		boolean isItFiltered = c.isCandidateNameFiltered(expectedCandidateName);
		Assert.assertTrue(isItFiltered);

	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment Candidates if Hiring Manager are filtered")
	public void TC0_Recruitment_Candidates_isHiringManagerFiltered(String username, String password) {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getRecruitment().click();
		Candidates c = new Candidates(driver);
		c.getCandidates().click();

		String expectedManager = "Test 54";
		c.selectHiringManager(expectedManager);
		c.clickSearch();

		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 500).perform();

		boolean isItFiltered = c.isHiringManagerFiltered(expectedManager);
		Assert.assertTrue(isItFiltered);

	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment_Candidates if Date is WithinRange")
	public void TC0_Recruitment_Candidates_isDateFiltered(String username, String password)
			throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getRecruitment().click();
		Candidates c = new Candidates(driver);
		c.getCandidates().click();

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
		sb.getRecruitment().click();
		Candidates c = new Candidates(driver);
		c.getCandidates().click();

		String expectedStatus = "Shortlisted";
		c.selectStatus(expectedStatus);
		c.clickSearch();

		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 500).perform();

		boolean isItFiltered = c.isStatusFiltered(expectedStatus);
		Assert.assertTrue(isItFiltered);

	}
	
	@Test(dataProvider = "validCredential", description = " Verify Recruitment_Candidates_Table if Checkbox Header Working")
	public void TC0_Recruitment_Candidates_Table_isCheckboxHeaderWorking(String username, String password) {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getRecruitment().click();
		Candidates c = new Candidates(driver);
		c.getCandidates().click();

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
		sb.getRecruitment().click();
		Candidates c = new Candidates(driver);
		c.getCandidates().click();

		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 500).perform();

		String row1Name = c.getRow1Name();
		System.out.println("Row1 Candidate name: " + row1Name);
		c.clickViewProfile();

		boolean isAtCorrectProfile = c.isProfileEqualToRow1Name(row1Name);

		Assert.assertTrue(isAtCorrectProfile);
	}

	@Test(dataProvider = "validCredential", description = " Verify Recruitment_Candidates_Table_Action_DeleteProfileBtn for Row1")
	public void TC0_Recruitment_Candidates_Action_isDeleteProfileBtnWorking(String username, String password) {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getRecruitment().click();
		Candidates c = new Candidates(driver);
		c.getCandidates().click();

		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 500).perform();

		String row1NameAndDate = c.getRow1NameAndDate();
		System.out.println("Candidate to be deleted: " + row1NameAndDate);

		c.clickDeleteProfile();
		c.clickPopupDeleteBtn();

		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(sb.getToastNotif()));

		boolean isDeleted = c.isRow1CandidateDeleted(row1NameAndDate);

		Assert.assertTrue(isDeleted, "Candidate record was not deleted successfully!");

	}
	
	

}
