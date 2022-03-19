package cs501;

import cs501.interfaces.Entry;
import cs501.interfaces.PriorityQueueInterface;
import java.util.Comparator;

public abstract class AbstractPriortyQueue<K, V>
  implements PriorityQueueInterface<K, V> {

  protected static class PQEntry<K, V> implements Entry<K, V> {

    private K k;
    private V v;

    public PQEntry(K key, V value) {
      k = key;
      v = value;
    }

    public K getKey() {
      return k;
    }

    public V getValue() {
      return v;
    }
  }

  private Comparator<K> comp;

  //#region constructors
  protected AbstractPriortyQueue(Comparator<K> c) {
    comp = c;
  }

  protected AbstractPriortyQueue() {
    this(new StringLengthComparator.DefaultComparator<K>());
  }

  //#endregion
  protected int compare(Entry<K, V> a, Entry<K, V> b) {
    return comp.compare(a.getKey(), b.getKey());
  }

  protected boolean checkKey(K key) throws IllegalArgumentException {
    try {
      return comp.compare(key, key) == 0;
    } catch (ClassCastException e) {
      throw new IllegalArgumentException("bad key." + e);
    }
  }

  //#region interface methods.

  public boolean isEmpty() {
    return size() == 0;
  }
  //#endregion

}
