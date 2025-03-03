package testcase.MyInfo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import page.LoginPage;
import page.SideBar;
import page.MyInfo.ContactDetails;
import testcase.BaseTest;

public class ContactDetailsTest extends BaseTest {
	private Map<String, String> getExpectedContactDetails() {
		Map<String, String> details = new HashMap<>();
		details.put("street1", "2050B");
		details.put("street2", "Sampaloc");
		details.put("city", "Manila");
		details.put("state", "NCR");
		details.put("zip", "1008");
		details.put("country", "Philippines");
		details.put("homenumber", "73312312");
		details.put("mobilenumber", "096656");
		details.put("worknumber", "096656");
		details.put("workemail", "rom.robillos@yahoo.com");
		details.put("otheremail", "rom.robillos@yahoo.com");
		return details;
	}

	@Test(dataProvider = "validCredential", description = "Check if MyInfo Contact Details fields can be modified.")
	public void TC019_MyInfo_PersonalDetails_Edit(String username, String password) throws InterruptedException {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickDashboard();
		sb.clickMyInfo();
		ContactDetails cd = page.getInstance(ContactDetails.class);
		cd.clickContactDetails();

		Map<String, String> expectedDetails = getExpectedContactDetails();
		cd.fillOutCD(expectedDetails.get("street1"), expectedDetails.get("street2"), expectedDetails.get("city"),
				expectedDetails.get("state"), expectedDetails.get("zip"), expectedDetails.get("country"),
				expectedDetails.get("homenumber"), expectedDetails.get("mobilenumber"),
				expectedDetails.get("worknumber"), expectedDetails.get("workemail"), expectedDetails.get("otheremail"));

		sb.waitToastNotif();

		// assert
		String actualStreet1 = cd.getStreet1Value();
		String actualStreet2 = cd.getStreet2Value();
		String actualCity = cd.getCityValue();
		String actualState = cd.getStateValue();
		String actualZip = cd.getZipValue();
		String actualCountry = cd.getCountryTxt();
		String actualHomenumber = cd.getHomeNumberValue();
		String actualMobilenumber = cd.getMobileNumberValue();
		String actualWorknumber = cd.getWorkNumberValue();
		String actualWorkemail = cd.getWorkEmailValue();
		String actualOtheremail = cd.getOtherEmailValue();

		SoftAssert softAssert = new SoftAssert();

		softAssert.assertEquals(actualStreet1, expectedDetails.get("street1"), "Street1 mismatch!");
		softAssert.assertEquals(actualStreet2, expectedDetails.get("street2"), "Street2 mismatch!");
		softAssert.assertEquals(actualCity, expectedDetails.get("city"), "City mismatch!");
		softAssert.assertEquals(actualState, expectedDetails.get("state"), "State mismatch!");
		softAssert.assertEquals(actualZip, expectedDetails.get("zip"), "Zip Code mismatch!");
		softAssert.assertEquals(actualCountry, expectedDetails.get("country"), "Country mismatch!");
		softAssert.assertEquals(actualHomenumber, expectedDetails.get("homenumber"), "Home number mismatch!");
		softAssert.assertEquals(actualMobilenumber, expectedDetails.get("mobilenumber"), "Mobile number mismatch!");
		softAssert.assertEquals(actualWorknumber, expectedDetails.get("worknumber"), "Work number mismatch!");
		softAssert.assertEquals(actualWorkemail, expectedDetails.get("workemail"), "Work email mismatch!");
		softAssert.assertEquals(actualOtheremail, expectedDetails.get("otheremail"), "Other email mismatch!");

		softAssert.assertAll();
	}

	@Test(dataProvider = "validCredential", description = "Check if MyInfo Contact Details are accurate.")
	public void TC0_MyInfo_ContactDetails(String username, String password) throws InterruptedException {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickDashboard();
		sb.clickMyInfo();
		ContactDetails cd = page.getInstance(ContactDetails.class);
		cd.clickContactDetails();

		String expectedStreet1 = "Rua Sevilla 215";
		String expectedStreet2 = "Rua Berlim 434";
		String expectedCity = "Campinas";
		String expectedState = "São Paulo";
		String expectedZip = "01521-020";
		String expectedCountry = "Brazil";
		String expectedHomeNumber = "11993456569";
		String expectedMobileNumber = "";
		String expectedWorkNumber = "112-898-7612";
		String expectedWorkEmail = "testqa123@example.com";
		String expectedOtherEmail = "";

		String actualStreet1 = cd.getStreet1Value();
		String actualStreet2 = cd.getStreet2Value();
		String actualCity = cd.getCityValue();
		String actualState = cd.getStateValue();
		String actualZip = cd.getZipValue();
		String actualCountry = cd.getCountryTxt();
		String actualHomenumber = cd.getHomeNumberValue();
		String actualMobilenumber = cd.getMobileNumberValue();
		String actualWorknumber = cd.getWorkNumberValue();
		String actualWorkemail = cd.getWorkEmailValue();
		String actualOtheremail = cd.getOtherEmailValue();

		SoftAssert softAssert = new SoftAssert();

		softAssert.assertEquals(actualStreet1, expectedStreet1, "Street1 mismatch!");
		softAssert.assertEquals(actualStreet2, expectedStreet2, "Street2 mismatch!");
		softAssert.assertEquals(actualCity, expectedCity, "City mismatch!");
		softAssert.assertEquals(actualState, expectedState, "State mismatch!");
		softAssert.assertEquals(actualZip, expectedZip, "Zip Code mismatch!");
		softAssert.assertEquals(actualCountry, expectedCountry, "Country mismatch!");
		softAssert.assertEquals(actualHomenumber, expectedHomeNumber, "Home number mismatch!");
		softAssert.assertEquals(actualMobilenumber, expectedMobileNumber, "Mobile number mismatch!");
		softAssert.assertEquals(actualWorknumber, expectedWorkNumber, "Work number mismatch!");
		softAssert.assertEquals(actualWorkemail, expectedWorkEmail, "Work email mismatch!");
		softAssert.assertEquals(actualOtheremail, expectedOtherEmail, "Other email mismatch!");

		softAssert.assertAll(); 
	}
}
