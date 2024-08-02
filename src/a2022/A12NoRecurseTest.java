package a2022;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A12NoRecurseTest
{
    @Test
    public void parse() throws IOException {
        A12Board board = A12.parse(new File("a12.test.txt"));
        assertEquals(new Point(0,0), board.startPoint);
        assertEquals(new Point(5,2), board.endPoint);
        assertEquals(31, new A12NoRecurse(board).loopBoard());
    }

    @Test
    public void parseFull() throws IOException {
        A12Board board = A12.parse(new File("a12.full.txt"));
        assertEquals(new Point(0,20), board.startPoint);
        assertEquals(new Point(120,20), board.endPoint);
        assertEquals(31, new A12NoRecurse(board).loopBoard());
    }
}