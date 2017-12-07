package ua.epam.appliance.dao;

import ua.epam.appliance.criteria.Expression;
import ua.epam.appliance.entity.Appliance;

import java.util.List;

public interface ApplianceDAO {
    void add(Appliance appliance);
    void remove(Long id) throws IllegalArgumentException;
    List<Appliance> lisAppliance();
    List<Appliance> listAppliance(Expression expression);
    Appliance getAppliance(Long id);
    Long getSize();
}
