
import cs501.interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Assignment 3
 * P-7.58 Implement an experiment, using techniques similar to those in section
 * 4.1
 * to test the efficiency of n successive calls to the add method of an
 * ArrayList,
 * for various n, under each of the following scenarios:
 * a. Each add takes place at index 0.
 * b. Each add takes place at index size( ) /2.
 * c. Each add takes place at index size( ).
 * Analyze your empirical results.
 */

public class ArrayList<E> implements List<E> {

  public static void main(String[] args) {

    //Test with 3 different sizes of n
    int[] n = new int[] { 256, 512, 1024 };

    System.out.println("--------------------------------------------------");
    System.out.println("\tScenario #1 - Add @ Front of List");
    System.out.println("\tScenario #2 - Add @ Middle of List");
    System.out.println("\tScenario #3 - Add @ End of List");

    //Run tests for 3 values of n: byte, 2bytes and 4bytes value
    for (int j = 0; j < n.length; j++) {
      ArrayList<String> list = new ArrayList<>(n[j]);
      System.out.println("--------------------------------------------------");
      System.out.println("N: " + n[j]);

      // Scenario #1 Add takes place at Index 0 - The front of the list. All elements
      // need to shift left.
      list.counter = 0;
      long startTime = System.nanoTime();
      for (int i = 0; i < n[j]; i++) {
        int index = 0; // always insert at front of list. (index 0)
        list.add(index, "Front_Of_List" + i);
      }
      long endTime = System.nanoTime();

      System.out.println("Scenario #1 \t Number of internal moves: " + list.counter);
      System.out.println("\t\t total time in ns: " + (endTime - startTime));

      // Scenario #2 - Add takes place at Index Size() / 2 - the middle of the list.
      list = new ArrayList<>(n[j]);
      list.counter = 0;
      startTime = System.nanoTime();
      for (int i = 0; i < n[j]; i++) {
        int index = list.size / 2; // insert in the middle
        list.add(index, "Middle_of_List" + i);
      }
      endTime = System.nanoTime();
      System.out.println("Scenario #2 \t Number of internal moves: " + list.counter);
      System.out.println("\t\t total time in ns: " + (endTime - startTime));

      // Scenario #3 - Add takes place at Index Size() - the end of the list.
      list = new ArrayList<>(n[j]);
      list.counter = 0;
      startTime = System.nanoTime();
      for (int i = 0; i < n[j]; i++) {
        int index = list.size; // new element is inserted at end of list.
        list.add(index, "End_Of_List" + i);
      }
      endTime = System.nanoTime();
      System.out.println("Scenario #3 \t Number of internal moves: " + list.counter);
      System.out.println("\t\t total time in ns: " + (endTime - startTime));

    }

  }

  private E[] data;
  private int size = 0;

  // This counter is used to count the inner loop iterations to help measure
  // efficiency
  private int counter;

  //default size is 1024
  public ArrayList() {
    this(1024);
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

  // Provide method to add a new entry to end of list.
  public void add(E e) {
    add(size(), e);
  }

  public void add(int i, E e) {
    if (size == data.length) {
      throw new IllegalStateException("array is full.");
    }
    for (int k = size - 1; k >= i; k--) {
      data[k + 1] = data[k]; // shift element to the right.
      counter++; // Assume that this takes O(1) for each iteration so increment the counter;

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
