import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class A16
{
    private static int NR_MINUTES = 30;
    public static class Valve implements Comparable<Valve>{
        String name;
        int rate;
        List<Valve> valves = new ArrayList<>();

        @Override
        public int compareTo(Valve o) {
            return this.rate - o.rate;
        }
        public String toString()
        {
            return this.name;
        }
    }

    public static ValveSystem calculate(List<Valve> valves)
    {
        var el = new OptimismElimination();
        var sys = new ValveSystem(valves.get(0), valves);
        return (ValveSystem) el.calculate(sys);
    }

    public static class ValveSystem implements OptimismElimination.Solution
    {
        public Valve currentValve;
        Map<Valve,Integer> openValveToTickTimes = new HashMap<>();
        public List<Valve> allValves;
        public int nrTicks = 0;

        public ValveSystem(Valve currentValve, List<Valve> otherValves) {
            this.currentValve = currentValve;
            this.allValves = otherValves;
        }
        public ValveSystem(ValveSystem system) {
            this.currentValve = system.currentValve;
            this.allValves = system.allValves;
            this.openValveToTickTimes = new HashMap<>(system.openValveToTickTimes);
            this.nrTicks = system.nrTicks;
        }

        public ValveSystem tick()
        {
            var newValveSystem = new ValveSystem(this);
            newValveSystem.nrTicks++;
            return newValveSystem;
        }

        public ValveSystem openAndTick()
        {
            return openValve().tick();
        }
        public ValveSystem gotoAndTick(Valve valve)
        {
            return gotoValve(valve).tick();
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
            newValveSystem.openValveToTickTimes.put(newValveSystem.currentValve, newValveSystem.nrTicks + 1);
            return newValveSystem;
        }

        @Override
        public long getActual() {
            return openValveToTickTimes.entrySet().stream().mapToLong(e -> (long) e.getKey().rate * (NR_MINUTES - e.getValue())).sum();
        }

        public long getOptimistic()
        {
            ArrayList<Valve> allClosedValves = getAllClosedValves();
            if (allClosedValves.size() == 0) {
                return 0;
            }
            var allClosedValvesValues = allClosedValves.stream().map(e -> e.rate * -1).collect(Collectors.toCollection(PriorityQueue::new));
            long nrs = 0;
            for (int tick = nrTicks+1; tick <= NR_MINUTES; tick++) {
                if (allClosedValvesValues.size() == 0) break;
                var ticksLeft = NR_MINUTES - tick;
                int bestRate = allClosedValvesValues.poll() * -1;
                nrs += (long) bestRate * (ticksLeft);
            }
            return nrs + getActual();
        }
        public boolean isCurrentValveOpen()
        {
            return openValveToTickTimes.containsKey(currentValve);
        }

        private ArrayList<Valve> getAllClosedValves() {
            var allClosedValves = new ArrayList<>(allValves);
            allClosedValves.removeAll(openValveToTickTimes.keySet());
            return allClosedValves;
        }

        public String toString()
        {
            return "@" + currentValve + " " + openValveToTickTimes.toString();
        }

        @Override
        public OptimismElimination.Solution getBestGreedyWalk() {
            var valveSystem = new ValveSystem(this);
            while(valveSystem.nrTicks < NR_MINUTES) {
                if (!valveSystem.isCurrentValveOpen() && valveSystem.currentValve.rate != 0) {
                    valveSystem = valveSystem.openValve().tick();
                    System.out.println("Opened valve " + valveSystem.currentValve.name);
                } else {
                    var closedValves = new ArrayList<>(valveSystem.currentValve.valves);
                    closedValves.removeAll(valveSystem.openValveToTickTimes.keySet());
                    closedValves.sort(Collections.reverseOrder());
                    if (!closedValves.isEmpty()) {
                        System.out.println("Goto valve " + closedValves.get(0));
                        valveSystem = valveSystem.gotoValve(closedValves.get(0)).tick();
                    } else {
                        valveSystem = valveSystem.tick();
                    }
                }
            }
            return valveSystem;
        }

        @Override
        public List<OptimismElimination.Solution> getOneStepFurther() {
            var systems = new ArrayList<OptimismElimination.Solution>();
            var currentSys = this;
            if (!currentSys.isCurrentValveOpen()) {
                systems.add(currentSys.openAndTick());
            }
            for (var availableValves : currentSys.currentValve.valves) {
                systems.add(currentSys.gotoAndTick(availableValves));
            }
            return systems;
        }

        @Override
        public OptimismElimination.Solution clone() {
            return new ValveSystem(this);
        }

        public int compareTo(OptimismElimination.Solution o) {
            return (int)(-1*(getOptimistic())) - (int)(-1*(o.getOptimistic()));
        }

    }
    public static long calculate(File file) throws IOException {
        var valves = loadValves(file);
        return calculate(valves).getActual();
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
