package ua.epam.arrays;

import ua.epam.arrays.helpers.HelperArrays;

/**
 * Дана квадратная матрица A порядка M (M — нечетное число). Начи ная с элемента A1,1 и перемещаясь по часовой стрелке, вывести все ее элементы
 * по спирали: первая строка, последний столбец, последняя строка в обратном  порядке,  первый  столбец  в  обратном  порядке,  оставшиеся  эле-
 * менты второй строки и т. д.; последним выводится центральный элемент  матрицы.
 */
public class Task5 {
    public static void main(String[] args) {
        int m = 5;
        int[][] matrix = createMatrix(m);
        System.out.println("input:");
        HelperArrays.printMatrix(matrix);
        System.out.println("Transformed:");
        printSpiral(matrix);
    }

    private static void printSpiral(int[][] matrix) {
        int len = matrix.length*matrix.length;
        int count = 0;
        int direction = 0;
        int i = 0;
        int j = 0;
        int minI = 1;
        int maxI = matrix.length -1;
        int minJ = 0;
        int maxJ = matrix.length -1;
        while (count<len){
            System.out.print("A["+i+","+j+"]="+matrix[i][j]+" ");
            count++;
            switch (direction){
                case 0:
                    if(++j==maxJ){
                        maxJ--;
                        direction++;
                    }
                    break;
                case 1:
                    if(++i==maxI){
                        maxI--;
                        direction++;
                    }
                    break;
                case 2:
                    if(--j==minJ){
                        minJ++;
                        direction++;
                    }
                    break;
                case 3:
                    if(--i==minI){
                        minI++;
                        direction++;
                    }
                    break;
            }
            direction %=4;
        }
    }

    private static int[][] createMatrix(int m) {
        int[][] matrix = new int[m][m];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int)(Math.random()*matrix.length*matrix.length);
            }
        }
        return matrix;
    }
}
