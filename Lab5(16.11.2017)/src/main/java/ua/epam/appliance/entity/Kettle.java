package ua.epam.appliance.entity;

import ua.epam.appliance.criteria.CriteriaField;

public class Kettle extends Appliance {
    private int vaterVolume;
    private int boilingTime;

    public Kettle(String modelName, int power) {
        super(modelName, power);
    }

    @Override
    public boolean hasField(CriteriaField criteriaField) {
        switch (criteriaField) {
            case VATER_VOLUME:
            case BOLING_TIME:
                return true;
            default:
                return super.hasField(criteriaField);
        }
    }

    @Override
    public Object getValue(CriteriaField criteriaField) {
        switch (criteriaField) {
            case VATER_VOLUME:
                return getVaterVolume();
            case BOLING_TIME:
                return getBoilingTime();
            default:
                return super.getValue(criteriaField);
        }
    }

    public int getVaterVolume() throws IllegalArgumentException {
        if(vaterVolume<0)
            throw new IllegalArgumentException("Недопустимий обєм");
        return vaterVolume;
    }

    public void setVaterVolume(int vaterVolume) {
        this.vaterVolume = vaterVolume;
    }

    public int getBoilingTime() {
        if(boilingTime<0)
            throw new IllegalArgumentException("Не допустимий час вскипання");
        return boilingTime;
    }

    public void setBoilingTime(int boilingTime) {
        this.boilingTime = boilingTime;
    }

    @Override
    public String toString() {
        return "Kettle{" +
                "modelName='" + getModelName() + '\'' +
                ", vaterVolume=" + vaterVolume +
                ", boilingTime=" + boilingTime +
                ", power=" + getPower() +
                '}';
    }
}
