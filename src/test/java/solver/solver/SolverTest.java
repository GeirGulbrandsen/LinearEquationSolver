package solver.solver;

import org.junit.Before;
import org.junit.Test;
import solver.commands.CommandCentral;
import solver.commands.SolverSolveCommand;
import solver.matrix.Matrix;
import solver.matrix.Row;

import static org.junit.Assert.*;

public class SolverTest {

    private static CommandCentral coms;
    private static Solver solver;

    @Before
    public void setUp() {
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

        matrix.print();
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

        assertEquals("No solutions", solver.getSolution());
    }

    @Test
    public void weCanDetectEquationsWithNoSolution() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{0, 0, 2, 9}),
                new Row("R2", new double[]{0, 5, 2, 1}),
                new Row("R3", new double[]{0, 0, 0, 2})});

        solver.checkForNoSolution(matrix);

        assertTrue("The System has a solution.", solver.hasNoSolution());
    }

    @Test
    public void weCanDetectEquationsWithInfiniteSolutions() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{1, 3, 4, 5}),
                new Row("R2", new double[]{0, 1, 5, 5}),
                new Row("R3", new double[]{0, 0, 0, 0}),
                new Row("R4", new double[]{0, 0, 0, 0}),
                new Row("R5", new double[]{0, 0, 0, 0})});

        solver.checkForInfiniteSolutions(matrix);

        assertTrue("System has less than infinite solutions.", solver.hasInfiniteSolutions());
    }

    @Test
    public void weHandleA4x4WithNoSolutions() {
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{0, 1, 2, 9}),
                new Row("R2", new double[]{0, 1, 3, 1}),
                new Row("R3", new double[]{1, 0, 6, 0}),
                new Row("R4", new double[]{2, 0, 2, 0})});

        coms.addCmd(new SolverSolveCommand(solver, matrix));
        coms.processCmds();

        matrix.print();
        System.out.println(solver.getSolution());
//        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[0].getCoefficients(), 0.001);
//        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[1].getCoefficients(), 0.001);
//        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, matrix.rows[2].getCoefficients(), 0.001);
    }
}
