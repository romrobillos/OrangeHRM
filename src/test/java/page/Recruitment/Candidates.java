package page.Recruitment;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.BasePage;

public class Candidates extends BasePage {

	public Candidates(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[normalize-space()='Candidates']")
	WebElement candidates;

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

	@FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement candidates_search;

	@FindBy(xpath = "//input[@placeholder='From']")
	WebElement candidates_dateFrom;

	@FindBy(xpath = "//input[@placeholder='To']")
	WebElement candidates_dateTo;

	public WebElement getCandidates() {
		return candidates;
	}

	public WebElement getdateFrom() {
		return candidates_dateFrom;
	}

	public WebElement getdateTo() {
		return candidates_dateTo;
	}

	public WebElement getCandidateName() {
		return candidates_candidateName;
	}

	public void clickSearch() {
		candidates_search.click();
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

	public void selectVacancy(String vacancy) {
		candidates_vacancy.click();

		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getVacancyTxt();

			if (highlightedText.equals(vacancy)) {
				candidates_vacancy.sendKeys(Keys.ENTER);
				break;
			}
			candidates_vacancy.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + vacancy);
		}
	}

	public boolean isVacancyFiltered(String vacancy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

	public void selectHiringManager(String manager) {
		candidates_hiringManager.click();

		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getHiringManagerTxt();

			if (highlightedText.contains(manager)) {
				candidates_hiringManager.sendKeys(Keys.ENTER);
				break;
			}
			candidates_hiringManager.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + manager);
		}
	}

	public boolean isHiringManagerFiltered(String manager) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
		System.out.println("Total rows found: " + rows.size());
		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(text(),'" + manager + "')]";

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

	public void selectStatus(String status) {
		candidates_status.click();

		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getStatusTxt();

			if (highlightedText.contains(status)) {
				candidates_status.sendKeys(Keys.ENTER);
				break;
			}
			candidates_status.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + status);
		}
	}

	public boolean isStatusFiltered(String status) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

	public void selectName(String name) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		List<WebElement> dropdown = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//div[@role='listbox']/div/span[normalize-space()='" + name + "']")));
		
		System.out.println("Dropdown list:");
		for (int i = 0; i < dropdown.size(); i++) {
			System.out.println(dropdown.get(i).getText());
		}
		System.out.println("Total:"+dropdown.size());
		// select
		for (int i = 0; i < dropdown.size(); i++)
			if (dropdown.get(i).getText().equalsIgnoreCase(name)) {
				dropdown.get(i).click();
				break;
				
			}

	}

	public boolean isCandidateNameFiltered(String name) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
		System.out.println("Total rows found: " + rows.size());
		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[contains(normalize-space(text()),'"
					+ name + "')]";

			try {
				WebElement nameElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = nameElement.getText();
				System.out.println("Row " + i + ": " + actValue);
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Get "Date From" and "Date To" values from input fields
		String dateFromText = candidates_dateFrom.getAttribute("value").trim();
		String dateToText = candidates_dateTo.getAttribute("value").trim();

		// Convert "Date From" and "Date To" into LocalDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust format if needed
		LocalDate dateFrom = LocalDate.parse(dateFromText, formatter);
		LocalDate dateTo = LocalDate.parse(dateToText, formatter);

		// Retrieve all displayed "Date of Application" values from the table
		List<WebElement> dateElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-cell')][5]")));

		System.out.println("Number of 'Date of Application' elements found: " + dateElements.size());

		if (dateElements.isEmpty()) {
			System.out.println("No matching dates found in the table.");
			return false;
		}

		// Traditional for-loop version
		for (int i = 0; i < dateElements.size(); i++) {
			WebElement dateElement = dateElements.get(i);

			// Get text and convert to LocalDate
			String dateText = dateElement.getText().trim();
			System.out.println("Row " + (i + 1) + ": Raw date text - " + dateText);

			try {
				LocalDate applicationDate = LocalDate.parse(dateText, formatter);

				// Check if application date is within range
				boolean isWithinRange = (applicationDate.isEqual(dateFrom) || applicationDate.isAfter(dateFrom))
						&& (applicationDate.isEqual(dateTo) || applicationDate.isBefore(dateTo));

				System.out.println("Checking: " + applicationDate + " is within range: " + dateFrom + " to " + dateTo
						+ " -> Result: " + isWithinRange);

				if (!isWithinRange) {
					return false; // If any date is out of range, return false immediately
				}
			} catch (DateTimeParseException e) {
				System.out.println("Skipping row " + (i + 1) + " due to invalid date format: " + dateText);
			}
		}

		return true; // All dates are within range
	}

}
