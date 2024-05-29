package SortingAlgorithms;

import com.sun.jdi.event.ClassUnloadEvent;
import terminalUtils.ProgressBar;
import terminalUtils.TerminalUtils;

import java.util.ArrayList;
import java.util.Random;

import static terminalUtils.TerminalUtils.printProgressBar;

public class SortingTest {

    public static void testSortingAlgorithms(boolean seq, boolean vector, int n) throws InterruptedException {

        /*
        *
        *   la variable @seq, es para correr todos los algoritmos de manera secuencial, o para
        *   correrlos de manera paralela y comparar tiempos.
        *
        *   TODO: el codigo actual solo esta para un solo algoritmo. acomodar para correrlos todos.
        *
        */

        Thread sortingThread;
        ProgressBar pb = new ProgressBar();
        Thread progressBarThread = new Thread(() -> {
            while (!pb.isSortingComplete()) {
                printProgressBar(pb.getProgress(), pb.getTotalSteps(), true);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            printProgressBar(pb.getTotalSteps(), pb.getTotalSteps(), false);
        });


        if (vector) {
            sortingThread = new Thread(() -> {
                SortingAlgorthmsVector.bubbleSort(generateVector(n), n, pb);
            });
        } else {
            sortingThread = new Thread(() -> {
                SortingAlgorthmsVector.bubbleSort(generateVector(n), n, pb);
            });
        }

        launch(sortingThread, progressBarThread);
    }

    private static void launch(Thread sortingThread, Thread progressBarThread) throws InterruptedException {
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

    // util;

    private static int[] generateVector(int n) {
        Random random = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt((n - 1) + 1) + 1;
        }
        return array;
    }

    private static ArrayList<Integer> generateArrayList(int n) {
        Random random = new Random();
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            array.add(random.nextInt((n - 1) + 1) + 1);
        }
        return array;
    }

}
