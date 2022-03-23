package cs501;

import cs501.interfaces.Entry;
import java.util.Comparator;

public class HeapPriorityQueue<K, V> extends AbstractPriortyQueue<K, V> {

    /**
     *            1
     *        2      3
     *      7   5   4   6
     * ---------------------------------------------------------
     * | 1 | 2 | 3 | 7 | 5 | 4 | 6 |
     * | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 
     */

    public static void main(String[] args) {
        HeapPriorityQueue<Integer, String> heap = new HeapPriorityQueue<>();
        // heap.insert(8, "buckle");
        heap.insert(7, "seven");
        heap.insert(1, "one");
        heap.insert(6, "six");
        heap.insert(2, "two");
        heap.insert(4, "four");
        heap.insert(3, "three");
        heap.insert(5, "five");


        //-972458019
        int hashA = heap.hashCode();


        while (heap.isEmpty() == false) {
            Entry<Integer, String> value = heap.removeMin();
            System.out.println(value.getValue());
        }

        System.out.println("------heapify-----------");


        Integer[] keys = new Integer[]{6,3,1,2,5,4,7};
        String[] values = new String[]{"six","three","one","two","five","four","seven"};

        heap = new HeapPriorityQueue<>(keys, values);
        //-1071917151
        int hashB = heap.hashCode();

        while (heap.isEmpty() == false) {
            Entry<Integer, String> value = heap.removeMin();
            System.out.println(value.getValue());
        }

        System.out.println(hashA == hashB);
    }

    public int hashCode(){
        ArrayList<Entry<K, V>> list = this.arrayList;
        int h = 0;
        for (int i=0; i < list.size(); i++){
            Entry<K,V> entry = list.get(i);
            h ^= entry.hashCode();
            h = (h<<5)|(h>>>27);
        }
        return h;


    }

    protected ArrayList<Entry<K, V>> arrayList = new ArrayList<>();

    //@formatter:off
    HeapPriorityQueue() { super(); }
    HeapPriorityQueue(Comparator<K> comp) { super(comp); }
    HeapPriorityQueue(K[] keys, V[] values){
        super();
        for (int j=0; j < Math.min(keys.length, values.length); j++){
            add(new PQEntry<>(keys[j], values[j]));
        }
        heapify();
    }

    protected void heapify() {
        int startIndex = parent(size() - 1);
        for (int j=startIndex; j >= 0; j--){
            downHeap(j);
        }

    }
    // utilities
    protected int left(int j) { return (2 * j) + 1; }
    protected int parent(int j) { return (j - 1) / 2; }
    protected int right(int j) { return (2 * j) + 2; }
    protected boolean hasLeft(int j) { return left(j) < arrayList.size(); }
    protected boolean hasRight(int j) { return right(j) < arrayList.size(); }
    //@formatter:on
    protected void swap(int i, int j) {
        Entry<K, V> temp = arrayList.get(i);
        arrayList.set(i, arrayList.get(j));
        arrayList.set(j, temp);
    }

    //Adds to the end of the heap.
    private Entry<K,V> add(Entry<K,V> entry){
        checkKey(entry.getKey());
        arrayList.add(arrayList.size(), entry);
        return entry;
    }

    protected void upHeap(int j) {
        while (j > 0) {
            int p = parent(j);
            if (compare(arrayList.get(j), arrayList.get(p)) >= 0)
                break;
            swap(j, p);
            j = p;
        }
    }

    protected void downHeap(int j) {
        while (hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(arrayList.get(leftIndex), arrayList.get(rightIndex)) > 0) {
                    smallChildIndex = rightIndex;
                }
            }
            if (compare(arrayList.get(smallChildIndex), arrayList.get(j)) >= 0) {
                break;
            }
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    public int size() { // TODO Auto-generated method stub
        return arrayList.size();
    }

    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new PQEntry<>(key, value);
        arrayList.add(arrayList.size(), newest);
        upHeap(arrayList.size() - 1);
        return newest;
    }

    public Entry<K, V> removeMin() {
        if (arrayList.isEmpty()) {
            return null;
        }
        Entry<K, V> result = arrayList.get(0);
        swap(0, arrayList.size() - 1);
        arrayList.remove(arrayList.size() - 1);
        downHeap(0);
        return result;

    }

    public Entry<K, V> min() {
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList.get(0);
    }

}
