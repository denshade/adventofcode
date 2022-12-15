import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A5Test {

    @Test
    void checkStacks() throws IOException {
        var stacks = A5.loadStacks(new File("a5.test.txt"));
        assertEquals(3, stacks.size());
        assertEquals("[Z, N]", stacks.get(0).toString());
        assertEquals("[M, C, D]", stacks.get(1).toString());
    }

    @Test
    void checkFile() throws IOException {
        var stacks = A5.processFile(new File("a5.test.txt"));
        assertEquals("", A5.toString(stacks));
    }

    @Test
    void checkFileFull() throws IOException {
        var stacks = A5.processFile(new File("a5.full.txt"));
        assertEquals("", A5.toString(stacks));
    }

}