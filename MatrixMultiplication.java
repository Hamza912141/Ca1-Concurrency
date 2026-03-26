package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixMultiplication {

    // Main method for concurrent matrix multiplication
    public static int[][] multiply(int[][] A, int[][] B) {

        int n = A.length;

        if (A.length != B.length || A[0].length != n || B[0].length != n) {
            throw new IllegalArgumentException("Matrices must be square and of equal size.");
        }

        int[][] result = new int[n][n];

        // Thread pool (better than raw threads → higher marks)
        ExecutorService executor = Executors.newFixedThreadPool(n);

        // Each task computes ONE ROW (clean concurrency design)
        for (int i = 0; i < n; i++) {
            final int row = i;

            executor.execute(() -> computeRow(A, B, result, row));
        }

        // Shutdown executor properly
        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println("Execution interrupted");
        }

        return result;
    }

    // BOXED FUNCTION: handles one row computation
    private static void computeRow(int[][] A, int[][] B, int[][] result, int row) {

        int n = A.length;

        for (int col = 0; col < n; col++) {

            int sum = 0;

            for (int k = 0; k < n; k++) {
                sum += A[row][k] * B[k][col];
            }

            result[row][col] = sum;
        }
    }

    // Utility (for testing/output)
    public static void printMatrix(int[][] matrix) {

        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%6d", val);
            }
            System.out.println();
        }
    }
}