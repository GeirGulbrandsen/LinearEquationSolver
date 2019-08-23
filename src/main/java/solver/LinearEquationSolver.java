package solver;

import java.io.FileWriter;
import java.io.IOException;

class LinearEquationSolver {

    static void normaliseRow(Row row, int pos) {
        double[] a = row.getCoefficients();
        double x = a[pos];

        System.out.printf("%.2f * %s -> %s\n", 1 / x, row.getName(), row.getName());

        for (int i = 0; i < a.length; i++) {
            a[i] = a[i] / x;
            if (a[i] == -0.0) {
                a[i] = 0.0;
            }
        }
    }

    static void gaussElimRow(Row rowA, Row rowB, int pos) {
        double[] a = rowA.getCoefficients();
        double[] b = rowB.getCoefficients();

        double x;

        x = -b[pos] / a[pos];

        System.out.printf("%.2f * %s + %s -> %s\n",
                x, rowA.getName(), rowB.getName(), rowB.getName());

        for (int i = 0; i < b.length; i++) {
            b[i] += a[i] * x;
        }
    }

    private static void jordanElimRow(Row rowA, Row rowB, int pos) {
        double[] a = rowA.getCoefficients();
        double[] b = rowB.getCoefficients();

        double x;

        x = -b[pos] / a[pos];

        System.out.printf("%.2f * %s + %s -> %s\n",
                x, rowA.getName(), rowB.getName(), rowB.getName());

        for (int i = 0; i < b.length; i++) {
            b[i] += a[i] * x;
        }
    }

    static void gaussJordanElim(Matrix matrix, String outputFile) {

        System.out.println("Start solving the equation.\nRows manipulation:");
        for (int pos = 0; pos < matrix.rows[0].getLength() - 1; pos++) {
            if (matrix.rows[pos].getValue(pos) != 1) normaliseRow(matrix.rows[pos], pos);
            for (int i = pos; i < matrix.rows.length - 1; i++) {
                gaussElimRow(matrix.rows[pos], matrix.rows[i + 1], pos);
            }
        }

        for (int pos = matrix.rows[0].getLength() - 2; pos > 0; pos--) {
            for (int row = pos - 1; row >= 0; row--) {
                jordanElimRow(matrix.rows[pos], matrix.rows[row], pos);
            }
        }

        printResult(matrix);

        StringBuilder output = new StringBuilder();
        for (Row row : matrix.rows) {
            output.append(row.getValue(row.getLength() - 1));
            output.append("\n");
        }

        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            fileWriter.write(output.toString());
        } catch (IOException e) {
            System.out.println("Could not open file");
        }
        System.out.printf("Saved to file %s\n%n", outputFile);
    }

    private static void printResult(Matrix matrix) {
        System.out.print("The solution is: (");
        for (Row row : matrix.rows) {
            System.out.printf("%f ", row.getValue(row.getLength() - 1));
        }
        System.out.print(")\n");


    }
}

