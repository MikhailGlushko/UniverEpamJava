package ua.epam.appliance.factory;

import ua.epam.appliance.annotation.ApplianceAnnotation;
import ua.epam.appliance.entity.Laptop;

/**
 * Фабрика генерування ноутбуків (Фабричний метод)
 */
@ApplianceAnnotation
public class LaptopFactory extends AbstractApplianceFactory {
    private final String LAPTOP_VENDORS[] = new String[]{"HP", "Lenovo","DELL", "LG","Sony"};
    private final String HDD_VENDORS[]    = new String[]{"Samsung", "Toshiba", "Western Digital"};
    private final int    HDD_CAPACITY[]   = new int[]{2000,1600,1200,1000,800,500,240,160,80};
    private final String MEMORY_VENDORS[] = new String[]{"Samsung", "Kingston", "Corsair","Hynix"};
    private final int    MEMORY_CAPACITY[]= new int[]{32,24,20,22,16,12,10,8,6,4,2,1};
    private final int    MAX_POWER = 100;
    private final int    MIN_POWER = 30;
    private final int    BATTERY_CAPACITY[]=new int[]{7200,5400,2700};
    private final String CPU_TYPE[] = new String[]{"Intel Core I9","Intel Core I7", "Intel Core I5", "Intel Core I3", "Intel Celeron"};
    private final String CPU_FREQUENCY[] = new String[]{"1000","2000","3000"};
    private final String OS[] = new String[]{"Windows 10", "Windows 8", "Windows 7", "Windows XP","Linux","NO"};
    private final double[] INC=new double[]{21,20,19,17,15,14,13,12,11};
    private final String[] DVD=new String[]{"32xDVD RW","32XRVD R","16XCD RW","8X CD R","NO"};

    public LaptopFactory() {
        super();
    }

    /**
     * Повертає екземпляр ноутбука
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public Laptop getAppliance() throws IllegalAccessException {
        int rndIdx = (int)(Math.random()*LAPTOP_VENDORS.length);
        String modelName = LAPTOP_VENDORS[rndIdx];
        int power = (int)(Math.random()*(MAX_POWER-MIN_POWER)+MIN_POWER)/5*5;
        Laptop laptop = new Laptop(modelName,power);

        //CPU
        rndIdx = (int)(Math.random()* CPU_TYPE.length);
        String cpu = CPU_TYPE[rndIdx];
        laptop.setCPU(cpu);

        //CPUFrequency
        rndIdx = (int)(Math.random()*CPU_FREQUENCY.length);
        String cpuFrequency = CPU_FREQUENCY[rndIdx];
        laptop.setCPUFrequency(cpuFrequency);

        //OS
        rndIdx = (int)(Math.random()*OS.length);
        String os = OS[rndIdx];
        laptop.setOS(os);

        //memoryCapacity
        rndIdx = (int)(Math.random()*MEMORY_CAPACITY.length);
        int memoryCapacity = MEMORY_CAPACITY[rndIdx];
        laptop.setMemoryCapacity(memoryCapacity);

        //driveCapacity
        rndIdx = (int)(Math.random()*HDD_CAPACITY.length);
        int driveCapacity = HDD_CAPACITY[rndIdx];
        laptop.setDriveCapacity(driveCapacity);

        //batteryCapacity
        rndIdx = (int)(Math.random()*BATTERY_CAPACITY.length);
        int batteryCapacity = BATTERY_CAPACITY[rndIdx];
        laptop.setBatteryCapacity(batteryCapacity);

        //inches
        rndIdx = (int)(Math.random()*INC.length);
        double inches = INC[rndIdx];
        laptop.setInches(inches);

        //DVD
        rndIdx = (int)(Math.random()*DVD.length);
        String dvd = DVD[rndIdx];
        laptop.setDVD(dvd);

        return laptop;
    }
}
