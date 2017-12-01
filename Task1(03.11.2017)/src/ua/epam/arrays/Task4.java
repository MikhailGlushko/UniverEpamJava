package ua.epam.arrays;

import java.util.Arrays;

/**
 * @author Mikhail Glushko
 * mikhail.glushko@gmail.com
 * Дана целочисленная прямоугольная матрица. Упорядочить строки, по возрастанию по самой длинной серии одинаковых элементов.
 */
public class Task4 {
    public static void main(String[] args) {

        int row = 5;
        int col = 10;
        int[][] matrix = createMatrix(row, col);
        System.out.println("Input: ");
        printMas(matrix);
        int[][] additionalMatrix = createAdditionalMatrix(matrix);
        matrix = sortMatrix(matrix, additionalMatrix);
        System.out.println("Sorted: ");
        printMasAfterSort(matrix, additionalMatrix);
    }

    private static int[][] sortMatrix(int[][] matrix, int[][] additionalMatrix) {
        // сортуємо інформацію в допоміжному масиві
        for (int i = 0; i < additionalMatrix.length; i++) {
            for (int j = 0; j < additionalMatrix.length - 1 - i; j++) {
                if (additionalMatrix[j][0] > additionalMatrix[j + 1][0]) {
                    int[] tmp = new int[2];
                    tmp = additionalMatrix[j];
                    additionalMatrix[j] = additionalMatrix[j + 1];
                    additionalMatrix[j + 1] = tmp;
                }
            }
        }

        // сортуємо рядки матриці
        int[][] tmp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            tmp[i] = matrix[additionalMatrix[i][1]];
        }
        return tmp;

    }

    /**
     * Допоміжний масив-матриці, що містить інформацію про довдину самої довшої серії одинакових елементів
     * та інформацію про стрічку, в якій вона знайдена - потрібна для формування нової відсортованої матриці
     * @param matrix
     * @return
     */
    private static int[][] createAdditionalMatrix(int[][] matrix) {
        int[][] additionalMatrix = new int[matrix.length][2];
        for (int i = 0; i < matrix.length; i++) {
            int count = 1;
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] == matrix[i][j + 1])
                    count++;
                if(count>additionalMatrix[i][0]){
                    additionalMatrix[i][0] = count;
                    additionalMatrix[i][1] = i;
                }
                else {
                    count = 1;
                }
            }
        }
        return additionalMatrix;
    }

    private static int[][] createMatrix(int row, int col) {
        int[][] matrix = new int[row][col];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random() * 2);
            }
        }
        return matrix;
    }

    private static void printMas(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%6s", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static void printMasAfterSort(int[][] matrix, int[][] additionalMatrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%6s", matrix[i][j]);
            }
            // информація звідки переставлено рядок
            System.out.printf(" ftom row %1s\n", additionalMatrix[i][1]);
        }
    }
}
