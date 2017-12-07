package ua.epam.entity;

import org.junit.Test;
import ua.epam.factory.AbstractFigureFactory;
import ua.epam.factory.FigureFactory;

import java.util.Map;

import static org.junit.Assert.*;

public class FigureTest {
    @Test
    public void compareTo() {

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