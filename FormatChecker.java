import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



class FormatChecker {
    private int[][] matrix;
    private Scanner scnr = null;

    public FormatChecker(String filename) throws FileNotFoundException {
        matrix = readFile(filename);
    }

    private int[][] readFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        scnr = new Scanner(file);

        int rowSize = Integer.parseInt(scnr.next());
        int colSize = Integer.parseInt(scnr.nextLine().trim());

        int[][] mat = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (scnr.hasNextInt()) {
                    mat[row][col] = scnr.nextInt();
                }
            }
        }

        scnr.close();
        return mat;
    }

    public boolean isValid() {
        //Scanner scan expects the next value to be an int
    if ( ! scnr.hasNextInt() ) {
	    try {
            throw new InvalidFileFormatException( "missing expected integer" );
        } catch (FormatChecker.InvalidFileFormatException e) {
            e.printStackTrace();
        }
    }

        return true;
    }

    /** Used when a file is found to be in the wrong format */
    public class InvalidFileFormatException extends IOException {
        /** Constructor */
        public InvalidFileFormatException(String message) {
            super(message); // pass through message to super
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        String str = "";

        return str;
    }
}