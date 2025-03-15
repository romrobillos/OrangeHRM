package testcase.PIM;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.SideBar;
import page.PIM.EmployeeList;
import testcase.BaseTest;

public class EmployeeListTest extends BaseTest{
	@Test(dataProvider = "validCredential", description = "Verify PIM EmployeeList if EmployeeName is Filtered from Auto Suggest")
	public void TC0_PIM_EmployeeList_isEmployeeNameFiltered(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		EmployeeList e = page.getInstance(EmployeeList.class);
		e.clickEmployeeList();
		
		String expectedEmployeeName = "Christopher";
		e.searchEmployeeNameThruAutoSuggest(expectedEmployeeName);

		e.scrollBy(0, 500);

		boolean isItFiltered = e.isEmployeeNameFiltered(expectedEmployeeName);
		Assert.assertTrue(isItFiltered);
		
		
	}
}
