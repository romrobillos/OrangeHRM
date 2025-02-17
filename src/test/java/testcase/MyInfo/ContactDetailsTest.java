package testcase.MyInfo;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.SideBar;
import page.MyInfo.ContactDetails;
import testcase.BaseTest;

public class ContactDetailsTest extends BaseTest {

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_ContactDetails if Editable")
	public void TC019_MyInfo_PersonalDetails_Edit(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		SideBar sb = new SideBar(driver);
		sb.getMyInfo().click();
		ContactDetails cd = new ContactDetails(driver);
		cd.getContactDetails().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			wait.until(ExpectedConditions.visibilityOf(cd.getStreet1()));
			wait.until(ExpectedConditions.visibilityOf(cd.getStreet2()));
			wait.until(ExpectedConditions.visibilityOf(cd.getCity()));
			wait.until(ExpectedConditions.visibilityOf(cd.getState()));
			wait.until(ExpectedConditions.visibilityOf(cd.getZip()));
			wait.until(ExpectedConditions.visibilityOf(cd.getCountry()));
			wait.until(ExpectedConditions.visibilityOf(cd.getHomeNumber()));
			wait.until(ExpectedConditions.visibilityOf(cd.getWorkNumber()));
			wait.until(ExpectedConditions.visibilityOf(cd.getMobileNumber()));
			wait.until(ExpectedConditions.visibilityOf(cd.getWorkEmail()));
			wait.until(ExpectedConditions.visibilityOf(cd.getOtherEmail()));

			String expectedStreet1 = "2050B";
			js.executeScript("arguments[0].value = '" + expectedStreet1 + "';", cd.getStreet1());

			String expectedStreet2 = "Sampaloc";
			js.executeScript("arguments[0].value = '" + expectedStreet2 + "';", cd.getStreet2());

			String expectedCity = "Manila";
			js.executeScript("arguments[0].value = '" + expectedCity + "';", cd.getCity());

			String expectedZip = "1008";
			js.executeScript("arguments[0].value = '" + expectedZip + "';", cd.getZip());

			String expectedCountry = "Philippines";
			wait.until(ExpectedConditions.elementToBeClickable(cd.getCountry()));
			cd.selectCountry(expectedCountry);

			String expectedHomeNumber = "73312312";
			js.executeScript("arguments[0].value = '" + expectedHomeNumber + "';", cd.getHomeNumber());

			String expectedMobileNumber = "096656";
			js.executeScript("arguments[0].value = '" + expectedMobileNumber + "';", cd.getMobileNumber());

			String expectedWorkNumber = "096656";
			js.executeScript("arguments[0].value = '" + expectedWorkNumber + "';", cd.getWorkNumber());

			String expectedWorkEmail = "rom.robillos@yahoo.com";
			js.executeScript("arguments[0].value = '" + expectedWorkEmail + "';", cd.getWorkEmail());

			String expectedOtherEmail = "rom.robillos@yahoo.com";
			js.executeScript("arguments[0].value = '" + expectedOtherEmail + "';", cd.getOtherEmail());

			cd.getSaveBtn().click();
	
			wait.until(ExpectedConditions.visibilityOf(sb.getToastNotif()));

			// assert
			String actualStreet1 = cd.getStreet1Value();
			Assert.assertEquals(expectedStreet1, actualStreet1);

			String actualStreet2 = cd.getStreet2Value();
			Assert.assertEquals(expectedStreet2, actualStreet2);

			String actualCity = cd.getCityValue();
			Assert.assertEquals(expectedCity, actualCity);

			String actualZip = cd.getZipValue();
			Assert.assertEquals(expectedZip, actualZip);

			String actualCountry = cd.getCountryTxt();
			Assert.assertEquals(expectedCountry, actualCountry);

			String actualHomeNumber = cd.getHomeNumberValue();
			Assert.assertEquals(expectedHomeNumber, actualHomeNumber);

			String actualMobileNumber = cd.getMobileNumberValue();
			Assert.assertEquals(expectedMobileNumber, actualMobileNumber);

			String actualWorkNumber = cd.getWorkNumberValue();
			Assert.assertEquals(expectedWorkNumber, actualWorkNumber);

			String actualWorkEmail = cd.getWorkEmailValue();
			Assert.assertEquals(expectedWorkEmail, actualWorkEmail);

			String actualOtherEmail = cd.getOtherEmailValue();
			Assert.assertEquals(expectedOtherEmail, actualOtherEmail);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_ContactDetails correct information")
	public void TC0_MyInfo_ContactDetails(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		new SideBar(driver).getMyInfo().click();
		ContactDetails cd = new ContactDetails(driver);
		cd.getContactDetails().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(cd.getStreet1())).click();
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
		Assert.assertEquals(expectedStreet1, actualStreet1);

		String actualStreet2 = cd.getStreet2Value();
		Assert.assertEquals(expectedStreet2, actualStreet2);

		String actualCity = cd.getCityValue();
		Assert.assertEquals(expectedCity, actualCity);

		String actualState = cd.getStateValue();
		Assert.assertEquals(expectedState, actualState);

		String actualZip = cd.getZipValue();
		Assert.assertEquals(expectedZip, actualZip);

		String actualCountry = cd.getCountryTxt();
		Assert.assertEquals(expectedCountry, actualCountry);

		String actualHomeNumber = cd.getHomeNumberValue();
		Assert.assertEquals(expectedHomeNumber, actualHomeNumber);

		String actualMobileNumber = cd.getMobileNumberValue();
		Assert.assertEquals(expectedMobileNumber, actualMobileNumber);

		String actualWorkNumber = cd.getWorkNumberValue();
		Assert.assertEquals(expectedWorkNumber, actualWorkNumber);

		String actualWorkEmail = cd.getWorkEmailValue();
		Assert.assertEquals(expectedWorkEmail, actualWorkEmail);

		String actualOtherEmail = cd.getOtherEmailValue();
		Assert.assertEquals(expectedOtherEmail, actualOtherEmail);
	}
}
