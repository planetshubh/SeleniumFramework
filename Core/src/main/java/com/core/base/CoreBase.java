package com.core.base;

import com.core.exceptions.BrowserInitializationException;
import com.core.exceptions.PropertyFileException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * This class is responsible for invoking the browsers.
 *
 * @author Shubhendra Singh
 */

public class CoreBase {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected CoreBase() {
    }

    public void initialization() {
        String browserName = PropertyLoader.getProp("browser");
        if (Objects.isNull(getDriver())) {
            if (browserName.equalsIgnoreCase("Chrome")) {
                WebDriverManager.chromedriver().setup();
                setDriver(new ChromeDriver());
            } else if (browserName.equalsIgnoreCase("Safari")) {
                setDriver(new SafariDriver());
            } else if (browserName.equalsIgnoreCase("Firefox")) {
                WebDriverManager.firefoxdriver().setup();
                setDriver(new FirefoxDriver());
            } else
                throw new BrowserInitializationException("Problem occured in browser initialization, not a valid value for browser.", new NullPointerException());
        }
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        getDriver().get(PropertyLoader.getProp("url"));
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverref) {
        driver.set(driverref);
    }

    public static void unload(){
        driver.remove();
    }
}
