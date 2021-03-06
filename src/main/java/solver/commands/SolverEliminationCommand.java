package solver.commands;

import solver.commands.Command;
import solver.matrix.Matrix;
import solver.solver.Solver;

public class SolverEliminationCommand implements Command {
    private final Solver solver;
    private final Matrix matrix;

    public SolverEliminationCommand(Solver solver, Matrix matrix) {
        this.solver = solver;
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        solver.gaussJordanElim(matrix);
    }
}
