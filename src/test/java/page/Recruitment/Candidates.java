package page.Recruitment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page.BasePage;

public class Candidates extends BasePage {

	public Candidates(WebDriver driver) {
		super(driver);
	}

	// Candidates

	@FindBy(xpath = "//a[normalize-space()='Candidates']")
	WebElement candidatesSubTab;

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[1]/div[1]/div[2]/div/div/div[1]")
	WebElement candidates_jobTitle;

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[2]/div[1]/div[2]/div/div/div[1]")
	WebElement candidates_vacancy;

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[3]/div[1]/div[2]/div/div/div[1]")
	WebElement candidates_hiringManager;

	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/div[1]/div[4]/div[1]/div[2]/div/div/div[1]")
	WebElement candidates_status;

	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	WebElement candidates_candidateName;

	@FindBy(xpath = "//input[@placeholder='Enter comma seperated words...']")
	WebElement candidates_keywords;

	@FindBy(xpath = "//input[@placeholder='From']")
	WebElement candidates_dateFrom;

	@FindBy(xpath = "//input[@placeholder='To']")
	WebElement candidates_dateTo;

	@FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement candidates_searchBtn;

	@FindBy(xpath = "//button[normalize-space()='Reset']")
	WebElement candidates_reset;

	public void clickCandidatesSubTab() {
		clickWaitElement(candidatesSubTab);
	}

	public WebElement getCandidateName() {
		return waitForElement(candidates_candidateName);
	}

	public WebElement getdateFrom() {
		return candidates_dateFrom;
	}

	public WebElement getdateTo() {
		return candidates_dateTo;
	}

	public void clickSearch() {
		clickWaitElement(candidates_searchBtn);
	}

	public void clickReset() {
		clickWaitElement(candidates_reset);
	}

	public String getHiringManagerTxt() {
		return candidates_hiringManager.getText();
	}

	public String getStatusTxt() {
		return candidates_status.getText();
	}

	public String getVacancyTxt() {
		return candidates_vacancy.getText();
	}

	// Table

	@FindBy(xpath = "//button[normalize-space()='Add']")
	WebElement table_add;

	@FindBy(xpath = "//div[@class='oxd-table-body']/div[1]/div[1]/div[7]/div/button[1]")
	WebElement actionViewProfileRow1;

	@FindBy(xpath = "//div[@class='oxd-table-body']/div[1]/div[1]/div[7]/div/button[2]")
	WebElement actionDeleteProfileRow1;

	@FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
	WebElement action_popUpDeleteBtn;

	@FindBy(xpath = "//div[@class='oxd-table-header']//label/span")
	WebElement table_checkbox;

	// Add Candidate
	@FindBy(xpath = "//input[@placeholder='First Name']")
	WebElement addCandidate_firstname;

	@FindBy(xpath = "//input[@placeholder='Middle Name']")
	WebElement addCandidate_middlename;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	WebElement addCandidate_lastname;

	@FindBy(xpath = "//div[@class='oxd-select-text-input']")
	WebElement addCandidate_vacancy;

	@FindBy(xpath = "//div[3]//div[1]//div[1]//div[1]//div[2]//input[1]")
	WebElement addCandidate_email;

	@FindBy(xpath = "//form[@class='oxd-form']/div[3]/div[1]/div[2]//input")
	WebElement addCandidate_contactnumber;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement addCandidate_saveBtn;

	@FindBy(xpath = "//button[normalize-space()='Cancel']")
	WebElement addCandidate_cancel;

	public void clickAdd() {
		table_add.click();
	}

	public void clickHeaderCheckbox() {
		clickWaitElement(table_checkbox);
	}

	public void viewProfileRow1() {
		clickWaitElement(actionViewProfileRow1);
	}

	public void deleteProfileRow1() {
		clickWaitElement(actionDeleteProfileRow1);
		clickWaitElement(action_popUpDeleteBtn);
	}

	public void searchVacancy(String vacancy) {
		clickWaitElement(candidates_vacancy);

		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getVacancyTxt();

			if (highlightedText.equals(vacancy)) {
				candidates_vacancy.sendKeys(Keys.ENTER);
				clickWaitElement(candidates_searchBtn);
				break;
			}
			candidates_vacancy.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + vacancy);
		}
	}

	public boolean isVacancyFiltered(String vacancy) {

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));

		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(text(),'" + vacancy + "')]";
			try {
				WebElement vacancyElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = vacancyElement.getText();

				if (actValue.equals(vacancy)) {
					flag = true;
					break;
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found in row " + i);
			}
		}

		return flag;
	}

	public void searchHiringManager(String manager) {
		clickWaitElement(candidates_hiringManager);

		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getHiringManagerTxt();

			if (highlightedText.contains(manager)) {
				candidates_hiringManager.sendKeys(Keys.ENTER);
				clickWaitElement(candidates_searchBtn);
				break;
			}
			candidates_hiringManager.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + manager);
		}
	}

	public boolean isHiringManagerFiltered(String manager) {

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
		System.out.println("Total rows found: " + rows.size());
		boolean flag = false;
		String label = manager.split(" ")[0];

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(text(),'" + label + "')]";

			try {
				WebElement managerElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = managerElement.getText();
				System.out.println("Row " + i + ": " + actValue);
				if (actValue.contains(manager)) {
					flag = true;
					break;
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found in row " + i);
			}
		}

		return flag;
	}

	public void searchStatus(String status) {
		clickWaitElement(candidates_status);

		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getStatusTxt();

			if (highlightedText.contains(status)) {
				candidates_status.sendKeys(Keys.ENTER);
				clickWaitElement(candidates_searchBtn);
				break;
			}
			candidates_status.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + status);
		}
	}

	public void searchDate(String DateFrom, String DateTo) {
		sendKeysWithWait(candidates_dateFrom, DateFrom);
		sendKeysWithWait(candidates_dateTo, DateTo);
		clickWaitElement(candidates_searchBtn);
	}

	public boolean isStatusFiltered(String status) {

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
		System.out.println("Total rows found: " + rows.size());
		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(text(),'" + status + "')]";

			try {
				WebElement statusElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = statusElement.getText();
				System.out.println("Row " + i + ": " + actValue);
				if (actValue.contains(status)) {
					flag = true;
					break;
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found in row " + i);
			}
		}

		return flag;
	}

	public void searchCandidateByNameThruManual(String testName) {
		WebElement candidateNameInput = getCandidateName();

		candidateNameInput.sendKeys(Keys.CONTROL + "a");
		candidateNameInput.sendKeys(Keys.BACK_SPACE);

		candidateNameInput.sendKeys(testName);

		clickSearch();
	}

	public void searchAndSelectCandidateByNameThruAutoSuggest(String fullName) {
		String firstName = fullName.split(" ")[0]; // Extract first name
		sendKeysWithWait(candidates_candidateName, firstName);

		// Wait for and fetch suggestions
		List<WebElement> suggestions = driver.findElements(
				By.xpath("//div[@role='listbox']/div/span[contains(normalize-space(text()), '" + fullName + "')]"));

		System.out.println("Total Suggestions Found: " + suggestions.size());

		if (!suggestions.isEmpty()) {
			System.out.println("Selecting: " + suggestions.get(0).getText());
			suggestions.get(0).click(); // Click first matching suggestion
		} else {
			System.out.println("No matching suggestion found for: " + fullName);
		}

		clickSearch(); // Perform search after selecting
	}

	public boolean isManualSearchGotFilteredOrInvalid(String name) {
		List<WebElement> tableResults = driver
				.findElements(By.xpath("//div[@class='oxd-table-body']//div[@role='row']/div[3]"));

		boolean invalid = false;
		try {
			WebElement errorMessage = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//div[contains(@class,'text-input--error')]")));
			invalid = errorMessage.isDisplayed();
		} catch (TimeoutException e) {
			invalid = false;
		}

		boolean allMatch = true;
		for (WebElement row : tableResults) {
			String rowText = row.getText().trim();
			if (!rowText.equalsIgnoreCase(name)) {
				allMatch = false;
				break;
			}
		}

		return invalid || !allMatch;
	}

	public boolean isCandidateNameFiltered(String name) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(normalize-space(text()),'"
					+ name + "')]";

			try {
				WebElement nameElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = nameElement.getText();
				System.out.println("Row " + i + " Candidate Name: " + actValue);
				if (actValue.contains(name)) {
					flag = true;
					break;
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found in row " + i);
			}
		}

		return flag;
	}

	public boolean isDateOfApplicationWithinRange() {

		String dateFromText = candidates_dateFrom.getAttribute("value").trim();
		String dateToText = candidates_dateTo.getAttribute("value").trim();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateFrom = LocalDate.parse(dateFromText, formatter);
		LocalDate dateTo = LocalDate.parse(dateToText, formatter);

		List<WebElement> dateElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-cell')][5]")));

		System.out.println("Number of 'Date of Application' elements found: " + dateElements.size());

		if (dateElements.isEmpty()) {
			System.out.println("No matching dates found in the table.");
			return false;
		}

		for (int i = 0; i < dateElements.size(); i++) {
			WebElement dateElement = dateElements.get(i);

			String dateText = dateElement.getText().trim();
			System.out.println("Row " + (i + 1) + ": Raw date text - " + dateText);

			try {
				LocalDate applicationDate = LocalDate.parse(dateText, formatter);

				boolean isWithinRange = (applicationDate.isEqual(dateFrom) || applicationDate.isAfter(dateFrom))
						&& (applicationDate.isEqual(dateTo) || applicationDate.isBefore(dateTo));

				System.out.println("Checking: " + applicationDate + " is within range: " + dateFrom + " to " + dateTo
						+ " -> Result: " + isWithinRange);

				if (!isWithinRange) {
					return false;
				}
			} catch (DateTimeParseException e) {
				System.out.println("Skipping row " + (i + 1) + " due to invalid date format: " + dateText);
			}
		}

		return true;
	}

	public boolean verifySelectAllCheckbox() {

		List<WebElement> rowCheckboxes = wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']//input[@type='checkbox']")));

		boolean allChecked = true;
		for (WebElement checkbox : rowCheckboxes) {
			if (!checkbox.isSelected()) {
				allChecked = false;
				System.out.println("Checkbox not selected: " + checkbox);
			}
		}

		if (allChecked) {
			System.out.println("All checked!");
		} else {
			System.out.println("Some checkboxes are NOT selected.");
		}

		return allChecked;
	}

	public boolean isProfileEqualToAddedOrRow1Name(String name) {
		String stageXpath = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
						"//div[@class='oxd-input-group']/div[2]/p[contains(normalize-space(.), '" + name + "')]")))
				.getText();
		System.out.println("Application Stage Candidate Name: " + stageXpath);

		boolean isItEqual = name.equals(stageXpath);
		return isItEqual;

	}

	public String getRow1Name() {
		String row1NameXpath = wait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//div[@class='oxd-table-body']/div[1]//div[contains(@class,'oxd-table-cell')][3]")))
				.getText();
		return row1NameXpath;
	}

	public String getRow1NameAndDate() {
		List<WebElement> row1NameAndDate = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//div[@class='oxd-table-body']/div[1]//div[contains(@class,'oxd-table-cell')][3] | "
						+ "//div[@class='oxd-table-body']/div[1]//div[contains(@class,'oxd-table-cell')][5]")));
		String row1Name = row1NameAndDate.get(0).getText().trim();
		String row1Date = row1NameAndDate.get(1).getText().trim();

		return row1Name + " & " + row1Date;
	}

	public boolean isRow1CandidateDeleted(String nameAndDate) {

		List<WebElement> updatedRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//div[@class='oxd-table-body']/div//div[contains(@class,'oxd-table-cell')][3] | "
						+ "//div[@class='oxd-table-body']/div//div[contains(@class,'oxd-table-cell')][5]")));

		System.out.println("Total elements found: " + updatedRows.size());

		String[] parts = nameAndDate.split("\\&");
		if (parts.length != 2) {
			System.out.println("Error: Invalid format. Expected 'Name & Date'. Received: " + nameAndDate);
			return false;
		}

		String expectedName = parts[0].trim();
		String expectedDate = parts[1].trim();

		System.out.println("Expected Name: " + expectedName);
		System.out.println("Expected Date: " + expectedDate);

		boolean candidateFound = false;
		int duplicateCount = 0;

		for (int i = 0; i < updatedRows.size(); i += 2) {
			String rowName = updatedRows.get(i).getText().trim();
			String rowDate = updatedRows.get(i + 1).getText().trim();

			System.out.println("Checking row " + (i / 2 + 1) + ": " + rowName + " | " + rowDate);

			if (rowName.equalsIgnoreCase(expectedName) && rowDate.equals(expectedDate)) {
				duplicateCount++;
				candidateFound = true;

			}
		}

		if (candidateFound) {
			if (duplicateCount > 1) {
				System.out.println("Candidate still exists as a duplicate! Found " + duplicateCount + " times.");
			} else {
				System.out.println("Candidate still exists.");
			}
			return false;
		}

		System.out.println("Candidate deleted successfully.");
		return true;
	}

	// Add Candidate

	public String getAdd_VacancyTxt() {
		return addCandidate_vacancy.getText();
	}

	public WebElement getAdd_Vacancy() {
		return addCandidate_vacancy;
	}

	public void addCandidate(String fname, String mname, String lname, String email, String contactNumber,
			String vacancy) {
		clickWaitElement(table_add);
		sendKeysWithWait(addCandidate_firstname, fname);
		sendKeysWithWait(addCandidate_middlename, mname);
		sendKeysWithWait(addCandidate_lastname, lname);
		sendKeysWithWait(addCandidate_email, email);
		sendKeysWithWait(addCandidate_contactnumber, contactNumber);
		selectAdd_Vacancy(vacancy);
		clickWaitElement(addCandidate_saveBtn);
	}

	public void cancelAddCandidate() {
		clickWaitElement(table_add);
		waitForElement(addCandidate_firstname);
		clickWaitElement(addCandidate_cancel);
	}

	public void selectAdd_Vacancy(String vacancy) {
		clickWaitElement(addCandidate_vacancy);
		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getAdd_VacancyTxt();

			if (highlightedText.equals(vacancy)) {
				addCandidate_vacancy.sendKeys(Keys.ENTER);
				break;
			}
			addCandidate_vacancy.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Vacancy not found: " + vacancy);
		}
	}

}
