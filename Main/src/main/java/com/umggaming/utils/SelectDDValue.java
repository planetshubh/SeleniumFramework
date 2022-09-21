package com.umggaming.utils;

import com.core.base.CoreBase;
import com.core.enums.WaitStrategy;
import com.core.utilities.CoreUtils;
import com.umggaming.constants.SelectBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.function.Consumer;

public class SelectDDValue {
    CoreUtils utils = new CoreUtils(CoreBase.getDriver());

    public void selectDropdownValue(By element, String value, SelectBy strategy) {
        Select select = new Select(utils.getElement(element, WaitStrategy.CLICKABLE));
        Consumer<Select> consumer;
        switch (strategy) {
            case VALUE:
                consumer = (e) -> e.selectByValue(value);                       //Definition for Select by value.
                consumer.accept(select);
                break;

            case INDEX:
                consumer = (e) -> e.selectByIndex(Integer.parseInt(value));     //Definition for Select by Index.
                consumer.accept(select);
                break;

            case VISIBILETEXT:
                consumer = (e) -> e.selectByVisibleText(value);                 //Definition for Select by visible text.
                consumer.accept(select);
                break;
        }
    }
}