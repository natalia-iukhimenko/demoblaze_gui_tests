package ru.iukhimenko.demoblaze.ui.base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.iukhimenko.demoblaze.ui.pages.HomePage;
import org.openqa.selenium.WebDriver;

import static ru.iukhimenko.demoblaze.SysConfig.CONFIG;

public class GuiTest {
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    protected void initWebDriver() {
        Browser selectedBrowser = Browser.valueOf(CONFIG.browser().toUpperCase());
        driver = WebDriverFactory.createDriver(selectedBrowser);
    }

    protected HomePage openHomePage() {
        driver.get(CONFIG.baseUrl());
        driver.manage().window().maximize();
        return new HomePage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Failed to quit WebDriver: " + e.getMessage());
            }
        }
    }
}