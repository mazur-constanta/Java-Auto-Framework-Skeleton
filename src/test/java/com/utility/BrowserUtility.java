package com.utility;

import com.constants.Browser;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BrowserUtility {
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private WebDriver driver;

    public BrowserUtility(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public BrowserUtility(Browser browserName) {
        logger.info("Launching the browser for " + browserName);
        if(browserName == Browser.CHROME) {
            driver = new ChromeDriver();
        } else if(browserName == Browser.FIREFOX) {
            driver = new FirefoxDriver();
        } else if(browserName == Browser.EDGE) {
            driver = new EdgeDriver();
        } else {
            logger.error("Invalid browser name");
            System.err.println("Invalid browser name, please select chrome/firefox/or edge");
        }
    }

    public void goToWebsite(String url) {
        logger.info("Visiting the website " + url);
        driver.get(url);
    }

    public void maximizeWindow() {
        logger.info("Maximizing the browser window");
        driver.manage().window().maximize();
    }

    public void clickOn(By locator) {
        logger.info("Finding element with locator " + locator);
        WebElement element = driver.findElement(locator);
        element.click();
        logger.info("Element is found and now performing the click");
    }

    public void enterText(By locator, String textToEnter) {
        logger.info("Finding element with locator " + locator);
        WebElement element = driver.findElement(locator);
        logger.info("Element found and now enter the text " + textToEnter);
        element.sendKeys(textToEnter);
    }

    public String getVisibleText(By locator) {
        logger.info("Finding element with locator " + locator);
        WebElement element = driver.findElement(locator);
        logger.info("Element found and now return the visible text " + element.getText());
        return element.getText();
    }

    public void closeBrowser() {
        logger.info("Closing the browser");
        driver.close();
    }
}
