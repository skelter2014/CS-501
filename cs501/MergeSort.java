package cs501;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;


/**
* 1. DIVIDE: If S has zero or one element, return S immediately; it is already
* sorted. Otherwise (S has at least two elements), remove all the elements
* from S and put them into two sequences, S1 and S2, each containing about
* half of the elements of S; that is, S1 contains the first ⌊n/2⌋ elements of S,
* and S2 contains the remaining ⌈n/2⌉ elements.
* 2. CONQUER: Recursively sort sequences S1 and S2.
* 3. COMBINE: Put the elements back into S by merging the sorted sequences S1
* and S2 into a sorted sequence.
 */
public class MergeSort {

    public static void main(String[] args) {

        MergeSort sorter = new MergeSort();
        Integer[] a;
        Integer[] b;
        Integer[] c;

        a = new Integer[]{5,9,7,3,1};
        b = new Integer[]{8,2,6,4,10};
        c = new Integer[]{22,45,34,56,12,37,23,44,19};



        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }

        };

        //Calling the merge function separately partially combines the 2 arrays - calling
        //it again has no effect. - splitting this into 2 halves and merging again
        //has no effect because it only compares a/b. You need to split each sub array
        //in half so that it compares a to a and b to b
        Integer[] result = new Integer[10];
        merge(a, b, result, comp);
        System.out.print(Arrays.toString(a));
        System.out.println(" " + Arrays.toString(b));
        System.out.println(Arrays.toString(result));

        

        //Correct usage of merge sort. call with an array and a comparator.
        Integer[] result2 = mergeSort(result, comp);
        System.out.println(Arrays.toString(mergeSort(result2, comp)));
        System.out.println(Arrays.toString(mergeSort(c, comp)));


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
