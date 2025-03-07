package page.Recruitment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page.BasePage;

public class Vacancies extends BasePage {

	public Vacancies(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[normalize-space()='Vacancies']")
	WebElement vacanciesSubtab;

	@FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[1]/div/div[1]")
	WebElement vacancies_jobTitle;

	@FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[2]/div/div[1]")
	WebElement vacancies_vacancy;

	@FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[3]/div/div[1]")
	WebElement vacancies_hiringManager;

	@FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[4]/div/div[1]")
	WebElement vacancies_status;

	@FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement vacancies_searchBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Reset']")
	WebElement vacancies_resetBtn;
	
	

	public void clickVacanciesSubtab() {
		clickWaitElement(vacanciesSubtab);
	}

	// Job Title
	public String getJobTitleTxt() {
		return vacancies_jobTitle.getText();
	}
	
	public void clickReset() {
		clickWaitElement (vacancies_resetBtn);  
	}

	public void searchJobTitle(String jobTitle) {
		clickWaitElement(vacancies_jobTitle);

		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getJobTitleTxt();

			if (highlightedText.equals(jobTitle)) {
				vacancies_jobTitle.sendKeys(Keys.ENTER);
				clickWaitElement(vacancies_searchBtn);
				break;
			}
			vacancies_jobTitle.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + jobTitle);
		}
	}

	public boolean isJobTitleFiltered(String jobTitle) {

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
		System.out.println("Total rows found: " + rows.size());
		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[ (text()='" + jobTitle + "')]";

			try {
				WebElement jobTitleElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = jobTitleElement.getText();
				System.out.println("Row " + i + ": " + actValue);
				if (actValue.contains(jobTitle)) {
					flag = true;
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found in row " + i);
			}
		}

		return flag;
	}

	// Vacancy

	public String getVacancyTxt() {
		return vacancies_vacancy.getText();
	}

	public void searchVacancy(String vacancy) {
		clickWaitElement(vacancies_vacancy);

		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getVacancyTxt();

			if (highlightedText.equals(vacancy)) {
				vacancies_vacancy.sendKeys(Keys.ENTER);
				clickWaitElement(vacancies_searchBtn);
				break;
			}
			vacancies_vacancy.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + vacancy);
		}
	}

	public boolean isVacancyFiltered(String vacancy) {

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
		System.out.println("Total rows found: " + rows.size());
		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[ (text()='" + vacancy + "')]";

			try {
				WebElement vacancyElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = vacancyElement.getText();
				System.out.println("Row " + i + ": " + actValue);
				if (actValue.contains(vacancy)) {
					flag = true;
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found in row " + i);
			}
		}

		return flag;
	}

	// Hiring Manager

	public String getHiringManagerTxt() {
		return vacancies_hiringManager.getText();
	}

	public void searchHiringManager(String manager) {
		clickWaitElement(vacancies_hiringManager);

		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getHiringManagerTxt();

			if (highlightedText.equals(manager)) {
				vacancies_hiringManager.sendKeys(Keys.ENTER);
				clickWaitElement(vacancies_searchBtn);
				break;
			}
			vacancies_hiringManager.sendKeys(Keys.ARROW_DOWN);
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

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[ (text()='" + manager + "')]";

			try {
				WebElement managerElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = managerElement.getText();
				System.out.println("Row " + i + ": " + actValue);
				if (actValue.contains(manager)) {
					flag = true;
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found in row " + i);
			}
		}

		return flag;
	}
	
	// Status 
	
	public String getStatusTxt() {
		return vacancies_status.getText();
	}

	public void searchStatus(String status) {
		clickWaitElement(vacancies_status);

		int maxTries = 100;
		boolean found = true;

		for (int i = 0; i < maxTries; i++) {
			String highlightedText = getStatusTxt();

			if (highlightedText.equals(status)) {
				vacancies_status.sendKeys(Keys.ENTER);
				clickWaitElement(vacancies_searchBtn);
				break;
			}
			vacancies_status.sendKeys(Keys.ARROW_DOWN);
		}

		if (!found) {
			throw new RuntimeException("Not found: " + status);
		}
	}

	public boolean isStatusFiltered(String status) {

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']/div")));

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
		System.out.println("Total rows found: " + rows.size());
		boolean flag = false;

		for (int i = 1; i <= rows.size(); i++) {
			String dynamicXpath = "//div[@class='oxd-table-body']/div[" + i
					+ "]//div[contains(@class,'oxd-table-cell')]//descendant::div[ (text()='" + status + "')]";

			try {
				WebElement statusElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				String actValue = statusElement.getText();
				System.out.println("Row " + i + ": " + actValue);
				if (actValue.contains(status)) {
					flag = true;
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found in row " + i);
			}
		}

		return flag;
	}
}
