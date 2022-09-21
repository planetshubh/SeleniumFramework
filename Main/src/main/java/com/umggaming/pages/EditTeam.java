package com.umggaming.pages;

import com.core.base.CoreBase;
import com.core.enums.WaitStrategy;
import com.core.utilities.CoreUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EditTeam extends CoreBase {
    CoreUtils utils = new CoreUtils(getDriver());

    By confirmationMsg = By.className("jq-toast-heading");
    By closeSMsg = By.xpath("//span[@class='close-jq-toast-single']");
    By eligibilityTM1 = By.xpath("//th[normalize-space()='Eligibility']/following::span[6]");
    By createMatchLink = By.xpath("//a[normalize-space()='Create Match']");

    public WebElement getConfirmationMsg() {
        return utils.getElement(confirmationMsg, WaitStrategy.PRESENCE);
    }

    public WebElement getEligibility() {
        return utils.getElement(eligibilityTM1, WaitStrategy.PRESENCE);
    }

    public EditTeam clickCloseSMsg() {
        utils.click(closeSMsg);
        return this;
    }

    public CreateAMatch clickCreateMatch() {
        utils.click(createMatchLink);
        return new CreateAMatch();
    }
}

