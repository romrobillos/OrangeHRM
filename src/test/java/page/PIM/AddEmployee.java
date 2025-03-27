package page.PIM;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page.BasePage;

public class AddEmployee extends BasePage {

	public AddEmployee(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[normalize-space()='Add Employee']")
	WebElement addEmployee;

	@FindBy(xpath = "//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")
	WebElement uploadPhotoBtn;
	
	@FindBy(xpath = "//img[contains(@src,'data:image/jpeg;base64')]") 
	WebElement uploadedPhotoPreview;

	@FindBy(xpath = "//input[@placeholder='First Name']")
	WebElement firstName;

	@FindBy(xpath = "//input[@placeholder='Middle Name']")
	WebElement middleName;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	WebElement lastName;

	@FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
	WebElement employeeID;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement saveBtn;

	@FindBy(xpath = "//button[normalize-space()='Cancel']")
	WebElement cancelBtn;

	// Create Login Details

	@FindBy(xpath = "//span[contains(@class, 'oxd-switch-input')]")
	WebElement createLoginDetailsCheckbox;

	@FindBy(xpath = "//div[@class='oxd-form-row']/div[1]/div[1]/div[1]/div[2]/input")
	WebElement username;

	@FindBy(xpath = "//div[@class='oxd-form-row user-password-row']/div[1]/div[1]/div[1]/div[2]/input")
	WebElement password;

	@FindBy(xpath = "//div[@class='oxd-form-row user-password-row']/div[1]/div[2]//input")
	WebElement confirmPassword;

	public void clickAddEmployee() {
		clickWaitElement(addEmployee);
	}

	public void clickCreateLoginDetailsCheckbox() {
		clickWaitElement(createLoginDetailsCheckbox);
	}

	public void toAddEmployee(String firstName, String middleName, String lastName, String employeeID) {
		sendKeysWithWait(this.firstName, firstName);
		sendKeysWithWait(this.middleName, middleName);
		sendKeysWithWait(this.lastName, lastName);
		jsExecuteScriptWithWait(this.employeeID, employeeID);
	}

	public void toCreateLoginDetails(String username, String password, String confirmPassword) {
		sendKeysWithWait(this.username, username);
		sendKeysWithWait(this.password, password);
		sendKeysWithWait(this.confirmPassword, confirmPassword);
	}

	public void clickUploadPhotoBtn() {
		clickWaitElement(uploadPhotoBtn);
	}

	public void clickSaveBtn() {
		clickWaitElement(saveBtn);
	}

	public boolean isPhotoUploaded() {
	    try {
	        String imgSrc = uploadedPhotoPreview.getAttribute("src");
	        return imgSrc.startsWith("data:image/jpeg;base64,");  
	    } catch (NoSuchElementException e) {
	        return false;  
	    }
	}

	public boolean isEmployeeAdded(String firstName, String lastName) {
		WebElement header = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='" + firstName + " " + lastName + "']")));

		return header.isDisplayed();
	}

	public boolean isCreateLoginUsernameDisplayed() {
		try {
			return username.isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

}
