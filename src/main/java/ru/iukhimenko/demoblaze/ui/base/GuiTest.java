package ru.iukhimenko.demoblaze.ui.base;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.iukhimenko.demoblaze.ui.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.stqa.selenium.factory.WebDriverPool;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import java.net.MalformedURLException;
import java.net.URL;

import static ru.iukhimenko.demoblaze.SysConfig.CONFIG;

public class GuiTest {
    private WebDriver driver;


    @BeforeTest
    protected void initWebDriver() {
        Capabilities capabilities = getWebDriverCapabilities(CONFIG.browser());

        if (CONFIG.isRemote()) {
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else {
            driver = WebDriverPool.DEFAULT.getDriver(capabilities);
        }
    }

    private Capabilities getWebDriverCapabilities(String browserName) {
        if (browserName.equalsIgnoreCase(Browser.FIREFOX.name())) {
            return new FirefoxOptions();
        }
        return new ChromeOptions();
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
