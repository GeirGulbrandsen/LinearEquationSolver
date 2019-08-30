package solver.commands;

import solver.matrix.Matrix;
import solver.solver.Solver;

public class SolverUndoColSwapsCommand implements Command {
    private final Solver solver;
    private final Matrix matrix;

    public SolverUndoColSwapsCommand(Solver solver, Matrix matrix) {
        this.solver = solver;
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        solver.undoColSwaps(matrix);
    }
}
