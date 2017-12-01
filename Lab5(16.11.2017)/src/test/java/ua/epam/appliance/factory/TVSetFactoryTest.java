package ua.epam.appliance.factory;

import org.junit.Test;
import ua.epam.appliance.entity.Appliance;

import static org.junit.Assert.*;

public class TVSetFactoryTest {
    @Test
    public void getAppliance() throws Exception {
        TVSetFactory tvSetFactory = new TVSetFactory();
        Appliance tvSet = tvSetFactory.getAppliance();
        System.out.println(tvSet);
    }

}