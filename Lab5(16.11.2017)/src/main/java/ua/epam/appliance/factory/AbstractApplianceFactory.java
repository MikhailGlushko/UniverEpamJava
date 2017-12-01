package ua.epam.appliance.factory;

import ua.epam.appliance.entity.Appliance;

abstract public class AbstractApplianceFactory {
    abstract public Appliance getAppliance() throws IllegalAccessException, InstantiationException;
}
