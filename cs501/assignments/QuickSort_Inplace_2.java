

import java.util.Arrays;

public class QuickSort_Inplace_2 {
    public static int counter = 0;

    public static void main(String[] args){
        int[] list = new int[] { 4, 5, 2, 3, 1, 8, 9, 7, 10, 6 };
        sort(list, 0, list.length - 1);

        System.out.println(Arrays.toString(list));

    }

    /** Sort the subarray S[a..b] inclusive. */
    public static <K> void sort(int[] S, int a, int b) {
        if (a >= b)
            return; // subarray is trivially sorted
        int left = a;
        int right = b - 1;
        int pivot = S[b];
        int temp; // temp object used for swapping
        while (left <= right) { // scan until reaching value equal or larger than pivot (or right marker)
            while (left <= right && S[left] < pivot){
                left++;
            }
            // scan until reaching value equal or smaller than pivot (or left marker)
            while (left <= right && S[right] > pivot) {
                right--;
            }
            if (left <= right) { // indices did not strictly cross
                // so swap values and shrink range
                temp = S[left];
                S[left] = S[right];
                S[right] = temp;
                left++;
                right--;
            }
        } // put pivot into its final place (currently marked by left index)
        temp = S[left];
        S[left] = S[b];
        S[b] = temp;
        counter ++;
        // make recursive calls
        sort(S, a, left - 1);
        sort(S, left + 1, b);
    }

}
