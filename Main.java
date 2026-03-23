package concurrency;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Integer> data = CSVReader.readCSV("C:\\Users\\hp\\Documents\\NetBeansProjects\\Concurrency\\src\\concurrency\\data.csv");

    // 1. Standard Deviation
        double sd = StandardDeviation.calculate(data);
        System.out.println("Standard Deviation: " + sd);
