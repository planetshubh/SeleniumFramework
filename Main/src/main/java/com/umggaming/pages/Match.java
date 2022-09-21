package com.umggaming.pages;

import com.core.base.CoreBase;
import com.core.enums.WaitStrategy;
import com.core.utilities.CoreUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Match extends CoreBase {
    CoreUtils utils = new CoreUtils(getDriver());

    By confirmationMsg = By.className("jq-toast-heading");

    public WebElement getConfirmationMsg() {
        return utils.getElement(confirmationMsg, WaitStrategy.VISIBLE);
    }
}
