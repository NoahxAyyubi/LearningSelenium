package com.woa.test;

import com.woa.base2.TestBase2;
import com.woa.page.HomePage;
import com.woa.page.LoginPage;
import com.woa.page.SearchResultsPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;



    public class POMSetUp extends TestBase2 {

        protected HomePage homePage;
        protected SearchResultsPage.SearchResultPage searchResultPage;
        protected LoginPage loginPage;

        @BeforeMethod(alwaysRun = true)
        public void generatePOM() {
            homePage = PageFactory.initElements(driver, HomePage.class);
            searchResultPage = PageFactory.initElements(driver, SearchResultsPage.SearchResultPage.class);
            loginPage = PageFactory.initElements(driver, LoginPage.class);
        }
    }


