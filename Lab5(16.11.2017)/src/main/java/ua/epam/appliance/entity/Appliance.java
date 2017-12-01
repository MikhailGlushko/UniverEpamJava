package ua.epam.appliance.entity;

import ua.epam.appliance.criteria.Condition;
import ua.epam.appliance.criteria.CriteriaCondition;
import ua.epam.appliance.criteria.CriteriaField;

/**
 * Суперклас для всіх пристроїв
 */
abstract public class Appliance implements Comparable<Appliance>{
    private long id;
    private String modelName;
    private int power;
    private boolean isSwiched;

    /**
     * перевіряє чи обєкт має вказані атрибути
     * @param criteriaField @see {@link CriteriaField}
     * @return
     */
    public boolean hasField(CriteriaField criteriaField){
        switch (criteriaField) {
            case ID:
            case MODEL_NAME:
            case POWER:
            case SWICHED:
                return true;
            default:
                return false;
        }
    }

    /**
     * Повертає значення вказаного атрибуту
     * @param criteriaField @see {@link CriteriaField}
     * @return
     */
    public Object getValue(CriteriaField criteriaField) {
        switch (criteriaField) {
            case ID:
                return getId();
            case MODEL_NAME:
                return getModelName();
            case POWER:
                return getPower();
            case SWICHED:
                return isSwiched();
            default:
                return null;
        }
    }

    public Appliance(String modelName, int power) {
        if(modelName==null || modelName.isEmpty() || power<=0)
            throw new IllegalArgumentException("Помилка в аргументах при еонструюванні єбєкта "+this.getClass().getSimpleName());
        this.modelName = modelName;
        this.power = power;
        this.isSwiched = false;
    }

    public boolean isSwiched() {
        return isSwiched;
    }

    /**
     * включити прибор в електромережу
     * @throws IllegalAccessException
     */
    public void switchON() throws IllegalAccessException {
        if(isSwiched==true)
            throw new IllegalAccessException(this.getModelName()+" вже включено");
        else
            isSwiched=true;

    }

    /**
     * Відключити прибор від електромережі
     * @throws IllegalAccessException
     */
    public void switchOff() throws IllegalAccessException {
        if(isSwiched==false)
            throw new IllegalAccessException(this.getModelName()+" вже виключено");
        else
            isSwiched = false;
    }

    /**
     * Повернути споживаючу потужність
     * @return
     */
    public int getConsumedPower(){
        if(isSwiched==true)
            return getPower();
        return 0;
    }

    public int getPower() {
        return power;
    }

    public String getModelName() {
        return modelName;
    }

    @Override
    public int compareTo(Appliance other) {
        return this.getConsumedPower() - other.getConsumedPower();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) throws IllegalArgumentException {
        if(id<0)
            throw new IllegalArgumentException("не допустимий ідентифікатор");
        this.id = id;
    }
}
