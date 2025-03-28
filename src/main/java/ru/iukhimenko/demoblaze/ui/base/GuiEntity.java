package ru.iukhimenko.demoblaze.ui.base;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class GuiEntity {
    protected WebDriver driver;
    protected final int MAX_TIMEOUT = 5;

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

    protected void waitForClickabilityOf(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(elementToBeClickable(element));
    }

    protected void waitForVisibilityOf(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(visibilityOf(element));
    }

    protected Alert waitForAlertIsPresent(int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(alertIsPresent());
    }

    protected boolean isMaskedInput(WebElement input) {
        return Objects.equals(input.getDomProperty("type"), "password");
    }
}