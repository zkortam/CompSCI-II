/*
 * Zakaria Kortam - COMSC 76 - Professor Estrada
 * Sorting - 4/12/2023
 * 
 * 1. Call the runner function which sets up a modular way of running through each array size
 * specification.
 * 2. Use the createArray function to create the arrays according to the desired size.
 * 3. Run selection sort, merge sort,quick sort, heap sort, radix sort,  counting sort, and bubble
 * sort(Extra). For each sorting variation, have it keep track of and declare the amount of time in ms that
 * it took to sort.
 * 4. Have everything printed and organized according to the array size. Header function is used to help with
 * the labeling.
 * 
 */
import java.util.*;

public class kortam_zakaria_sort {
    public static void main(String[] args){

        System.out.println("Array Size | Selection    Merge   Quick   Heap   Radix   Couinting   Bubble");
        System.out.println("---------------------------------------------------------------------------");
        runner(50000);
        overlapCatcher();
        runner(100000);
        overlapCatcher();
        runner(150000);
        overlapCatcher();
        runner(200000);
        overlapCatcher();
        runner(250000);
    }

    public static void overlapCatcher(){
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
           
        }
    }

    public static void runner(int size){
        int[] array = createArray(size);
    
        //Selection Sort
        long startTime = System.currentTimeMillis();
        selection(array);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        long selection = totalTime;

        //Merge Sort
        startTime = System.currentTimeMillis();
        merge(array);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        long merge = totalTime;

        //Quick Sort
        startTime = System.currentTimeMillis();
        quick(array);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        long quick = totalTime;

        //Heap Sort
        startTime = System.currentTimeMillis();
        heapSort(array);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        long heap = totalTime;
        
        //Radix Sort
        startTime = System.currentTimeMillis();
        radixSort(array);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        long radix = totalTime;
        
        //Counting Sort
        startTime = System.currentTimeMillis();
        countingSort(array,50000,1000);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        long counting = totalTime;

        //Bubble Sort - For fun
        startTime = System.currentTimeMillis();
        bubbleSort(array);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        long bubble = totalTime;

        String filler ="";
        if(size == 50000){
            filler = " ";
        }

        

        System.out.printf("  %d   %s| %s       %s%s  %s         %s         %s        %s        %s     %s%n", size, filler, selection, filler, filler, merge,quick,heap,radix,counting,bubble);

        
    }

    public static int[] createArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 1000); 
        }
        return arr;
    }
    
    // Merge Sort
    public static void merge(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);
            merge(left);
            merge(right);
    
            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    arr[k++] = left[i++];
                } else {
                    arr[k++] = right[j++];
                }
            }
            while (i < left.length) {
                arr[k++] = left[i++];
            }
            while (j < right.length) {
                arr[k++] = right[j++];
            }
        }
    }

    // Selection Sort
    public static void selection(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

    // Quick Sort
    public static void quick(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j <= right - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    // Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;
    
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }
    private static void heapify(int[] arr, int n, int i) {
        int largest = i; 
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }
    

         
    private static void countingSort(int[] arr, int n, int exp) {
        int[] output = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < n; i++) {
            int index = (arr[i] / exp) % 10;
            count[index]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            int index = (arr[i] / exp) % 10;
            output[count[index] - 1] = arr[i];
            count[index]--;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    

    public static void header(String input, int size) {
        System.out.println("\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        if(size == 50000){
            System.out.printf("<<<<<< %s  %d <<<<<<<<%n", input, size);
        } else {
            System.out.printf("<<<<<< %s  %d <<<<<<<%n", input, size);
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\n");
    }
    
}

