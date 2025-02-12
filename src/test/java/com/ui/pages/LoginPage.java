package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class LoginPage extends BrowserUtility {
    private static final By EMAIL_TEXTBOX_LOCATOR = By.id("email");
    private static final By PASSWORD_TEXTBOX_LOCATOR = By.id("passwd");
    private static final By SUBMIT_LOGIN_LOCATOR = By.id("SubmitLogin");

    public LoginPage(WebDriver browser) {
        super(browser);
    }

    public MyAccountPage doLoginWith(String email, String password) {
        enterText(EMAIL_TEXTBOX_LOCATOR, email);
        enterText(PASSWORD_TEXTBOX_LOCATOR, password);
        clickOn(SUBMIT_LOGIN_LOCATOR);

        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        return myAccountPage;
    }
}
