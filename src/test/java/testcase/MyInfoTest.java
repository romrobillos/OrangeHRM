package testcase;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;
import page.MyInfo;

public class MyInfoTest extends BaseTest {

	@Test(dataProvider = "validCredential", description = "Verify MyInfo_PersonalDetails")
	public void TC019_MyInfo_PersonalDetails(String username, String password) throws InterruptedException {
		new LoginPage(driver).toLogin(username, password);
		MyInfo myInfo = new MyInfo(driver);
		myInfo.getMyInfo().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(myInfo.getFirstname())).click();
		String expectedFirstname = "sam";
		String expectedMiddlename = "akhil";
		String expectedLastname = "davidtony";
		String expectedEmployeeId = "muser";
		String expectedOtherId = "4957589";
		String expectedDLN = "56788";
		  
		
		String actualFirstname = myInfo.getFirstnameValue();
		Assert.assertEquals(expectedFirstname, actualFirstname);
		
		String actualMiddlename = myInfo.getMiddlenameValue();
		Assert.assertEquals(expectedMiddlename, actualMiddlename);
		
		String actualLastname = myInfo.getLastnameValue();
		Assert.assertEquals(expectedLastname, actualLastname);
		
		String actualEmployeeId = myInfo.getEmployeeIdValue();
		Assert.assertEquals(expectedEmployeeId, actualEmployeeId);
		
		String actualOtherId = myInfo.getOtherIdValue();
		Assert.assertEquals(expectedOtherId, actualOtherId);
//		
//		String actualDLN = myInfo.getDLNValue();
//		Assert.assertEquals(expectedDLN, actualDLN);
		
	}
}
