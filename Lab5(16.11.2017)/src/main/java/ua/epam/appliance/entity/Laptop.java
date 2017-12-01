package ua.epam.appliance.entity;


import ua.epam.appliance.criteria.CriteriaField;

/**
 * Ноутбук
 */
public class Laptop extends ComputerDevice {

    private String DVD;

    public Laptop(String modelName, int power) {
        super(modelName, power);
    }

    public String getDVD() {
        return DVD;
    }

    public Laptop setDVD(String DVD) {
        this.DVD = DVD;
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
            case DVD:
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
            case DVD:
                return getDVD();
            default:
                return super.getValue(criteriaField);
        }
    }

    @Override
    public String toString() {
        return "Laptop[" +
                "modelName='" + getModelName() + '\'' +
                ", inches=" + getInches() +
                ", CPU='" + getCPU() + '\'' +
                ", CPUFrequency='" + getCPUFrequency() + '\'' +
                ", memoryCapacity=" + getMemoryCapacity() +
                ", driveCapacity=" + getDriveCapacity() +
                ", OS='" + getOS() + '\'' +
                ", DVD='" + DVD + '\'' +
                ", batteryCapacity=" + getBatteryCapacity() +
                ", power=" + getPower() +
                ']';
    }

}
