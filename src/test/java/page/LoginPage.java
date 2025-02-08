package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		loginBtn.click();
	}

	public void clickLoginBtn() {
		loginBtn.click();
	}

	public String getTxtErrorMessage() {
		return errorLabel.getText();
	}

	public String getTxtRequiredUser() {
		return requiredUser.getText();
	}

	public String getTxtRequiredPass() {
		return requiredPass.getText();
	}

	public String getUserBorderColor() {
		return username.getCssValue("border-color");
	}

	public String getPassBorderColor() {
		return password.getCssValue("border-color");
	}

	public void clickforgotPass() {
		forgotPasswordLink.click();
	}

	public void clickCancelBtn() {
		cancelBtn.click();
	}

	public WebElement getResetPassLabel() {
		return resetPassLabel;
	}

	public void toResetPass(String username) {
		this.username.sendKeys(username);
		resetPassBtn.click();
	}

	public boolean isResetSuccess() {
		return resetIsSentLabel.isDisplayed()
				&& resetIsSentLabel.getText().equals("Reset Password link sent successfully");
	}
	
	public boolean isLoginPage() {
		return loginLabel.isDisplayed()
				&& loginLabel.getText().equals("Login");
	}
	
	public boolean isLoginSuccesful() {
		return orangeHrmBanner.isDisplayed()
				&& myInfo.isDisplayed();
	}

}
