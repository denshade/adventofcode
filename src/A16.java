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
        boolean open = false;
        List<Valve> valves = new ArrayList<>();

        Valve()
        {

        }
        Valve(Valve valve) {
            this.name = valve.name;
            this.rate = valve.rate;
            this.open = valve.open;
            this.valves = valve.valves;
        }

        @Override
        public int compareTo(Valve o) {
            return this.rate - o.rate;
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
        public List<Valve> allValves;
        public long totalRate = 0;
        public int nrTicks = 0;

        public ValveSystem(Valve currentValve, List<Valve> otherValves) {
            this.currentValve = currentValve;
            this.allValves = otherValves;
        }
        public ValveSystem(ValveSystem system) {
            this.currentValve = new Valve(system.currentValve);
            this.allValves = system.allValves.stream().map(Valve::new).collect(Collectors.toList());
            this.totalRate = system.totalRate;
            this.nrTicks = system.nrTicks;
        }

        public ValveSystem tick()
        {
            var newValveSystem = new ValveSystem(this);
            newValveSystem.nrTicks++;
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
            newValveSystem.totalRate += newValveSystem.currentValve.rate * (NR_MINUTES - newValveSystem.nrTicks);
            return newValveSystem;
        }

        @Override
        public long getActual() {
            return totalRate;
        }

        public long getOptimistic()
        {
            if (allValves.stream().filter(e->!e.open).count() == 0) {
                return 0;
            }
            var toOpenValves = allValves.stream().filter(e->!e.open).mapToLong(e ->e.rate).sorted().boxed().collect(Collectors.toList());
            long nrs = 0;
            var loopBack = 0;
            for (int tick = nrTicks+1; tick <= NR_MINUTES; tick++) {
                if (toOpenValves.size() - tick - nrTicks < 0) break;
                var ticksLeft = NR_MINUTES - tick;
                Long aLong = toOpenValves.get(toOpenValves.size() - ++loopBack);
                nrs += aLong * (ticksLeft);
            }
            return nrs;
        }

        @Override
        public OptimismElimination.Solution getBestGreedyWalk() {
            var valveSystem = new ValveSystem(this);
            while(valveSystem.nrTicks < NR_MINUTES) {
                if (!valveSystem.currentValve.open) {
                    valveSystem = valveSystem.openValve().tick();
                    System.out.println("Opened valve " + valveSystem.currentValve.name);
                } else {
                    var openValves = valveSystem.currentValve.valves.stream().filter(e -> !e.open).collect(Collectors.toList());
                    openValves.sort(Collections.reverseOrder());
                    if (!openValves.isEmpty()) {
                        valveSystem = valveSystem.gotoValve(openValves.get(0)).tick();
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
            if (!currentSys.currentValve.open) {
                systems.add(currentSys.openValve().tick());
            }
            for (var availableValves : currentSys.currentValve.valves) {
                systems.add(currentSys.gotoValve(availableValves).tick());
            }
            return systems;
        }

        @Override
        public OptimismElimination.Solution clone() {
            return new ValveSystem(this);
        }

        public int compareTo(OptimismElimination.Solution o) {
            return (int)(-1*(getOptimistic() + totalRate)) - (int)(-1*(o.getOptimistic()+o.getActual()));
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
