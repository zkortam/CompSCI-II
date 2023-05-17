/*
 * Zakaria Kortam - Professor Estrada
 * Generics Assignment 3/1/2023
 * 
 * 1. Create three Array-Lists of Integer, Double, and String types.
 * 2. Place their values and send them through a Sort() function
 * that takes a generic parameter of E[].
 * 3.It initialized the smallest value and the smallest index within the function
 * and it checks if any value at an index is smaller than the minimu, if there is, it becomes
 * the new minimum.
 * 4. Then, the next for loop completes the swap in index in order to properly order the array.
 * 5. The array is printed through the use of the printList() function
 * which uses a for loop to print each index.
 */

import java.util.*;
import java.lang.*;

public class Generics {
    public static void main(String[] args) {

        header();

        Integer[] intArray = new Integer[3];
        intArray[0] = 2;
        intArray[1] = 4;
        intArray[2] = 3;

        Double[] doubArray = new Double[3];
        doubArray[0] = -12.3;
        doubArray[1] = 1.2;
        doubArray[2] = 3.4;

        String[] strArray = new String[4];
        strArray[0] = "Bob";
        strArray[1] = "Alice";
        strArray[2] = "Ted";
        strArray[3] = "Carol";

        sort(intArray);
        sort(doubArray);
        sort(strArray);

        System.out.println("\n\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("<<<<<<< Sorted Output  <<<<<<<<");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.print("\nInteger Array List [Sorted]: ");
        printList(intArray);
        System.out.print("\nDouble Array List [Sorted]: ");
        printList(doubArray);
        System.out.print("\nString Array List [Sorted]: ");
        printList(strArray);

    }

    public static <E extends Comparable<E>> void sort(E[] list) {
        E currentMin;
        int currentMinIndex;

        for (int i = 0; i < list.length - 1; i++) {
            currentMin = list[i];
            currentMinIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (currentMin.compareTo(list[j]) > 0) {
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }

            if (currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }

    public static void printList(Object[] list) {
        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
        System.out.println();
    }

    public static void header() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("<<<<<<< Zakaria Kortam - COMSC 76 <<<<<<<<");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

}
