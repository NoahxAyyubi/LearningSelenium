package com.woa.page;

import com.woa.base2.ExtentTestManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Set;

public class HomePage {

    private Logger logger = Logger.getLogger(HomePage.class);
    // Logger logger = Logger.getLogger(HomePage.Class);
    @FindBy(linkText = "Sign in")
    private WebElement signInButton;

    @FindBy(id = "gh-ac")
    private WebElement searchBar;

    @FindBy(id = "gh-btn")
    private WebElement searchButton;

    @FindBy(linkText = "Daily Deals")
    private WebElement dailyDeals;

    @FindBy(id = "gh-cat")
    private WebElement category;

    @FindBy(linkText = "News")
    private WebElement ScrollToSeeNews;

    @FindBy(linkText = "Brand Outlet")
    private WebElement branOutlet;

    @FindBy(linkText = "Electronics")
    private WebElement electronics;
    @FindBy (linkText =  "Video Games")
    private WebElement videoGames;

    private WebDriver driver;


    public void typeOnSearchBar(String data) {
        searchBar.sendKeys(data);
        ExtentTestManager.info("searched for java  books");
        logger.info("some log im adding for ex");
    }

    public void clickOnSearchBar() {
        searchButton.click();
        ExtentTestManager.info("clicked on searchbar");
    }


    public void openInNewTabByDailyDeals() {
        dailyDeals.sendKeys(Keys.CONTROL, Keys.ENTER);
        ExtentTestManager.info("opened link");
        logger.info("log to see logger");
    }

    public void clickOnSignInButton() {
        signInButton.click();
        ExtentTestManager.info("clicked on signIn button");
    }

    public void dropdownCategory(String categories) {

        Select select = new Select(category);
        //perform actions of a dropdown
        //select.selectByIndex(3);
        //select.selectByValue("14339");
        select.selectByVisibleText(categories);
    }

    public void scrollToViewNews() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", ScrollToSeeNews);
    }


    public void openTabsEbayDailyDealsBrandOutlet() {

        dailyDeals.sendKeys(Keys.CONTROL, Keys.ENTER);
        branOutlet.sendKeys(Keys.CONTROL, Keys.ENTER);

        driver.getWindowHandles();

        Set<String> windows = driver.getWindowHandles();
        HashMap<String, String> tabInfo = new HashMap<>();
        HashMap<String, String> zanWay = new HashMap<>();
        System.out.println(windows);
        for (String tabs : windows) {
            driver.switchTo().window(tabs);
            String tabTitles = driver.getTitle();
            System.out.println(tabTitles);
            tabInfo.put(driver.getTitle(), driver.getWindowHandle());
            zanWay.put(tabTitles, tabs);

            System.out.println(zanWay);
            System.out.println(tabInfo);
        }
    }
    public void mouseHoverElec() {

        Actions actions = new Actions(driver);

        actions.moveToElement(electronics).perform();

       videoGames.click();

}

}