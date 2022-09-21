package com.umggaming.utils;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RenderDynamicLocator {
    private RenderDynamicLocator() {
    }

 /*   --------------->  This method will be deleted as the same is implemented in getByLocator method using Java8 feature.
      --------------->  Keeping it commented until I gain full confidence for the new logic.
    public static By getXpath(String xpath, String value) {
        String newXpath = String.format(String.valueOf(xpath), value);
        By renderedXpath = By.xpath(newXpath);
        return renderedXpath;
    }*/

    //Using Function Interface of Java8
    private static final Map<String, Function<String, By>> map = new HashMap<>();

    private static final Function<String, By> id = (value) -> By.id(value);
    private static final Function<String, By> xpath = (value) -> By.xpath(value);

    static {
        map.put("id", id);
        map.put("xpath", xpath);
    }

    public static By getByLocator(String locatortype, String locator, String value) {
        String newValue = String.format(String.valueOf(locator), value);
        return map.get(locatortype).apply(newValue);
    }
}
