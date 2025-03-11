package page;

import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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

	public boolean validateOldPWField(String oldPW, String newPW, String confirmPW, String expectedErrorMsg) {

		// Enter the values and click Save
		sendKeysWithWait(updatepw_currentPWTxtbox, oldPW);
		sendKeysWithWait(updatepw_newPWTxtbox, newPW);
		sendKeysWithWait(updatepw_confirmNewPWTxtbox, confirmPW);
		clickWaitElement(updatepw_saveBtn);
		// Get the current value from the old password textbox
		String oldPWValue = updatepw_currentPWTxtbox.getAttribute("value");
		String newPWValue = updatepw_newPWTxtbox.getAttribute("value");
		String confirmNewPWValue = updatepw_confirmNewPWTxtbox.getAttribute("value");
		System.out.println("old: " + oldPWValue);
		System.out.println("New: " + newPWValue);
		System.out.println("Confirm: " + confirmNewPWValue);

		// If old password is empty, we expect a field error (expectedErrorMsg is
		// "Required")
		if (oldPWValue.isEmpty()) {
			WebElement oldPWErrorMessage = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("(//div[contains(@class, 'field-bottom-space')])[1]/span")));
			String oldPWActualErrorMsg = oldPWErrorMessage.getText();
			System.out.println("Old PW Field Error - Actual: " + oldPWActualErrorMsg);
			System.out.println("Expected: " + expectedErrorMsg);
			return oldPWActualErrorMsg.equals(expectedErrorMsg);
		} else {
			// Otherwise, a toast notification should appear indicating success or error.
			// Adjust the XPath as needed to target the toast on the OrangeHRM demo Change
			// Password page.
			try {
				WebElement toastNotification = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"oxd-toaster_1\"]")));

				String toastText = toastNotification.getText();
				System.out.println("Toast Notification: " + toastText);
				// Optionally, you can check for a specific keyword.
				// For example, if a valid password change shows "Success" and an invalid one
				// shows "Error",
				// you might do:
				if (toastText.contains("Success") || toastText.contains("Error")) {
					return true;
				} else {
					return false;
				}
			} catch (TimeoutException e) {
				System.out.println("Toast notification did not appear.");
				return false;
			}
		}
	}

	public boolean validateNewPWField(String oldPW, String newPW, String confirmPW, String expectedErrorMsg) {

		sendKeysWithWait(updatepw_currentPWTxtbox, oldPW);
		sendKeysWithWait(updatepw_newPWTxtbox, newPW);
		sendKeysWithWait(updatepw_confirmNewPWTxtbox, confirmPW);
		clickWaitElement(updatepw_saveBtn);

		WebElement newPWErrorMessage = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("(//div[contains(@class, 'field-bottom-space')])[2]/span")));
		String newPWActualErrorMsg = newPWErrorMessage.getText();

		System.out.println("Actual: " + newPWActualErrorMsg);
		System.out.println("Expected: " + expectedErrorMsg);

		String oldPWValue = updatepw_currentPWTxtbox.getAttribute("value");
		String newPWValue = updatepw_newPWTxtbox.getAttribute("value");
		String confirmNewPWValue = updatepw_confirmNewPWTxtbox.getAttribute("value");

		// Define expected error messages for different conditions
		Map<String, Boolean> conditions = new HashMap<>();
		conditions.put("Required", oldPWValue.isEmpty() || newPWValue.isEmpty() || confirmNewPWValue.isEmpty());
		conditions.put("Password should not exceed 64 characters", newPWValue.length() > 64);
		conditions.put("Your password should not contain spaces", newPWValue.contains(" "));
		conditions.put("Password should have at least 7 characters", newPWValue.length() < 7);
		conditions.put("Your password must contain at least 1 number", !newPWValue.matches(".*\\d.*"));
		conditions.put("Should have at least 7 characters", newPWValue.length() < 7);
		// Check if the expected error message is valid for the given input
		System.out.println(oldPWValue);
		System.out.println(newPWValue);
		System.out.println(confirmNewPWValue);
		System.out.println(newPWActualErrorMsg);
		System.out.println(expectedErrorMsg);

		return conditions.getOrDefault(expectedErrorMsg, true) && newPWActualErrorMsg.equals(expectedErrorMsg);
	}

	public boolean validateConfirmPWField(String oldPW, String newPW, String confirmNewPW, String expectedErrorMsg) {

		sendKeysWithWait(updatepw_currentPWTxtbox, oldPW);
		sendKeysWithWait(updatepw_newPWTxtbox, newPW);
		sendKeysWithWait(updatepw_confirmNewPWTxtbox, confirmNewPW);
		clickWaitElement(updatepw_saveBtn);

		WebElement confirmNewPWErrorMessage = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("(//div[contains(@class, 'field-bottom-space')])[3]/span")));
		String confirmNewPWActualErrorMsg = confirmNewPWErrorMessage.getText();

		System.out.println("Actual: " + confirmNewPWActualErrorMsg);
		System.out.println("Expected: " + expectedErrorMsg);

		String oldPWValue = updatepw_currentPWTxtbox.getAttribute("value");
		String newPWValue = updatepw_newPWTxtbox.getAttribute("value");
		String confirmNewPWValue = updatepw_confirmNewPWTxtbox.getAttribute("value");

		// Define expected error messages for different conditions
		Map<String, Boolean> conditions = new HashMap<>();
		conditions.put("Passwords do not match", confirmNewPWValue.isEmpty());
		conditions.put("Passwords do not match", !newPWValue.equals(confirmNewPWValue));

		// Check if the expected error message is valid for the given input
		System.out.println(oldPWValue);
		System.out.println(newPWValue);
		System.out.println(confirmNewPWValue);
		System.out.println(confirmNewPWActualErrorMsg);
		System.out.println(expectedErrorMsg);

		return conditions.getOrDefault(expectedErrorMsg, true) && confirmNewPWActualErrorMsg.equals(expectedErrorMsg);
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
