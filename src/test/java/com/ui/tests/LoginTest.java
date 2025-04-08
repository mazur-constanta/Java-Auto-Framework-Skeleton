package com.ui.tests;

import com.ui.dataprodivers.LoginDataProvider;
import com.ui.pages.HomePage;
import static org.testng.Assert.assertEquals;

import com.ui.pojo.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.constants.Browser.*;

@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest {
    HomePage homePage;

    @BeforeMethod(description = "Load the Homepage of the website")
    public void setup() {
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
}
