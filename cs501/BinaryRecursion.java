package cs501;

public class BinaryRecursion {

    public static void main(String[] args){
        int[] data = new int[]{0,1,2,3,4,5,6,7,8,9};

        System.out.println(binarySum(data, 0, 2,"-"));
    }

  public static int binarySum(int[] data, int low, int high, String src) {
      System.out.println("low: " + low + " high: " + high + " src: " + src);
    if (low > high) {
      return 0;
    } else if (low == high) {
      return data[low];
    } else {
      int mid = (low + high) / 2;
      return binarySum(data, low, mid,"l") + binarySum(data, mid + 1, high,"r");
    }
  }
}
