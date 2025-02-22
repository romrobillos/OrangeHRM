package testcase.MyInfo;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();

		try {

			String expectedFirstname = "Jon Romeo";
			String expectedMiddlename = "Igoy";
			String expectedLastname = "Robillos";
			String expectedEmployeeId = "0398";
			String expectedOtherId = "0303";
			String expectedDLN = "123456";
			String expectedLicenseExpiry = "2025-03-10";
			String expectedNationality = "Filipino";
			String expectedMaritalStatus = "Single";
			String expectedDOB = "1994-03-03";
			String expectedGender = "Male";

			wait.until(ExpectedConditions.visibilityOf(pd.getFirstname()));
			js.executeScript("arguments[0].value = arguments[1];", pd.getFirstname(), expectedFirstname);

			wait.until(ExpectedConditions.visibilityOf(pd.getMiddlename()));
			js.executeScript("arguments[0].value = arguments[1];", pd.getMiddlename(), expectedMiddlename);

			wait.until(ExpectedConditions.visibilityOf(pd.getLastname()));
			js.executeScript("arguments[0].value = arguments[1];", pd.getLastname(), expectedLastname);

			wait.until(ExpectedConditions.visibilityOf(pd.getEmployeeId()));
			js.executeScript("arguments[0].value = arguments[1];", pd.getEmployeeId(), expectedEmployeeId);

			wait.until(ExpectedConditions.visibilityOf(pd.getOtherId()));
			js.executeScript("arguments[0].value = arguments[1];", pd.getOtherId(), expectedOtherId);

			wait.until(ExpectedConditions.visibilityOf(pd.getDLN()));
			js.executeScript("arguments[0].value = arguments[1];", pd.getDLN(), expectedDLN);

			wait.until(ExpectedConditions.visibilityOf(pd.getLicenseExpiryDate()));
			js.executeScript("arguments[0].value = arguments[1];", pd.getLicenseExpiryDate(), expectedLicenseExpiry);

			wait.until(ExpectedConditions.elementToBeClickable(pd.getNationality()));
			pd.selectNationality(expectedNationality);

			wait.until(ExpectedConditions.elementToBeClickable(pd.getMaritalStatus()));
			pd.selectMaritalStatus(expectedMaritalStatus);

			wait.until(ExpectedConditions.elementToBeClickable(pd.getDOB()));
			js.executeScript("arguments[0].value = arguments[1];", pd.getDOB(), expectedDOB);

			pd.selectGender(expectedGender);

			pd.getSaveBtn().click();
			wait.until(ExpectedConditions.visibilityOf(sb.getToastNotif()));
			wait.until(ExpectedConditions.visibilityOf(pd.getFirstname()));

			// assert

			String actualFirstname = pd.getFirstnameValue();
			String actualMiddlename = pd.getMiddlenameValue();
			String actualLastname = pd.getLastnameValue();
			String actualEmployeeId = pd.getEmployeeIdValue();
			String actualOtherId = pd.getOtherIdValue();
			String actualDLN = pd.getDLNValue();
			String actualLicenseExpiry = pd.getLicenseExpiryDateValue();
			String actualNationality = pd.getNationalityTxt();
			String actualMaritalStatus = pd.getMaritalStatusTxt();
			String actualDOB = pd.getDOBValue();
			String actualGender = "Male";

			softAssert.assertEquals(expectedFirstname, actualFirstname);
			softAssert.assertEquals(expectedMiddlename, actualMiddlename);
			softAssert.assertEquals(expectedLastname, actualLastname);
			softAssert.assertEquals(expectedEmployeeId, actualEmployeeId);
			softAssert.assertEquals(expectedOtherId, actualOtherId);
			softAssert.assertEquals(expectedDLN, actualDLN);
			softAssert.assertEquals(expectedLicenseExpiry, actualLicenseExpiry);
			softAssert.assertEquals(expectedNationality, actualNationality);
			softAssert.assertEquals(expectedMaritalStatus, actualMaritalStatus);
			softAssert.assertTrue(pd.isGenderSelected(actualGender));
			softAssert.assertEquals(expectedDOB, actualDOB);

		} catch (Exception e) {
			e.printStackTrace();
			softAssert.fail("Error" + e.getMessage());
		}
		softAssert.assertAll();
	}

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_PersonalDetails correct information")
	public void TC020_MyInfo_PersonalDetails_isCorrect(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		new SideBar(driver).getMyInfo().click();
		PersonalDetails pd = new PersonalDetails(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(pd.getFirstname())).click();

		String expectedFirstname = "FirstNameTest";
		String expectedMiddlename = "MiddleNameField";
		String expectedLastname = "LastNameField";
		String expectedEmployeeId = "Employee";
		String expectedOtherId = "OtherIdTest";
		String expectedDLN = "DriversLicenseTest";
		String expectedLicenseExpiry = "2030-12-12";
		String expectedNationality = "Brazilian";
		String expectedMaritalStatus = "Married";
		String expectedDOB = "2000-12-12";
		

		String actualFirstname = pd.getFirstnameValue();
		String actualMiddlename = pd.getMiddlenameValue();
		String actualLastname = pd.getLastnameValue();
		String actualEmployeeId = pd.getEmployeeIdValue();
		String actualOtherId = pd.getOtherIdValue();
		String actualDLN = pd.getDLNValue();
		String actualLicenseExpiry = pd.getLicenseExpiryDateValue();
		String actualNationality = pd.getNationalityTxt();
		String actualMaritalStatus = pd.getMaritalStatusTxt();
		String actualDOB = pd.getDOBValue();
		String actualGender = "Male";

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(expectedFirstname, actualFirstname);
		softAssert.assertEquals(expectedMiddlename, actualMiddlename);
		softAssert.assertEquals(expectedLastname, actualLastname);
		softAssert.assertEquals(expectedEmployeeId, actualEmployeeId);
		softAssert.assertEquals(expectedOtherId, actualOtherId);
		softAssert.assertEquals(expectedDLN, actualDLN);
		softAssert.assertEquals(expectedLicenseExpiry, actualLicenseExpiry);
		softAssert.assertEquals(expectedNationality, actualNationality);
		softAssert.assertEquals(expectedMaritalStatus, actualMaritalStatus);
		softAssert.assertEquals(expectedDOB, actualDOB);
		softAssert.assertTrue(pd.isGenderSelected(actualGender));
		softAssert.assertAll();

	}

}
