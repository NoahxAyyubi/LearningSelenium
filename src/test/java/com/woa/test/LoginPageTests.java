package com.woa.test;

import org.testng.annotations.Test;

public class LoginPageTests extends POMSetUp {

    @Test(enabled = true, groups = "SMOKE")
    public void validateUserCanSeeLoginPage1() {
        homePage.clickOnSignInButton();
        loginPage.validateSignInPageDisplayed();
    }

    @Test(dependsOnMethods = "validateUserCanSeeLoginPage1")
    public void validateUserCanSeeLoginPage2() {
        homePage.clickOnSignInButton();
        loginPage.validateSignInPageDisplayed();
    }


}
