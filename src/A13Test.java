import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class A13Test
{
    @Test
    void checkLoads() throws IOException {
        assertEquals(13, A13.calculateIndices(new File("a13.test.txt")));
    }
    @Test
    void checkLoadsFull() throws IOException {
        assertEquals(13, A13.getIndices(new File("a13.full.txt")));
    }
    @Test
    void checkLoadsFullSum() throws IOException {
        assertEquals(5450, A13.calculateIndices(new File("a13.full.txt")));
    }

}