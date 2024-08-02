package a2022;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class A12
{
    Set<Point> visitedPoints;
    A12Board board;
    public A12(A12Board board)
    {
        this.board = board;
        visitedPoints = new HashSet<>();
    }
    public int boardRecurse(Point currentPosition)
    {
        var currentValue = board.boardScores[currentPosition.y][currentPosition.x];
        if (currentPosition.equals(board.endPoint)) {
            return 0;
        }
        visitedPoints.add(currentPosition);
        Point left = new Point(currentPosition.x - 1, currentPosition.y);
        Point right = new Point(currentPosition.x + 1, currentPosition.y);
        Point up = new Point(currentPosition.x, currentPosition.y + 1);
        Point down = new Point(currentPosition.x, currentPosition.y - 1);
        var options = Set.of(left, right, up, down);
        var validoptions = options.stream().filter(move -> isValidMove(move, board, currentValue, visitedPoints)).collect(Collectors.toList());
        var results = validoptions.stream().map(validoption -> boardRecurse(validoption)).collect(Collectors.toList());
        return results.stream().mapToInt(l -> l).min().orElse(1000) + 1;
    }
    private static boolean isValidMove(Point point, A12Board board, int currentValue, Set<Point> visitedPoints)
    {
        if (point.x < 0) return false;
        if (point.x >=  board.boardScores[0].length) return false;
        if (point.y < 0) return false;
        if (point.y >=  board.boardScores.length) return false;
        if (board.boardScores[point.y][point.x] == Integer.MIN_VALUE) return true;
        if (visitedPoints.contains(point)) return false;
        if (board.boardScores[point.y][point.x] < currentValue || board.boardScores[point.y][point.x] > currentValue + 1) return false;
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
