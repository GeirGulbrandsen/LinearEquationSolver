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
}
