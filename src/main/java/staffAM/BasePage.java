package staffAM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import url_manager.UrlSetter;

abstract public class BasePage {
    protected WebDriver driver;
    protected final String BASE_URL = UrlSetter.getUrl();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}