package a2022;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class A10CommandParser
{
    public static List<A10Command> parse(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        return lines.stream().map(A10CommandParser::parseLine).collect(Collectors.toList());
    }
    public static A10Command parseLine(String line) {
        if (line.equals("noop")) {
            return new A10Noop();
        }
        if (line.startsWith("addx")) {
            var howMuch = Integer.parseInt(line.split(" ")[1]);
            return new A10Add(howMuch);
        }
        throw new IllegalArgumentException(line);

    }
}
