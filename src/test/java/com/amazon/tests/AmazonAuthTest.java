package com.amazon.tests;

import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AmazonAuthTest extends BaseTest {
    HomePage homePage = new HomePage(getDriver());
    LoginPage loginPage = new LoginPage(getDriver());

    @DataProvider(name = "invalidLoginCredentials", parallel = true)
    public Object[][] getLoginData() {
        return new Object[][]{
                {"invalid_user_1@example.com", "WrongPassword1"},
                {"invalid_user_2@example.com", "WrongPassword2"}
        };
    }

    @Test(priority = 1,groups = {"smoke"},timeOut = 30000)
    public void verifyRegistrationPage() {
        homePage.clickAccountList();
        homePage.clickCreateAccount();
    // just add steps to do registration not adding because OTP verification required
        Assert.assertTrue(loginPage.getPageHeaderText().contains("Create account"),
                "Failed to navigate to Create Account page!");
    }

    @Test(priority = 2 )
    public void verifySignInPageTitle() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickAccountList();

        Assert.assertTrue(getDriver().getTitle().contains("Amazon"), "Title mismatch!");
    }

//    @Test(
//            priority = 3,
//            description = "Expect NoSuchElementException when looking for non-existent ID",
//            expectedExceptions = {NoSuchElementException.class}
//    )
//    public void testNonExistentElement() {
//        getDriver().findElement(By.id("this-id-does-not-exist")).click();
//    }

    @Test(priority = 3,dataProvider = "invalidLoginCredentials")
    public void testParallelLoginStress(String email, String password) {
        homePage.clickAccountList();
        loginPage.enterEmail(email);
        loginPage.clickContinue();

        Assert.assertTrue(loginPage.isAlertOrPasswordFieldDisplayed(),
                "Expected alert message or password field after entering username.");
    }
}