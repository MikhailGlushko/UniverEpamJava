package ua.epam.appliance.factory;

import ua.epam.appliance.annotation.ApplianceAnnotation;
import ua.epam.appliance.entity.Kettle;

/**
 * Фаьрика для генерування Чайників (Фабричний метод)
 */
@ApplianceAnnotation
public class KettleFactory extends AbstractApplianceFactory {

    private final String KETTLE_VENDORS[] = new String[]{"Gorenje", "Bosh","Braun", "Mulinex","Philips","Elenberg"};
    private final int    VATER_VOLUMES[]  = new int[]{1000,1500,2000};
    private final int    BOLING_TIME[]    = new int[]{5,10,15};
    private final int    MAX_POWER = 3000;
    private final int    MIN_POWER = 1000;

    public KettleFactory() {
        super();
    }

    /**
     * Повертає екземпляр чайника
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public Kettle getAppliance() throws IllegalAccessException {
        int rndIdx = (int)(Math.random()* KETTLE_VENDORS.length);
        String modelName = KETTLE_VENDORS[rndIdx];
        int power = (int)(Math.random()*(MAX_POWER-MIN_POWER)+MIN_POWER)/100*100;
        Kettle kettle = new Kettle(modelName,power);

        //vaterVolume;
        rndIdx = (int)(Math.random()*VATER_VOLUMES.length);
        int vaterVolume = VATER_VOLUMES[rndIdx];
        kettle.setVaterVolume(vaterVolume);

        //boilingTime;
        rndIdx = (int)(Math.random()*BOLING_TIME.length);
        int boilingTime = BOLING_TIME[rndIdx]*(kettle.getVaterVolume()/1000);
        kettle.setBoilingTime(boilingTime);

        return kettle;
    }
}
