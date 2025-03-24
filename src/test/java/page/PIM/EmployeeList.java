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

	@FindBy(xpath = "//a[normalize-space()='Employee List']")
	WebElement pim_employeeList;

	@FindBy(xpath = "(//input[contains(@placeholder,'Type')])[1]")
	WebElement employeeName;

	@FindBy(xpath = "(//input[contains(@class,'oxd-input')])[2]")
	WebElement employeeId;

	@FindBy(xpath = "(//div[@class='oxd-select-wrapper']//div[contains(@class,'text-input')])[1]")
	WebElement status;

	@FindBy(xpath = "(//div[@class='oxd-select-wrapper']//div[contains(@class,'text-input')])[2]")
	WebElement include;

	@FindBy(xpath = "(//input[contains(@placeholder,'Type')])[2]")
	WebElement supervisorName;

	@FindBy(xpath = "(//div[@class='oxd-select-wrapper']//div[contains(@class,'text-input')])[3]")
	WebElement jobTitle;

	@FindBy(xpath = "(//div[@class='oxd-select-wrapper']//div[contains(@class,'text-input')])[4]")
	WebElement subUnit;

	@FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement searchBtn;

	@FindBy(xpath = "//button[normalize-space()='Reset']")
	WebElement resetBtn;

	@FindBy(xpath = "//button[normalize-space()='Add']")
	WebElement addBtn;

	public void clickEmployeeList() {
		clickWaitElement(pim_employeeList);
	}

	public WebElement getEmployeeName() {
		return waitForElement(employeeName);
	}

	public WebElement getEmployeeID() {
		return waitForElement(employeeId);
	}

	public WebElement getSupervisorName() {
		return waitForElement(supervisorName);
	}

	public void clickSearchBtn() {
		clickWaitElement(searchBtn);
	}

	public void clickResetBtn() {
		clickWaitElement(resetBtn);
	}

	public void clickAddBtn() {
		clickWaitElement(addBtn);
	}

	// Employee Method

	public void manualSearchEmployeeName(String employeeName) { // Manual Search
		WebElement candidateNameInput = getEmployeeName();

		candidateNameInput.sendKeys(Keys.CONTROL + "a");
		candidateNameInput.sendKeys(Keys.BACK_SPACE);

		candidateNameInput.sendKeys(employeeName);
		clickSearchBtn();

	}

	public void searchAndSelectEmployeeNameThruAutoSuggest1(String employeeName) {
		sendKeysWithWait(this.employeeName, employeeName);

		// Wait for suggestions to appear
		List<WebElement> suggestions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
				.xpath("//div[@role='listbox']/div/span[contains(normalize-space(text()), '" + employeeName + "')]")));

		System.out.println("Total Suggestions Found: " + suggestions.size());

		if (!suggestions.isEmpty()) {
			System.out.println("Selecting: " + suggestions.get(0).getText());
			suggestions.get(0).click();
		} else {
			System.out.println("No matching suggestion found for: " + employeeName);
		}

		clickSearchBtn();
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
				System.out.println("Full employee name not found in row " + i + ". Checking partial match...");

				String[] nameParts = employeeName.split("\\s+");
				for (String part : nameParts) {
					String partialXpath = "//div[@class='oxd-table-body']/div[" + i
							+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(normalize-space(text()),'"
							+ part + "')]";

					try {
						WebElement partialElement = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(partialXpath)));

						String partialValue = partialElement.getText();
						System.out.println("Row " + i + " Partial Match Found: " + partialValue);

						if (partialValue.contains(part)) {
							flag = true;

						}
					} catch (TimeoutException ex) {
						System.out.println("Partial match not found for '" + part + "' in row " + i);
					}
				}
			}
			if (flag)
				break;
		}
		return flag;
	}

	// Employee ID method

	public void searchEmployeeID(String id) { // Manual Search
		WebElement candidateIDInput = getEmployeeID();

		candidateIDInput.sendKeys(Keys.CONTROL + "a");
		candidateIDInput.sendKeys(Keys.BACK_SPACE);

		candidateIDInput.sendKeys(id);
		clickSearchBtn();

	}

	public boolean isEmployeeIDFiltered(String id) {

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
		System.out.println("Total rows found: " + rows.size());
		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(text(),'" + id + "')]";

			try {
				WebElement statusElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = statusElement.getText();
				System.out.println("Row " + i + ": " + actValue);
				if (actValue.contains(id)) {
					flag = true;
					break;
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found in row " + i);
			}
		}

		return flag;
	}

	// Employment status

	public String getStatusTxt() {
		return status.getText();
	}

	public void searchStatus(String status) {
		clickWaitElement(this.status);

		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getStatusTxt();

			if (highlightedText.equals(status)) {
				this.status.sendKeys(Keys.ENTER);
				clickSearchBtn();
				break;
			}
			this.status.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + status);
		}
	}

	public boolean isStatusFiltered(String status) {

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));

		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(text(),'" + status + "')]";
			try {
				WebElement statusElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = statusElement.getText();

				if (actValue.equals(status)) {
					flag = true;
					break;
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found in row " + i);
			}
		}

		return flag;
	}

	// Supervisor Name

	public void manualSearchSupervisorName(String supervisorName) { // Manual Search
		WebElement candidateNameInput = getSupervisorName();

		candidateNameInput.sendKeys(Keys.CONTROL + "a");
		candidateNameInput.sendKeys(Keys.BACK_SPACE);

		candidateNameInput.sendKeys(supervisorName);
		clickSearchBtn();

	}

	public void searchAndSelectSupervisorNameThruAutoSuggest1(String supervisorName) {
		sendKeysWithWait(this.supervisorName, supervisorName);

		// Wait for suggestions to appear
		List<WebElement> suggestions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
				"//div[@role='listbox']/div/span[contains(normalize-space(text()), '" + supervisorName + "')]")));

		System.out.println("Total Suggestions Found: " + suggestions.size());

		if (!suggestions.isEmpty()) {
			System.out.println("Selecting: " + suggestions.get(0).getText());
			suggestions.get(0).click();
		} else {
			System.out.println("No matching suggestion found for: " + supervisorName);
		}

		clickSearchBtn();
	}

	public boolean isSupervisorNameFiltered(String supervisorName) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(normalize-space(text()),'"
					+ supervisorName + "')]";

			try {
				WebElement supervisorNameElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = supervisorNameElement.getText();
				System.out.println("Row " + i + " Employee Name: " + actValue);
				if (actValue.contains(supervisorName)) {
					flag = true;
					break;
				}
			} catch (TimeoutException e) {
				System.out.println("Full employee name not found in row " + i + ". Checking partial match...");

				String[] nameParts = supervisorName.split("\\s+");
				for (String part : nameParts) {
					String partialXpath = "//div[@class='oxd-table-body']/div[" + i
							+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(normalize-space(text()),'"
							+ part + "')]";

					try {
						WebElement partialElement = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(partialXpath)));

						String partialValue = partialElement.getText();
						System.out.println("Row " + i + " Partial Match Found: " + partialValue);

						if (partialValue.contains(part)) {
							flag = true;

						}
					} catch (TimeoutException ex) {
						System.out.println("Partial match not found for '" + part + "' in row " + i);
					}
				}
			}
			if (flag)
				break;
		}
		return flag;
	}

	// Job Title

	public String getJobtitleTxt() {
		return jobTitle.getText();
	}

	public void searchJobtitle(String jobTitle) {
		clickWaitElement(this.jobTitle);

		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getJobtitleTxt();

			if (highlightedText.equals(jobTitle)) {
				this.jobTitle.sendKeys(Keys.ENTER);
				clickSearchBtn();
				break;
			}
			this.jobTitle.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + jobTitle);
		}
	}

	public boolean isJobtitleFiltered(String jobtitle) {

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));

		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(text(),'" + jobtitle + "')]";
			try {
				WebElement jobtitleElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = jobtitleElement.getText();

				if (actValue.equals(jobtitle)) {
					flag = true;
					break;
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found in row " + i);
			}
		}

		return flag;
	}

	// Sub Unit

	public String getSubUnitTxt() {
		return subUnit.getText();
	}

	public void searchSubUnit(String subUnit) {
		clickWaitElement(this.subUnit);

		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getSubUnitTxt();

			if (highlightedText.equals(subUnit)) {
				this.subUnit.sendKeys(Keys.ENTER);
				clickSearchBtn();
				break;
			}
			this.subUnit.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + subUnit);
		}
	}

	public boolean isSubUnitFiltered(String subUnit) {

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));

		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(text(),'" + subUnit + "')]";
			try {
				WebElement subUnitElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = subUnitElement.getText();

				if (actValue.equals(subUnit)) {
					flag = true;
					break;
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found in row " + i);
			}
		}

		return flag;
	}

	public boolean isAtAddEmployee() {
		addBtn.click();
		WebElement AddEmployeeHeader = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='Add Employee']")));
		return AddEmployeeHeader.isDisplayed();
	}

}
