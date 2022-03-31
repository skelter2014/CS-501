
import java.util.Arrays;

public class QuickSort_2 {

    static boolean overflowFlag = false;
    static int counter = 0;

    public static void main(String[] args) {
        int[] list = new int[] { 4, 5, 2, 3, 1, 8, 9, 7, 10, 6 };
        sort(list, 0, list.length - 1);

        System.out.println(Arrays.toString(list));

    }

    public static void sort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            try {
                sort(arr, begin, partitionIndex - 1);
                sort(arr, partitionIndex + 1, end);
            } catch (StackOverflowError e) {
                overflowFlag = true;
            }

        }
    }

    private static int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
                //each iteration occurs in O(1) time.
                counter ++;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

}
