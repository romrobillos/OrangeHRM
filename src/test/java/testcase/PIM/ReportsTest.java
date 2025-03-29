package testcase.PIM;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.SideBar;
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
}
