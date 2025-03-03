package page.MyInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
		clickWaitElement(emergencyContacts);
	}

	public void clickAddEmergencyContactsBtn() {
		clickWaitElement(addEmergencyContactsBtn);
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
		clickWaitElement(cancelBtn);
	}

	public boolean isEmergencyContactNameDisplayed() {
		try {
			return ec_nameTxt.isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public boolean isEmergencyContactDisplayed(String name, String mobileNumber) {
		String contactXpath = "//div[contains(@class,'oxd-table-row')]//div[text()='" + name + "']";
		String mobileXpath = "//div[contains(@class,'oxd-table-row')]//div[text()='" + mobileNumber + "']";
		WebElement nameElement = driver.findElement(By.xpath(contactXpath));
		WebElement mobileElement = driver.findElement(By.xpath(mobileXpath));

		return waitForElement(nameElement).isDisplayed() && waitForElement(mobileElement).isDisplayed();

	}

	public void fillOutEC(String name, String mobile, String relationship) {
		sendKeysWithWait(ec_nameTxt, name);
		sendKeysWithWait(ec_mobileTxt, mobile);
		sendKeysWithWait(ec_relationTxt, relationship);
		clickWaitElement(saveBtn);
	}

}
