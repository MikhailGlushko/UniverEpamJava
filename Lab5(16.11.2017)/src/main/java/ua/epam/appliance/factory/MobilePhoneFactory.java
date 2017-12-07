package ua.epam.appliance.factory;

import ua.epam.appliance.annotation.ApplianceAnnotation;
import ua.epam.appliance.entity.MobilePhone;

/**
 * Фабрика генерування мобільних телефонів (Фабричний метод)
 */
@ApplianceAnnotation
public class MobilePhoneFactory extends AbstractApplianceFactory {

        private final String MOBILE_VENDORS[] = new String[]{"Lenovo","LG","Sony","Samsung"};
        private final int    FLASH_CAPACITY[]   = new int[]{64,32,16,8,4,2,1};
        private final int    MEMORY_CAPACITY[]= new int[]{32,24,20,22,16,12,10,8,6,4,2,1};
        private final int    MAX_POWER = 10;
        private final int    MIN_POWER = 1;
        private final int    BATTERY_CAPACITY[]=new int[]{2700,2000,1500,1000};
        private final String CPU_TYPE[] = new String[]{"Qualcomm","Huawei", "Samsung", "Mediatek"};
        private final String CPU_FREQUENCY[] = new String[]{"1000","2000","3000"};
        private final String OS[] = new String[]{"Android", "Windows Mobile"};
        private final double[] INC=new double[]{7,6,5,4};
        private final String[] CAMERA = new String[]{"10","8","6","5","2"};

    public MobilePhoneFactory() {
        super();
    }

    @Override
        public MobilePhone getAppliance() {
            int rndIdx = (int)(Math.random()*MOBILE_VENDORS.length);
            String modelName = MOBILE_VENDORS[rndIdx];
            int power = (int)(Math.random()*(MAX_POWER-MIN_POWER)+MIN_POWER);
            MobilePhone phone = new MobilePhone(modelName,power);

            //CPU
            rndIdx = (int)(Math.random()* CPU_TYPE.length);
            String cpu = CPU_TYPE[rndIdx];
            phone.setCPU(cpu);

            //CPUFrequency
            rndIdx = (int)(Math.random()*CPU_FREQUENCY.length);
            String cpuFrequency = CPU_FREQUENCY[rndIdx];
            phone.setCPUFrequency(cpuFrequency);

            //OS
            rndIdx = (int)(Math.random()*OS.length);
            String os = OS[rndIdx];
            phone.setOS(os);

            //memoryCapacity
            rndIdx = (int)(Math.random()*MEMORY_CAPACITY.length);
            int memoryCapacity = MEMORY_CAPACITY[rndIdx];
            phone.setMemoryCapacity(memoryCapacity);

            //driveCapacity
            rndIdx = (int)(Math.random()*FLASH_CAPACITY.length);
            int driveCapacity = FLASH_CAPACITY[rndIdx];
            phone.setDriveCapacity(driveCapacity);

            //batteryCapacity
            rndIdx = (int)(Math.random()*BATTERY_CAPACITY.length);
            int batteryCapacity = BATTERY_CAPACITY[rndIdx];
            phone.setBatteryCapacity(batteryCapacity);

            //inches
            rndIdx = (int)(Math.random()*INC.length);
            double inches = INC[rndIdx];
            phone.setInches(inches);

            //camera
            rndIdx = (int)(Math.random()*CAMERA.length);
            String camera = CAMERA[rndIdx];
            phone.setCamera(camera);

            return phone;
        }
}
