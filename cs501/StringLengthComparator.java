package cs501;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String> {

  public int compare(String a, String b) {
    if (a.length() < b.length()) {
      return -1;
    } else if (a.length() == b.length()) {
      return 0;
    } else {
      return 1;
    }
  }

  static class DefaultComparator<E> implements Comparator<E> {

    public int compare(E a, E b) {
      return ((Comparable<E>) a).compareTo(b);
    }
  }
}
