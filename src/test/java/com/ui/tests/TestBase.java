package com.ui.tests;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {
    protected  HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private  Boolean isLambdaTest;
    @Parameters({"browser","isLambdaTest", "isHeadLess"})
    @BeforeMethod(description = "Load the home page of the website")
    public void setup(
            @Optional("chrome") String browser,
            @Optional("false") Boolean isLambdaTest,
            @Optional("false") Boolean isHeadLess, ITestResult result)
    {
        this.isLambdaTest = isLambdaTest;
        WebDriver lambdaDriver;
        if (isLambdaTest)
        {
            lambdaDriver= LambdaTestUtility.initializeLambdaTestSession(result.getMethod().getMethodName(), browser);
            homePage = new HomePage(lambdaDriver);

        }
        else {
            logger.info("Load home page of the website");
            homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadLess);
        }
    }

    public BrowserUtility getInstance()
    {
        return homePage;
    }
//    @AfterMethod(description = "Tear Down the browser")
//    public void tearDown()
//    {
//        if(isLambdaTest)
//        {
//            LambdaTestUtility.quitSession();
//        }
//        else {
//            homePage.quit();
//        }
//    }
}
