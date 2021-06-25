package driver_waiter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DriverWaiter {
    protected WebDriverWait wait;

    public DriverWaiter(WebDriver driver, int timeout) {

        wait = new WebDriverWait(driver, 30);
    }

    public DriverWaiter visibilityOfElementLoc(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return this;
    }

    public DriverWaiter visibilityOfAllElements(List<WebElement> list) {
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
        return this;
    }
}
