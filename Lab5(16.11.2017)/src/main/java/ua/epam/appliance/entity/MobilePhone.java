package ua.epam.appliance.entity;

import ua.epam.appliance.criteria.CriteriaField;

/**
 * Мобільний телефон
 */
public class MobilePhone extends ComputerDevice {

    private String camera;

    public MobilePhone(String modelName, int power) {
        super(modelName, power);
    }

    public String getCamera() {
        return camera;
    }

    public MobilePhone setCamera(String camera) {
        this.camera = camera;
        return this;
    }

    /**
     * перевірка наявності поля у обєкта
     * @param criteriaField @see {@link CriteriaField}
     * @return
     */
    @Override
    public boolean hasField(CriteriaField criteriaField) {
        switch (criteriaField) {
            case CAMERA:
                return true;
            default:
                return super.hasField(criteriaField);
        }
    }

    /**
     * Повертає значення поля
     * @param criteriaField @see {@link CriteriaField}
     * @return
     */
    @Override
    public Object getValue(CriteriaField criteriaField) {
        switch (criteriaField) {
            case CAMERA:
                return getCamera();
            default:
                return super.getValue(criteriaField);
        }
    }

    @Override
    public String toString() {
        return "MobilePhone[" +
                "modelName='" + getModelName() + '\'' +
                ", inches=" + getInches() +
                ", CPU='" + getCPU() + '\'' +
                ", batteryCapacity=" + getBatteryCapacity() +
                ", CPUFrequency='" + getCPUFrequency() + '\'' +
                ", memoryCapacity=" + getMemoryCapacity() +
                ", driveCapacity=" + getDriveCapacity() +
                ", camera='" + camera + '\'' +
                ", OS='" + getOS() + '\'' +
                ", power=" + getPower() +
                ']';
    }

}
