import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A14Test
{

    @Test
    void parsePoints() throws IOException {
        var points= A14.parsePoints(new File("a14.test.txt"));
        assertEquals(2, points.size());
        assertEquals(3, points.get(0).size());
        assertEquals(4, points.get(1).size());
    }

    @Test
    void createBoard() throws IOException {
        var board= A14.parseBoard(new File("a14.test.txt"));
        assertEquals("", A14.showBoard(494, 503, 0, 9, board));
    }

    @Test
    void markPositions() throws IOException {
        var board = new char[5][5];
        A14.markPoints(new Point(1,0), new Point(1,1), board);
        assertEquals('#', board[0][1] );
        assertEquals('#', board[1][1] );
    }

    @Test
    void trickleBoard() throws IOException {
        var board= A14.parseBoard(new File("a14.test.txt"));
        assertEquals(new Point(500,1), A14.trickleDown(board, new Point(500,0)));
    }
}