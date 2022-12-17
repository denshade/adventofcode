import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class A15
{
    static class Sensor {
        Point location;
        Point closestBeacon;
    }
    static List<Sensor> loadSensors(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        var lst = new ArrayList<Sensor>();
        for (String l : lines) {
            var sensor = new Sensor();
            var sensorL = l.split(":")[0];
            var rX = sensorL.substring("Sensor at x=".length(), sensorL.indexOf(','));
            var rY = sensorL.substring(sensorL.indexOf(',') + 4);
            var beaconL = l.split(":")[1];
            var bX = beaconL.substring(" closest beacon is at x=".length(), beaconL.indexOf(','));
            var bY = beaconL.substring(beaconL.indexOf(',') + 4);
            sensor.location = new Point(Integer.parseInt(rX), Integer.parseInt(rY));
            sensor.closestBeacon = new Point(Integer.parseInt(bX), Integer.parseInt(bY));
            lst.add(sensor);
        }
        return lst;
    }

}
