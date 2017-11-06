package ua.epam;

import ua.epam.helpers.HelperArrays;

import java.util.Arrays;

/**
 * @author Mikhail Glushko
 * mikhail.glushko@gmail.com
 * В одномерном массиве расположить сначала положительные потом отрицательные за О(n).
 */

public class Task2 {
    public static void main(String[] args) {
        int count = 20;
        int[] mas = HelperArrays.createRandomIntMas(count);

        System.out.println("Input array: ");
        HelperArrays.printMas(mas);
        sortMas(mas);
        System.out.println("Sorted array: ");
        HelperArrays.printMas(mas);
    }

    private static void sortMas(int[] mas) {
        int count = 0;
        for (int start=0, end=mas.length-1; start<end;  ){
            if(mas[start]>=0)
                start++;
            if(mas[end]<0)
                end--;
            if(mas[start]<mas[end]){
                int tmp    = mas[start];
                mas[start] = mas[end];
                mas[end]   = tmp;
                count++;
            }
        }
    }
}
