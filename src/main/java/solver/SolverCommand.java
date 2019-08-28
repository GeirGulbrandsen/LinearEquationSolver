package solver;

public class SolverCommand {
    private Solver solver;
    private Matrix matrix;

    public SolverCommand(Solver solver, Matrix matrix) {
        this.solver = solver;
        this.matrix = matrix;
    }

    public String execute() {
        return solver.solveSystem(matrix);
    }
}
