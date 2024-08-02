package a2022;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class A3
{
    public static String getDuplicated(String line)
    {
        String firstPart = line.substring(0,line.length()/2);
        String secondPart = line.substring(line.length()/2);
        if (firstPart.length() != secondPart.length()) {
            throw new IllegalArgumentException(firstPart + " vs. "+secondPart);
        }
        var firstSet = firstPart.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        var secondSet = secondPart.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        firstSet.retainAll(secondSet);
        return String.valueOf(firstSet.stream().findFirst().get());
    }
    public static int toScore(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return ch - 'a' + 1;
        }
        if (ch >= 'A' && ch <= 'Z') {
            return ch - 'A' + 27;
        }
        throw new IllegalArgumentException("unknown char "+ch);

    }
    public static int calculate(String line) {
        var s = getDuplicated(line);

        return toScore(s.charAt(0));
    }
    public static long calculateTriple(String s1, String s2, String s3) {
        var firstSet = s1.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        var secondSet = s2.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        var thirdSet = s3.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        firstSet.retainAll(secondSet);
        firstSet.retainAll(thirdSet);
        return toScore(firstSet.stream().findFirst().get());
    }
    public static long calculateTriple(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        long sum = 0;
        for (int index = 0; index < lines.size(); index += 3)
        {
            sum += calculateTriple(lines.get(index), lines.get(index + 1), lines.get(index + 2));
        }
        return sum;
    }

        public static long calculate(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        return lines.stream().mapToInt(A3::calculate).sum();
    }
}
