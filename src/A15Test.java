import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A15Test
{
    @Test
    void loadSensors() throws IOException {
        assertEquals(14, A15.loadSensors(new File("a15.test.txt")).size());
    }
    @Test
    void loadData() throws IOException {
        assertEquals(26, A15.calculateOnLine(10, A15.loadSensors(new File("a15.test.txt"))));
    }

    @Test
    void findUncoveredTest() throws IOException {
        assertEquals(new Point(14, 11), A15.findPointNotTaken(A15.loadSensors(new File("a15.test.txt")), 20));
    }
    @Test
    void findUncoveredPaintTest() throws IOException {
        assertEquals(new Point(14, 11), A15.findAtEdge(A15.loadSensors(new File("a15.full.txt")), 4000000));
    }

    @Test
    void findUncoveredFullTest() throws IOException {
        assertEquals(new Point(14, 11), A15.findPointNotTaken(A15.loadSensors(new File("a15.full.txt")), 4000000));
    }

    @Test
    void loadDataFull() throws IOException {
        assertEquals(26, A15.calculateOnLine(2000000, A15.loadSensors(new File("a15.full.txt"))));
    }

    @Test
    void loadPointsAtEdge() {
        var sensor = new A15.Sensor(new Point(50,0), new Point(60,0));
        assertEquals(40, sensor.getEdgePoints(100).size());
    }

}