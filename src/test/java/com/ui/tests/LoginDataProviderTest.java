package com.ui.tests;

import com.ui.dataproviders.LoginDataProvider;
import com.ui.listeners.RetryAnalyzer;
import com.ui.pages.HomePage;
import com.ui.pojo.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.constants.Browser.CHROME;
import static org.testng.Assert.assertEquals;


public class LoginDataProviderTest {
    HomePage homePage;
    @BeforeMethod(description = "Load the home page of the website")
    public void setup()
    {
        homePage = new HomePage(CHROME, true);
    }

    @Test(description = "Verify valid user is able to login into the application", groups = {"e2e","sanity"}, dataProviderClass = LoginDataProvider.class, dataProvider = "LoginTestJSONDataProvider")
    public void loginJSONTest(User user){
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword())
                .getUserName(), "harsha");
    }
    @Test(description = "Verify valid user is able to login into the application", groups = {"e2e","sanity"}, dataProviderClass = LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
    public void loginCSVTest(User user){
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword())
                .getUserName(), "harsha");
    }
    @Test(description = "Verify valid user is able to login into the application", groups = {"e2e","sanity"}, dataProviderClass = LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider",retryAnalyzer = RetryAnalyzer.class)
    public void loginExcelTest(User user){
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword())
                .getUserName(), "harsha");
    }
}
