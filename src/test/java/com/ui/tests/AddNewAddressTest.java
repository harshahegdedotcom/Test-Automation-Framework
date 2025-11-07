package com.ui.tests;

import com.ui.listeners.TestListener;
import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPOJO;
import com.utility.FakeAddressUtility;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class AddNewAddressTest extends TestBase {
    private MyAccountPage myAccountPage;
    private AddressPage addressPage;
    private AddressPOJO address;

    @BeforeMethod(description = "Valid first time user logs into the application to Save Address")
    public void setup() {
        myAccountPage = homePage.goToLoginPage().doLoginWith("bakomam596@skrak.com", "password");
        address = FakeAddressUtility.getFakeAddress();
    }

    @Test(description = "Verify user registration", groups = {"e2e", "sanity"})
    public void addNewAddress() {
      String newAddress =  myAccountPage.goToAddAddressPage().saveAddress(address);
        Assert.assertEquals(newAddress, address.getAddressAlias().toUpperCase());
    }
}
