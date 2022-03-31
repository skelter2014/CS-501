package cs501;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class QuickSort {

    static int steps;

    public static void main(String[] args) {

        // Comparator<Integer> comp = new Comparator<Integer>() {
        // @Override
        // public int compare(Integer o1, Integer o2) {
        // return o1.compareTo(o2);
        // }

        // };

        // ArrayList<Integer> a = new ArrayList<>();
        // Random rnd = new Random();
        // for (int i = 0; i < 128; i++) {
        // Integer next = rnd.nextInt(128);
        // if (!a.contains(next)) {
        // a.add(next);
        // }
        // }

        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(34, 12, 3, 55, 45, 68,
                33, 11, 10, 9, 4, 5, 2, 1, 77, 67, 39, 20));
        // ArrayList<Integer> a = new ArrayList<>( Arrays.asList(1, 2, 3, 4, 5, 9, 10,
        // 11, 12, 20, 33, 34, 39, 45, 55, 67, 68, 77));
        // ArrayList<Integer> a = new ArrayList<>( Arrays.asList(77 ,86 ,76 ,55 ,54 ,93
        // ,43 ,33 ,02 ,21 ,11 ,01 ,9 ,5 ,4 ,3 ,2 ,1));
        System.out.println("\u001B[37m" + a.toString());
        sort(a, getComparator());
        System.out.println("\u001B[37m" + a.toString());

        System.out.println("==============================================================================");
        int logN = (int) (Math.log(a.size()) / Math.log(2));
        int n = a.size();
        System.out.println("Total: " + n + " logN: " + logN + " NlogN: " + n * logN);
        System.out.println("==============================================================================");

        a = new ArrayList<>(Arrays.asList(34, 12, 3, 55, 45, 68,
                33, 11, 10, 9, 4, 5, 2, 1, 77, 67, 39, 20));
        quickSortInPlace(a.toArray(), getComparator(), 0, a.size() - 1);

    }

    public static Comparator getComparator() {
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }

        };
        return comp;
    }

    @SuppressWarnings("unchecked")
    public static <K> void sort(ArrayList<K> S, Comparator<K> comp) {

        int n = S.size();
        if (n < 2)
            return;

        K pivot = (K) S.get(n - 1);
        // System.out.println("pivot: " + pivot);

        ArrayList<K> L = new ArrayList<>();
        ArrayList<K> E = new ArrayList<>();
        ArrayList<K> G = new ArrayList<>();

        int k = 0;
        while (S.isEmpty() != true) {
            K element = S.remove(k); // just work off the front of the list.
            int c = comp.compare(element, pivot);
            if (c < 0) {
                L.add(element);
            } else if (c == 0) {
                E.add(element);
            } else {
                G.add(element);
            }
            steps++;
            System.out.print(String.format("\u001B[37msteps: %02d", steps));
            System.out.print("\u001B[34m  L::" + L.toString());
            System.out.print("\u001B[33m  E::" + E.toString());
            System.out.println("\u001B[32m  G::" + G.toString());
        }

        sort(L, comp);
        sort(G, comp);

        while (L.isEmpty() == false) {
            S.add(L.remove(0));
        }
        while (E.isEmpty() == false) {
            S.add(E.remove(0));
        }
        while (G.isEmpty() == false) {
            S.add(G.remove(0));
        }
        System.out.print(String.format("\u001B[37msteps: %02d", steps++));
        System.out.println("  S - " + S.toString());

    }

    /** Sort the subarray S[a..b] inclusive. */
    private static <K> void quickSortInPlace(K[] S, Comparator<K> comp,
            int a, int b) {
        if (a >= b)
            return; // subarray is trivially sorted
        int left = a;
        int right = b - 1;
        K pivot = S[b];
        K temp; // temp object used for swapping
        while (left <= right) { // scan until reaching value equal or larger than pivot (or right marker)
            while (left <= right && comp.compare(S[left], pivot) < 0)
                left++;
            // scan until reaching value equal or smaller than pivot (or left marker)
            while (left <= right && comp.compare(S[right], pivot) > 0)
                right--;
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
        // make recursive calls
        quickSortInPlace(S, comp, a, left - 1);
        quickSortInPlace(S, comp, left + 1, b);
    }

}
