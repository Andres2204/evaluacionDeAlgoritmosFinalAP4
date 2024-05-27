package SortingAlgorithms;

import java.util.ArrayList;

public class SortingAlgorithmsArrayList {

    /*
        BUBBLE SORT
    */

    public static void bubbleSort(ArrayList<Integer> arr, int n) { // vercion optimizada
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {

                    // Swap arr[j] and arr[j+1]
                    temp = arr.get(j);
                    arr.set(i, arr.get(j + 1));
                    arr.set(j + 1,temp);
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }

    }


    /*
        Ordenamiento por inserción:
    */

    public static void insertionSort(ArrayList<Integer> arr) {
        int j, aux;

        for (int i = 1; i < 100; i++) {          // ¡Ojo! Empezamos en 1
            aux = arr.get(i);                        // Guardamos el valor a ordenar.
            j = i - 1;
            while ((j >= 0) && arr.get(j) > aux)     // Siempre que sea mayor que aux.
                // ordenamos de menor a mayor.
                arr.set(j + 1,arr.get(j--));           // Trasladamos el valor y movemos el indice.

            arr.set(j + 1,aux);                    // Ponemos el valor a ordenar en su sitio.

            // updates progressbar
        }
        //end progressbar

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


    // The main function that implements QuickSort
    // arr[] --> Array to be sorted,
    // low --> Starting index,
    // high --> Ending index
    static void quickSort(ArrayList<Integer> arr, int low, int high)
    {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // This function takes last element as pivot,
    // places the pivot element at its correct position
    // in sorted array, and places all smaller to left
    // of pivot and all greater elements to right of pivot
    public static int partition(ArrayList<Integer> arr, int low, int high) {
        // Choosing the pivot
        int pivot = arr.get(high);

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller than the pivot
            if (arr.get(j) < pivot) {

                // Increment index of smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    public static void swap(ArrayList<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i,arr.get(j));
        arr.set(j,temp);
    }



    /*
        heap sort.
    */

    public void heapSort(ArrayList<Integer> arr)
    {
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
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(ArrayList<Integer> arr, int N, int i)
    {
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
