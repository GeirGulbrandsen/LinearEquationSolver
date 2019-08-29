package solver;

class SolverCommand implements Command {
    private Solver solver;
    private Matrix matrix;

    SolverCommand(Solver solver, Matrix matrix) {
        this.solver = solver;
        this.matrix = matrix;
    }

    public String execute() {
        return solver.solveSystem(matrix);
    }
}
