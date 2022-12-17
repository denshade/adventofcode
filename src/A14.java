import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class A14
{
    public static List<List<Point>> parseBoard(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        var points = new ArrayList<List<Point>>();
        for(String line: lines)
        {
            var lineOfPoints  = new ArrayList<Point>();
            var pointParts = line.split(" -> ");
            for (String pointPart: pointParts) {
                var miniparts = pointPart.split(",");
                Point p = new Point(Integer.parseInt(miniparts[0]), Integer.parseInt(miniparts[1]));
                lineOfPoints.add(p);
            }
            points.add(lineOfPoints);
        }
        return points;
    }
}
