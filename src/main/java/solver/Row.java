package solver;

import java.util.Arrays;

class Row {
    private String name;
    private int length;
    private double[] coefficients;

    Row(String name, double[] coefficients) {
        this.coefficients = coefficients;
        this.length = coefficients.length;
        this.name = name;
    }

    String getName() {
        return name;
    }

    int getLength() {
        return length;
    }

    double getValue(int pos) {
        return coefficients[pos];
    }

    double[] getCoefficients() {
        return coefficients;
    }
}
