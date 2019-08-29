package solver.solver;

import solver.commands.Command;
import solver.matrix.Matrix;

public class SolverCommand implements Command {
    private Solver solver;
    private Matrix matrix;

    public SolverCommand(Solver solver, Matrix matrix) {
        this.solver = solver;
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        solver.solveSystem(matrix);
    }
}
