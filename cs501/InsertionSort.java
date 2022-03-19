package cs501;

import java.util.Arrays;

public class InsertionSort {

  public static void main(String[] args) {
    int[] list = new int[] { 4, 5, 2, 3, 1, 8, 9, 7, 10, 6 };

    for (int i = 1; i < list.length; i++) {
      int temp = list[i];
      int j = i ;

      while ( j > 0 && temp < list[j-1] )  {
        list[j] = list[j - 1]; //swap
        j--;
      }
      list[j] = temp;
    }
    System.out.println(Arrays.toString(list));
  }
}
