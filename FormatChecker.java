
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

class FormatChecker {
    public static void main(String[] args) throws FileNotFoundException {
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

    private static boolean readFile(String filename) throws FileNotFoundException {
        File file = new File(filename);

        try {
            if (!file.exists()) {
                throw new FileNotFoundException(filename + " (The system cannot find the file specified)");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return false;
        }

        Scanner scnr = new Scanner(file);

        try {
            
            String firstLine = scnr.nextLine().trim();
            Scanner firstLineScanner = new Scanner(firstLine);
            if(! firstLineScanner.hasNextInt()) {
                String val1 = firstLineScanner.next();
                firstLineScanner.close();
                throw new InputMismatchException("Number of rows needs to be an integer, this is not an integer: " + val1);
            }
            int rowSize = firstLineScanner.nextInt();
            int colSize = 0;
            if(firstLineScanner.hasNextInt()) {
                colSize = firstLineScanner.nextInt();
                if(firstLineScanner.hasNext()) {
                    firstLineScanner.close();
                    throw new InputMismatchException("Too many values for number of rows and columns, expected two");
                }
            } else if (firstLineScanner.hasNext()) {
                String val2 = firstLineScanner.next();
                firstLineScanner.close();
                throw new InputMismatchException("Number of columns needs to be an integer, this is not an integer: " + val2);
            } else {
                firstLineScanner.close();
                throw new InputMismatchException("Not enough values for number of rows and columns, expected two");
            }
            

            firstLineScanner.close();
            

            for (int row = 0; row < rowSize; row++) {
                if(! scnr.hasNextLine()) {
                    throw new IndexOutOfBoundsException("Not enough rows in matrix");
                }
                String line = scnr.nextLine();
                Scanner lineScanner = new Scanner(line);
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

            if(scnr.hasNextLine() && (! scnr.nextLine().trim().isEmpty())) {
                throw new IndexOutOfBoundsException("Too many rows in matrix");
            }


        } catch (NumberFormatException | IndexOutOfBoundsException | NoSuchElementException e) {
            System.out.println(e);
            scnr.close();
            return false;
        } finally {
            scnr.close();
        }

        return true;
    }
}
