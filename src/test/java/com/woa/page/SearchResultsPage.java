package com.woa.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage {

    public class SearchResultPage {

        private final Logger logger = Logger.getLogger(SearchResultPage.class);
        @FindBy(id = "dd")
        private WebElement searchResultCount;
    }
}
