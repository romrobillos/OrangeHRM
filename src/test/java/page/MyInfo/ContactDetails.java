package page.MyInfo;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.BasePage;

public class ContactDetails extends BasePage {

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[1]/div[1]/div[2]/input")
	WebElement cd_street1;

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement cd_street2;

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[3]/div[1]/div[2]/input")
	WebElement cd_city;

	@FindBy(xpath = "//div[@class='oxd-form-row']/div[1]/div[4]/div[1]/div[2]/input")
	WebElement cd_state;

	@FindBy(xpath = "//div[@class='oxd-form-row']/div[1]/div[5]/div[1]/div[2]/input")
	WebElement cd_zip;

	@FindBy(xpath = "//div[@class='oxd-form-row']/div[1]/div[6]/div[1]/div[2]/div[1]/div[1]/div[1]")
	WebElement cd_country;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[1]/div[1]/div[1]/div[2]/input")
	WebElement cd_homeNumber;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement cd_mobileNumber;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[1]/div[3]/div[1]/div[2]/input")
	WebElement cd_workNumber;

	@FindBy(xpath = "//form[@class='oxd-form']/div[3]/div[1]/div[1]/div[1]/div[2]/input")
	WebElement cd_workEmail;

	@FindBy(xpath = "//form[@class='oxd-form']/div[3]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement cd_otherEmail;

	@FindBy(xpath = "//a[normalize-space()='Contact Details']")
	WebElement contactDetails;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement cd_saveBtn;

	public WebElement getContactDetails() {							
		return contactDetails;
	}

	public WebElement getStreet1() {
		return cd_street1;
	}
	
	public WebElement getStreet2() {
		return cd_street2;
	}

	public WebElement getCity() {
		return cd_city;
	}

	public WebElement getState() {
		return cd_state;
	}

	public WebElement getZip() {
		return cd_zip;
	}

	public WebElement getCountry() {
		return cd_country;
	}

	public WebElement getHomeNumber() {
		return cd_homeNumber;
	}

	public WebElement getMobileNumber() {
		return cd_mobileNumber;
	}

	public WebElement getWorkNumber() {
		return cd_workNumber;
	}

	public WebElement getWorkEmail() {
		return cd_workEmail;
	}

	public WebElement getOtherEmail() {
		return cd_otherEmail;
	}

	public String getStreet1Value() {
		return cd_street1.getAttribute("value");
	}

	public String getStreet2Value() {
		return cd_street2.getAttribute("value");
	}

	public String getCityValue() {
		return cd_city.getAttribute("value");
	}

	public String getStateValue() {
		return cd_state.getAttribute("value");
	}

	public String getZipValue() {
		return cd_zip.getAttribute("value");
	}

	public String getCountryTxt() {
		return cd_country.getText();
	}

	public String getHomeNumberValue() {
		return cd_homeNumber.getAttribute("value");
	}

	public String getMobileNumberValue() {
		return cd_mobileNumber.getAttribute("value");
	}

	public String getWorkNumberValue() {
		return cd_workNumber.getAttribute("value");
	}

	public String getWorkEmailValue() {
		return cd_workEmail.getAttribute("value");
	}

	public String getOtherEmailValue() {
		return cd_otherEmail.getAttribute("value");
	}

	public WebElement getSaveBtn() {
		return cd_saveBtn;
	}

	public ContactDetails(WebDriver driver) {
		super(driver);
	}
	
	public void selectCountry(String country) {
		cd_country.click();
		int maxTries = 200;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getCountryTxt();

			if (highlightedText.equals(country)) {
				cd_country.sendKeys(Keys.ENTER);
				break;
			}
			cd_country.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Nationality not found: " + country);
		}
	}

}
