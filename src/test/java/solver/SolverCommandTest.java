package solver;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolverCommandTest {

    @Test
    public void weCanCreateAndExecuteASolverCommand() {

        Command command;

        Solver solver = new Solver();
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{1, 1, 2, 9}),
                new Row("R2", new double[]{2, 4, -3, 1}),
                new Row("R3", new double[]{3, 6, -5, 0})});

        command = new SolverCommand(solver, matrix);
        command.execute();

        assertEquals("1.00000 2.00000 3.00000", matrix.getSolution());
    }


}