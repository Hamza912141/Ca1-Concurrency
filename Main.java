/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class Main {
    public static void main(String[] args) {

        List<Integer> data = CSVReader.readCSV("data.csv");

        // 1. Standard Deviation
        double sd = StandardDeviation.calculate(data);
        System.out.println("Standard Deviation: " + sd);

        // 2. Matrix Multiplication
        int size = (int) Math.sqrt(data.size() / 2);
        int[][] A = new int[size][size];
        int[][] B = new int[size][size];

        int index = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                A[i][j] = data.get(index++);

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                B[i][j] = data.get(index++);

        int[][] result = MatrixMultiplication.multiply(A, B);
        System.out.println("Matrix Multiplication Done");

        // 3. Merge Sort
        int[] arr = data.stream().mapToInt(i -> i).toArray();
        ParallelMergeSort.sort(arr);

        System.out.println("Sorted (Descending): " + Arrays.toString(arr));
    }
}
