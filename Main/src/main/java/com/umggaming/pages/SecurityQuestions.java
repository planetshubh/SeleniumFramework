package com.umggaming.pages;

import com.core.base.CoreBase;
import com.core.enums.WaitStrategy;
import com.core.utilities.CoreUtils;
import com.umggaming.constants.SelectBy;
import com.umggaming.utils.RenderDynamicLocator;
import com.umggaming.utils.SelectDDValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SecurityQuestions extends CoreBase {
    CoreUtils utils = new CoreUtils(getDriver());
    SelectDDValue localUtils = new SelectDDValue();

    By heading = By.cssSelector(".black.margin-20.text-center");
    By continueBtn = By.cssSelector("button[class='btn btn-lg btn-blue']");
    String question = "question_%s";
    String answer = "answer_%s";
    By cmWarningDialog = By.xpath("//div[@id='creatematch']//div[@class='modal-body']");
    By cmWarningDialogClose = By.xpath("//div[@role='document']//button[@aria-label='Close']");
    By cmWarningDialogLink1 = By.xpath("//div[@id='creatematch']//div[@class='modal-body']//div//p//a");
    By ctWarningDialog = By.xpath("//div[@id='createateam']//div[@class='modal-body']");
    By ctWarningDialogClose = By.xpath("//div[@id='createateam']//button[@aria-label='Close']");
    By ctWarningDialogLink1 = By.xpath("//div[@id='createateam']//div[@class='modal-body']//div//p//a");

    public SecurityQuestions selectQuestion(String question_num, String questionToSelect) {
        By field = RenderDynamicLocator.getByLocator("id", question, question_num);
        localUtils.selectDropdownValue(field, questionToSelect, SelectBy.VALUE);
        return this;
    }

    public SecurityQuestions enterAnswer(String answer_num, String answerToEnter) {
        By field = RenderDynamicLocator.getByLocator("id", answer, answer_num);
        utils.sendKeys(field, answerToEnter);
        return this;
    }

    public WebElement getHeading() {
        return utils.getElement(heading, WaitStrategy.VISIBLE);
    }

    public ActivateAccount clickContinue() {
        utils.click(continueBtn);
        return new ActivateAccount();
    }

    public WebElement getCmWarningDialog() {
        return utils.getElement(cmWarningDialog, WaitStrategy.VISIBLE);
    }

    public SecurityQuestions closeCmWarningDialog() {
        utils.click(cmWarningDialogClose);
        return this;
    }

    public WebElement getCtWarningDialog() {
        return utils.getElement(ctWarningDialog, WaitStrategy.VISIBLE);
    }

    public SecurityQuestions closeCtWarningDialog() {
        utils.click(ctWarningDialogClose);
        return this;
    }

    public SecurityQuestions clickCmWarningDialogLink1() {
        utils.click(cmWarningDialogLink1);
        return this;
    }

    public SecurityQuestions clickCtWarningDialogLink1() {
        utils.click(ctWarningDialogLink1);
        return this;
    }
}