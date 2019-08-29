package solver.matrix;

import org.junit.Test;
import solver.commands.CommandCentral;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static solver.matrix.Matrix.readMatrixFromFile;

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

    @Test
    public void weCanSwapTwoRowsByCommand() {
        CommandCentral comms = new CommandCentral();

        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{1, 1, 2, 9}),
                new Row("R2", new double[]{2, 4, -3, 1}),
                new Row("R3", new double[]{3, 6, -5, 0})});

        comms.setSlot(new MatrixSwapRowsCommand(matrix, 0, 1));
        comms.processComms();

        assertArrayEquals(new double[]{2.0, 4.0, -3.0, 1.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{1.0, 1.0, 2.0, 9.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{3.0, 6.0, -5.0, 0.0}, matrix.rows[2].getCoefficients(), 0.001);
    }

    @Test
    public void weCanSwapColumnsByCommand() {
        CommandCentral comms = new CommandCentral();

        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{1, 1, 2, 9}),
                new Row("R2", new double[]{2, 4, -3, 1}),
                new Row("R3", new double[]{3, 6, -5, 0})});

        comms.setSlot(new MatrixSwapColsCommand(matrix, 0, 2));
        comms.processComms();

        assertArrayEquals(new double[]{2.0, 1.0, 1.0, 9.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{-3.0, 4.0, 2.0, 1.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{-5.0, 6.0, 3.0, 0.0}, matrix.rows[2].getCoefficients(), 0.001);
    }
}