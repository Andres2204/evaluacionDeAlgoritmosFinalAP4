package SortingAlgorithms;

import terminalUtils.ProgressBar;

public class SortingAlgorthmsVector {


    /*
        BUBBLE SORT
    */

    public static void bubbleSort(int[] arr, int n, ProgressBar pb) { // vercion optimizada
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {

                    // Swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            pb.updateProgress(i+1, n); // update progress

            // If no two elements were
            // swapped by inner loop, then break
            if (!swapped)
                break;
        }
        pb.setSortingComplete(true); // finish progress
    }


    /*
        Ordenamiento por inserción:
    */

    public static void insertionSort(int[] arr) {
        int j, aux;

        for (int i = 1; i < 100; i++) {          // ¡Ojo! Empezamos en 1
            aux = arr[i];                        // Guardamos el valor a ordenar.
            j = i - 1;
            while ((j >= 0) && arr[j] > aux)     // Siempre que sea mayor que aux.
                // ordenamos de menor a mayor.
                arr[j + 1] = arr[j--];           // Trasladamos el valor y movemos el indice.

            arr[j + 1] = aux;                    // Ponemos el valor a ordenar en su sitio.

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
    static void quickSort(int[] arr, int low, int high)
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
    public static int partition(int[] arr, int low, int high) {
        // Choosing the pivot
        int pivot = arr[high];

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller than the pivot
            if (arr[j] < pivot) {

                // Increment index of smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



    /*
        heap sort.
    */

    public void heapSort(int[] arr)
    {
        int N = arr.length;

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(arr, N, i);

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int[] arr, int N, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < N && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }
    }


}
