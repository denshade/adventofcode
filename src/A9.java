import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class A9
{
    private Point head;
    private Point tail;

    public A9()
    {
        head = new Point();
        tail = new Point();
    }

    public Set<Point> moveSnake(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        var tailPoints = new HashSet<Point>();
        var commands = lines.stream().map(A9::parseLine).collect(Collectors.toList());
        for (A9Command command: commands)
        {
            tailPoints.addAll(getTailMoves(command));
        }
        return tailPoints;
    }

    public static A9Command parseLine(String s)
    {
        var parts = s.split(" ");
        var nrSteps = Integer.parseInt(parts[1]);
        switch(parts[0])
        {
            case "R": return new A9Right(nrSteps);
            case "L": return new A9Left(nrSteps);
            case "U": return new A9Up(nrSteps);
            case "D": return new A9Down(nrSteps);
            default:
                throw new IllegalArgumentException(parts[0]);
        }
    }


    public List<Point> getTailMoves(A9Command a9Command)
    {
        var headMoves = a9Command.getHeadLocations(head);
        var tailMoves = new ArrayList<Point>();
        for (Point headMovement: headMoves) {
            head.setLocation(headMovement);
            Point tailMovement = new Point(calculateTailMovement(head, tail));
            tail = tailMovement;
            System.out.println(head + " vs. " + tailMovement);
            tailMoves.add(tailMovement);
        }
        return tailMoves;
    }

    public static Point calculateTailMovement(Point head, Point tail) {
        Point result = new Point(tail);
        if (Math.abs(head.x - tail.x) < 2 && Math.abs(head.y -tail.y) < 2) {
            return result;
        }
        if (head.x - tail.x == 2) {
            result.x = head.x - 1;
            if (Math.abs(head.y - tail.y) == 1) {
                result.y = head.y;
            }
        }
        if (head.y - tail.y == 2) {
            result.y = head.y - 1;
            if (Math.abs(head.x - tail.x) == 1) {
                result.x = head.x;
            }
        }
        if (tail.x - head.x == 2) {
            result.x = head.x + 1;
            if (Math.abs(head.y - tail.y) == 1) {
                result.y = head.y;
            }
        }
        if (tail.y - head.y == 2) {
            result.y = head.y + 1;
            if (Math.abs(head.x - tail.x) == 1) {
                result.x = head.x;
            }
        }
        return result;
    }
}
