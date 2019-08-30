package solver.commands;

import solver.matrix.Matrix;

public class MatrixSwapColsCommand implements Command {
    private final Matrix matrix;
    private final int col1;
    private final int col2;

    public MatrixSwapColsCommand(Matrix matrix, int col1, int col2) {
        this.matrix = matrix;
        this.col1 = col1;
        this.col2 = col2;
    }

    @Override
    public void execute() {
        this.matrix.swapCols(col1, col2);
    }
}
