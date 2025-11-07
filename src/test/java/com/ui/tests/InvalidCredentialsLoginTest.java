package com.ui.tests;

import com.ui.listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


@Listeners({TestListener.class})
public class InvalidCredentialsLoginTest extends TestBase {

    private static final String INVALID_EMAIL_ADDRESS = "harsha@gmail.com";
    private static final String INVALID_PASWORD = "Password123";


    @Test(description = "Verify the error mesage with invalid crednetials", groups = {"e2e", "sanity"})
    public void loginInValidTest() {
        logger.info("started test");
        assertEquals(homePage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASWORD)
                .getErrorMessage(),"Authentication failed.");

    }

}
