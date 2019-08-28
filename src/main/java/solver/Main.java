package solver;


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
        gaussJordanElim(matrix, outputFile);


    }
}
