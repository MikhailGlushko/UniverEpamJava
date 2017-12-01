package ua.epam.appliance.dao;

import org.junit.Before;
import org.junit.Test;
import ua.epam.appliance.criteria.Condition;
import ua.epam.appliance.criteria.CriteriaCondition;
import ua.epam.appliance.criteria.CriteriaField;
import ua.epam.appliance.criteria.Expression;
import ua.epam.appliance.entity.Appliance;
import ua.epam.appliance.factory.ApplianceFactory;
import ua.epam.appliance.factory.LaptopFactory;

import java.util.List;

import static org.junit.Assert.*;

public class ApplianceDAOImpTest {

    ApplianceFactory applianceFactory;
    Appliance appliance;
    ApplianceDAO applianceDAO;

    @Before
    public void createAppliance() throws IllegalAccessException {
        applianceFactory = new ApplianceFactory();
        applianceDAO = new ApplianceDAOImp();
    }

    @Test
    public void add() throws Exception {
        appliance = applianceFactory.getAppliance();
        applianceDAO.add(appliance);
        Long id = appliance.getId();
        Appliance result = applianceDAO.getAppliance(id);
        assertEquals(result,appliance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeWrondID() throws Exception {
        applianceDAO.remove(1L);
    }

    @Test
    public void remove() throws Exception {
        appliance = applianceFactory.getAppliance();
        applianceDAO.add(appliance);
        Long id = appliance.getId();
        applianceDAO.remove(id);
        Long count = applianceDAO.getSize();
        Long expected = 0L;
        assertEquals(count,expected);
    }

    @Test
    public void lisAppliance() throws Exception {
        appliance = applianceFactory.getAppliance();
        applianceDAO.add(appliance);
        List<Appliance> list = applianceDAO.lisAppliance();
        int count = list.size();
        int expected = 1;
        assertEquals(count,expected);
    }

    @Test
    public void getAppliance() throws Exception {
        appliance = applianceFactory.getAppliance();
        applianceDAO.add(appliance);
        Appliance expected = applianceDAO.getAppliance(appliance.getId());
        boolean result = expected.equals(appliance);
        assertEquals(result,true);
    }

    @Test
    public void listApplainceFilter() throws InstantiationException, IllegalAccessException {
        appliance = applianceFactory.getAppliance();
        applianceDAO.add(appliance);
        List<Appliance> list = applianceDAO.lisAppliance();

        Expression expression = new Expression();
        Condition condition = new Condition(CriteriaField.POWER, CriteriaCondition.LESS_OR_EQUAL, 100);
        expression.add(condition);
        condition = new Condition(CriteriaField.POWER, CriteriaCondition.MORE_OR_EQUAL, 10);
        expression.add(condition);
        List<Appliance> applianceList = applianceDAO.listAppliance(expression);
        System.out.println("Умова для відбору : "+expression);
        for (Appliance appl:applianceList) {
            System.out.println(appl);
        }
    }
}