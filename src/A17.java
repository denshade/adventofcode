import java.util.ArrayList;
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
    }

    public enum Block {
        Minus, Plus, L, I, Block
    }

    public static class Board {
        Board dropBlock(Block block, Queue<Moves> nextMoves)
        {
            return this;
        }
    }
    public static int calculateHeight(String directions)
    {
        for (int block = 0; block < 2022; block++) {

        }
        return 0;
    }

}
