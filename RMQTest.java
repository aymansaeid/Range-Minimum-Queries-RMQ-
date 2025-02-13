package last;

import java.util.Arrays;
import java.util.Random;

public class RMQTest {

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000};

        for (int size : sizes) {
            System.out.println("\nTesting arrays of size: " + size);

            int[] sorted = generateSortedArray(size);
            int[] unsorted = generateRandomArray(size);
            int[] reversed = generateReversedArray(size);

            System.out.println("Sorted Array:");
            testAllAlgorithms(sorted);

            System.out.println("Unsorted Array:");
            testAllAlgorithms(unsorted);

            System.out.println("Reversed Array:");
            testAllAlgorithms(reversed);
        }
    }

    private static void testAllAlgorithms(int[] array) {
        testAlgorithm("Precompute All", new PrecomputeAll(array), array);
        testAlgorithm("Sparse Table", new SparseTable(array), array);
        testAlgorithm("Blocking", new Blocking(array), array);
        testAlgorithm("Precompute None", new PrecomputeNone(array), array);
    }

    private static void testAlgorithm(String name, Object algorithm, int[] array) {
        System.out.println("Testing " + name);

        long preprocessStart = System.nanoTime();
        
        if (algorithm instanceof PrecomputeAll) {
            new PrecomputeAll(array); 
        } else if (algorithm instanceof SparseTable) {
            new SparseTable(array); 
        } else if (algorithm instanceof Blocking) {
            new Blocking(array); 
        } else if (algorithm instanceof PrecomputeNone) {
            new PrecomputeNone(array); 
        }
        long preprocessEnd = System.nanoTime();

        // Random query
        Random random = new Random();
        int i = random.nextInt(array.length / 2);
        int j = i + random.nextInt(array.length - i);

        // Query timing
        long queryStartTime = System.nanoTime();
        int result = 0;

        if (algorithm instanceof PrecomputeAll) {
            result = ((PrecomputeAll) algorithm).query(i, j);
        } else if (algorithm instanceof SparseTable) {
            result = ((SparseTable) algorithm).query(i, j);
        } else if (algorithm instanceof Blocking) {
            result = ((Blocking) algorithm).query(i, j);
        } else if (algorithm instanceof PrecomputeNone) {
            result = ((PrecomputeNone) algorithm).query(i, j);
        }

        long queryEndTime = System.nanoTime();

        // output times
        long preprocessTime = preprocessEnd - preprocessStart;
        if (preprocessTime > 100) {
            System.out.println("Preprocessing time: " + preprocessTime + " ns");
        } else {
            System.out.println("Preprocessing time is negligible.");
        }

        System.out.println("Query time: " + (queryEndTime - queryStartTime) + " ns");
        System.out.println("Query result: " + result);

        System.out.println("Time Complexity:");
        if (name.equals("Precompute All")) {
            System.out.println("Preprocessing: O(n), Querying: O(1)");
        } else if (name.equals("Sparse Table")) {
            System.out.println("Preprocessing: O(n log n), Querying: O(1)");
        } else if (name.equals("Blocking")) {
            System.out.println("Preprocessing: O(n), Querying: O(sqrt(n))");
        } else if (name.equals("Precompute None")) {
            System.out.println("Preprocessing: O(1), Querying: O(n)");
        }
        System.out.println();
    }

    private static int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000);
        }
        return array;
    }

    private static int[] generateReversedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }
}
