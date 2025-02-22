package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;

public class LoginTest extends BaseTest {

	@Test(dataProvider = "validCredential")
	public void TC001_toLogin(String username, String password) {
		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);
		Assert.assertTrue(login.isLoginSuccesful());
	}

	@Test(priority = 2, description = "Verify Invalid login error message")
	public void TC002_verifyErrorMessage() {
		String errorMessage;
		String username = "invalid";
		String password = "1234";
		LoginPage loginPage = new LoginPage(driver);
		loginPage.toLogin(username, password);
		errorMessage = loginPage.getTxtErrorMessage();
		Assert.assertTrue(errorMessage.contains("Invalid credentials"));
	}

	@Test(priority = 3, description = "Required Field Validation")
	public void TC003_verifyRequiredField()  {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickLoginBtn();
		Assert.assertEquals(loginPage.getTxtRequiredUser(), "Required");
		Assert.assertEquals(loginPage.getTxtRequiredPass(), "Required");
	}

	@Test(priority = 4, description = "Verify textbox border color")
	public void TC004_txtboxBorderColor() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickLoginBtn();
		String expectedRedColor = "rgb(235, 9, 16)";

		Assert.assertEquals(loginPage.getUserBorderColor(), expectedRedColor);
		Assert.assertEquals(loginPage.getPassBorderColor(), expectedRedColor);
	}

	@Test(priority = 5, description = "Verify forgot password link")
	public void TC005_forgotPassLink() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickforgotPass();

		String expectedResetUrl = prop.getProperty("resetUrl");
		String actualResetUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(expectedResetUrl, actualResetUrl);
		Assert.assertTrue(loginPage.getResetPassLabel().isDisplayed());
	}

	@Test(priority = 6, description = "Verify Reset password")
	public void TC006_toResetPassword() {
		String username = "Admin";
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickforgotPass();
		loginPage.toResetPass(username);

		Assert.assertTrue(loginPage.isResetSuccess());

	}

	@Test(priority = 7, description = "Verify Cancel Reset Button")
	public void TC007_toCancel() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickforgotPass();
		loginPage.clickCancelBtn();
		

		String expectedLoginUrl = prop.getProperty("loginUrl");
		String actualLoginUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(expectedLoginUrl, actualLoginUrl);
		Assert.assertTrue(loginPage.isLoginPage());

	}

}
