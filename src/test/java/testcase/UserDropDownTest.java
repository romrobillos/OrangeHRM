package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import page.LoginPage;
import page.UserDropDown;

public class UserDropDownTest extends BaseTest {
	@Test(dataProvider = "validCredential")
	public void TC001_isAtChangePWPage(String username, String password) {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.toLogin(username, password);
		UserDropDown userDD = page.getInstance(UserDropDown.class);
		userDD.clickuserDD();
		userDD.clickChangePW();

		String actualPage = userDD.getCurrentPageUrl();
		String expectedPage = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/updatePassword";

		boolean isAt = userDD.isAtChangePWPage(actualPage, expectedPage);

		Assert.assertTrue(isAt);
	}

	@Test(dataProvider = "validCredential")
	public void TC001_atLeast7CharsErrorMsg(String username, String password) {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.toLogin(username, password);
		UserDropDown userDD = page.getInstance(UserDropDown.class);
		userDD.clickuserDD();
		userDD.clickChangePW();

		String oldPW = "Admin123";
		String newPW = "abcdef";
		String expectedErrorMsg = "Should have at least 7 characters";

		String actualErrorMsg = userDD.changePWErrorMsg(oldPW, newPW);

		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);

	}

	@Test(dataProvider = "validCredential")
	public void TC001_mustContain1NumErrorMsg(String username, String password) {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.toLogin(username, password);
		UserDropDown userDD = page.getInstance(UserDropDown.class);
		userDD.clickuserDD();
		userDD.clickChangePW();

		String oldPW = "Admin123";
		String newPW = "abcdefg";
		String expectedErrorMsg = "Your password must contain minimum 1 number";

		String actualErrorMsg = userDD.changePWErrorMsg(oldPW, newPW);

		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);

	}

	@Test(dataProvider = "validCredential")
	public void TC001_notExceed64CharsErrorMsg(String username, String password) {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.toLogin(username, password);
		UserDropDown userDD = page.getInstance(UserDropDown.class);
		userDD.clickuserDD();
		userDD.clickChangePW();

		String oldPW = "Admin123";
		String newPW = "Abcd1234!@#$%^&*()_+=-qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVB";
		String expectedErrorMsg = "Should not exceed 64 characters";

		String actualErrorMsg = userDD.changePWErrorMsg(oldPW, newPW);

		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);

	}

	@Test(dataProvider = "validCredential")
	public void TC001_isUpdatePwCanceled(String username, String password) {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.toLogin(username, password);
		UserDropDown userDD = page.getInstance(UserDropDown.class);
		userDD.clickuserDD();
		userDD.clickChangePW();

		boolean isCanceled = userDD.toCancelChangePW();

		Assert.assertTrue(isCanceled);

	}

	@Test(dataProvider = "validCredential")
	public void TC001_isLogoutSuccesful(String username, String password) {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.toLogin(username, password);
		UserDropDown userDD = page.getInstance(UserDropDown.class);
		userDD.clickuserDD();
		userDD.clickLogout();

		String actualPage = userDD.getCurrentPageUrl();
		String expectedPage = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

		boolean isLogout = userDD.isLogoutSuccesful(actualPage, expectedPage);

		Assert.assertTrue(isLogout);

	}

	@Test(dataProvider = "validCredential")
	public void TC001_ValidatePW(String username, String password) throws InterruptedException {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.toLogin(username, password);
		UserDropDown userDD = page.getInstance(UserDropDown.class);
		userDD.clickuserDD();
		userDD.clickChangePW();
		SoftAssert softAssert = new SoftAssert();
	
		String validOldPW = "admin123";
		String validNewPW = "abcdef1";
		String validConfirmNewPW= "abcdef1";
		
		String expectedRequiredErrorMsg = "Required";
		String noErrorMsg = "";
		
		// Should not exceed 64 characters
		String newPWWith65chars = "Abcd1234!@#$%^&*()_+=-qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVB";
		String confirmPW65Chars = "Abcd1234!@#$%^&*()_+=-qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVB";
		String expected65CharsErrorMsg = "Should not exceed 64 characters";

		boolean isCorrectErrorMsg = userDD.validateNewPWField(validOldPW, newPWWith65chars,confirmPW65Chars, expected65CharsErrorMsg);
		softAssert.assertTrue(isCorrectErrorMsg);
		userDD.toRefreshPage();

		// Your password must contain minimum 1 number
		String pwWithNoNumber = "abcdefg";
		String confirmNewPWNoNumber = "abcdefg";
		String expectedNoNumberErrorMsg = "Your password must contain minimum 1 number";

		boolean isCorrect1NumErrorMsg = userDD.validateNewPWField(validOldPW, pwWithNoNumber,confirmNewPWNoNumber, expectedNoNumberErrorMsg);

		softAssert.assertTrue(isCorrect1NumErrorMsg);
		userDD.toRefreshPage();

		// Your password should not contain spaces
		String newPWWithSpace = "abc de2fg";
		String confirmNewPWWithSpace = "abc de2fg";
		String expectedNoSpacesErrorMsg = "Your password should not contain spaces";

		boolean isCorrectNoSpaceErrorMsg = userDD.validateNewPWField(validOldPW, newPWWithSpace,confirmNewPWWithSpace, expectedNoSpacesErrorMsg);

		softAssert.assertTrue(isCorrectNoSpaceErrorMsg);
		userDD.toRefreshPage();
		
		// Required
		String blankAllPW = "";
		
		boolean isCorrectBlankPWErrorMsg = userDD.validateNewPWField( blankAllPW, blankAllPW, blankAllPW, expectedRequiredErrorMsg);
		softAssert.assertTrue(isCorrectBlankPWErrorMsg);
		userDD.toRefreshPage();
		
		// Should have at least 7 characters
		String less7CharsNewPW = "qweas1";
		String expectedless7CharsPWErrorMsg = "Should have at least 7 characters";
		
		boolean isCorrectless7CharsPWErrorMsg = userDD.validateNewPWField( validOldPW, less7CharsNewPW, less7CharsNewPW, expectedless7CharsPWErrorMsg);
		softAssert.assertTrue(isCorrectless7CharsPWErrorMsg);
		userDD.toRefreshPage();
		
		// Passwords do not match *Confirm Password
		String confirmNewPWBlank = "";
		String expectedDoNotMatchPWErrorMsg = "Passwords do not match";
		

		boolean isCorrectDoNotMatchPWErrorMsg = userDD.validateConfirmPWField( validOldPW, validNewPW, confirmNewPWBlank, expectedDoNotMatchPWErrorMsg);
		softAssert.assertTrue(isCorrectDoNotMatchPWErrorMsg);
		userDD.toRefreshPage();
		
		// Required error message for *Current Password
		String blankOldPW = "";
		

		boolean isCorrectRequiredErrorMsg= userDD.validateOldPWField( blankOldPW, validNewPW, validConfirmNewPW, expectedRequiredErrorMsg);
		softAssert.assertTrue(isCorrectRequiredErrorMsg);
		userDD.toRefreshPage();
		
		// Entered valid Old password
		boolean isChangePWSuccess= userDD.validateOldPWField( validOldPW, validNewPW, validConfirmNewPW, noErrorMsg);
		softAssert.assertTrue(isChangePWSuccess);
		userDD.toRefreshPage();
		
		
		// Entered invalid Old password
		String invalidOldPW = "admin321";

		boolean isChangePWFailed= userDD.validateOldPWField( invalidOldPW, validNewPW, validConfirmNewPW, noErrorMsg);
		softAssert.assertTrue(isChangePWFailed);

		
		
		softAssert.assertAll();

	}

}
