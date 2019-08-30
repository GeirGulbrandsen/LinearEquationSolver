package solver.solver;

import solver.commands.Command;
import solver.matrix.Matrix;

public class SolverEliminationCommand implements Command {
    private final Solver solver;
    private final Matrix matrix;

    SolverEliminationCommand(Solver solver, Matrix matrix) {
        this.solver = solver;
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        Solver.gaussJordanElim(matrix);
    }
}
