package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class MyAccountPage extends BrowserUtility {
    private static final By USER_NAME_LOCATOR = By.xpath("//a[@title='View my customer account']/span");

    public MyAccountPage(WebDriver browser) {
        super(browser);
    }

    public String getUserName() {
        return getVisibleText(USER_NAME_LOCATOR);
    }


}
