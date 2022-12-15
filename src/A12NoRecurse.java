import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class A12NoRecurse
{
    Set<Point> visitedPoints;
    A12Board board;
    public A12NoRecurse(A12Board board)
    {
        this.board = board;
        visitedPoints = new HashSet<>();
    }
    public int loopBoards()
    {
        var bestRoad= 10000;
        //loop over all boards with start position 'a'.
        for (int y = 0; y < board.boardScores.length; y++) {
            for (int x = 0; x < board.boardScores[y].length; x++) {
                if (board.boardScores[y][x] == 0) {
                    board.startPoint = new Point(x, y);
                    var currentRoad = loopBoard();
                    if (currentRoad < bestRoad) {
                        bestRoad = currentRoad;
                    }
                }
            }
        }
        return bestRoad;
    }
    public int loopBoard()
    {
        var score = new int[board.boardScores.length][board.boardScores[0].length];
        for(int y = 0; y < score.length; y++)
        {
            Arrays.fill(score[y], 1000);
        }
        score[board.startPoint.y][board.startPoint.x] = 0;
        var hasChange = true;
        while (hasChange) {
            hasChange = false;
            for (int y = 0; y < score.length; y++) {
                for (int x = 0; x < score[y].length; x++) {
                    var currentValue = board.boardScores[y][x];
                    var currentScore = score[y][x];

                    Point left = new Point(x - 1, y);
                    Point right = new Point(x + 1, y);
                    Point up = new Point(x, y + 1);
                    Point down = new Point(x, y - 1);
                    var options = Set.of(left, right, up, down);
                    var validoptions = options.stream().filter(move -> isValidMove(move, board, currentValue, visitedPoints)).collect(Collectors.toList());
                    for (var validoption : validoptions) {
                        var scoreAtOption = score[validoption.y][validoption.x];
                        if (currentScore != 1000 && scoreAtOption > currentScore + 1) {
                            score[validoption.y][validoption.x] = currentScore + 1;
                            hasChange = true;
                        }
                    }
                }
            }
        }
        printScore(score);
        return score[board.endPoint.y][board.endPoint.x];
    }

    private void printScore(int[][] score) {
        System.out.println();
        for (int y = 0; y < score.length; y++)
        {
            for (int x = 0; x < score[y].length; x++){
                System.out.print(score[y][x] + "["+String.valueOf((char)(board.boardScores[y][x]+ 'a')) + "] , ");
            }
            System.out.println();

        }

    }

    private static boolean isValidMove(Point point, A12Board board, int currentValue, Set<Point> visitedPoints)
    {
        if (point.x < 0) return false;
        if (point.x >=  board.boardScores[0].length) return false;
        if (point.y < 0) return false;
        if (point.y >=  board.boardScores.length) return false;
        if (board.boardScores[point.y][point.x] == Integer.MIN_VALUE) return true;
        if (visitedPoints.contains(point)) return false;
        if (board.boardScores[point.y][point.x] > currentValue + 1) return false;
        return true;
    }
    public static A12Board parse(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        A12Board a12Board = new A12Board();
        a12Board.boardScores = new int[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++) {
            var line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
            {
                var currentChar = line.charAt(x);
                if (currentChar == 'S') {
                    a12Board.startPoint = new Point(x,y);
                    a12Board.boardScores[y][x] = 0;
                } else if (currentChar == 'E') {
                    a12Board.boardScores[y][x] = 'z' - 'a';
                    a12Board.endPoint = new Point(x,y);
                } else {
                    a12Board.boardScores[y][x] = currentChar - 'a';
                }
            }
        }
        return a12Board;

    }
}
