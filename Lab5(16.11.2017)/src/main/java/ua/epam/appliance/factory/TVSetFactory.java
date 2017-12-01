package ua.epam.appliance.factory;

import ua.epam.appliance.annotation.ApplianceAnnotation;
import ua.epam.appliance.entity.TVSet;

/**
 * Фабрика генерування телевізорів (Фабричний метод)
 */
@ApplianceAnnotation
public class TVSetFactory extends AbstractApplianceFactory {

    private final String TVSET_VENDORS[] = new String[]{"Lenovo","LG","Sony","Samsung"};
    private final int    MAX_POWER = 10;
    private final int    MIN_POWER = 100;
    private final double[] INC=new double[]{21,20,19,17,15,14,13,12,11};
    private final String[] SCREEN_RESOLUTION = new String[]{"1280х720", "1366х768", "1400х900" , "1680х1050", "1920x1080", "3840х2160"};
    private final String[] SCREEN_REFRESH_RATE = new String[]{"100","90","80","60","55"};
    private final String[] DIGITAL_TUNER = new String[]{"DVB-C","DVB-C2","DVB-S","DVB-T","DVB-T2","ATSC","ISDB"};

    public TVSetFactory() {
        super();
    }

    /**
     * Повертає екземпляр телевізора
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public TVSet getAppliance() throws IllegalAccessException {
        int rndIdx = (int)(Math.random()* TVSET_VENDORS.length);
        String modelName = TVSET_VENDORS[rndIdx];
        int power = (int)(Math.random()*(MAX_POWER-MIN_POWER)+MIN_POWER)/5*5;
        TVSet tvSet = new TVSet(modelName,power);

        //inches
        rndIdx = (int)(Math.random()*INC.length);
        double inches = INC[rndIdx];
        tvSet.setInches(inches);

        //screenResolution
        rndIdx = (int)(Math.random()*SCREEN_RESOLUTION.length);
        String screenResolution = SCREEN_RESOLUTION[rndIdx];
        tvSet.setScreenResolution(screenResolution);

        //screenRefreshRate
        rndIdx = (int)(Math.random()*SCREEN_REFRESH_RATE.length);
        String screenRefreshRate = SCREEN_REFRESH_RATE[rndIdx];
        tvSet.setScreenRefreshRate(screenRefreshRate);

        // digitalTuner
        rndIdx = (int)(Math.random()*DIGITAL_TUNER.length);
        String digitalTuner = DIGITAL_TUNER[rndIdx];
        tvSet.setDigitalTuner(digitalTuner);

        return tvSet;
    }
}
