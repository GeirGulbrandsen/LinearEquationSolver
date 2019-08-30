package solver.solver;

import org.junit.Test;
import solver.commands.CommandCentral;
import solver.matrix.Matrix;
import solver.matrix.Row;

import static org.junit.Assert.assertEquals;

public class SolverCommandTest {

    @Test
    public void weCanCreateAndExecuteASolverCommand() {
        CommandCentral comms = new CommandCentral();
        Solver solver = new Solver();
        Matrix matrix = new Matrix(new Row[]{
                new Row("R1", new double[]{1, 1, 2, 9}),
                new Row("R2", new double[]{2, 4, -3, 1}),
                new Row("R3", new double[]{3, 6, -5, 0})});

        comms.setSlot(new SolverCommand(solver, matrix));
        comms.processComms();

        assertEquals("1.00000 2.00000 3.00000", matrix.getSolution());
    }


}