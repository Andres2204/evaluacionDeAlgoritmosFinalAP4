package Main;

import SortingAlgorithms.SortingTest;

import java.io.Console;

public class Main {
    public static void main(String[] args) {
        try {
            SortingTest.testSortingAlgorithms(true, true, 10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}