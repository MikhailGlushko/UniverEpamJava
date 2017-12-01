package ua.epam.appliance.factory;

import org.junit.Test;
import ua.epam.appliance.entity.Appliance;

import static org.junit.Assert.*;

public class MobilePhoneFactoryTest {
    @Test
    public void getAppliance() throws Exception {
        MobilePhoneFactory phoneFactory = new MobilePhoneFactory();
        Appliance mobilePhone = phoneFactory.getAppliance();
        System.out.println(mobilePhone);
    }

}