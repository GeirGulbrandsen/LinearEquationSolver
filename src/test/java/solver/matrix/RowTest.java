package solver.matrix;

import org.junit.Test;
import solver.matrix.Row;

import static org.junit.Assert.assertArrayEquals;
import static solver.solver.Solver.normaliseRow;

public class RowTest {

    @Test
    public void weCanNormaliseTheFirstRow() {
        Row row1 = new Row("R1", new double[]{2, 3, 4, 5});

        normaliseRow(row1, 0);

        assertArrayEquals(new double[]{1.0, 1.5, 2.0, 2.5}, row1.getCoefficients(), 0.001);
    }

    @Test
    public void weCanNormaliseTheSecondRow() {
        Row row1 = new Row("R1", new double[]{0, 2, 4, 5});

        normaliseRow(row1, 1);

        assertArrayEquals(new double[]{0.0, 1.0, 2.0, 2.5}, row1.getCoefficients(), 0.001);
    }
}
