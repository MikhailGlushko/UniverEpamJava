package ua.epam.appliance.entity;

import ua.epam.appliance.criteria.CriteriaField;

public class TVSet extends Appliance {
    private double inches;
    private String screenResolution;
    private String screenRefreshRate;
    private String digitalTuner;

    public TVSet(String modelName, int power) {
        super(modelName, power);
    }

    @Override
    public boolean hasField(CriteriaField criteriaField) {
        switch (criteriaField) {
            case INCHES:
            case SCREEN_RESOLUTION:
            case SCREEN_REFRESH_RATE:
            case DIGITAL_TUNER:
                return true;
            default:
                return super.hasField(criteriaField);
        }
    }

    @Override
    public Object getValue(CriteriaField criteriaField) {
        switch (criteriaField) {
            case INCHES:
                return getInches();
            case SCREEN_RESOLUTION:
                return getScreenResolution();
            case SCREEN_REFRESH_RATE:
                return getScreenRefreshRate();
            case DIGITAL_TUNER:
                return getDigitalTuner();
            default:
                return super.getValue(criteriaField);
        }
    }

    public TVSet setInches(double inches) {
        this.inches = inches;
        return this;
    }

    public TVSet setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
        return this;
    }

    public TVSet setScreenRefreshRate(String screenRefreshRate) {
        this.screenRefreshRate = screenRefreshRate;
        return this;
    }

    public TVSet setDigitalTuner(String digitalTuner) {
        this.digitalTuner = digitalTuner;
        return this;
    }

    public double getInches() {
        return inches;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public String getScreenRefreshRate() {
        return screenRefreshRate;
    }

    public String getDigitalTuner() {
        return digitalTuner;
    }

    @Override
    public String toString() {
        return "TVSet[" +
                "modelName='" + getModelName() + '\'' +
                ", inc=" + inches +
                ", screenResolution='" + screenResolution + '\'' +
                ", screenRefreshRate='" + screenRefreshRate + '\'' +
                ", digitalTuner='" + digitalTuner + '\'' +
                ", power=" + getPower() +
                ']';
    }

}
