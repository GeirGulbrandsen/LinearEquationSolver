package solver;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void testMain() {
        int ExpextedNumberOfLines = 3;

        Main.main(new String[]{"-in", "src/test/resources/in.txt", "-out", "out.txt"});

        assertEquals(ExpextedNumberOfLines,countLinesFromOutPut());
    }

    @Test
    public void testMain20() {
        int ExpextedNumberOfLines = 20;

        Main.main(new String[]{"-in", "src/test/resources/in20.txt", "-out", "out.txt"});

        assertEquals(ExpextedNumberOfLines,countLinesFromOutPut());
    }

    @Test
    public void testMain6x6() {
        int ExpextedNumberOfLines = 6;

        Main.main(new String[]{"-in", "src/test/resources/in6x6.txt", "-out", "out.txt"});

        assertEquals(ExpextedNumberOfLines,countLinesFromOutPut());
    }

    @Test
    public void testMain4x4NoSolution() {
        int ExpextedNumberOfLines = 1;

        Main.main(new String[]{"-in", "src/test/resources/4x4NoSolution.txt", "-out", "out.txt"});

        assertEquals(ExpextedNumberOfLines,countLinesFromOutPut());
    }

    @Test
    public void testMain4x3OneSolution() {
        int ExpextedNumberOfLines = 3;

        Main.main(new String[]{"-in", "src/test/resources/4x3OneSolution.txt", "-out", "out.txt"});

        assertEquals(ExpextedNumberOfLines,countLinesFromOutPut());
    }

    @Test
    public void testMain3x4NoSolution() {
        int ExpextedNumberOfLines = 1;

        Main.main(new String[]{"-in", "src/test/resources/3x4NoSolution.txt", "-out", "out.txt"});

        assertEquals(ExpextedNumberOfLines,countLinesFromOutPut());
    }


    private int countLinesFromOutPut() {
        int lines = 0;
        try (FileReader fileReader = new FileReader("out.txt")){
            BufferedReader reader = new BufferedReader(fileReader);
            while (reader.readLine() != null){
                System.out.println("read");
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
