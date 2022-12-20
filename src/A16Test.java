import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A16Test {
    @Test
    void loadVals() throws IOException {
        assertEquals(10, A16.loadValves(new File("a16.test.txt")).size());
    }

}