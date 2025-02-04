package testcase;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.LoginPage;

public class LoginTest extends BaseTest {

	@Test(dataProvider = "validCredential")
	public void TC001_toLogin(String username, String password) {
		LoginPage login = new LoginPage(driver);
		login.toLogin(username, password);
	}

	@Test(priority = 2, description = "Verify invalid login error message")
	public void TC002_verifyErrorMessage() {
		this.username = "Invalid_user";
		this.password = "Invalid_pass";
		TC001_toLogin(username, password);
		LoginPage loginPage = new LoginPage(driver);
		this.errorMessage = loginPage.getTxtErrorMessage();
		Assert.assertTrue(this.errorMessage.contains("Invalid credentials"));
	}

	@Test(priority = 3, description = "Required Field Validation")
	public void TC003_verifyRequiredField() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickLoginBtn();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		Assert.assertEquals(loginPage.getTxtRequiredUser(), "Required");
		Assert.assertEquals(loginPage.getTxtRequiredPass(), "Required");
	}

	@Test(priority = 4, description = "Verify textbox border color")
	public void TC004_txtboxBorderColor() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickLoginBtn();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String expectedRedColor = "rgb(235, 9, 16)";

		Assert.assertEquals(loginPage.getUserBorderColor(), expectedRedColor);
		Assert.assertEquals(loginPage.getPassBorderColor(), expectedRedColor);
	}

	@Test(priority = 5, description = "Verify forgot password link")
	public void TC005_forgotPassLink() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickforgotPass();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		Assert.assertTrue(loginPage.getResetPassLabel().isDisplayed());
	}

	@Test(priority = 6, description = "Verify Reset password")
	public void TC006_toResetPassword() throws InterruptedException {
		String username = "Admin";
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickforgotPass();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		loginPage.toResetPass(username);
		
		Assert.assertTrue(loginPage.isResetSuccess());
		
	}
	
	@Test(priority = 7, description = "Verify Cancel Reset Button")
	public void TC006_toCancel() throws InterruptedException {
		String username = "Admin";
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickforgotPass();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		loginPage.clickCancelBtn();
		
		Assert.assertTrue(loginPage.isLoginPage());
		
	}

}
