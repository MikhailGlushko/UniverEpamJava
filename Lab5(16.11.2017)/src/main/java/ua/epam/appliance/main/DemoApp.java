package ua.epam.appliance.main;

import ua.epam.appliance.criteria.Condition;
import ua.epam.appliance.criteria.CriteriaCondition;
import ua.epam.appliance.criteria.CriteriaField;
import ua.epam.appliance.criteria.Expression;
import ua.epam.appliance.dao.ApplianceDAO;
import ua.epam.appliance.dao.ApplianceDAOImp;
import ua.epam.appliance.entity.Appliance;
import ua.epam.appliance.factory.ApplianceFactory;

import java.util.Comparator;
import java.util.List;

/**
 * Создать консольное приложение, удовлетворяющее следующим требованиям:

 1.	Использовать возможности ООП: классы, наследование, полиморфизм, инкапсуляция.
 2.	Каждый класс должен иметь исчерпывающее смысл название и информативный состав.
 3.	Наследование должно применяться только тогда, когда это имеет смысл.
 4.	При кодировании должны быть использованы соглашения об оформлении кода java code convention.
 5.	Классы должны быть грамотно разложены по пакетам.
 6.	Работа с консолью или консольное меню должно быть минимальным.
 7.	Для хранения параметров инициализации можно использовать файлы.

 3.	Домашние электроприборы. Определить иерархию электроприборов. Включить некоторые в розетку.
 Посчитать потребляемую мощность Провести сортировку приборов в квартире на основе мощности.
 Найти прибор в квартире, соответствующий заданному диапазону параметров.
 */
public class DemoApp {

    public static void main(String[] args) {

        ApplianceFactory applianceFactory = new ApplianceFactory();
        ApplianceDAO applianceDAO = new ApplianceDAOImp();;
        Appliance appliance;

        int coutAppliance = 10;
        try {
            generateAppliances(applianceFactory, applianceDAO, coutAppliance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        showInfoAbourSwichedOnAppliances(applianceDAO);
        showSortedAppliancesByPower(applianceDAO);
        showFilteredAppliance(applianceDAO);

    }

    /**
     * Вивести прибори за заданим фільтром
     * @param applianceDAO
     */
    private static void showFilteredAppliance(ApplianceDAO applianceDAO) {
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

    /**
     * відсортувати прибори за потужністю та вивести їх у відсортованому виді
     * @param applianceDAO
     */
    private static void showSortedAppliancesByPower(ApplianceDAO applianceDAO) {
        List<Appliance> applianceList = applianceDAO.lisAppliance();
        applianceList.sort(Comparator.comparingInt(Appliance::getPower));
        System.out.println("List of all Appliances sorted by power:");
        for (Appliance applianceItem: applianceList) {
            System.out.println(applianceItem);
        }
        System.out.println();
    }

    /**
     * Вивести інформацію про включені в мережу електроприбори та підрахувати сумарну їх потужність
     * @param applianceDAO
     */
    private static void showInfoAbourSwichedOnAppliances(ApplianceDAO applianceDAO) {
        List<Appliance> applianceList = applianceDAO.lisAppliance();
        int power = 0;
        System.out.println("Now swiched on : ");
        for (Appliance applianceItem: applianceList) {
            if(applianceItem.getConsumedPower()!=0) {
                System.out.println(applianceItem);
                power += applianceItem.getConsumedPower();
            }
        }
        System.out.println("Total power is: "+power);
        System.out.println();
    }

    /**
     * Згенерувати список електроприборів
     * @param applianceFactory
     * @param applianceDAO
     * @param coutAppliance
     * @throws InstantiationException
     */
    private static void generateAppliances(ApplianceFactory applianceFactory, ApplianceDAO applianceDAO, int coutAppliance) throws InstantiationException {
        Appliance appliance;
        for (int i = 0; i < coutAppliance; i++) {
            try {
                appliance = applianceFactory.getAppliance();
                applianceDAO.add(appliance);

                int rnd = (int)(Math.random()*2);
                if(rnd!=0)
                    appliance.switchON();

            } catch (IllegalAccessException e) {

            }
        }
    }
}
