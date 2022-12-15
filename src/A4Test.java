import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A4Test
{
    @Test
    void testA4() throws IOException {
        assertEquals(2, A4.calculate(new File("a4-categories.txt")));
    }

    @Test
    void testA4Full() throws IOException {
        assertEquals(526, A4.calculate(new File("a4-full.txt")));
    }
    @Test
    void testA4Overlaps() throws IOException {
        assertEquals(4, A4.calculateOverlap(new File("a4-categories.txt")));
    }
    @Test
    void testA4OverlapsFull() throws IOException {
        assertEquals(886, A4.calculateOverlap(new File("a4-full.txt")));
    }

}