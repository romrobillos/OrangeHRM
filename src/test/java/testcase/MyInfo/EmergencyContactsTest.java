package testcase.MyInfo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import page.LoginPage;
import page.SideBar;
import page.MyInfo.EmergencyContacts;
import testcase.BaseTest;

public class EmergencyContactsTest extends BaseTest {

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_EmergencyContacts Add Button")
	public void TC0_MyInfo_EmergencyContacts_AddBtn(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getMyInfo().click();
		EmergencyContacts ec = new EmergencyContacts(driver);
		ec.getEmergencyContacts().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		ec.getAddEmergencyContactsBtn().click();

		Assert.assertTrue(ec.isEmergencyContactNameDisplayed());
	}

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_EmergencyContacts Cancel Button")
	public void TC0_MyInfo_EmergencyContacts_CancelBtn(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getMyInfo().click();
		EmergencyContacts ec = new EmergencyContacts(driver);
		ec.getEmergencyContacts().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Assert.assertFalse(ec.isEmergencyContactNameDisplayed(),
				"Emergency Contact name should not be displayed after clicking.");
	}

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_EmergencyContacts Add Emergency contact")
	public void TC0_MyInfo_EmergencyContacts_AddEmergencyContact(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getMyInfo().click();
		EmergencyContacts ec = new EmergencyContacts(driver);
		ec.getEmergencyContacts().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		ec.getAddEmergencyContactsBtn().click();
		String name = "Al Jubail";
		String mobile = "0926";
		String relationship = "Brother";
//		JavascriptExecutor js = (JavascriptExecutor) driver;
	 
			wait.until(ExpectedConditions.visibilityOf(ec.getEc_nameTxt()));
			wait.until(ExpectedConditions.visibilityOf(ec.getEc_mobileTxt()));
			wait.until(ExpectedConditions.visibilityOf(ec.getEc_relationTxt()));

			ec.getEc_nameTxt().sendKeys(name);
			ec.getEc_mobileTxt().sendKeys(mobile);
			ec.getEc_relationTxt().sendKeys(relationship);
			Thread.sleep(2000);
//			js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
//					ec.getEc_nameTxt(), name);
//			js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
//					ec.getEc_relationTxt(), relationship);
//			js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
//					ec.getEc_mobileTxt(), mobile);

			ec.getSaveBtn().click();

			wait.until(ExpectedConditions.visibilityOf(sb.getToastNotif()));
			
			boolean isITDisplayed = ec.isEmergencyContactDisplayed(name, mobile);

			Assert.assertTrue(isITDisplayed,
					"Emergency Contact is not displayed in records.");
	 
	}

//	@Test(dataProvider = "validCredential", description = "Verify MyInfo_EmergencyContacts Add Emergency contact")
//	public void TC0_MyInfo_EmergencyContacts_AddEmergencyContact(String username, String password)
//			throws InterruptedException {
//
//		new LoginPage(driver).toLogin(username, password);
//		SideBar sb = new SideBar(driver);
//		sb.getMyInfo().click();
//		EmergencyContacts ec = new EmergencyContacts(driver);
//		ec.getEmergencyContacts().click();
//		
//		String contactName = "Al Jubail";
//        String mobileNumber = "0926";
//		String contactXpath = "//div[contains(@class,'oxd-table-row')]//div[text()='" + contactName + "']";
//		String mobileXpath = "//div[contains(@class,'oxd-table-row')]//div[text()='" + mobileNumber + "']";
//
//		WebElement contactElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(contactXpath)));
//		WebElement mobileElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mobileXpath)));
//
//		Assert.assertTrue(contactElement.isDisplayed() && mobileElement.isDisplayed(),
//				"Emergency Contact is not displayed in records.");
//
//	}

}
