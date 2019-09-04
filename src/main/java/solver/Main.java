package solver;

import solver.commands.CommandCentral;
import solver.commands.SolverSolveCommand;
import solver.matrix.Matrix;
import solver.solver.Solver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String inputFile = "";
        String outputFile = "";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-in":
                    inputFile = args[++i];
                    break;
                case "-out":
                    outputFile = args[++i];
            }
        }

        CommandCentral comms = new CommandCentral();
        Solver solver = new Solver();
        Matrix matrix = Matrix.readMatrixFromFile(inputFile);

        if (matrix != null) {
            comms.addCmd(new SolverSolveCommand(solver, matrix));
            comms.processCmds();

            try (FileWriter fileWriter = new FileWriter(outputFile)) {
                if (solver.hasNoSolution() || solver.hasInfiniteSolutions()) {
                    fileWriter.write(matrix.getSolution());
                } else {
                    Arrays.stream(solver.getSolution()
                            .split(" "))
                            .forEach(x -> {
                                try {
                                    fileWriter.write(x + "\n");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.printf("Saved to file %s\n%n", outputFile);
        }
    }
}
