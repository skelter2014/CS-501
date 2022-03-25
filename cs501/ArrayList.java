package cs501;

import cs501.interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();

    for (int i = 0; i < CAPACITY; i++) {
      list.add(i, "value_" + i);
    }

    Iterator<String> iter = list.iterator();

    while (iter.hasNext()) {
      System.out.println(iter.next());
    }

  }

  public static final int CAPACITY = 16;
  private E[] data;
  private int size = 0;

  public ArrayList() {
    this(CAPACITY);
  }

  public ArrayList(int capacity) {
    data = (E[]) new Object[capacity];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public E get(int i) {
    return data[i];
  }

  public E set(int i, E e) {
    data[i] = e;
    return e;
  }

  //Provide method to add a new entry to end of list.
  public void add(E e){
    add(size(),e);
  }
  public void add(int i, E e) {
    if (size == data.length) {
      throw new IllegalStateException("array is full.");
    }
    for (int k = size - 1; k >= i; k--) {
      data[k + 1] = data[k]; // shift to the right. Assume that this taks O(1) for each iteration
    }
    data[i] = e;
    size++;
  }

  public E remove(int i) {
    E temp = data[i];
    for (int k = i; k < size - 1; k++) {
      data[k] = data[k + 1];
    }
    data[size - 1] = null; // garbage collect
    size--;
    return temp;
  }

  public Iterator<E> iterator() {

    return Arrays.stream(data).iterator();

  }

}
