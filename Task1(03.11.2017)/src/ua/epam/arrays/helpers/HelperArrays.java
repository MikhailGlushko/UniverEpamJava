package ua.epam.arrays.helpers;

import java.util.Arrays;

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

    public static int[] merge_sort(int[] inp){

        for(int k=1;k<inp.length;) {
            for (int i = 0; i < inp.length-k; i += 2*k) {
                int[] a = Arrays.copyOfRange(inp, i, i + k);
                int[] b = Arrays.copyOfRange(inp, i + k, (i + k+k<inp.length)?(i + k+k):inp.length);
                int[] c = merge(a, b);
                System.arraycopy(c, 0, inp, i,c.length);
            }
            if (k==1)
                k=2;
            else
                k=k*2;
        }

        return inp;
    }

    public static int[] merge(int[] a, int[] b){
        int[] result = new int[a.length+b.length];
        int i=0, j=0, k=0;
        while (i<a.length || j<b.length){
            if(j==b.length || (i<a.length && a[i]<=b[j]))
                result[k++] = a[i++];
            else
                result[k++] = b[j++];
        }
        return result;
    }
}
