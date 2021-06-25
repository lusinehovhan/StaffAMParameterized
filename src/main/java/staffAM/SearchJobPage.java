package staffAM;

import driver_waiter.DriverWaiter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchJobPage extends BasePage {
    private String url = "/en/jobs";

    private List<WebElement> jobResultListElem() {
        By jobResultLoc = By.cssSelector(".list-view [data-key]");
        return driver.findElements(jobResultLoc);
    }

    private By jobResultViewLoc = By.cssSelector(".list-view");

    @FindBy(css = ".green_color.pull-right.jobClearFilter")
    private WebElement clearFiltersElem;

    private WebElement jobFilterElem(String jobsFilter) {
        String jobsFilterLoc = String.format("//form[@id='jobs-filter']//label[text()='%s']", jobsFilter);
        return driver.findElement(By.xpath(jobsFilterLoc));
    }

    private WebElement jobFilterListElem(String jobsFilter) {
        String jobsFilterLoc = String.format("//form[@id='jobs-filter']//*[text()='%s']", jobsFilter);
        return driver.findElement(By.xpath(jobsFilterLoc));
    }

    private WebElement jobFilterNumElem(String jobsFilter) {
        String jobsFilterNumLoc = String.format("//form[@id='jobs-filter']//*[text()='%s']/following::span", jobsFilter);
        return driver.findElement(By.xpath(jobsFilterNumLoc));
    }

    public SearchJobPage(WebDriver driver) {
        super(driver);
    }
    public void clickJobFilter(String filter) throws InterruptedException {
        jobFilterListElem(filter).click();
        driverWaiter.visibilityOfElementLoc(jobResultViewLoc);
    }

    public int expectedNumberJobs(String specialist) throws InterruptedException {
        String expectedNumSpecialist = "";
        try {
            if (jobFilterElem(specialist).isDisplayed()) {
                expectedNumSpecialist = jobFilterElem(specialist).getText().replaceAll("[^0-9]", "");
                return Integer.parseInt(expectedNumSpecialist);
            }
        } catch (NoSuchElementException e) {
            expectedNumSpecialist = jobFilterNumElem(specialist).getText().replaceAll("[^0-9]", "");
            return Integer.parseInt(expectedNumSpecialist);
        }
        return Integer.parseInt(expectedNumSpecialist);
    }

    public int actualNumberJobs() {
        return jobResultListElem().size();
    }

    public void clearFilter() {
        Actions actions = new Actions(driver);
        actions.moveToElement(clearFiltersElem).perform();
        clearFiltersElem.click();
    }

    public SearchJobPage open() {
        driver.get(BASE_URL + url);
        return this;
    }

    public void waitPageLoad() {

        driverWaiter.visibilityOfAllElements(jobResultListElem());
    }
}
