package a2022;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class A15
{
    static class Sensor {

        public Sensor(Point location, Point closestBeacon)
        {
            this.location = location;
            this.closestBeacon = closestBeacon;
            distanceToBeacon = calculate(location, closestBeacon);
        }
        Point location;
        Point closestBeacon;
        long distanceToBeacon;

        public boolean isInRange(Point position)
        {
            return calculate(location, position) <= distanceToBeacon;
        }

        public List<Point> getEdgePoints(int max)
        {
            var list = new ArrayList<Point>();
            for (int y = 0; y <= distanceToBeacon; y++) {
                System.out.println(y);
                for (int x = (int) (distanceToBeacon - y); x <= distanceToBeacon; x++) {
                    if (y + x < distanceToBeacon) continue;
                    Point p2 = new Point(location.x - x, location.y - y);
                    if (calculate(location, p2) == distanceToBeacon + 1) {
                        list.add(p2);
                    }
                    Point p1 = new Point(location.x + x, location.y - y);
                    if (calculate(location, p1) == distanceToBeacon + 1) {
                        list.add(p1);
                    }
                    Point p3 = new Point(location.x - x, location.y + y);
                    if (calculate(location, p1) == distanceToBeacon + 1) {
                        list.add(p3);
                    }
                    Point p4 = new Point(location.x + x, location.y + y);
                    if (calculate(location, p4) == distanceToBeacon + 1) {
                        list.add(p4);
                    }

                }
            }
            return list.stream().filter(e -> e.y < max && e.y > -1 && e.x < max && e.x > -1).collect(Collectors.toList());
        }
    }
    public static long calculate(Point p1, Point p2) {
        int dist = Math.abs(p2.x - p1.x) + Math.abs(p2.y - p1.y);
        return dist;
    }

    public static long calculateOnLine(int y, List<Sensor> sensors)
    {
        int correct = 0;
        for (int x = -10000000; x < 10000000; x++) {
            correct = getCorrect(y, sensors, correct, x);
        }
        return correct;
    }

    private static int getCorrect(int y, List<Sensor> sensors, int correct, int x) {
        for (Sensor sensor : sensors) {
            if (sensor.isInRange(new Point(x, y))) {
                correct++;
                break;
            }
        }
        for (Sensor sensor : sensors) {
            if (sensor.closestBeacon.equals(new Point(x, y))) {
                correct--;
                break;
            }
        }

        return correct;
    }

    public static Point findAtEdge(List<Sensor> sensors, int maxRange)
    {
        for (Sensor sensor : sensors) {
            var points = sensor.getEdgePoints(maxRange);
            for (var point: points)
            {
                if (sensors.stream().noneMatch(s -> s.isInRange(point))){
                    return point;
                }
            }
        }
        return new Point(0,0);
    }

    public static Point findPointNotTaken(List<Sensor> sensors, int maxRange)
    {
        for (int y = 0; y < maxRange; y++) {
            System.out.println(y);
            for (int x = 0; x < maxRange; x++) {
                var point = new Point(x,y);
                if (sensors.stream().noneMatch(s -> s.isInRange(point))){
                    return point;
                }
            }
        }
        return new Point(0, 0);
    }

    static List<Sensor> loadSensors(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        var lst = new ArrayList<Sensor>();
        for (String l : lines) {
            var sensorL = l.split(":")[0];
            var rX = sensorL.substring("Sensor at x=".length(), sensorL.indexOf(','));
            var rY = sensorL.substring(sensorL.indexOf(',') + 4);
            var beaconL = l.split(":")[1];
            var bX = beaconL.substring(" closest beacon is at x=".length(), beaconL.indexOf(','));
            var bY = beaconL.substring(beaconL.indexOf(',') + 4);
            var sensor = new Sensor(new Point(Integer.parseInt(rX), Integer.parseInt(rY)),
                    new Point(Integer.parseInt(bX), Integer.parseInt(bY)));
            lst.add(sensor);
        }
        return lst;
    }

}
