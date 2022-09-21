package com.umggaming.pages;

import com.core.utilities.CoreUtils;
import org.openqa.selenium.By;

import static com.core.base.CoreBase.getDriver;

public class Bank {
    CoreUtils utils = new CoreUtils(getDriver());

    By addCreditsBtn = By.xpath("//a[normalize-space()='Add Credits']");
    By makeDepositBtn = By.xpath("//a[normalize-space()='Make a Deposit']");


}
