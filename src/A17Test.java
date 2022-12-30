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
        assertNotNull(DynamicBlocks.create(0,0, A17.BlockType.Block));
        assertEquals("""
                ##
                ##
                """, new A17.Board(2,2).commitBoard(DynamicBlocks.create(0,0, A17.BlockType.Block)).toString());
        assertEquals("""
                        #.
                        #.
                        #.
                        #.
                        """
                , new A17.Board(2,4).commitBoard(DynamicBlocks.create(0,0, A17.BlockType.I)).toString());
        assertEquals("####\n"
                , new A17.Board(4,1).commitBoard(DynamicBlocks.create(0,0, A17.BlockType.Minus)).toString());
        assertEquals("""
                        .#.
                        ###
                        .#.
                        """
                , new A17.Board(3,3).commitBoard(DynamicBlocks.create(0,0, A17.BlockType.Plus)).toString());
        assertEquals("""
                        ..#
                        ..#
                        ###
                        """
                , new A17.Board(3,3).commitBoard(DynamicBlocks.create(0,0, A17.BlockType.L)).toString());
    }

    @Test
    void dropDown()
    {
        assertTrue(new A17.Board(7,100).dropBlock(A17.BlockType.Minus, A17.Moves.createQueue(testString), 4).toString().contains("..####."));
    }
    @Test
    void verifyHeight() {
        assertEquals(0,new A17.Board(7,100).getHeightCommittedBlocks());
        assertEquals(3,new A17.Board(7,100).commitBoard(DynamicBlocks.create(0,0, A17.BlockType.L)).getHeightCommittedBlocks());
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