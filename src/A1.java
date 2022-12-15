import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;

public class A1
{

    static long maxCaloriesForAllElves(File file) throws IOException {
        return caloriesPerElf(file).stream().max(Long::compare).orElse(0L);
    }
    static long top3Elves(File file) throws IOException {
        List<Long> topCalories = caloriesPerElf(file);
        Collections.sort(topCalories, Collections.reverseOrder());

        long sum = 0;
        for (int i = 0; i < 3; i++){
            sum += topCalories.get(i);
        }
        return sum;
    }

    static List<Long> caloriesPerElf(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        List<Long> results = new ArrayList<>();
        long accumulation = 0;
        for (String line : lines) {
            if (line.equals("")) {
                results.add(accumulation);
                accumulation = 0;
            } else {
                accumulation += Integer.parseInt(line);
            }
        }
        results.add(accumulation);
        return results;
    }
}
