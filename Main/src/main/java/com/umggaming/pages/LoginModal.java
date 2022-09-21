package com.umggaming.pages;

import com.core.base.CoreBase;
import com.core.enums.WaitStrategy;
import com.core.utilities.CoreUtils;
import org.openqa.selenium.By;

public class LoginModal extends CoreBase {

    CoreUtils utils = new CoreUtils(getDriver());

    By loginBtn = By.xpath("//a[@class='btn-header']");
    By username = By.id("login");
    By password = By.xpath("//div[@class='modal-content']//input[@id='password']");
    By loginAccnt = By.cssSelector("button[class='btn btn-primary btn-your-account']");
    By loggedUser = By.xpath("//strong[@class='name']");
    By rememberMe = By.className("border-switch-control-indicator");
    By termsOfUseLink = By.linkText("Terms of Use");
    By privacyOfPolicyLink = By.xpath("//a[@class='link-italic'][normalize-space()='Privacy Policy']");
    By confirmationMsg = By.xpath("//div[@class='jq-toast-single animated fadeInUpBig']");
    By closeSMsg = By.xpath("//span[@class='close-jq-toast-single']");

    public LoginModal clickLoginBtn() {
        utils.click(loginBtn);
        return this;
    }

    public LoginModal enterUnPwd(String un, String pwd) {
        utils.sendKeys(username, un);
        pwd = utils.decodedString(pwd);
        utils.sendKeys(password, pwd);
        return this;
    }

    public LoginModal clickLoginAccnt() {
        utils.click(loginAccnt);
        return this;
    }

    public boolean isLoggedIn() {
        return utils.verifyElementExists(loggedUser);
    }

    public String getConfirmationMsg() {
        return utils.getElement(confirmationMsg, WaitStrategy.PRESENCE).getText().substring(2);
    }

    public LoginModal clickCloseSMsg() {
        utils.click(closeSMsg);
        return this;
    }

    public LoginModal clickRememberMe() {
        utils.click(rememberMe);
        return this;
    }

    public LoginModal clickTermsOfUseLink() {
        utils.click(termsOfUseLink);
        return this;
    }

    public LoginModal clickPrivacyOfPolicyLink() {
        utils.click(privacyOfPolicyLink);
        return this;
    }
}
