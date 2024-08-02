package a2022;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class A9Test
{
    @Test
    void findTailMoves() throws IOException {
        var set = new A9().moveSnake(new File("a9.full.txt"));
        //assertEquals("", set.toString());
        assertEquals(13, set.size());
    }

    @Test
    void findTailDiagonal() throws IOException {
        var point = A9.calculateTailMovement(new Point(4, 0), new Point(3,0));
        assertEquals(new Point(3,0), point);
        point = A9.calculateTailMovement(new Point(4, -1), new Point(3,0));
        assertEquals(new Point(3,0), point);
        point = A9.calculateTailMovement(new Point(4, -2), new Point(3,0));
        assertEquals(new Point(4,-1), point);
    }

}