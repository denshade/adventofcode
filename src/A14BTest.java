import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class A14BTest {

    @Test
    void parsePoints() throws IOException {
        var points = A14.parsePoints(new File("a14.test.txt"));
        assertEquals(2, points.size());
        assertEquals(3, points.get(0).size());
        assertEquals(4, points.get(1).size());
    }

    @Test
    void createBoard() throws IOException {
        var board = A14.parseBoard(new File("a14.test.txt"));
        assertEquals("", A14.showBoard(494, 503, 0, 9, board));
    }

    @Test
    void markPositions() throws IOException {
        var board = new char[5][5];
        A14B.markPoints(new Point(1, 0), new Point(1, 1), board);
        assertEquals('#', board[0][1]);
        assertEquals('#', board[1][1]);
    }

    @Test
    void trickleBoard() throws IOException {
        var board = A14B.parseBoard(new File("a14.test.txt"));
        assertEquals(new Point(500, 1), A14.trickleDown(board, new Point(500, 0)));
        assertEquals(new Point(501, 4), A14.trickleDown(board, new Point(502, 3)));
        assertEquals(new Point(500, 8), A14.trickleDown(board, new Point(500, 8)));
    }

    @Test
    void findFinalPosition() throws IOException {
        var board = A14.parseBoard(new File("a14.test.txt"));
        var adjustedBoard = A14.findFinalPosition(board, new Point(500, 0));
        assertEquals('o', adjustedBoard[8][500]);
    }

    @Test
    void findSolution() throws IOException {
        var board = A14B.parseBoard(new File("a14.test.txt"));
        var score = A14B.calculate(board);
        assertEquals(93, score);
    }
    @Test
    void findFullSolution() throws IOException {
        var board = A14B.parseBoard(new File("a14.full.txt"));
        var score = A14B.calculate(board);
        assertEquals(22499, score);
    }
}