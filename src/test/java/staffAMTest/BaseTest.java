package staffAMTest;

import driver_manager.DriverSetter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import staffAM.SearchJobPage;
import url_manager.UrlSetter;

public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void chromeSetup() throws InterruptedException {
        UrlSetter.setUrl();
    }
    @BeforeClass
    public void setup() throws InterruptedException {
        DriverSetter.setDriver();
        driver = DriverSetter.getDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void quitTest() {

        driver.quit();
    }
}
