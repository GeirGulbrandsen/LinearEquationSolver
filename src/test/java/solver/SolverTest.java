package solver;

import org.junit.Test;
import solver.solver.Solver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SolverTest {
    @Test
    public void findX() {
        assertThat(Solver.findX(5, 3), is(0.6));
    }

    @Test
    public void findXandY() {
        String[] abc = {"4", "5", "7"};
        String[] def = {"3", "9", "9"};
        assertThat(Solver.findXandY(abc, def), is("0.85714 0.71429"));
    }
}