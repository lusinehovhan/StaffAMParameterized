package staffAMTest;

import driver_manager.DriverSetter;
import driver_waiter.DriverWaiter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import staffAM.SearchJobPage;
import url_manager.UrlSetter;

public class StaffAMTest extends BaseTest {
    private SearchJobPage searchJobPage;
    private int currentPageNum = 100;

    @BeforeClass
    public void setupSearchPage() throws InterruptedException {
        searchJobPage = new SearchJobPage(driver).open();
        searchJobPage.waitPageLoad();
    }

    @Test(dataProvider = "getTestParameters", dataProviderClass = DataProviders.class)
    public void jobSearchResultCategory(String jobsFilter1, String jobsFilter2) throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        searchJobPage.clickJobFilter(jobsFilter1);
        if (searchJobPage.expectedNumberJobs(jobsFilter1) < currentPageNum) {
            softAssert.assertEquals(searchJobPage.expectedNumberJobs(jobsFilter1), searchJobPage.actualNumberJobs());
        } else {
            softAssert.assertEquals(searchJobPage.actualNumberJobs(), currentPageNum);
        }
        searchJobPage.clickJobFilter(jobsFilter2);
        if (searchJobPage.expectedNumberJobs(jobsFilter2) < currentPageNum) {
            softAssert.assertEquals(searchJobPage.expectedNumberJobs(jobsFilter2), searchJobPage.actualNumberJobs());
        } else {
            softAssert.assertEquals(searchJobPage.actualNumberJobs(), currentPageNum);
        }
        softAssert.assertAll();
    }

    @AfterMethod
    public void clearFilters() {
        searchJobPage.clearFilter();
    }
}