package SortingAlgorithms;

import terminalUtils.ProgressBar;
import terminalUtils.TerminalUtils;

import java.util.ArrayList;
import java.util.Random;

import static terminalUtils.TerminalUtils.printProgressBar;

public class SortingTest {

    // Attributes
    boolean vector;
    int[] vec;
    int n;
    ArrayList<Integer> arr;
    boolean seq;

    ProgressBar[] progressThreads = new ProgressBar[4];


    // VEC THREADS

    /*
    public Thread bubbleVecThread = new Thread(() -> {

    });

    public Thread insertionVecThread = new Thread(() -> {

    });

    public Thread quickVecThread = new Thread(() -> {

    });

    public Thread heapVecThread = new Thread(() -> {

    });

    // ARRAY THREADS

    public Thread bubbleArrayThread = new Thread(() -> {

    });

    public Thread insertionArrayThread = new Thread(() -> {

    });

    public Thread quickArrayThread = new Thread(() -> {

    });

    public Thread heapArrayThread = new Thread(() -> {

    });

    */

    public SortingTest(boolean useVec, int n, boolean seq) {

        // progressThreads creation
        for (int i = 0; i < 4; i++) {
            progressThreads[i] = new ProgressBar();
        }

        // generate vector or arrayList
        if (useVec) {
            this.vec = generateVector(n);
        } else {
            this.arr = generateArrayList(n);
        }
        this.n = n;
        this.vector = useVec;
        this.seq = seq;
    }


    public void testSortingAlgorithms() throws InterruptedException {

        /*
        *
        *   la variable @seq, es para correr todos los algoritmos de manera secuencial, o para
        *   correrlos de manera paralela y comparar tiempos.
        *
        *   TODO: El codigo actual solo esta para un solo algoritmo. acomodar para correrlos todos.
        *   TODO: Podria usar reflexivas para facilitar el proceso de asignacion de funciones
        *   TODO: ACTUALIZAR EL HILO DE CADA ALGORITMO EN LA PARTE SEQUENCIAL, O REVISAR QUE PASA CON LA ACTUALIZACION DE LA BARRA
        *
        */

        Thread progressBarThread;
        Thread[] sortThread = initializeThreads();
        if (seq) {
            for (int i = 0; i < 4; i++) { // number of threads
                ProgressBar pb = progressThreads[i];
                progressBarThread = new Thread(() -> {
                    while (!pb.isSortingComplete()) {
                        printProgressBar(pb.getProgress(), pb.getTotalSteps(), true);
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    printProgressBar(pb.getTotalSteps(), pb.getTotalSteps(), false);
                });

                launch(sortThread[i], progressBarThread);
            }

        } else {
            progressBarThread = new Thread(() -> {
                boolean sortingComplete = false;
                int totalSteps = 100; // setting to 100 for uniformity

                while (!sortingComplete) {
                    int progress = 0;

                    for (ProgressBar pb : progressThreads) {
                        progress += pb.getProgress();
                    }
                    progress /= 4; // number of algorithms

                    sortingComplete = true;
                    for (ProgressBar pb : progressThreads) {
                        if (!pb.isSortingComplete()) {
                            sortingComplete = false;
                            break;
                        }
                    }

                    printProgressBar(progress, totalSteps, true);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                printProgressBar(100, 100, false);
            });

            launchMultiple(sortThread, progressBarThread);
        }

    }

    private Thread[] initializeThreads() {
        Thread[] sortingThreads = new Thread[4]; // number of threads // <--------- CAMBIO
        if (vector) {
            sortingThreads[0] = new Thread(() -> {
                SortingAlgorithmsVector.bubbleSort(this.vec.clone(), n, progressThreads[0]);
            });

            sortingThreads[1] = new Thread(() -> {
                SortingAlgorithmsVector.insertionSort(this.vec.clone(), n, progressThreads[1]);
            });

            sortingThreads[2] = new Thread(() -> {
                SortingAlgorithmsVector.quickSort(this.vec.clone(), n, progressThreads[2]);
            });

            sortingThreads[3] = new Thread(() -> {
                SortingAlgorithmsVector.heapSort(this.vec.clone(), progressThreads[3]);
            });

        } else {
            sortingThreads[0] = new Thread(() -> {
                SortingAlgorithmsArrayList.bubbleSort(generateArrayList(n), n, progressThreads[0]);
            });

            sortingThreads[1] = new Thread(() -> {
                SortingAlgorithmsArrayList.insertionSort(generateArrayList(n), progressThreads[1]);
            });

            sortingThreads[2] = new Thread(() -> {
                SortingAlgorithmsArrayList.quickSort(generateArrayList(n), n, progressThreads[2]);
            });

            sortingThreads[3] = new Thread(() -> {
                SortingAlgorithmsArrayList.heapSort(generateArrayList(n), progressThreads[3]);
            });
        }
        return sortingThreads;
    }

    private void launch(Thread sortingThread, Thread progressBarThread) throws InterruptedException {
        // start time
        long start = System.currentTimeMillis();

        // Threads
        sortingThread.start();
        progressBarThread.start();
        sortingThread.join();
        progressBarThread.join();

        // time end and final time
        long end = System.currentTimeMillis();
        float time = (float) ((end - start));

        System.out.println();
        TerminalUtils.infoTrace("Fueron " + time + " millisegundos.");
    }

    private void launchMultiple(Thread[] sortingThreads, Thread progressBarThread) throws InterruptedException {
        // start time
        long start = System.currentTimeMillis();

        // Threads
        for (Thread st : sortingThreads) {
            st.start();
        }
        progressBarThread.start();

        for (Thread st : sortingThreads) {
            st.join();
        }
        progressBarThread.join();

        // time end and final time
        long end = System.currentTimeMillis();
        float time = (float) ((end - start));

        System.out.println();
        TerminalUtils.infoTrace("Fueron " + time + " millisegundos.");
    }

    // util;

    private int[] generateVector(int n) {
        Random random = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt((n - 1) + 1) + 1;
        }
        return array;
    }

    private ArrayList<Integer> generateArrayList(int n) {
        Random random = new Random();
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            array.add(random.nextInt((n - 1) + 1) + 1);
        }
        return array;
    }

    public boolean getSeq() {
        return seq;
    }

    public void setSeq(boolean seq) {
        this.seq = seq;
    }

}
