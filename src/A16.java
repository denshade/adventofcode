import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class A16
{
    public static class Valve {
        String name;
        int rate;
        boolean open = false;
        List<Valve> valves = new ArrayList<>();
    }

    public static ValveSystem calculate(List<Valve> valves)
    {
        long bestSolutionScore = 0;

        var sys = new ValveSystem(valves.get(0), valves);
        PriorityQueue<ValveSystem> systems = new PriorityQueue<>();
        systems.add(sys);
        while(systems.size() > 0) {
            var currentSys = systems.poll();
            if (currentSys.nrTicks == 30) {
                if (currentSys.totalRate > bestSolutionScore) {
                    bestSolutionScore = currentSys.totalRate;
                    System.out.println(bestSolutionScore);
                }
            }
            if (!currentSys.currentValve.open) {
                systems.add(currentSys.openValve().tick());
            }
            for (var availableValves : currentSys.currentValve.valves) {
                systems.add(currentSys.gotoValve(availableValves).tick());
            }
        }
        return sys;
    }

    public static class ValveSystem implements Comparable<ValveSystem>
    {
        public Valve currentValve;
        public List<Valve> allValves;
        public long totalRate = 0;
        public long nrTicks = 0;

        public ValveSystem(Valve currentValve, List<Valve> otherValves) {
            this.currentValve = currentValve;
            this.allValves = otherValves;
        }
        public ValveSystem(ValveSystem system) {
            this.currentValve = system.currentValve;
            this.allValves = system.allValves;
            this.totalRate = system.totalRate;
            this.nrTicks = system.nrTicks;
        }

        public ValveSystem tick()
        {
            var newValveSystem = new ValveSystem(this);
            newValveSystem.nrTicks++;
            newValveSystem.totalRate += newValveSystem.allValves.stream().filter(v -> v.open).mapToLong(v -> v.rate).sum();
            return newValveSystem;
        }


        public ValveSystem gotoValve(Valve valve)
        {
            var newValveSystem = new ValveSystem(this);
            newValveSystem.currentValve = valve;
            return newValveSystem;
        }

        public ValveSystem openValve()
        {
            var newValveSystem = new ValveSystem(this);
            newValveSystem.currentValve.open = true;
            return newValveSystem;
        }

        @Override
        public int compareTo(ValveSystem o) {
            return (int)(-1*this.totalRate) - (int)(-1*o.totalRate);
        }
    }
    public static long calculate(File file) throws IOException {
        var valves = loadValves(file);
        return calculate(valves).totalRate;
    }

    public static List<Valve> loadValves(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        List<Valve> valves = new ArrayList<>();
        Map<String, Valve> valveMap = new HashMap<>();
        for (var line : lines) {
            var valve=  new Valve();
            var lineParts = line.split(" ");
            valve.name = lineParts[1];
            var rate = lineParts[4];
            var rateStripped = rate.substring("rate=".length());
            valve.rate = Integer.parseInt(rateStripped.substring(0, rateStripped.length() - 1 ));
            valves.add(valve);
            valveMap.put(valve.name, valve);
        }

        for (var line : lines) {
            var lineParts = line.split(" ");
            String valveName = lineParts[1];
            var currentValve = valveMap.get(valveName);
            for (int l = 9; l < lineParts.length; l++) {
                var otherValve = lineParts[l].replaceAll(",", "");
                currentValve.valves.add(valveMap.get(otherValve));
            }
        }

        return valves;
    }
}
