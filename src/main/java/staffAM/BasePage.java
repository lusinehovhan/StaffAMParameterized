package staffAM;

import driver_waiter.DriverWaiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import url_manager.UrlSetter;

abstract public class BasePage {
    protected WebDriver driver;
    protected DriverWaiter driverWaiter;
    protected final String BASE_URL = UrlSetter.getUrl();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        driverWaiter = new DriverWaiter(driver, 30);
        PageFactory.initElements(driver, this);
    }
}