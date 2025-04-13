package com.ui.tests;

import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.constants.Browser.CHROME;

public class TestBase {
    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());

    @BeforeMethod(description = "Load the Homepage of the website")
    public void setup() {
        logger.info("Load the Homepage of the website");
        homePage = new HomePage(CHROME);
    }

    public BrowserUtility getInstance() {
        return homePage;
    }

    @AfterMethod(description = "Close the browser")
    public void tearDown() {
        logger.info("Closing the browser");
        homePage.closeBrowser();
    }
}
