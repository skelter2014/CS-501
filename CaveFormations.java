import java.util.Arrays;

public class CaveFormations {

    public static void main(String[] args) {
        // pivot the array. Columns are now rows
        int[][] matrix = {
                { 0, 1, 0, 1, 0, 1, 0, 0, 1 }, // 
                { 0, 1, 1, 1, 1, 1, 1, 1, 0 }, //---> extend this way to add more formations
                { 0, 0, 1, 0, 0, 1, 1, 1, 1 }, // add new rows to increase height of formation.
                { 1, 0, 1, 1, 1, 0, 1, 0, 0 }
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
        int bitDepth = (int) Math.pow(2, formationHeight) - 1; // example: 0b1111

        for (int k = 0; k < transformedMatrix.length; k++) {
            // Convert the sequence of 1's and 0's to an int
            int formation = convertToBinary(transformedMatrix[k]);

            // is the formation attached to the floor ( ex. 0 0 0 1 )
            if ((formation & floorMask) != 0) {
                // Shift the digits to the right until you hit a zero. ()
                while ((formation & floorMask) != 0) {
                    formation = formation >> 1;
                }
                if ((formation | 0) == 0) { // where shouldn't be anything left.
                    System.out.println(Arrays.toString(transformedMatrix[k]) + ": Attached to Floor.");
                } else {
                    System.out.println(Arrays.toString(transformedMatrix[k]) + ": Not a valid formation.");
                }

                // Is the formation attached to the ceiling ( ex. 1 0 0 0 )
            } else if ((formation & ceilingMask) != 0) {

                while ((formation & ceilingMask) != 0) {
                    formation = (formation << 1) & bitDepth; // apply the bitDepth Mask to chop off higher bits
                }
                if ((formation | 0) == 0) { // there shouldn't be anything left
                    System.out.println(Arrays.toString(transformedMatrix[k]) + ": Attached to Ceiling.");
                } else {
                    System.out.println(Arrays.toString(transformedMatrix[k]) + ": Not a valid formation.");
                }
            }
        }
    }

    // Take a 2 dimentional array of N rows and M columns and transpose i.e. M rows
    // x N Columns
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

    // Take an array of 1's and Zero'1 and convert it to an integer
    //This is pretty rough and can be done cleaner.
    public static int convertToBinary(int[] a) {
        String binString = Arrays.toString(a)
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "");
        return Integer.parseInt(binString, 2);

    }
}
