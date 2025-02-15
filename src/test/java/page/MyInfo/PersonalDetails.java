package page.MyInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.BasePage;

public class PersonalDetails extends BasePage {

	@FindBy(xpath = "///a[normalize-space()='Personal Details']")
	WebElement personalDetails;

	@FindBy(xpath = "//input[@placeholder='First Name']")
	WebElement pd_firstname;

	@FindBy(xpath = "//input[@placeholder='Middle Name']")
	WebElement pd_middlename;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
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

	@FindBy(xpath = "//label[normalize-space()='Male']")
	WebElement pd_Gender;

	@FindBy(xpath = "//div[contains(@class,'horizontal')]//button[@type='submit'][normalize-space()='Save']")
	WebElement pd_saveBtn;

	public PersonalDetails(WebDriver driver) {
		super(driver);
	}

	public WebElement getPersonalDetails() {
		return personalDetails;
	}

	public WebElement getFirstname() {
		return pd_firstname;
	}

	public WebElement getMiddlename() {
		return pd_middlename;
	}

	public WebElement getLastname() {
		return pd_lastname;
	}

	public WebElement getEmployeeId() {
		return pd_employeeID;
	}

	public WebElement getOtherId() {
		return pd_otherID;
	}

	public WebElement getDLN() {
		return pd_DLN;
	}

	public WebElement getLicenseExpiryDate() {
		return pd_licenseExpiryDate;
	}

	public WebElement getNationality() {
		return pd_nationality;
	}

	public WebElement getMaritalStatus() {
		return pd_maritalStatus;
	}

	public WebElement getDOB() {
		return pd_DOB;
	}

	public WebElement getSaveBtn() {
		return pd_saveBtn;
	}

	public String getFirstnameValue() {
		return pd_firstname.getAttribute("value");
	}

	public String getMiddlenameValue() {
		return pd_middlename.getAttribute("value");
	}

	public String getLastnameValue() {
		return pd_lastname.getAttribute("value");
	}

	public String getEmployeeIdValue() {
		return pd_employeeID.getAttribute("value");
	}

	public String getOtherIdValue() {
		return pd_otherID.getAttribute("value");
	}

	public String getDLNValue() {
		return pd_DLN.getAttribute("value");
	}

	public String getLicenseExpiryDateValue() {
		return pd_licenseExpiryDate.getAttribute("value");
	}

	public String getNationalityTxt() {
		return pd_nationality.getText();
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
		return pd_maritalStatus.getText();
	}

	public void selectMaritalStatus(String maritalStatus) {
		pd_maritalStatus.click();
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

	public String getDOBValue() {
		return pd_DOB.getAttribute("value");
	}

	public void selectGender(String gender) {
		String xpath = "//label[normalize-space()='" + gender + "']";
		WebElement genderRadioButton = driver.findElement(By.xpath(xpath));

		if (!genderRadioButton.isSelected()) {
			genderRadioButton.click();
		}
	}
	public boolean isGenderSelected(String gender) {
	    String xpath = "//label[normalize-space()='" + gender + "']";
	    WebElement genderRadioButton = driver.findElement(By.xpath(xpath));
	    return genderRadioButton.isSelected();
	}
}
