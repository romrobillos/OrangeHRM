package page.MyInfo;

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

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/input")
	WebElement pd_licenseExpiryDate;

	@FindBy(xpath = "//div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]")
	WebElement pd_nationality;

	@FindBy(xpath = "//div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]")
	WebElement pd_maritalStatus;

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

	public String getMaritalStatusTxt() {
		return pd_maritalStatus.getText();
	}

}
