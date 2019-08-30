package solver.solver;

import solver.commands.Command;
import solver.matrix.Matrix;

public class SolverSortMatrixCommand implements Command {
    private Solver solver;
    private Matrix matrix;

    SolverSortMatrixCommand(Solver solver, Matrix matrix) {
        this.solver = solver;
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        this.solver.sortMatrix(matrix, 0);
    }
}
