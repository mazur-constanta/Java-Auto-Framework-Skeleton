package com.ui.tests;

import com.ui.pages.HomePage;
import static org.testng.Assert.assertEquals;

import com.ui.pojo.User;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.constants.Browser.*;

public class LoginTest {
    HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());

    @BeforeMethod(description = "Load the Homepage of the website")
    public void setup() {
        homePage = new HomePage(CHROME);
    }

    @Test(description = "Verify if valid user is able to login into application", groups = {"e2e", "smokeTesting"},
            dataProviderClass = com.ui.dataprodivers.LiginDataProvider.class, dataProvider = "LoginTestDataProvider")
    public void loginTest(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName(), "Linda May");
    }

    @Test(description = "Verify if valid user is able to login into application", groups = {"e2e", "smokeTesting"},
            dataProviderClass = com.ui.dataprodivers.LiginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
    public void loginCSVTest(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName(), "Linda May");
    }

    @Test(description = "Verify if valid user is able to login into application", groups = {"e2e", "smokeTesting"},
            dataProviderClass = com.ui.dataprodivers.LiginDataProvider.class, dataProvider = "LoginTestExcelDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class
    )
    public void loginExcelTest(User user) {
        logger.info("Started Login Excel Test");
            assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName(), "Linda May");
        logger.info("Login Excel Test completed");
    }
}
