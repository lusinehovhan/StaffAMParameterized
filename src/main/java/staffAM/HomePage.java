package staffAM;

import driver_waiter.DriverWaiter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    private String url = "/en/jobs";
    private DriverWaiter driverWaiter;


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

    public HomePage(WebDriver driver) {
        super(driver);
        driverWaiter = new DriverWaiter(driver, 30);
    }

    public int actualNumberJobs(String specialist) {
        jobFilterListElem(specialist).click();
        driverWaiter.visibilityOfElementLoc(jobResultViewLoc);
        return jobResultListElem().size();
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

    public void clearFilter() {
        Actions actions = new Actions(driver);
        actions.moveToElement(clearFiltersElem).perform();
        clearFiltersElem.click();
    }

    public HomePage open() {
        driver.get(BASE_URL + url);
        return this;
    }

    public void waitPageLoad() {
        driverWaiter.visibilityOfAllElements(jobResultListElem());
    }
}
