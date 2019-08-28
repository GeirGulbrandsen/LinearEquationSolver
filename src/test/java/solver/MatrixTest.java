package solver;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static solver.Solver.gaussJordanElim;
import static solver.Matrix.readMatrixFromFile;

public class MatrixTest {

    @Test
    public void weCanPerformGaussJordanEliminationOnAThreeByFourMatrix() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{1, 1, 2, 9}),
                new Row("R2", new double[]{2, 4, -3, 1}),
                new Row("R3", new double[]{3, 6, -5, 0})});

        gaussJordanElim(matrix, "out.txt");

        assertArrayEquals(new double[]{1.0, 0.0, 0.0, 1.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 1.0, 0.0, 2.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 0.0, 1.0, 3.0}, matrix.rows[2].getCoefficients(), 0.001);
    }

    @Test
    public void weCanPerformGaussJordanEliminationWhenWeNeedToNormaliseTheFirstRow() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{2, 0, 2}),
                new Row("R2", new double[]{0, 2, 2})});

        gaussJordanElim(matrix, "out.txt");

        assertArrayEquals(new double[]{1.0, 0.0, 1.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 1.0, 1.0}, matrix.rows[1].getCoefficients(), 0.001);
    }

    @Test
    public void readMatrixFromFileTest() {

        String inputFile = "src/test/resources/in.txt";

        Matrix matrix = readMatrixFromFile(inputFile);

        assertThat(new int[]{3, 4}, is(matrix.getShape()));
        assertArrayEquals(new double[]{1.0, 1.0, 2.0, 9.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{2.0, 4.0, -3.0, 1.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{3.0, 6.0, -5.0, 0.0}, matrix.rows[2].getCoefficients(), 0.001);
    }
}