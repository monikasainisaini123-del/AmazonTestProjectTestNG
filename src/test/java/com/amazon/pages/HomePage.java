package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    // 1. Locators
    private By accountListLink = By.id("nav-link-accountList");
    private By createAccountBtn = By.id("createAccountSubmit");

    // 2. Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. Actions
    public void clickAccountList() {
        driver.findElement(accountListLink).click();
    }

    public void clickCreateAccount() {
        driver.findElement(createAccountBtn).click();
    }
}