package testcase.Recruitment;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.SideBar;
import page.Recruitment.Vacancies;
import testcase.BaseTest;

public class VacanciesTest extends BaseTest {

	
	@Test(dataProvider = "validCredential", description = " Verify Recruitment Vacancies if JobTitle are filtered")
	public void TC0_Recruitment_Candidates_isJobTitleFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Vacancies v = page.getInstance(Vacancies.class);
		v.clickVacanciesSubtab();

		String expectedStatus = "Account Assistant";

		v.searchJobTitle(expectedStatus);

		v.scrollBy(0, 500);

		boolean isItFiltered = v.isJobTitleFiltered(expectedStatus);
		Assert.assertTrue(isItFiltered);

	}
	
	@Test(dataProvider = "validCredential", description = " Verify Recruitment Vacancies if Vacancy are filtered")
	public void TC0_Recruitment_Candidates_isVacancyFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Vacancies v = page.getInstance(Vacancies.class);
		v.clickVacanciesSubtab();

		String expectedStatus = "Senior QA Lead";

		v.searchVacancy(expectedStatus);

		v.scrollBy(0, 500);

		boolean isItFiltered = v.isVacancyFiltered(expectedStatus);
		Assert.assertTrue(isItFiltered);

	}
	
	@Test(dataProvider = "validCredential", description = " Verify Recruitment Vacancies if Hiring Manager are filtered")
	public void TC0_Recruitment_Candidates_isHiringManagerFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Vacancies v = page.getInstance(Vacancies.class);
		v.clickVacanciesSubtab();

		String expectedStatus = "John user";

		v.searchHiringManager(expectedStatus);

		v.scrollBy(0, 500);

		boolean isItFiltered = v.isHiringManagerFiltered(expectedStatus);
		Assert.assertTrue(isItFiltered);

	}
	
	@Test(dataProvider = "validCredential", description = " Verify Recruitment Vacancies if Status are filtered")
	public void TC0_Recruitment_Candidates_isStatusFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickRecruitment();
		Vacancies v = page.getInstance(Vacancies.class);
		v.clickVacanciesSubtab();

		String expectedStatus = "Active";

		v.searchStatus(expectedStatus);

		v.scrollBy(0, 500);

		boolean isItFiltered = v.isStatusFiltered(expectedStatus);
		Assert.assertTrue(isItFiltered);

	}
}
