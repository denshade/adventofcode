import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class A9Left implements A9Command
{
    private final int steps;

    public A9Left(int steps){
        this.steps = steps;
    }

    @Override
    public List<Point> getHeadLocations(Point location) {
        List<Point> points = new ArrayList<>();
        for (int cy = 1; cy <= steps; cy++) {
            points.add(new Point(location.x - cy, location.y));
        }
        return points;
    }
}
