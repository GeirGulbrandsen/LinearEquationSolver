package solver;

class SolverCommand {
    private Solver solver;
    private Matrix matrix;

    SolverCommand(Solver solver, Matrix matrix) {
        this.solver = solver;
        this.matrix = matrix;
    }

    String execute() {
        return solver.solveSystem(matrix);
    }
}
