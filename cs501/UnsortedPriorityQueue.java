package cs501;

import cs501.interfaces.Entry;
import cs501.interfaces.Position;
import cs501.interfaces.PositionalList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.RandomAccess;

public class UnsortedPriorityQueue<K, V> extends AbstractPriortyQueue<K, V> {

  private PositionalList<Entry<K, V>> list = new DoublyLinkedPositionList<Entry<K, V>>();

  public UnsortedPriorityQueue() {
    super();
  }

  public UnsortedPriorityQueue(Comparator<K> comp) {
    super(comp);
  }

  public static void main(String[] args) {
    //UnsortedPriorityQueue<String, String> queue = new UnsortedPriorityQueue<>( new StringLengthComparator());
    UnsortedPriorityQueue<Integer, String> queue = new UnsortedPriorityQueue<>( );

    Random rnd = new Random();


    for (int i = 0; i < 25; i++) {
        int v = rnd.nextInt(999);
        queue.insert(v, "value_"+ v);
    }


     //queue.insert(1, "world");
    // queue.insert("big", "sky");
    // queue.insert("john", "wick");
    long start = System.nanoTime();
    Position<Entry<Integer, String>> min = queue.findMin();
    long end = System.nanoTime();
    Integer key = min.getElement().getKey();
    String value = min.getElement().getValue();
    System.out.println("-------------Min Key value---------------");
    System.out.println("key: " + key + " value: " + value);
    System.out.println("time: " + (end - start));
    System.out.println("-----------------------------------------");

    start = System.nanoTime();
    Position<Entry<Integer, String>> first = queue.list.first();
    end = System.nanoTime();

    System.out.println("-------------Get first element------------");
    System.out.println("key: " + first.getElement().getKey() + " value: " + first.getElement().getValue());
    System.out.println("time: " + (end - start));
    System.out.println("-----------------------------------------");



    //Use Positions()
    System.out.println("Positions() method.");
    for (Position<Entry<Integer, String>> p : queue.list.positions()) {
      key = p.getElement().getKey();
      value = p.getElement().getValue();
      System.out.println("\t" + key + " : " + value);
    }
    //Use Iterator method

    Iterator iter = queue.list.iterator();
    System.out.println("iterator() method.");
    while(iter.hasNext()) {
        Entry<Integer, String> p = (Entry<Integer, String>) iter.next();

        System.out.print("\t" + p.getKey()); System.out.println(" : "+ p.getValue());
        // key = ((Position<Entry<String, String>>) p).getElement().getKey();
        // value = ((Position<Entry<String, String>>) p).getElement().getValue();
        // System.out.println("iterator() method.");
        // System.out.println("key: " + key + " value: " + value);
      }
  }

  private Position<Entry<K, V>> findMin() {
    Position<Entry<K, V>> small = list.first();
    for (Position<Entry<K, V>> walk : list.positions()) {
      if (compare(walk.getElement(), small.getElement()) < 0) {
        small = walk;
      }
    }
    return small;
  }

  public int size() {
    return list.size();
  }

  public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
    checkKey(key);
    Entry<K, V> newest = new PQEntry<>(key, value);
    list.addLast(newest);
    return newest;
  }

  public Entry<K, V> min() {
    if (list.isEmpty()) {
      return null;
    }
    return findMin().getElement();
  }

  public Entry<K, V> removeMin() {
    if (list.isEmpty()) {
      return null;
    }
    return list.remove((findMin()));
  }
}
