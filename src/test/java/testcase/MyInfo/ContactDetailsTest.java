package testcase.MyInfo;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.SideBar;
import page.MyInfo.ContactDetails;
import testcase.BaseTest;

public class ContactDetailsTest extends BaseTest {
	@Test(dataProvider = "validCredential", description = "Verify MyInfo_ContactDetails")
	public void TC020_MyInfo_ContactDetails(String username, String password) throws InterruptedException {
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
		
		System.out.println(cd.getMobileNumberValue());
		System.out.println(cd.getOtherEmailValue());
		
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
