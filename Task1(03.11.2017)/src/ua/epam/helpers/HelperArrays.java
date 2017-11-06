package ua.epam.helpers;

public class HelperArrays {

    public static int[] createRandomIntMas(int count) {
        int[] res = new int[count];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int)(Math.random()*199)-100;
        }
        return res;
    }

    public static void printMas(int[] mas) {
        for (int i = 0; i < mas.length; i++) {
                System.out.printf("%6s",mas[i]);
        }
        System.out.println();
    }

    public static int[][] createRandomIntMatrix(int row, int col) {
        int[][] matrix = new int[row][col];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int)(Math.random()*199)-100;
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%6s",matrix[i][j]);
            }
            System.out.println();
        }
    }
}
