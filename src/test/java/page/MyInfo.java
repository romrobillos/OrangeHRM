package page;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyInfo extends BasePage {

	@FindBy(xpath = "//span[normalize-space()='My Info']")
	WebElement myInfo;

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

	@FindBy(xpath = "//input[@class='oxd-input oxd-input--active'])[4]")
	WebElement pd_DLN;

	public MyInfo(WebDriver driver) {
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

	public WebElement getMyInfo() {
		return myInfo;
	}

	 
}
