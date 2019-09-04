package solver;

import org.junit.Test;

public class MainTest {

    @Test
    public void testMain() {
        Main.main(new String[]{"-in", "src/test/resources/in.txt", "-out", "out.txt"});

    }

    @Test
    public void testMain20() {
        Main.main(new String[]{"-in", "src/test/resources/in20.txt", "-out", "out.txt"});

    }

    @Test
    public void testMain6x6() {
        Main.main(new String[]{"-in", "src/test/resources/in6x6.txt", "-out", "out.txt"});

    }

    @Test
    public void testMain4x4NoSolution() {
        Main.main(new String[]{"-in", "src/test/resources/4x4NoSolution.txt", "-out", "out.txt"});

    }

    @Test
    public void testMain4x3OneSolution() {
        Main.main(new String[]{"-in", "src/test/resources/4x3OneSolution.txt", "-out", "out.txt"});

    }
    @Test
    public void testMain3x4NoSolution() {
        Main.main(new String[]{"-in", "src/test/resources/3x4NoSolution.txt", "-out", "out.txt"});

    }
}
