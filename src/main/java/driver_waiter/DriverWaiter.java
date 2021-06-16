package driver_waiter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DriverWaiter {
    private WebDriverWait wait;

    public DriverWaiter(WebDriver driver, int timeout) {

        wait = new WebDriverWait(driver, 30);
    }

    public void visibilityOfElementLoc(By by) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void visibilityOfAllElements(List<WebElement> list) {

        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }
}
