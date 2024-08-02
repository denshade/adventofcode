package a2022;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class MonkeyParser
{

    private static final int LCM = 19 * 3 * 11 * 17 * 5 * 2 * 13 * 7;

    public static List<Monkey> parse(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        var monkeyList = new ArrayList<Monkey>();
        var currentMonkey = new Monkey();
        var set = new HashSet<Integer>();
        for (var line : lines) {
            if (line.equals("")) {
                monkeyList.add(currentMonkey);
                currentMonkey = new Monkey();
            }
            if (line.startsWith("  Starting items: ")) {
                currentMonkey.items = Arrays.stream(line.substring("  Starting items: ".length()).split(",")).map(l -> new BigInteger(l.trim())).collect(Collectors.toList());
            }
            if (line.startsWith("  Test: divisible by ")) {
                currentMonkey.divisibility = Integer.parseInt(line.substring("  Test: divisible by ".length()));
                set.add(currentMonkey.divisibility);
            }
            if (line.startsWith("    If true: throw to monkey ")) {
                currentMonkey.trueMonkey = Integer.parseInt(line.substring("    If true: throw to monkey ".length()));
            }
            if (line.startsWith("    If false: throw to monkey ")) {
                currentMonkey.falseMonkey = Integer.parseInt(line.substring("    If false: throw to monkey ".length()));
            }
            if (line.startsWith("  Operation: new = old * old")) {
                currentMonkey.monkeyCommand = new MonkeySquare(LCM);
            }
            else if (line.startsWith("  Operation: new = old * ")) {
                currentMonkey.monkeyCommand = new MonkeyMult(Integer.parseInt(line.substring("  Operation: new = old * ".length())), LCM);
            }
            if (line.startsWith("  Operation: new = old + ")) {
                currentMonkey.monkeyCommand = new MonkeyAdd(Integer.parseInt(line.substring("  Operation: new = old + ".length())), LCM);
            }
        }
        monkeyList.add(currentMonkey);

        var i = 0;
        for (var line : lines) {
        }


        return monkeyList;
    }
}
