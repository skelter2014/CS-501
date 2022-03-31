
import java.util.Arrays;

public class SelectionSort_2 {
    static int counter = 0;

    public static void main(String[] args) {
        int[] a = { 4, 5, 2, 3, 1, 8, 9, 7, 10, 6 };

        sort(a);

        System.out.println(Arrays.toString(a));

    }

    public static void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[index]) {
                    index = j;// searching for lowest index
                    counter ++;
                }
            }
            int smallerNumber = a[index];
            a[index] = a[i];
            a[i] = smallerNumber;
            counter ++;
        }
    }

}
