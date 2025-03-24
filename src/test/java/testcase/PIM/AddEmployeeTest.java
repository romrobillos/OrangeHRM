package testcase.PIM;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.SideBar;
import page.PIM.AddEmployee;
import testcase.BaseTest;

public class AddEmployeeTest extends BaseTest {
	@Test(dataProvider = "validCredential", description = " Verify PIM EmployeeList if Employee is added ")
	public void TC0_PIM_EmployeeList_toAddEmployee(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		AddEmployee ae = page.getInstance(AddEmployee.class);
		ae.clickAddEmployee();
		
		String expectedFirstName = "Jon";
		String expectedMiddleName = "Igoy";
		String expectedLastName = "Robillos";
		String expectedEmployeeID = "0966";
		
		ae.toAddEmployee(expectedFirstName, expectedMiddleName, expectedLastName, expectedEmployeeID);
		sb.waitToastNotif();
		boolean isItAdded = ae.isItAdded(expectedFirstName, expectedLastName);
		Assert.assertTrue(isItAdded);
		
	}
}
