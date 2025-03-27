package ru.iukhimenko.demoblaze.ui.base;

import ru.iukhimenko.demoblaze.SysConfig;
import ru.iukhimenko.demoblaze.ui.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ru.stqa.selenium.factory.WebDriverPool;

import static ru.iukhimenko.demoblaze.SysConfig.CONFIG;

public class GuiTest {
    private WebDriver driver;

    @BeforeTest
    protected void initWebDriver() {
        String browserName = System.getProperty("browser");
        if (browserName.equals("firefox")) {
            driver = WebDriverPool.DEFAULT.getDriver(new FirefoxOptions());
        }
        else if (browserName.equals("chrome")) {
            driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
        }
    }

    protected HomePage openHomePage() {
        driver.get(CONFIG.baseUrl());
        driver.manage().window().maximize();
        return new HomePage(driver);
    }

    protected WebDriver getWebDriver() {
        if (driver == null) {
            initWebDriver();
        }
        return driver;
    }

    @AfterTest
    public void tearDown() {
        WebDriverPool.DEFAULT.dismissAll();
    }
}
