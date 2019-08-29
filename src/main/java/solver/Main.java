package solver;

import java.io.FileWriter;
import java.io.IOException;

import static solver.Solver.gaussJordanElim;

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

        Matrix matrix = Matrix.readMatrixFromFile(inputFile);

        if (matrix != null) {
            gaussJordanElim(matrix);

            try (FileWriter fileWriter = new FileWriter(outputFile)) {
                for (String str : matrix.getSolution().split(" ")) {
                    fileWriter.write(str + "\n");
                }
            } catch (IOException e) {
                System.out.println("Could not open file");
            }
            System.out.printf("Saved to file %s\n%n", outputFile);
        }
    }
}
