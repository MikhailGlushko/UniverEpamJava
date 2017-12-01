package ua.epam.appliance.factory;

import ua.epam.appliance.annotation.Annotation;
import ua.epam.appliance.annotation.ApplianceAnnotation;
import ua.epam.appliance.entity.Appliance;


/**
 * Фабрика генерування екземплярів Appliance
 *
 * Щукає в заданому пакеті анотовані фабрики Appliance і з їх використанням генеруються випадкові
 * екземпляри Appliance @see {@link Annotation}
 * Для підключення додаткових фабрик необхідно реалізувати фабрику на основі суперкласу @see {@link AbstractApplianceFactory}
 * і помітити її анотацією @ApplianceAnnotation @see {@link ApplianceAnnotation}
 */
public class ApplianceFactory extends AbstractApplianceFactory {
    private final Class<? extends ApplianceAnnotation> annotations[] = new Class[]{ApplianceAnnotation.class};
    private final String packageParh = "ua.epam.appliance.factory";
    private final Annotation annotation = new Annotation(packageParh, annotations);
    private final Object[] listFactories = annotation.getClasses();
    private final int COUNT_TYPE_OF_APPLIANCE = annotation.getCountClassAnnotated();

    public ApplianceFactory(){
        super();
        if (COUNT_TYPE_OF_APPLIANCE==0)
            throw new IllegalArgumentException("не знайдено жодної фабрики в пакеті : "+packageParh);
    }

    /**
     * Повертає екземпляр випадкового пристрою на базі знайдених фабрик
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Override
    public Appliance getAppliance() throws IllegalAccessException, InstantiationException {
        int index = (int)(Math.random()*COUNT_TYPE_OF_APPLIANCE);
        Class clazz = ((Class)listFactories[index]);
        Object instanceOfClass = clazz.newInstance();
        if(! (instanceOfClass instanceof AbstractApplianceFactory))
            throw new IllegalArgumentException("В пакеті знайдено погану фвбрику");
        AbstractApplianceFactory applianceFactory = ((AbstractApplianceFactory)instanceOfClass);
        Appliance appliance = applianceFactory.getAppliance();
        return appliance;
    }
}
