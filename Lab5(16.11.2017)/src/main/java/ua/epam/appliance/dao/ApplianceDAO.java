package ua.epam.appliance.dao;

import ua.epam.appliance.criteria.Expression;
import ua.epam.appliance.entity.Appliance;

import java.util.List;

public interface ApplianceDAO {
    public void add(Appliance appliance);
    public void remove(Long id) throws IllegalArgumentException;
    public List<Appliance> lisAppliance();
    public List<Appliance> listAppliance(Expression expression);
    public Appliance getAppliance(Long id);
    public Long getSize();
}
