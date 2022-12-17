import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

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
