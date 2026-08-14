package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // 1. Locators
    private By emailField = By.id("ap_email");
    private By continueButton = By.id("continue");
    private By passwordField = By.id("ap_password");
    private By alertBox = By.className("a-alert-content");
    private By pageHeader = By.tagName("h1");

    // 2. Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. Actions
    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public String getPageHeaderText() {
        return driver.findElement(pageHeader).getText().trim();
    }

    public boolean isAlertOrPasswordFieldDisplayed() {
        boolean isAlert = driver.findElements(alertBox).size() > 0;
        boolean isPassword = driver.findElements(passwordField).size() > 0;
        return isAlert || isPassword;
    }
}