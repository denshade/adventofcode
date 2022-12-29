import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class A17
{
    public enum Moves {
        Left, Right;
        static List<Moves> parse(String moves){
            return moves.chars().mapToObj(e-> e=='<'?Left:Right).collect(Collectors.toList());
        }
        static ListQueue<Moves> createQueue(String moves){
            return new ListQueue<>(parse(moves));
        }

    }

    public enum Block {
        Minus, Plus, L, I, Block;
        public static ListQueue<Block> getBlockQueue()
        {
            return new ListQueue<>(List.of(values()));
        }
    }

    public static class Board {
        Board dropBlock(Block block, Queue<Moves> nextMoves)
        {
            return this;
        }
        long getHeight()
        {
            return 0;
        }
    }
    public static int calculateHeight(String directions)
    {
        var board = new Board();
        for (int block = 0; block < 2022; block++) {
            //board.dropBlock()
        }
        return 0;
    }

}
