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
        int getHeightCommittedBlocks() {
            for (int y = 0; y < board.length; y++) {
                var hasElement = false;
                for (int x = 0; x < board[y].length; x++) {
                    if (board[y][x]) {
                        hasElement = true;
                    }
                }
                if (!hasElement) return y;
            }
            return board.length;
        }
        Board dropBlock(BlockType block, ListQueue<Moves> nextMoves, int dropHeight)
        {
            var dynamicBlock = DynamicBlocks.create(2,dropHeight, block);
            while(!incomingCollision(dynamicBlock)) {
                var move = nextMoves.pop();
                if (move == Moves.Left) {
                    dynamicBlock = dynamicBlock.moveToLeft();
                }
                if (move == Moves.Right) {
                    dynamicBlock = dynamicBlock.moveToRight(7);
                }
                dynamicBlock = dynamicBlock.moveDown();
            }
            commitBoard(dynamicBlock);
            return this;
        }

        private boolean incomingCollision(DynamicBlocks dynamicBlock) {
            return dynamicBlock.points.stream().anyMatch(p -> p.y == 0 || board[p.y - 1][p.x]);
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
