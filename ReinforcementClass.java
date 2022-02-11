
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

        // pivot the array. Columns are now rows
        int[][] matrix = {
                { 0, 1, 0, 1, 1, 0, 0, 1 }, // add more formations by increasing the width of each array
                { 0, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 0, 1, 0, 1, 1, 1, 1 },
                { 1, 0, 1, 1, 0, 1, 0, 0 }
        };
        // for (int i = 0; i < matrix.length; i++)
        // System.out.println(Arrays.toString(matrix[i]));

        int[][] transformedMatrix = transpose(matrix);

        int numFormations = transformedMatrix.length;
        int formationHeight = transformedMatrix[0].length;

        // Masks identify the highest order and lowest order bits that define a floor or
        // ceiling formation.
        int floorMask = 0b0001;
        int ceilingMask = (int) Math.pow(2, formationHeight - 1);
        int bitDepth = (int)Math.pow(2, formationHeight) - 1; // example: 0b1111

        for (int k = 0; k < transformedMatrix.length; k++) {
            // Convert the sequence of 1's and 0's to an int
            int formation = convertToBinary(transformedMatrix[k]);

            // is the formation attached to the floor ( ex. 0 0 0 1 )
            if ((formation & floorMask) != 0) {
                // Shift the digits to the right until you hit a zero. ()
                while ((formation & floorMask) != 0) {
                    formation = formation >> 1;
                }
                if ((formation | 0) == 0) {
                    System.out.println(Arrays.toString(transformedMatrix[k]) + ": Attached to Floor.");
                } else {
                    System.out.println(Arrays.toString(transformedMatrix[k]) + ": Not a valid formation.");
                }

                // Is the formation attached to the ceiling ( ex. 1 0 0 0 )
            } else if ((formation & ceilingMask) != 0) {

                while ((formation & ceilingMask) != 0) {
                    formation = (formation << 1) & bitDepth; // apply the bitDepth Mask to chop off higher bits
                }
                if ((formation | 0) == 0) {
                    System.out.println(Arrays.toString(transformedMatrix[k]) + ": Attached to Ceiling.");
                } else {
                    System.out.println(Arrays.toString(transformedMatrix[k]) + ": Not a valid formation.");
                }

                // possible column (attached to both)
            } else if ((formation & 0b1001) != 0) {
                System.out.println(Arrays.toString(transformedMatrix[k]) + ": Attached to both Floor and Ceiling.");

            } else
                System.out.println(Arrays.toString(transformedMatrix[k]) + ": Not a valid formation.");
        }

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
