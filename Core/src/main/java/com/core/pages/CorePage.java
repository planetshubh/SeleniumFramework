package com.core.pages;

import com.core.base.CoreBase;
import com.core.constants.CoreConstants;
import com.core.enums.WaitStrategy;

import com.core.utilities.CoreUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class is to support the test cases in CoreTest class, it captures the element of the website pages.
 * This is also an example of Page class in Page object, which will be used in Main project.
 *
 * @author Shubhendra Singh
 */

public class CorePage extends CoreBase {

    public CorePage(WebDriver driver) {
    }

    //Elements of the Login page:
    //--------------------------------------------
    CoreUtils coreUtils = new CoreUtils(getDriver());

    By loginBtn = By.xpath("//a[@class='btn-header']");

    By username = By.id("login");

    By password = By.xpath("//div[@class='modal-content']//input[@id='password']");

    By loginAcct = By.cssSelector("button[class='btn btn-primary btn-your-account']");

    By loggedUser = By.xpath("//strong[@class='name']");

    By dashLink = By.xpath("//a[normalize-space()='Dashboard']");

    //Functionalities of the Login page:
    //--------------------------------------------
    public String loginPageTitle() {
        return getDriver().getTitle();
    }

    public boolean login(String un, String pwd) {
        if (coreUtils.verifyElementExists(loginBtn)) {
            coreUtils.click(loginBtn, "Login button");
            //ExtentManager.getExtentTest().pass("Login is clicked.");
            coreUtils.sendKeys(username, un, "Username");
            coreUtils.sendKeys(password, pwd, "Password");
            coreUtils.click(loginAcct, "Login to your account");
            return coreUtils.getElement(loggedUser, WaitStrategy.CLICKABLE).isDisplayed();
        }
        return false;
    }

    public void dashNav() {
        coreUtils.clickbyJS(loggedUser);
        coreUtils.click(dashLink);

    }
}
