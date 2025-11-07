package com.ui.tests;

import com.ui.listeners.TestListener;
import com.ui.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.constants.Size.L;

@Listeners({TestListener.class})
public class ProductCheckoutTest extends TestBase {

    private static final String SEARCH_TERM = "Printed Summer Dress";
    private SearchResultPage searchResultPage;

    @BeforeMethod(description = "Valid user logs into the application")
    public void setup() {
        homePage.goToLoginPage().doLoginWith("bakomam596@skrak.com", "password")
                .searchForAProduct(SEARCH_TERM);
    }

    @Test(description = "Verify Logged in user is able to buy a dress", groups = {"e2e", "sanity"})
    public void checkoutTest() {
        String result = searchResultPage.clickOnTheProductAtIndex(0)
                .changeSize(L)
                .addProductToCart()
                .proceedToCheckout()
                .goToConfirmAddressPage()
                .goToShipmentPage()
                .goToPaymentPage()
                .makePaymentByWire();
        Assert.assertTrue(result.contains("complete"));

    }
}
