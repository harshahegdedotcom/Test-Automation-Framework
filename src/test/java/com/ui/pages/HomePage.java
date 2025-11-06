package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;

import org.openqa.selenium.By;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import org.openqa.selenium.WebDriver;

public final class HomePage extends BrowserUtility {
    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[normalize-space()='Sign in']");
    public HomePage(WebDriver driver) {
        super(driver);
        goToWebsite(JSONUtility.readJSON(QA).getUrl()); // This is to read from JSON file

    }
    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName,isHeadless);
        //goToWebsite(readProperty(QA, "URL")); // This is to read from Property File
        goToWebsite(JSONUtility.readJSON(QA).getUrl()); // This is to read from JSON file
    }

    public LoginPage goToLoginPage() {
        clickOn(SIGN_IN_LINK_LOCATOR);
        return new LoginPage(getDriver());
    }
}
