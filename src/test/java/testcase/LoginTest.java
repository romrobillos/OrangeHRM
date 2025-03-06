package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;

public class LoginTest extends BaseTest {

	@Test(dataProvider = "validCredential")
	public void TC001_toLogin(String username, String password) {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.toLogin(username, password);
	
		boolean isLoginSuccessful = loginPage.isLoginSuccesful();
		Assert.assertTrue(isLoginSuccessful);
	}

	@Test(priority = 2, description = "Verify Invalid login error message")
	public void TC002_verifyErrorMessage() {
		String username = "invalid";
		String password = "1234";
		
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.toLogin(username, password);
		
		Assert.assertTrue(loginPage.getTxtErrorMessage().contains("Invalid credentials"));
	}

	@Test(priority = 3, description = "Required Field Validation")
	public void TC003_verifyRequiredField()  {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.clickLoginBtn();
		Assert.assertEquals(loginPage.getTxtUserRequired(), "Required");
		Assert.assertEquals(loginPage.getTxtPassRequired(), "Required");
	}

	@Test(priority = 4, description = "Verify textbox border color")
	public void TC004_txtboxBorderColor() {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.clickLoginBtn();
		String expectedRedColor = "rgb(235, 9, 16)";

		Assert.assertEquals(loginPage.getUserBorderColor(), expectedRedColor);
		Assert.assertEquals(loginPage.getPassBorderColor(), expectedRedColor);
	}

	@Test(priority = 5, description = "Verify forgot password link")
	public void TC005_forgotPassLink() {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.clickforgotPass();
		
		Assert.assertTrue(loginPage.getResetPassLabel().isDisplayed());
		
		String expectedResetUrl = prop.getProperty("resetUrl");
		String actualResetUrl = loginPage.getCurrentPageUrl();
		
		
		Assert.assertEquals(expectedResetUrl, actualResetUrl);
		
	}

	@Test(priority = 6, description = "Verify Reset password")
	public void TC006_toResetPassword() {
		String username = "Admin";
		LoginPage loginPage = page.getInstance(LoginPage.class);
		
		loginPage.toResetPass(username);

		Assert.assertTrue(loginPage.isResetSuccess());

	}

	@Test(priority = 7, description = "Verify Cancel Reset Button")
	public void TC007_toCancel() {

		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.clickCancelResetBtn();
		

		String expectedLoginUrl = prop.getProperty("loginUrl");
		String actualLoginUrl =  loginPage.getCurrentPageUrl();
		
		Assert.assertEquals(expectedLoginUrl, actualLoginUrl);
		Assert.assertTrue(loginPage.isLoginPage());

	}

}
