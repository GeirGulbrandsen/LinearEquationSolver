package solver.commands;

import solver.matrix.Matrix;

public class MatrixSwapRowsCommand implements Command {
    private final Matrix matrix;
    private final int row1;
    private final int row2;

    public MatrixSwapRowsCommand(Matrix matrix, int row1, int row2) {
        this.matrix = matrix;
        this.row1 = row1;
        this.row2 = row2;
    }

    @Override
    public void execute() {
        this.matrix.swapRows(row1, row2);
    }
}
