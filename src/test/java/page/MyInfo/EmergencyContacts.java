package page.MyInfo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.BasePage;

public class EmergencyContacts extends BasePage {

	@FindBy(xpath = "//a[normalize-space()='Emergency Contacts']")
	WebElement emergencyContacts;

	@FindBy(xpath = "//div[contains(@class,'content')]/div[1]/div[1]/button[contains(@class,'oxd-button')]")
	WebElement addEmergencyContactsBtn;

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[1]/div[1]/div[2]/input")
	WebElement ec_nameTxt;

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement ec_relationTxt;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[1]/div[1]/div[1]/div[2]/input")
	WebElement ec_homeTelephoneTxt;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement ec_mobileTxt;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[1]/div[3]/div[1]/div[2]/input")
	WebElement ec_workTelephoneTxt;

	@FindBy(xpath = "//button[normalize-space()='Cancel']")
	WebElement cancelBtn;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement saveBtn;

	public EmergencyContacts(WebDriver driver) {
		super(driver);
	}

	public void clickEmergencyContacts() {
		  emergencyContacts.click();;
	}

	public WebElement getAddEmergencyContactsBtn() {
		return addEmergencyContactsBtn;
	}

	public WebElement getEc_nameTxt() {
		return ec_nameTxt;
	}

	public WebElement getEc_relationTxt() {
		return ec_relationTxt;
	}

	public WebElement getEc_homeTelephoneTxt() {
		return ec_homeTelephoneTxt;
	}

	public WebElement getEc_mobileTxt() {
		return ec_mobileTxt;
	}

	public WebElement getEc_workTelephoneTxt() {
		return ec_workTelephoneTxt;
	}

	public void clickCancelBtn() {
		  cancelBtn.click();
	}

	public void clickSaveBtn() {
		  saveBtn.click();;
	}

	public boolean isEmergencyContactNameDisplayed() {
		try {
			return getEc_nameTxt().isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public boolean isEmergencyContactDisplayed(String name, String mobileNumber) {
		String contactXpath = "//div[contains(@class,'oxd-table-row')]//div[text()='" + name + "']";
		String mobileXpath = "//div[contains(@class,'oxd-table-row')]//div[text()='" + mobileNumber + "']";
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement nameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(contactXpath)));
		WebElement mobileElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mobileXpath)));
		return nameElement.isDisplayed() && mobileElement.isDisplayed();

	}

}
