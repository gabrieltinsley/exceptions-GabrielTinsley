import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

/**
 * The FormatChecker class reads a file and verifies if the matrix in the file
 * is valid or invalid. The file must begin with two integers, the first is for
 * row size and the second is for column size. Each value must be a double in
 * the matrix. Exceptions tell the user why the file is invalid in the command line.
 * 
 * @author Gabriel Tinsley
 * @version 1.0 CS221 Fall 2024
 */
class FormatChecker {

    /**
     * The main method that takes filenames as arguments, reads each file, and
     * checks the matrix format
     * 
     * @param args Array of filenames to check
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        if(args.length == 0) {
            System.out.println("Usage: $ java FormatChecker file1 [file2 ... fileN]");
        }

        for (String filename : args) {
            System.out.println(filename);
            boolean isValid = readFile(filename);

            if (isValid) {
                System.out.println("VALID");
            } else {
                System.out.println("INVALID");
            }
            System.out.println();
        }

    }

    /**
     * Reads a matrix from a file and checks the format.
     * The first line must contain two integers (number of rows and columns).
     * Each line after is in the matrix, and should contain double values.
     * The matrix must have the correct number of rows and columns as specified.
     * 
     * @param filename The name of the file to read the matrix from
     * @return true if the matrix format is valid, false otherwise
     * @throws FileNotFoundException
     */
    private static boolean readFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scnr = null;

        try {
            scnr = new Scanner(file);
            String firstLine = scnr.nextLine().trim();
            Scanner firstLineScanner = new Scanner(firstLine);

            // Checks if the first value for number of rows is an integer
            if (!firstLineScanner.hasNextInt()) {
                String val1 = firstLineScanner.next();
                firstLineScanner.close();
                throw new InputMismatchException(
                        "Number of rows needs to be an integer, this is not an integer: " + val1);
            }
            int rowSize = firstLineScanner.nextInt();
            int colSize = 0;

            // Checks if the second value for number of columns is an integer
            if (firstLineScanner.hasNextInt()) {
                colSize = firstLineScanner.nextInt();
                if (firstLineScanner.hasNext()) {
                    firstLineScanner.close();
                    throw new InputMismatchException("Too many values for number of rows and columns, expected two");
                }
            } else if (firstLineScanner.hasNext()) {
                String val2 = firstLineScanner.next();
                firstLineScanner.close();
                throw new InputMismatchException(
                        "Number of columns needs to be an integer, this is not an integer: " + val2);
            } else {
                firstLineScanner.close();
                throw new InputMismatchException("Not enough values for number of rows and columns, expected two");
            }

            firstLineScanner.close();

            // Check each row for valid entries
            for (int row = 0; row < rowSize; row++) {
                if (!scnr.hasNextLine()) {
                    throw new IndexOutOfBoundsException("Not enough rows in matrix");
                }
                String line = scnr.nextLine();
                Scanner lineScanner = new Scanner(line);

                // Check each column for valid entries
                for (int col = 0; col < colSize; col++) {

                    if (lineScanner.hasNextInt()) {
                        lineScanner.nextInt();
                    } else if (lineScanner.hasNextDouble()) {
                        lineScanner.nextDouble();
                    } else if (!lineScanner.hasNext()) {
                        lineScanner.close();
                        throw new NoSuchElementException("Not enough columns in row " + row);
                    } else {
                        String nextVal = lineScanner.next();
                        lineScanner.close();
                        throw new NumberFormatException("For Input String: " + nextVal);
                    }

                }
                if (lineScanner.hasNext()) {
                    lineScanner.close();
                    throw new IndexOutOfBoundsException("Too many columns in row " + row);
                }

                lineScanner.close();
            }

            // Checks there are no extra rows in the file
            if (scnr.hasNextLine() && (!scnr.nextLine().trim().isEmpty())) {
                throw new IndexOutOfBoundsException("Too many rows in matrix");
            }
            scnr.close();

        } catch (NumberFormatException | IndexOutOfBoundsException | NoSuchElementException | FileNotFoundException e) {
            System.out.println(e);
            return false;
        } finally {
            if (scnr != null) {
                scnr.close();
            }
        }

        return true;
    }
}
