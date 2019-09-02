package solver.solver;

import org.junit.Before;
import org.junit.Test;
import solver.commands.CommandCentral;
import solver.commands.SolverSolveCommand;
import solver.commands.SolverSortMatrixCommand;
import solver.matrix.Matrix;
import solver.matrix.Row;

import static org.junit.Assert.assertArrayEquals;

public class SolverSorterTest {
    private static Solver solver;
    private static CommandCentral coms;

    @Before
    public void setUp() {
        solver = new Solver();
        coms = new CommandCentral();
    }

    @Test
    public void weCanScanThroughMultipleRowsForNonZeroElementAndSwap() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{0, 1, 2, 9}),
                new Row("R2", new double[]{0, 4, -3, 1}),
                new Row("R3", new double[]{3, 6, -5, 0})});

        coms.addCmd(new SolverSortMatrixCommand(solver, matrix));
        coms.processCmds();

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

        coms.addCmd(new SolverSortMatrixCommand(solver, matrix));
        coms.processCmds();

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

        coms.addCmd(new SolverSortMatrixCommand(solver, matrix));
        coms.processCmds();

        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[2].getCoefficients(), 0.001);
    }

    @Test
    public void weHandle6x6() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{1, 6, 1, 1, 9, 1, 6}),
                new Row("R2", new double[]{0, 1, 4, 3, 2, 1, 5}),
                new Row("R3", new double[]{0, 0, 0, 1, 4, 2, 6}),
                new Row("R4", new double[]{0, 0, 0, 2, 9, 2, 4}),
                new Row("R5", new double[]{0, 0, 0, 2, 3, 1, 6}),
                new Row("R6", new double[]{0, 0, 5, 1, 7, 9, 6})});

        coms.addCmd(new SolverSortMatrixCommand(solver, matrix));
        coms.processCmds();

        matrix.print();
        assertArrayEquals(new double[]{1, 6, 1, 1, 9, 1, 6}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0, 1, 4, 3, 2, 1, 5}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0, 0, 5, 1, 7, 9, 6}, matrix.rows[2].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0, 0, 0, 2, 9, 2, 4}, matrix.rows[3].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0, 0, 0, 2, 3, 1, 6}, matrix.rows[4].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0, 0, 0, 1, 4, 2, 6}, matrix.rows[5].getCoefficients(), 0.001);
    }

    @Test
    public void weHandleAnother6x6() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{0, 6, 1, 3, 9, 1, 6}),
                new Row("R2", new double[]{9, 0, 4, 3, 6, 1, 5}),
                new Row("R3", new double[]{6, 5, 0, 1, 4, 2, 6}),
                new Row("R4", new double[]{4, 1, 1, 0, 9, 2, 4}),
                new Row("R5", new double[]{5, 4, 6, 2, 0, 1, 6}),
                new Row("R6", new double[]{3, 5, 4, 1, 7, 0, 6})});

        coms.addCmd(new SolverSortMatrixCommand(solver, matrix));
        coms.processCmds();

        matrix.print();
        assertArrayEquals(new double[]{9.0, 0.0, 4.0, 3.0, 6.0, 1.0, 5.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 6.0, 1.0, 3.0, 9.0, 1.0, 6.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{4.0, 1.0, 1.0, 0.0, 9.0, 2.0, 4.0}, matrix.rows[2].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{6.0, 5.0, 0.0, 1.0, 4.0, 2.0, 6.0}, matrix.rows[3].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{3.0, 5.0, 4.0, 1.0, 7.0, 0.0, 6.0}, matrix.rows[4].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{5.0, 4.0, 6.0, 2.0, 0.0, 1.0, 6.0}, matrix.rows[5].getCoefficients(), 0.001);
    }

    @Test
    public void weHandleA4x4WithNoSolutions() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{0, 1, 2, 9}),
                new Row("R2", new double[]{0, 1, 3, 1}),
                new Row("R3", new double[]{1, 0, 6, 0}),
                new Row("R4", new double[]{2, 0, 2, 0})});

        coms.addCmd(new SolverSortMatrixCommand(solver, matrix));
        coms.processCmds();

        matrix.print();
//        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[0].getCoefficients(), 0.001);
//        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[1].getCoefficients(), 0.001);
//        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[2].getCoefficients(), 0.001);
    }
}
