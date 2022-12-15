import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A8Test
{
    @Test
    void checkTest() throws IOException {
        assertEquals(21, A8.countVisible(new File("a8.test.txt")));
    }
    @Test
    void checkFullTest() throws IOException {
        assertEquals(21, A8.countVisible(new File("a8.full.txt")));
    }

    @Test
    void checkTestScenic() throws IOException {
        assertEquals(8, A8.bestScenicVisible(new File("a8.test.txt")));
    }
    @Test
    void checkFullScenicTest() throws IOException {
        assertEquals(21, A8.bestScenicVisible(new File("a8.full.txt")));
    }
}