package a2022;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.BitSet;
import java.util.List;

public class A4
{
    public static BitSet parseRange(String range)
    {
        String[] parts = range.split("-");
        var firstPart = parts[0];
        var secondPart = parts[1];
        int firstPartL = Integer.parseInt(firstPart);
        int secondPartL = Integer.parseInt(secondPart);
        var bitSet = new BitSet();
        for (int index = firstPartL; index <= secondPartL; index++) {
            bitSet.set(index);
        }
        return bitSet;
    }

    public static long calculate(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        var counter = 0;
        for (var line : lines) {
            String[] parts = line.split(",");
            var firstPart = parts[0];
            var secondPart = parts[1];
            var firstBitSet = parseRange(firstPart);
            var secondBitSet = parseRange(secondPart);

            if (containsAll(firstBitSet, secondBitSet)||containsAll(secondBitSet, firstBitSet))
                counter++;
        }
        return counter;
    }

    public static long calculateOverlap(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        var counter = 0;
        for (var line : lines) {
            String[] parts = line.split(",");
            var firstPart = parts[0];
            var secondPart = parts[1];
            var firstBitSet = parseRange(firstPart);
            var secondBitSet = parseRange(secondPart);

            if (overlaps(firstBitSet, secondBitSet)||overlaps(secondBitSet, firstBitSet))
                counter++;
        }
        return counter;
    }
    private static boolean overlaps(BitSet s1, BitSet s2) {
        BitSet intersection = (BitSet) s1.clone();
        intersection.and(s2);
        return !intersection.isEmpty();
    }

    private static boolean containsAll(BitSet s1, BitSet s2) {
        BitSet intersection = (BitSet) s1.clone();
        intersection.and(s2);
        return intersection.equals(s2);
    }
}
