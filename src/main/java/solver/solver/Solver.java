package solver.solver;

import solver.commands.CommandCentral;
import solver.commands.SolverEliminationCommand;
import solver.commands.SolverSortMatrixCommand;
import solver.commands.SolverUndoColSwapsCommand;
import solver.matrix.Matrix;
import solver.matrix.Row;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Solver {
    private CommandCentral coms;
    private ArrayDeque<Integer> undoColSwapList;
    private boolean hasNoSolution = false;
    private boolean hasInfiniteSolutions = false;
    private String solution = "";

    public Solver() {
        coms = new CommandCentral();
        undoColSwapList = new ArrayDeque<>();
    }

    boolean hasNoSolution() {
        return hasNoSolution;
    }

    boolean hasInfiniteSolutions() {
        return hasInfiniteSolutions;
    }

    public static void normaliseRow(Row row, int pos) {
        double[] a = row.getCoefficients();
        double x = a[pos];

        if (x != 0.0) {
            System.out.printf("%.2f * %s -> %s\n", 1 / x, row.getName(), row.getName());

            for (int i = 0; i < a.length; i++) {
                a[i] = a[i] / x;
                if (a[i] == -0.0) {
                    a[i] = 0.0;
                }
            }
        }
    }

    public void sortMatrix(Matrix matrix, int col) {
        for (int i = col; i < matrix.getShape()[1] - 1; i++) {
            if (matrix.getValue(i, col) == 0d) {
                for (int j = i + 1; j <= matrix.getShape()[0] - 1; j++) {
                    if (matrix.getValue(j, col) != 0) {
                        matrix.swapRows(j, col);
                        break;
                    }
                }
                if (matrix.getValue(col, col) == 0 && col < matrix.getShape()[1] - 2) {
                    sortMatrix(matrix, col + 1);
                    matrix.swapCols(col + 1, col);
                    this.undoColSwapList.add(col);
                }
            }
            col++;
        }
    }

    private void gaussJordanElimRow(Row rowA, Row rowB, int pos) {
        double[] a = rowA.getCoefficients();
        double[] b = rowB.getCoefficients();

        double x;

        if (a[pos] != 0.0) {
            x = -b[pos] / a[pos];

            System.out.printf("%.2f * %s + %s -> %s\n",
                    x, rowA.getName(), rowB.getName(), rowB.getName());

            for (int i = 0; i < b.length; i++) {
                b[i] += a[i] * x;
            }
        }
    }

    public void gaussJordanElim(Matrix matrix) {

        System.out.println("Start solving the equation.\nRows manipulation:");
        for (int pos = 0; pos < matrix.rows[0].getLength() - 1; pos++) {
            if (matrix.rows[pos].getValue(pos) != 1) normaliseRow(matrix.rows[pos], pos);
            for (int i = pos; i < matrix.rows.length - 1; i++) {
                gaussJordanElimRow(matrix.rows[pos], matrix.rows[i + 1], pos);
            }
        }

        this.checkForNoSolution(matrix);
        if (this.hasNoSolution()) {
            solution = "No solutions";
        } else {
            this.checkForInfiniteSolutions(matrix);
            if (this.hasInfiniteSolutions) {
                solution = "Infinitely many solutions";
            } else {
                for (int pos = matrix.rows[0].getLength() - 2; pos > 0; pos--) {
                    for (int row = pos - 1; row >= 0; row--) {
                        gaussJordanElimRow(matrix.rows[pos], matrix.rows[row], pos);
                    }
                }
                StringBuilder buildSolution = new StringBuilder();
                for (Row row : matrix.rows) {
                    String str = (String.format("%.5f", row.getValue(row.getLength() - 1)));
                    if (Double.parseDouble(str) != 0.0) {
                        solution = String.format("%s%s\n", solution, str);
                    }
                }
            }
        }
        matrix.setSolution(solution.trim());
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

    public void undoColSwaps(Matrix matrix) {
        int col;
        while (!undoColSwapList.isEmpty()) {
            col = undoColSwapList.removeLast();
            matrix.swapCols(col, col + 1);
        }
    }

    void checkForNoSolution(Matrix matrix) {
        boolean hasSolution = true;
        for (Row row : matrix.rows) {
            int i = 0;
            boolean zeroRow = true;
            while (i < row.getLength() - 1) {
                if (row.getCoefficients()[i] != 0.0) {
                    zeroRow = false;
                }
                i++;
            }
            if (zeroRow) {
                if (row.getCoefficients()[row.getLength() - 1] != 0.0) {
                    hasSolution = false;
                }
            }
        }
        hasNoSolution = !hasSolution;
    }

    void checkForInfiniteSolutions(Matrix matrix) {
        int significantEquations = 0;
        for (Row row : matrix.rows) {
            boolean isAllZeros = true;
            for (double coeff : row.getCoefficients()) {
                if (coeff != 0.0) {
                    isAllZeros = false;
                    break;
                }
            }
            if (!isAllZeros) {
                significantEquations++;
            }
        }
        if (significantEquations < matrix.getShape()[1] - 1) {
            hasInfiniteSolutions = true;
        }
    }

    public void solveSystem(Matrix matrix) {
        coms.addCmd(new SolverSortMatrixCommand(this, matrix));
        coms.addCmd(new SolverEliminationCommand(this, matrix));
        coms.processCmds();

        if (this.hasInfiniteSolutions || this.hasNoSolution) {
        } else {
            if (!undoColSwapList.isEmpty()) {
                coms.addCmd(new SolverUndoColSwapsCommand(this, matrix));
                coms.processCmds();
            }
            solution = solution.trim();
        }
    }

    public String getSolution() {
        return solution;
    }
}

