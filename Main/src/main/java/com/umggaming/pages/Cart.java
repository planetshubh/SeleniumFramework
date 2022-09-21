package com.umggaming.pages;

import org.openqa.selenium.By;

public class Cart {

    By cartTable = By.cssSelector(".table.umg-table");

    By checkout = By.xpath("//a[normalize-space()='Checkout']");
    By continueShopping = By.cssSelector(".link-italic-black");

}
