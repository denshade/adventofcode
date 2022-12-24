import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A16Test {
    @Test
    void loadVals() throws IOException {
        assertEquals(10, A16.loadValves(new File("a16.test.txt")).size());
    }
    @Test
    void checkSolution() throws IOException {
        assertEquals(1651, A16.loadValves(new File("a16.test.txt")).size());
    }

    @Test
    void checkSolutionSys() throws IOException {
        assertEquals(1651, A16.calculate(A16.loadValves(new File("a16.test.txt"))).totalRate);
    }

    @Test
    void checkValveOpened() throws IOException {
        var valves = A16.loadValves(new File("a16.test.txt"));
        var v = new A16.ValveSystem(valves.get(0), valves).gotoValve(valves.get(1)).openValve().tick();
        assertEquals(1, v.nrTicks);
        assertEquals(13, v.totalRate);

    }

}