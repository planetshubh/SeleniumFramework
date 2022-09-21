package com.core.utilities;

import com.core.base.CoreBase;
import com.core.enums.WaitStrategy;
import com.core.exceptions.CoreException;
import com.core.reports.ExtentLogger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.core.constants.CoreConstants;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * This class contains the utilities.
 * These utilities will be responsible to handle repetitive tasks on UI elements e.g. click etc.
 *
 * @author Shubhendra Singh
 */

public class CoreUtils extends CoreBase {
    public CoreUtils(WebDriver driver) {
        super();
    }

    //utility to verify presence of an element on a webpage
    public boolean verifyElementExists(By selector) {
        int n=0;
        try {
            waitForElement(selector, WaitStrategy.VISIBLE);
        } catch (TimeoutException e) {
            System.out.println("Element was not found on this page " + selector);
        }
        n = getDriver().findElements(selector).size();
        if (n != 0) {
            System.out.println("Element found on this page");
            return true;
        } else
            return false;
    }
//        waitForElement(selector, WaitStrategy.VISIBLE);
//        int n = getDriver().findElements(selector).size();
//        if (n != 0) {
//            System.out.println("Element found on this page");
//            return true;
//        } else {
//            System.out.println("Element was not found on this page " + selector);
//            return false;
//        }

    //utility to capture Screenshot
    public void captureScreenShot() {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("./Screenshots/" + System.nanoTime() + ".png"));
        } catch (IOException e) {
            System.out.println("Unable to capture screenshot " + e.getMessage());
        }
    }

    public static String getBase64Image() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }

    // utility to get the Element using By locator
    public WebElement getElement(By selector, WaitStrategy waitStrategy) {
        try {
            waitForElement(selector, waitStrategy);
            return getDriver().findElement(selector);
        } catch (Exception e) {
            throw new CoreException("Element " + selector + " does not exist - proceeding");
        }
    }

    // utility to get the multiple Elements using By locator
    public List<WebElement> getElements(By selector, WaitStrategy waitStrategy) {
        try {
            waitForElement(selector, waitStrategy);
            return getDriver().findElements(selector);
        } catch (Exception e) {
            throw new CoreException("Elements " + selector + " does not exist - proceeding");
        }
    }

    //utility to enter text in fields
    public void sendKeys(By selector, String value) {
        try {
            WebElement element = getElement(selector, WaitStrategy.CLICKABLE);
            element.clear();
            element.sendKeys(value);
            ExtentLogger.pass("Value is entered in " + selector + " field.");
        } catch (NoSuchElementException e) {
            ExtentLogger.fail("Error in sending input value in " + selector + ".");
            throw new CoreException("Error in sending " + value + " to the following element:" + selector.toString(), e);
        }
    }

    //utility to click on a particular element
    public void click(By selector) {
        try {
            WebElement element = getElement(selector, WaitStrategy.CLICKABLE);
            element.click();
            ExtentLogger.pass(selector + " is clicked.");
        } catch (NoSuchElementException e) {
            ExtentLogger.fail(selector + " was not clicked.");
            throw new CoreException("The following element is not clickable: " + selector, e);
        }
    }

    //utility to enter text in fields with logging message
    public void sendKeys(By selector, String value, String fieldName) {
        try {
            WebElement element = getElement(selector, WaitStrategy.CLICKABLE);
            element.clear();
            element.sendKeys(value);
            ExtentLogger.pass("Value is entered in '" + fieldName + "' field.");
        } catch (NoSuchElementException e) {
            ExtentLogger.fail("Error in sending input value in '" + fieldName + "'.");
            throw new CoreException("Error in sending " + value + " to the following element:" + fieldName, e);
        }
    }

    //utility to click on a particular element
    public void click(By selector, String elementName) {
        try {
            WebElement element = getElement(selector, WaitStrategy.CLICKABLE);
            element.click();
            ExtentLogger.pass("'" +elementName + "' is clicked.");
        } catch (NoSuchElementException e) {
            ExtentLogger.fail("'" +elementName + "' was not clicked due to some error.");
            throw new CoreException("The following element is not clickable: " + elementName, e);
        }
    }

    //utility to click on a particular element
    public void clickbyJS(By selector) {
        try {
            WebElement element = getElement(selector, WaitStrategy.VISIBLE);
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            ExtentLogger.fail(selector + " was not clicked.");
            throw new CoreException("The following element is not clickable: " + selector, e);
        }
    }

    //utility to handle explicit wait
    public void waitForElement(By selector, WaitStrategy waitStrategy) {
        WebDriverWait wait = new WebDriverWait(getDriver(), CoreConstants.getExplicitwait());
        if (waitStrategy == WaitStrategy.CLICKABLE) {
            wait.until(ExpectedConditions.elementToBeClickable(selector));
        } else if (waitStrategy == WaitStrategy.PRESENCE) {
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
        } else if (waitStrategy == WaitStrategy.VISIBLE) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        }
    }

    //utility to decode Passwords
    public String decodedString(String encodedString) {
        try {
            return new String(Base64.getDecoder().decode(encodedString.getBytes()));
        } catch (IllegalArgumentException e) {
            throw new CoreException("Looks like you forgot to encode the password with BASE64 encoder. Please provide the encoded password in JSON file.");
        }
    }

}
