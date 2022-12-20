import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A16
{
    public static class Valve {
        String name;
        int rate;
        List<Valve> valves = new ArrayList<>();
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
