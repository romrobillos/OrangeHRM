package page.MyInfo;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.BasePage;

public class ContactDetails extends BasePage {

	public ContactDetails(WebDriver driver) {
		super(driver);
	}
	
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
	WebElement cd_homenumber;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement cd_mobilenumber;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[1]/div[3]/div[1]/div[2]/input")
	WebElement cd_worknumber;

	@FindBy(xpath = "//form[@class='oxd-form']/div[3]/div[1]/div[1]/div[1]/div[2]/input")
	WebElement cd_workemail;

	@FindBy(xpath = "//form[@class='oxd-form']/div[3]/div[1]/div[2]/div[1]/div[2]/input")
	WebElement cd_otheremail;

	@FindBy(xpath = "//a[normalize-space()='Contact Details']")
	WebElement contactDetails;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement cd_saveBtn;

	public void clickContactDetails() {
		clickWaitElement(contactDetails);
	}

	public String getStreet1Value() {
		waitForElement(cd_street1);
		return cd_street1.getAttribute("value");
	}

	public String getStreet2Value() {
		return cd_street2.getAttribute("value");
	}

	public String getCityValue() {
		waitForElement(cd_city);
		return cd_city.getAttribute("value");
	}

	public String getStateValue() {
		waitForElement(cd_state);
		return cd_state.getAttribute("value");
	}

	public String getZipValue() {
		waitForElement(cd_zip);
		return cd_zip.getAttribute("value");
	}

	public String getCountryTxt() {
		waitForElement(cd_country);
		return cd_country.getText();
	}

	public String getHomeNumberValue() {
		waitForElement(cd_homenumber);
		return cd_homenumber.getAttribute("value");
	}

	public String getMobileNumberValue() {
		waitForElement(cd_mobilenumber);
		return cd_mobilenumber.getAttribute("value");
	}

	public String getWorkNumberValue() {
		waitForElement(cd_worknumber);
		return cd_worknumber.getAttribute("value");
	}

	public String getWorkEmailValue() {
		waitForElement(cd_workemail);
		return cd_workemail.getAttribute("value");
	}

	public String getOtherEmailValue() {
		waitForElement(cd_otheremail);
		return cd_otheremail.getAttribute("value");
	}

	public void selectCountry(String country) {
		clickWaitElement(cd_country);
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

	public void fillOutCD(String street1, String street2, String city, String state, String zip, String country,
			String homenumber, String mobilenumber, String worknumber, String workemail, String otheremail) {
		jsExecuteScriptWithWait(cd_street1, street1);
		jsExecuteScriptWithWait(cd_street2, street2);
		jsExecuteScriptWithWait(cd_city, city);
		jsExecuteScriptWithWait(cd_zip, zip);
		jsExecuteScriptWithWait(cd_state, state);
		selectCountry(country);
		jsExecuteScriptWithWait(cd_homenumber, homenumber);
		jsExecuteScriptWithWait(cd_mobilenumber, mobilenumber);
		jsExecuteScriptWithWait(cd_worknumber, worknumber);
		jsExecuteScriptWithWait(cd_workemail, workemail);
		jsExecuteScriptWithWait(cd_otheremail, otheremail);

		clickWaitElement(cd_saveBtn);

	}

}
