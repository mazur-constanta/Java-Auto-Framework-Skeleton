package com.ui.tests;

import com.ui.dataprodivers.LoginDataProvider;
import static org.testng.Assert.assertEquals;

import com.ui.pojo.User;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest extends TestBase {

    @Test(description = "Verify if valid user is able to login into application with json", groups = {"e2e", "smokeTesting"},
            dataProviderClass = LoginDataProvider.class, dataProvider = "LoginTestJsonProvider")
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
}
