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
    void createBlock() {
        assertNotNull(A17.DynamicBlocks.create(0,0, A17.BlockType.Block));
        assertEquals("##\n" +
                "##\n", new A17.Board(2,2).commitBoard(A17.DynamicBlocks.create(0,0, A17.BlockType.Block)).toString());
        assertEquals("#.\n" +
                "#.\n"+
                "#.\n"+
                "#.\n"
                , new A17.Board(2,4).commitBoard(A17.DynamicBlocks.create(0,0, A17.BlockType.I)).toString());
        assertEquals("####\n"
                , new A17.Board(4,1).commitBoard(A17.DynamicBlocks.create(0,0, A17.BlockType.Minus)).toString());
        assertEquals(".#.\n" +
                        "###\n" +
                        ".#.\n"
                , new A17.Board(3,3).commitBoard(A17.DynamicBlocks.create(0,0, A17.BlockType.Plus)).toString());
        assertEquals("..#\n" +
                        "..#\n" +
                        "###"
                , new A17.Board(3,3).commitBoard(A17.DynamicBlocks.create(0,0, A17.BlockType.L)).toString());
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
        var q = A17.BlockType.getBlockQueue();
        assertEquals(A17.BlockType.Minus, q.pop());
        assertEquals(A17.BlockType.Plus, q.pop());
        assertEquals(A17.BlockType.L, q.pop());
        assertEquals(A17.BlockType.I, q.pop());
        assertEquals(A17.BlockType.Block, q.pop());
        assertEquals(A17.BlockType.Minus, q.pop());
    }
        @Test
    void calculate()
    {
        assertEquals(3068, A17.calculateHeight(testString));
    }

}