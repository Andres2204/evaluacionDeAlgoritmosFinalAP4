package SortingAlgorithms;

import terminalUtils.ProgressBar;

public class SortingAlgorithmsVector {

    public static void bubbleSort(int[] arr, int n, ProgressBar pb) {
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            pb.updateProgress(i + 1, n); // update progress 
            if (!swapped) break;
        }
        pb.setSortingComplete(true); 
    }

    public static void insertionSort(int[] arr, int n, ProgressBar pb) {
        int j, aux;
        for (int i = 1; i < n; i++) {
            aux = arr[i];
            j = i - 1;
            while ((j >= 0) && arr[j] > aux)
                arr[j + 1] = arr[j--];
            arr[j + 1] = aux;
            pb.updateProgress(i + 1, n); // update progress 
        }
        pb.setSortingComplete(true); 
    }

    public static void quickSort(int[] arr, int n, ProgressBar pb) {
        quickSort(arr, 0, n - 1, pb);
        pb.setSortingComplete(true); 
    }

    private static void quickSort(int[] arr, int low, int high, ProgressBar pb) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1, pb);
            quickSort(arr, pi + 1, high, pb);
            pb.updateProgress((pi - low) * 100 / (high - low), 100); // update progress 
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapSort(int[] arr, ProgressBar pb) {
        int N = arr.length;
        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(arr, N, i);
        for (int i = N - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
            pb.updateProgress(N - i, N); // update progress 
        }
        pb.setSortingComplete(true); 
    }

    private static void heapify(int[] arr, int N, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < N && arr[l] > arr[largest])
            largest = l;
        if (r < N && arr[r] > arr[largest])
            largest = r;
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, N, largest);
        }
    }
}