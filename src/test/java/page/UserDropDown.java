package page;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserDropDown extends BasePage {

	public UserDropDown(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")
	WebElement userDD;

	// Update Password

	@FindBy(xpath = "//a[normalize-space()='Change Password']")
	WebElement userDD_changePW;

	@FindBy(xpath = "(//div[contains(@class, 'field-bottom-space')])[1]//input")
	WebElement updatepw_currentPWTxtbox;

	@FindBy(xpath = "(//div[contains(@class, 'field-bottom-space')])[2]//input")
	WebElement updatepw_newPWTxtbox;

	@FindBy(xpath = "(//div[contains(@class, 'field-bottom-space')])[3]//input")
	WebElement updatepw_confirmNewPWTxtbox;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement updatepw_saveBtn;

	@FindBy(xpath = "//button[normalize-space()='Cancel']")
	WebElement updatepw_cancelBtn;

	public void clickuserDD() {
		clickWaitElement(userDD);
	}

	public void clickChangePW() {
		clickWaitElement(userDD_changePW);
	}

	public boolean isAtChangePWPage(String actualPage, String expectedPage) {
		WebElement updatePWHeader = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//h6[normalize-space()='Update Password']")));

		return actualPage.equals(expectedPage) && updatePWHeader.isDisplayed();
	}

	public boolean toCancelChangePW() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h6[normalize-space()='Update Password']")));

		clickWaitElement(updatepw_cancelBtn);

		boolean isInvisible = wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h6[normalize-space()='Update Password']")));
		return isInvisible;
	}

	public String changePWErrorMsg(String oldPw, String newPW) {
		sendKeysWithWait(updatepw_currentPWTxtbox, oldPw);
		sendKeysWithWait(updatepw_newPWTxtbox, newPW);
		clickWaitElement(updatepw_saveBtn);

		WebElement errorMessage = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//span[contains(@class,'field-error-message')]")));
		return errorMessage.getText();
	}

	public boolean validatePw(String newPW, String confirmNewPW, String expectedErrorMsg) {
		sendKeysWithWait(updatepw_newPWTxtbox, newPW);
		sendKeysWithWait(updatepw_confirmNewPWTxtbox, confirmNewPW);
		clickWaitElement(updatepw_saveBtn);
		
		WebElement newPWErrorMessage = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("(//div[contains(@class, 'field-bottom-space')])[2]/span")));
		 String newPWActualErrorMsg = newPWErrorMessage.getText();
		 System.out.println(newPWActualErrorMsg);
		 System.out.println(expectedErrorMsg);
		
		String newPWValue = updatepw_newPWTxtbox.getAttribute("value");
		String confirmNewPWValue = updatepw_confirmNewPWTxtbox.getAttribute("value");

		if (newPWValue.isEmpty() && newPWActualErrorMsg.equals(expectedErrorMsg)) {
			return true;
		}
		if (newPWValue.length() > 64 && newPWActualErrorMsg.equals(expectedErrorMsg)) {
			return true;
		}
		if (newPWValue.contains(" ") && newPWActualErrorMsg.equals(expectedErrorMsg)) {
			return true;
		}
		if (newPWValue.length() < 7 && newPWActualErrorMsg.equals(expectedErrorMsg))  {
			return true;
		}
		if (!newPWValue.matches(".*\\d.*") && newPWActualErrorMsg.equals(expectedErrorMsg)) {
			return true;
		}
		if (!newPWValue.equals(confirmNewPWValue) && newPWActualErrorMsg.equals(expectedErrorMsg)) {
			return true;
		}

		return false;

	}
	
	public boolean validatePw2(String newPW, String confirmNewPW, String expectedErrorMsg) {
	    sendKeysWithWait(updatepw_newPWTxtbox, newPW);
	    sendKeysWithWait(updatepw_confirmNewPWTxtbox, confirmNewPW);
	    clickWaitElement(updatepw_saveBtn);

	    WebElement newPWErrorMessage = wait.until(ExpectedConditions
	            .presenceOfElementLocated(By.xpath("(//div[contains(@class, 'field-bottom-space')])[2]/span")));
	    String newPWActualErrorMsg = newPWErrorMessage.getText();
	    
	    System.out.println("Actual: " + newPWActualErrorMsg);
	    System.out.println("Expected: " + expectedErrorMsg);

	    String newPWValue = updatepw_newPWTxtbox.getAttribute("value");
	    String confirmNewPWValue = updatepw_confirmNewPWTxtbox.getAttribute("value");

	    // Define expected error messages for different conditions
	    Map<String, Boolean> conditions = new HashMap<>();
	    conditions.put("Required", newPWValue.isEmpty());
	    conditions.put("Password should not exceed 64 characters", newPWValue.length() > 64);
	    conditions.put("Your password should not contain spaces", newPWValue.contains(" "));
	    conditions.put("Password should have at least 7 characters", newPWValue.length() < 7);
	    conditions.put("Your password must contain at least 1 number", !newPWValue.matches(".*\\d.*"));
	    conditions.put("Passwords do not match", !newPWValue.equals(confirmNewPWValue));

	    // Check if the expected error message is valid for the given input
	    return conditions.getOrDefault(expectedErrorMsg, true) && newPWActualErrorMsg.equals(expectedErrorMsg);
	}

	// Logout

	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement userDD_logout;

	public void clickLogout() {
		clickWaitElement(userDD_logout);
	}

	public boolean isLogoutSuccesful(String actualPage, String expectedPage) {
		WebElement Login = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[normalize-space()='Login']")));

		return actualPage.equals(expectedPage) && Login.isDisplayed();
	}

}
