package ua.epam.appliance.factory;

import org.junit.Test;
import ua.epam.appliance.entity.Appliance;

import static org.junit.Assert.*;

public class KettleFactoryTest {
    @Test
    public void getAppliance() throws Exception {
        KettleFactory kettleFactory= new KettleFactory();
        Appliance kettle = kettleFactory.getAppliance();
        System.out.println(kettle);
    }

}