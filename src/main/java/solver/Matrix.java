package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class Matrix {
    private int[] shape;
    Row[] rows;

    Matrix(Row[] rows) {
        this.rows = new Row[rows.length];
        System.arraycopy(rows, 0, this.rows, 0, rows.length);
        System.out.println();
        this.shape = new int[]{this.rows.length, this.rows[0].getLength()};
    }

    static Matrix readMatrixFromFile(String filename) {
        File inputFile = new File(filename);
        Scanner scanner;
        try {
            scanner = new Scanner(inputFile);
            int variables = scanner.nextInt();
            scanner.nextLine();

            Row[] rows = new Row[variables];

            for (int i = 0; i < variables; i++) {
                rows[i] = new Row("R" + (i + 1),
                        Arrays.stream(scanner.nextLine()
                                .split(" "))
                                .mapToDouble(Double::parseDouble)
                                .toArray());
            }
            return new Matrix(rows);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    int[] getShape() {
        return shape;
    }
}
