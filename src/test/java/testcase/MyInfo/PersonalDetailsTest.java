package testcase.MyInfo;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.SideBar;
import page.MyInfo.PersonalDetails;
import testcase.BaseTest;

public class PersonalDetailsTest extends BaseTest {
	@Test(dataProvider = "validCredential", description = "Verify MyInfo_PersonalDetails if Editable")
	public void TC019_MyInfo_PersonalDetails_Edit(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getMyInfo().click();
		PersonalDetails pd = new PersonalDetails(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			wait.until(ExpectedConditions.visibilityOf(pd.getFirstname()));
			wait.until(ExpectedConditions.visibilityOf(pd.getMiddlename()));
			wait.until(ExpectedConditions.visibilityOf(pd.getLastname()));
			wait.until(ExpectedConditions.visibilityOf(pd.getEmployeeId()));
			wait.until(ExpectedConditions.visibilityOf(pd.getOtherId()));
			wait.until(ExpectedConditions.visibilityOf(pd.getDLN()));
			wait.until(ExpectedConditions.visibilityOf(pd.getLicenseExpiryDate()));
			String expectedFirstname = "Jon Romeo";
			js.executeScript("arguments[0].value = '" + expectedFirstname + "';", pd.getFirstname());

			String expectedMiddlename = "Igoy";
			js.executeScript("arguments[0].value = '" + expectedMiddlename + "';", pd.getMiddlename());

			String expectedLastname = "Robillos";
			js.executeScript("arguments[0].value = '" + expectedLastname + "';", pd.getLastname());

			String expectedEmployeeId = "0398";
			js.executeScript("arguments[0].value = '" + expectedEmployeeId + "';", pd.getEmployeeId());

			String expectedOtherId = "0303";
			js.executeScript("arguments[0].value = '" + expectedOtherId + "';", pd.getOtherId());

			String expectedDLN = "123456";
			js.executeScript("arguments[0].value = '" + expectedDLN + "';", pd.getDLN());

			String expectedLicenseExpiry = "2025-03-10";
			js.executeScript("arguments[0].value = '" + expectedLicenseExpiry + "';", pd.getLicenseExpiryDate());

			String expectedNationality = "Filipino";
			wait.until(ExpectedConditions.elementToBeClickable(pd.getNationality()));
			pd.selectNationality(expectedNationality);

			String expectedMaritalStatus = "Single";
			wait.until(ExpectedConditions.elementToBeClickable(pd.getMaritalStatus()));
			pd.selectMaritalStatus(expectedMaritalStatus);

			String expectedDOB = "1994-03-03";
			js.executeScript("arguments[0].value = '" + expectedDOB + "';", pd.getDOB());

			String expectedGender = "Male";
			pd.selectGender(expectedGender);

			pd.getSaveBtn().click();

			wait.until(ExpectedConditions.visibilityOf(sb.getToastNotif()));

			// assert

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

			String actualDOB = pd.getDOBValue();
			Assert.assertEquals(expectedDOB, actualDOB);

			Assert.assertTrue(pd.isGenderSelected("Female"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_PersonalDetails correct information")
	public void TC020_MyInfo_PersonalDetails_isCorrect(String username, String password) throws InterruptedException {
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
		String expectedDOB = "1994-03-03";

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

		String actualDOB = pd.getDOBValue();
		Assert.assertEquals(expectedDOB, actualDOB);

	}

}
