import java.awt.*;
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

    public enum BlockType {
        Minus, Plus, L, I, Block;
        public static ListQueue<BlockType> getBlockQueue()
        {
            return new ListQueue<>(List.of(values()));
        }
    }
    public static class DynamicBlocks
    {
        private final List<Point> points;

        public static DynamicBlocks create(int xOffset, int height, BlockType blockType){
            var pts = switch(blockType){
                case Minus -> List.of(new Point(xOffset,height), new Point(xOffset + 1,height), new Point(xOffset + 2,height), new Point(xOffset + 3,height));
                case Plus -> List.of(
                                                                    new Point(xOffset + 1,height),
                        new Point(xOffset,height + 1),    new Point(xOffset + 1,height + 1), new Point(xOffset + 2,height + 1),
                                                                    new Point(xOffset + 1,height + 2));
                case L -> List.of(new Point(xOffset + 2,height),
                        new Point(xOffset + 2,height + 1),
                        new Point(xOffset,height + 2), new Point(xOffset + 1,height + 2),new Point(xOffset + 2,height + 2)
                );
                case I -> List.of(new Point(xOffset,height),
                        new Point(xOffset,height + 1),
                        new Point(xOffset,height + 2),
                        new Point(xOffset,height + 3)
                );
                case Block -> List.of(new Point(xOffset,height),new Point(xOffset + 1,height),
                        new Point(xOffset,height + 1),new Point(xOffset + 1,height + 1));
                default -> throw new IllegalStateException("Unexpected value: " + blockType);
            };
            return new DynamicBlocks(pts);
        }
        private DynamicBlocks(List<Point> points)
        {
            this.points = points;
        }
    }

    public static class Board {
        private boolean[][] board;
        public Board(int width, int height) {
            board = new boolean[height][width];
        }

        public Board commitBoard(DynamicBlocks blocks)
        {
            for (Point point: blocks.points) {
                board[point.y][point.x] = true;
            }
            return this;
        }
        public String toString()
        {
            var builder = new StringBuilder();
            for (int y = 0; y < board.length; y++)
            {
                for (int x = 0; x < board[y].length; x++) {
                    builder.append(board[y][x]?'#':'.');
                }
                builder.append('\n');
            }
            return builder.toString();
        }

        Board dropBlock(BlockType block, Queue<Moves> nextMoves)
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
        var board = new Board("..@@@@.".length(), 4000);
        for (int block = 0; block < 2022; block++) {
            //board.dropBlock()
        }
        return 0;
    }

}
