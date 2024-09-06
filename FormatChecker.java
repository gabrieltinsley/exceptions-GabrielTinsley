import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class FormatChecker {
    private int[][] matrix;

    public FormatChecker(String filename) throws FileNotFoundException {
        matrix = readFile(filename);
    }

    private int[][] readFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scnr = new Scanner(file);

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
        return true;
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