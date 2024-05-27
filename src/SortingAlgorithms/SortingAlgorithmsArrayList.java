package SortingAlgorithms;

public class SortingAlgorithmsArrayList {

        /*
        BUBBLE SORT
    */

    public static void bubbleSort(int[] arr) {

    }


    /*
        Ordenamiento por inserción:
    */


    // TODO: modificar
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
    */



    /*
        heap sort.
    */


}
