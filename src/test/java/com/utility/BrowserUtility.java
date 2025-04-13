package com.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BrowserUtility {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public BrowserUtility(WebDriver driver) {
        super();
        this.driver.set(driver); // Initializing the driver instance variable
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public BrowserUtility(Browser browserName) {
        logger.info("Launching the browser for " + browserName);

        if(browserName == Browser.CHROME) {
            driver.set(new ChromeDriver());
        } else if(browserName == Browser.FIREFOX) {
            driver.set(new FirefoxDriver());
        } else if(browserName == Browser.EDGE) {
            driver.set(new EdgeDriver());
        } else {
            logger.error("Invalid browser name");
            System.err.println("Invalid browser name, please select chrome/firefox/or edge");
        }
    }

    public void goToWebsite(String url) {
        logger.info("Visiting the website " + url);
        driver.get().get(url);
    }

    public void maximizeWindow() {
        logger.info("Maximizing the browser window");
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator) {
        logger.info("Finding element with locator " + locator);
        WebElement element = driver.get().findElement(locator);
        element.click();
        logger.info("Element is found and now performing the click");
    }

    public void enterText(By locator, String textToEnter) {
        logger.info("Finding element with locator " + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now enter the text " + textToEnter);
        element.sendKeys(textToEnter);
    }

    public String getVisibleText(By locator) {
        logger.info("Finding element with locator " + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now return the visible text " + element.getText());
        return element.getText();
    }

    public void closeBrowser() {
        logger.info("Closing the browser");
        driver.get().close();
    }

    public String takeScreenshot(String name) {
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH-MM-SS");
        String timestamp = format.format(date);
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "//screenshots//" + name + "-" + timestamp + ".png";
        File screenshotFile = new File(path);

        try {
            FileUtils.copyFile(screenshotData, screenshotFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}
