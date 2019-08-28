package solver;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class basicSolverTest {

    @Test
    public void solves5timesXis3() {
        assertThat(Solver.findX(5,3), is(0.6));
    }
}
