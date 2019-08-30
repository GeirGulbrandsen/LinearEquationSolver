package solver.solver;

import org.junit.Test;
import solver.matrix.Matrix;
import solver.matrix.Row;

import static org.junit.Assert.assertArrayEquals;

public class SolverSorterTest {

    @Test
    public void weCanScanThroughMultipleRowsForNonZeroElementAndSwap() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{0, 1, 2, 9}),
                new Row("R2", new double[]{0, 4, -3, 1}),
                new Row("R3", new double[]{3, 6, -5, 0})});

        sortMatrix(matrix, 0);

        assertArrayEquals(new double[]{3.0, 6.0, -5.0, 0.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 4.0, -3.0, 1.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 1.0, 2.0, 9.0}, matrix.rows[2].getCoefficients(), 0.001);
    }

    @Test
    public void weCanSortAMatrixIfAllTheFirstElementIsZeroInMultipleColumns() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{0, 0, 2, 9}),
                new Row("R2", new double[]{0, 0, -3, 1}),
                new Row("R3", new double[]{0, 0, -5, 0})});

        sortMatrix(matrix, 0);

        assertArrayEquals(new double[]{2.0, 0.0, 0.0, 9.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{-3.0, 0.0, 0.0, 1.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{-5.0, 0.0, 0.0, 0.0}, matrix.rows[2].getCoefficients(), 0.001);
    }

    @Test
    public void weHandleAllZeroMatrixes() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{0, 0, 0, 0}),
                new Row("R2", new double[]{0, 0, 0, 0}),
                new Row("R3", new double[]{0, 0, 0, 0})});

        sortMatrix(matrix, 0);

        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[2].getCoefficients(), 0.001);
    }

    private void sortMatrix(Matrix matrix, int col) {
        if (matrix.getValue(0, col) == 0) {
            for (int i = 1; i < matrix.getShape()[0]; i++) {
                if (matrix.getValue(i, 0) != 0) {
                    matrix.swapRows(i, 0);
                    break;
                }
            }
        }
        if (matrix.getValue(0, col) == 0 && col < matrix.getShape()[1]-1) {
            sortMatrix(matrix, col + 1);
            matrix.swapCols(col + 1, col);
        }
    }
}
