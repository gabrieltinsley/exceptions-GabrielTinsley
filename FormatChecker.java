import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class FormatChecker {
    private int[][] matrix;

    public FormatChecker (String filename) throws FileNotFoundException {
        matrix = readFile(filename);
    }

    private int[][] readFile (String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scnr = new Scanner(file);

        int rowSize = scnr.nextInt();
        int colSize = scnr.nextInt();


        int[][] mat = new int[rowSize][colSize];

            for (int row = 0; row < rowSize; row++) {
                for (int col = 0; col < colSize; col++) {
                    mat[row][col] = scnr.nextInt();
                }
            }
            
        scnr.close();
        return mat;
    }
}