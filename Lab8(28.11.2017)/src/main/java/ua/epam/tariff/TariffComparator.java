package ua.epam.tariff;

import ua.epam.tariff.Tariff.Plan;

import java.util.Comparator;

/**
 * Compares tariffs by their value
 */
public class TariffComparator implements Comparator<Plan> {

    /**
     * Compares two Plan objects by price.
     * @param plan1
     * @param plan2
     * @return positive value if plan1 > plan2, negative value if plan1 < plan2,
     * zero if plan1 is equal to plan2.
     */
    @Override
    public int compare(Plan plan1, Plan plan2) {
        if (plan1.getPayroll() > plan2.getPayroll()) {
            return 1;
        } else if (plan1.getPayroll() < plan2.getPayroll()) {
            return -1;
        } else {
            return 0;
        }
    }
}