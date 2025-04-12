package com.ui.tests;

import com.ui.dataprodivers.LoginDataProvider;
import com.ui.pages.HomePage;
import static org.testng.Assert.assertEquals;

import com.ui.pojo.User;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.constants.Browser.*;

@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest {
    HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());

    @BeforeMethod(description = "Load the Homepage of the website")
    public void setup() {
        logger.info("Load the Homepage of the website");
        homePage = new HomePage(CHROME);
    }

    @Test(description = "Verify if valid user is able to login into application with json", groups = {"e2e", "smokeTesting"},
            dataProviderClass = LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
    public void loginTest(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName(), "Linda May");
    }

    @Test(description = "Verify if valid user is able to login into application with csv", groups = {"e2e", "smokeTesting"},
            dataProviderClass = LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
    public void loginCSVTest(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName(), "Linda May");
    }

    @Test(description = "Verify if valid user is able to login into application with xlsx", groups = {"e2e", "smokeTesting"},
            dataProviderClass = LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
    public void loginExcelTest(User user) {
            assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName(), "Linda May");
    }

    @AfterMethod(description = "Close the browser")
    public void closeTheBrowser() {
        logger.info("Close the browser");
        homePage.closeBrowser();
    }
}
