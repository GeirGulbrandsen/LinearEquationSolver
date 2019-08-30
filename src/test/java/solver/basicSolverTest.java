package solver;

import org.junit.Test;
import solver.solver.Solver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class basicSolverTest {

    @Test
    public void solves5timesXis3() {
        assertThat(new Solver().findX(5,3), is(0.6));
    }
}
