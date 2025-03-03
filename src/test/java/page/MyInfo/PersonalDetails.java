package page.MyInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.BasePage;

public class PersonalDetails extends BasePage {

	@FindBy(xpath = "///a[normalize-space()='Personal Details']")
	WebElement personalDetails;

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement pd_firstname;

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/input")
	WebElement pd_middlename;

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/input")
	WebElement pd_lastname;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[1]/div[1]/div[1]/div[2]/input")
	WebElement pd_employeeID;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement pd_otherID;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[2]/div[1]/div[1]/div[2]/input")
	WebElement pd_DLN;

	@FindBy(xpath = "//div[@class='oxd-form-row']/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/input")
	WebElement pd_licenseExpiryDate;

	@FindBy(xpath = "//div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]")
	WebElement pd_nationality;

	@FindBy(xpath = "//div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]")
	WebElement pd_maritalStatus;

	@FindBy(xpath = "//div[@class='oxd-form-row']/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/input")
	WebElement pd_DOB;

	@FindBy(xpath = "//div[contains(@class,'horizontal')]//button[@type='submit'][normalize-space()='Save']")
	WebElement pd_saveBtn;

	public PersonalDetails(WebDriver driver) {
		super(driver);
	}

	public void fillOutPD(String fname, String mname, String lname, String id, String otherID, String dln, String led,
			String nationality, String marital, String dob, String gender) {
		jsExecuteScriptWithWait(pd_firstname, fname);
		jsExecuteScriptWithWait(pd_middlename, mname);
		jsExecuteScriptWithWait(pd_lastname, lname);
		jsExecuteScriptWithWait(pd_employeeID, id);
		jsExecuteScriptWithWait(pd_otherID, otherID);
		jsExecuteScriptWithWait(pd_DLN, dln);
		jsExecuteScriptWithWait(pd_licenseExpiryDate, led);
		selectNationality(nationality);
		selectMaritalStatus(marital);
		jsExecuteScriptWithWait(pd_DOB, dob);
		selectGender(gender);
		clickSaveBtn();
		
	}

	public void clickPersonalDetails() {
		clickWaitElement(personalDetails);
	}

	public String getFirstnameValue() {
		waitForElement(pd_firstname);
		return pd_firstname.getAttribute("value");
	}

	public String getMiddlenameValue() {
		waitForElement(pd_middlename);
		return pd_middlename.getAttribute("value");
	}

	public String getLastnameValue() {
		waitForElement(pd_lastname);
		return pd_lastname.getAttribute("value");
	}

	public String getEmployeeIdValue() {
		waitForElement(pd_employeeID);
		return pd_employeeID.getAttribute("value");
	}

	public String getOtherIdValue() {
		waitForElement(pd_otherID);
		return pd_otherID.getAttribute("value");
	}

	public String getDLNValue() {
		waitForElement(pd_DLN);
		return pd_DLN.getAttribute("value");
	}

	public String getLicenseExpiryDateValue() {
		waitForElement(pd_licenseExpiryDate);
		return pd_licenseExpiryDate.getAttribute("value");
	}

	public String getNationalityTxt() {
		waitForElement(pd_nationality);
		return pd_nationality.getText();
	}
	
	public String getDOBValue() {
		waitForElement(pd_DOB);
		return pd_DOB.getAttribute("value");
	}
	
	public void clickSaveBtn() {
		clickWaitElement(pd_saveBtn);
	}

	public void selectNationality(String nationality) {
		pd_nationality.click();
		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getNationalityTxt();

			if (highlightedText.equals(nationality)) {
				pd_nationality.sendKeys(Keys.ENTER);
				break;
			}
			pd_nationality.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Nationality not found: " + nationality);
		}
	}

	public String getMaritalStatusTxt() {
		waitForElement(pd_maritalStatus);
		return pd_maritalStatus.getText();
	}

	public void selectMaritalStatus(String maritalStatus) {
		clickWaitElement(pd_maritalStatus);
		int maxTries = 10;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getMaritalStatusTxt();

			if (highlightedText.equals(maritalStatus)) {
				pd_maritalStatus.sendKeys(Keys.ENTER);
				break;
			}
			pd_maritalStatus.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Marital Status not found: " + maritalStatus);
		}
	}

	public void selectGender(String actualGender) {
		String xpath = "//label[normalize-space()='" + actualGender + "']//preceding-sibling::input";
		WebElement genderRadioButton = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderRadioButton);

	}

	public boolean isGenderSelected(String expectedGender) {
		String xpath = "//label[normalize-space()='" + expectedGender + "']//preceding-sibling::input";
		WebElement genderRadioButton = driver.findElement(By.xpath(xpath));
		return genderRadioButton.isSelected();
	}
}
