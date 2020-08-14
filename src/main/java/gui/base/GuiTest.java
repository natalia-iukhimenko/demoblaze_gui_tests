package gui.base;

import gui.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ru.stqa.selenium.factory.WebDriverPool;

public class GuiTest {
    private WebDriver driver;
    private String baseURL = "https://www.demoblaze.com";

    @BeforeTest
    protected void initWebDriver() {
        //String browserName = System.getProperty("browser");
        String browserName = "chrome";
        if (browserName.equals("firefox")) {
            driver = WebDriverPool.DEFAULT.getDriver(new FirefoxOptions());
        }
        else if (browserName.equals("chrome")) {
            driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
        }
    }

    protected HomePage openHomePage() {
        driver.get(baseURL);
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
