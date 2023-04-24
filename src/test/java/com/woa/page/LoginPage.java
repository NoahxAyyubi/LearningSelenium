package com.woa.page;

import com.woa.base2.ExtentTestManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage {
    @FindBy(id = "gh-logo")
    private WebElement EbayLogo;

    public void validateSignInPageDisplayed() {
        Assert.assertTrue(EbayLogo.isDisplayed(), "Ebay login page isn't displayed");
        ExtentTestManager.info("Validated Ebay Sign in Page");
    }
}


