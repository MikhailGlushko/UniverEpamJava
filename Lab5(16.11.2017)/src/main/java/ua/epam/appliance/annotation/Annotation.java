package ua.epam.appliance.annotation;

import java.io.File;
import java.util.*;

/**
 * Клас для роботи з анотаціями
 *
 * Можливості
 * -Знайти всі анотовані класи в пакеті
 * -Перевірити клас чи він анотований вказаною анотацією
 * -Підрахувати кількість класів в заданому пакеті якы анотованы аказаною анотацыэю
 */
public class Annotation {
    private String packagePath;
    private Class<? extends java.lang.annotation.Annotation> annotation[];
    private Object[] classes;

    public Annotation(String packagePath, Class<? extends java.lang.annotation.Annotation> annotation[]) {
        this.packagePath = packagePath;
        this.annotation = annotation;
        listClassWithAnnotation(packagePath, annotation);
    }

    /**
     * пірраховує кількість анотованих класів
     * @return
     */
    public int getCountClassAnnotated(){
        return classes.length;
    }

    /**
     * перевіряє чи вказаний клас анотований
     * @param clazz - клас для перевірки
     * @return
     */
    public boolean isClassAnnotated(Class<?> clazz){
        for (Object object: classes) {
            if (object.equals(clazz))
                return true;
        }
        return false;
    }

    /**
     * Формує масив класів в заданому пакеті які анотовані вказаною анттацією
     * @param pck - пакет для перевірки класів
     * @param annotation - анотація
     */
    private void listClassWithAnnotation(String pck, Class<? extends java.lang.annotation.Annotation> annotation[]) {
        String[] classList = listClassInPackage(pck);
        List<Class<?>> classes = new ArrayList<>();
        if (classList==null || classList.length==0) {
            this.classes = classes.toArray();
            return;
        }
        for (String className: classList) {
            try {
                String classFullName = pck+"."+className;
                classFullName = classFullName.substring(0,classFullName.length()-6);
                Class<?> clazz = Class.forName(classFullName);
                for (int i = 0; i < annotation.length; i++) {
                    boolean annotationPresent = clazz.isAnnotationPresent(annotation[i]);
                    if(annotationPresent)
                        classes.add(clazz);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        this.classes = classes.toArray();
    }

    /**
     * Формує список класів в заданому пакеті
     * @param packagename - пакет для пошуку класів
     * @return
     */
    private String[] listClassInPackage(String packagename) {
        packagename = packagename.replaceAll("\\.","/");

        String path = getClass()
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath()
                .substring(1).replaceAll("%20"," ");

        path = path+packagename;
        String[] fileList = new File(path).list();
        return fileList;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public Class<? extends java.lang.annotation.Annotation>[] getAnnotation() {
        return annotation;
    }

    public Object[] getClasses() {
        return classes;
    }
}
