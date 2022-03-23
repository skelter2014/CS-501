package cs501;

import java.util.Iterator;
import java.util.NoSuchElementException;

import cs501.interfaces.Entry;

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {

    public static void main(String[] args){
        UnsortedTableMap<Integer,String> map = new UnsortedTableMap<>();

        for (int i=0; i < 16; i++){
            map.put(i, "value_"+i);
        }
        System.out.println("--keys--");
        for (Integer key : map.keySet()){
            System.out.println(key);
        }
        System.out.println("--entries--");
        for (Entry<Integer,String> entry : map.entrySet()){
            System.out.println(entry.getKey()+ ":" + entry.getValue());
        }
        System.out.println("--get(i)--");
        System.out.println(map.get(14));
    }
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    public UnsortedTableMap() {
    }

    private int findKey(K key) {
        int n = table.size();
        for (int j = 0; j < n; j++) {
            if (table.get(j).getKey().equals(key)) {
                return j;
            }
        }
        return -1;
    }

    public int size() {
        return table.size();
    }

    public V get(K key) {
        int j = findKey(key);
        if (j == -1) {
            return null;
        }
        return table.get(j).getValue();
    }

    public V put(K key, V value) {
        int j = findKey(key);
        if (j == -1) {
            table.add(new MapEntry<>(key, value));
            return null;
        } else {
            return table.get(j).setValue(value);
        }

    }

    public V remove(K key) {
        int j = findKey(key);
        int n = size();
        if (j == -1) {
            return null;
        }
        V result = table.get(j).getValue();
        if (j != n - 1) {
            table.set(j, table.get(n - 1));// move last entry to 'hole' created by removal
        }
        table.remove(n - 1);
        return result;
    }

    private class EntryIterator implements Iterator<Entry<K,V>> {
        private int j=0;
        public boolean hasNext() {return  j < table.size();}
        public Entry<K,V> next(){
            if (j==table.size()){throw new NoSuchElementException();}
            return table.get(j++);
        }
        public void remove(){}
    }
    private class EntryIterable implements Iterable<Entry<K,V>>{
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }
    public Iterable<Entry<K,V>> entrySet(){ return new EntryIterable();}


}