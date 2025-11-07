package com.ui.tests;

import com.ui.listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({TestListener.class})
public class AddNewAddressTest extends TestBase {


    @Test(description = "Verify user registration", groups = {"e2e","sanity"})
    public void loginValidTest() {

    }
}
