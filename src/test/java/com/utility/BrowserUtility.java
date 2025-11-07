package com.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BrowserUtility {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();


    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        super();
        this.driver.set(driver);
    }

//    public BrowserUtility(String browserName) {
//        if (browserName.equalsIgnoreCase("chrome")) {
//            driver.set(new ChromeDriver());
//        } else if (browserName.equalsIgnoreCase("edge")) {
//            driver.set(new EdgeDriver());
//        } else {
//            System.out.println("invalid browser");
//        }
//    }

    public BrowserUtility(Browser browserName, Boolean isHeadLess) {
        if (browserName == Browser.CHROME ) {
            if(isHeadLess) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
            }
            else
            {
                driver.set(new ChromeDriver());
            }
        } else if (browserName == Browser.EDGE) {
            if(isHeadLess) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                driver.set(new EdgeDriver(options));
            }
            else
            {
                driver.set(new EdgeDriver());
            }
        } else if (browserName == Browser.FIREFOX) {
            if(isHeadLess) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                driver.set(new FirefoxDriver(options));
            }
            else
            {
                driver.set(new FirefoxDriver());
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

    public void clickOn(By locator) {
        WebElement element = driver.get().findElement(locator);
        element.click();
    }

    public void enterText(By locator, String textToEnter) {
        WebElement element = driver.get().findElement(locator);
        element.sendKeys(textToEnter);
    }

    public String getVisibleText(By locator) {
        WebElement element = driver.get().findElement(locator);
        return element.getText();
    }

    public String takeScreenShot(String name) {
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        String timeStamp = format.format(date);
        String path = "./screenshots/" + name +" - "+ timeStamp+  ".png";
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
