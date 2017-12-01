package ua.epam;

/**
 * (C) Mikhail Glushko
 * mikhail.glushko@gmail.com
 *
 * Використовуючи цикли та методи System.out.print("* "), System.out.print("  "), System.out.print("\n")
 * вивести на екран:
 * - прямокутник
 * - прямокутний трикутник
 * - рівносторонній трикутник
 * - ромб
 */
public class Figures {
    public static void main(String[] args) {

        System.out.println("\nПрямокутник: ");
        rectangle(10, 5);

        System.out.println("\nПрямокутний трикутник: ");
        rightAngledTriangle(6);

        System.out.println("\nПравильний трикутник: ");
        equilateralTriangle(7);

        System.out.println("\nРомб: ");
        diamond(8);

    }

    /**
     * Show Rectangle
     * @param width
     * @param heigth
     */
    public static void rectangle(int width, int heigth){
        boolean isSolidLine;

        for (int i=1; i<= heigth; i++) {
            isSolidLine = (i == 1 || i == heigth);
            drawLine(width, isSolidLine);
        }
    }

    /**
     * Show Right-Angled Triangle
     * @param sideLength
     */
    private static void rightAngledTriangle(int sideLength) {
        drawTriangle(sideLength,0,-1);
    }

    /**
     * Show Eequilateral Triangle
     * @param sideLength
     */
    private static void equilateralTriangle(int sideLength) {
        drawTriangle(sideLength,0,0);
    }

    /**
     * Show Diamond
     * @param sideLength
     */
    private static void diamond(int sideLength) {
        drawTriangle(sideLength,sideLength,0);
    }

    /**
     * Universal method to Draw part of Triangle/Diamond from top to bottom line by line
     * @param sideLength
     * @param countLinesFromTop - висота фігури, якщо число в інтервалы выд 0 і більше (-sideLength) - малюємо
     *                          трикутник у якого вершина направлена вверх а протилежна сторона в низ,
     *                          якщо число быльше 0 ы меншк sideLength - починаємо малювати трикутник направлений вершиною
     *                          вниз, основа якого співнадає з основою попереднього трикутника
     *                          якщо countLinesFromTop< sideLength - отримаємо трапецію
     * @param orientation "<0" - діагональ справа, вершина ліворуч, ">0" - діагональ зліва, вершина праворуч, "0" вершина по центру
     */
    private static void drawTriangle(int sideLength, int countLinesFromTop, int orientation){
        int diagonal;
        boolean needDrawBorder;
        int width;

        countLinesFromTop = countLinesFromTop>sideLength?sideLength:countLinesFromTop;

        for (int i=-sideLength+1; i <= countLinesFromTop; i++) {
            width = Math.abs(i);
            needDrawBorder = (i == countLinesFromTop);

            if (orientation == 0) {
                width = Math.abs(i);
                diagonal = (sideLength - width) * 2 - 1;
            } else if (orientation < 0) {
                width = Math.abs(i);
                //diagonal = sideLength - width;        // рівносторонній (катети рівні)
                diagonal = (sideLength - width) * 2 - 1;// катети не рівнв
                width = 0;
            }
            else {
                width = Math.abs(i);
                diagonal = sideLength - width;
            }
            drawSpaces(width);
            drawLine(diagonal, needDrawBorder);
        }
    }

    private static void drawSpaces(int i) {
        for (int j = 1; j <= i; j++) //робимо відступ
            System.out.print("  ");
    }

    /**
     * Draw line
     * @param width - length of line
     * @param isSolidLine true - is solid line, false - is border
     */
    private static void drawLine(int width, boolean isSolidLine) {
        boolean needBorderSymbol;

        for (int i=1; i<=width; i++) {
            needBorderSymbol = (isSolidLine || i == 1 || i == width);
            if(needBorderSymbol)
                System.out.print("* ");
            else
                System.out.print("  ");
        }

        System.out.print("\n");
    }
}
