package testcase.MyInfo;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.SideBar;
import page.MyInfo.EmergencyContacts;
import testcase.BaseTest;

public class EmergencyContactsTest extends BaseTest {

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_EmergencyContacts Add Button")
	public void TC0_MyInfo_EmergencyContacts_AddBtn(String username, String password) {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getMyInfo().click();
		EmergencyContacts ec = new EmergencyContacts(driver);
		ec.clickEmergencyContacts();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ec.getAddEmergencyContactsBtn())).click();

		Assert.assertTrue(ec.isEmergencyContactNameDisplayed());
	}

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_EmergencyContacts Cancel Button")
	public void TC0_MyInfo_EmergencyContacts_CancelBtn(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getMyInfo().click();
		EmergencyContacts ec = new EmergencyContacts(driver);
		ec.clickEmergencyContacts();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ec.getAddEmergencyContactsBtn())).click();
		ec.clickCancelBtn();

		Assert.assertFalse(ec.isEmergencyContactNameDisplayed());
	}

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_EmergencyContacts Add Emergency contact")
	public void TC0_MyInfo_EmergencyContacts_AddEmergencyContact(String username, String password) {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getMyInfo().click();
		EmergencyContacts ec = new EmergencyContacts(driver);
		ec.clickEmergencyContacts();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ec.getAddEmergencyContactsBtn())).click();

		String name = "Al Jubail";
		String mobile = "0926";
		String relationship = "Brother";

		ec.getEc_nameTxt().sendKeys(name);
		ec.getEc_mobileTxt().sendKeys(mobile);
		ec.getEc_relationTxt().sendKeys(relationship);

		ec.clickSaveBtn();

		wait.until(ExpectedConditions.visibilityOf(sb.getToastNotif()));

		boolean isITDisplayed = ec.isEmergencyContactDisplayed(name, mobile);

		Assert.assertTrue(isITDisplayed, "Emergency Contact is not displayed in records.");

	}

}
