package ru.iukhimenko.demoblaze.ui.base;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

import static ru.iukhimenko.demoblaze.SysConfig.CONFIG;

public class WebDriverFactory {
    public static WebDriver createDriver(Browser browser) {
        Capabilities capabilities;
        String remoteUrl;

        switch (browser) {
            case FIREFOX:
                capabilities = new FirefoxOptions();
                remoteUrl = CONFIG.remoteUrlFirefox();
                break;
            case CHROME:
            default:
                capabilities = new ChromeOptions();
                remoteUrl = CONFIG.remoteUrlChrome();

        }

        if (CONFIG.isRemote()){
            return createRemoteDriver(capabilities, remoteUrl);
        } else {
            return createLocalDriver(browser);
        }
    }

    private static WebDriver createRemoteDriver(Capabilities capabilities, String remoteUrl) {
        try {
            return new RemoteWebDriver(new URL(remoteUrl), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid remote WebDriver URL: " + remoteUrl, e);
        }
    }

    private static WebDriver createLocalDriver(Browser browser) {
        return switch (browser) {
            case FIREFOX -> new FirefoxDriver();
            default -> new ChromeDriver();
        };
    }
}
