package page.PIM;

import org.openqa.selenium.By;
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
	
	
	public void clickAddEmployee() {
		clickWaitElement(addEmployee);
	}

	public void toAddEmployee(String firstName, String middleName, String lastName, String employeeID) {
		sendKeysWithWait(this.firstName, firstName);
		sendKeysWithWait(this.middleName, middleName);
		sendKeysWithWait(this.lastName, lastName);
		jsExecuteScriptWithWait(this.employeeID, employeeID);
		saveBtn.click();
	}

	public boolean isItAdded(String firstName, String lastName) {
		WebElement header = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='" + firstName + " " + lastName + "']")));
		
		return header.isDisplayed();
	}

}
