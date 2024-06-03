package SortingAlgorithms;

import terminalUtils.ProgressBar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SortingAlgorithmsArrayList {
    public static void bubbleSort(ArrayList<Integer> arr, int n, ProgressBar pb) { // vercion optimizada
        int i, j;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    Collections.swap(arr, j, j+1);
                    swapped = true;
                }
            }
            pb.updateProgress(i + 1, n);
            if (!swapped)
                break;
        }
        pb.setSortingComplete(true);
    }

    public static void insertionSort(ArrayList<Integer> arr, int n, ProgressBar pb) {
        int j, aux;

        for (int i = 1; i < n; i++) {          // ¡Ojo! Empezamos en 1
            aux = arr.get(i);                        // Guardamos el valor a ordenar.
            j = i - 1;
            while ((j >= 0) && arr.get(j) > aux)     // Siempre que sea mayor que aux.
                // ordenamos de menor a mayor.
                arr.set(j + 1,arr.get(j--));           // Trasladamos el valor y movemos el indice.

            arr.set(j + 1,aux);                    // Ponemos el valor a ordenar en su sitio.
            pb.updateProgress(i + 1, n);

            // updates progressbar
        }
        //end progressbar
        pb.setSortingComplete(true);

    }


    // ======= AVANZADOS =======


    /*
        QuickSort
        --> Function call
            int N = arr.length;
            quickSort(arr, 0, N - 1);
            System.out.println("Sorted array:");
            printArr(arr);
     */
    public static void quickSort(ArrayList<Integer> arr, int n, ProgressBar pb) {
        quickSort(arr, 0, n-1, pb);
        pb.setSortingComplete(true);
    }
    private static void quickSort(ArrayList<Integer> arr, int low, int high, ProgressBar pb) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1, pb);
            quickSort(arr, pi + 1, high, pb);
            pb.updateProgress((pi - low) * 100 / (high - low), 100);
        }
    }

    public static int partition(ArrayList<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr.get(j) < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    public static void swap(ArrayList<Integer> arr, int i, int j) {
        Collections.swap(arr, i , j);
    }

    public static void heapSort(ArrayList<Integer> arr, ProgressBar pb) {
        int N = arr.size();

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(arr, N, i);

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr.get(0);
            arr.set(0,arr.get(i));
            arr.set(i,temp);

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
            pb.updateProgress(N - i, N);
        }
        pb.setSortingComplete(true);
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    public static void heapify(ArrayList<Integer> arr, int N, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && arr.get(l) > arr.get(largest))
            largest = l;

        // If right child is larger than largest so far
        if (r < N && arr.get(r) > arr.get(largest))
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr.get(i);
            arr.set(i,arr.get(largest));
            arr.set(largest,swap);

            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }
    }

}
