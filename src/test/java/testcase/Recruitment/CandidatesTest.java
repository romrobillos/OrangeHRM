package testcase.Recruitment;

import org.openqa.selenium.JavascriptExecutor;
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

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

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
		c.selectName(expectedCandidateName);
		c.clickSearch();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

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

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

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

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
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

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		boolean isItFiltered = c.isStatusFiltered(expectedStatus);
		Assert.assertTrue(isItFiltered);

	}
	
	@Test(dataProvider = "validCredential", description = " Verify Recruitment_Candidates_Table_Action_ViewProfileBtn")
	public void TC0_Recruitment_Candidates_Action_isViewProfileBtnWorking(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getRecruitment().click();
		Candidates c = new Candidates(driver);
		c.getCandidates().click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		String row1Name = c.getRow1Name();
		c.clickViewProfile();
		
		boolean isAtCorrectProfile = c.isProfileEqualToRowName(row1Name);
		Thread.sleep(4000);
		Assert.assertTrue(isAtCorrectProfile);
	}

}
