import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class A14 {

    public static class RockBottomException extends RuntimeException
    {

    }

    public static List<List<Point>> parsePoints(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        var points = new ArrayList<List<Point>>();
        for (String line : lines) {
            var lineOfPoints = new ArrayList<Point>();
            var pointParts = line.split(" -> ");
            for (String pointPart : pointParts) {
                var miniparts = pointPart.split(",");
                Point p = new Point(Integer.parseInt(miniparts[0]), Integer.parseInt(miniparts[1]));
                lineOfPoints.add(p);
            }
            points.add(lineOfPoints);
        }
        return points;
    }

    public static char[][] findFinalPosition(char[][] board, Point particlePosition)
    {
        var currentPoint = particlePosition;
        var pointHasChanged = true;
        do {
            var adjustedpoint = trickleDown(board, currentPoint);
            pointHasChanged = !adjustedpoint.equals(currentPoint);
            currentPoint = adjustedpoint;
        } while(pointHasChanged);
        // while can trickleDown, do
        // if no longer can trickleDown
        // mark on the board.
        board[currentPoint.y][currentPoint.x] = 'o';
        return board;
    }

    public static long calculate(char[][] board)
    {
        int nrSandDrops = 0;
        try{
            while (true){
                nrSandDrops++;
                board = findFinalPosition(board, new Point(500,0));
            }
        } catch (RockBottomException ex) {

        }
        return nrSandDrops;
    }

    public static Point trickleDown(char[][] board, Point particlePosition)
    {
        if (particlePosition.y + 1 >= board.length)
            throw new RockBottomException();

        if (board[particlePosition.y + 1][particlePosition.x] == 0){
            return new Point(particlePosition.x, particlePosition.y + 1);
        }
        else if (board[particlePosition.y + 1][particlePosition.x - 1] == 0){
            return new Point(particlePosition.x - 1, particlePosition.y + 1);
        }
        else if (board[particlePosition.y + 1][particlePosition.x + 1] == 0){
            return new Point(particlePosition.x + 1, particlePosition.y + 1);
        }

        /*
        A unit of sand always falls down one step if possible.
        If the tile immediately below is blocked (by rock or sand), the unit of sand attempts to instead move
         diagonally one step down and to the left. If that tile is blocked, the unit of sand attempts to instead move
          diagonally one step down and to the right. Sand keeps moving as long as it is able to do so, at each step
          trying to move down, then down-left, then down-right. If all three possible destinations are blocked,
          the unit of sand comes to rest and no longer moves,
          at which point the next unit of sand is created back at the source.
         */
        return particlePosition;
    }

    public static String showBoard(int fromX, int toX, int fromY, int toY, char[][] board) {
        var builder = new StringBuilder();
        for (int y = fromY; y <= toY; y++)
        {
            for (int x = fromX; x <= toX; x++) {
                if (board[y][x] == 0) {
                    builder.append(' ');
                } else {
                    builder.append(board[y][x]);
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    public static char[][] parseBoard(File file) throws IOException {
        char[][] board = buildEmptyBoard(file);

        for (List<Point> rowOfPoints : parsePoints(file)) {
            var currentPoint = rowOfPoints.get(0);
            for (int pointIndex = 1; pointIndex < rowOfPoints.size(); pointIndex++) {
                markPoints(rowOfPoints.get(pointIndex),currentPoint, board);
                currentPoint = rowOfPoints.get(pointIndex);
            }
        }
        return board;
    }

    public static char[][] markPoints(Point fromPoint, Point toPoint, char[][] board) {
        for (int x = fromPoint.x; x <= toPoint.x; x++) {
            board[fromPoint.y][x] = '#';
        }
        for (int x = toPoint.x; x <= fromPoint.x; x++) {
            board[toPoint.y][x] = '#';
        }
        for (int y = fromPoint.y; y <= toPoint.y; y++) {
            board[y][fromPoint.x] = '#';
        }
        for (int y = toPoint.y; y <= fromPoint.y; y++) {
            board[y][toPoint.x] = '#';
        }
        return board;
    }

    private static char[][] buildEmptyBoard(File file) throws IOException {
        var maxX = 0;
        var maxY = 0;
        for (List<Point> rowOfPoints : parsePoints(file)) {
            if (rowOfPoints.stream().mapToInt(e -> e.x).max().orElse(0) > maxX) {
                maxX = rowOfPoints.stream().mapToInt(e -> e.x).max().orElse(0);
            }
            if (rowOfPoints.stream().mapToInt(e -> e.y).max().orElse(0) > maxY) {
                maxY = rowOfPoints.stream().mapToInt(e -> e.y).max().orElse(0);
            }
        }
        char[][] board = new char[maxY + 1][maxX + 1];
        return board;
    }
}
