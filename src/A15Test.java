import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A15Test
{
    @Test
    void loadSensors() throws IOException {
        assertEquals(14, A15.loadSensors(new File("a15.test.txt")).size());
    }
    @Test
    void loadData() throws IOException {
        assertEquals(26, A15.calculateOnLine(10, A15.loadSensors(new File("a15.test.txt"))));
    }

    @Test
    void loadDataFull() throws IOException {
        assertEquals(26, A15.calculateOnLine(2000000, A15.loadSensors(new File("a15.full.txt"))));
    }

}