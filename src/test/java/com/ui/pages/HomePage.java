package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import static com.utility.PropertiesUtil.*;

import com.utility.JSONUtility;
import org.openqa.selenium.By;

public final class HomePage extends BrowserUtility {
    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(), 'Sign in')]");

    public HomePage(Browser browser) {
        super(browser);
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
    }

    public LoginPage goToLoginPage() {
        /** Page functions cannot return the void type */
        clickOn(SIGN_IN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver());
        return loginPage;
    }
}
