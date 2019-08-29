package solver;

import java.util.Arrays;

class Solver {

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

    private static void gaussJordanElimRow(Row rowA, Row rowB, int pos) {
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

    static String gaussJordanElim(Matrix matrix) {

        System.out.println("Start solving the equation.\nRows manipulation:");
        for (int pos = 0; pos < matrix.rows[0].getLength() - 1; pos++) {
            if (matrix.rows[pos].getValue(pos) != 1) normaliseRow(matrix.rows[pos], pos);
            for (int i = pos; i < matrix.rows.length - 1; i++) {
                gaussJordanElimRow(matrix.rows[pos], matrix.rows[i + 1], pos);
            }
        }

        for (int pos = matrix.rows[0].getLength() - 2; pos > 0; pos--) {
            for (int row = pos - 1; row >= 0; row--) {
                gaussJordanElimRow(matrix.rows[pos], matrix.rows[row], pos);
            }
        }

        StringBuilder solution = new StringBuilder();
        for (Row row : matrix.rows) {
            solution.append(String.format("%.5f", row.getValue(row.getLength() - 1)));
            solution.append(" ");
        }

        return solution.toString().trim();
    }

    private static void printResult(Matrix matrix) {
        System.out.print("The solution is: (");
        for (Row row : matrix.rows) {
            System.out.printf("%f ", row.getValue(row.getLength() - 1));
        }
        System.out.print(")\n");
    }

    static double findX(double multiplier, double value) {
        return value / multiplier;
    }

    static String findXandY(String[] abc, String[] def) {

        Matrix matrix = new Matrix(new Row[]{
                new Row("R1",
                        Arrays.stream(abc)
                                .mapToDouble(Double::parseDouble)
                                .toArray()),
                new Row("R2",
                        Arrays.stream(def)
                                .mapToDouble(Double::parseDouble)
                                .toArray())
        });

        return gaussJordanElim(matrix);
    }

    String solveSystem(Matrix matrix) {
        return null;
    }
}

