package com.ui.tests;
import static com.constants.Browser.*;

import com.ui.listeners.TestListener;
import com.ui.pages.HomePage;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class LoginTest extends TestBase {


    @Test(description = "Verify valid user is able to login into the application", groups = {"e2e","sanity"})
    public void loginValidTest(){
        logger.info("started test");
        assertEquals(homePage.goToLoginPage().doLoginWith("bakomam596@skrak.com","password")
                .getUserName(), "Jatin Sharma");
        logger.info("completed test");

    }
    @Test(description = "Verify valid user is able to login into the application", groups = {"e2e","sanity"})
        public void loginInvalidTest(){
        logger.info("started test");
        assertEquals(homePage.goToLoginPage().doLoginWith("bakomam596@skrak.com","password")
                .getUserName(), "Jatin Sharma1");
        logger.info("completed test");

    }
}
