package ua.epam.appliance.entity;

import ua.epam.appliance.criteria.CriteriaField;

/**
 * Суперклас для компютеризованих приборів
 */
abstract public class ComputerDevice extends Appliance {
    private String CPU;
    private String CPUFrequency;
    private String OS;
    private int memoryCapacity;
    private int driveCapacity;
    private int batteryCapacity;
    private double inches;

    public ComputerDevice(String modelName, int power) {
        super(modelName, power);
    }

    /**
     * перевірка наявності поля у обєкта
     * @param criteriaField @see {@link CriteriaField}
     * @return
     */
    @Override
    public boolean hasField(CriteriaField criteriaField) {
        switch (criteriaField){
            case OS:
            case CPU_FREQUENCY:
            case CPU:
            case MEMORY_CAPACITY:
            case DRIVE_CAPACITY:
            case BATTERY_CAPACITY:
            case INCHES:
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
        switch (criteriaField){
            case OS:
                return getOS();
            case CPU_FREQUENCY:
                return getCPUFrequency();
            case CPU:
                return getCPU();
            case MEMORY_CAPACITY:
                return getMemoryCapacity();
            case DRIVE_CAPACITY:
                return getDriveCapacity();
            case BATTERY_CAPACITY:
                return getBatteryCapacity();
            case INCHES:
                return getInches();
            default:
                return super.getValue(criteriaField);
        }
    }

    public ComputerDevice setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        return this;
    }

    public ComputerDevice setOS(String OS){
        this.OS = OS;
        return this;
    }

    public ComputerDevice setDriveCapacity(int driveCapacity){
        this.driveCapacity = driveCapacity;
        return this;
    }

    public ComputerDevice setMemoryCapacity(int memoryCapacity){
        this.memoryCapacity = memoryCapacity;
        return this;
    }

    public ComputerDevice setCPUFrequency(String CPUFrequency){
        this.CPUFrequency = CPUFrequency;
        return this;
    }

    public ComputerDevice setCPU(String CPU){
        this.CPU = CPU;
        return this;
    }

    public ComputerDevice setInches(double inches){
        this.inches = inches;
        return this;
    }

    public double getInches() {
        return inches;
    }

    public String getCPU() {
        return CPU;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public String getCPUFrequency() {
        return CPUFrequency;
    }

    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public int getDriveCapacity() {
        return driveCapacity;
    }

    public String getOS() {
        return OS;
    }

}
