package Main;

import SortingAlgorithms.SortingTest;

public class Main {
    public static void main(String[] args) {
        try {
            new SortingTest(true, 100000, true).testSortingAlgorithms();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}