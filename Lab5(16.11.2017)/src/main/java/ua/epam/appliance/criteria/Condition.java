package ua.epam.appliance.criteria;

/**
 * Умови для фільтрування записів
 */
public class Condition {
    private CriteriaField field;
    private CriteriaCondition condition;
    private Object value;

    /**
     * Створюємо нову умову
     * @param field      поле @see {@link CriteriaField}
     * @param condition  умова @see {@link CriteriaCondition}
     * @param value      значення
     */
    public Condition(CriteriaField field, CriteriaCondition condition, Object value) {
        this.field = field;
        this.condition = condition;
        this.value = value;
    }

    public CriteriaField getField() {
        return field;
    }

    public void setField(CriteriaField field) {
        this.field = field;
    }

    public CriteriaCondition getCondition() {
        return condition;
    }

    public void setCondition(CriteriaCondition condition) {
        this.condition = condition;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return  " " + field +
                " " + condition +
                " " + value;
    }
}
