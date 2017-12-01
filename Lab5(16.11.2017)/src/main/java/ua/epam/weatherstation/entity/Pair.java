package ua.epam.weatherstation.entity;

/**
 * інформація про погодні дані
 */
public class Pair implements Cloneable{
    private String value;
    private String unit;

    public Pair(String value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public Pair() {
    }

    public String getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;

        if (getValue() != null ? !getValue().equals(pair.getValue()) : pair.getValue() != null) return false;
        return getUnit() != null ? getUnit().equals(pair.getUnit()) : pair.getUnit() == null;
    }

    @Override
    public int hashCode() {
        int result = getValue() != null ? getValue().hashCode() : 0;
        result = 31 * result + (getUnit() != null ? getUnit().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
