package gui.base;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class GuiEntity {
    protected WebDriver driver;
    private final int MAX_TIMEOUT = 5; // to config

    protected GuiEntity(WebDriver driver) {
        this.driver = driver;
    }

    protected void clickOn(WebElement element) {
        waitForClickabilityOf(element, MAX_TIMEOUT);
        element.click();
    }

    protected void clearAndTypeIn(WebElement field, String text) {
        waitForVisibilityOf(field, MAX_TIMEOUT);
        field.clear();
        field.sendKeys(text);
    }

    protected void waitForElementToExist(By locator) {
        int maxAttempts = MAX_TIMEOUT, attempt = 0;
        while (driver.findElements(locator).isEmpty()) {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            attempt++;
            if (attempt == maxAttempts)
                break;
        }
    }

    protected void waitForClickabilityOf(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(elementToBeClickable(element));
    }

    protected void waitForVisibilityOf(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(visibilityOf(element));
    }

    protected Alert waitForAlertIsPresent(int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(alertIsPresent());
    }

    protected boolean isMaskedInput(WebElement input) {
        return input.getAttribute("type").equals("password");
    }
}