package com.umggaming.pages;

import com.core.base.CoreBase;
import com.core.enums.WaitStrategy;
import com.core.utilities.CoreUtils;
import com.umggaming.constants.SelectBy;
import com.umggaming.utils.RenderDynamicLocator;
import com.umggaming.utils.SelectDDValue;
import org.openqa.selenium.By;

public class RegisterUser extends CoreBase {
    CoreUtils utils = new CoreUtils(getDriver());
    SelectDDValue localUtils = new SelectDDValue();

    By email = By.id("email");
    By username = By.id("username");
    By timezone = By.name("timezone");
    By birthMonth = By.id("month");
    By birthDay = By.id("day");
    By birthYear = By.id("year");
    By pwd = By.id("password");
    By confirmPwd = By.id("password_confirmation");
    By termsCheckBx = By.xpath("//input[@name='privacy']/parent::div");
    By privacyCheckBx = By.xpath("//input[@name='terms']/parent::div");
    By tncLink = By.linkText("Terms and Conditions");
    By privacyLink1 = By.xpath("//a[normalize-space()='Privacy Policy'][1]");
    By privacyLink2 = By.xpath("//a[normalize-space()='Privacy Policy'][2]");
    By registerBtn = By.id("register_btn");
    By loginLink = By.linkText("Log in");
    By umgLogo = By.xpath("//img[@alt='UMG Gaming Logo']");

    String errorMsgsId = "%s-error";

    public RegisterUser enterEmail(String emailId) {
        utils.sendKeys(email, emailId);
        return this;
    }

    public RegisterUser enterUsername(String un) {
        utils.sendKeys(username, un);
        return this;
    }

    public RegisterUser selectTimezone(String timezone, SelectBy strategy) {
        localUtils.selectDropdownValue(this.timezone, timezone, strategy);
        return this;
    }

    public RegisterUser selectBirthMonth(String birthmm, SelectBy strategy) {
        localUtils.selectDropdownValue(this.birthMonth, birthmm, strategy);
        return this;
    }

    public RegisterUser selectBirthDay(String birthdd, SelectBy strategy) {
        localUtils.selectDropdownValue(this.birthDay, birthdd, strategy);
        return this;
    }

    public RegisterUser selectBirthYear(String birthyy, SelectBy strategy) {
        localUtils.selectDropdownValue(this.birthYear, birthyy, strategy);
        return this;
    }

    public RegisterUser enterPwd(String pass) {
        utils.sendKeys(pwd, utils.decodedString(pass));
        return this;
    }

    public RegisterUser enterConfirmPwd(String cpass) {
        utils.sendKeys(confirmPwd, utils.decodedString(cpass));
        return this;
    }

    public RegisterUser clickTermsCheckBx() {
        utils.click(termsCheckBx);
        return this;
    }

    public RegisterUser clickPrivacyCheckBx() {
        utils.click(privacyCheckBx);
        return this;
    }

    public RegisterUser clickTncLink() {
        utils.click(tncLink);
        return this;
    }

    public RegisterUser clickPrivacyLink1() {
        utils.click(privacyLink1);
        return this;
    }

    public RegisterUser clickPrivacyLink2() {
        utils.click(privacyLink2);
        return this;
    }

    public RegisterUser clickRegisterBtn() {
        utils.click(registerBtn);
        return this;
    }

    public RegisterUser clickLoginLink() {
        utils.click(loginLink);
        return this;
    }

    public HomePage clickUmgLogo() {
        utils.click(umgLogo);
        return new HomePage();
    }

    public String getErrorMsg(String fieldName) {
        return utils.getElement(RenderDynamicLocator.getByLocator("id", errorMsgsId, fieldName), WaitStrategy.PRESENCE).getText();
    }
}