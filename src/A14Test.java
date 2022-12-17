import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A14Test
{

    @Test
    void parseBoard() throws IOException {
        var board= A14.parseBoard(new File("a14.test.txt"));
        assertEquals(2, board.size());
        assertEquals(3, board.get(0).size());
        assertEquals(4, board.get(1).size());
    }
}