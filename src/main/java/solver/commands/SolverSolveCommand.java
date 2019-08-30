package solver.commands;

import solver.matrix.Matrix;
import solver.solver.Solver;

public class SolverSolveCommand implements Command {
    private Solver solver;
    private Matrix matrix;

    public SolverSolveCommand(Solver solver, Matrix matrix) {
        this.solver = solver;
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        this.solver.solveSystem(matrix);
    }
}
