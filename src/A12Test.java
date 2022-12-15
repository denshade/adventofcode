import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class A12Test
{
    @Test
    public void parse() throws IOException {
        A12Board board = A12.parse(new File("a12.test.txt"));
        assertEquals(new Point(0,0), board.startPoint);
        assertEquals(new Point(5,2), board.endPoint);
        assertEquals(29, new A12NoRecurse(board).loopBoards());
    }

    @Test
    public void parseFull() throws IOException {
        A12Board board = A12.parse(new File("a12.full.txt"));
        assertEquals(new Point(0,20), board.startPoint);
        assertEquals(new Point(120,20), board.endPoint);
        assertEquals(31, new A12NoRecurse(board).loopBoards());
    }
}