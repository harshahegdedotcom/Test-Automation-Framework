package com.ui.tests;

import com.ui.listeners.TestListener;
import com.ui.pages.MyAccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class SearchProductTest extends TestBase {
    private MyAccountPage myAccountPage;
    private static final String SEARCH_TERM = "Printed Summer Dress";

    @BeforeMethod(description = "Valid user logs into the application")
    public void setup() {
        myAccountPage = homePage.goToLoginPage().doLoginWith("bakomam596@skrak.com", "password");
    }

    @Test(description = "Verify logged in user is able to search for right product", groups = {"e2e", "sanity", "smoke"})
    public void verifyProductSearchTest() {
        boolean actualResult = myAccountPage.searchForAProduct(SEARCH_TERM).isSearchTermPresentInProductList(SEARCH_TERM);
        Assert.assertEquals(actualResult, true);
    }
}
