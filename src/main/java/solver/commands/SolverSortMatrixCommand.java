package solver.commands;

import solver.matrix.Matrix;
import solver.solver.Solver;

public class SolverSortMatrixCommand implements Command {
    private Solver solver;
    private Matrix matrix;

    public SolverSortMatrixCommand(Solver solver, Matrix matrix) {
        this.solver = solver;
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        this.solver.sortMatrix(matrix, 0);
    }
}
