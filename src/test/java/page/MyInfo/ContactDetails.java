package page.MyInfo;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.BasePage;

public class ContactDetails extends BasePage {

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[1]/div[1]/div[2]/input")
	WebElement street1;

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement street2;

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[3]/div[1]/div[2]/input")
	WebElement city;

	@FindBy(xpath = "//div[@class='oxd-form-row']/div[1]/div[4]/div[1]/div[2]/input")
	WebElement state;

	@FindBy(xpath = "//div[@class='oxd-form-row']/div[1]/div[5]/div[1]/div[2]/input")
	WebElement zip;

	@FindBy(xpath = "//div[@class='oxd-form-row']/div[1]/div[6]/div[1]/div[2]/div[1]/div[1]/div[1]")
	WebElement country;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[1]/div[1]/div[1]/div[2]/input")
	WebElement homeNumber;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement mobileNumber;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[1]/div[3]/div[1]/div[2]/input")
	WebElement workNumber;

	@FindBy(xpath = "//form[@class='oxd-form']/div[3]/div[1]/div[1]/div[1]/div[2]/input")
	WebElement workEmail;

	@FindBy(xpath = "//form[@class='oxd-form']/div[3]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement otherEmail;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath = "//a[normalize-space()='Contact Details']")
	WebElement contactDetails;
	
	public WebElement getContactDetails() {
		return contactDetails;
	}
	
	public WebElement getStreet1() {
		return street1;
	}

	public String getStreet1Value() {
		return street1.getAttribute("value");
	}

	public String getStreet2Value() {
		return street2.getAttribute("value");
	}

	public String getCityValue() {
		return city.getAttribute("value");
	}

	public String getStateValue() {
		return state.getAttribute("value");
	}

	public String getZipValue() {
		return zip.getAttribute("value");
	}

	public String getCountryTxt() {
		return country.getText();
	}

	public String getHomeNumberValue() {
		return homeNumber.getAttribute("value");
	}

	public String getMobileNumberValue() {
		return mobileNumber.getAttribute("value");
	}

	public String getWorkNumberValue() {
		return workNumber.getAttribute("value");
	}

	public String getWorkEmailValue() {
		return workEmail.getAttribute("value");
	}

	public String getOtherEmailValue() {
		return otherEmail.getAttribute("value");
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public ContactDetails(WebDriver driver) {
		super(driver);

	}

}
