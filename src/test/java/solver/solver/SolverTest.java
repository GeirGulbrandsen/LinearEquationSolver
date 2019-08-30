package solver.solver;

import org.junit.BeforeClass;
import org.junit.Test;
import solver.commands.CommandCentral;
import solver.commands.SolverSolveCommand;
import solver.commands.SolverSortMatrixCommand;
import solver.matrix.Matrix;
import solver.matrix.Row;

import static org.junit.Assert.assertArrayEquals;

public class SolverTest {

    private static CommandCentral coms;
    private static Solver solver;

    @BeforeClass
    public static void setUp() {
        coms = new CommandCentral();
        solver = new Solver();

    }

    @Test
    public void weCanPerformGaussJordanEliminationOnAThreeByFourMatrix() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{1, 1, 2, 9}),
                new Row("R2", new double[]{2, 4, -3, 1}),
                new Row("R3", new double[]{3, 6, -5, 0})});

        coms.addCmd(new SolverSolveCommand(solver, matrix));
        coms.processCmds();

        assertArrayEquals(new double[]{1.0, 0.0, 0.0, 1.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 1.0, 0.0, 2.0}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 0.0, 1.0, 3.0}, matrix.rows[2].getCoefficients(), 0.001);
    }

    @Test
    public void weCanPerformGaussJordanEliminationWhenWeNeedToNormaliseTheFirstRow() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{2, 0, 2}),
                new Row("R2", new double[]{0, 2, 2})});

        coms.addCmd(new SolverSolveCommand(solver, matrix));
        coms.processCmds();


        assertArrayEquals(new double[]{1.0, 0.0, 1.0}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 1.0, 1.0}, matrix.rows[1].getCoefficients(), 0.001);
    }

    @Test
    public void weCanSortAMatrixIfAllTheFirstElementIsZeroInMultipleColumns() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{0, 0, 2, 9}),
                new Row("R2", new double[]{0, 0, -3, 1}),
                new Row("R3", new double[]{0, 0, -5, 0})});

        coms.addCmd(new SolverSolveCommand(solver, matrix));
        coms.processCmds();

        matrix.print();

        assertArrayEquals(new double[]{1.0, 0.0, 0.0, 4.5}, matrix.rows[0].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 14.5}, matrix.rows[1].getCoefficients(), 0.001);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 22.5}, matrix.rows[2].getCoefficients(), 0.001);
    }
}
