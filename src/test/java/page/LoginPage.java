package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

	@FindBy(name = "username")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement loginBtn;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
	WebElement errorLabel;

	@FindBy(xpath = "//div[@class='orangehrm-login-slot-wrapper']//div[1]//div[1]//span[1]")
	WebElement requiredUser;

	@FindBy(xpath = "//div[@class='orangehrm-login-form']//div[2]//div[1]//span[1]")
	WebElement requiredPass;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")
	WebElement forgotPasswordLink;

	@FindBy(xpath = "//h6[normalize-space()='Reset Password']")
	WebElement resetPassLabel;

	@FindBy(xpath = "//button[normalize-space()='Reset Password']")
	WebElement resetPassBtn;

	@FindBy(xpath = "//h6[normalize-space()='Reset Password link sent successfully']")
	WebElement resetIsSentLabel;

	@FindBy(xpath = "//button[@type='button']")
	WebElement cancelBtn;

	@FindBy(xpath = "//h5[normalize-space()='Login']")
	WebElement loginLabel;

	@FindBy(xpath = "//img[@alt='client brand banner']")
	WebElement orangeHrmBanner;

	@FindBy(xpath = "//span[normalize-space()='My Info']")
	WebElement myInfo;

	public LoginPage(WebDriver driver) {
		super(driver);

	}

	public void toLogin(String username, String password) {
		sendKeysWithWait(this.username, username);
		sendKeysWithWait(this.password, password);
		clickWaitElement(loginBtn);
	}

	public void clickLoginBtn() {
		clickWaitElement(loginBtn);
	}

	public String getTxtErrorMessage() {
		return errorLabel.getText();
	}

	public String getTxtUserRequired() {
		return requiredUser.getText();
	}

	public String getTxtPassRequired() {
		return requiredPass.getText();
	}

	public String getUserBorderColor() {
		WebElement userTxtbox = username;
		waitForCssValueChange(userTxtbox, "border-color", "rgb(235, 9, 16)");
		return userTxtbox.getCssValue("border-color");
	}

	public String getPassBorderColor() {
		WebElement passTxtbox = password; 
		waitForCssValueChange(passTxtbox, "border-color", "rgb(235, 9, 16)");
		return passTxtbox.getCssValue("border-color");
	}

	public void clickforgotPass() {
		clickWaitElement(forgotPasswordLink); 
	}

	public void clickCancelResetBtn() {
		clickWaitElement(forgotPasswordLink); 
		clickWaitElement(cancelBtn);
	}

	public WebElement getResetPassLabel() {
		return resetPassLabel;
	}

	public void toResetPass(String username) {
		clickWaitElement(forgotPasswordLink); 
		sendKeysWithWait(this.username, username);
		clickWaitElement(resetPassBtn);
	}

	public boolean isResetSuccess() {
		return resetIsSentLabel.isDisplayed()
				&& resetIsSentLabel.getText().equals("Reset Password link sent successfully");
	}

	public boolean isLoginPage() {
		return loginLabel.isDisplayed() && loginLabel.getText().equals("Login");
	}

	public boolean isLoginSuccesful() {
		return orangeHrmBanner.isDisplayed() && myInfo.isDisplayed();
	}

}
