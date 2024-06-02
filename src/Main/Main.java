package Main;

import SortingAlgorithms.SortingAlgorithmsVector;
import SortingAlgorithms.SortingTest;
import terminalUtils.ProgressBar;
import terminalUtils.TerminalUtils;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        testManual();
        /*
        try {
            new SortingTest(true, 50000, true).testSortingAlgorithms();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        */
    }

    public static void testManual() {
        int[] v = generateVector(10000000);
        long start = System.currentTimeMillis();

        SortingAlgorithmsVector.quickSort(v, v.length, new ProgressBar());

        long end = System.currentTimeMillis();
        float time = (float) ((end - start)/1000);
        System.out.println();
        TerminalUtils.infoTrace("Fueron " + time + " s.");

        for (int i = 0; i < 100; i++) {
            System.out.println(v[i]);
        }
    }

    private static int[] generateVector(int n) {
        Random random = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt((n - 1) + 1) + 1;
        }
        return array;
    }
}