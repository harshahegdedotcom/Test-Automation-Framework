package com.ui.tests;

import com.ui.listeners.TestListener;

import static org.testng.Assert.*;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class LoginTest extends TestBase {
    private MyAccountPage myAccountPage;
    private AddressPage addressPage;

    @BeforeMethod(description = "Valid user logs into the application")
    public void setup() {
        myAccountPage = homePage.goToLoginPage().doLoginWith("bakomam596@skrak.com", "password");
    }

    @Test(description = "Verify valid user is able to login into the application", groups = {"e2e","sanity"})
    public void addNewAddress(){
        logger.info("started Add New Address test");
        addressPage = myAccountPage.goToAddAddressPage();
        logger.info("completed Add New Address test");

    }

}
