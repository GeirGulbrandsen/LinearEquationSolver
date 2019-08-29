package solver;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static solver.Matrix.readMatrixFromFile;

public class MatrixTest {

    @Test
    public void readMatrixFromFileTest() {

        String inputFile = "src/test/resources/in.txt";

        Matrix matrix = readMatrixFromFile(inputFile);

        assertNotNull(matrix);
        assertThat(new int[]{3, 4}, is(matrix.getShape()));
        assertArrayEquals(new double[]{1.0, 1.0, 2.0, 9.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{2.0, 4.0, -3.0, 1.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{3.0, 6.0, -5.0, 0.0}, matrix.rows[2].getCoefficients(), 0.001);
    }

    @Test
    public void weReturnNullWhenTryingToReadMatrixFromMissingFileTest() {

        String inputFile = "src/test/resources/inn.txt";

        Matrix matrix = readMatrixFromFile(inputFile);

        assertNull(matrix);
    }
}