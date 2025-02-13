package testcase.MyInfo;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.SideBar;
import page.MyInfo.PersonalDetails;
import testcase.BaseTest;

public class PersonalDetailsTest extends BaseTest {

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_PersonalDetails")
	public void TC019_MyInfo_PersonalDetails(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		new SideBar(driver).getMyInfo().click();
		PersonalDetails pd = new PersonalDetails(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(pd.getFirstname())).click();
		String expectedFirstname = "FirstNameTest";
		String expectedMiddlename = "Ramesh";
		String expectedLastname = "LastNameTest";
		String expectedEmployeeId = "Employee";
		String expectedOtherId = "OtherIdTest";	
		String expectedDLN = "DriverLicenseTest";
		String expectedLicenseExpiry = "2025-03-10";
		String expectedNationality = "American";
		String expectedMaritalStatus = "Married";
		
		String actualFirstname = pd.getFirstnameValue();
		Assert.assertEquals(expectedFirstname, actualFirstname);
		
		String actualMiddlename = pd.getMiddlenameValue();
		Assert.assertEquals(expectedMiddlename, actualMiddlename);
		
		String actualLastname = pd.getLastnameValue();
		Assert.assertEquals(expectedLastname, actualLastname);
		
		String actualEmployeeId = pd.getEmployeeIdValue();
		Assert.assertEquals(expectedEmployeeId, actualEmployeeId);
		
		String actualOtherId = pd.getOtherIdValue();
		Assert.assertEquals(expectedOtherId, actualOtherId);
	
		String actualDLN = pd.getDLNValue();
		Assert.assertEquals(expectedDLN, actualDLN);
		
		String actualLicenseExpiry = pd.getLicenseExpiryDateValue();
		Assert.assertEquals(expectedLicenseExpiry, actualLicenseExpiry);
		
		String actualNationality = pd.getNationalityTxt();
		Assert.assertEquals(expectedNationality, actualNationality);
		
		String actualMaritalStatus = pd.getMaritalStatusTxt();
		Assert.assertEquals(expectedMaritalStatus, actualMaritalStatus);
		
	}
}
