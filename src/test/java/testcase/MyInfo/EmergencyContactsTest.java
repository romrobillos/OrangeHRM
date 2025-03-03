package testcase.MyInfo;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.SideBar;
import page.MyInfo.EmergencyContacts;
import testcase.BaseTest;

public class EmergencyContactsTest extends BaseTest {
	private Map<String, String> getExpectedEmergencyContactDetails() {
		Map<String, String> details = new HashMap<>();
		details.put("name", "Al Jubail");
		details.put("mobile", "0926");
		details.put("relationship", "Brother");	
		return details;
	}

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_EmergencyContacts Add Button")
	public void TC0_MyInfo_EmergencyContacts_AddBtn(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickMyInfo();
		EmergencyContacts ec = page.getInstance(EmergencyContacts.class);
		ec.clickEmergencyContacts();
		ec.clickAddEmergencyContactsBtn();

		Assert.assertTrue(ec.isEmergencyContactNameDisplayed());
	}

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_EmergencyContacts Cancel Button")
	public void TC0_MyInfo_EmergencyContacts_CancelBtn(String username, String password) throws InterruptedException {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickMyInfo();
		EmergencyContacts ec = page.getInstance(EmergencyContacts.class);
		ec.clickEmergencyContacts();
		ec.clickAddEmergencyContactsBtn();
		ec.clickCancelBtn();

		Assert.assertFalse(ec.isEmergencyContactNameDisplayed());
	}

	@Test(dataProvider = "validCredential", description = "Verify adding an emergency contact in MyInfo_EmergencyContacts")
	public void TC0_MyInfo_EmergencyContacts_AddEmergencyContact(String username, String password) {
		page.getInstance(LoginPage.class).toLogin(username, password);
		SideBar sb = page.getInstance(SideBar.class);
		sb.clickMyInfo();
		EmergencyContacts ec = page.getInstance(EmergencyContacts.class);
		ec.clickEmergencyContacts();
		ec.clickAddEmergencyContactsBtn();

	 

		Map<String, String> expectedDetails = getExpectedEmergencyContactDetails();
		ec.fillOutEC(expectedDetails.get("name"), expectedDetails.get("mobile"), expectedDetails.get("relationship"));

		sb.waitToastNotif();

		boolean isITDisplayed = ec.isEmergencyContactDisplayed(expectedDetails.get("name"), expectedDetails.get("mobile"));

		Assert.assertTrue(isITDisplayed, "Emergency Contact is not displayed in records.");

	}

}
