import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class A17Test {
    private final String testString = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";
    @Test
    void parseMoves()
    {
        assertEquals(testString.length(), A17.Moves.parse(testString).size());
        assertEquals(A17.Moves.Right, A17.Moves.parse(testString).get(0));
        assertEquals(A17.Moves.Left, A17.Moves.parse(testString).get(4));

    }
    @Test
    void parseListQueue()
    {
        assertEquals(A17.Moves.Right, A17.Moves.createQueue(testString).pop());
        assertEquals(A17.Moves.Right, A17.Moves.createQueue(testString).pop());
        assertEquals(A17.Moves.Right, A17.Moves.createQueue(testString).pop());
        assertEquals(A17.Moves.Left, A17.Moves.createQueue(testString).pop());
    }
    @Test
    void parseListBlocks() {
        var q = A17.Block.getBlockQueue();
        assertEquals(A17.Block.Minus, q.pop());
        assertEquals(A17.Block.Plus, q.pop());
        assertEquals(A17.Block.L, q.pop());
        assertEquals(A17.Block.I, q.pop());
        assertEquals(A17.Block.Block, q.pop());
        assertEquals(A17.Block.Minus, q.pop());
    }
        @Test
    void calculate()
    {
        assertEquals(3068, A17.calculateHeight(testString));
    }

}