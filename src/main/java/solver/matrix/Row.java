package solver.matrix;

public class Row {
    private String name;
    private int length;
    private double[] coefficients;

    public Row(String name, double[] coefficients) {
        this.coefficients = coefficients;
        this.length = coefficients.length;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public double getValue(int pos) {
        return coefficients[pos];
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    void swapValues(int val1, int val2) {
        double temp = coefficients[val1];
        coefficients[val1] = coefficients[val2];
        coefficients[val2] = temp;
    }
}
