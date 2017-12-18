package ua.epam;

import ua.epam.entity.*;
import ua.epam.factory.FigureFactory;

import java.util.Map;

/**
 * 1. создать последовательность из фигур  используя фабрики цветных и нецветных фигур
 2. получить масивы цветных и не цветных и сколько каждых фигур в нем присутсвует
 3. групируем в масивах фигуры
 4. тестируем методы фигур
 */
public class FiguresExample2 {

    public static void main(String[] args) {

        FigureFactory factory = new FigureFactory();
        Figure[] figures = new Figure[20];

        System.out.println("Generate "+figures.length+" figures using "+factory.getClass().getName()+" : \n");
        for (int i=0; i<figures.length; i++){
            figures[i] = factory.getFigure(FigureFactory.FiguresType.COLORED_AND_NO_COLORED);
            System.out.println(figures[i]);
        }

        System.out.println("\nSorting and group by FigureType:");
        Figure.sortFiguresMethod2(figures);
        Figure.showSortedFiguresMethod2(figures);
        Map<String, Integer> mapByClass = Figure.calculateCountFiguresByType(figures);
        System.out.println("Total by Type    : "+mapByClass);
        Map<String, Integer> mapByColored = Figure.calculateCountFiguresQuality(figures);
        System.out.println("Total by Colored : "+mapByColored);

    }
}
