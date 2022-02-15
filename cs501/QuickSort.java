package cs501;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class QuickSort {

    static int steps;

    public static void main(String[] args) {



        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }

        };

        //ArrayList<Integer> a = new ArrayList<>( Arrays.asList(34, 12, 3, 55, 45, 68, 33, 11, 10, 9, 4, 5, 2, 1, 77, 67, 39, 20 ));
        //ArrayList<Integer> a = new ArrayList<>( Arrays.asList(12, 23, 45));//, 68, 33, 11, 10, 9, 4, 5, 2, 1, 77, 67, 39, 20 ));
        //ArrayList<Integer> a = new ArrayList<>( Arrays.asList(1, 2, 3, 4, 5, 9, 10, 11, 12, 20, 33, 34, 39, 45, 55, 67, 68, 77));
        ArrayList<Integer> a = new ArrayList<>( Arrays.asList(77 ,86 ,76 ,55 ,54 ,93 ,43 ,33 ,02 ,21 ,11 ,01 ,9 ,5 ,4 ,3 ,2 ,1));
        System.out.println("\u001B[37m"+a.toString());
        quickSort(a, comp);
        System.out.println("\u001B[37m"+a.toString());
  

    }

    @SuppressWarnings("unchecked")
    public static <K> void quickSort(ArrayList<K> S, Comparator<K> comp) {

        int n = S.size();
        if (n < 2)
            return ;

        K pivot = (K) S.get(n-1);
        //System.out.println("pivot: " + pivot);

        ArrayList<K> L = new ArrayList<>();
        ArrayList<K> E = new ArrayList<>();
        ArrayList<K> G = new ArrayList<>();

        int k = 0;
        while (S.isEmpty() != true){
            K element = S.remove(k); //just work off the front of the list.
            int c = comp.compare(element, pivot);
            if (c < 0) {
                L.add(element);
            } else if (c == 0) {
               E.add(element);
            } else {
                G.add(element);
            }

        }
        System.out.print(String.format("\u001B[37msteps: %02d", steps++));
        System.out.print("\u001B[34m  L::" + L.toString());
        System.out.print("\u001B[33m  E::" + E.toString());
        System.out.println("\u001B[32m  G::" + G.toString());
        quickSort(L, comp);
        quickSort(G, comp);




       while(L.isEmpty() == false) {
            S.add(L.remove(0));
        }
        while(E.isEmpty() == false) {
            S.add(E.remove(0));
        }
        while(G.isEmpty() == false) {
            S.add(G.remove(0));
        }
        System.out.print(String.format("\u001B[37msteps: %02d", steps++));
        System.out.println("  S - " + S.toString());

        



    }

}
