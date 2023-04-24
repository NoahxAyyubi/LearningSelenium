package com.woa.test;

import com.woa.base2.TestBase2;
import com.woa.page.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EbayTest extends TestBase2 {
    private HomePage homePage;

 @BeforeMethod
public void generatePOM(){
      homePage = PageFactory.initElements(driver, HomePage.class);
 }
    @Test
    public void testRun() {
        //HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.typeOnSearchBar("java books");
        homePage.clickOnSearchBar();
        homePage.openInNewTabByDailyDeals();


    }

    @Test(dataProvider = "getSearchTestData", dataProviderClass = DataForTest.class)
    public void testRun2(String bookName) {
        //HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        //HomePage homepage = PageFactory.initElements(driver, HomPage.Class);
        homePage.typeOnSearchBar(bookName);
        homePage.clickOnSearchBar();
        homePage.openInNewTabByDailyDeals();
    }


    @Test(dataProvider = "getCategories", dataProviderClass = DataForTest.class)
    public void dropdownCategory(String categories) {
        //HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.dropdownCategory(categories);
        //perform actions of a dropdown
        //.selectByIndex(3);
        //.selectByValue("14339");

    }

}

