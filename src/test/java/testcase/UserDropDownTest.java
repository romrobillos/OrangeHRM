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

		// Should not exceed 64 characters
		String pwWith65chars = "Abcd1234!@#$%^&*()_+=-qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVB";
		String expected65CharsErrorMsg = "Should not exceed 64 characters";

		boolean isCorrectErrorMsg = userDD.validatePw2(pwWith65chars, pwWith65chars, expected65CharsErrorMsg);
		softAssert.assertTrue(isCorrectErrorMsg);
		Thread.sleep(4000);
		userDD.toRefreshPage();

		// Your password must contain minimum 1 number
		String pwWithNoNumber = "abcdefg";
		String expectedNoNumberErrorMsg = "Your password must contain minimum 1 number";

		boolean isCorrect1NumErrorMsg = userDD.validatePw2(pwWithNoNumber, pwWithNoNumber, expectedNoNumberErrorMsg);

		softAssert.assertTrue(isCorrect1NumErrorMsg);
		Thread.sleep(4000);
		userDD.toRefreshPage();

		// Your password should not contain spaces
		String pwWithSpace = "abc de2fg";
		String expectedNoSpacesErrorMsg = "Your password should not contain spaces";

		boolean isCorrectNoSpaceErrorMsg = userDD.validatePw2(pwWithSpace, pwWithSpace, expectedNoSpacesErrorMsg);

		softAssert.assertTrue(isCorrectNoSpaceErrorMsg);
		Thread.sleep(4000);
		softAssert.assertAll();

	}

}
