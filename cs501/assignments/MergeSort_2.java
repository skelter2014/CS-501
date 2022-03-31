
import java.util.Arrays;

public class MergeSort_2 {
    static int counter = 0;

    public static void main(String[] args) {
        int[] a = { 4, 5, 2, 3, 1, 8, 9, 7, 10, 6 };

        sort(a, a.length);
        System.out.println(Arrays.toString(a));

    }

    public static void sort(int[] a, int n) {

        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
            counter ++;
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
            counter ++;
        }

        sort(l, mid);
        sort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
                counter ++;
            } else {
                a[k++] = r[j++];
                counter ++;
            }
        }
        while (i < left) {
            a[k++] = l[i++];
            counter ++;
        }
        while (j < right) {
            a[k++] = r[j++];
            counter ++;
        }
    }

}
