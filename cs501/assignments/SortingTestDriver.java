
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SortingTestDriver {

    enum SortMethod {
        Selection,
        Merge,
        Insertion,
        QuickSort
    };

    /**
     * Implement insertion, selection, merge and quick sorts. Perform a series of
     * benchmarking
     * tests to see which one is faster.
     * Your tests should include sequences that are random as well as almost sorted.
     * 
     */
    public static <T> void main(String[] args) {
        int items = 100; //
        System.out.println("-------------------------------------------------------------");
        System.out.println("## Unsorted Data ##");

        // Fill array with random numbers between 1 - 1000
        Random rnd = new Random();
        int[] a = new int[items];
        for (int i = 0; i < items; i++) {
            a[i] = rnd.nextInt(1000);
        }

        printPartial(a);

        // Test each sorting method.
        testSort(Arrays.copyOf(a, a.length), SortMethod.Selection);
        testSort(Arrays.copyOf(a, a.length), SortMethod.Merge);
        testSort(Arrays.copyOf(a, a.length), SortMethod.Insertion);
        testSort(Arrays.copyOf(a, a.length), SortMethod.QuickSort);

        System.out.println("-------------------------------------------------------------");
        System.out.println("%% Almost Sorted Data (ASC) %%");

        // Presort most of the test data array except for 2%
        int partial = (int) (a.length * .98);
        Arrays.sort(a, 0, partial);
        // Arrays.sort(a);

        // fill the last 2% of elements with new random data
        for (int i = partial; i < items; i++) {
            a[i] = rnd.nextInt(1000);
        }

  

        printPartial(a);

        testSort(Arrays.copyOf(a, a.length), SortMethod.Selection);
        testSort(Arrays.copyOf(a, a.length), SortMethod.Merge);
        testSort(Arrays.copyOf(a, a.length), SortMethod.Insertion);
        testSort(Arrays.copyOf(a, a.length), SortMethod.QuickSort);

        System.out.println("-------------------------------------------------------------");
        System.out.println("%% Almost Sorted Data (DESC) %%");


        int[] b = reverse(a);

        printPartial(b);

        testSort(Arrays.copyOf(b, b.length), SortMethod.Selection);
        testSort(Arrays.copyOf(b, b.length), SortMethod.Merge);
        testSort(Arrays.copyOf(b, b.length), SortMethod.Insertion);
        testSort(Arrays.copyOf(b, b.length), SortMethod.QuickSort);

    }

    private static int[] reverse(int a[])
    {
        int[] b = new int[a.length];
        int j = a.length;
        for (int i = 0; i < a.length; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }
        return b;
    }

    private static void printPartial(int[] a) {
        System.out.print("[ ");
        for (int i = 0; i < 5; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.print(" ... ");
        for (int j = a.length - 5; j < a.length; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println("]");

    }

    private static void testSort(int[] s1, SortMethod sortMethod) {
        int operations = 0;
        long startTime = System.nanoTime();
        switch (sortMethod) {
            case Selection:
                SelectionSort_2.sort(s1);
                operations = SelectionSort_2.counter;
                SelectionSort_2.counter = 0; // reset it
                break;
            case Merge:
                MergeSort_2.sort(s1, s1.length);
                operations = MergeSort_2.counter;
                MergeSort_2.counter = 0; // reset it
                break;
            case Insertion:
                InsertionSort_2.sort(s1);
                operations = InsertionSort_2.counter;
                InsertionSort_2.counter = 0; // reset it
                break;
            case QuickSort:
                QuickSort_2.sort(s1, 0, s1.length - 1);
                operations = QuickSort_2.counter;
                QuickSort_2.counter = 0; // reset it
                break;
            default:
                // unsorted
        }
        long endTime = System.nanoTime();

        double seconds = (double) (endTime - startTime) / 1_000_000_000;

        // Option2, use decimalFormat.
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(true);

        System.out.println("Method:   " + sortMethod + " \tElapsed time (s): " + df.format(seconds));
        System.out.println("\t\t\tNumber of operations: " + nf.format(operations));

    }

}
