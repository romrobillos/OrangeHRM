package testcase.PIM;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.SideBar;
import page.PIM.AddEmployee;
import testcase.BaseTest;

public class AddEmployeeTest extends BaseTest {
	@Test(dataProvider = "validCredential", description = " Verify PIM EmployeeList if Employee is added along with Login Details ")
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
		
		String expectedUsername = "romrobillos";
		String expectedPassword = "robillos123";
		String expectedConfirmPassword = "robillos123";
		
		ae.toAddEmployee(expectedFirstName, expectedMiddleName, expectedLastName, expectedEmployeeID);
		
		ae.clickCreateLoginDetailsCheckbox();
		
		ae.toCreateLoginDetails(expectedUsername, expectedPassword, expectedConfirmPassword);
		ae.clickSaveBtn();
		
		sb.waitToastNotif();
		
		boolean isItAdded = ae.isEmployeeAdded(expectedFirstName, expectedLastName);
		Assert.assertTrue(isItAdded);
		
	}
	
	@Test(dataProvider = "validCredential", description = " Verify PIM EmployeeList if Create Login Details Checkbox is working")
	public void TC0_PIM_EmployeeList_verifyCreateLoginDetailsChcekbox(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		AddEmployee ae = page.getInstance(AddEmployee.class);
		ae.clickAddEmployee();
		ae.clickCreateLoginDetailsCheckbox();
		
		boolean isItDisplayed = ae.isCreateLoginUsernameDisplayed();
		Assert.assertTrue(isItDisplayed);
		
		ae.clickCreateLoginDetailsCheckbox();
		boolean isItNotDisplayed = ae.isCreateLoginUsernameDisplayed();
		Assert.assertFalse(isItNotDisplayed);
	}
	
	@Test(dataProvider = "validCredential", description = " Verify PIM EmployeeList_AddEmployee if can upload photo")
	public void TC0_PIM_EmployeeList_verifyAddPhoto(String username, String password) throws InterruptedException, AWTException {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickPim();
		AddEmployee ae = page.getInstance(AddEmployee.class);
		ae.clickAddEmployee();
		
		String expectedPhoto = "C:\\Users\\admin\\eclipse-workspace\\OrangeHRM\\OrangeHRM\\src\\test\\resources\\employee.jpg";
		
		ae.clickUploadPhotoBtn( );
		ae.toUpload(expectedPhoto);
		
		boolean isItUploaded = ae.isPhotoUploaded();
		Assert.assertTrue(isItUploaded);
		
		
	}
}
