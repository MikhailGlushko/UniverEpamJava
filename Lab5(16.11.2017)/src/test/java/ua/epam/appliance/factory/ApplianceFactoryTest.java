package ua.epam.appliance.factory;

import org.junit.Test;
import ua.epam.appliance.entity.Appliance;

import static org.junit.Assert.*;

public class ApplianceFactoryTest {
    @Test
    public void getAppliance() throws Exception {
        ApplianceFactory applianceFactory = new ApplianceFactory();
        Appliance appliance = applianceFactory.getAppliance();
        System.out.println(appliance);
    }

}