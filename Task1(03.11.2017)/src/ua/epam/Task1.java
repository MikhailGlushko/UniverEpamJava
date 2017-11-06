package ua.epam;

import ua.epam.helpers.HelperArrays;

import java.util.Arrays;

/**
 * @author Mikhail Glushko
 * mikhail.glushko@gmail.com
 *
 * Упорядочить одномерный масиве разместив вначале отрицательные по возрастанию затем положительные по убыванию.
 */
public class Task1 {
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
        for (int i=0; i<mas.length;i++)
            for (int j = 0; j < mas.length - 1 - i; j++) {
                if(mas[j]>mas[j+1] && mas[j+1]<0 || mas[j]<mas[j+1] && mas[j]>0){
                    int tmp = mas[j];
                    mas[j] = mas[j+1];
                    mas[j+1] = tmp;
                }
            }
    }
}
