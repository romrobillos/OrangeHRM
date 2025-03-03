package testcase.MyInfo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import page.LoginPage;
import page.SideBar;
import page.MyInfo.PersonalDetails;
import testcase.BaseTest;

public class PersonalDetailsTest extends BaseTest {

	private Map<String, String> getExpectedPersonalDetails() {
		Map<String, String> details = new HashMap<>();
		details.put("Firstname", "Jon Romeo");
		details.put("Middlename", "Igoy");
		details.put("Lastname", "Robillos");
		details.put("EmployeeId", "0398");
		details.put("OtherId", "0303");
		details.put("DLN", "123456");
		details.put("LicenseExpiry", "2025-03-10");
		details.put("Nationality", "Filipino");
		details.put("MaritalStatus", "Single");
		details.put("DOB", "1994-03-03");
		details.put("Gender", "Male");
		return details;
	}

	@Test(dataProvider = "validCredential", description = "Check if MyInfo Personal Details fields can be modified.")
	public void TC019_MyInfo_PersonalDetails_Edit(String username, String password) throws InterruptedException {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickDashboard();
		sb.clickMyInfo();
		PersonalDetails pd = page.getInstance(PersonalDetails.class);
		SoftAssert softAssert = new SoftAssert();

		Map<String, String> expectedDetails = getExpectedPersonalDetails();
		pd.fillOutPD(expectedDetails.get("Firstname"), expectedDetails.get("Middlename"),
				expectedDetails.get("Lastname"), expectedDetails.get("EmployeeId"), expectedDetails.get("OtherId"),
				expectedDetails.get("DLN"), expectedDetails.get("LicenseExpiry"), expectedDetails.get("Nationality"),
				expectedDetails.get("MaritalStatus"), expectedDetails.get("DOB"), expectedDetails.get("Gender"));

		sb.waitToastNotif();

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

		// assert
		softAssert.assertEquals(actualFirstname, expectedDetails.get("Firstname"), "First name mismatch!");
		softAssert.assertEquals(actualMiddlename, expectedDetails.get("Middlename"), "Middle name mismatch!");
		softAssert.assertEquals(actualLastname, expectedDetails.get("Lastname"), "Last name mismatch!");
		softAssert.assertEquals(actualEmployeeId, expectedDetails.get("EmployeeId"), "Employee ID mismatch!");
		softAssert.assertEquals(actualOtherId, expectedDetails.get("OtherId"), "Other ID mismatch!");
		softAssert.assertEquals(actualDLN, expectedDetails.get("DLN"), "DLN mismatch!");
		softAssert.assertEquals(actualLicenseExpiry, expectedDetails.get("LicenseExpiry"), "License Expiry mismatch!");
		softAssert.assertEquals(actualNationality, expectedDetails.get("Nationality"), "Nationality mismatch!");
		softAssert.assertEquals(actualMaritalStatus, expectedDetails.get("MaritalStatus"), "Marital status mismatch!");
		softAssert.assertEquals(actualDOB, expectedDetails.get("DOB"), "Date of Birth mismatch!");
		softAssert.assertTrue(pd.isGenderSelected(expectedDetails.get("Gender")), "Gender selection mismatch!");
		softAssert.assertAll();
	}

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_PersonalDetails correct information")
	public void TC020_MyInfo_PersonalDetails_isCorrect(String username, String password) throws InterruptedException {

		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickDashboard();
		sb.clickMyInfo();
		PersonalDetails pd = page.getInstance(PersonalDetails.class);

		String expectedFirstname = "sam";
		String expectedMiddlename = "MiddleNameTest";
		String expectedLastname = "davidtony";
		String expectedEmployeeId = "EmpTest";
		String expectedOtherId = "EmployeeOtherIDTest";
		String expectedDLN = "DriversNumberTest";
		String expectedLicenseExpiry = "2025-01-03";
		String expectedNationality = "Brazilian";
		String expectedMaritalStatus = "Single";
		String expectedDOB = "2003-07-23";
		String expectedGender = "Male";

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

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualFirstname, expectedFirstname, "First name mismatch!");
		softAssert.assertEquals(actualMiddlename, expectedMiddlename, "Middle name mismatch!");
		softAssert.assertEquals(actualLastname, expectedLastname, "Last name mismatch!");
		softAssert.assertEquals(actualEmployeeId, expectedEmployeeId, "Employee ID mismatch!");
		softAssert.assertEquals(actualOtherId, expectedOtherId, "Other ID mismatch!");
		softAssert.assertEquals(actualDLN, expectedDLN, "DLN mismatch!");
		softAssert.assertEquals(actualLicenseExpiry, expectedLicenseExpiry, "License Expiry mismatch!");
		softAssert.assertEquals(actualNationality, expectedNationality, "Nationality mismatch!");
		softAssert.assertEquals(actualMaritalStatus, expectedMaritalStatus, "Marital status mismatch!");
		softAssert.assertEquals(actualDOB, expectedDOB, "Date of Birth mismatch!");
		softAssert.assertTrue(pd.isGenderSelected(expectedGender), "Gender selection mismatch!");

		softAssert.assertAll();

	}

}
