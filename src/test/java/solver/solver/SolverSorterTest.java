package solver.solver;

import org.junit.BeforeClass;
import org.junit.Test;
import solver.matrix.Matrix;
import solver.matrix.Row;

import static org.junit.Assert.assertArrayEquals;

public class SolverSorterTest {
    private static Solver solver;

    @BeforeClass
    public static void setUp() {
        solver = new Solver();
    }

    @Test
    public void weCanScanThroughMultipleRowsForNonZeroElementAndSwap() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{0, 1, 2, 9}),
                new Row("R2", new double[]{0, 4, -3, 1}),
                new Row("R3", new double[]{3, 6, -5, 0})});

        solver.sortMatrix(matrix, 0);

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

        solver.sortMatrix(matrix, 0);

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

        solver.sortMatrix(matrix, 0);

        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[2].getCoefficients(), 0.001);
    }
}
