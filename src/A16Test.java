import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class A16Test {
    @Test
    void loadVals() throws IOException {
        assertEquals(10, A16.loadValves(new File("a16.test.txt")).size());
    }

    @Test
    void checkSolutionSys() throws IOException {
        assertEquals(1651, A16.calculate(A16.loadValves(new File("a16.test.txt"))).getActual());
    }
    @Test
    void checkSolutionFirstStepSys() throws IOException {
        List<A16.Valve> valves = A16.loadValves(new File("a16.test.txt"));
        var sys = new A16.ValveSystem(valves.get(0), valves);
        assertEquals(2146, sys.getOneStepFurther().get(0).getOptimistic());
    }

    @Test
    void checkValveOpened() throws IOException {
        var valves = A16.loadValves(new File("a16.test.txt"));
        var v = new A16.ValveSystem(valves.get(0), valves).gotoValve(valves.get(1)).openValve().tick();
        assertEquals(1, v.nrTicks);
    }
    @Test
    void checkExampleValveScore() throws IOException {
        var valves = A16.loadValves(new File("a16.test.txt"));
        var map = new HashMap<String, A16.Valve>();
        for (A16.Valve valve: valves) {
            map.put(valve.name, valve);
        }
        var v = new A16.
                ValveSystem(map.get("AA"), valves)
                .gotoValve(map.get("DD")).tick()
                .openValve().tick()
                .gotoValve(map.get("CC")).tick()
                .gotoValve(map.get("BB")).tick()
                .openValve().tick()
                .gotoValve(map.get("AA")).tick()
                .gotoValve(map.get("II")).tick()
                .gotoValve(map.get("JJ")).tick()
                .openValve().tick()
                .gotoValve(map.get("II")).tick()
                .gotoValve(map.get("AA")).tick()
                .gotoValve(map.get("DD")).tick()
                .gotoValve(map.get("EE")).tick()
                .gotoValve(map.get("FF")).tick()
                .gotoValve(map.get("GG")).tick()
                .gotoValve(map.get("HH")).tick()//16
                .openValve().tick()//17
                .gotoValve(map.get("GG")).tick()//18
                .gotoValve(map.get("FF")).tick()//19
                .gotoValve(map.get("EE")).tick()//20
                .openValve().tick()//21
                .gotoValve(map.get("DD")).tick()//22
                .gotoValve(map.get("CC")).tick()//23
                .openValve().tick()//24
                .tick()//25
                .tick()//26
                .tick()//27
                .tick()//28
                .tick()//29
                .tick()//30
                ;
        assertEquals(30, v.nrTicks);
        assertEquals(1651, v.getActual());

    }
}