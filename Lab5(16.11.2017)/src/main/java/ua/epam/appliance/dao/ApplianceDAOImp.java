package ua.epam.appliance.dao;

import ua.epam.appliance.criteria.Condition;
import ua.epam.appliance.criteria.CriteriaCondition;
import ua.epam.appliance.criteria.CriteriaField;
import ua.epam.appliance.criteria.Expression;
import ua.epam.appliance.entity.Appliance;

import java.util.*;

/**
 * Клас для зберігання екземплярів Appliance
 */
public class ApplianceDAOImp implements ApplianceDAO {
    private Map<Long, Appliance> applianceMap = new LinkedHashMap<>();
    private long index = 0;

    /**
     * Добавити екземпляр в колекцію
     * @param appliance
     */
    @Override
    public void add(Appliance appliance) {
        long id = ++index;
        appliance.setId(id);
        applianceMap.put(id,appliance);
    }

    /**
     * Видалити екземпляр з колекції
     * @param id
     * @throws IllegalArgumentException
     */
    @Override
    public void remove(Long id) throws IllegalArgumentException {
        if(applianceMap.remove(id)==null)
            throw new IllegalArgumentException("Елемента з таким номером не існує");
    }

    /**
     * Отримати список всіх елементів в колекції
     * @return
     */
    @Override
    public List<Appliance> lisAppliance() {
        return new ArrayList<Appliance>(applianceMap.values());
    }

    /**
     * Отримати з колекції лише записи, що відповідають заданим критеріям
     * @param expression @see {@link Expression}
     * @return
     */
    public List<Appliance> listAppliance(Expression expression){
        List<Appliance> list = new ArrayList<>();
        for (long i = 1; i <= applianceMap.size(); i++) {
            Appliance appliance = applianceMap.get(i);
            Iterator<Condition> iterator = expression.iterator();

            boolean result = false;
            while (iterator.hasNext()){
                Condition condition = iterator.next();
                CriteriaField criteriaField = condition.getField();
                CriteriaCondition criteriaCondition = condition.getCondition();
                Object criteriaValue = condition.getValue();

                Object resultValue = appliance.getValue(criteriaField);
                if(resultValue==null)
                    break;

                result = true;
                switch (criteriaCondition){
                    case EQUAL:
                        if(resultValue instanceof String) {
                            result = resultValue.equals(criteriaValue);
                            if(result==false)
                                break;
                        } else {
                            if(resultValue instanceof Double)
                                result = resultValue.equals(criteriaValue);
                            else
                                result = resultValue.equals(criteriaValue);
                        }
                        break;
                    case NOT_EQUAL:
                        if(resultValue instanceof String){
                            result = resultValue.equals(criteriaValue);
                            if(result==true)
                                break;
                        } else {
                            if(resultValue instanceof Double)
                                result = !resultValue.equals(criteriaValue);
                            else
                                result = !resultValue.equals(criteriaValue);
                        }
                        break;
                    case MORE:
                        if(resultValue instanceof String)
                            break;
                        else {
                            if(resultValue instanceof Double)
                                result = ((Double) resultValue) > (Double) criteriaValue;
                            else
                                result = ((Integer) resultValue) > (Integer) criteriaValue;
                        }
                        break;
                    case MORE_OR_EQUAL:
                        if(resultValue instanceof String)
                            break;
                        else {
                            if(resultValue instanceof Double)
                                result = ((Double) resultValue) >= (Double) criteriaValue;
                            else
                                result = ((Integer) resultValue) >= (Integer) criteriaValue;
                        }
                        break;
                    case LESS:
                        if(resultValue instanceof String)
                            break;
                        else {
                            if(resultValue instanceof Double)
                                result = ((Double) resultValue) < (Double) criteriaValue;
                            else
                                result = ((Integer) resultValue) < (Integer) criteriaValue;
                        }
                        break;
                    case LESS_OR_EQUAL:
                        if(resultValue instanceof String)
                            break;
                        else {
                            if(resultValue instanceof Double)
                                result = ((Double) resultValue) <= (Double) criteriaValue;
                            else
                                result = ((Integer) resultValue) <= (Integer) criteriaValue;
                        }
                        break;
                    case BETWEEN:
                        if(resultValue instanceof String)
                            break;
                        break;
                    case NOT_BETWEEN:
                        if(resultValue instanceof String)
                            break;
                        break;
                }
                if(result==false)
                    break;
            }
            if(result==true)
                list.add(appliance);
        }
        return list;
    }

    /**
     * Отримати екземпляр з колекції за його номером
     * @param id
     * @return
     */
    @Override
    public Appliance getAppliance(Long id) {
        return applianceMap.get(id);
    }

    @Override
    public Long getSize() {
        return (long) applianceMap.size();
    }
}
