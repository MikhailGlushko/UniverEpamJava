package ua.epam.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

abstract public class Figure implements Comparable<Figure>{

    @Override
    public int compareTo(Figure other) {
        Class<Figure> f1 = (Class<Figure>) this.getClass();
        Class<Figure> f2 = (Class<Figure>) other.getClass();
        if(f1==f2 || f1.getSuperclass()==f2 || f2.getSuperclass()==f1)
            return 0;
        if(this instanceof Point){
            if(!(other instanceof Point))
                return -1;
            else
                return 1;
        } else if(this instanceof Line){
            if(!(other instanceof Line || other instanceof Point))
                return -1;
            else
                return 1;
        } else if(this instanceof Triangle){
            if(!(other instanceof Triangle || other instanceof Point || other instanceof Line))
                return -1;
            else
                return 1;
        } if(this instanceof Poligon){
            if(!(other instanceof Poligon))
                return 1;
        }

        return 0;
    }

    public static void sortFiguresMethod2(Figure[] figures) {
        Arrays.sort(figures);
    }

    public static void showSortedFiguresMethod2(Figure[] figures) {
        Class<Figure> clazz = (Class<Figure>) figures[0].getClass();
        if (!clazz.getSuperclass().getSimpleName().equals("Figure")){
            clazz = (Class<Figure>) clazz.getSuperclass();
        }
        System.out.println("\n"+clazz.getSimpleName()+"'s :");
        int count =0;

        for (int i = 0; i < figures.length; i++) {
            count ++;
            Class<Figure> clazzTemp = (Class<Figure>) figures[i].getClass();
            if (!clazzTemp.getSuperclass().getSimpleName().equals("Figure")){
                clazzTemp = (Class<Figure>) clazzTemp.getSuperclass();
            }
            if(!clazz.getSimpleName().equals(clazzTemp.getSimpleName())) {
                System.out.println("Total : "+ String.valueOf(--count));
                count=1;
                clazz = clazzTemp;
                System.out.println("\n"+clazz.getSimpleName()+"'s :");
            }
            System.out.println(figures[i]);
        }
        System.out.println("Total : "+ String.valueOf(count));
        System.out.println();
    }

    public static Map<String,Integer> calculateCountFiguresByType(Figure[] figures){
        Map<String,Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < figures.length; i++) {
            Class<Figure> clazzTemp = (Class<Figure>) figures[i].getClass();
            if (!clazzTemp.getSuperclass().getSimpleName().equals("Figure")){
                clazzTemp = (Class<Figure>) clazzTemp.getSuperclass();
            }
            Integer value = map.get(clazzTemp.getSimpleName());
            if (value==null)
                value = 1;
            else
                value++;
            map.put(clazzTemp.getSimpleName(),value);
        }
        return map;
    }

    public static Map<String, Integer> calculateCountFiguresQuality(Figure[] figures) {
        Map<String,Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < figures.length; i++) {
            String key = "";
            if(figures[i] instanceof ColoredFigure)
                key = "Colored";
            else
                key = "Not Colored";

            Integer value = map.get(key);
            if (value==null)
                value = 1;
            else
                value++;
            map.put(key,value);
        }
        return map;
    }
}
