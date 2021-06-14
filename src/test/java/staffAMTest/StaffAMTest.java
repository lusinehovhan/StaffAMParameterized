package staffAMTest;

import driver_manager.DriverSetter;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import staffAM.HomePage;
import url_manager.UrlSetter;

public class StaffAMTest {
    private WebDriver driver;
    private HomePage homePage;
    private int currentPageNum = 100;

    @BeforeSuite
    public void chromeSetup() throws InterruptedException {
        UrlSetter.setUrl();
    }

    @BeforeClass
    public void setup() throws InterruptedException {
        DriverSetter.setDriver();
        driver = DriverSetter.getDriver();
        driver.manage().window().maximize();

        homePage = new HomePage(driver).open();
        homePage.waitPageLoad();
    }

    @Test(dataProvider = "getTestParameters", dataProviderClass = DataProviders.class)
    public void jobSearchResultCategory(String jobsFilter1, String jobsFilter2) throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        if(homePage.expectedNumberJobs(jobsFilter1) < currentPageNum) {
            softAssert.assertEquals(homePage.actualNumberJobs(jobsFilter1), homePage.expectedNumberJobs(jobsFilter1));
        }else {
            softAssert.assertEquals(homePage.actualNumberJobs(jobsFilter1), currentPageNum);
        }
        if(homePage.expectedNumberJobs(jobsFilter2) < currentPageNum) {
            softAssert.assertEquals(homePage.actualNumberJobs(jobsFilter2), homePage.expectedNumberJobs(jobsFilter2));
        }else {
            softAssert.assertEquals(homePage.actualNumberJobs(jobsFilter2), currentPageNum);
        }

        softAssert.assertAll();
        homePage.clearFilter();

    }
    @AfterClass
    public void quitTest() {

        driver.quit();
    }
}