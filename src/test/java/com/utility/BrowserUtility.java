package com.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BrowserUtility {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private Logger logger = LoggerUtility.getLogger(this.getClass());
    private WebDriverWait wait;

    public WebDriver getDriver() {
        return driver.get();

    }

    public BrowserUtility(WebDriver driver) {
        super();
        this.driver.set(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
    }

    public BrowserUtility(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            driver.set(new ChromeDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));

        } else if (browserName.equalsIgnoreCase("edge")) {
            driver.set(new EdgeDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));

        } else {
            System.out.println("invalid browser");
        }
    }

    public BrowserUtility(Browser browserName, Boolean isHeadLess) {
        if (browserName == Browser.CHROME) {
            if (isHeadLess) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));

            } else {
                driver.set(new ChromeDriver());
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));

            }
        } else if (browserName == Browser.EDGE) {
            if (isHeadLess) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                driver.set(new EdgeDriver(options));
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));

            } else {
                driver.set(new EdgeDriver());
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
            }
        } else if (browserName == Browser.FIREFOX) {
            if (isHeadLess) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                driver.set(new FirefoxDriver(options));
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));

            } else {
                driver.set(new FirefoxDriver());
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));

            }
        } else {
            System.out.println("invalid browser");
        }
    }

    public void goToWebsite(String url) {
        driver.get().get(url);
    }

    public void maximizeWindow() {
        driver.get().manage().window().maximize();
    }

    public void clearText(By locator) {
        logger.info("Clearing text locator " + locator);
        WebElement element = driver.get().findElement(locator);
        element.clear();
    }

    public void clickOn(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
    public void clickOnCheckBox(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.click();
    }

    public void clickOn(WebElement element) {
        element.click();
    }

    public void selectFromDropDown(By dropDownLocator, String optionToSelect) {
        logger.info("Finding an element with the locator " + dropDownLocator);
        WebElement element = driver.get().findElement(dropDownLocator);
        Select select = new Select(element);
        select.selectByVisibleText(optionToSelect);
        logger.info("Selecting the option " + optionToSelect);
    }

    public void enterText(By locator, String textToEnter) {
        logger.info("Finding an element with the locator " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element found and now enter " + locator);
        element.sendKeys(textToEnter);
    }

    public void enterSpecialKey(By locator, Keys keyToEnter) {
        logger.info("Finding an element with the locator " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Found element and now enter special " + keyToEnter);
        element.sendKeys(keyToEnter);
    }

    public String getVisibleText(By locator) {
        WebElement element = driver.get().findElement(locator);
        return element.getText();
    }

    public String getVisibleText(WebElement element) {
        return element.getText();
    }

    public List<String> getAllVisibleText(By locator) {
        logger.info("Finding all elements with the locator " + locator);
        List<WebElement> elementList = driver.get().findElements(locator);
        logger.info("Printing all elements with the locator " + locator);
        List<String> visibleTextList = new ArrayList<String>();

        for (WebElement element : elementList) {
            visibleTextList.add(getVisibleText(element));
        }
        return visibleTextList;
    }

    public List<WebElement> getAllElements(By locator) {
        logger.info("Finding all elements with the locator " + locator);
        List<WebElement> elementList = driver.get().findElements(locator);
        logger.info("Printing all elements with the locator " + locator);
        return elementList;
    }

    public String takeScreenShot(String name) {
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        String timeStamp = format.format(date);
        String path = "./screenshots/" + name + " - " + timeStamp + ".png";
        File screenShotFile = new File(path);
        try {
            FileUtils.copyFile(screenshotData, screenShotFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public void quit() {
        driver.get().quit();
    }
}
