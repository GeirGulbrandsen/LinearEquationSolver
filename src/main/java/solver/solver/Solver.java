package solver.solver;

import solver.commands.CommandCentral;
import solver.commands.SolverSortMatrixCommand;
import solver.matrix.Matrix;
import solver.matrix.Row;

import java.util.Arrays;

public class Solver {
    private CommandCentral coms = new CommandCentral();

    public static void normaliseRow(Row row, int pos) {
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

    public void sortMatrix(Matrix matrix, int col) {
        if (matrix.getValue(0, col) == 0) {
            for (int i = 1; i < matrix.getShape()[0]; i++) {
                if (matrix.getValue(i, 0) != 0) {
                    matrix.swapRows(i, 0);
                    break;
                }
            }
        }
        if (matrix.getValue(0, col) == 0 && col < matrix.getShape()[1] - 1) {
            sortMatrix(matrix, col + 1);
            matrix.swapCols(col + 1, col);
        }
    }

    private void gaussJordanElimRow(Row rowA, Row rowB, int pos) {
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

    void gaussJordanElim(Matrix matrix) {

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

        matrix.setSolution(solution.toString().trim());
    }

    public double findX(double multiplier, double value) {
        return value / multiplier;
    }

    public String findXandY(String[] abc, String[] def) {

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

        gaussJordanElim(matrix);
        return matrix.getSolution();
    }

    public void solveSystem(Matrix matrix) {

        coms.addCmd(new SolverSortMatrixCommand(this, matrix));
        coms.addCmd(new SolverEliminationCommand(this, matrix));
        coms.processCmds();
    }
}

