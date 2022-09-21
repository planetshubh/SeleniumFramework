package com.umggaming.pages;

import org.openqa.selenium.By;

public class StoreUmgCash {

    By viewProdBtn = By.xpath("//a[normalize-space()='View Product']");
    By selectCash = By.cssSelector("select[name='cash']");
    By selectQuantity = By.id("quantity");
    By addToCart = By.id("add");

}
