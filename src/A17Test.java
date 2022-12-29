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
        ListQueue<A17.Moves> queue = A17.Moves.createQueue(testString);
        assertEquals(A17.Moves.Right, queue.pop());
        assertEquals(A17.Moves.Right, queue.pop());
        assertEquals(A17.Moves.Right, queue.pop());
        assertEquals(A17.Moves.Left, queue.pop());
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