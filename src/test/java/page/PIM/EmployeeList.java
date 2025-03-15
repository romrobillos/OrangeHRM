package page.PIM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page.BasePage;

public class EmployeeList extends BasePage {
	
	public EmployeeList(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (xpath = "//a[normalize-space()='Employee List']")
	WebElement pim_employeeList;
	
	@FindBy (xpath = "(//input[contains(@placeholder,'Type')])[1]")
	WebElement employeeName;
	
	@FindBy (xpath = "(//input[contains(@class,'oxd-input')])[2]")
	WebElement employeeId;
	
	@FindBy (xpath = "(//div[@class='oxd-select-wrapper']//div[contains(@class,'text-input')])[1]")
	WebElement employmentStatus;

	@FindBy (xpath = "(//div[@class='oxd-select-wrapper']//div[contains(@class,'text-input')])[2]")
	WebElement include;
	
	@FindBy (xpath = "(//input[contains(@placeholder,'Type')])[2]")
	WebElement supervisorName;
	
	@FindBy (xpath = "(//div[@class='oxd-select-wrapper']//div[contains(@class,'text-input')])[3]")
	WebElement jobTitle;
	
	@FindBy (xpath = "(//div[@class='oxd-select-wrapper']//div[contains(@class,'text-input')])[4]")
	WebElement subUnit;
	
	@FindBy (xpath = "//button[normalize-space()='Search']")
	WebElement searchBtn;
	
	
	
	public void clickEmployeeList() {
	clickWaitElement(pim_employeeList);
	}
	
	public WebElement getEmployeeName() {
		return waitForElement(employeeName);
	}
	
	public void clickSearchBtn() {
		clickWaitElement(searchBtn);
	}
	
	public void searchEmployeeName(String employeeName) { //Manual Search
		WebElement candidateNameInput = getEmployeeName();

		candidateNameInput.sendKeys(Keys.CONTROL + "a");
		candidateNameInput.sendKeys(Keys.BACK_SPACE);

		candidateNameInput.sendKeys(employeeName);

		clickSearchBtn();
	}
	
	public void searchEmployeeNameThruAutoSuggest(String fullName) {
		sendKeysWithWait(employeeName, fullName);
		selectEmployeeNameFromSuggestion1(fullName);
		clickSearchBtn();
	}
	
	public void selectEmployeeNameFromSuggestion1(String name) {

		List<WebElement> suggestions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//div[@role='listbox']/div/span[contains(normalize-space(text()), '"+name+"')]")));

		System.out.println("Total Suggestions Found: " + suggestions.size());

		if (!suggestions.isEmpty()) {
			System.out.println("Selecting: " + suggestions.get(0).getText());
			suggestions.get(0).click(); // Click the first suggestion
		} else {
			System.out.println("No matching suggestion found for: " + name);
		}
	}
	
	public boolean isEmployeeNameFiltered(String employeeName) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(normalize-space(text()),'"
					+ employeeName + "')]";

			try {
				WebElement nameElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = nameElement.getText();
				System.out.println("Row " + i + " Employee Name: " + actValue);
				if (actValue.contains(employeeName)) {
					flag = true;
					break;
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found in row " + i);
			}
		}
		return flag;
	}
	
}
