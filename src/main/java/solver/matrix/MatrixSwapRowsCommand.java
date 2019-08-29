package solver.matrix;

import solver.commands.Command;

public class MatrixSwapRowsCommand implements Command {
    private final Matrix matrix;
    private final int row1;
    private final int row2;

    MatrixSwapRowsCommand(Matrix matrix, int row1, int row2) {
        this.matrix = matrix;
        this.row1 = row1;
        this.row2 = row2;
    }

    @Override
    public void execute() {
        this.matrix.swapRows(row1, row2);
    }
}
