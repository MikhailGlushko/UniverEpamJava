package ua.epam;

import ua.epam.entity.*;
import ua.epam.factory.FigureFactory;

/**
 * 1. создать последовательность из фигур  используя фабрики цветных и нецветных фигур
 2. получить масивы цветных и не цветных и сколько каждых фигур в нем присутсвует
 3. групируем в масивах фигуры
 4. тестируем методы фигур
 */
public class FiguresExample {

    private Figure[] figures = new Figure[20];

    public static void main(String[] args) {

        FigureFactory factory = new FigureFactory();
        FiguresExample example = new FiguresExample();

        example.generateFiguresArray(factory);
        example.showFiguresGroupByColor();
        example.showFiguresGroupByClass();

    }

    public void generateFiguresArray(FigureFactory factory) {
        System.out.println("Генеруємо "+figures.length+" фігур з використанням "+factory.getClass().getName()+" : \n");
        for (int i=0; i<figures.length; i++){
            figures[i] = factory.getFigure(FigureFactory.FiguresType.COLORED_AND_NO_COLORED);
            System.out.println(figures[i]);
        }
    }

    public void showFiguresGroupByClass(){
        System.out.println("\nВиводимо інформацію про фігури в кожній групі\n");
        ShowOneClass(figures, Point.class);
        ShowOneClass(figures, Line.class);
        ShowOneClass(figures, Triangle.class);
        ShowOneClass(figures, Poligon.class);
    }

    private void ShowOneClass(Figure[] figures, Class<?> cls) {
        System.out.println(cls.getSimpleName()+"'s :");
        int count = 0;
        for (int i = 0; i < figures.length; i++) {
            if(cls.isInstance(figures[i])){
                System.out.println(figures[i]);
                count++;
            }
        }
        System.out.println("Total : "+String.valueOf(count));
        System.out.println();
    }

    public void showFiguresGroupByColor() {
        Figure[] colored = new Figure[figures.length];
        Figure[] notColored = new Figure[figures.length];
        int c = 0;
        int nc = 0;
        for (int i = 0; i < figures.length ; i++) {
            if(figures[i] instanceof ColoredFigure){
                colored[c++] = figures[i];
            } else {
                notColored[nc++] = figures[i];
            }
        }
        System.out.println("\nВиводимо інформацію про фігури\n");
        showFigures(colored,"Кольорові: ");
        showFigures(notColored, "Не кольорові: ");
    }

    private void showFigures(Figure[] figures, String title) {
        System.out.println(title);
        int count = 0;
        for (int i = 0; i < figures.length; i++) {
            if (figures[i]==null)
                break;
            System.out.println(figures[i]);
            count++;
        }
        System.out.println("Total : "+String.valueOf(count));
        System.out.println();
    }
}
