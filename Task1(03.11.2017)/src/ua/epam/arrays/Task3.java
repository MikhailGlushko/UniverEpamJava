package ua.epam.arrays;

import ua.epam.arrays.helpers.HelperArrays;

/**
 * Дана целочисленная прямоугольная матрица. Упорядочить столбцы по убыванию среднего значения.
 */
public class Task3 {
    public static void main(String[] args) {

        int rowCount = 5;
        int colCount = 7;
        int[][] matrix;
        matrix = HelperArrays.createRandomIntMatrix(rowCount, colCount);

        System.out.println("Input: ");
        HelperArrays.printMatrix(matrix);
        double[][] averages = createMasOfAveragesFromMatrix(matrix);
        matrix = sortMatrix(matrix, averages);
        System.out.println("Sorted: ");
        HelperArrays.printMatrix(matrix);
        System.out.println();
        printAdditionalMatrix(averages);

    }

    private static int[][] sortMatrix(int[][] matrix, double[][] averages) {
        for (int i = 0; i <averages.length ; i++) {
            for (int j = 0; j < averages.length - 1 - i; j++) {
                if(averages[j][0]<averages[j+1][0]){
                    double[] tmp = averages[j];
                    averages[j] = averages[j+1];
                    averages[j+1] = tmp;
                }
            }
        }
        int[][] tmp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                tmp[i][j] = matrix[i][(int)averages[j][1]];
            }
        }
        return tmp;
    }

    private static double[][] createMasOfAveragesFromMatrix(int[][] matrix) {
        double[][] mas = new double[matrix[0].length][2];
        for (int i =0; i<matrix[0].length; i++){
            double average = 0;
            for (int j = 0; j < matrix.length; j++) {
                average += matrix[j][i];
            }
            average = average/matrix.length;
            mas[i][0]=average;
            mas[i][1]=i;
        }
        return mas;
    }

    public static void printAdditionalMatrix(double[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            if(i==0)
                System.out.println("Averages:");
            else
                System.out.println("from cols:");
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%6s",(int)matrix[j][i]);
            }
            System.out.println();
        }
    }



}
