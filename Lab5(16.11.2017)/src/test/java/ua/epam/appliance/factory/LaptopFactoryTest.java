package ua.epam.appliance.factory;

import org.junit.Test;
import ua.epam.appliance.entity.Appliance;

import static org.junit.Assert.*;

public class LaptopFactoryTest {
    @Test
    public void getAppliance() throws Exception {
        LaptopFactory laptopFactory = new LaptopFactory();
        Appliance laptop = laptopFactory.getAppliance();
        System.out.println(laptop);
    }

}