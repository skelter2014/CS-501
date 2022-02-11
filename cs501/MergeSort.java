package cs501;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort {

    public static void main(String[] args) {

        MergeSort sorter = new MergeSort();
        Integer[] a;
        Integer[] b;

        a = new Integer[]{5,10,4,9,3};
        b = new Integer[]{8,2,7,6,1};

        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }

        };

        Integer[] result = new Integer[10];
        merge(a, b, result, comp);

        System.out.println(Arrays.toString(result));

        Integer[] result2 = mergeSort(result, comp);

        System.out.println(Arrays.toString(mergeSort(result2, comp)));

    };

    public static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comp) {
        int i = 0, j = 0;
        while (i + j < S.length) {
            if (j == S2.length || i < S1.length && comp.compare(S1[i], S2[j]) < 0) {
                S[i + j] = S1[i++];
            } else {
                S[i + j] = S2[j++];
            }

        }
    }

    public static <K> K[] mergeSort(K[] S, Comparator<K> comp) {
        int n = S.length;
        if (n < 2)
            return null;
        // divide
        int mid = n / 2;
        K[] S1 = Arrays.copyOfRange(S, 0, mid);
        K[] S2 = Arrays.copyOfRange(S, mid, n);

        // conquer with recursion
        mergeSort(S1, comp);
        mergeSort(S2, comp);

        // merge results
        merge(S1, S2, S, comp);
        return S;
    }
}
