
import java.util.Arrays;

public class InsertionSort_2 {
  static int counter = 0;

  public static void main(String[] args) {
    int[] list = new int[] { 4, 5, 2, 3, 1, 8, 9, 7, 10, 6 };
    sort(list);

    System.out.println(Arrays.toString(list));
  }

  public static void sort(int[] list) {
    for (int i = 1; i < list.length; i++) {
      int temp = list[i];
      int j = i;

      while (j > 0 && temp < list[j - 1]) {
        list[j] = list[j - 1]; // swap
        counter ++; //each iteration takes O(1) time.
        j--;
      }
      list[j] = temp;
      counter ++; //takes O(1) time
    }

  }

}
