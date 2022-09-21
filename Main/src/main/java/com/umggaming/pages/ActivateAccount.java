package com.umggaming.pages;

import com.core.base.CoreBase;
import com.core.enums.WaitStrategy;
import com.core.utilities.CoreUtils;
import org.openqa.selenium.By;

public class ActivateAccount extends CoreBase {
    CoreUtils utils = new CoreUtils(getDriver());

    By gdprConfirmModal = By.xpath("//div[@id='gdprConfirmModal']//div[@class='modal-body']");
    By gdprAcceptBtn = By.cssSelector("#gdprsuccess");
    By verifyEmail = By.cssSelector(".c-heading");
    By sendEmailAgainBtn = By.cssSelector(".btn.btn-blue");
    By userEmailId = By.cssSelector("div[class='vm-box'] p strong");

    public ActivateAccount clicksendEmailAgainBtn() {
        utils.click(sendEmailAgainBtn);
        return this;
    }

    public String getVerifyEmailHdr() {
        return utils.getElement(verifyEmail, WaitStrategy.PRESENCE).getText();
    }

    public String getUserEmailId() {
        return utils.getElement(userEmailId, WaitStrategy.PRESENCE).getText();
    }

    public String getgdprConfirmModalMsg() {
        return utils.getElement(gdprConfirmModal, WaitStrategy.PRESENCE).getText();
    }

    public ActivateAccount clickgdprAcceptBtn() {
        utils.click(gdprAcceptBtn);
        return this;
    }
}
