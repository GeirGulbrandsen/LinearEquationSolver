package solver.solver;

import org.junit.Test;
import solver.matrix.Matrix;
import solver.matrix.Row;

import static org.junit.Assert.assertArrayEquals;

public class SolverSorterTest {

    @Test
    public void weCanSortAMatrixIfTheFirstElementIsZero() {
        Solver solver = new Solver();
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{0, 1, 2, 9}),
                new Row("R2", new double[]{2, 4, -3, 1}),
                new Row("R3", new double[]{3, 6, -5, 0})});

        sortMatrix(matrix);

        assertArrayEquals(new double[]{0.0, 1.0, 2.0, 9.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{2.0, 4.0, -3.0, 1.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{3.0, 6.0, -5.0, 0.0}, matrix.rows[2].getCoefficients(), 0.001);
    }

    private void sortMatrix(Matrix matrix) {
        matrix.getValue(0, 0);


    }
}

