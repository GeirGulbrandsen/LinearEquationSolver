package solver.matrix;

import org.junit.Test;
import solver.commands.CommandCentral;
import solver.commands.MatrixSwapColsCommand;
import solver.commands.MatrixSwapRowsCommand;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static solver.matrix.Matrix.readMatrixFromFile;

public class MatrixTest {

    @Test
    public void weCanReadAMatrixFromFile() {

        String inputFile = "src/test/resources/in.txt";

        Matrix matrix = readMatrixFromFile(inputFile);

        assertNotNull(matrix);
        assertThat(new int[]{3, 4}, is(matrix.getShape()));
        assertArrayEquals(new double[]{1.0, 1.0, 2.0, 9.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{2.0, 4.0, -3.0, 1.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{3.0, 6.0, -5.0, 0.0}, matrix.rows[2].getCoefficients(), 0.001);
    }

    @Test
    public void weReturnNullWhenTryingToReadMatrixFromMissingFile() {

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

        comms.addCmd(new MatrixSwapRowsCommand(matrix, 0, 1));
        comms.processCmds();

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

        comms.addCmd(new MatrixSwapColsCommand(matrix, 0, 2));
        comms.processCmds();

        assertArrayEquals(new double[]{2.0, 1.0, 1.0, 9.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{-3.0, 4.0, 2.0, 1.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{-5.0, 6.0, 3.0, 0.0}, matrix.rows[2].getCoefficients(), 0.001);
    }

    @Test
    public void getValue() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{1, 1, 2, 9}),
                new Row("R2", new double[]{2, 4, -3, 1}),
                new Row("R3", new double[]{3, 6, -5, 0})});

        assertEquals(6.0, matrix.getValue(2, 1), 0.00001);
    }
}