
import java.util.Arrays;
import java.util.Scanner;;

public class ReinforcementClass {
    boolean bool = false;
    char c = 0;
    byte b = 0;
    short s = 0;
    int i = 0;
    long l = 0;
    float f = 0.0f;
    double d = 0.0;

    public static void main(String[] args) {
        ReinforcementClass tester = new ReinforcementClass();
        // tester.inputAllBaseTypes();

        System.out.println("Number 3 is odd: " + ReinforcementClass.isOdd(3));
        System.out.println("Number 4 is odd: " + ReinforcementClass.isOdd(4));

        // System.out.println(ReinforcementClass.repeat1('a', 1000000));

        System.out.println("recursive add(1..6): \t" + add(6));
        System.out.println("recursive power 2^7:\t" + power1(2, 7));

        System.out.println("factorial: (9)\t" + factorial(9));
        int[] a = new int[] { 34, 12, 3, 55, 45, 68, 33, 11, 10, 9, 4, 5, 2, 1, 77, 67, 39, 20 };

        // array needs to be sorted
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        System.out.println("index of binary search(55) : " + binarySearch(a, 55, 0, a.length - 1));


        System.out.println( cap("hello world!".toCharArray()) );
    }

    public static String cap(char[] s){
        char[] result = new char[s.length];
        for (int i=0; i < s.length; i++){
            char c = s[i];
                result[i] = (char) (c & 0b1011111);
        }
        return String.valueOf(result);
       
    }

    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] result = new int[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    // a recursive add function
    public static int add(int a) {
        if (a == 1) {
            return 1;
        } else
            return (a + add(a - 1));
    }

    // recursive power class
    public static int power1(int x, int y) {
        if (y == 1) {
            return x;
        } else
            return x * power1(x, y - 1);
    }

    //

    public static boolean isOdd(int num) {
        return (num & 0x1) == 1;
    }

    public void inputAllBaseTypes() {
        Scanner scan = new Scanner(System.in);

        if (scan.hasNextBoolean()) {
            bool = scan.nextBoolean();
        }
        if (scan.hasNextByte()) {
            b = scan.nextByte();
            c = (char) b;
            i = c;
            l = c;
            s = (short) c;
            d = c;
            f = (float) c;
        }

        System.out.println(toString());
        scan.close();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("boolean = " + this.bool);
        sb.append("\n\rbyte = " + this.b);
        sb.append("\n\rchar = " + this.c);
        sb.append("\n\rint = " + this.i);
        sb.append("\n\rlong = " + this.l);
        sb.append("\n\rshort = " + this.s);
        sb.append("\n\rdouble = " + this.d);
        sb.append("\n\rfloat = " + this.f);

        return sb.toString();

    }

    /** Fragment 4.4 */
    public static String repeat1(char c, int n) {
        String answer = "";
        for (int i = 0; i < n; i++) {
            answer += c;
        }
        return answer;
    }

    public static boolean disjoint1(int[] groupA, int[] groupB, int[] groupC) {
        for (int a : groupA) {
            for (int b : groupB) {
                for (int c : groupC) {
                    if ((a == b) && (b == c)) {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    public static int factorial(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1; // base case. will stop recursion

        } else
            return n * factorial(n - 1);

    }

    public static int binarySearch(int[] data, int target, int low, int high) {
        if (low > high) {
            return -1;
        } else {
            int mid = (low + high) / 2;
            if (target == data[mid])
                return mid;
            else if (target < data[mid])
                return binarySearch(data, target, low, mid - 1);
            else
                return binarySearch(data, target, mid + 1, high);
        }
    }

    public static int convertToBinary(int[] a) {
        String binString = Arrays.toString(a)
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "");
        return Integer.parseInt(binString, 2);

    }
}
