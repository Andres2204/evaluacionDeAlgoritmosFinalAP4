package Main;

import SortingAlgorithms.SortingAlgorithmsArrayList;
import SortingAlgorithms.SortingAlgorithmsVector;
import SortingAlgorithms.SortingTest;
import terminalUtils.ProgressBar;
import terminalUtils.TerminalUtils;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //testManualArr();
        try {
            new SortingTest(false, 50000, true).testSortingAlgorithms();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testManual() {
        int[] v = generateVector(100000);
        long start = System.nanoTime();

        SortingAlgorithmsVector.bubbleSort(v, v.length, new ProgressBar());

        long end = System.nanoTime();
        float time = (float) ((end - start)/Math.pow(10,9));
        System.out.println();

        for (int i = v.length-1; i > v.length-10000; i--) {
            System.out.println(v[i]);
        }
        TerminalUtils.infoTrace("Fueron " + time + " s.");
    }

    public static void testManualArr() {
        int[] v = generateVector(10000);
        long start = System.nanoTime();

        ArrayList<Integer> arr = new ArrayList<>();

        for (int i : v) {
            arr.add(i);
        }

        SortingAlgorithmsArrayList.bubbleSort(arr, arr.size(), new ProgressBar());

        long end = System.nanoTime();
        float time = (float) ((end - start)/Math.pow(10,9));
        System.out.println();

        for (int i = v.length-1; i > v.length-10000; i--) {
            System.out.println(arr.get(i));
        }
        TerminalUtils.infoTrace("Fueron " + time + " s.");
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