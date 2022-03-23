package cs501;

import java.util.Iterator;

import cs501.interfaces.Entry;
import cs501.interfaces.Map;

public abstract class AbstractMap<K, V> implements Map<K, V> {
    public boolean isEmpty() {
        return size() == 0;
    }

    // ------------Nested MapEntry Class------------------
    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K k;
        private V v;

        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }
        protected V setValue(V value){
            V old = v;
            v = value;
            return old;
        }


    }

    //@formatter:off
    private class KeyIterator implements Iterator<K> {
        private Iterator<Entry<K,V>> entries = entrySet().iterator();
        public boolean hasNext() { return entries.hasNext();}
        public K next() { return entries.next().getKey(); }//return the key
        public void remove() { throw new UnsupportedOperationException();}
    }
    private class KeyIterable implements Iterable<K> {
        public Iterator<K> iterator(){ return new KeyIterator();}
    }
    public Iterable<K> keySet() { return new KeyIterable();}

    private class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K,V>> entries = entrySet().iterator();
        public boolean hasNext() { return entries.hasNext();}
        public V next() { return entries.next().getValue();} //return the value
        public void remove() { throw new UnsupportedOperationException();}
    }
    private class ValueIterable implements Iterable<V> {
        public Iterator<V> iterator(){ return new ValueIterator();}
    }
    public Iterable<V> values() { return new ValueIterable();}

    //@formatter:on
}
