package com.utility;

import com.github.javafaker.Faker;
import com.ui.pojo.AddressPOJO;

import java.util.Locale;

public class FakeAddressUtility {
    public static AddressPOJO getFakeAddress()
    {
        Faker faker = new Faker(Locale.US);
        AddressPOJO addressPOJO = new AddressPOJO(
                faker.company().name(),
                faker.address().buildingNumber(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.phoneNumber().cellPhone(),
                faker.phoneNumber().cellPhone(),
                faker.phoneNumber().cellPhone(),
                "other" ,
                faker.address().state());
        return addressPOJO;
    }
}
