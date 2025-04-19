package page.PIM;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page.BasePage;

public class Reports extends BasePage {

	public Reports(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[normalize-space()='Reports']")
	WebElement reports;

	@FindBy(xpath = "//div[@class='--toggle']//button[@type='button']")
	WebElement employeeReportsToggleBtn;

	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	WebElement reportName;

	@FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement searchBtn;

	@FindBy(xpath = "//button[normalize-space()='Reset']")
	WebElement resetBtn;

	@FindBy(xpath = "//button[normalize-space()='Add']")
	WebElement addReportBtn;

	public WebElement getEmployeeReportsToggleBtn() {
		return waitForElement(employeeReportsToggleBtn);
	}

	public void clickEmployeeReportsToggleBtn() {
		clickWaitElement(employeeReportsToggleBtn);
	}

	public void clickReports() {
		clickWaitElement(reports);
	}

	public void clickResetBtn() {
		clickWaitElement(resetBtn);
	}

	public void clickAddReportBtn() {
		clickWaitElement(addReportBtn);
	}

	public String getReportNameValue() {
		return reportName.getAttribute("value");
	}

	public void searchAndSelectReportNameThruAutoSuggest1(String reportName) {
		sendKeysWithWait(this.reportName, reportName);

		// Wait for suggestions to appear
		List<WebElement> suggestions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//div[@role='listbox']/div/span[contains(normalize-space(text()), '" + reportName + "')]")));

		System.out.println("Total Suggestions Found: " + suggestions.size());

		if (!suggestions.isEmpty()) {
			System.out.println("Selecting: " + suggestions.get(0).getText());
			suggestions.get(0).click();
		} else {
			System.out.println("No matching suggestion found for: " + reportName);
		}

		searchBtn.click();
	}

	public boolean isReportNameFiltered(String reportName) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(normalize-space(text()),'"
					+ reportName + "')]";

			try {
				WebElement nameElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = nameElement.getText();
				System.out.println("Row " + i + " Report Name: " + actValue);
				if (actValue.contains(reportName)) {
					flag = true;
					break;
				}
			} catch (TimeoutException e) {
				System.out.println("Report name not found in row " + i + ". Checking partial match...");

				String[] nameParts = reportName.split("\\s+");
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

	public boolean isReportNameToggleWorking() {
		try {
			WebElement toggle = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[contains(@class,'oxd-icon')])[7]")));

			boolean beforeToggle = toggle.getAttribute("class").contains("bi-caret-up-fill");
			boolean isReportVisibleBefore = reportName.isDisplayed();

			clickWaitElement(toggle);

			boolean afterToggle = wait.until(ExpectedConditions.attributeToBe(toggle, "class",
					beforeToggle ? "oxd-icon bi-caret-down-fill" : "oxd-icon bi-caret-up-fill"));

			boolean isReportVisibleAfter = afterToggle ? reportName.isDisplayed()
					: wait.until(ExpectedConditions.invisibilityOf(reportName));

			return (beforeToggle && isReportVisibleBefore) != (afterToggle && isReportVisibleAfter);

		} catch (TimeoutException | NoSuchElementException | ElementNotInteractableException e) {
			System.out.println("Toggle button or report name issue: " + e.getMessage());
			return false;
		}
	}

	// Add Report

	@FindBy(xpath = "//input[@placeholder='Type here ...']")
	WebElement addReport_reportName;

	@FindBy(xpath = "(//div[@class='oxd-form-row']//div[@class='oxd-select-wrapper']/div[1]/div[1])[1]")
	WebElement selectionCriteria;

	@FindBy(xpath = "(//div[@class='oxd-form-row']//div[@class='oxd-select-wrapper']/div[1]/div[1])[2]")
	WebElement include;

	@FindBy(xpath = "(//div[@class='oxd-form-row']//div[@class='oxd-select-wrapper']/div[1]/div[1])[3]")
	WebElement displayFieldGroup;

	@FindBy(xpath = "(//div[@class='oxd-form-row']//div[@class='oxd-select-wrapper']/div[1]/div[1])[4]")
	WebElement displayField;

	@FindBy(xpath = "(//div[@class='oxd-form-row']//div[1]//div[2]//div[2]//button[1]//i[1])[1]")
	WebElement selectionCriteria_plusIcon;

	@FindBy(xpath = "(//div[@class='oxd-form-row']//div[1]//div[2]//div[2]//button[1]//i[1])[2]")
	WebElement displayField_plusIcon;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement saveBtn;

	public void inputReportName(String addReport_reportName) {
		sendKeysWithWait(this.addReport_reportName, addReport_reportName);
	}

	// Selection Criteria

	public String getSelectionCriteriaTxt() {
		return selectionCriteria.getText();
	}

	public void searchSelectionCriteria(String selectionCriteria) {
		clickWaitElement(this.selectionCriteria);

		int maxTries = 100;
		boolean found = false;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getSelectionCriteriaTxt();

			if (highlightedText.equals(selectionCriteria)) {
				this.selectionCriteria.sendKeys(Keys.ENTER);
				found = true; // Found the element
				break;
			}
			this.selectionCriteria.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + selectionCriteria);
		}
	}

	public void searchInputSelectionCriteria(String selectionCriteria, String inputSelectionCriteria) {
		WebElement inputSelectionCriteriaElement = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='"
						+ selectionCriteria + "']/following::div[normalize-space()='-- Select --'][4]")));
		clickWaitElement(inputSelectionCriteriaElement);

		int maxTries = 100;
		boolean found = false;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = inputSelectionCriteriaElement.getText();

			if (highlightedText.equals(inputSelectionCriteria)) {
				inputSelectionCriteriaElement.sendKeys(Keys.ENTER);
				found = true; // Found the element
				break;
			}
			inputSelectionCriteriaElement.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + inputSelectionCriteria);
		}
	}

	public void addSelectionCriteria(List<String> criteriaList, Map<String, String> inputCriteriaMap) {
		for (String criteria : criteriaList) {
			searchSelectionCriteria(criteria);
			clickSelectionCriteria_plusIcon();

			String input = inputCriteriaMap.get(criteria);
			if (input != null) {
				searchInputSelectionCriteria(criteria, input);
			}
		}
	}
	public boolean isSelectionCriteriaDisplayed(List<String> criteriaList, Map<String, String> inputCriteriaMap) {
		  for (String criteria : criteriaList) {
		        String expectedInput = inputCriteriaMap.get(criteria);
		        if (expectedInput == null) continue;

		        String xpath = "//p[normalize-space()='" + criteria + "']/following::div[normalize-space()='" + expectedInput + "'][1]";
		        try {
		            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		            if (!element.isDisplayed()) {
		                return false;
		            }
		        } catch (TimeoutException e) {
		            return false;
		        }
		    }
		    return true; // All criteria and inputs are displayed
		}

	public String getIncludeTxt() {
		return include.getText();
	}

	public void selectInclude(String include) {
		clickWaitElement(this.include);

		int maxTries = 100;
		boolean found = false;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getIncludeTxt();

			if (highlightedText.equals(include)) {
				this.include.sendKeys(Keys.ENTER);
				found = true; // Found the element
				break;
			}
			this.include.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + include);
		}
	}

	public String getDisplayFieldGroupTxt() {
		return displayFieldGroup.getText();
	}

	public void searchDisplayFieldGroup(String displayFieldGroup) {
		clickWaitElement(this.displayFieldGroup);

		int maxTries = 100;
		boolean found = false; // Set to false initially

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getDisplayFieldGroupTxt();

			if (highlightedText.equals(displayFieldGroup)) {
				this.displayFieldGroup.sendKeys(Keys.ENTER);
				found = true; // Found the element
				break;
			}
			this.displayFieldGroup.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Display Field Group not found: " + displayFieldGroup);
		}
	}

	public String getDisplayFieldTxt() {
		return displayField.getText();
	}

	public void searchDisplayField(String displayField) {
		clickWaitElement(this.displayField);

		int maxTries = 100;
		boolean found = false;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getDisplayFieldTxt();

			if (highlightedText.equals(displayField)) {
				this.displayField.sendKeys(Keys.ENTER);
				found = true; // Found the element
				break;
			}
			this.displayField.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + displayField);
		}
	}

	public void clickSelectionCriteria_plusIcon() {
		clickWaitElement(selectionCriteria_plusIcon);
	}

	public void clickDisplayFields_plusIcon() {
		clickWaitElement(displayField_plusIcon);
	}

	public boolean isAddedSelectionCriteriaCorrect(String expectedSelectionCriteria, String expectedInput) {
		// Wait for and get all Display Field Group elements
		List<WebElement> selectionCriteria = wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//p[normalize-space()='" + expectedSelectionCriteria
						+ "']/following::div[normalize-space()='" + expectedInput + "'][4]")));
		// Check if the element is found and displayed
		if (!selectionCriteria.isEmpty()) {
			return selectionCriteria.get(0).isDisplayed();
		} else {
			return false;
		}
	}

	public boolean isAddedDisplayFieldsCorrect(String expectedDisplayFieldGroup, String expectedDisplayField) {
		// Wait for and get all Display Field Group elements
		List<WebElement> fieldGroupElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-report-field-name']")));

		// Wait for and get all Display Field elements
		List<WebElement> fieldElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//span[@class='oxd-chip oxd-chip--default oxd-multiselect-chips-selected']")));

		// Extract text from elements
		List<String> actualFieldGroups = fieldGroupElements.stream().map(WebElement::getText)
				.collect(Collectors.toList());

		List<String> actualFields = fieldElements.stream().map(WebElement::getText).collect(Collectors.toList());

		// Check if elements are displayed
		boolean areFieldGroupsDisplayed = fieldGroupElements.stream().allMatch(WebElement::isDisplayed);
		boolean areFieldsDisplayed = fieldElements.stream().allMatch(WebElement::isDisplayed);

		// Check if expected strings exist in the actual lists
		boolean fieldGroupMatch = actualFieldGroups.contains(expectedDisplayFieldGroup);
		boolean fieldMatch = actualFields.contains(expectedDisplayField);

		return areFieldGroupsDisplayed && areFieldsDisplayed && fieldGroupMatch && fieldMatch;
	}

	public void clickSaveBtn() {
		clickWaitElement(saveBtn);
	}
}
