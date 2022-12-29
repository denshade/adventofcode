import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class A16Test {
    @Test
    void loadVals() throws IOException {
        assertEquals(10, getTestValves().size());
    }

    private List<A16.Valve> getTestValves() throws IOException {
        return A16.loadValves(new File("a16.test.txt"));
    }

    @Test
    void checkSolutionSys() throws IOException {
        assertEquals(1651, A16.calculate(getTestValves()).getActual());
    }
    @Test
    void checkSolutionFirstStepSys() throws IOException {
        List<A16.Valve> valves = getTestValves();
        var sys = new A16.ValveSystem(valves.get(0), valves);
        assertEquals(2146, sys.getOneStepFurther().get(0).getOptimistic());
    }

    @Test
    void checkValveOpened() throws IOException {
        var valves = getTestValves();
        var v = new A16.ValveSystem(valves.get(0), valves).gotoValve(valves.get(1)).openValve().tick();
        assertEquals(1, v.nrTicks);
    }
    @Test
    void checkExampleValveMap() throws IOException {
        var valves = getTestValves();
        var map = new HashMap<String, A16.Valve>();
        for (A16.Valve valve : valves) {
            map.put(valve.name, valve);
        }
        assertEquals(2, map.get("BB").valves.size());
    }
    @Test
    void checkExampleValveScore() throws IOException {
        var valves = getTestValves();
        var map = new HashMap<String, A16.Valve>();
        for (A16.Valve valve: valves) {
            map.put(valve.name, valve);
        }
        var v = new A16.
                ValveSystem(map.get("AA"), valves)
                .gotoAndTick(map.get("DD"))
                .openAndTick()
                .gotoAndTick(map.get("CC"))
                .gotoAndTick(map.get("BB"))
                .openAndTick()
                .gotoAndTick(map.get("AA"))
                .gotoAndTick(map.get("II"))
                .gotoAndTick(map.get("JJ"))
                .openAndTick()
                .gotoAndTick(map.get("II"))
                .gotoAndTick(map.get("AA"))
                .gotoAndTick(map.get("DD"))
                .gotoAndTick(map.get("EE"))
                .gotoAndTick(map.get("FF"))
                .gotoAndTick(map.get("GG"))
                .gotoAndTick(map.get("HH"))//16
                .openAndTick()//17
                .gotoAndTick(map.get("GG"))//18
                .gotoAndTick(map.get("FF"))//19
                .gotoAndTick(map.get("EE"))//20
                .openAndTick()//21
                .gotoAndTick(map.get("DD"))//22
                .gotoAndTick(map.get("CC"))//23
                .openAndTick()//24
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
    @Test
    void checkExampleValveOptimistic() throws IOException {
        var valves = getTestValves();
        var map = new HashMap<String, A16.Valve>();
        for (A16.Valve valve : valves) {
            map.put(valve.name, valve);
        }
        var v = new A16.
                ValveSystem(map.get("AA"), valves)
                .gotoAndTick(map.get("DD"))
                .openAndTick()
                .gotoAndTick(map.get("CC"))
                .gotoAndTick(map.get("BB"))
                .openAndTick()
                .gotoAndTick(map.get("AA"))
                .gotoAndTick(map.get("II"))
                .gotoAndTick(map.get("JJ"))
                .openAndTick()

                ;
        assertTrue(v.getOptimistic()> 1500);

    }
}