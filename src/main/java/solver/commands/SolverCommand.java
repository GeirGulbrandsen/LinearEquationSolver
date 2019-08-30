package solver.commands;

import solver.matrix.Matrix;
import solver.solver.Solver;

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
