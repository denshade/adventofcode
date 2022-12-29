import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class A16BTest {

    private List<A16B.Valve> getTestValves() throws IOException {
        return A16B.loadValves(new File("a16.test.txt"));
    }

    @Test
    void checkSolutionSys() throws IOException {
        assertEquals(1707, A16B.calculate(getTestValves()).getActual());
    }
}